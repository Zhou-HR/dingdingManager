package com.gdiot.ssm.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DingDepPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String dep_id;
    private String dep_detail;
    private String dep_name;
    String parent_id;
    //	String AutoAddUser;
    String Code;
    //	String CreateDeptGroup;
    String DeptManagerUseridList;
    String DeptPerimits;
    String DeptPermits;
    //	String GroupContainSubDept;
//	String OuterDept;
    String UserPerimits;
    String UserPermits;

}
