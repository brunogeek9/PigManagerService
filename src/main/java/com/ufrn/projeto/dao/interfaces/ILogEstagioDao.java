package com.ufrn.projeto.dao.interfaces;

import com.ufrn.projeto.model.LogEstagio;

public interface ILogEstagioDao extends IGenericDao<LogEstagio, Integer>{
    
    public String findByEstagio(int id);
    
}
