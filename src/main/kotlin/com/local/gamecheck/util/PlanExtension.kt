package com.local.gamecheck.util

import com.local.gamecheck.db_connection.PlanEntity
import com.local.gamecheck.db_connection.PlanSingleEntity
import com.local.gamecheck.db_connection.PlanSubscriptionEntity
import com.local.gamecheck.models.Plan
import com.local.gamecheck.models.PlanSingle
import com.local.gamecheck.models.PlanSubscription

fun Plan.transformToEntity():PlanEntity{
    return if (this is PlanSubscription){
        PlanSubscriptionEntity(this.tipo,
            this.mensalidade,
            this.jogosIncluidos,
            this.percentualDescontoReputacao,
            this.id)
    } else {
        PlanSingleEntity(this.tipo, this.id)
    }
}

fun PlanEntity.entityToModel():Plan{
    return if (this is PlanSubscriptionEntity){
        PlanSubscription(this.type,
            this.subscriptionFee,
            this.gamesIncluded,
            this.discountPercentage,
            this.id)
    } else {
        PlanSingle(this.type, this.id)
    }
}