package com.ufrn.projeto.services;

import com.ufrn.projeto.dao.implementations.EstagioMatrizDaoImpl;
import com.ufrn.projeto.dao.interfaces.IEstagioMatrizDao;
import com.ufrn.projeto.exceptions.OutputMessage;
import com.ufrn.projeto.model.Estagio;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.criterion.Order;


@Path("/logEstagio")
public class ServiceEstagioMatriz {      
   
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Estagio estagio){         
        try{
            IEstagioMatrizDao estagioMatrizDao = new EstagioMatrizDaoImpl();    	
            estagioMatrizDao.save(estagio);            
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,e.getMessage()))
                    .build();
        }       
       
        return Response
                .status(Response.Status.CREATED)
                .entity(estagio)
                .build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listById(@PathParam("id") int id){
        try{
            IEstagioMatrizDao estagioMatrizDao = new EstagioMatrizDaoImpl();    
            Estagio obj = estagioMatrizDao.findById(id);
            if (obj == null){
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();
                
            }else{
                return Response
                    .status(Response.Status.OK)
                    .entity(obj)
                    .build();
            }
        }catch (Exception e){
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new OutputMessage(500,e.getMessage()))
                .build();
        }
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(
            @QueryParam("orderby") @DefaultValue("id") String orderBy,
            @QueryParam("sort") @DefaultValue("asc") String sort){
        try{
            IEstagioMatrizDao estagioMatrizDao = new EstagioMatrizDaoImpl();    
            List<Estagio> obj;
            if(sort.equals("desc")){
                obj = estagioMatrizDao.findAll(Order.desc(orderBy));
            }else{
                obj = estagioMatrizDao.findAll(Order.asc(orderBy));
            }
            if (obj == null){
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();
            }else{
                return Response
                    .status(Response.Status.OK)
                    .entity(obj)
                    .build();
            }
        }catch (Exception e){
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new OutputMessage(500,e.getMessage()))
                .build();
        }        
    }
    
    @GET
    @Path("/value")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllQuery(
            @QueryParam("orderby") @DefaultValue("id") String orderBy,
            @QueryParam("sort") @DefaultValue("asc") String sort, 
            @QueryParam("v") List<Double> value){
        
        if (value.isEmpty()){
            return listAll(orderBy, sort);
        }
        try{
            IEstagioMatrizDao estagioMatrizDao = new EstagioMatrizDaoImpl();  
            List<Estagio> obj;
            if(sort.equals("desc")){
                obj = estagioMatrizDao.findAllBy("value", value, Order.desc(orderBy));
            }else{
                obj = estagioMatrizDao.findAllBy("value", value, Order.asc(orderBy));
            }
            if (obj.isEmpty()){
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();
            }else{
                return Response
                    .status(Response.Status.OK)
                    .entity(obj)
                    .build();
            }
        }catch (Exception e){
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new OutputMessage(500,e.getMessage()))
                .build();
        }        
    }   
}
