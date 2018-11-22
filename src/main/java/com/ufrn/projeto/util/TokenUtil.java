package com.ufrn.projeto.util;

import com.ufrn.projeto.dao.implementations.LoginDaoImpl;
import com.ufrn.projeto.dao.interfaces.ILoginDao;
import com.ufrn.projeto.model.Login;
import java.security.Key;
import java.util.Calendar;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {
    
    public static String criaToken(String email) {                
        
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS384;
        
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("35tdsxz");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
 
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .claim("usuario", email)
                .claim("create", Calendar.getInstance().getTime())
                .signWith(signatureAlgorithm, signingKey);//Token completo e compactado
        
        String compact = builder.compact();
        
        return compact;
    }
    
    public static Login validaToken(String token) throws Exception {
        // Verifica se o token existe no banco de dados, caso não existir lançar uma exceção
    	try {
            ILoginDao credenciaisDao = new LoginDaoImpl();
            Login credenciais = (Login) credenciaisDao.findByTokenUser(token);

            if(credenciais != null) {
                if(credenciais.getToken() == null) {
                                throw new Exception();
                } else {
                        //refreshToken(credenciais);
                        return credenciais;
                }
            } else {
                throw new Exception();
            }

        } finally {
                // TODO: handle finally clause
        }    	
    }
	
//    public static void refreshToken(Login credencial) {
//        ILoginDao credenciaisDao = new LoginDaoImpl();
//        Login credenciaisWithNewToken = new Login();
//
//        credenciaisWithNewToken = credenciaisDao.findById(credencial.getId());
//
//        credenciaisWithNewToken.setToken(criaToken(credenciaisWithNewToken.getEmail()));
//
//        credenciaisDao.save(credenciaisWithNewToken);
//
//    }
//    
//    public static Claims campos(String token){
//        //Obtenção dos Claims do token
//        Claims claims = Jwts.parser()
//                .setSigningKey(DatatypeConverter.parseBase64Binary("35tdsxz"))
//                .parseClaimsJws(token).getBody();
//        
//        return claims;
//    }
    
}
