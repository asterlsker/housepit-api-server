package com.asterlsker.housepit.api.presentation.common.config

import com.asterlsker.housepit.api.application.auth.AuthFacade
import com.asterlsker.housepit.api.presentation.auth.AuthController
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockKExtension::class)
@AutoConfigureMockMvc
@SpringBootTest
@ComponentScan(basePackages = ["com.asterlsker.housepit"])
abstract class StandaloneControllerConfig {
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    // presentation layer
    @InjectMockKs
    lateinit var authController: AuthController

    // application layer
    @MockK
    lateinit var authFacade: AuthFacade

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @BeforeEach
    fun setUp() {
        // mockMvc
        val jacksonConverter = MappingJackson2HttpMessageConverter()
        jacksonConverter.objectMapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE

        mockMvc = MockMvcBuilders
            .standaloneSetup(authController)
            .setMessageConverters(jacksonConverter)
            .build()
    }
}