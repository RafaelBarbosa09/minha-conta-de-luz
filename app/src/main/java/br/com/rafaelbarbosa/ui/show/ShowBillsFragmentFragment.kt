package br.com.rafaelbarbosa.ui.show

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.rafaelbarbosa.R
import br.com.rafaelbarbosa.domain.entity.BillsUtil
import br.com.rafaelbarbosa.domain.service.FirebaseAuthService
import br.com.rafaelbarbosa.ui.home.HomeViewModel
import br.com.rafaelbarbosa.ui.signup.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_show_bills_fragment.*
import java.text.DecimalFormat

class ShowBillsFragmentFragment : Fragment() {

    private lateinit var viewModel: ShowBillsViewModel
    private lateinit var firebaseAuthService: FirebaseAuthService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_show_bills_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ShowBillsViewModel::class.java)

        val billsUtil = BillsUtil.selectedBills
        var hour = billsUtil?.hour
        var power = billsUtil?.power
        var consuption = (power!! * hour!! * 30)/1000
        var description = billsUtil?.description

        textShowHour.setText(hour.toString())
        textShowPower.setText(power.toString())
        editDescription.setText(description.toString())

        val decimalFormat = DecimalFormat("###,###,###,##0.00")
        var totalF = decimalFormat.format(consuption)
        textShowTotal.text = totalF.toString()

        backToHome.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        val btnRemove = view.findViewById<Button>(R.id.btnRemove)
        btnRemove.setOnClickListener {
            if (billsUtil != null) {
                viewModel.deleteBills(billsUtil)
                findNavController().navigate(R.id.homeFragment)
            }
        }

        btnEdit.setOnClickListener {
            billsUtil?.power = textShowPower.text.toString().toDouble()
            billsUtil?.hour = textShowHour.text.toString().toInt()
            billsUtil?.description = editDescription.text.toString()

            if (billsUtil != null) {
                viewModel.editBills(billsUtil)
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }
}