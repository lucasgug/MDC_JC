package com.lucasgugliuzza.mdc_jc.ui.theme

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lucasgugliuzza.mdc_jc.R

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun ContentPreview(){
    MDC_JCTheme {
        Content()
    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Content(modifier: Modifier = Modifier){
    Column(modifier = modifier
            //HAGO UN SCROLL VERTICAL
        .verticalScroll(rememberScrollState())) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Column {
               //CARGO IMAGEN
//                Image(painter = painterResource(id = R.drawable.ic_shop),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(dimensionResource(id = R.dimen.card_img_cover_height))
//                        .background(colorResource(id = R.color.teal_200)))

                //ESTADO PARA QUE PODAMOS ESCRIBIR Y ACTUALIZAR EL VALOR
                var urlValue by remember { mutableStateOf("") }

                //CARGO IMAGEN CON GLIDE
                GlideImage(model = urlValue ,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.card_img_cover_height))
                        .background(colorResource(id = R.color.teal_200)),
                    contentScale = ContentScale.Crop)


                //CARGO EN TEXTO
                Text(text = stringResource(id = R.string.card_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.common_padding_default)),
                    style = MaterialTheme.typography.h5)


                //TEXTFIELD para ingresar url
                OutlinedTextField(value = urlValue ,
                    onValueChange = { urlValue = it }, label = { Text(text = stringResource(id = R.string.input_url))},
                    singleLine = true, 
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.common_padding_default),
                            start = dimensionResource(id = R.dimen.common_padding_default),
                            end = dimensionResource(id = R.dimen.common_padding_default)
                        ), trailingIcon = { //Agrego un Icono que al seleccionarlo limpio el texfield
                            if (urlValue.isNotEmpty()){
                                Icon(imageVector = Icons.Filled.Clear,
                                    contentDescription = "Limpiar",
                                    modifier= Modifier
                                        .clickable { urlValue = "" })
                            }

                    })


                //Ponemos Texto requerido
                Text(text = stringResource(id = R.string.card_required),
                    style =  MaterialTheme.typography.caption ,
                    color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.common_padding_default),
                        top = dimensionResource(id = R.dimen.common_padding_min)))

                

                //ESTADO DEL PASSWORD
                var passwordValue by remember { mutableStateOf("") }

                var isPasswordVisibility by remember { mutableStateOf(false) }

                //TEXTFIELD de password
                OutlinedTextField(value = passwordValue ,
                    onValueChange = { passwordValue = it },
                    label = { Text(text = stringResource(id = R.string.password))},
                    singleLine = true ,
                    visualTransformation = if (isPasswordVisibility) VisualTransformation.None
                            else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.common_padding_default),
                            start = dimensionResource(id = R.dimen.common_padding_default),
                            end = dimensionResource(id = R.dimen.common_padding_default)
                        ),
                    trailingIcon = { //PUEDO OCULTAR EL PASSWORD
                            Icon( painter = if (isPasswordVisibility)
                                painterResource(id = R.drawable.ic_visibility_off)
                                else painterResource(id = R.drawable.ic_visibility ),
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable { isPasswordVisibility != isPasswordVisibility })

                    })

//                  AGREGO CHECKBOX
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Checkbox(checked = false,
//                        onCheckedChange = { true })
//                    Text(text = stringResource(id = R.string.password_enable))
//                }
            
            }
        }

    }
}




