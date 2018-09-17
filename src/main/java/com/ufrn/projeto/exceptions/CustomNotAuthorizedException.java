/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Taniro
 */
public class CustomNotAuthorizedException extends WebApplicationException{
    
    public CustomNotAuthorizedException() {
        super(Response
                    .status(Response.Status.FORBIDDEN)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
    }
    
    public CustomNotAuthorizedException(String message) {
        super(Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(new OutputMessage(Response.Status.FORBIDDEN.getStatusCode(), message))
                    .type(MediaType.APPLICATION_JSON)
                    .build());
    }
    
}
