package an.maguste.android.archsamples.ui.mvc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import an.maguste.android.archsamples.R
import an.maguste.android.archsamples.models.AuthRepository
import an.maguste.android.archsamples.models.AuthRepositoryImpl
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_mvc.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MVCFragment : Fragment() {

    private val authRepository = AuthRepositoryImpl()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mvc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            if (!validateEmail()) return@setOnClickListener
            if (!validatePass()) return@setOnClickListener
            performLogin()
        }
    }

    private fun performLogin(){
        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = authRepository.login(email = textLogin.text.toString(),
                password = textPassword.text.toString()).await()

            if (errorMessage.isEmpty()){
                launch(Dispatchers.Main){
                    Toast.makeText(requireContext(), "Success login", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateEmail(): Boolean {
        return textLogin.text.toString().contains("@") && textLogin.text.toString().contains(".")
    }

    private fun validatePass(): Boolean {
        return textPassword.text.toString().length >= 8
    }
}