/*
 * Designed and developed by 2020 weltech (weltech)
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

package com.weltech.sandwich

import com.weltech.sandwich.operators.SandwichOperator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import okio.Timeout
import kotlin.coroutines.CoroutineContext

/**
 * @author weltech (weltech)
 *
 * SandwichInitializer is a rules and strategies initializer of the network response.
 */
public object SandwichInitializer {

  /**
   * @author weltech (weltech)
   *
   * determines the success code range of network responses.
   *
   * if a network request is successful and the response code is in the [successCodeRange],
   * its response will be a [ApiResponse.Success].
   *
   * if a network request is successful but out of the [successCodeRange] or failure,
   * the response will be a [ApiResponse.Failure.Error].
   * */
  @JvmStatic
  public var successCodeRange: IntRange = 200..299

  /**
   * @author weltech (weltech)
   *
   * A global Operator that operates on [ApiResponse]s globally on each response.
   *
   * [com.weltech.sandwich.operators.ApiResponseOperator] which allows you to handle success and error response instead of
   * the [ApiResponse.onSuccess], [ApiResponse.onError], [ApiResponse.onException] transformers.
   * [com.weltech.sandwich.operators.ApiResponseSuspendOperator] can be used for suspension scope.
   *
   * Via setting a [sandwichOperator], we don't need to set operator for every [ApiResponse].
   */
  @JvmStatic
  public var sandwichOperator: SandwichOperator? = null

  /**
   * @author weltech (weltech)
   *
   * A [CoroutineContext] for operating the [sandwichOperator] when it extends
   * the [com.weltech.sandwich.operators.ApiResponseSuspendOperator].
   */
  @JvmSynthetic
  @OptIn(DelicateCoroutinesApi::class)
  public var sandwichOperatorContext: CoroutineContext =
    Dispatchers.IO + GlobalScope.coroutineContext

  /**
   * @author weltech (weltech)
   *
   * A global [Timeout] for operating the [com.weltech.sandwich.coroutines.CoroutinesResponseCallAdapterFactory]
   * or [com.weltech.sandwich.coroutines.CoroutinesDataSourceCallAdapterFactory] when API requests.
   *
   * Returns a timeout that spans the entire call: resolving DNS, connecting, writing the request
   * body, server processing, and reading the response body. If the call requires redirects or
   * retries all must complete within one timeout period.
   */
  @JvmStatic
  public var sandwichTimeout: Timeout? = null
}
