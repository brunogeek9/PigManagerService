package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.IUsuarioDao;
import com.ufrn.projeto.model.Usuario;

public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer> implements IUsuarioDao {
	
    public UsuarioDaoImpl() { 
           super(Usuario.class); 
    }          
  
}