package com.devapps.justclass.ui.composables

import androidx.activity.result.IntentSenderRequest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devapps.justclass.R
import com.devapps.justclass.ui.theme.peach
import kotlinx.coroutines.launch


@Composable
fun InfoCard(
    stat: String,
    title: String,
    color: Color
) {
    ElevatedCard(
        onClick = { null },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp,
            focusedElevation = 8.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = color
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = stat,
                fontSize = 36.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
                )
            Text(text = title,
                fontSize = 16.sp,
                color = Color.Black,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UtilityPreview() {
}