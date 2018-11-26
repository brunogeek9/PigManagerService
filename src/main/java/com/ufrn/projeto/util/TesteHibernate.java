/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.util;

import com.ufrn.projeto.dao.implementations.EstagioMatrizDaoImpl;
import com.ufrn.projeto.dao.implementations.MatrizDaoImpl;
import com.ufrn.projeto.dao.interfaces.IEstagioMatrizDao;
import com.ufrn.projeto.dao.interfaces.IMatrizDao;
import com.ufrn.projeto.model.LogEstagio;
import com.ufrn.projeto.model.Matriz;
import com.ufrn.projeto.model.enums.EnumEstagio;
import org.hibernate.Session;

/**
 *
 * @author jamelli
 */
public class TesteHibernate {
    public static void main(String args[]){
        /*
        Session sessao = null;
        IEstagioMatrizDao estagioDao = new EstagioMatrizDaoImpl();
        IMatrizDao mDao = new MatrizDaoImpl();
        
        Matriz m = mDao.findById(2);
        
        LogEstagio log = new LogEstagio(m,EnumEstagio.VAZIA);
        System.out.println(m.toString()+" "+log.toString());
        estagioDao.save(log);
        */
        IEstagioMatrizDao estagioDao = new EstagioMatrizDaoImpl();
        //estagioDao.saveLog(3, EnumEstagio.VAZIA);
        String teste = estagioDao.getCurrentStage(2);
                
                
    }
    
                    
    
}
