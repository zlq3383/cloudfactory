package com.neuedu.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permit {
    private Integer id;

    private Integer flag;

    private Integer status;

    private Integer parentId;

    private String permitName;

    private String permitIcon;

    private String permitPath;
    //子类权限
    private List<Permit> children;
}