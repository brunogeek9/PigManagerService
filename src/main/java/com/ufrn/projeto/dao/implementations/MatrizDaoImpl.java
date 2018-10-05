package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.IMatrizDao;
import com.ufrn.projeto.model.Matriz;

public class MatrizDaoImpl extends GenericDaoImpl<Matriz, Integer> implements IMatrizDao {
	
	 public MatrizDaoImpl() { 
	        super(Matriz.class); 
	 } 

	 
	 
}