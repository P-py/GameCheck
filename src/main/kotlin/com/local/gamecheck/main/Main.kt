package com.local.gamecheck.main

import com.local.gamecheck.db_connection.*
import com.local.gamecheck.models.*
import java.util.Scanner

val consoleInput = Scanner(System.`in`)
val dbManager = Database.getEntityManager()
val gamesDAO = GamesDAO(dbManager)
val plansDAO = PlanDAO(dbManager)
val rentsDAO = RentRelationDAO(dbManager)
val usersDAO = UsersDAO(dbManager)

fun main() {
    var gamesCounter = gamesDAO.getList().size
    var plansCounter = plansDAO.getList().size
    var rentsCounter = rentsDAO.getList().size
    var usersCounter = usersDAO.getList().size
    println("\n---TESTS CASE---")
    do{
        printMenu()
        val op = consoleInput.nextInt()
        when(op){
            1 -> viewDatabase()
            2 ->{
                addTestGame(gamesCounter)
                gamesCounter++
            }
            3 ->{
                deleteGameById()
                gamesCounter--
            }
            4 ->{
                addTestPlan(plansCounter)
                plansCounter++
            }
            5 ->{
                deletePlanById()
                plansCounter--
            }
            6 ->{
                addTestUser(usersCounter)
                usersCounter++
            }
            7 ->{
                deleteUsedById()
                usersCounter--
            }
            8 ->{
                addTestRentedGame(rentsCounter)
                rentsCounter++
            }
            9 ->{
                deleteRentedGameById()
                rentsCounter--
            }
            10 ->{
                println("Exiting...")
            }
        }
    }while(op!=10)
}
fun printMenu(){
    println("\n[1] View database")
    println("[2] Add test game")
    println("[3] Delete game by id")
    println("[4] Add test plan")
    println("[5] Delete plan by id")
    println("[6] Add test user")
    println("[7] Delete user by id")
    println("[8] Add test rented game")
    println("[9] Delete rented game by id")
    println("[10] Exit")
    print("->")
}
fun viewDatabase(){
    val listGames = gamesDAO.getList()
    val listPlans = plansDAO.getList()
    val listRents = rentsDAO.getList()
    val listUsers = usersDAO.getList()
    println("--- USERS ---")
    listUsers.forEach{
        println(it)
    }
    println("--- GAMES ---")
    listGames.forEach{
        println(it)
    }
    println("--- PLANS ----")
    listPlans.forEach{
        println(it)
    }
    println("--- RENTED GAMES ---")
    listRents.forEach {
        println(it)
    }
}
fun addTestGame(counter:Int){
    val testGame = Game("Test title $counter", "Cover $counter", 10.0*counter,
                "Description $counter")
    gamesDAO.add(testGame)
}
fun deleteGameById(){
    print("ID: ")
    val id = consoleInput.nextInt()
    gamesDAO.deleteById(id)
}
fun addTestPlan(counter:Int){
    if (counter==0 || counter%2==0){
        val testPlan = PlanSubscription("Plan $counter", 1.50*counter, 1*counter,
                                        0.01*counter)
        plansDAO.add(testPlan)
    } else {
        val testPlan = PlanSingle("Plan $counter")
        plansDAO.add(testPlan)
    }
}
fun deletePlanById(){
    print("ID: ")
    val id = consoleInput.nextInt()
    plansDAO.deleteById(id)
}
fun addTestUser(counter:Int){
    val testUser = User("User $counter", "user$counter@email.com", "$counter/$counter/$counter",
                        "user_$counter#")
    usersDAO.add(testUser)
}
fun deleteUsedById(){
    print("ID: ")
    val id = consoleInput.nextInt()
    usersDAO.deleteById(id)
}
fun addTestRentedGame(counter:Int){
    val testRentedGame = RentRelation(usersDAO.getById(counter), gamesDAO.getById(counter), TimePeriod())
    rentsDAO.add(testRentedGame)
}
fun deleteRentedGameById(){
    print("ID: ")
    val id = consoleInput.nextInt()
    rentsDAO.deleteById(id)
}