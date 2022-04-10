/*
 * Designed and developed by 2019 weltech (weltech)
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

package com.weltech.whatif_android

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.weltech.whatif.whatIfNotNullAs
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * @author weltech (weltech)
 *
 * An expression for invoking [whatIf] when the [FragmentActivity] has an attached fragment [T].
 *
 * @param id A fragment currently on the back stack associated with this ID resource.
 * @param whatIf An executable lambda function if the [FragmentActivity] has a fragment [T].
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : Fragment> FragmentActivity.whatIfFindFragment(
  @IdRes id: Int,
  whatIf: (T) -> Unit
) {
  contract {
    callsInPlace(whatIf, InvocationKind.EXACTLY_ONCE)
  }
  whatIfFindFragment(id, whatIf, {})
}

/**
 * @author weltech (weltech)
 *
 * An expression for invoking [whatIf] when the [FragmentActivity] has an attached fragment [T].
 * If [FragmentActivity] has not an attached fragment [T], [whatIfNot] will be invoked instead of the [whatIf].
 *
 * @param id A fragment currently on the back stack associated with this ID resource.
 * @param whatIf An executable lambda function if the [FragmentActivity] has a fragment [T].
 * @param whatIfNot An executable lambda function if the [FragmentActivity] has not an attached fragment [T].
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : Fragment> FragmentActivity.whatIfFindFragment(
  @IdRes id: Int,
  whatIf: (T) -> Unit,
  whatIfNot: () -> Unit
) {
  contract {
    callsInPlace(whatIf, InvocationKind.EXACTLY_ONCE)
  }
  supportFragmentManager.findFragmentById(id).whatIfNotNullAs(whatIf, whatIfNot)
}

/**
 * @author weltech (weltech)
 *
 * An expression for invoking [whatIf] when the [FragmentActivity] has an attached fragment [T].
 *
 * @param id A fragment currently on the back stack associated with this tag name.
 * @param whatIf An executable lambda function if the [FragmentActivity] has a fragment [T].
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : Fragment> FragmentActivity.whatIfFindFragment(
  tag: String?,
  whatIf: (T) -> Unit
) {
  contract {
    callsInPlace(whatIf, InvocationKind.EXACTLY_ONCE)
  }
  whatIfFindFragment(tag, whatIf, {})
}

/**
 * @author weltech (weltech)
 *
 * An expression for invoking [whatIf] when the [FragmentActivity] has an attached fragment [T].
 * If [FragmentActivity] has not an attached fragment [T], [whatIfNot] will be invoked instead of the [whatIf].
 *
 * @param tag A fragment currently on the back stack associated with this tag name.
 * @param whatIf An executable lambda function if the [FragmentActivity] has a fragment [T].
 * @param whatIfNot An executable lambda function if the [FragmentActivity] has not an attached fragment [T].
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : Fragment> FragmentActivity.whatIfFindFragment(
  tag: String?,
  whatIf: (T) -> Unit,
  whatIfNot: () -> Unit
) {
  contract {
    callsInPlace(whatIf, InvocationKind.EXACTLY_ONCE)
  }
  supportFragmentManager.findFragmentByTag(tag).whatIfNotNullAs(whatIf, whatIfNot)
}
