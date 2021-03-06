package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.IMatrizDao;
import com.ufrn.projeto.model.Matriz;
import com.ufrn.projeto.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MatrizDaoImpl extends GenericDaoImpl<Matriz, Integer> implements IMatrizDao {
	
    public MatrizDaoImpl() { 
        super(Matriz.class); 
    } 

    public List<Matriz> findByEstagio(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT m FROM Matriz m ORDER BY estagio";
        Query q = sessao.createQuery(sql);
        return q.list();
    };
    @Override
    public void save(Matriz m){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.saveOrUpdate(m);
            t.commit();
        }catch (HibernateException e){
            System.out.println("Erro salvando: " + e);
            if(t != null) {
                t.rollback();
            }
        }finally{
            session.close();
        }
    }

	 
	 
}