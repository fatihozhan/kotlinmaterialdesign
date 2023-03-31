package com.example.materialdesing

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.materialdesing.ui.theme.MaterialDesingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialDesingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
//                    SayfaTopBar()
//                    TopAppBarArama()
//                    SayfaCard()
//                    SayfaSabitListelemeColumn()
//                    SayfaSabitListelemeRow()
//                    DinamikListeleme()
                    SayfaGecisleri()


                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialDesingTheme {
        SayfaTopBar()
    }
}


@Composable
fun SayfaTopBar() {
    val menuIsOpen = remember { mutableStateOf(false) }
    Scaffold(topBar = {
        TopAppBar(title = {
            Column {
                Text(text = "Başlık")
                Text(text = "Alt Başlık", fontSize = 12.sp)

            }
        },
            backgroundColor = colorResource(id = R.color.anaRenk),
            contentColor = colorResource(id = R.color.white),
            actions = {
                Text(text = "Çıkış",
                    modifier = Modifier.clickable { Log.e("TopBar", "Çıkış Seçildi") })
                IconButton(onClick = { Log.e("TopBar", "İcona basıldı") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.info_resim),
                        contentDescription = ""
                    )
                }
                IconButton(onClick = { menuIsOpen.value = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.more_resim),
                        contentDescription = ""
                    )
                }
                DropdownMenu(expanded = menuIsOpen.value,
                    onDismissRequest = { menuIsOpen.value = false }) {
                    DropdownMenuItem(onClick = {
                        Log.e("Sil", "Silidni"); menuIsOpen.value = false
                    }) {
                        Text(text = "Sil")
                    }
                    DropdownMenuItem(onClick = {
                        Log.e(
                            "Güncelle", "Güncellendi"
                        ); menuIsOpen.value = false
                    }) {
                        Text(text = "Güncelle")
                    }

                }
            })
    }, content = { it -> it })
}


@Composable
fun TopAppBarArama() {
    val isSearching = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    Scaffold(topBar = {
        TopAppBar(title = {
            if (isSearching.value) {
                TextField(value = tf.value, onValueChange = { tf.value = it }, label = {
                    Text(
                        text = "Arama"
                    )
                }, colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    unfocusedIndicatorColor = Color.White

                )
                )
            } else {
                Text(text = "Başlık")

            }
        }, actions = {
            if (isSearching.value) {
                IconButton(onClick = {
                    Log.e("TopBar", "İcona basıldı"); isSearching.value = false; tf.value = ""
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.kapat_resim),
                        contentDescription = ""
                    )
                }
            } else {

                IconButton(onClick = {
                    Log.e("TopBar", "İcona basıldı"); isSearching.value = true
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arama_resim),
                        contentDescription = ""
                    )
                }
            }

        })
    }, content = { it -> it })
}

@Composable
fun SayfaCard() {

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Card(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            elevation = 10.dp,
            backgroundColor = Color.Blue,
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            border = BorderStroke(2.dp, Color.Magenta)
        ) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.clickable {
                Log.e("Kart", "Tıklandı")
            }) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(all = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gunes_resim),
                        contentDescription = ""
                    )
                    Text(text = "Güneş", color = Color.White, fontSize = 36.sp)
                }
            }
        }

    }
}

@Composable
fun SayfaSabitListelemeColumn() {
    LazyColumn {
        item {
            Card(
                modifier = Modifier
                    .padding(all = 5.dp)
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier.clickable { Log.e("Liste", " GÜneş Tıklandı") }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gunes_resim_24),
                            contentDescription = ""
                        )
                        Text(text = "Güneş", modifier = Modifier.padding(all = 5.dp))
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(all = 5.dp)
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier.clickable { Log.e("Liste", " Ay Tıklandı") }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ay_24),
                            contentDescription = ""
                        )
                        Text(text = "Güneş", modifier = Modifier.padding(all = 5.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SayfaSabitListelemeRow() {
    LazyRow {
        item {
            Card(
                modifier = Modifier
                    .padding(all = 5.dp)
                    .size(
                        100.dp
                    )
            ) {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { Log.e("Liste", " GÜneş Tıklandı") }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gunes_resim_24),
                            contentDescription = ""
                        )
                        Text(text = "Güneş", modifier = Modifier.padding(all = 5.dp))
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(all = 5.dp)
                    .size(
                        100.dp
                    )
            ) {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { Log.e("Liste", " Ay Tıklandı") }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(all = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ay_24),
                            contentDescription = ""
                        )
                        Text(text = "Güneş", modifier = Modifier.padding(all = 5.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun DinamikListeleme(navController: NavController) {
    val ulkeListesi = remember { mutableStateListOf("Türkiye", "Almanya", "İtalya", "Japonya") }
    LazyColumn {
        items(
            count = ulkeListesi.count(),
            itemContent = {
                val ulke = ulkeListesi[it]
                Card(
                    modifier = Modifier
                        .padding(all = 5.dp)
                        .fillMaxWidth()
                ) {
                    Row(modifier = Modifier.clickable {
                        Log.d("Column", "Tıklandııı.")
                        navController.navigate("detay_sayfa/$ulke")
                    }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = ulke, modifier = Modifier.padding(all = 5.dp))
                            OutlinedButton(onClick = { }) {
                                Text(text = "Ülke Seç")
                            }
                        }
                    }
                }
            }
        )
    }
}


@Composable
fun DinamikListelemeLazyRow(navController: NavController) {
    val ulkeListesi = remember { mutableStateListOf("Türkiye", "Almanya", "İtalya", "Japonya") }
    LazyRow {
        items(
            count = ulkeListesi.count(),
            itemContent = {
                val ulke = ulkeListesi[it]
                Card(
                    modifier = Modifier
                        .padding(all = 5.dp)
                        .fillMaxWidth()
                ) {
                    Row(modifier = Modifier.clickable {
                        Log.d("Column", "Tıklandııı.")
                        navController.navigate("detay_sayfa/$ulke")
                    }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(all = 10.dp).size(200.dp, 100.dp)
                        ) {
                            Text(text = ulke, modifier = Modifier.padding(all = 5.dp))
                            OutlinedButton(onClick = { }) {
                                Text(text = "Ülke Seç")
                            }
                        }
                    }
                }
            }
        )
    }
}
@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "sayfaRow") {
        composable("sayfa") {
            DinamikListeleme(navController = navController)
        }
        composable("sayfaRow") {
            DinamikListelemeLazyRow(navController = navController)
        }
        composable("detay_sayfa/{ulkeAdi}", arguments = listOf(
            navArgument("ulkeAdi") { type = NavType.StringType }
        )) {
            val ulkeAdi = it.arguments?.getString("ulkeAdi")!!
            DetaySayfa(ulkeAdi)
        }
    }
}