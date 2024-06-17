package com.local.gamecheck.db_connection

import com.local.gamecheck.models.Plan
import com.local.gamecheck.util.entityToModel
import com.local.gamecheck.util.transformToEntity
import javax.persistence.EntityManager

class PlanDAO(dbManager: EntityManager):DAO<Plan, PlanEntity>(dbManager, PlanEntity::class.java) {
    override fun transformToEntity(originalObject:Plan):PlanEntity {
        return originalObject.transformToEntity()
    }
    override fun entityToModel(originalObject:PlanEntity):Plan {
        return originalObject.entityToModel()
    }
}