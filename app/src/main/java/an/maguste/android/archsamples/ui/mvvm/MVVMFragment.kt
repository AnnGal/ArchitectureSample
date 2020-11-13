package an.maguste.android.archsamples.ui.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import an.maguste.android.archsamples.R
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_mvvm.*

class MVVMFragment : Fragment() {

    private var mvvmViewModel = MVVMViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //mvvmViewModel = ViewModelProvider(this).get(MVVMViewModel::class.java)

        mvvmViewModel.state.observe( viewLifecycleOwner,
            Observer<ModelState> {state ->
                when (state) {
                    is ModelState.LoginSucessState -> {
                        //Toast.makeText()
                    }
                    is ModelState.SendingState -> {
                        //
                    }
                    is ModelState.ErrorState<*> -> {
                        when (state.message) {
                            is Int -> Toast.makeText(requireContext(), getString(state.message as Int), Toast.LENGTH_SHORT).show()
                            is String -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                        //
                    }
                    is ModelState.InitializeState -> {
                        //
                    }
                }
            })

        return inflater.inflate(R.layout.fragment_mvvm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         btnLogin.setOnClickListener {
             mvvmViewModel.login(textLogin.text.toString(), textPassword.text.toString())
             //Toast.makeText(requireContext(), "PressButton", Toast.LENGTH_SHORT).show()
         }
    }
}