/*
 * Designed and developed by 2021 weltech (weltech)
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

package com.weltech.bindables

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

/**
 * @author weltech (weltech)
 *
 * A binding extension for inflating a [layoutRes] and returns a DataBinding type [T].
 *
 * @param layoutRes The layout resource ID of the layout to inflate.
 * @param attachToParent Whether the inflated hierarchy should be attached to the parent parameter.
 *
 * @return T A DataBinding class that inflated using the [layoutRes].
 */
@BindingOnly
public fun <T : ViewDataBinding> ViewGroup.binding(
  @LayoutRes layoutRes: Int,
  attachToParent: Boolean = false
): T {
  return DataBindingUtil.inflate(
    LayoutInflater.from(context), layoutRes, this, attachToParent
  )
}

/**
 * @author weltech (weltech)
 *
 * A binding extension for inflating a [layoutRes] and returns a DataBinding type [T] with a receiver.
 *
 * @param layoutRes The layout resource ID of the layout to inflate.
 * @param attachToParent Whether the inflated hierarchy should be attached to the parent parameter.
 * @param block A DataBinding receiver lambda.
 *
 * @return T A DataBinding class that inflated using the [layoutRes].
 */
@BindingOnly
public inline fun <T : ViewDataBinding> ViewGroup.binding(
  @LayoutRes layoutRes: Int,
  attachToParent: Boolean = false,
  block: T.() -> Unit
): T {
  return binding<T>(layoutRes, attachToParent).apply(block)
}

/**
 * @author weltech (weltech)
 *
 * Returns a binding ID by a [KProperty].
 *
 * @return A binding resource ID.
 */
internal inline val KProperty<*>.bindingId: Int
  @JvmSynthetic get() = BindingManager.getBindingIdByProperty(this)

/**
 * @author weltech (weltech)
 *
 * Returns a binding ID by a [KFunction].
 *
 * @return A binding resource ID.
 */
internal inline val KFunction<*>.bindingId: Int
  @JvmSynthetic get() = BindingManager.getBindingIdByFunction(this)
