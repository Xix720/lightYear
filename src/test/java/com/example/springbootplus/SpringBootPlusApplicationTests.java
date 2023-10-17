package com.example.springbootplus;

import com.example.springbootplus.util.LocalDateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootPlusApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testDateTime(){
        System.out.println(LocalDateUtil.now());
    }

}
