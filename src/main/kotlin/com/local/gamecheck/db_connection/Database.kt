package com.local.gamecheck.db_connection

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

//use of a singleton
//only one instance/object of this class
object Database {
    //will return null in case of non-existing database
    //or connection error
    //fun createConnection():Connection?{
    //   return try{
    //        DriverManager.getConnection("jdbc:mysql://localhost:3306/gamecheck",
    //            "root", "###")
    //    } catch (e:SQLException){
    //        e.printStackTrace()
    //        null
    //    }
    // }
    fun getEntityManager():EntityManager {
        //factory uses the 'persistenceUnitName' parameter to find in the persistence.xml the tag and infos
        //to connect in the DB
        val factory:EntityManagerFactory = Persistence.createEntityManagerFactory("gamecheck")
        return factory.createEntityManager()
    }
}