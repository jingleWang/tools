package com.jingl.spring.boot.service;

import com.jingl.spring.boot.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @Date : 2018/8/10
 * @Author : 汪京陆(Ben)[wangjinglu@souche.com]
 * @CopyRight : DataTeam @ SouChe Inc
 * @Desc :
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    @PostConstruct
    public void test() {
        List list = testMapper.daoTest();
        System.out.println(list.get(0));
    }
}
