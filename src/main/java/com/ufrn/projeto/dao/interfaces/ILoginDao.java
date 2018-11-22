package com.ufrn.projeto.dao.interfaces;

import com.ufrn.projeto.model.Login;

public interface ILoginDao extends IGenericDao<Login, Integer>{
    
    public Login validUser(String userName, String pass);
	
    public Login findByTokenUser(String token);   
    
    public Login verificarUsuario(String token);
}
