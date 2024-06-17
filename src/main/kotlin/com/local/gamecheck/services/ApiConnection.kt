package com.local.gamecheck.services

import com.local.gamecheck.models.User
import com.local.gamecheck.models.InfoUserJson
import com.local.gamecheck.models.InfoGame
import com.local.gamecheck.models.InfoGameJson
import com.local.gamecheck.models.Game
import com.local.gamecheck.util.createUser
import com.local.gamecheck.util.createGame
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiConnection {

    private fun consomeDados(endereco: String): String {
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        return response.body()
    }

    fun buscaJogo(id:String): InfoGame {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val json = consomeDados(endereco)

        val gson = Gson()
        val myInfoGame = gson.fromJson(json, InfoGame::class.java)

        return myInfoGame

    }

    fun buscaGamers(): List<User> {
        val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"
        val json = consomeDados(endereco)

        val gson = Gson()
        val meuGamerTipo = object : TypeToken<List<InfoUserJson>>() {}.type
        val listaGamer:List<InfoUserJson> = gson.fromJson(json, meuGamerTipo)

        val listaGamerConvertida = listaGamer.map { infoGamerJson -> infoGamerJson.createUser() }

        return listaGamerConvertida
    }

    fun buscaJogosJson(): List<Game> {
        val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/jogos.json"
        val json = consomeDados(endereco)

        val gson = Gson()
        val meuJogoTipo = object : TypeToken<List<InfoGameJson>>() {}.type
        val listaJogo: List<InfoGameJson> = gson.fromJson(json, meuJogoTipo)

        val listaJogoConvertida = listaJogo.map { infoJogoJson -> infoJogoJson.createGame() }

        return listaJogoConvertida
    }

}