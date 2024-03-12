package com.swayy.trainapp.presentation

import com.swayy.trainapp.domain.model.Station
import com.swayy.trainapp.domain.model.Travel

data class StationState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val station: ArrayList<Station> = ArrayList()
)
