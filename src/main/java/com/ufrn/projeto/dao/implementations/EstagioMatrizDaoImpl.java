package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.IEstagioMatrizDao;
import com.ufrn.projeto.dao.interfaces.IMatrizDao;
import com.ufrn.projeto.model.LogEstagio;
import com.ufrn.projeto.model.Matriz;
import com.ufrn.projeto.model.enums.EnumEstagio;
import com.ufrn.projeto.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

public class EstagioMatrizDaoImpl extends GenericDaoImpl<LogEstagio, Integer> implements IEstagioMatrizDao {
	
        private Session session = null;
           
        
        public EstagioMatrizDaoImpl() { 
               super(LogEstagio.class); 
        } 

        @Override
        public LogEstagio findByEstagio(String estagio, int id){
            try{
               Session sessao = HibernateUtil.getSessionFactory().openSession();
               String sql = "SELECT e FROM logestagio e WHERE estagio = :estagio AND e.matriz.id = :id";
               Query query = sessao.createQuery(sql);
               query.setParameter("estagio", EnumEstagio.valueOf(estagio));
               query.setParameter("id", id);
//             Depois verificar qual dos dois usar, ja que é um relacionamento ManytoOne
//             Estagio es = (Estagio) query.list().iterator().next();
               LogEstagio es = (LogEstagio) query.uniqueResult();
               sessao.close();
               return es;
            }catch(HibernateException e){
                System.out.println("ERRO ENCONTRANDO ESTAGIO");
            }
            return  null;
        }
        
        @Override
        public String getCurrentStage(int id){
            Transaction t = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(LogEstagio.class)
                    .add(Restrictions.eq("matriz", id)); 
             
            return criteria.uniqueResult().toString(); 
        }catch (HibernateException e){
            System.out.println("Erro listando: " + e);
        }finally{
            session.close();
        }
        return null;
            
        }
        
        

//    @Override
//    public LogEstagio buscarMatriz(Matriz matriz) {
//        LogEstagio resultado = null;
//
//        try {
//            sessao = HibernateUtil.getSessionFactory().openSession();
//            
//            String sql = "SELECT m FROM logestagio, matriz m WHERE logestagio = :logestagio AND m.ativo = true";           
//            Query query = sessao.createQuery(sql);    
//            query.setParameter("matriz", matriz);
//            resultado = (LogEstagio) query.uniqueResult();
//            
//          
//            System.out.println("resultado: " + resultado);
////            resultado = (LogEstagio) sessao.createCriteria(LogEstagio.class)
////                    .add(Restrictions.eq("matriz", matriz))
////                    .add(Restrictions.eq("ativo", true))
////                    .uniqueResult();
//
//        } catch (HibernateException e) {
//            sessao.close();
//            return resultado;
//        }
//        sessao.close();
//        return resultado;
//    }
      
      @Override
      public void saveLog(int id, EnumEstagio e){
          IEstagioMatrizDao estagioDao = new EstagioMatrizDaoImpl();
          IMatrizDao mDao = new MatrizDaoImpl();
          
          Matriz m = mDao.findById(id);
          LogEstagio log = new LogEstagio(m,e);
          estagioDao.save(log);
          
      }
	 
}