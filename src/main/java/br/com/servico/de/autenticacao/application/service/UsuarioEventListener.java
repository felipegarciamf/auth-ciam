package br.com.servico.de.autenticacao.application.service;


import br.com.servico.de.autenticacao.application.dto.EventoUsuarioDto;
import br.com.servico.de.autenticacao.domain.entity.AuthUser;
import br.com.servico.de.autenticacao.infra.repository.AuthUserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEventListener {

    private final AuthUserRepository authUserRepository;

    public UsuarioEventListener(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }


    @KafkaListener(topics = "user-created-topic", groupId = "auth-service-group")
    public void consumeUsuarioCriado(EventoUsuarioDto evento){
        AuthUser authUser = new AuthUser();
        authUser.setEmail(evento.email());
        authUser.setSenha(evento.senha());
        authUserRepository.save(authUser);

        System.out.println("Usuário salvo no auth DB: " + evento.email());
    }

}
