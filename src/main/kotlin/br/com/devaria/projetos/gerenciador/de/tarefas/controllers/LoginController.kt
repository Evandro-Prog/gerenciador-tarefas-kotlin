package br.com.devaria.projetos.gerenciador.de.tarefas.controllers

import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.ErroDTO
import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.LoginDTO
import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.LoginRespostaDto
import br.com.devaria.projetos.gerenciador.de.tarefas.extensions.md5
import br.com.devaria.projetos.gerenciador.de.tarefas.extensions.toHex
import br.com.devaria.projetos.gerenciador.de.tarefas.repositories.UsuarioRepository
import br.com.devaria.projetos.gerenciador.de.tarefas.utils.JWTUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/login")
class LoginController (val usuarioRepository: UsuarioRepository) {



    @PostMapping
    fun efetuarLogin(@RequestBody dto : LoginDTO) : ResponseEntity<Any>{
        try{
            if(dto == null || dto.login.isNullOrBlank() || dto.login.isNullOrEmpty()
                || dto.senha.isNullOrEmpty() || dto.senha.isNullOrBlank()){
                return ResponseEntity(ErroDTO(HttpStatus.BAD_REQUEST.value(),
                    "Parâmetros de entrada inválidos"), HttpStatus.BAD_REQUEST)
            }

            var usuario = usuarioRepository.findByEmail((dto.login))

            if(usuario == null || usuario.senha != md5(dto.senha).toHex()){
                return ResponseEntity(ErroDTO(HttpStatus.BAD_REQUEST.value(),
                    "Parâmetros de entrada inválidos"), HttpStatus.BAD_REQUEST)
            }

            val idUsuario = 1
            val token = JWTUtils().gerarToken(idUsuario.toString())

            val usuarioTeste = LoginRespostaDto(usuario.nome, usuario.email, token)
            return ResponseEntity(usuarioTeste, HttpStatus.OK)
        }catch (e: Exception){
            return ResponseEntity(ErroDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Não foi possível efetuar o login, tente novamente"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}