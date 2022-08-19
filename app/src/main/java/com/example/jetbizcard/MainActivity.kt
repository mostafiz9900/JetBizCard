package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun  CreateBizCard(){
    val buttonClickedState= remember {
        mutableStateOf(false )
    }
 Surface(modifier = Modifier
     .fillMaxWidth()
     .fillMaxHeight()) {

Card(modifier = Modifier
    .width(200.dp)
    .height(400.dp)
    .padding(10.dp), elevation = 5.dp,
    backgroundColor = Color.White,
    shape = RoundedCornerShape(corner = CornerSize(20.dp))
) {
    Column( modifier = Modifier.padding(10.dp),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
        ) {
        CreateProfileImage()
        Divider(modifier = Modifier.padding(top = 10.dp))
        CreateInfo()
       Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
//            Log.d("click button", "CreateBizCard: ")
            buttonClickedState.value=!buttonClickedState.value
        }) {

            Text(text = "Portfolio", style = MaterialTheme.typography.button)

        }
        if(buttonClickedState.value){
            Content()
        }else{
            Box{}
        }
    }



}
  }
}


@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)){
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
            border = BorderStroke(width = 2.dp, color=Color.LightGray)
        ) {
        Portfolio(data=listOf("Project 1","Project 2","Project 3"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data){item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(), shape = RectangleShape) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(color = MaterialTheme.colors.surface)
                    .padding(15.dp)) {
                    CreateProfileImage(modifier = Modifier.size(100.dp))
                    Column(
                        modifier = Modifier.padding(10.dp).align(alignment = Alignment.CenterVertically)

                    ) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "Get Project", style = MaterialTheme.typography.subtitle1)
                    }
                }
            }
            
        } 
    }


}


@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Mostafizur",
            Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primary


        )

        Text(text = "Mobile App Developer", modifier = Modifier.padding(3.dp), color = MaterialTheme.colors.primary, style = MaterialTheme.typography.h6)
        Text(
            text = "mostafiz9900@gmail.com",
            modifier = Modifier.padding(3.dp),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier=Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, color = Color.LightGray),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.profile_img
            ),
            contentDescription = "Profile Image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}