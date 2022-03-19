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

package dev.kobalt.launcher.web.legal.server

import dev.kobalt.launcher.web.legal.server.license.LegalServerLicenseRepository
import dev.kobalt.launcher.web.legal.server.privacy.LegalServerPrivacyRepository
import dev.kobalt.launcher.web.legal.server.thirdparty.LegalServerThirdPartyRepository

object LegalServerRepository {

    val pageTitle = "Server"
    val pageSubtitle = "Legal details for server that runs this website."
    val pageRoute = "server/"
    val pageLinks = listOf(
        Triple(
            LegalServerLicenseRepository.pageRoute,
            LegalServerLicenseRepository.pageTitle,
            LegalServerLicenseRepository.pageSubtitle
        ),
        Triple(
            LegalServerPrivacyRepository.pageRoute,
            LegalServerPrivacyRepository.pageTitle,
            LegalServerPrivacyRepository.pageSubtitle
        ),
        Triple(
            LegalServerThirdPartyRepository.pageRoute,
            LegalServerThirdPartyRepository.pageTitle,
            LegalServerThirdPartyRepository.pageSubtitle
        )
    )

}