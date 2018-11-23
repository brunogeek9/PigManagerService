package com.ufrn.projeto.dao.interfaces;

import com.ufrn.projeto.model.LogEstagio;
//import com.ufrn.projeto.model.Matriz;

public interface IEstagioMatrizDao extends IGenericDao<LogEstagio, Integer>{
    public LogEstagio findByEstagio(String estagio, int id);
    public String getCurrentStage(int id);
    //public LogEstagio buscarMatriz(Matriz matriz);
    
}
