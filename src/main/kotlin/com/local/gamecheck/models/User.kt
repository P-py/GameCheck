package com.local.gamecheck.models

import java.util.Scanner
import kotlin.random.Random

data class User(var nome:String, var email:String): Suggest {
    var dataNascimento:String? = null
    var usuario:String? = null
        set(value) {
            field = value
            if(idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }
    var id = 0
    var idInterno:String? = null
        private set
    var plan: Plan = PlanSingle("BRONZE")
    val jogosBuscados = mutableListOf<Game?>()
    val jogosAlugados = mutableListOf<RentRelation>()
    private val listaNotas = mutableListOf<Int>()
    val jogosRecomendados = mutableListOf<Game>()

    override val media: Double
        get() = listaNotas.average()

    override fun recomendar(nota: Int) {
        listaNotas.add(nota)
    }

    fun recomendarJogo(game: Game, nota: Int) {
        game.recomendar(nota)
        jogosRecomendados.add(game)
    }

    constructor(nome: String, email: String, dataNascimento:String?, usuario:String?, id: Int = 0):
            this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        this.id = id
        criarIdInterno()
    }

    init {
        if (nome.isBlank()) {
            throw IllegalArgumentException("Nome está em branco")
        }
        this.email = validarEmail()
    }

    override fun toString(): String {
        return "Gamer:\n" +
                "Nome: $nome\n" +
                "Email: $email\n" +
                "Data Nascimento: $dataNascimento\n" +
                "Usuario: $usuario\n" +
                "IdInterno: $idInterno\n" +
                "Reputação: $media\n" +
                "Id: $id\n" +
                "Plano: ${plan.tipo}\n"
    }

    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email inválido")
        }
    }

    fun alugaJogo(game: Game, timePeriod: TimePeriod): RentRelation {
        val rent = RentRelation(this, game, timePeriod)
        jogosAlugados.add(rent)

        return rent
    }

    fun jogosDoMes(mes:Int): List<Game> {
        return jogosAlugados
            .filter { aluguel ->  aluguel.timePeriod.dataInicial.monthValue == mes}
            .map { aluguel ->  aluguel.game}
    }

    companion object {
        fun createUser(leitura: Scanner): User {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return User(nome, email, nascimento, usuario)
            } else {
                return User (nome, email)
            }

        }
    }

}
