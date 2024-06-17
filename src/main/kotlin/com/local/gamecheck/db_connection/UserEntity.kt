package com.local.gamecheck.db_connection

import javax.persistence.*

@Entity
@Table(name="users")
// indicates the class as a Hibernate Entity
// and links it to a table on the DB
class UserEntity (
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id:Int = 0,
    val name:String = "N/A Name",
    val email:String = "N/A E-mail",
    val birthDate:String? = "N/A Birth Date",
    val user:String? = "N/A User",
    @ManyToOne
    // Link between two entities
    // More than one user can use the same type of plan
    val plan:PlanEntity = PlanSingleEntity()
){}