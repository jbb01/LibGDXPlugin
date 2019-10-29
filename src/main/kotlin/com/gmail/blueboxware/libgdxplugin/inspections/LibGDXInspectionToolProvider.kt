package com.gmail.blueboxware.libgdxplugin.inspections

import com.gmail.blueboxware.libgdxplugin.filetypes.json.inspections.LibGDXDuplicatePropertyInspection
import com.gmail.blueboxware.libgdxplugin.filetypes.json.inspections.LibGDXJsonInvalidEscapeInspection
import com.gmail.blueboxware.libgdxplugin.filetypes.skin.inspections.*
import com.gmail.blueboxware.libgdxplugin.inspections.gradle.*
import com.gmail.blueboxware.libgdxplugin.inspections.java.*
import com.gmail.blueboxware.libgdxplugin.inspections.kotlin.*
import com.gmail.blueboxware.libgdxplugin.inspections.xml.MissingExternalFilesPermissionInspection
import com.gmail.blueboxware.libgdxplugin.inspections.xml.OpenGLESDirectiveInspection
import com.gmail.blueboxware.libgdxplugin.inspections.xml.XmlTestIdsInspection
import com.intellij.codeInspection.InspectionToolProvider
import com.intellij.codeInspection.LocalInspectionTool

/*
 * Copyright 2017 Blue Box Ware
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
class LibGDXInspectionToolProvider: InspectionToolProvider {

  override fun getInspectionClasses(): Array<Class<out LocalInspectionTool>> = arrayOf(
          JavaUnsafeIteratorInspection::class.java,
          JavaTestIdsInspection::class.java,
          JavaStaticResourceInspection::class.java,
          JavaMissingFlushInspection::class.java,
          JavaProfilingCodeInspection::class.java,
          JavaShapeRenderer64BitCrashInspection::class.java,
          JavaFlushInsideLoopInspection::class.java,
          JavaLogLevelInspection::class.java,
          JavaGDXAssetsInspection::class.java,
          JavaInvalidPropertyKeyInspection::class.java,
          JavaNonExistingAssetInspection::class.java,
          JavaUnusedClassTagInspection::class.java,

          KotlinUnsafeIteratorInspection::class.java,
          KotlinTestIdsInspection::class.java,
          KotlinStaticResourceInspection::class.java,
          KotlinMissingFlushInspection::class.java,
          KotlinProfilingCodeInspection::class.java,
          KotlinShapeRenderer64BitCrashInspection::class.java,
          KotlinFlushInsideLoopInspection::class.java,
          KotlinLogLevelInspection::class.java,
          KotlinGDXAssetsInspection::class.java,
          KotlinInvalidPropertyKeyInspection::class.java,
          KotlinNonExistingAssetInspection::class.java,
          KotlinUnusedClassTagInspection::class.java,

          OpenGLESDirectiveInspection::class.java,
          MissingExternalFilesPermissionInspection::class.java,

          GradleOutdatedVersionsInspection::class.java,
          GradleKotlinOutdatedVersionInspection::class.java,
          GradlePropertiesOutdatedVersionsInspection::class.java,
          GradleTestIdsInspection::class.java,
          GradlePropertiesTestIdsInspection::class.java,

          XmlTestIdsInspection::class.java,

          SkinNonExistingClassInspection::class.java,
          SkinNonExistingFieldInspection::class.java,
          SkinNonExistingResourceAliasInspection::class.java,
          SkinMalformedColorStringInspection::class.java,
          SkinTypeInspection::class.java,
          SkinDuplicateResourceNameInspection::class.java,
          SkinMissingPropertyInspection::class.java,
          SkinDuplicatePropertyInspection::class.java,
          SkinNonExistingFontFileInspection::class.java,
          SkinAbbrClassInspection::class.java,
          SkinDeprecatedInspection::class.java,

          LibGDXJsonInvalidEscapeInspection::class.java,
          LibGDXDuplicatePropertyInspection::class.java

  )
}