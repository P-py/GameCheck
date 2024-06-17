package com.local.gamecheck.db_connection

import com.local.gamecheck.models.RentRelation
import com.local.gamecheck.util.entityToModel
import com.local.gamecheck.util.transformToEntity
import javax.persistence.EntityManager

class RentRelationDAO(dbManager: EntityManager):DAO<RentRelation, RentRelationEntity>(
    dbManager, RentRelationEntity::class.java) {
    override fun transformToEntity(originalObject: RentRelation): RentRelationEntity {
        return RentRelationEntity(originalObject.user.transformToEntity(), originalObject.game.transformToEntity(),
            originalObject.timePeriod).apply {
                value = originalObject.valorDoAluguel
                id = originalObject.id
            }
        }
    override fun entityToModel(originalObject: RentRelationEntity): RentRelation {
        return RentRelation(originalObject.user.entityToModel(), originalObject.game.entityToModel(),
            originalObject.period).apply {
                id = originalObject.id
            }
    }
}