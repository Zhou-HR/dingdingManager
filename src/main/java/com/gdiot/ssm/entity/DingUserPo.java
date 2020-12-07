package com.gdiot.ssm.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ZhouHR
 */
@Data
public class DingUserPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String user_id;
    private String user_detail;

}
