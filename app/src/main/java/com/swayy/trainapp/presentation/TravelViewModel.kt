package com.swayy.trainapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.trainapp.domain.repository.TravelRepository
import com.swayy.trainapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TravelViewModel @Inject constructor(
    private val repository: TravelRepository
) : ViewModel() {
    private val _travel = mutableStateOf(TravelState(isLoading = true))
    val travel: State<TravelState> = _travel

    init {
        getTravel()
    }

    fun getTravel() {
        viewModelScope.launch {
            when (val result = repository.getTravels()) {
                is Resource.Error -> {
                    _travel.value = TravelState(isLoading = false, error = result.message)
                }

                is Resource.Success -> {
                    _travel.value =
                        TravelState(isLoading = false, travel = result.data ?: ArrayList())
                }

                else -> {
                    travel
                }
            }
        }
    }

}