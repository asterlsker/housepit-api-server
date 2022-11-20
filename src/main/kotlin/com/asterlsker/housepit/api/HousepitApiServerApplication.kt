package com.asterlsker.housepit.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.asterlsker.housepit"
    ]
)
class HousepitApiServerApplication
fun main(args: Array<String>) {
    runApplication<HousepitApiServerApplication>(*args)
}
