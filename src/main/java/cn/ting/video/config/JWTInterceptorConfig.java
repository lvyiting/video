package cn.ting.video.config;

import cn.ting.video.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JWTInterceptorConfig implements WebMvcConfigurer {
@Override
    public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new JWTInterceptor())
            .addPathPatterns("/user/**")
            .excludePathPatterns("/user/login");
}

}