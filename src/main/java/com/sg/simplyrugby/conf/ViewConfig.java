package com.sg.simplyrugby.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author geng
 * @since 2024/12/20 15:37:52
 */
@Configuration
public class ViewConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/player/view").setViewName("player/list");
        registry.addViewController("/players/add/view").setViewName("player/add");
    }
}
