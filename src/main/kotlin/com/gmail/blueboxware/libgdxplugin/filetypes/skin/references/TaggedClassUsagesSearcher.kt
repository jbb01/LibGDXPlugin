package com.gmail.blueboxware.libgdxplugin.filetypes.skin.references

import com.gmail.blueboxware.libgdxplugin.filetypes.skin.LibGDXSkinFileType
import com.gmail.blueboxware.libgdxplugin.filetypes.skin.psi.SkinFile
import com.gmail.blueboxware.libgdxplugin.utils.TAG_ANNOTATION_NAME
import com.gmail.blueboxware.libgdxplugin.utils.getSkinTag2ClassMap
import com.intellij.openapi.application.QueryExecutorBase
import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiReference
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.util.Processor
import org.jetbrains.kotlin.psi.KtClass


/*
 * Copyright 2018 Blue Box Ware
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
class TaggedClassUsagesSearcher: QueryExecutorBase<PsiReference, ReferencesSearch.SearchParameters>() {

  override fun processQuery(queryParameters: ReferencesSearch.SearchParameters, consumer: Processor<in PsiReference>) {

    if (queryParameters.scope is LocalSearchScope) {
      return
    }

    val element = queryParameters.elementToSearch

    val qualifiedName = if (element is PsiClass) {
      ReadAction.compute<String, Throwable> {  element.qualifiedName }
    } else if (element is KtClass) {
      ReadAction.compute<String, Throwable> { element.fqName?.asString() }
    } else {
      null
    }

    if (qualifiedName == null || qualifiedName == TAG_ANNOTATION_NAME) {
      return
    }

    DumbService.getInstance(queryParameters.project).runReadActionInSmartMode {

      val tagsToFind = queryParameters.project.getSkinTag2ClassMap()?.getTags(qualifiedName)?.filter { tag ->
        StringUtil.getShortName(qualifiedName) != tag
      } ?: return@runReadActionInSmartMode

      if (tagsToFind.isEmpty()) {
        return@runReadActionInSmartMode
      }

      val psiManager = PsiManager.getInstance(queryParameters.project)

      FileTypeIndex.getFiles(LibGDXSkinFileType.INSTANCE, GlobalSearchScope.allScope(queryParameters.project)).forEach { virtualFile ->
        (psiManager.findFile(virtualFile) as? SkinFile)?.let { skinFile ->
          skinFile.getClassSpecifications(qualifiedName).forEach { classSpec ->
            if (classSpec.className.stringLiteral.value in tagsToFind) {
              classSpec.className.references.forEach { reference ->
                consumer.process(reference)
              }
            }
          }
        }
      }

    }

  }
}