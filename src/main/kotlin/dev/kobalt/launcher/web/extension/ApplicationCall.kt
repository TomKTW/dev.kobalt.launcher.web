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

package dev.kobalt.launcher.web.extension

import dev.kobalt.launcher.web.html.HtmlRepository
import dev.kobalt.launcher.web.index.IndexRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.utils.io.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jsoup.Jsoup
import java.io.ByteArrayOutputStream

suspend fun ApplicationCall.respondHtmlContent(
    status: HttpStatusCode = HttpStatusCode.OK,
    title: String? = null,
    description: String? = null,
    head: (HEAD.() -> Unit) = {},
    content: (DIV.() -> Unit) = {}
) {
    var rootPath = "./"; repeat(request.uri.count { it == '/' } - 1) { rootPath += "../" }

    respond(HtmlContent(status = status) {
        head {
            title?.let { this.title { text("$it - ${HtmlRepository.mainTitle}") } }
                ?: run { this.title(HtmlRepository.mainTitle) }
            description?.let { meta { name = "description"; this.content = it } }
            meta { httpEquiv = "Content-Type"; this.content = "text/html;charset=UTF-8" }
            meta { name = "viewport"; this.content = "width=device-width, initial-scale=1.0, shrink-to-fit=no" }
            link { rel = "icon"; type = "image/gif"; href = HtmlRepository.faviconUrl }
            style { defaultCss() }
            head.invoke(this)
        }
        body {
            div("wrapper") {
                div("header-main") {
                    div("header") {
                        div("title") {
                            HtmlRepository.mainTitle.let { h1 { a(href = rootPath) { text(it) } } }
                        }
                        hr { }
                        label { htmlFor = "header-main-nav-toggle"; text("Menu") }
                        input { type = InputType.checkBox; id = "header-main-nav-toggle" }
                        div("nav") {
                            ul {
                                IndexRepository.pageLinks.forEachIndexed { index, it ->
                                    li { a(href = "${rootPath}${it.first}") { text(it.second) } }
                                }
                            }
                        }
                    }
                }
                hr { id = "top" }
                div("content-main") {
                    content.invoke(this)
                }
                hr { }
                div("push")
            }
            div("footer-main") {
                div("footer") {
                    div("nav") {
                        ul {
                            li { a(href = "./..") { text("Back") } }
                            li("top") { a(href = "#top") { text("Top") } }
                        }
                    }
                }
            }
        }
    })
}

class HtmlContent(
    override val status: HttpStatusCode? = null,
    private val builder: HTML.() -> Unit
) : OutgoingContent.WriteChannelContent() {

    override val contentType: ContentType
        get() = ContentType.Text.Html.withCharset(Charsets.UTF_8)

    override suspend fun writeTo(channel: ByteWriteChannel) {
        val content = ByteArrayOutputStream()
        try {
            content.bufferedWriter().use {
                it.append("<!DOCTYPE html>\n")
                it.appendHTML().html(block = builder)
            }
        } catch (cause: Throwable) {
            channel.close(cause)
            throw cause
        }
        channel.writeFully(Jsoup.parse(content.toString()).toString().toByteArray())
    }
}
