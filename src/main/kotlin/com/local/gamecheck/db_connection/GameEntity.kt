package com.local.gamecheck.db_connection

import javax.persistence.*

@Entity
@Table(name="games")
class GameEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int = 0,
    val cover:String = "N/A Cover",
    val description:String? = "N/A Description",
    val price:Double = 0.0,
    val title:String = "N/A Title") { }