package com.lucasgugliuzza.mdc_jc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lucasgugliuzza.mdc_jc.ui.theme.Content
import com.lucasgugliuzza.mdc_jc.ui.theme.MDC_JCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MDC_JCTheme {
               ContentMain()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentMain() {
    //REEMPLAZA A COORDINATOR LAYOUT
    Scaffold(modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            BottomAppBar(cutoutShape = CircleShape) {
                //LE DA UN MEJOR ASPECTO , SE USA PARA EL PRIMER ICONO
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu , contentDescription = "Este es el Menu")
                    }
                }
                //ME DEJA MOVERLO A LA IZQUIERDA
                Spacer(modifier = Modifier.weight(1f,true))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.MoreVert , contentDescription = "Opcions")
                }

            }
            //FLOATING ACTION BUTTON
        }, floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Create, contentDescription = null)
            }
            //EL FLOATING ACTION BUTON SE METE EN EL ACTION BAR
        }, floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true)
    { padding ->
        Content(Modifier.padding(padding))
    }
}