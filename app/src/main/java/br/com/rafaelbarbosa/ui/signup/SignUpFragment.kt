package br.com.rafaelbarbosa.ui.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.rafaelbarbosa.R
import br.com.rafaelbarbosa.domain.service.FirebaseAuthService
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var firebaseAuthService: FirebaseAuthService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.sign_up_fragment, container, false)

        firebaseAuthService = FirebaseAuthServiceImpl()

        val loginViewModelFactory = SignUpViewModelFactory(firebaseAuthService as FirebaseAuthServiceImpl)
        viewModel = ViewModelProvider(this, loginViewModelFactory).get(SignUpViewModel::class.java)

        observer(viewModel)

        return view
    }

    private fun observer(viewModel: SignUpViewModel) {
        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSignup = view.findViewById<Button>(R.id.btnAddConsumption)
        val linkGoToSignUpFragment = view.findViewById<TextView>(R.id.linkSignup)

        linkGoToSignUpFragment.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        btnSignup.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.editHour).text.toString()
            val password = view.findViewById<EditText>(R.id.editPower).text.toString()

            viewModel.signUp(email, password)
        }
    }
}