package br.com.rafaelbarbosa.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.rafaelbarbosa.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.home_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGoToBillsPage = view.findViewById<FloatingActionButton>(R.id.btnGoToBillsPage)

        btnGoToBillsPage.setOnClickListener{
            findNavController().navigate(R.id.registerBillsFragment)
        }

        //Inicialização do Google AdMob
        MobileAds.initialize(requireContext())

        //Widget que exibirá o anúncio
        val adView = view.findViewById<AdView>(R.id.adView)

        //Requisitar um anúncio
        val adRequest = AdRequest.Builder().build()

        //Lançar o anúncio no widget próprio
        adView.loadAd(adRequest)
    }

}