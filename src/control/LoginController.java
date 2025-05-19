package control;
import static control.CriarUsuario.hashSenha;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.JSONObject;


public class LoginController {
     
           private static final String DB_PATH = "usuarios.json";

    
    public static boolean login(String email, String senha) {
        
        try{
        String conteudo = new String(Files.readAllBytes(Paths.get(DB_PATH)));
        JSONObject usuarios = new JSONObject(conteudo);
        Iterator<String> keys = usuarios.keys();
        
        while(keys.hasNext()) {
          String key = keys.next();
          JSONObject user = usuarios.getJSONObject(key);
          
          if(user.getString("email").equalsIgnoreCase(email)){
              System.out.println("Usuário encontrado.");
              
              String senhaHash = user.getString("senha");
              Boolean senhaEIgual =senhaHash.equalsIgnoreCase(String.valueOf(senha));
              
              System.out.println(senhaHash);

                if (senhaEIgual) {
                    System.out.println("Senha incorreta.");
                    return false;
                }
             
               String id = email + hashSenha(senha);
               CriarAcessoController.criarAcesso(email, id);
               
              System.out.println("Login bem-sucedido. Bem-vindo, " + email + "!");

               return true;
          }
        
        }


        }catch(IOException e){
            throw new RuntimeException("Erro ao fazer login", e);
        }

               
        return true;
    }

}
