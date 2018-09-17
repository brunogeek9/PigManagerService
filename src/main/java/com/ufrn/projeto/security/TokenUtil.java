/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.security;

import io.jsonwebtoken.Claims;
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
public class TokenUtil {
    
    public static String criaToken(String username) {                
        
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS384;
        
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("35tdsxz");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
 
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .claim("usuario", username)
                .claim("create", Calendar.getInstance().getTime())
                .signWith(signatureAlgorithm, signingKey);//Token completo e compactado
        
        String compact = builder.compact();
        
        return compact;
    }
    
    public static void validaToken(String token) throws Exception {
        // Verifica se o token existe no banco de dados, caso não existir lançar uma exceção
    }
}
