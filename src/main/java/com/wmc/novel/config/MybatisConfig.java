package com.wmc.novel.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: MybatisConfig 
 * @Description: Mybatis配置类
 * @author money
 * @date 2020年11月17日
 */
@Component
@MapperScan(basePackages = {"com.wmc.novel.mbg.mapper", "com.wmc.novel.mapper"})
public class MybatisConfig {
}
