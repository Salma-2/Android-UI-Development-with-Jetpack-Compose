package com.salma.chapter_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hello()
        }
    }
}

@Composable
fun Hello() {
    val name = remember {
        mutableStateOf("")
    }
    val nameEntered = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center

    ) {
        if (nameEntered.value) {
            Greeting(name = name.value)
        } else {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Welcome()
                TextAndButton(name = name, nameEntered = nameEntered)
            }
        }
    }

}

@Composable
@Preview
fun Welcome() {
    Text(
        text = stringResource(id = R.string.welcome),
        style = MaterialTheme.typography.subtitle1,
    )
}


@Composable
fun TextAndButton(name: MutableState<String>, nameEntered: MutableState<Boolean>) {

    Row(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            placeholder = {
                Text(text = stringResource(id = R.string.hint))
            },
            singleLine = true,
            modifier = Modifier
                .alignByBaseline()
                .weight(1.0F),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    nameEntered.value = true
                }
            )
        )

        Button(
            onClick = { nameEntered.value = true },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = stringResource(id = R.string.done))
        }
    }
}

//@Composable
//@Preview
//fun GreetingWrapper(){
//    Greeting(name = "static name")
//}


@Composable
@Preview
fun Greeting(name: String= "no one") {
    Text(
        text = stringResource(id = R.string.greeting, name),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle1
    )
}