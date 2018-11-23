/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.util;

import com.ufrn.projeto.dao.implementations.EstagioMatrizDaoImpl;
import com.ufrn.projeto.dao.interfaces.IEstagioMatrizDao;
import org.hibernate.Session;

/**
 *
 * @author jamelli
 */
public class TesteHibernate {
    public static void main(String args[]){
        Session sessao = null;
        IEstagioMatrizDao estagioDao = new EstagioMatrizDaoImpl();
        String estagio = estagioDao.getCurrentStage(1);
        System.out.println("estagio :"+estagio);
    }
    
                    
    
}
