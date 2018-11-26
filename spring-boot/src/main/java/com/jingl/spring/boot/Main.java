package com.jingl.spring.boot;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Date : 2018/8/10
 * @Author : 汪京陆(Ben)[wangjinglu@souche.com]
 * @CopyRight : DataTeam @ SouChe Inc
 * @Desc :
 */
@SpringBootApplication
@ComponentScan
@MapperScan("com.jingl.spring.boot.mapper")
public class Main implements CommandLineRunner {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println(System.getProperty("spring.datasource.driverClassName"));
        System.out.println("hello world");
    }
}
