package com.gdiot.ssm.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DingAccountPo {

    int id;
    String client;
    String suiteId;
    String appId;
    String suiteKey;
    String suiteSecret;

}
