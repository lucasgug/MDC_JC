package com.lucasgugliuzza.mdc_jc.ui.theme

import android.widget.Switch
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
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


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun Content(modifier: Modifier = Modifier){
    Column(modifier = modifier
            //HAGO UN SCROLL VERTICAL
        .verticalScroll(rememberScrollState())) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)){
            //AGREGO UN CONSTRAINLAOUT
            ConstraintLayout(modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default),
                    top = dimensionResource(id = R.dimen.common_padding_default))) {

                //HACEMOS REFERENCIAS A LOS ID
                val(imgCard, btnBuy,btnSkip,tvTitle,tvContent) = createRefs()


                var image = ContextCompat.getDrawable(LocalContext.current,R.mipmap.ic_launcher)
                Image(bitmap = image!!.toBitmap().asImageBitmap()
                    , contentDescription = null,
                    modifier = Modifier //Le doy su ID y lo ubico con las constrains
                        .constrainAs(imgCard){
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        })
                
                    Button(onClick = { },
                        modifier = Modifier
                            .constrainAs(btnBuy){
                                end.linkTo(parent.end)
                                top.linkTo(imgCard.bottom)
                            }) {
                        Icon(painter = painterResource(id = R.drawable.ic_shop), contentDescription = null)
                        Text(text = stringResource(id = R.string.card_btn_buy))

                    }
                    TextButton(onClick = { },
                        modifier = Modifier
                            .constrainAs(btnSkip){
                                end.linkTo(btnBuy.start)
                                top.linkTo(btnBuy.top)
                                bottom.linkTo(btnBuy.bottom)
                            }) {
                        Text(text = stringResource(id = R.string.card_btn_skip))
                    }

                Text(text = stringResource(id = R.string.card_title),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.common_padding_default))
                        .constrainAs(tvTitle) {
                            end.linkTo(parent.end)
                            start.linkTo(imgCard.end)
                            top.linkTo(parent.top)
                            width = Dimension.fillToConstraints //LE DECIMOS QUE ABARQUE FULL ANCHO POSIBLE
                        })


                Text(text = stringResource(id = R.string.card_texto_large),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    maxLines = 3, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .constrainAs(tvContent){
                            start.linkTo(tvTitle.start)
                            top.linkTo(tvTitle.bottom)
                            end.linkTo(tvTitle.end)
                            bottom.linkTo(imgCard.bottom)
                            width = Dimension.fillToConstraints //LE DECIMOS QUE ABARQUE FULL ANCHO POSIBLE
                        })

            }
        }


        var colorMain by remember { mutableStateOf(Color.DarkGray) }
        Card(modifier = Modifier
            .background(colorMain)
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
                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Checkbox(checked = false,
//                        onCheckedChange = { true })
//                    Text(text = stringResource(id = R.string.password_enable))

                    //CON EL SPACER LO MUEVO A LA IZQUIERDA
                    Spacer(modifier = Modifier.weight(1f,true))
                    Text(text = stringResource(id = R.string.fab))
                    var isSwitchChecked by remember { mutableStateOf(true) }

                    Switch(checked = isSwitchChecked ,
                        onCheckedChange = {isSwitchChecked = it})

                }
                //SLIDER
                var sliderValue by remember { mutableStateOf(5f) }
                //CONTEXT
                var content = LocalContext.current
                Slider(value = sliderValue,
                    onValueChange = {
                        sliderValue = it
                        urlValue = "Volumen es ${sliderValue.toInt()}" //SE MUESTRA EN EL TEXFIELD
                    },
                    //MUESTRA EL VALOR DEL SLIDER EN UN TOAST CUANDO EL USUARIO DEJA DE MOVERLO
                    onValueChangeFinished = {
                        Toast.makeText(content,"Vol:${sliderValue}",Toast.LENGTH_SHORT).show()
                    },
                    valueRange = 0f..10f ,
                    steps = 4 )

                val emailValue by remember { mutableStateOf("lucas@hotmail.com") }
                val chipVisible by remember { mutableStateOf(true) }

                if (chipVisible){
                    Chip(onClick = { Toast.makeText(content, emailValue,Toast.LENGTH_SHORT).show()},
                         modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.common_padding_default))) {
                        Text(text = emailValue)
                        Icon(imageVector = Icons.Filled.Close, contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                                .clickable { chipVisible == false })
                    }
                }

                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp))

                var colors= listOf("Red","Blue","Green")
                SegmentedControl(items = colors,
                    defaultSelectedItemIndex = -1, //PARA QUE NO ESTE SELECCIONADO NIGUNO
                    cornerRadius = 48,
                    color = R.color.purple_500,
                    onItemSelection = {
                        colorMain = when(it){
                            0 -> Color.Red
                            1 -> Color.Blue
                            else -> Color.Green
                        }
                    })

            }
        }

    }
}




