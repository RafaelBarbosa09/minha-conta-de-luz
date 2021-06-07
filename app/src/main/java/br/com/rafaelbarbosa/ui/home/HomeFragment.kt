package br.com.rafaelbarbosa.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rafaelbarbosa.R
import br.com.rafaelbarbosa.adapter.AdapterBills
import br.com.rafaelbarbosa.domain.entity.Bill
import br.com.rafaelbarbosa.domain.entity.BillsUtil
import br.com.rafaelbarbosa.domain.service.impl.BillServiceImpl
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.bills_list_adapter.*
import kotlinx.android.synthetic.main.home_fragment.*
import java.text.DecimalFormat
import java.util.ArrayList

class HomeFragment : Fragment() {

    var authService = FirebaseAuthServiceImpl()
    private lateinit var viewModel: HomeViewModel
    private lateinit var billsServiceImpl: BillServiceImpl
    var consumption: Double ?= 0.0
//    var consumptionList = ArrayList<Double>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.home_fragment, container, false)

        billsServiceImpl = BillServiceImpl()

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewModel.getBills();

        viewModel.bills.observe(viewLifecycleOwner, Observer {
            setupBillsList(it)
            makeList(view)
        });

        val btnGoToBillsPage = view.findViewById<FloatingActionButton>(R.id.btnGoToBillsPage)
        btnGoToBillsPage.setOnClickListener{
            findNavController().navigate(R.id.registerBillsFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGoToBillsPage = view.findViewById<FloatingActionButton>(R.id.btnGoToBillsPage)
        val btnLogout = view.findViewById<ImageView>(R.id.btnLogout)

        btnLogout.setOnClickListener {
            authService.logout()
            findNavController().navigate(R.id.signInFragment)
        }

        btnGoToBillsPage.setOnClickListener{
            findNavController().navigate(R.id.registerBillsFragment)
        }

    }

    private fun makeList(view: View) {
        var totalConsumption: Double ?= 0.0
        val bills = viewModel.bills.value

        var consumptionList = ArrayList<Double>()
        makeList(bills, consumptionList)

        consumptionList.forEach {
            totalConsumption = totalConsumption?.plus(it)
        }

//        val dec = DecimalFormat("#.##0,0#")
//        val totalFormat = dec.format(totalConsumption)
        total.text = "R$ $totalConsumption"
    }

    private fun makeList(billsList: List<Bill>?, consumptionList: ArrayList<Double>) {
        billsList?.forEach {
            var hour = it.hour!!
            var power = it.power!!
            consumption = (power * hour * 30)/1000
            consumptionList.add(consumption!!)
        }
    }

    private fun setupBillsList(bills: List<Bill>) {
        recyclerBills.adapter = AdapterBills(bills) {
            BillsUtil.selectedBills = it
            findNavController().navigate(R.id.showBillsFragmentFragment)
        }
        recyclerBills.layoutManager = LinearLayoutManager(requireContext())
    }

}