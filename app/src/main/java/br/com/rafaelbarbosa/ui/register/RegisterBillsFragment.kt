package br.com.rafaelbarbosa.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.rafaelbarbosa.R
import br.com.rafaelbarbosa.domain.entity.Bill
import br.com.rafaelbarbosa.domain.service.impl.BillServiceImpl

class RegisterBillsFragment : Fragment() {

    val service = BillServiceImpl()
    private lateinit var viewModel: RegisterBillsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.register_bills_fragment, container, false)

        viewModel = RegisterBillsViewModel()

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAddConsumption = view.findViewById<Button>(R.id.btnAddConsumption)
        val btnBackStack = view.findViewById<ImageView>(R.id.backStack)
        val btnLogout = view.findViewById<ImageView>(R.id.btnLogoutInRegister)

        btnLogout.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        btnBackStack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnAddConsumption.setOnClickListener {
            val editPower = view.findViewById<EditText>(R.id.editPower).text.toString().toDouble()
            val editHour = view.findViewById<EditText>(R.id.editHour).text.toString().toInt()
            val editDescription = view.findViewById<EditText>(R.id.editDescription).text.toString()

            viewModel.addBill(Bill(null, editHour, editPower, editDescription))

            findNavController().popBackStack()
        }
    }
}