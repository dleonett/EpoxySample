package com.leonett.photofeed.ui.compose.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leonett.photofeed.data.mapper.Action
import com.leonett.photofeed.data.mapper.Contact
import com.leonett.photofeed.data.mapper.Title

@Composable
fun RecentContacts(
    contacts: List<Contact>,
    recentContactsTitle: Title,
    viewAllContactsTitle: Title? = null,
    onActionClick: ((action: Action) -> Unit)? = null
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(PaddingValues(horizontal = 16.dp))
        ) {
            Text(
                text = recentContactsTitle.text,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .weight(1f)
                    .padding(PaddingValues(vertical = 12.dp))
            )

            viewAllContactsTitle?.let {
                TextButton(onClick = {
                    it.action?.let {
                        onActionClick?.invoke(it)
                    }
                }) {
                    Text(text = it.text)
                }
            }
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(contacts) { contact ->
                Contact(contact = contact, onActionClick = onActionClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecentContacts() {
    RecentContacts(Contact.generateDummyList(), Title("Recientes"), Title("Ver agenda"))
}