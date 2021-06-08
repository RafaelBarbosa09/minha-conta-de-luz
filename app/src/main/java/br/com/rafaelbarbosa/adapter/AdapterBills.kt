package br.com.rafaelbarbosa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaelbarbosa.R
import br.com.rafaelbarbosa.domain.entity.Bill
import kotlinx.android.synthetic.main.bills_list_adapter.view.*

class AdapterBills (private val billsList: List<Bill>, private val actionClick: (Bill) -> Unit) :
    RecyclerView.Adapter<AdapterBills.BillsViewHolder>() {

    class BillsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var power = view.textPower
        var hour = view.textHour
        var description = view.textViewDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bills_list_adapter, parent, false)
        return BillsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BillsViewHolder, position: Int) {
        val bill = billsList.get(position)

        holder.power.text = bill.power.toString()
        holder.hour.text = bill.hour.toString()
        holder.description.text = bill.description.toString()

        holder.itemView.setOnClickListener{
            actionClick(bill)
        }
    }

    override fun getItemCount(): Int = billsList.size
}