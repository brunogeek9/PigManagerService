package com.ufrn.projeto.services;

import com.ufrn.projeto.dao.implementations.LoginDaoImpl;
import com.ufrn.projeto.dao.interfaces.ILoginDao;
import com.ufrn.projeto.exceptions.OutputMessage;
import com.ufrn.projeto.model.Login;
import com.ufrn.projeto.util.TokenUtil;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/logout")
public class ServiceLogout {
    
    @Context HttpServletRequest httpServletRequest =null;
    @GET
    public Response logar(){
        Login logUsuario = null;
        ILoginDao loginDAO = new LoginDaoImpl();
        //Adquirindo token a partir do cabeçalho da requisição
        String token = httpServletRequest.getHeader("Authorization").split("Bearer ")[1];
        
        System.out.println("token " + token);
        /**
         * Testando se o token é válido. 
         * Caso não haja erro na execução, o token do usuário é destruído.
         */
        try{
            Claims campos = TokenUtil.campos(token);
            System.out.println("campos " + campos);
            
            logUsuario = loginDAO.verificarUsuario(token, (int) campos.get("usuario"));            
            System.out.println("logUsuario " + logUsuario);
            
            logUsuario.setToken("");
            loginDAO.save(logUsuario);
        }catch(Exception e){
            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500,"ERRO: " + e.toString()))
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        
        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .build();//200
    }
}