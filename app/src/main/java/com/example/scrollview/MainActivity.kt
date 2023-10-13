package com.example.scrollview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.scrollview.ui.theme.ScrollViewTheme
import com.example.scrollview.ui.theme.Anime
import com.example.scrollview.ui.theme.DataSource

//Menerapkan override pada fungsi onCreate
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollViewTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimeGrid(
                        modifier = Modifier.padding(dimensionResource(androidx.core.R.dimen.notification_main_column_padding_top))
                    )
                }
            }
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi AnimeGrid yaitu umtuk mengatur grid
@Composable
fun AnimeGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid( //Untuk membuat grid dengan list item yang dapat discroll secara vertikal
        columns = GridCells.Fixed(2), //Untuk menentukan jumlah kolom yang akan ditampilkan di list grid
        verticalArrangement = Arrangement.spacedBy(dimensionResource(androidx.core.R.dimen.notification_main_column_padding_top)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(androidx.core.R.dimen.compat_button_padding_horizontal_material)),
        modifier = modifier
    ) {
        items(DataSource.animes) { anime ->
            AnimeCard(anime)
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi AnimeCard yaitu membuat tampilan seperti card yang menampilkan informasi tentang populasi hewan
@Composable
fun AnimeCard(anime: Anime, modifier: Modifier = Modifier) {
    Card {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            Image( //Untuk menampilkan gambar topik
                painter = painterResource(id = anime.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    top = dimensionResource(androidx.core.R.dimen.notification_main_column_padding_top),
                    bottom = dimensionResource(androidx.core.R.dimen.compat_button_padding_vertical_material)
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally //Text dimasukkan untuk mengisi keterangan gambar
        ) {
            Text(
                text = stringResource(id = anime.name),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.anime),
                    contentDescription = null,
                    modifier = Modifier.padding(start = dimensionResource(androidx.core.R.dimen.notification_main_column_padding_top))
                )
                Text(
                    text = anime.availableCourses.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(start = dimensionResource(androidx.core.R.dimen.notification_right_side_padding_top))
                )
            }
        }
    }
}

//Fungsi di bawah ini adalah komponen Composable yang digunakan untuk menampilkan preview atau pratinjau dari AnimalPopulation
@Preview(showBackground = true)
@Composable
fun AnimePreview() {
    ScrollViewTheme {
        val anime = Anime(R.string.onepiece, 5500, R.drawable.onepiece)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimeCard(anime = anime)
        }
    }
}
