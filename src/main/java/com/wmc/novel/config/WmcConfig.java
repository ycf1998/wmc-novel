package com.wmc.novel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wmc")
public class WmcConfig {

    /** 项目名称 */
    private String name;

    /** 项目url */
    private String basePath;

    /** 上传路径 */
    private String uploadPath;

}
