package com.ufrn.projeto.services;
import com.ufrn.projeto.dao.implementations.LoginDaoImpl;
import com.ufrn.projeto.dao.interfaces.ILoginDao;
import com.ufrn.projeto.exceptions.OutputMessage;
import com.ufrn.projeto.model.Login;
import com.ufrn.projeto.util.TokenUtil;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class ServiceLogin {
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticUser(Login login) {
        ILoginDao credenciaisDao = new LoginDaoImpl();
        try {
            Login userLogged = validUser(login.getEmail(), login.getSenha());
            String token = TokenUtil.criaToken(login.getEmail());

            userLogged.setToken(token);
            credenciaisDao.save(userLogged);

            return Response.status(Response.Status.OK)
                    .entity(new OutputMessage(200, "Bearer "+token))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new OutputMessage(Response.Status.UNAUTHORIZED.getStatusCode(),
                                    "Permission denied " + e.getMessage()))
                    .build();
        }
    }

    public Login validUser(String email, String senha) throws Exception {
        ILoginDao userDao = new LoginDaoImpl();

        Login login = userDao.validUser(email, senha);

        if (login == null)
            throw new Exception("User doesn't exists.");
        else
            return login;
    }

}