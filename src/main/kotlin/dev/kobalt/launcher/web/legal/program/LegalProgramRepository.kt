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

package dev.kobalt.launcher.web.legal.program

import dev.kobalt.launcher.web.legal.program.license.LegalProgramLicenseRepository
import dev.kobalt.launcher.web.legal.program.privacy.LegalProgramPrivacyRepository
import dev.kobalt.launcher.web.legal.program.thirdparty.LegalProgramThirdPartyRepository

object LegalProgramRepository {

    val pageTitle = "Program"
    val pageSubtitle = "Legal details about this program."
    val pageRoute = "program/"
    val pageLinks = listOf(
        Triple(
            LegalProgramLicenseRepository.pageRoute,
            LegalProgramLicenseRepository.pageTitle,
            LegalProgramLicenseRepository.pageSubtitle
        ),
        Triple(
            LegalProgramPrivacyRepository.pageRoute,
            LegalProgramPrivacyRepository.pageTitle,
            LegalProgramPrivacyRepository.pageSubtitle
        ),
        Triple(
            LegalProgramThirdPartyRepository.pageRoute,
            LegalProgramThirdPartyRepository.pageTitle,
            LegalProgramThirdPartyRepository.pageSubtitle
        )
    )

}