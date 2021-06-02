package br.com.rafaelbarbosa.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rafaelbarbosa.R

class RegisterBillsFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterBillsFragment()
    }

    private lateinit var viewModel: RegisterBillsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_bills_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterBillsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}