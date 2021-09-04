package com.neuedu.pojo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Integer id;

    private Integer flag;
    
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer createUserid;
    
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer updateUserid;

    private String productNum;

    private String productName;

    private String productImgUrl;

    private Integer productTypeId;
    //1比1，关联映射产品种类
    private ProductType productType;
    
    //1比1，关联映射创建人
    private User createUser;
    //1比1，关联映射修改人
    private User updateUser;
    //1对多，关联映射产品订单
    private List<ProductOrder> productOrders;

}