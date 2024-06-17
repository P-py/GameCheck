package com.local.gamecheck.models

data class RentRelation(
    val user: User,
    val game: Game,
    val timePeriod: TimePeriod
) {
    val valorDoAluguel = user.plan.obterValor(this)
    var id = 0

    override fun toString(): String {
        return "Aluguel do jogo ${game.titulo} por ${user.nome} pelo valor R$$valorDoAluguel"
    }
}
