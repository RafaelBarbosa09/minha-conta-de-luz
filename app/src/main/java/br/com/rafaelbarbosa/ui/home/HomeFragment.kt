package br.com.rafaelbarbosa.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaelbarbosa.R
import br.com.rafaelbarbosa.adapter.AdapterBills
import br.com.rafaelbarbosa.domain.entity.Bill
import br.com.rafaelbarbosa.domain.service.impl.BillServiceImpl
import br.com.rafaelbarbosa.domain.service.impl.FirebaseAuthServiceImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    var authService = FirebaseAuthServiceImpl()
    private lateinit var viewModel: HomeViewModel
    private lateinit var billsServiceImpl: BillServiceImpl

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

    private fun setupBillsList(bills: List<Bill>) {
        recyclerBills.adapter = AdapterBills(bills) {

        }
        recyclerBills.layoutManager = LinearLayoutManager(requireContext())
    }

}