package com.local.gamecheck.util

import com.local.gamecheck.db_connection.GameEntity
import com.local.gamecheck.models.InfoGameJson
import com.local.gamecheck.models.Game

fun InfoGameJson.createGame(): Game {
    return Game(this.titulo, this.capa, this.preco, this.descricao)
}

fun Game.transformToEntity():GameEntity{
    return GameEntity(this.id, this.capa, this.descricao, this.preco,
        this.titulo)
}

fun GameEntity.entityToModel():Game{
    return Game(this.title, this.cover, this.price, this.description,
        this.id)
}