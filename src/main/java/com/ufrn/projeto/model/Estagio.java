package com.ufrn.projeto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ufrn.projeto.model.enums.EnumEstagio;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estagio")
public class Estagio implements Serializable{
      
    @Id
    @Column(name = "idestagio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Matriz matriz;
    
    @Column(name = "estagio", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, informe o tipo de estagio da matriz!")
    private EnumEstagio estagio;

    public Estagio() {
    }

    public Estagio(Matriz matriz, EnumEstagio estagio) {
        this.matriz = matriz;
        this.estagio = estagio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public EnumEstagio getEstagio() {
        return estagio;
    }

    public void setEstagio(EnumEstagio estagio) {
        this.estagio = estagio;
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
        final Estagio other = (Estagio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    
}
