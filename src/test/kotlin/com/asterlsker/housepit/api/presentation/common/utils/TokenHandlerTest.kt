package com.asterlsker.housepit.api.presentation.common.utils

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith

const val token: String = "Bearer DUMMY_TOKEN"

@ExtendWith(MockKExtension::class)
internal class TokenHandlerTest : FunSpec({
    test("get string token") {
        TokenHandler.getToken(token) shouldBe "DUMMY_TOKEN"
    }
})
