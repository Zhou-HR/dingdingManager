package com.gdiot.ssm.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DingMsgDataPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String userid;
    private String msg;

}
