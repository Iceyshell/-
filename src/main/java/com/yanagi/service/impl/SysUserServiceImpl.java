package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanagi.config.security.service.UserDetailServiceImpl;
import com.yanagi.entity.*;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.SysUserMapper;
import com.yanagi.service.SysUserService;
import com.yanagi.utils.*;
import com.yanagi.vo.ChangePwdVo;
import com.yanagi.vo.LoginVo;
import com.yanagi.vo.UserQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanagi
 * @description 用户service实现
 * @date 2024-04-20 23:13
 */
//加入service注解和打印日志的注解
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public Result findAll() {
        log.info("获取用户信息");
        return Result.success("获取用户信息成功", userMapper.findAll());
    }

    @Override
    public Result login(LoginVo loginVo) {
//        log.info("1.开始登录");
//        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getUsername());
//        log.info("2.判读用户是否存在，密码是否正确");
//        if (userDetails == null || !passwordEncoder.matches(loginVo.getPassword(), userDetails.getPassword())){
//            log.error("账号或密码错误");
//            return Result.fail("账号或密码错误，请重新输入");
//        }
//        if (!userDetails.isEnabled()){
//            log.error("该账号已禁用");
//            return Result.fail(" 该账号已禁用，请联系管理员");
//        }
        UserDetails userDetails;
        // 验证码登录
        if ("2".equals(loginVo.getType())) {
            if (!StringUtils.isNotEmpty(loginVo.getPhoneNumber()) || !StringUtils.isNotEmpty(loginVo.getCode())) {
                return Result.fail("请填写完整信息！");
            }
            // 验证码对比
            Object code = redisUtils.getValue(loginVo.getPhoneNumber() + "sms");
            if (code == null) {
                return Result.fail("验证码已过期！");
            }
            if (!code.toString().equals(loginVo.getCode())) {
                return Result.fail("验证码不正确！");
            }
            // 可以通过openid获取
            userDetails = userDetailsService.loadUserByUsername(loginVo.getPhoneNumber());
        } else {
            if (!StringUtils.isNotEmpty(loginVo.getUsername()) || !StringUtils.isNotEmpty(loginVo.getPassword())) {
                return Result.fail("请填写完整的信息！");
            }
            log.info("1. 开始登录");
            userDetails = userDetailsService.loadUserByUsername(loginVo.getUsername());
            log.info("2. 判断用户是否存在，密码是否正确");
            if (null == userDetails || !passwordEncoder.matches(loginVo.getPassword(), userDetails.getPassword())) {
                return Result.fail("账号或密码错误，请重新输入！");
            }
        }
        if (!userDetails.isEnabled()) {
            return Result.fail("该账号未启用，请联系管理员！");
        }
        log.info("登录成功，在security对象中存入登录信息");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("根据登录信息获取token");
        //借助jwt生成token
        String token = tokenUtils.generateToken(userDetails);
        Map<String ,String> map = new HashMap<>(2);
        map.put("tokenHead", tokenHead);
        map.put("token", token);

        if (redisUtils.getValue("userCount") != null){
            int userCount = (Integer)redisUtils.getValue("userCount");
            redisUtils.setValue("userCount", userCount+1);
        }
        else {
            redisUtils.setValue("userCount", 1);
        }
        return Result.success("登录成功", map);
    }

    @Override
    public List<SysMenu> findMenus(Long userId) {
        List<SysMenu> menus = userMapper.findMenus(userId);
        menus.forEach(item -> item.setMeta(new Meta(item.getIcon(), item.getTitle())));
        menus.forEach(item -> item.setChildren(findChildrenMenu(item.getId(), userId)));
        return menus;
    }

    @Override
    public List<SysMenu> findChildrenMenu(Long id, Long userId) {
        List<SysMenu> menus = userMapper.findChildrenMenu(id, userId);
        menus.forEach(item -> item.setMeta(new Meta(item.getIcon(), item.getTitle())));
        return menus;
    }

    @Override
    public Map<String, List<String>> findPermissions(Long userId) {
        List<SysPermission> permissions = userMapper.findPermissions(userId);
        Map<String, List<String>> map = new HashMap<>();
        for(SysPermission permission: permissions) {
            String[] tokens = permission.getCode().split("_");
            // 判断格式符不符合xx_xx
            if (tokens.length < 2) continue;
            if (!map.containsKey(tokens[0])) {
                map.put(tokens[0], new ArrayList<>());
                map.get(tokens[0]).add(tokens[1]);
            }
            else {
                map.get(tokens[0]).add(tokens[1]);
            }
        }
        return map;
    }

    @Override
    public Result findPage(UserQueryVo queryInfo) {
        log.info("分页查询--> 页码==>{}, 页数大小==>{}", queryInfo.getPageNumber(), queryInfo.getPageSize());
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<SysUser> page = userMapper.findPage(queryInfo);
        long total = page.getTotal();
        List<SysUser> result = page.getResult();
        result.forEach(item -> {
            item.setRoles(userMapper.findRoles(item.getId()));
            item.setName(item.getUsername());
            item.setPassword(null);
        });
        Record record = new Record();
        record.setType(4);
        recordMapper.insert(record);

        return PageResult.pageResult(queryInfo.getPageNumber(), queryInfo.getPageSize(), total, result);
    }

    @Transactional
    @Override
    public Result insert(SysUser user) {
        log.info("根据用户名查询用户信息");
        SysUser sysUser = userMapper.findByUsername(user.getUsername());
        if (null != sysUser) {
            return Result.fail("用户名已经存在！");
        }
        sysUser = userMapper.findByEmail(user.getEmail());
        if (null != sysUser) {
            return Result.fail("邮箱已经被使用！");
        }
        log.info("给密码加密");
        // 新用户默认密码123456
        user.setPassword("123456");
        // 用户的密码用md5加密
        user.setPassword(passwordEncoder.encode(MD5Utils.md5(user.getPassword())));
        user.setAdmin(false); // 新创建的用户不是系统管理员
        user.setStatus(true);
        log.info("1. 添加用户信息");
        userMapper.insert(user);
        log.info("2. 添加角色信息");
        List<Long> roleIds = user.getRoleIds();
        if (roleIds.size() > 0) {
            roleIds.forEach(item -> userMapper.insertUserRoles(user.getId(), item));
        }
        log.info("3. 用户角色添加成功有{}个", roleIds.size());

        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);

        return Result.success("用户添加成功！");
    }

    @Transactional
    @Override
    public Result update(SysUser user) {
        // 如果用户的角色信息有变化
        if (user.getRoleIds() != null) {
            //先将用户角色信息给删除
            userMapper.deleteRolesByUserId(user.getId());
            //添加用户角色信息
            List<Long> roleIds = user.getRoleIds();
            if (roleIds.size() > 0) {
                roleIds.forEach(item -> userMapper.insertUserRoles(user.getId(), item));
            }
        }
        //修改用户信息
        userMapper.update(user);

        Record record = new Record();
        record.setType(3);
        recordMapper.insert(record);

        return Result.success("用户信息修改成功！");
    }

    @Override
    public Result delete(Long id) {
        SysUser user = userMapper.findById(id);
        if (null == user) {
            return Result.fail("用户id不存在！");
        }
        userMapper.deleteRolesByUserId(id);
        userMapper.delete(id);

        Record record = new Record();
        record.setType(2);
        recordMapper.insert(record);
        return Result.success("用户信息删除成功！");
    }


    @Override
    public void updatePwdByMail(String email, String password) {
        log.info("邮箱修改密码");
        userMapper.updatePwdByMail(email, password);
    }

    @Override
    public Result updateByopenId(SysUser user) {
        return null;
    }

    @Override
    public Result changePwd(ChangePwdVo changePwdVo) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(changePwdVo.getUsername());
        if (userDetails == null || !passwordEncoder.matches(changePwdVo.getOldPwd(), userDetails.getPassword())){
            log.error("原密码输入错误");
            return Result.fail("原密码错误，请重新输入");
        }
        SysUser user = SecurityUtils.getUser();
        user.setPassword(passwordEncoder.encode(changePwdVo.getNewPwd()));
        userMapper.update(user);
        return Result.success("用户修改密码成功！");

    }

    @Override
    public SysUser findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
