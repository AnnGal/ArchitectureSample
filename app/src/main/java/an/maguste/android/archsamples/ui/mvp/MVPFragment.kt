package an.maguste.android.archsamples.ui.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import an.maguste.android.archsamples.R
import an.maguste.android.archsamples.ui.mvp.presenter.LoginPresenterImpl
import an.maguste.android.archsamples.ui.mvp.presenter.LoginView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_mvc.*

class MVPFragment : Fragment(), LoginView {

    private val loginPresenter = LoginPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPresenter.attachView(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mvp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            loginPresenter.login(textLogin.text.toString(),
                textPassword.text.toString())
        }
    }

    override fun showSuccess() {
        Toast.makeText(requireContext(), "Success login", Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showValidateError(message: Int) {
        Toast.makeText(requireContext(), resources.getString(message), Toast.LENGTH_SHORT).show()
    }
}