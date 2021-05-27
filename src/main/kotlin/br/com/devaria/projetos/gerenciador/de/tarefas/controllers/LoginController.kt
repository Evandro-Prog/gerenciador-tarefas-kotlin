package br.com.devaria.projetos.gerenciador.de.tarefas.controllers

import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.ErroDTO
import br.com.devaria.projetos.gerenciador.de.tarefas.dtos.LoginDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/login")
class LoginController {

    @PostMapping
    fun efetuarLogin(@RequestBody objeto : LoginDTO) : ResponseEntity<Any>{
        try{
            throw RuntimeException("Testando uma exceção")
        }catch (e: Exception){
            return ResponseEntity(ErroDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Não foi possível efetuar o login, tente novamente"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}