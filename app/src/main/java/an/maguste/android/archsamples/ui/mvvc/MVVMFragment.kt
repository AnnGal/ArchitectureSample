package an.maguste.android.archsamples.ui.mvvc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import an.maguste.android.archsamples.R

class MVVMFragment : Fragment() {

    private lateinit var mvvmViewModel: MVVMViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mvvmViewModel =
                ViewModelProvider(this).get(MVVMViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_mvvm, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        mvvmViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}