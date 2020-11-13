package an.maguste.android.archsamples.ui.mvvm

import an.maguste.android.archsamples.R
import an.maguste.android.archsamples.models.AuthRepositoryImpl
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.fragment_mvc.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MVVMViewModel : ViewModel()/*, LifecycleObserver*/ {

    private val authRepository = AuthRepositoryImpl()
    var state = MutableLiveData<ModelState>().apply { ModelState.InitializeState() }

    fun login(email: String, password: String){
        if (!validateEmail(email)){
            state.apply { ModelState.ErrorState(R.string.wrong_email_format) }
            return
        }

        if (!validateEmail(password)){
            state.apply { ModelState.ErrorState(R.string.wrong_pass_format) }
            return
        }

        state.apply { ModelState.SendingState() }
        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = authRepository
                .login(email = email, password = password)
                .await()

            if (errorMessage.isEmpty()){
                launch(Dispatchers.Main){
                    state.apply { ModelState.LoginSucessState() }
                }
            } else {
                launch(Dispatchers.Main){
                    state.apply { ModelState.ErrorState(errorMessage) }
                }
            }
        }

    }

    // validation moves here
    private fun validateEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    private fun validatePass(password: String): Boolean {
        return password.length >= 8
    }

    /*private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text*/
}