package br.com.devaria.projetos.gerenciador.de.tarefas.dtos

data class LoginRespostaDto (val nome: String, val email: String, val token: String = "")
