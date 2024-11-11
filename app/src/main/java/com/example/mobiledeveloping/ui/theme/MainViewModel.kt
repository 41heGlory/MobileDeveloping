import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val isCalendarClicked = mutableStateOf(false)
    val isHomeClicked = mutableStateOf(true)
    val isProfileClicked = mutableStateOf(false)

    fun onCalendarClick() {
        isCalendarClicked.value = true
        isHomeClicked.value = false
        isProfileClicked.value = false
    }

    fun onHomeClick() {
        isCalendarClicked.value = false
        isHomeClicked.value = true
        isProfileClicked.value = false
    }

    fun onProfileClick() {
        isCalendarClicked.value = false
        isHomeClicked.value = false
        isProfileClicked.value = true
    }
}

