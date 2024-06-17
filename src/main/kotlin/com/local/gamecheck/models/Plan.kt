package com.local.gamecheck.models

sealed class Plan(val tipo: String, var id:Int = 0) {
    open fun obterValor(rent: RentRelation): Double {
        return rent.game.preco * rent.timePeriod.emDias
    }
}