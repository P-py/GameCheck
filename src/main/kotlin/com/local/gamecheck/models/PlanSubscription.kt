package com.local.gamecheck.models

class PlanSubscription(
    tipo: String,
    val mensalidade: Double,
    val jogosIncluidos: Int,
    val percentualDescontoReputacao: Double,
    id: Int = 0): Plan(tipo, id) {

    override fun obterValor(rent: RentRelation): Double {
        val totalJogosNoMes = rent.user.jogosDoMes(rent.timePeriod.dataInicial.monthValue).size+1

        return if (totalJogosNoMes <= jogosIncluidos) {
            0.0
        } else {
            var valorOriginal = super.obterValor(rent)
            if (rent.user.media > 8) {
                valorOriginal -= valorOriginal * percentualDescontoReputacao
            }
            valorOriginal
        }
    }

    override fun toString(): String {
        return "Plano Assinatura\n" +
                "Tipo: $tipo\n" +
                "Id: $id\n" +
                "Mensalidade: $mensalidade\n" +
                "Jogos Incluidos: $jogosIncluidos\n" +
                "Percentual Desconto Reputacao: $percentualDescontoReputacao\n" + "\n"
    }
}
