package com.ufrn.projeto.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
      
    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    @NotEmpty(message = "Por favor, insira seu nome!")
    private String nome;   
    
    @Column(name = "nome_usuario", nullable = false)
    @NotEmpty(message = "O nome de usuário não pode ser nulo")
    private String nomeUsuario;
   
    @Column(nullable = false)
    private boolean ativo = true;  
    
    public Usuario() {
    }

    public Usuario(String nome, String nomeUsuario) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;

    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
            return nomeUsuario;
    }
    
    public void setNomeUsuario(String nomeUsuario) {
            this.nomeUsuario = nomeUsuario;
    }  
   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    
}
