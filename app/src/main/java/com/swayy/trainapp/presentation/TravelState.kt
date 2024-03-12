package com.swayy.trainapp.presentation

import com.swayy.trainapp.domain.model.Travel

data class TravelState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val travel: ArrayList<Travel> = ArrayList()
)
