package br.com.devaria.projetos.gerenciador.de.tarefas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class GerenciadorDeTarefasApplication

fun main(args: Array<String>) {
	runApplication<GerenciadorDeTarefasApplication>(*args)
}
