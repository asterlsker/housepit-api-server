package com.asterlsker.housepit.api.presentation.auth

import com.asterlsker.housepit.api.common.*
import com.asterlsker.housepit.api.presentation.auth.dto.LinkReq
import com.asterlsker.housepit.api.presentation.auth.dto.RefreshRes
import com.asterlsker.housepit.api.presentation.auth.dto.SignInReq
import com.asterlsker.housepit.api.presentation.common.config.StandaloneControllerConfig
import com.asterlsker.housepit.auth.domain.data.TokenData
import com.asterlsker.housepit.core.enums.OAuth2Provider
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


internal class AuthControllerTest() : StandaloneControllerConfig() {

    @Test
    fun signIn() {
        val body = SignInReq(provider = OAuth2Provider.GOOGLE, idToken = DUMMY_ID_TOKEN)
        val expectedResult = RefreshRes(accessToken = DUMMY_ACCESS_TOKEN, refreshToken = DUMMY_REFRESH_TOKEN)
        every { authFacade.signIn(any(), any()) } returns TokenData(DUMMY_ACCESS_TOKEN, DUMMY_REFRESH_TOKEN)

        mockMvc.perform(
            post(AUTH_SIGNIN)
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON)
        )
//            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.payload.access_token").value(expectedResult.accessToken))
            .andExpect(jsonPath("$.payload.refresh_token").value(expectedResult.refreshToken))
    }

    @Test
    fun link() {
        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $DUMMY_ACCESS_TOKEN")
        val body = LinkReq(provider = OAuth2Provider.GOOGLE, idToken = DUMMY_ID_TOKEN)
        every { authFacade.link(any(), OAuth2Provider.GOOGLE, any()) } returns Unit

        mockMvc.perform(
            post(AUTH_LINK)
                .headers(headers)
                .content(objectMapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON)
        )
//            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
    }

    @Test
    fun signOut() {
        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $DUMMY_ACCESS_TOKEN")

        mockMvc.perform(
            post(AUTH_SIGNOUT)
                .headers(headers)
        )
//            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
    }

    @Test
    fun refresh() {
        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $DUMMY_REFRESH_TOKEN")
        val expectedResult = RefreshRes(accessToken = DUMMY_ACCESS_TOKEN, refreshToken = DUMMY_REFRESH_TOKEN)
        every { authFacade.refresh(any()) } returns TokenData(
            accessToken = DUMMY_ACCESS_TOKEN,
            refreshToken = DUMMY_REFRESH_TOKEN
        )

        mockMvc.perform(
            post(AUTH_REFRESH)
                .headers(headers)
        )
//            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.payload.access_token").value(expectedResult.accessToken))
            .andExpect(jsonPath("$.payload.refresh_token").value(expectedResult.refreshToken))
    }
}