package com.yanagi.entity;

import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-06-25 22:40
 */
@Data
public class Renter {
    private long id;

    private String name;

    private int sex;

    private String phoneNumber;

    private String email;

    private String avatar;

    private String idCard;

    private boolean status;

}
