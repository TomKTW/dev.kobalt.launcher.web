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

package dev.kobalt.launcher.web.markdown

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet

class MarkdownParser {

    companion object {
        val instance = MarkdownParser()
    }

    private val options = MutableDataSet().set(HtmlRenderer.ESCAPE_HTML, true)

    private val renderer = HtmlRenderer.builder(options).build()
    private val parser = Parser.builder(options).build()

    fun parse(content: String): String {

        return renderer.render(parser.parse(content.trim().replace("\r", "")))
    }

}

fun String.fromMarkdown(): String = MarkdownParser.instance.parse(this)
