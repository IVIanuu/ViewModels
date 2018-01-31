package com.ivianuu.viewmodels.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ivianuu.viewmodels.ViewModel
import com.ivianuu.viewmodels.ViewModelBinder

class MainActivity : AppCompatActivity(), ViewModelBinder.Factory<MainViewModel> {

    private val viewModel by lazy { ViewModelBinder.get(this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SomeFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun create(): MainViewModel = MainViewModel()

}

class MainViewModel : ViewModel() {

    init {
        d { "init" }
    }

    override fun onCleared() {
        d { "cleared" }
    }
}

fun Any.d(message: () -> String) {
    Log.d(this::class.java.simpleName, message())
}