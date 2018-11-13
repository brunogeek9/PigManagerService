package com.ufrn.projeto.filtro;

//package com.pa2.filtro;
//
//import com.pa2.dao.LoginDAO;
//import com.pa2.model.Login;
//import com.pa2.token.Token;
//import java.io.IOException;
//import javax.servlet.Filter;
//import io.jsonwebtoken.Claims;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
////Indica onde o filtro será usado
////@WebFilter({"/analiseQuimica/*","/unidade/*","/salaPreparacao/*",
////            "/prescricao/*","/preparacao/*","/menuAplicado/*","/menu/*","/itemEstoque/*","/insumoPerCapita/*",
////            "/insumo/*","/corte/*","/cardapioAplicado/*","/cardapio/*","/usuario/*",
////            "/relatorioLanches/*","/relatorioPrescricao/*","/relatorioPrescricaoVegetais/*","/requisicaoPrescricao/*",
////            "/prescricaoSalaCarnes/*"})
//
////,"/relatorioCardapioSemanal/*"
//public class FiltroAcesso implements Filter{
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//    @Override
//    public void destroy() {}
//    
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Login logUsuario = null;
//        LoginDAO loginDAO = new LoginDAO();
//        String token = "";
//        //Adquirindo token a partir do cabeçalho da requisição
//        try{
//            token = ((HttpServletRequest) request).getHeader("Authorization").split("bearer ")[1];
//        }catch(Exception e){}
//        
//        //Url da requisição
//        String urlRequisicao = ((HttpServletRequest) request).getPathInfo();
//        //Nome do serviço solicitado na requisição
//        String nomeServico = urlRequisicao.split("/")[1];
//        
//        //Flag de checagem de autorização
//        boolean autorizado = false;
//        
//        /**
//         * Testando se o token é válido. 
//         * Caso não haja erro na execução, o filtro continua a execução.
//         */
//        try{
//            Claims campos = Token.campos(token);
//            logUsuario = loginDAO.verificarUsuario(token, (int) campos.get("usuario"));
//            
//            //Testa se o token existe
//            if(logUsuario != null){
//                String tipoUsuario = (String) campos.get("tipo");
//            
//                //Checa qual o tipo do usuario que está tentando acessar o serviço
//                switch(tipoUsuario){
//                    //NUTRICIONISTA é admin e pode acessar todos os serviços
//                    case "NUTRICIONISTA":
//                        autorizado = true;
//                        break;
//                    //ALMOXARIFE pode acessar apenas o serviço itemEstoque
//                    case "ALMOXARIFE":
//                        if(nomeServico.equals("itemEstoque"))
//                            autorizado = true;
//                        else if(nomeServico.equals("unidade"))
//                            autorizado = true;
//                        else if(nomeServico.equals("insumo"))
//                            autorizado = true;
//                        break;
//                    //ESTAGIARIO não pode acessar apenas os serviços cardapioAplicado, menuAplicado e prescricao
//                    case "ESTAGIARIO":
//                        if(nomeServico.equals("cardapioAplicado"))
//                            autorizado = false;
//                        else if(nomeServico.equals("menuAplicado"))
//                            autorizado = false;
//                        else if(nomeServico.equals("prescricao"))
//                            autorizado = false;
//                        else if(nomeServico.equals("usuario"))
//                            autorizado = false;
//                        else if(nomeServico.equals("relatorioCardapioSemanal"))
//                            autorizado = false;
//                        else if(nomeServico.equals("relatorioLanches"))
//                            autorizado = false;
//                        else if(nomeServico.equals("relatorioPrescricao"))
//                            autorizado = false;
//                        else if(nomeServico.equals("relatorioPrescricaoVegetais"))
//                            autorizado = false;
//                        else if(nomeServico.equals("requisicaoPrescricao"))
//                            autorizado = false;
//                        else if(nomeServico.equals("prescricaoSalaCarnes"))
//                            autorizado = false;
//                        else
//                            autorizado = true;
//                        break;
//                }
//            }
//        }catch(Exception e){}
//        
//        /**
//         * Se ele for autorizado a acessar o serviço solicitado então a execução continua
//         * Senão é retornado o erro 401
//         */
//        if(autorizado){
//            chain.doFilter(request, response);
//        }
//        else{
//            ((HttpServletResponse) response).sendError(401, "Usuário não autorizado!");
//            //((HttpServletResponse) response).sendError(401);
//        }
//    }
//}
