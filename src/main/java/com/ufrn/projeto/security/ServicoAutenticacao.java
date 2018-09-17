/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.security;

import com.ufrn.projeto.exceptions.OutputMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Calendar;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Taniro
 */
@Path("/autenticacao")
public class ServicoAutenticacao {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticaUser(Credenciais c) {
        try {
               
            //Valida dados
            validaCredenciais(c.getUsername(), c.getPassword());

            // Cria Token
            String token = TokenUtil.criaToken(c.getUsername());

            // Responde com o Token
            return Response
                    .status(Response.Status.OK)
                    .entity(new OutputMessage(200, token))
                    .build();

        } catch (Exception e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(new OutputMessage(Response.Status.UNAUTHORIZED.getStatusCode(), "Não autorizado: " + e.getMessage()))
                    .build();
        }
    }

    private void validaCredenciais(String username, String password) throws Exception {
        
        //Autentica credenciais, aqui seria o acesso ao banco de dados para validação
        //Caso não seja encontrado, lancar uma execeção
        if(!username.equals("taniro") || !password.equals("12345")){
            throw new Exception("Verifique o nome de usuário e senha.");
        }
    }
}
