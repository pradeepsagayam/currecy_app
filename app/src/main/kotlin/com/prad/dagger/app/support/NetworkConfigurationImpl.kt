package com.prad.dagger.app.support

import android.content.Context
import com.prad.dagger.app.network.BuildConfig
import com.prad.dagger.app.network.NetworkConfiguration
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class NetworkConfigurationImpl(private val context: Context) : NetworkConfiguration {

    override fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioScheduler(): Scheduler = Schedulers.io()

    override fun getHost(): String {
        return if (BuildConfig.DEBUG) "https://api.exchangeratesapi.io/"
        else "http://data.fixer.io/api"
    }

    override fun getCacheDir(): File = context.cacheDir

    override fun getCacheSize(): Long = 10 * 1024 * 1024

    override fun getTimeoutSeconds(): Long = 60
}
