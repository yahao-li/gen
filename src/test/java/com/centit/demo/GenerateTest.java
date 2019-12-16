package com.centit.demo;

import com.centit.demo.service.GenerateCodeService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author: jiayh
 * @Description:
 * @date: 2019/11/1
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GenerateTest {
    @Autowired
    private GenerateCodeService generateCodeService;
    /**
     * 数据库连接和生产的类的package可以在application.yml中修改
     */
    @Test
    public void mySqlGeneration() {
//      需要生成哪些文件
        List<String> types = Lists.newArrayList("CONTROLLER", "SERVICE", "MAPPER", "XML", "MODEL");
//      指定库名和类名
        ByteArrayOutputStream out = generateCodeService.mySqlGenerateCode(types, "db_test", "t_cw_ndtcf");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("C:\\Users\\li_yh\\Desktop\\code.zip");
            fileOutputStream.write(out.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}