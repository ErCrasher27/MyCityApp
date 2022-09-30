package com.example.mycityapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.local.LocalPlaceData.places
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Filter
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.data.model.Rate
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
            uiState.value.currentFilters.clear()
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

    fun updateCurrentLocation(location: Location?) {
        _uiState.update {
            it.copy(
                currentLocation = location
            )
        }
    }

    fun updateCurrentFiltersPlace(currentFiltersPlace: MutableList<Filter>) {
        _uiState.update {
            it.copy(
                currentFilters = currentFiltersPlace
            )
        }
    }

    fun updateExpandedFilters(expandedFilters: Boolean) {
        if (!expandedFilters) {
            uiState.value.currentFilters.clear()
        }
        _uiState.update {
            it.copy(
                expandedFilters = expandedFilters
            )
        }
    }

    fun updatePlacesFiltered(placesFiltered: List<Place>) {
        _uiState.update {
            it.copy(
                placesFiltered = placesFiltered
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
    fun displayDistance(
        context: Context,
        placeLocation: LatLng,
    ): String? {
        var fusedLocation = LocationServices.getFusedLocationProviderClient(context)
        fusedLocation.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    updateCurrentLocation(location)
                }
            }

        var result: Float?
        var formatResult: String? = null

        val placeLocationToLocationType = Location("Place")
        placeLocationToLocationType.latitude = placeLocation.latitude
        placeLocationToLocationType.longitude = placeLocation.longitude

        result = uiState.value.currentLocation?.distanceTo(placeLocationToLocationType)

        if (result != null) {
            formatResult = "%.1f".format(result / 1000)
        }

        return formatResult
    }

    fun filterPlace(context: Context) {
        var placesFilter = places
        placesFilter =
            placesFilter.filter { it.category.nameCategory.name == uiState.value.currentTab.name }

        uiState.value.currentFilters.forEach {
            if (it == Filter.Best) {
                placesFilter.filter {
                    it.ratingPlace == Rate.STAR4 || it.ratingPlace == Rate.STAR5
                }
            }

            if (it == Filter.Near) {
                placesFilter.filter {
                    displayDistance(
                        context = context,
                        placeLocation = it.latLng
                    )?.toFloat()!! < 10.0
                }
            }

            if (it == Filter.Day) {
                placesFilter.filter {
                    it.dayVisitable == true
                }
            }

            if (it == Filter.Night) {
                placesFilter.filter {
                    it.dayVisitable == true
                }
            }

        }
        updatePlacesFiltered(placesFilter)
    }
}

