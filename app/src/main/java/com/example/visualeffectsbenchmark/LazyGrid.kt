package com.example.visualeffectsbenchmark

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LazyGrid(numOfFrames: Int = Int.MAX_VALUE) {
    val itemCount = 12000
    val entries = remember {List(itemCount) { Entry("$it") }}
    val state = rememberLazyGridState()

    MaterialTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "IamLazy" },
            state = state
        ) {
            items(entries) {
                ListCell(it)
            }
        }
    }

    var curItem by remember { mutableStateOf(0) }
    var direct by remember { mutableStateOf(true) }

    var curFrame by remember { mutableStateOf(0) }

    LaunchedEffect(curItem) {
        if (curFrame >= numOfFrames ) {
           return@LaunchedEffect
        }
        withFrameMillis {
           curFrame++
        }
        curItem += if (direct) 50 else -50
        if (curItem >= itemCount) {
            direct = false
            curItem = itemCount - 1
        } else if (curItem <= 0) {
            direct = true
            curItem = 0
        }
        state.scrollToItem(curItem)
    }
}

data class Entry(val contents: String)

@Composable
private fun ListCell(entry: Entry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = entry.contents,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
    }
}