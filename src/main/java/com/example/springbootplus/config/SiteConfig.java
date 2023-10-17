package com.example.springbootplus.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:site.properties")
@Data
public class SiteConfig {
    @Value("${site.name}")
    private String siteName;
    @Value("${site.url}")
    private String siteUrl;
}
