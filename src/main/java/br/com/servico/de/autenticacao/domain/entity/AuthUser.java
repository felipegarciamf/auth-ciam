package br.com.servico.de.autenticacao.domain.entity;


import jakarta.persistence.*;

@Table(name =  "auth_users")
@Entity
public class AuthUser {

    public AuthUser(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public AuthUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
