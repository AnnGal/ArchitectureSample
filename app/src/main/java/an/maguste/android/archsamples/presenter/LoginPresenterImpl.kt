package an.maguste.android.archsamples.presenter

import an.maguste.android.archsamples.R
import an.maguste.android.archsamples.models.AuthRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class LoginPresenterImpl: LoginPresenter {

    private val authRepository = AuthRepositoryImpl()
    private var viewState: WeakReference<LoginView>? = null

    fun attachView(view: LoginView){
        viewState = WeakReference(view)
    }

    override fun login(email: String, password: String) {
        if (!validateEmail(email = email)) {
            viewState?.get()?.showValidateError(R.string.wrong_email_format)
            return
        }
        if (!validatePass(password = password)) {
            viewState?.get()?.showValidateError(R.string.wrong_pass_format)
            return
        }


        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = authRepository
                .login(email = email, password = password)
                .await()

            if (errorMessage.isEmpty()){
                launch(Dispatchers.Main){
                    viewState?.get()?.showSuccess()
                }
            } else {
                launch(Dispatchers.Main){
                    viewState?.get()?.showError(message = errorMessage)
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

}