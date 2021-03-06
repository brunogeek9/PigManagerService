package com.ufrn.projeto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrn.projeto.model.enums.EnumEstagio;
import com.ufrn.projeto.util.DataUtil;
import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "logEstagio")
public class LogEstagio implements Serializable{
      
    @Id
    @Column(name = "idestagio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_cadastro", nullable = true)
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Brazil/East")
    private Date dataCadastro;
    
    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Matriz matriz;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Por favor, informe o tipo de estagio da matriz!")
    private EnumEstagio estagio;

    public LogEstagio() {
        //java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        //java.sql.T
        this.dataCadastro = DataUtil.currentDate();
    }

    public LogEstagio(Matriz matriz, EnumEstagio estagio) {
        this.matriz = matriz;
        this.estagio = estagio;
        this.dataCadastro = DataUtil.currentDate();
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

    public Date getDataCadastro() {
        Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        return (this.dataCadastro == null) ? now : this.dataCadastro;
        // return (this.stringValue == null) ? "Default" : stringValue;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final LogEstagio other = (LogEstagio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }  
}
