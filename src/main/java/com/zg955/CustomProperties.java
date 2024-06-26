package com.zg955;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
public class CustomProperties {

    private String applicationDesignation;

    private String dataPath;

    private String delimiter;

    private int chunkSize;
}
