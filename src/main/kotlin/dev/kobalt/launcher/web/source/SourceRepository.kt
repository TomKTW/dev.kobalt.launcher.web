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

object SourceRepository {

    val pageTitle = "Source"
    val pageSubtitle = "Check out the code used for making this project."
    val pageEmpty = "There are no source code repositories available."
    val pageRoute = "source/"

    fun selectList(): List<Triple<String, String, String>> = listOf(
        Triple("dev.kobalt.launcher.android", "Application", "APK package of this application."),
        Triple("dev.kobalt.launcher.web", "Frontend", "Website server that provides this page.")
    )

}