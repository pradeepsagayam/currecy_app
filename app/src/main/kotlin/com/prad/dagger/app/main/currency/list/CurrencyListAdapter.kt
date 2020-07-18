package com.prad.dagger.app.main.currency.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prad.dagger.app.R
import com.prad.dagger.app.main.currency.list.CurrencyListViewType.Companion.CURRENCY
import com.prad.dagger.app.main.currency.list.viewholders.CurrencyListCurrencyViewHolder
import com.prad.dagger.app.main.currency.list.viewmodels.CurrencyListCurrencyViewModel
import javax.inject.Inject


class CurrencyListAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var viewModels: List<CurrencyListViewModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            CURRENCY -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_view_currency_item, parent, false)
                return CurrencyListCurrencyViewHolder(view)
            }
            else -> throw IllegalStateException(
                "Unknown type $viewType for TripsTimaticRestrictionsSummaryViewModel"
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return this.viewModels[position].getType()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val viewModel = viewModels[position]
        when (@CurrencyListViewType val viewType = viewModel.getType()) {
            CURRENCY -> {
                (viewHolder as CurrencyListCurrencyViewHolder).bindView(
                    viewModel as CurrencyListCurrencyViewModel
                )
            }
            else -> throw IllegalStateException(
                "Unknown type $viewType for TripsTimaticRestrictionsSummaryViewModel"
            )
        }
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    fun setViewModels(viewModels: List<CurrencyListViewModel>) {
        this.viewModels = viewModels
        notifyDataSetChanged()
    }

}
