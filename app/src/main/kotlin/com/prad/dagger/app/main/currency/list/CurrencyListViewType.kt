package com.prad.dagger.app.main.currency.list

import androidx.annotation.IntDef
import com.prad.dagger.app.main.currency.list.CurrencyListViewType.Companion.CURRENCY

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    CURRENCY
)
annotation class CurrencyListViewType {

    companion object {
        const val CURRENCY = 0
    }
}
