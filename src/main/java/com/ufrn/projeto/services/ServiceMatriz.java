package com.ufrn.projeto.services;

import com.ufrn.projeto.dao.implementations.EstagioMatrizDaoImpl;
import com.ufrn.projeto.dao.implementations.MatrizDaoImpl;
import com.ufrn.projeto.dao.interfaces.IEstagioMatrizDao;
import com.ufrn.projeto.dao.interfaces.IMatrizDao;
import com.ufrn.projeto.exceptions.CustomNoContentException;
import com.ufrn.projeto.exceptions.OutputMessage;
import com.ufrn.projeto.model.LogEstagio;
import com.ufrn.projeto.model.Matriz;
import com.ufrn.projeto.security.Secured;
import java.util.List;
import java.util.Random;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.hibernate.criterion.Order;


@Path("/matriz")
public class ServiceMatriz {
      
    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Matriz matriz, @Context SecurityContext securityContext){  
        try{      
            IMatrizDao matrizDAO = new MatrizDaoImpl();  
            matriz.setIdentificador(gerarIdentificador());
            matrizDAO.save(matriz);            
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,"ERRO: " + e.toString()))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
        }
        
        //LOG ESTAGIO   
        try {
            IEstagioMatrizDao estagioMatrizDao = new EstagioMatrizDaoImpl();        
            estagioMatrizDao.save(new LogEstagio(matriz, matriz.getEstagio()));
            //System.out.println("salvou" + estagioMatrizDao);
        } catch (Exception e) {
            System.err.println("erro: "+ e);
        }
            
        return Response
                .status(Response.Status.CREATED)
                .entity(matriz)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }
    
    
     private String gerarIdentificador() {
        String randomString = Long.toHexString(Double.doubleToLongBits(Math.random()));
        int length = randomString.length();
        int start = new Random().nextInt(length - 8);
        int end = start + 8;
        return randomString.substring(start, end).toUpperCase();
    }
    
    @DELETE
    @Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id, @Context SecurityContext securityContext) throws CustomNoContentException {
        
        IMatrizDao matrizDao = new MatrizDaoImpl(); 
        Matriz obj = matrizDao.findById(id);        
                    
        if (obj == null){
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
        }
        
        try{
            obj.setAtivo(false);
            matrizDao.save(obj);
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,e.getMessage()))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
        }
        
        return Response
                .status(Response.Status.OK)
                .entity(new OutputMessage(200,"Objeto removido."))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                //.entity(new OutputMessage(200,"Objeto removido por "+ securityContext.getUserPrincipal().getName()))
                .build();
    }    
        
    @PUT
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Matriz matriz, @Context SecurityContext securityContext){
        try{
            IMatrizDao matrizDao = new MatrizDaoImpl();    	
            matrizDao.save(matriz);
        }catch (Exception e){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,e.getMessage()))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
        }
        
        //LOG ESTAGIO  
        try {
            IEstagioMatrizDao estagioMatrizDao = new EstagioMatrizDaoImpl();        
            estagioMatrizDao.save(new LogEstagio(matriz, matriz.getEstagio()));
            //System.out.println("salvou" + estagioMatrizDao);
        } catch (Exception e) {
            System.err.println("erro: "+ e);
        }
        
        return Response
                .status(Response.Status.OK)
                .entity(matriz)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }
    
//    @PUT("/{id}/logEstagio/atualizar")
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateEstagio(@PathParam("id") int id, EnumEstagio enumEstagio){
//        IMatrizDao matrizDao = new MatrizDaoImpl(); 
//        Matriz obj = matrizDao.findById(id);
//        
//        //ATUALIZAR SOMENTE O LOG DE ESTAGIO DA MATRIZ
//        
//        try {
//            
//        } catch (Exception e) {
//        }
//        
//        return null;        
//    }
    
    @GET
    @Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listById(@PathParam("id") int id, @Context SecurityContext securityContext){
        try{
            IMatrizDao matrizDao = new MatrizDaoImpl();    
            Matriz obj = matrizDao.findById(id);
            if (obj == null){
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .build();
                
            }else{
                return Response
                    .status(Response.Status.OK)
                    .entity(obj)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
            }
        }catch (Exception e){
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new OutputMessage(500,e.getMessage()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
        }
    }
    
    
    
    @GET
    @Secured
    @Path("/LogEstagio/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentStage(@PathParam("id") int id, @Context SecurityContext securityContext){
        try{
            //IMatrizDao matrizDao = new MatrizDaoImpl();    
            //Matriz obj = matrizDao.getC(id);
            IEstagioMatrizDao estagioDao = new EstagioMatrizDaoImpl();
            String estagio = estagioDao.getCurrentStage(id);
            if (estagio == null){
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .build();
                
            }else{
                return Response
                    .status(Response.Status.OK)
                    .entity(estagio)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
            }
        }catch (Exception e){
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new OutputMessage(500,e.getMessage()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
        }
    }
    
    
    
    
    @GET
    @Secured
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
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
            }
        }catch (Exception e){
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new OutputMessage(500,e.getMessage()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
        }        
    }
    
    @GET
    @Secured
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
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .build();
            }else{
                return Response
                    .status(Response.Status.OK)
                    .entity(obj)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .build();
            }
        }catch (Exception e){
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new OutputMessage(500,e.getMessage()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
        }        
    }   
}
