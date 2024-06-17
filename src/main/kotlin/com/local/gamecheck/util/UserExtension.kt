package com.local.gamecheck.util

import com.local.gamecheck.db_connection.UserEntity
import com.local.gamecheck.models.User
import com.local.gamecheck.models.InfoUserJson

fun InfoUserJson.createUser(): User {
    return User(this.nome, this.email, this.dataNascimento, this.usuario)
}

fun User.transformToEntity():UserEntity{
    return UserEntity(this.id, this.nome, this.email, this.dataNascimento,
        this.usuario, this.plan.transformToEntity())
    // this.plan.transformToEntity() -> transforms the parameter plan on User class to a
    // PlanEntity used on the database
}

fun UserEntity.entityToModel():User{
    return User(this.name, this.email, this.birthDate, this.user,
        this.id)
    // .apply { plan = this.plan.entityToModel() } - scope function used to transform the parameter plan
    // on the database entity (PlanEntity) to a parameter on the local class model
}