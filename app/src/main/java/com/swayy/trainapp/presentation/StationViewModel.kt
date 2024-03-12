package com.swayy.trainapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.trainapp.domain.repository.StationRepository
import com.swayy.trainapp.domain.repository.TravelRepository
import com.swayy.trainapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationViewModel @Inject constructor(
    private val repository: StationRepository
) :ViewModel(){
    private val _station = mutableStateOf(StationState(isLoading = true))
    val station: State<StationState> = _station

    init {
        getStation()
    }

    fun getStation() {
        viewModelScope.launch {
            when (val result = repository.getStations()) {
                is Resource.Error -> {
                    _station.value = StationState(isLoading = false, error = result.message)
                }

                is Resource.Success -> {
                    _station.value = StationState(isLoading = false, station = result.data ?: ArrayList())
                }

                else -> {
                    station
                }
            }
        }
    }

}