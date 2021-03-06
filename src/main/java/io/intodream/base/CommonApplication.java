package io.intodream.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 应用启动类
 * @author yangxianxi@gogpay.cn
 * @date 2018/5/15 17:49
 */
@SpringBootApplication
@ComponentScan("io.intodream")
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}
