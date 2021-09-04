package com.neuedu.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 赵灵巧
 * @create 2021-08-30 11:12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchProInfo {
    private Integer pageNum;
    private Integer pageSize;
    private int[]  selectProList;
    private String productName;
}
