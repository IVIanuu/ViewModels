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

package com.ivianuu.viewmodels.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivianuu.viewmodels.ViewModel
import com.ivianuu.viewmodels.ViewModelBinder

/**
 * @author Manuel Wrage (IVIanuu)
 */
class SomeFragment : Fragment(), ViewModelBinder.Factory<SomeViewModel> {

    private val viewModel by lazy { ViewModelBinder.get(this, this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_some, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
    }

    override fun create(): SomeViewModel = SomeViewModel()
}

class SomeViewModel : ViewModel() {

    init {
        d { "init" }
    }

    override fun onCleared() {
        d { "on cleared" }
    }
}