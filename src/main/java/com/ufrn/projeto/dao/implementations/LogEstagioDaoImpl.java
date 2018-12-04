package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.model.LogEstagio;
import com.ufrn.projeto.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.ufrn.projeto.dao.interfaces.ILogEstagioDao;

public class LogEstagioDaoImpl extends GenericDaoImpl<LogEstagio, Integer> implements ILogEstagioDao {
	
    private Session session = null;

    public LogEstagioDaoImpl() { 
           super(LogEstagio.class); 
    } 

    @Override
    public String findByEstagio(int id){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();
            String hql = "Select e.estagio from LogEstagio e where e.data_cadastro = "
                    + "(SELECT MAX(e2.data_cadastro) FROM LogEstagio e2 WHERE e2.matriz_idMatriz = :idMatriz)";
            Query query = session.createQuery(hql);
            query.setParameter("idMatriz", id);
            String result = query.uniqueResult().toString();
            t.commit();
            return result;
        }catch (HibernateException e){
            System.out.println("Erro listando: " + e);
        }finally{
            session.close();
        }
        return null;
    }   

}