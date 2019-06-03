package com.wt.wata;

import com.wt.wata.common.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WataApplicationTests {

    @Test
    public void contextLoads() {
        //测试非空验证
        String str = "字符串";
        int index = 1;
        Map<String,Object> map = null;
        List<String> list = new ArrayList<>();
        Object object = new Object();
        Set<String> set = new HashSet<>();

        System.out.println(CommonUtil.isNullPointer(str));
        System.out.println(CommonUtil.isNullPointer(index));
        System.out.println(CommonUtil.isNullPointer(map));
        System.out.println(CommonUtil.isNullPointer(list));
        System.out.println(CommonUtil.isNullPointer(object));
        System.out.println(CommonUtil.isNullPointer(set));
    }

}

