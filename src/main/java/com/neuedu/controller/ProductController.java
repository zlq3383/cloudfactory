package com.neuedu.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.pojo.Product;
import com.neuedu.service.ProductService;
import com.neuedu.utils.FactoryResult;
import com.neuedu.utils.SearchProInfo;

/**
 * @author 赵灵巧
 * @create 2021-08-30 10:10
 */

@RestController
@CrossOrigin
public class ProductController {
    @Autowired(required = false)
    private ProductService proService;
    
    //获取通过搜索信息获取所有的分页数据
    @PostMapping("/findProductList")
    public FactoryResult findProductListBySearchInfo(@RequestBody SearchProInfo searchProInfo) {
        //System.out.println(searchProInfo);
        return proService.findAllProBySearchInfo(searchProInfo);
    }
    //定义产品图像上传,
    @RequestMapping("/uploadProImg")
    public FactoryResult uploadProImg(@RequestParam("file") MultipartFile file,@RequestParam Integer id) throws Exception {
    	//获取文件后缀
    	String filename=file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		//保存路径为upload/proudct/创建人id/年月日/图片
		String imgPath="/upload/product/"+id+"/"+date+"/";
		String savePath="D:"+imgPath;
        String realPath = imgPath.replace('/', '\\');
        //用于查看路径是否正确
        //System.out.println(realPath);
        File savePathFile = new File(realPath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String newfilename = UUID.randomUUID().toString()  + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + newfilename));
        } catch (Exception e) {
            e.printStackTrace();
            return FactoryResult.fail();
        }
        //返回文件名称
		return new FactoryResult(200,"保存图片成功",imgPath + newfilename);
    	
    }
    //添加产品
    @PostMapping("/addProduct")
    public FactoryResult addProduct(@RequestBody Product product) {
		return proService.addProduct(product);
    }
    //修改产品
    @PostMapping("/editProduct")
    public FactoryResult editProduct(@RequestBody Product product) {
		return proService.editProduct(product);
    }
    //删除产品
    @PostMapping("/deleteProduct")
	public FactoryResult deleteProduct(@RequestBody Product product) {
		return proService.deleteProduct(product.getId());
	}
	//批量删除产品
	@PostMapping("/deleteProList")
	public FactoryResult deleteProList(@RequestBody int[] ids) {
		return proService.deleteProList(ids);
	}
}
