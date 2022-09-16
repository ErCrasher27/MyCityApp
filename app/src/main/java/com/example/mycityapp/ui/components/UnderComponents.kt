package com.example.mycityapp.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.data.model.Rate

@Composable
fun TitleAndDescriptionPlace(place: Place, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(place.name),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "\"" + stringResource(place.descriptionPlace) + "\"",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun LocationPlace(
    @StringRes location: Int, horizontalArrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = stringResource(location),
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(location),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun PhonePlace(
    @StringRes numberPhone: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = stringResource(id = numberPhone)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(numberPhone),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun StarsPlace(
    star: Rate,
    horizontalArrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        val repeater: Int
        when (star) {
            Rate.STAR1 -> repeater = 1
            Rate.STAR2 -> repeater = 2
            Rate.STAR3 -> repeater = 3
            Rate.STAR4 -> repeater = 4
            Rate.STAR5 -> repeater = 5
        }
        repeat(repeater) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = star.name
            )
        }
    }
}

@Composable
fun ImagePlace(
    @DrawableRes imagePlace: Int,
    @StringRes namePlace: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = imagePlace),
            contentDescription = stringResource(namePlace),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}