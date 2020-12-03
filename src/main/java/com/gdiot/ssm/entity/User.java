package com.gdiot.ssm.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String appKey;

    private String appSecret;

}
