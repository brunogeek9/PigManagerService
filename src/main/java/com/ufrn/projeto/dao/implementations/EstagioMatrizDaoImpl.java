package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.IEstagioMatrizDao;
import com.ufrn.projeto.model.Estagio;
import com.ufrn.projeto.model.enums.EnumEstagio;
import com.ufrn.projeto.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EstagioMatrizDaoImpl extends GenericDaoImpl<Estagio, Integer> implements IEstagioMatrizDao {
	
	 public EstagioMatrizDaoImpl() { 
	        super(Estagio.class); 
	 } 

         @Override
	 public Estagio findByEstagio(String estagio, int id){
             try{
                Session sessao = HibernateUtil.getSessionFactory().openSession();
                String sql = "SELECT e FROM Estagio e WHERE estagio = :estagio AND e.matriz.id = :id";
                Query query = sessao.createQuery(sql);
                query.setParameter("estagio", EnumEstagio.valueOf(estagio));
                query.setParameter("id", id);
//                Depois verificar qual dos dois usar, ja que é um relacionamento ManytoOne
//                Estagio es = (Estagio) query.list().iterator().next();
                Estagio es = (Estagio) query.uniqueResult();
                sessao.close();
                return es;
             }catch(HibernateException e){
                 System.out.println("ERRO ENCONTRANDO ESTAGIO");
             }
             return  null;
         }
	 
}