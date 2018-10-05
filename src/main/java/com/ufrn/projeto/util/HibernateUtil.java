/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.util;

import com.ufrn.projeto.model.Matriz;
import com.ufrn.projeto.model.Temperatura;
import com.ufrn.projeto.model.Usuario;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 *
 * @author Taniro
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
    
            cfg.addAnnotatedClass(Temperatura.class);
            cfg.addAnnotatedClass(Matriz.class);
            cfg.addAnnotatedClass(Usuario.class);

            StandardServiceRegistryBuilder registradorServico = new StandardServiceRegistryBuilder();
            registradorServico.applySettings(cfg.getProperties());
            StandardServiceRegistry servico = registradorServico.build();

            return cfg.buildSessionFactory(servico);
        } catch (Throwable e) {
            System.out.println("Criação inicial do objeto SessionFactory falhou. Erro: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}