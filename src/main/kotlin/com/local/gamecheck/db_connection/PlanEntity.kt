package com.local.gamecheck.db_connection

import javax.persistence.*

//How to map inheritance to Relational Structure coming from an Object-Oriented Structure?

@Entity
@Table(name = "subscription_plans")
// There are another 2 options of inheritance mapping
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
// Considering that the Single Plan and Subscription Plan objects will be stored in the same table applying inheritance
// there's need to differentiate objects from being of inherit type Single or Subscription
@DiscriminatorColumn (name = "TypePlan", discriminatorType = DiscriminatorType.STRING)
sealed class PlanEntity(
    val type:String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int=0)

@Entity
@DiscriminatorValue("Single")
class PlanSingleEntity(type:String = "Single", id:Int = 0):PlanEntity(type, id)

@Entity
@DiscriminatorValue("Subscription")
class PlanSubscriptionEntity(
    type:String = "Subscription",
    val subscriptionFee:Double = 0.0,
    val gamesIncluded:Int = 0,
    val discountPercentage:Double = 0.0,
    id:Int = 0):PlanEntity(type, id)