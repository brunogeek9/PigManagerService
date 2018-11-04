package com.ufrn.projeto.dao.interfaces;

import com.ufrn.projeto.model.Estagio;

public interface IEstagioMatrizDao extends IGenericDao<Estagio, Integer>{
    public Estagio findByEstagio(String estagio, int id);
}
