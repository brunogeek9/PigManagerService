package com.ufrn.projeto.model;

import com.ufrn.projeto.util.TokenUtil;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "login")
public class Login implements Serializable{
    
    @Id
    @Column(name = "id_login")
    @org.hibernate.annotations.GenericGenerator(name = "fk_login_id_usuaruio",
    strategy = "foreign", parameters = @Parameter(name = "property", value = "usuario"))
    @GeneratedValue(generator = "fk_login_id_usuaruio")
    private Integer id;
    
    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotNull(message = "Por favor, informe o email!")
    private String email;
    
    @Column(name = "senha", nullable = false)
    @NotNull(message = "Por favor, informe a senha!")
    private String senha;
    
    @Column
    private String token;
    
    @OneToOne
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Usuario usuario;
    
    @Column(nullable = false)
    private boolean ativo = true;    

    public Login() {
    }

    public Login(String email, String senha, String token, Usuario usuario) {
        this.email = email;
        this.senha = senha;
        this.token = token;
        this.usuario = usuario;
    }
    
    public void login() {
        setToken(TokenUtil.criaToken(email));
    }

    public void logout() {
        setToken(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Login other = (Login) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
