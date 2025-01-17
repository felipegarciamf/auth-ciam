package br.com.servico.de.autenticacao.application.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/publico")
    public String publico(){
        return "este é seu endpoint";
    }

    @GetMapping("/protegido")
    public String protegido(){
        return "este é seu endpoint protegido por autenticaçao";
    }

}
