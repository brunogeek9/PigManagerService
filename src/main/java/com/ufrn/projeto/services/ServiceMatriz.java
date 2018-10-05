/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.services;

import com.ufrn.projeto.dao.implementations.MatrizDaoImpl;
import com.ufrn.projeto.dao.interfaces.IMatrizDao;
import com.ufrn.projeto.exceptions.CustomNoContentException;
import com.ufrn.projeto.exceptions.OutputMessage;
import com.ufrn.projeto.model.Matriz;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.criterion.Order;


@Path("/matriz")
public class ServiceMatriz {
      
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Matriz matriz){         
        try{
            IMatrizDao matrizDAO = new MatrizDaoImpl();    	
            matrizDAO.save(matriz);
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,e.getMessage()))
                    .build();
        }
        
        return Response
                .status(Response.Status.CREATED)
                .entity(matriz)
                .build();
    }
    
    
    @DELETE
    //@Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id/*, @Context SecurityContext securityContext*/) throws CustomNoContentException {
        
        IMatrizDao matrizDao = new MatrizDaoImpl(); 
        Matriz obj = matrizDao.findById(id);

        if (obj == null){
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
        try{
            matrizDao.delete(obj);
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,e.getMessage()))
                    .build();
        }
        
        return Response
                .status(Response.Status.OK)
                .entity(new OutputMessage(200,"Objeto removido."))
                //.entity(new OutputMessage(200,"Objeto removido por "+ securityContext.getUserPrincipal().getName()))
                .build();
    }
    
    
    /*
    @DELETE
    @Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) throws CustomNoContentException {
        
        IMatrizDao matrizDao = new MatrizDaoImpl(); 
        Matriz obj = matrizDao.findById(id);

        if (obj == null){
            throw new CustomNoContentException();
        }

        matrizDao.delete(obj); 
    
        return Response
                .status(Response.Status.OK)
                .entity(new OutputMessage(200,"Objeto deletado."))
                .build();
    }*/
    
    

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Matriz matriz){
        try{
            IMatrizDao matrizDao = new MatrizDaoImpl();    	
            matrizDao.save(matriz);
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,e.getMessage()))
                    .build();
        }
        
        return Response
                .status(Response.Status.OK)
                .entity(matriz)
                .build();
    }
    

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listById(@PathParam("id") int id){
        try{
            IMatrizDao matrizDao = new MatrizDaoImpl();    
            Matriz obj = matrizDao.findById(id);
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
            IMatrizDao matrizDao = new MatrizDaoImpl(); 
            List<Matriz> obj;
            if(sort.equals("desc")){
                obj = matrizDao.findAll(Order.desc(orderBy));
            }else{
                obj = matrizDao.findAll(Order.asc(orderBy));
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
            IMatrizDao matrizDao = new MatrizDaoImpl(); 
            List<Matriz> obj;
            if(sort.equals("desc")){
                obj = matrizDao.findAllBy("value", value, Order.desc(orderBy));
            }else{
                obj = matrizDao.findAllBy("value", value, Order.asc(orderBy));
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
