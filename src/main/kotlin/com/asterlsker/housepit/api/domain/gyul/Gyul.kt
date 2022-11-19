package com.asterlsker.housepit.api.domain.gyul

data class Gyul(
    val blocks: List<Block>,
    val timestamp: Long,
    var version: String
)
