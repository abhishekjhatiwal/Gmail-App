package com.example.gmailapp.presentation.emaillist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.gmailapp.core.error.getErrorMessage
import com.example.gmailapp.core_ui.component.EmailItem
import com.example.gmailapp.core_ui.component.FullScreenError
import com.example.gmailapp.core_ui.component.LinearFullScreenProgress
import com.example.gmailapp.core_ui.funtational.toFormattedDate
import com.example.gmailapp.presentation.emaillist.mvi.EmailListContract
import com.example.gmailapp.ui.theme.Dimensions

@Composable
fun EmailListScreen(state: EmailListContract.EmailListState, dispatch: (EmailListContract.EmailListEvent) -> Unit) {
    when (state) {
        is EmailListContract.EmailListState.Error -> FullScreenError(
            errorMessage = state.error.getErrorMessage()
        )

        EmailListContract.EmailListState.Loading -> LinearFullScreenProgress(
            modifier = Modifier.semantics {
                contentDescription = "Loading"
            }
        )

        is EmailListContract.EmailListState.Success -> EmailListUi(state, dispatch)
    }
}

@Composable
fun EmailListUi(
    states: EmailListContract.EmailListState.Success,
    dispatch: (EmailListContract.EmailListEvent) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(Dimensions.dimen_16)) {
        items(
            states.emailList,
            key = { it.id }
        ) {
            EmailItem(
                modifier = Modifier
                    .padding(vertical = Dimensions.dimen_8)
                    .clickable {
                        dispatch.invoke(EmailListContract.EmailListEvent.EmailClicked(it))
                    },
                profileImageUrl = it.profileImage,
                senderName = it.from,
                emailSubject = it.subject,
                emailSnippet = it.snippet,
                isStarred = it.isStarred,
                date = it.date.toFormattedDate()
            )
        }
    }
}