package com.asterlsker.housepit.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(
    scanBasePackages = [
        "com.asterlsker.housepit"
    ]
)
@EnableJpaRepositories(basePackages = ["com.asterlsker.housepit.core"])
@EntityScan(basePackages = ["com.asterlsker.housepit.core"])
class HousepitApiServerApplication

fun main(args: Array<String>) {
    System.setProperty("spring.profiles.default", "local")
    runApplication<HousepitApiServerApplication>(*args)
}
