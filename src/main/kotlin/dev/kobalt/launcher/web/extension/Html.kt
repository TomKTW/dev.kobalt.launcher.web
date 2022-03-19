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

import dev.kobalt.launcher.web.markdown.fromMarkdown
import kotlinx.css.CssBuilder
import kotlinx.html.*

fun HTMLTag.whitespace() {
    unsafe { +"&nbsp;" }
}

fun DIV.pageLink(
    title: String? = null,
    subtitle: String? = null,
    titleLink: String,
    block: HtmlBlockTag.() -> Unit = {}
) {
    div {
        title?.let { h2("title") { a(href = titleLink) { text(it) } } }
        subtitle?.let { p("subtitle") { text(it) } }
        block.invoke(this)
    }
}

fun DIV.pageArticle(title: String? = null, subtitle: String? = null, block: DIV.() -> Unit = {}) {
    div("article") {
        title?.let { h1("title") { text(it) } }
        subtitle?.let { p("subtitle") { text(it) } }
        hr { }
        block.invoke(this)
    }
}

fun DIV.pageForm(name: String, block: DIV.() -> Unit = {}) = form {
    method = FormMethod.post
    encType = FormEncType.multipartFormData
    this.name = name
    div { block.invoke(this) }
}

fun DIV.pageMarkdown(content: String) {
    unsafe {
        raw(content.fromMarkdown())
    }
}

fun DIV.pageInputTextBox(label: String?, name: String, value: String = "") = label {
    label?.let { text(it); whitespace() }
    textArea { this.name = name; text(value) }
}

fun DIV.pageInputFile(label: String, name: String) {
    label {
        text(label)
        whitespace()
        input { type = InputType.file; this.name = name }
    }
}

fun DIV.pageInputTextLabel(label: String, name: String, value: String = "") {
    label {
        text(label)
        whitespace()
        input { type = InputType.text; this.name = name; this.value = value }
    }
}

fun DIV.pageInputPasswordLabel(label: String, name: String, value: String = "") {
    label {
        text(label)
        whitespace()
        input { type = InputType.password; this.name = name; this.value = value }
    }
}

fun DIV.pageInputCheckBox(label: String, name: String, value: Boolean = false) {
    label {
        text(label)
        whitespace()
        input { type = InputType.checkBox; this.name = name; this.value = value.toString() }
    }
}

fun DIV.pageInputSubmit(label: String, name: String, value: String = "Submit") {
    label {
        text(label)
        whitespace()
        input { type = InputType.submit; this.name = name; this.value = value }
    }
}

