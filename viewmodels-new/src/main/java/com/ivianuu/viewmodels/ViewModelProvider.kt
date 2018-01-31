/*
 * Copyright 2018 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.viewmodels

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * A provider for [ViewModel]'s
 */
class ViewModelBinder<out VM : ViewModel> private constructor(
    private val store: ViewModelStore,
    private val factory: () -> VM
) {

    @Suppress("UNCHECKED_CAST")
    fun get(key: Any) : VM {
        var viewModel = store[key]
        if (viewModel != null) {
            return viewModel as VM
        }

        viewModel = factory()
        store.put(key, viewModel)

        return viewModel
    }

    companion object {

        @JvmStatic
        fun <VM : ViewModel> of(activity: FragmentActivity,
                                factory: () -> VM): ViewModelBinder<VM> {
            val store = HolderFragment.holderFragmentFor(activity).viewModelStore
            return ViewModelBinder(store, factory)
        }

        @JvmStatic
        fun <VM : ViewModel> of(fragment: Fragment,
                                factory: () -> VM): ViewModelBinder<VM> {
            val store = HolderFragment.holderFragmentFor(fragment).viewModelStore
            return ViewModelBinder(store, factory)
        }
    }
}