package com.ufrn.projeto.dao.implementations;

import com.ufrn.projeto.dao.interfaces.ITemperaturaDao;
import com.ufrn.projeto.model.Temperatura;

public class TemperaturaDaoImpl extends GenericDaoImpl<Temperatura, Integer> implements ITemperaturaDao {
	
	 public TemperaturaDaoImpl() { 
	        super(Temperatura.class); 
	 } 

	 
	 
}