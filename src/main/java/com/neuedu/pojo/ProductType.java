package com.neuedu.pojo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductType {
    private Integer id;

    private Integer flag;
    //格式化前端传来的时间
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer createUserid;
    
    private Date updateTime;

    private Integer updateUserid;

    private String productTypeName;

    private Integer parentId;
    //多对一，获取父类
    private ProductType parent;
    //一对一，获取创建人
    private User createUser;
    //一对一，获取修改人
    private User updateUser;
    //一对多，获取子类
    private List<ProductType> children;
   
}