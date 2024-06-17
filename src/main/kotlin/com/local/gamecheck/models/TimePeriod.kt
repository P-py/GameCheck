package com.local.gamecheck.models

import java.time.LocalDate
import java.time.Period
import javax.persistence.Embeddable

@Embeddable
data class TimePeriod(
    val dataInicial:LocalDate = LocalDate.now(),
    val dataFinal:LocalDate = LocalDate.now().plusDays(7)) {
    val emDias = Period.between(dataInicial, dataFinal).days
}
