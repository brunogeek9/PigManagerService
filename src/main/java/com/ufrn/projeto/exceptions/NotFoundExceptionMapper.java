/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.exceptions;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Taniro
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>{
    
    @Override
    public Response toResponse(NotFoundException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new OutputMessage(Response.Status.NOT_FOUND.getStatusCode(), "Mensagem:" + exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}