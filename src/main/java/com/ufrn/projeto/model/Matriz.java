package com.ufrn.projeto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrn.projeto.model.enums.EnumEstagio;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "matriz")
public class Matriz implements Serializable{
    
    @Id
    @Column(name = "idMatriz")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true) 
    private String identificador;
    
    @Column(name = "raca", nullable = false)
    @NotEmpty(message = "Por favor, insira a raça da matriz!")
    private String raca;

    @Column(name = "peso", nullable = false)  
    @NotNull(message = "Por favor, informe o peso da matriz!")
    private double peso;

    @Column(name = "estagio", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, informe o tipo de estagio da matriz!")
    private EnumEstagio estagio;
    //private LogEstagio estagio;
    
    @Column(name = "data_nascimento", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Brazil/East")
    private Date dataNascimento;
    
    //AQUI É A FOTO == HISTÓRICO
    @Column(name = "arquivo", nullable = true)
    @NotEmpty(message = "Por favor, insira uma imagem!")
    private String arquivo;
    
    @Column(nullable = false)
    private boolean ativo = true;
   
    public Matriz() {
    }

    public Matriz(String identificador, String raca, double peso, EnumEstagio estagio, Date dataNascimento, String arquivo) {
        this.identificador = identificador;
        this.raca = raca;
        this.peso = peso;
        this.estagio = estagio;
        this.dataNascimento = dataNascimento;
        this.arquivo = arquivo;
    }   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public EnumEstagio getEstagio() {
        return estagio;
    }

    public void setEstagio(EnumEstagio estagio) {
        this.estagio = estagio;
    }     
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Matriz other = (Matriz) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
