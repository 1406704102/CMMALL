package com.ppjj.cmmall.product;

//import com.aliyun.oss.*;
//import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ppjj.cmmall.product.entity.BrandEntity;
import com.ppjj.cmmall.product.service.BrandService;
import com.ppjj.cmmall.product.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
class CmmallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    private CategoryService categoryService;


//    @Autowired
//    OSSClient ossClient;

    @Test
    void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("华为");
//        brandService.save(brandEntity);
//        log.info("保存成功:{}", brandEntity);
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        list.forEach(System.out::println);
    }

    @Test
    void testUpload() throws FileNotFoundException {

        List<Long> categoryPathById = categoryService.getCategoryPathById(225L);
        System.out.println(categoryPathById);
    }

}
