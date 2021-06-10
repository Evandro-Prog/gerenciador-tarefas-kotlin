package br.com.devaria.projetos.gerenciador.de.tarefas.repositories

import br.com.devaria.projetos.gerenciador.de.tarefas.models.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
}