import com.ufrn.projeto.dao.implementations.LogEstagioDaoImpl;
import com.ufrn.projeto.dao.implementations.MatrizDaoImpl;
import com.ufrn.projeto.dao.interfaces.IMatrizDao;
import com.ufrn.projeto.model.LogEstagio;
import com.ufrn.projeto.model.Matriz;
import com.ufrn.projeto.model.enums.EnumEstagio;
import org.hibernate.Session;
import com.ufrn.projeto.dao.interfaces.ILogEstagioDao;

public class Teste {
    
       //EXEMPLOS DE JSON
   
    //CADASTRO DE USUARIO
    //{
    //	"email":"fbio@gmail.com",
    //	"senha": "123",
    //	"usuario": {
    //		"nome":"Fábio",
    //        "nomeUsuario": "fbio1"
    //	}
    //	
    //}

    //LOGIN
    //   {
    //	"email":"fbio@gmail.com",
    //	"senha": "123"
    //}
    
    //PARA FAZER QUALQUER ALTERAÇÃO EM MATRIZ É NECESSARIO FAZER O LOGIN
    //APÓS FAZER O LOGIN, PEGUE O TOKEN GERADO E NA ABA "HEADERS" DO POSTMAN 
    //É SO ADICIONAR A KEY "Authorization" E O VALUE "Bearer (TOKEN GERADO NO LOGIN)"
   
    //CADASTRO DE MATRIZ
    //   {
    //    "raca": "bovina teste",
    //    "peso": 45.4,
    //    "enumEstagio": "COBERTA",
    //    "arquivo": "teste",
    //    "dataNascimento": "2018-10-10"
    //} 
   
    public static void main(String args[]){
        /*
        Session sessao = null;
        IEstagioMatrizDao estagioDao = new EstagioMatrizDaoImpl();
        IMatrizDao mDao = new MatrizDaoImpl();
        
        Matriz m = mDao.findById(2);
        
        LogEstagio log = new LogEstagio(m,EnumEstagio.VAZIA);
        System.out.println(m.toString()+" "+log.toString());
        estagioDao.save(log);
        */
        ILogEstagioDao estagioDao = new LogEstagioDaoImpl();
        //estagioDao.saveLog(3, EnumEstagio.VAZIA);
        String teste = estagioDao.findByEstagio(2);                         
    }                       
    
}
