package com.example.mycityapp.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.R
import com.example.mycityapp.data.model.Filter

@Composable
fun HeaderPlacesLists(
    currentFiltersPlace: MutableList<Filter>,
    onClickFilter: (MutableList<Filter>) -> Unit,
    expandedFilters: Boolean,
    onExpandFilters: (Boolean) -> Unit, modifier: Modifier = Modifier,

    ) {
    Column(
        Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SearchPlace()
            Spacer(modifier = Modifier.weight(1f))
            FilterPlace(
                currentFiltersPlace = currentFiltersPlace,
                onClickFilter = onClickFilter,
                expandedFilters = expandedFilters,
                onExpandFilters = onExpandFilters
            )
        }
    }
    if (expandedFilters) {
        FilterList(currentFiltersPlace = currentFiltersPlace, onClickFilter = onClickFilter)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPlace(
    modifier: Modifier = Modifier,
) {
    var textState by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        value = textState,
        onValueChange = { textState = it },
        placeholder = {
            Row {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search)
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = "Enter name place"
                )
            }
        },
        trailingIcon = {
            if (textState.isNotEmpty()) {
                IconButton(onClick = { textState = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Delete Search"
                    )
                }
            }
        })
}

@Composable
fun FilterPlace(
    modifier: Modifier = Modifier,
    currentFiltersPlace: MutableList<Filter>,
    onClickFilter: (MutableList<Filter>) -> Unit,
    onExpandFilters: (Boolean) -> Unit,
    expandedFilters: Boolean,
) {
    var iconArrow = if (!expandedFilters) Icons.Default.FilterList else Icons.Default.FilterListOff
    IconButton(
        onClick = { onExpandFilters(!expandedFilters) }
    ) {
        Icon(
            imageVector = iconArrow,
            contentDescription = stringResource(id = R.string.filter),
            tint = MaterialTheme.colorScheme.secondary
        )
    }

}

@Composable
fun FilterList(
    modifier: Modifier = Modifier,
    currentFiltersPlace: MutableList<Filter>,
    onClickFilter: (MutableList<Filter>) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            FilterItem(
                title = "best rating (4-5)",
                icon = Icons.Default.Stars,
                currentFiltersPlace = currentFiltersPlace,
                onClickFilter = onClickFilter,
                filter = Filter.Best
            )
            FilterItem(
                title = "near you (<10km)",
                icon = Icons.Default.NearMe,
                currentFiltersPlace = currentFiltersPlace,
                onClickFilter = onClickFilter,
                filter = Filter.Near
            )
            FilterItem(
                icon = Icons.Default.WbSunny,
                currentFiltersPlace = currentFiltersPlace,
                onClickFilter = onClickFilter,
                filter = Filter.Day
            )
            FilterItem(
                icon = Icons.Default.Nightlight,
                currentFiltersPlace = currentFiltersPlace,
                onClickFilter = onClickFilter,
                filter = Filter.Night

            )
        }
    }
}


@Composable
fun FilterItem(
    title: String? = null,
    icon: ImageVector,
    currentFiltersPlace: MutableList<Filter>,
    onClickFilter: (MutableList<Filter>) -> Unit,
    filter: Filter
) {
    var selected by rememberSaveable { mutableStateOf(false) }
    var buttonColor =
        if (selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondaryContainer

    OutlinedButton(
        colors = ButtonDefaults.buttonColors(buttonColor),
        border = BorderStroke(0.dp, Color.Transparent),
        onClick = {
            if (selected) {
                currentFiltersPlace.remove(filter)
                onClickFilter(currentFiltersPlace)
                Log.d("prova", currentFiltersPlace.toString())
                selected = false
            } else {
                currentFiltersPlace.add(filter)
                onClickFilter(currentFiltersPlace)
                Log.d("prova", currentFiltersPlace.toString())
                selected = true
            }
        }) {
        if (title != null) {
            Text(text = title)
            Spacer(modifier = Modifier.width(4.dp))
        }
        Icon(imageVector = icon, contentDescription = null)
    }
}