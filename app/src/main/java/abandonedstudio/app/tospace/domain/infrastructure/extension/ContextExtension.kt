package abandonedstudio.app.tospace.domain.infrastructure.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(@StringRes textResId: Int) {
    showToast(getString(textResId))
}