package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.IGenericDao;
import com.ufrn.projeto.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class GenericDaoImpl<T, ID extends Serializable> implements IGenericDao<T, ID> { 

    private Class<T> klass; 
    private Session session = null;

    public GenericDaoImpl() {} 
    public GenericDaoImpl(Class<T> klass) {
        this.klass = klass; 
    }

    @Override
    public T findById(ID id) {
        T object = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            object = (T) session.get(klass, id);
        }catch (HibernateException e){
        }finally{
            session.close();
        }
        return object;
    } 

    @Override
    public void save(T object) { 
        Transaction t = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.saveOrUpdate( (T) object);
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
    @Override
    public void delete(T object) {
        Transaction t = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.delete( (T) object);
            t.commit();
        }catch (HibernateException e){
            System.out.println("Erro deletando: " + e);
            if(t != null) {
                t.rollback();
            }
        }finally{
            session.close();
        }
    }
	
    @Override
    public List<T> listAll() {
        Transaction t = session.beginTransaction();

    	CriteriaBuilder builder = session.getCriteriaBuilder();        
        CriteriaQuery<T> query = builder.createQuery(klass);
        
        Root<T> klassRoot = query.from(klass);
        
        query.select(klassRoot);     	
        
        List<T> result = session.createQuery(query).getResultList();
        t.commit();
        return result;
        
    }
    
    @Override
    public List<T> findAll() { 
        return findAll(null); 
    } 

    @Override
    public List<T> findAll(Order order) {
        Transaction t = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(klass); 
            if (order != null) { 
                criteria.addOrder(order); 
            } 
            return (List<T>) criteria.list(); 
        }catch (HibernateException e){
            System.out.println("Erro listando: " + e);
        }finally{
            session.close();
        }
        return null;
    } 
    
    
    //Buscas avançadas    
    @Override
    public List<T> findAllBy(String property, List<?> values) { 
    	return findAllBy(property, values, null); 
    } 

    @Override
    public List<T> findAllBy(String property, List<?> values, Order order) { 
        Transaction t = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
             Criteria criteria = session.createCriteria(klass) 
                        .add(Restrictions.in(property, values)); 
            if (order != null) { 
                    criteria.addOrder(order); 
            }
            return (List<T>) criteria.list(); 
        }catch (HibernateException e){
            System.out.println("Erro deletando: " + e);
        }finally{
            session.close();
        }
        return null;
        } 
}
