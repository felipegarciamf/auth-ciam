package br.com.servico.de.autenticacao.infra.repository;

import br.com.servico.de.autenticacao.domain.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
}
