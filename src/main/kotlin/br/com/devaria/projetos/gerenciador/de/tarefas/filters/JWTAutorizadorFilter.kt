package br.com.devaria.projetos.gerenciador.de.tarefas.filters

import br.com.devaria.projetos.gerenciador.de.tarefas.authorization
import br.com.devaria.projetos.gerenciador.de.tarefas.bearer
import br.com.devaria.projetos.gerenciador.de.tarefas.impl.UsuarioDetalheImp
import br.com.devaria.projetos.gerenciador.de.tarefas.models.Usuario
import br.com.devaria.projetos.gerenciador.de.tarefas.repositories.UsuarioRepository
import br.com.devaria.projetos.gerenciador.de.tarefas.utils.JWTUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAutorizadorFilter(authenticationManager : AuthenticationManager, val jwtUtils: JWTUtils, val usuarioRepository: UsuarioRepository)
    : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader(authorization)

        if(authorization !=null && authorization.startsWith(bearer)){
            val autorizado = getAuthetication(authorization)
            SecurityContextHolder.getContext().authentication = autorizado
        }

        chain.doFilter(request, response)
    }

    private fun getAuthetication(authorization: String): UsernamePasswordAuthenticationToken {
        val token = authorization.substring(7)
        if(jwtUtils.isTokenValido(token)) {
            val idString = jwtUtils.getUsuarioId(token)
            if(!idString.isNullOrBlank() && idString.isNullOrEmpty()) {
                val usuario = usuarioRepository.findByIdOrNull(idString.toLong()) ?: throw UsernameNotFoundException("")
                val usuarioImpl = UsuarioDetalheImp(usuario)
                return UsernamePasswordAuthenticationToken(usuarioImpl, null, usuarioImpl.authorities)
            }
        }

        throw UsernameNotFoundException("Token informado n??o est?? valido, " +
                "ou n??o tem uma informa????o de identifica????o do usu??rio")
    }
}