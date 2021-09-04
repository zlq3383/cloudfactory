package com.neuedu.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOrder {
    private Integer id;

    private Integer flag;

    private Date createTime;

    private Integer createUserid;

    private Date updateTime;

    private Integer updateUserid;

    private String orderSeq;

    private Integer orderSource;

    private Integer productId;

    private Integer productCount;

    private Date endDate;

    private Integer orderStatus;

    private Integer factoryId;

    private Integer factoryYield;

    private Date biddingEndDate;

}