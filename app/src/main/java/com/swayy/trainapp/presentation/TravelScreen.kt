package com.swayy.trainapp.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swayy.trainapp.domain.model.Station
import com.swayy.trainapp.domain.model.Travel

@Composable
fun TravelScreen(
    viewModel: TravelViewModel = hiltViewModel(),
    stationViewModel: StationViewModel = hiltViewModel()
) {

    val state = viewModel.travel.value
    val stationState = stationViewModel.station.value

    val travel = state.travel

    Log.e("test", travel.size.toString())

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "SGR Train System",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 13.dp, top = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
            LazyColumn(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                items(state.travel) {
                    Text(
                        text = "Passenger Name : " + it.passengerName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 10.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Start Station " + it.startStation.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 10.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Exit Station " + it.exitStation.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 13.dp, top = 10.dp, bottom = 10.dp),
                        textAlign = TextAlign.Center
                    )

                }

            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Output : " + calculatePassengerCount(
                    state.travel, stationState.station
                ).joinToString(","),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 13.dp, top = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
            Text(
                text = stationState.station.size.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 13.dp, top = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }

    }
}

fun calculatePassengerCount(travelList: ArrayList<Travel>, station: ArrayList<Station>): IntArray {
    val size = station.size

    val stationCount = 6
    val passengerCount = IntArray(stationCount)

    for (travel in travelList) {
        passengerCount[travel.startStation]++
        passengerCount[travel.exitStation]--
    }

    for (i in 1 until stationCount) {
        passengerCount[i] += passengerCount[i - 1]
    }

    return passengerCount
}