fun STYLE.defaultCss() {
    unsafe {
        raw(
            CssBuilder().apply {
                rule("*") {
                    put("margin", "0")
                    put("padding", "0")
                    put("border", "0")
                    put("font", "inherit")
                    put("font-size", "100%")
                    put("font-weight", "normal")
                    put("font-family", "'DejaVu Sans',Verdana,Helvetica,sans-serif")
                    put("color", "#ffffff")
                    put("vertical-align", "baseline")
                    put("box-sizing", "border-box")
                }
                rule("body") {
                    put("background", "#000000")
                }
                rule("form") {
                    put("padding", "8px")
                }
                rule("input, textarea, select") {
                    put("background", "#111111")
                    put("padding", "8px")
                    put("width", "100%")
                    put("margin-top", "8px")
                    put("margin-bottom", "8px")
                }

                /* Footer sticky wrapper start */
                rule("html,body") {
                    put("height", "100%")
                    put("margin", "0")
                }
                rule(".wrapper") {
                    put("min-height", "100%")
                    put("margin-bottom", "-2.175em")
                }
                rule(".footer-main, .push") {
                    put("height", "2.175em")
                }
                /* Footer sticky wrapper end */

                /* Menu toggle start */
                rule("#header-main-nav-toggle") {
                    put("display", "none")
                }
                rule("#header-main-nav-toggle:checked + .nav") {
                    put("display", "block")
                }
                rule(".header-main .nav") {
                    put("display", "none")
                }
                rule(".header-main .header label") {
                    put("position", "absolute")
                    put("top", "0px")
                    put("right", "0px")
                    put("padding", "8px 16px")
                }
                rule(".header-main .header label:hover") {
                    put("background-color", "#111")
                    put("cursor", "pointer")
                }
                /* Menu toggle end */

                /* Main header begin */
                rule(".header-main") {
                    put("background", "#003300")
                }
                rule(".header-main .title h1") {
                    put("padding", "8px 0px")
                }
                rule(".header-main .title h1 a") {
                    put("padding", "8px 16px")
                    put("color", "white")
                    put("text-align", "center")
                    put("text-decoration", "none")
                }
                rule(".header-main .title h1 a:hover") {
                    put("background-color", "#111")
                }
                rule(".header-main .nav") {
                    put("background-color", "#004400")
                }
                rule(".header-main .nav ul") {
                    put("background-color", "#004400")
                    put("list-style-type", "none")
                    put("margin", "0")
                    put("padding", "0")
                    put("overflow", "hidden")
                }
                rule(".header-main .nav li a") {
                    put("display", "block")
                    put("color", "white")
                    put("text-align", "center")
                    put("padding", "8px")
                    put("text-decoration", "none")
                }
                rule(".header-main .nav li a:hover") {
                    put("background-color", "#111")
                }
                /* Main header end */

                /* Main footer begin */
                rule(".footer-main .nav") {
                    put("background-color", "#003300")
                }
                rule(".footer-main .nav ul") {
                    put("list-style-type", "none")
                    put("margin", "0")
                    put("padding", "0")
                    put("overflow", "hidden")
                }
                rule(".footer-main .nav li") {
                    put("float", "left")
                }
                rule(".footer-main .nav li.top") {
                    put("float", "right")
                }
                rule(".footer-main .nav li a") {
                    put("display", "block")
                    put("color", "white")
                    put("text-align", "center")
                    put("padding", "8px 16px")
                    put("text-decoration", "none")
                }
                rule(".footer-main .nav li a:hover") {
                    put("background-color", "#111")
                }
                /* Main footer end */

                /* Main content begin */
                rule(".content-main h1") {
                    put("padding", "8px")
                    put("fontSize", "1.50em")
                }
                rule(".content-main h2") {
                    put("padding", "8px")
                    put("fontSize", "1.40em")
                }
                rule(".content-main h3") {
                    put("padding", "8px")
                    put("fontSize", "1.30em")
                }
                rule(".content-main h4") {
                    put("padding", "8px")
                    put("fontSize", "1.20em")
                }
                rule(".content-main h5") {
                    put("padding", "8px")
                    put("fontSize", "1.10em")
                }
                rule(".content-main h6") {
                    put("padding", "8px")
                    put("fontSize", "1.00em")
                }
                rule(".content-main p") {
                    put("padding", "8px")
                    put("line-height", "1.5em")
                    put("fontSize", "1.00em")
                }
                rule(".content-main pre") {
                    put("padding", "8px")
                    put("line-height", "1.5em")
                    put("fontSize", "1em")
                    put("overflow", "auto")
                }
                rule(".content-main pre code") {
                    put("font-family", "monospace")
                }
                rule(".content-main ul li") {
                    put("padding", "8px")
                    put("line-height", "1em")
                    put("fontSize", "0.80em")
                    put("margin-left", "24px")
                }
                /* Main header end */
                /* Article start */
                rule(".content-main .article") {
                    put("padding", "8px")
                }
                rule(".content-main .article hr") {
                    put("margin", "8px")
                    put("border-width", "1px")
                    put("border-style", "solid")
                    put("border-color", "#888888")
                }
                /* Article end */
            }.toString().replace("\n", "")
        )
    }
}