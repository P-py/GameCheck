package com.local.gamecheck.db_connection

import com.local.gamecheck.models.Game
import com.local.gamecheck.util.entityToModel
import com.local.gamecheck.util.transformToEntity
import javax.persistence.EntityManager

class GamesDAO(dbManager:EntityManager):DAO<Game,GameEntity>(dbManager, GameEntity::class.java) {
    override fun entityToModel(originalObject:GameEntity):Game{
        return originalObject.entityToModel()
    }
    override fun transformToEntity(originalObject:Game): GameEntity {
        return originalObject.transformToEntity()
    }

//    fun getGames():List<Game>?{
//        val listGames = mutableListOf<Game>()
//        val dbConnection = Database.createConnection()
//        //check for valid MySQL connection
//        if (dbConnection!=null){
//            try {
//                val sqlStatement = dbConnection.createStatement()
//                val sqlResult = sqlStatement.executeQuery("SELECT * FROM gamecheck.games")
//                //iterates for each line in the database
//                while (sqlResult.next()){
//                    val id = sqlResult.getInt("id")
//                    val cover = sqlResult.getString("cover")
//                    val title = sqlResult.getString("title")
//                    val description = sqlResult.getString("description")
//                    val price = sqlResult.getDouble("price")
//
//                    val gameObject = Game(title, cover, price, description, id)
//                    listGames.add(gameObject)
//                }
//                sqlStatement.close()
//            } finally {
//                dbConnection.close()
//            }
//            return listGames
//        } else {
//            println("MySQL Connection not available.")
//            return null
//        }
//    }
//    fun addGame(gameObject:Game){
//        val dbConnection = Database.createConnection()
//        val insertQuery = "INSERT INTO gamecheck.games (title, cover, description, price) VALUES (?, ?, ?, ?)"
//        if (dbConnection!=null){
//            try {
//                val sqlInsertStatement = dbConnection.prepareStatement(insertQuery)
//                //maps the object parameters to query
//                sqlInsertStatement.setString(1, gameObject.titulo)
//                sqlInsertStatement.setString(2, gameObject.capa)
//                sqlInsertStatement.setString(3, gameObject.descricao)
//                sqlInsertStatement.setDouble(4, gameObject.preco)
//
//                sqlInsertStatement.executeUpdate()
//                sqlInsertStatement.close()
//            } finally {
//                dbConnection.close()
//            }
//        } else {
//            println("MySQL Connection not available.")
//        }
//    }
}