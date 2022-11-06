package com.asterlsker.housepit.api.common.config

import com.asterlsker.housepit.api.presentation.common.interceptor.AuthInterceptor
import com.asterlsker.housepit.core.converter.GenericEnumConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(
    private val authInterceptor: AuthInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
            .excludePathPatterns("/auth/**")
            .excludePathPatterns("/h2")
    }

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(GenericEnumConverter)
    }
}