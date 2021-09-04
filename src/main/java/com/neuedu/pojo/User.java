package com.neuedu.pojo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer id;

    private Integer flag;

    private Date createTime;

    private Integer createUserid;

    private Date updateTime;

    private Integer updateUserid;

    private Integer userStatus;

    private String userName;

    private String userRealName;

    private String userPasswd;

    private String userJobNum;

    private String userPhoneNum;

    private String userEmail;

    private Integer roleId;

    private Integer factoryId;
    //1对多，关联映射权限
    private List<Permit> permits;
}