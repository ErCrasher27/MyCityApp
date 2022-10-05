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
import com.example.mycityapp.data.remote.PostsService
import com.example.mycityapp.data.remote.dto.PostResponse
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

    fun updateSearchNamePlace(searchNamePlace: String) {
        _uiState.update {
            it.copy(
                searchPlaceName = searchNamePlace
            )
        }
    }

    fun updateWeather(postResponse: PostResponse) {
        _uiState.update {
            it.copy(
                weather = postResponse
            )
        }
    }

    /*  CallPlace will let the user call the place only if the place has a number and if the user
     *  has garanted the Phone Call permission, else the button will be disabled
    */
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
            }
        }
    }

    /*
     *    Function that let the user open the location of the place on the google maps app if they
     *    accept the permission of the Location
    */
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
        }
    }

    /*
     *    Function that gets the current location of the user if they accept the location and calculate
     *   the distance between the user location and the place location, and then save the result in
     *   the UiState variable [currentLocation]
    */
    @SuppressLint("MissingPermission")
    fun displayDistance(
        context: Context,
        placeLocation: LatLng,
    ): String? {
        val result: Float?
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
        val searchNamePlace = uiState.value.searchPlaceName
        var placesFilter = places

        placesFilter =
            placesFilter.filter { it.category.nameCategory.name == uiState.value.currentTab.name }

        if (searchNamePlace.isNotEmpty()) {
            placesFilter = filterPlacesByTitleOrDescription(context, placesFilter, searchNamePlace)
        }

        uiState.value.currentFilters.forEach {
            if (it == Filter.Best) {
                placesFilter = placesFilterByBestRating(placesFilter)
            }

            if (it == Filter.Near) {
                placesFilter = placesFilter.filter {
                    var distanceResult = displayDistance(
                        context = context,
                        placeLocation = it.latLng
                    )
                    val distanceResultToFloat = (distanceResult?.replace(",", "."))?.toFloatOrNull()
                    if (distanceResultToFloat == null) {
                        true
                    } else {
                        distanceResultToFloat < 10.0
                    }
                }
            }

            if (it == Filter.Day) {
                placesFilter = placesFilter.filter {
                    it.dayVisitable
                }
            }

            if (it == Filter.Night) {
                placesFilter = placesFilter.filter {
                    it.nightVisitable
                }
            }
        }
        updatePlacesFiltered(placesFilter)
    }

    /*  Function that filters a list of places by the user input in the textLabel, and returns
     *  a place only if the place title or description contains the input.
     */
    fun filterPlacesByTitleOrDescription(
        context: Context,
        places: List<Place>,
        searchNamePlace: String
    ): List<Place> {
        return places.filter {
            context.getString(it.name).contains(searchNamePlace, true)
                    || context.getString(it.descriptionPlace).contains(searchNamePlace, true)
        }
    }

    //Function that filters a list of places by its rating. True if greater of 4 or equal to 5 stars
    fun placesFilterByBestRating(placesFilter: List<Place>): List<Place> {
        return placesFilter.filter {
            it.ratingPlace == Rate.STAR4 || it.ratingPlace == Rate.STAR5
        }
    }

    //Function that gets the location of the user if they accept the Location Request
    @SuppressLint("MissingPermission")
    fun getCurrentLocation(context: Context) {
        val fusedLocation = LocationServices.getFusedLocationProviderClient(context)
        fusedLocation.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    updateCurrentLocation(location)
                }
            }
    }

    //Function to create a call to the weather API and save the information to the UiState
    suspend fun callWeatherApi() {
        val serviceWeather = PostsService.create()
        serviceWeather.getWeather(uiState.value.currentLocation)?.let { updateWeather(it) }
    }


}

