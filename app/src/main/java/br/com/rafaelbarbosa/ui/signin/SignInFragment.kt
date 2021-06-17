package br.com.rafaelbarbosa.ui.signin

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.sign_in_fragment.*

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel
    private lateinit var firebaseAuthService: FirebaseAuthService
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.sign_in_fragment, container, false)

        firebaseAuthService = FirebaseAuthServiceImpl()
        firebaseAuth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()

        val linkSignup = view.findViewById<TextView>(R.id.linkSignup)

        val signInViewModelFactory = SignInViewModelFactory(firebaseAuthService as FirebaseAuthServiceImpl)
        viewModel = ViewModelProvider(this, signInViewModelFactory).get(SignInViewModel::class.java)

        observer(viewModel)

        val login_button = view.findViewById<LoginButton>(R.id.login_button)
        login_button.setReadPermissions("email", "public_profile")
        login_button.setFragment(this)
        signIn(login_button)


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

    private fun signIn(login_button: LoginButton) {
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                handleFacebookAccessToken(loginResult!!.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(exception: FacebookException) {
                Log.d(TAG, "facebook:onError", exception)
            }
        })
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = firebaseAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken?) {
        Log.d(Companion.TAG, "handleFacebookAccessToken:$accessToken")

        val credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                if (it != null) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(Companion.TAG, "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    companion object {
        private val TAG: String? = "Login -> "
    }
}