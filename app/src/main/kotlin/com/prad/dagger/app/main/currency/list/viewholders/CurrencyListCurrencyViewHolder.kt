package com.prad.dagger.app.main.currency.list.viewholders

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.prad.dagger.app.R
import com.prad.dagger.app.main.currency.list.viewmodels.CurrencyListCurrencyViewModel
import java.util.*

class CurrencyListCurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.countryFlag)
    lateinit var countryFlag: AppCompatImageView

    @BindView(R.id.currencyCode)
    lateinit var currencyCode: AppCompatTextView

    @BindView(R.id.currencyName)
    lateinit var currencyName: AppCompatTextView

    @BindView(R.id.convertedAmount)
    lateinit var convertedAmount: AppCompatTextView

    init {
        ButterKnife.bind(this, itemView)
    }


    fun bindView(viewModel: CurrencyListCurrencyViewModel) {

        val flag = itemView.resources.getIdentifier(
            "ic_" + viewModel.currencyCode.toLowerCase(Locale.getDefault()),
            "drawable",
            "com.prad.dagger.app"
        )

        try {
            countryFlag.setImageResource(flag)
        } catch (ex: Exception) {
            println("FAILEDDD::: " + viewModel.currencyCode)
        }
        currencyCode.text = viewModel.currencyCode
        currencyName.text = viewModel.currencyName
        convertedAmount.text = viewModel.convertedAmount
    }
}
