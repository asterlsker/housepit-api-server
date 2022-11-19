package com.asterlsker.housepit.api.common.config

import com.asterlsker.housepit.api.presentation.common.interceptor.AuthInterceptor
import com.asterlsker.housepit.core.converter.GenericEnumConverter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(
    private val authInterceptor: AuthInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
            .excludePathPatterns("/")
            .excludePathPatterns("/auth/**")
            .excludePathPatterns("/h2", "/h2/**")
    }

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(GenericEnumConverter)
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>?>) {
        val converter = MappingJackson2HttpMessageConverter()
        val objectMapper = ObjectMapper()
        objectMapper
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
        converter.objectMapper = objectMapper
        converters.add(converter)
        super.configureMessageConverters(converters)
    }
}