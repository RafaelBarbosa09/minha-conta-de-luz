package br.com.rafaelbarbosa.ui.signin

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
import androidx.navigation.fragment.findNavController
import br.com.rafaelbarbosa.R
import br.com.rafaelbarbosa.domain.service.FirebaseAuthService
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl
import br.com.rafaelbarbosa.ui.signup.SignUpViewModel

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel
    private lateinit var firebaseAuthService: FirebaseAuthService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.sign_in_fragment, container, false)

        firebaseAuthService = FirebaseAuthServiceImpl()

        val linkSignup = view.findViewById<TextView>(R.id.linkSignup)

        val signInViewModelFactory = SignInViewModelFactory(firebaseAuthService as FirebaseAuthServiceImpl)
        viewModel = ViewModelProvider(this, signInViewModelFactory).get(SignInViewModel::class.java)

        observer(viewModel)

        linkSignup.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
        return view
    }

    private fun observer(viewModel: SignInViewModel) {
        viewModel.status.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it)
                findNavController().navigate(R.id.homeFragment)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSignIn = view.findViewById<Button>(R.id.btnSignin)

        btnSignIn.setOnClickListener {

            val email = view.findViewById<EditText>(R.id.editEmailLogin).text.toString()
            val password = view.findViewById<EditText>(R.id.iptPasswordLogin).text.toString()

            viewModel.signIn(email, password)
        }
    }
}