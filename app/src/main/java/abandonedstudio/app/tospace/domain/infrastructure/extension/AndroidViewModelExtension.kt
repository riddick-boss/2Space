package abandonedstudio.app.tospace.domain.infrastructure.extension

import abandonedstudio.app.tospace.ToSpaceApplication
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel

fun AndroidViewModel.showToast(text: String) {
    getApplication<ToSpaceApplication>().showToast(text)
}

fun AndroidViewModel.showToast(@StringRes textResId: Int) {
    getApplication<ToSpaceApplication>().showToast(textResId)
}