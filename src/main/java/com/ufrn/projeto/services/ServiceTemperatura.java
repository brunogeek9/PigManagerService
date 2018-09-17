/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.services;

import com.ufrn.projeto.dao.implementations.TemperaturaDaoImpl;
import com.ufrn.projeto.dao.interfaces.ITemperaturaDao;
import com.ufrn.projeto.exceptions.CustomNoContentException;
import com.ufrn.projeto.exceptions.OutputMessage;
import com.ufrn.projeto.model.Temperatura;
import com.ufrn.projeto.security.Secured;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.hibernate.criterion.Order;

/**
 *
 * @author Taniro
 */

@Path("/temperatura")
public class ServiceTemperatura {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Temperatura t){         
        try{
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImpl();    	
            temperaturaDAO.save(t);
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,e.getMessage()))
                    .build();
        }
        
        return Response
                .status(Response.Status.CREATED)
                .entity(t)
                .build();
    }
    
    
    @DELETE
    //@Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id/*, @Context SecurityContext securityContext*/) throws CustomNoContentException {
        
        ITemperaturaDao temperaturaDAO = new TemperaturaDaoImpl(); 
        Temperatura obj = temperaturaDAO.findById(id);

        if (obj == null){
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
        try{
            temperaturaDAO.delete(obj);
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
        
        ITemperaturaDao temperaturaDAO = new TemperaturaDaoImpl(); 
        Temperatura obj = temperaturaDAO.findById(id);

        if (obj == null){
            throw new CustomNoContentException();
        }

        temperaturaDAO.delete(obj); 
    
        return Response
                .status(Response.Status.OK)
                .entity(new OutputMessage(200,"Objeto deletado."))
                .build();
    }*/
    
    

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Temperatura t){
        try{
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImpl();    	
            temperaturaDAO.save(t);
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,e.getMessage()))
                    .build();
        }
        
        return Response
                .status(Response.Status.OK)
                .entity(t)
                .build();
    }
    

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listById(@PathParam("id") int id){
        try{
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImpl(); 
            Temperatura obj = temperaturaDAO.findById(id);
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
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImpl(); 
            List<Temperatura> obj;
            if(sort.equals("desc")){
                obj = temperaturaDAO.findAll(Order.desc(orderBy));
            }else{
                obj = temperaturaDAO.findAll(Order.asc(orderBy));
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
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImpl(); 
            List<Temperatura> obj;
            if(sort.equals("desc")){
                obj = temperaturaDAO.findAllBy("value", value, Order.desc(orderBy));
            }else{
                obj = temperaturaDAO.findAllBy("value", value, Order.asc(orderBy));
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
