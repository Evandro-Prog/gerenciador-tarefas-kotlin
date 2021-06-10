package br.com.devaria.projetos.gerenciador.de.tarefas.models

import javax.persistence.Entity
import javax.persistence.*


@Entity
data class Usuario (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val nome: String = "",
    val email: String = "",
    var senha: String = "")