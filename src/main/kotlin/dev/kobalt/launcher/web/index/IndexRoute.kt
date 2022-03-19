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

package dev.kobalt.launcher.web.index

import dev.kobalt.launcher.web.about.aboutRoute
import dev.kobalt.launcher.web.download.downloadRoute
import dev.kobalt.launcher.web.extension.pageArticle
import dev.kobalt.launcher.web.extension.pageLink
import dev.kobalt.launcher.web.extension.respondHtmlContent
import dev.kobalt.launcher.web.legal.legalRoute
import dev.kobalt.launcher.web.source.sourceRoute
import io.ktor.application.*
import io.ktor.routing.*

fun Route.indexRoute() {
    route("/") {
        get {
            call.respondHtmlContent(
                title = IndexRepository.pageTitle,
                description = IndexRepository.pageSubtitle
            ) {
                pageArticle(
                    IndexRepository.pageTitle,
                    IndexRepository.pageSubtitle
                ) {
                    IndexRepository.pageLinks.forEach {
                        pageLink(it.second, it.third, it.first)
                    }
                }
            }
        }
        aboutRoute()
        downloadRoute()
        sourceRoute()
        legalRoute()
    }
}