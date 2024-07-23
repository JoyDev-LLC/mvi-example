package com.example.mvvm_example.clean.presentation

import com.example.mvvm_example.R

enum class CharactersStatusEnum(val staus: String, val iconRes: Int?) {
    ALIVE("Alive", R.drawable.ic_circle_green),
    DEATH("Dead", R.drawable.ic_circle_red),
    UNKNOWN("unknown", null);

    companion object {
        fun String.getIconResByStatus(): Int? {
            return when(this) {
                ALIVE.staus -> ALIVE.iconRes
                DEATH.staus -> DEATH.iconRes
                else -> UNKNOWN.iconRes
            }
        }
    }
}