package an.maguste.android.archsamples.presenter

interface LoginPresenter {
    fun login(email: String, password: String)
}

interface LoginView {
    fun showSuccess()
    fun showError(message: String)
    fun showValidateError(message: Int)
}