package com.asterlsker.housepit.api.common.utils

import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class ProfileHandler(
    private val env: Environment
) {
    fun acceptable(vararg profiles: String): Boolean {
        for (profile in profiles) {
            if(env.defaultProfiles.contains(profile)) return true
        }
        return false
    }
}