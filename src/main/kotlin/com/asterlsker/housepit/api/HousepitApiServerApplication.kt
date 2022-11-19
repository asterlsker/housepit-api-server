package com.asterlsker.housepit.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(
    scanBasePackages = [
        "com.asterlsker.housepit"
    ]
)
@EnableJpaRepositories(basePackages = ["com.asterlsker.housepit"])
@EntityScan(basePackages = ["com.asterlsker.housepit"])
class HousepitApiServerApplication
fun main(args: Array<String>) {
    runApplication<HousepitApiServerApplication>(*args)
}
