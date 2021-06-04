package br.com.devaria.projetos.gerenciador.de.tarefas.impl

import br.com.devaria.projetos.gerenciador.de.tarefas.models.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails as UserDetails

class UsuarioDetalheImp (private val usuario: Usuario) : UserDetails {

    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun getPassword() = usuario.senha

    override fun getUsername() = usuario.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}