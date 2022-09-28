package com.example.mycityapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Place
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    fun updateCurrentCategory(category: CategoryName) {
        if (category == CategoryName.Homepage) {
            updateIsInHomepage(true)
        } else {
            updateIsInHomepage(false)
        }
        _uiState.update {
            it.copy(
                currentTab = category,
            )
        }
    }

    fun updateCurrentDetails(place: Place?) {
        _uiState.update {
            it.copy(
                currentDetails = place
            )
        }
    }

    fun updateIsInHomepage(isInHomePage: Boolean) {
        _uiState.update {
            it.copy(
                isInHomePage = isInHomePage
            )
        }
    }

    fun callPlace(context: Context, phoneNumber: String?) {
        if (phoneNumber != null) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel: $phoneNumber")
                startActivity(context, intent, null)
            } else {
            }
        }
    }

    fun navigateTo(context: Context, latLng: LatLng, title: String) {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val gmmIntentUri =
                Uri.parse("geo:0,0?q=${latLng.latitude},${latLng.longitude}(${title})")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(context, mapIntent, null)
        } else {
        }
    }

    @SuppressLint("MissingPermission")
    fun displayDistance(placeLocation: LatLng, context: Context): String? {
        var result: Float
        var formatResult: String? = null
        //Log.d("ci", formatResult.toString())
        var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val placeLocationToLocationType = Location("Place")
                    placeLocationToLocationType.latitude = placeLocation.latitude
                    placeLocationToLocationType.longitude = placeLocation.longitude
                    result = location.distanceTo(placeLocationToLocationType) / 1000
                    formatResult = "%.1f".format(result) + " km"
                    Log.d("ci", formatResult.toString())
                    //Log.d("ci", result.toString())
                }
            }
        //Log.d("ci", formatResult.toString())
        return formatResult
    }
}

