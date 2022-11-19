package com.asterlsker.housepit.api.domain.gyul

data class Header(
    val text: String,
    val color: String?,
    val level: Int
) : BlockData(BlockType.HEADER)