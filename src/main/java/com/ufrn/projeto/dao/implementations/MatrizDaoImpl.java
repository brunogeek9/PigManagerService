package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.IMatrizDao;
import com.ufrn.projeto.model.Matriz;
import com.ufrn.projeto.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
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

	 
	 
}