package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.ILoginDao;
import com.ufrn.projeto.model.Login;
import com.ufrn.projeto.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

public class LoginDaoImpl extends GenericDaoImpl<Login, Integer> implements ILoginDao{
    
    public LoginDaoImpl() { 
           super(Login.class); 
    }       
    
    @SuppressWarnings("deprecation")
    @Override
    public Login validUser(String email, String senha) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria consulta = session.createCriteria(Login.class);
            consulta.add(Restrictions.eq("email", email))
                    .add(Restrictions.eq("senha", senha));
            Login resultado = (Login) consulta.uniqueResult();

            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            session.close();
        }
    }


    @SuppressWarnings({ "deprecation", "rawtypes" })
    @Override
    public Login findByTokenUser(String token) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Login login = new Login();
        String tokenClean = token.trim();
        try {
            Query query = session.getSession().createQuery(
                            "SELECT c FROM Login c "
                            + " WHERE c.token = :tokenClean");

            query.setParameter("tokenClean", tokenClean);

            login = (Login) query.uniqueResult();

            return login;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            session.close();
        }
    }
    
     public Login verificarUsuario(String token) {
        Session sessao = null;
        Login resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            resultado = (Login) sessao.createCriteria(Login.class)
                    //.add(Restrictions.idEq(usuario))
                    .add(Restrictions.eq("token", token))
                    //.add(Restrictions.eq("ativo", true))
                    .uniqueResult();

        } catch (HibernateException e) {
            sessao.close();
            return resultado;
        }
        sessao.close();
        return resultado;
    }
}
