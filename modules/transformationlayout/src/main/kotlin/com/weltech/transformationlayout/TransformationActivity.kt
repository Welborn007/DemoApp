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

package com.weltech.transformationlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.weltech.transformationlayout.TransformationCompat.activityTransitionName

/** An abstract activity extending [ComponentActivity] with registering transformation automatically. */
public abstract class TransformationActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationEndContainer(intent.getParcelableExtra(activityTransitionName))
    super.onCreate(savedInstanceState)
  }
}