package com.local.gamecheck.db_connection

import com.local.gamecheck.models.TimePeriod
import javax.persistence.*

@Entity
@Table(name = "rented_games")
class RentRelationEntity(
    @ManyToOne
    val user:UserEntity = UserEntity(),
    @ManyToOne
    val game:GameEntity = GameEntity(),
    @Embedded
    val period:TimePeriod = TimePeriod()
){
    var value = 0.0
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
}