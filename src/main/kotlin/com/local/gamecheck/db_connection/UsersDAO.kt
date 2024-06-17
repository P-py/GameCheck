package com.local.gamecheck.db_connection

import com.local.gamecheck.models.User
import com.local.gamecheck.util.entityToModel
import com.local.gamecheck.util.transformToEntity
import javax.persistence.EntityManager

class UsersDAO(dbManager:EntityManager):DAO<User, UserEntity>(dbManager, UserEntity::class.java) {
    override fun transformToEntity(originalObject:User):UserEntity{
        return originalObject.transformToEntity()
        // originalObject.plan.transformToEntity() -> transforms the parameter plan on User class to a
        // PlanEntity used on the database
    }
    override fun entityToModel(originalObject:UserEntity): User {
        return originalObject.entityToModel().apply { plan = originalObject.plan.entityToModel() }
    }
}