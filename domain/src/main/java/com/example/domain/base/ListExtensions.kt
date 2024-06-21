package com.example.domain.base


inline fun List<Any>.whenEmpty(block: Action) {
    if (this.isEmpty()) block.invoke()
}