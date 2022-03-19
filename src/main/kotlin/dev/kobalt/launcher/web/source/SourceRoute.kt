/*
 * dev.kobalt.launcher
 * Copyright (C) 2022 Tom.K
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.kobalt.launcher.web.source

import dev.kobalt.launcher.web.extension.pageArticle
import dev.kobalt.launcher.web.extension.pageLink
import dev.kobalt.launcher.web.extension.respondHtmlContent
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.p

fun Route.sourceRoute() {
    route(SourceRepository.pageRoute) {
        get {
            call.respondHtmlContent(
                title = SourceRepository.pageTitle, description = SourceRepository.pageSubtitle
            ) {
                pageArticle(
                    SourceRepository.pageTitle, SourceRepository.pageSubtitle
                ) {
                    SourceRepository.selectList().takeIf { it.isNotEmpty() }?.forEach {
                        pageLink(it.second, it.third, "${it.first}/")
                    } ?: run {
                        p { text(SourceRepository.pageEmpty) }
                    }
                }
            }
        }
        SourceRepository.selectList().forEach { entry ->
            route("${entry.first}/") {
                get { call.respondRedirect("/git/entry/${entry.first}.git/") }
            }
        }
    }
}