package control;
import static control.CriarUsuario.hashSenha;
import static control.MostrarUsuarios.mostrarUsuarios;
import org.json.JSONObject;


public class LoginController {
    private static final String DB_PATH = "acessos.json";
  
    
    public static boolean login(String email, String senha) {
        JSONObject usuarios = mostrarUsuarios();
        if (!usuarios.has(email)) {
            System.out.println("Usuário não encontrado.");
            return false;
        }
        String senhaHash = usuarios.getJSONObject(email).getString("senha");
        if (!senhaHash.equals(hashSenha(senha))) {
            System.out.println("Senha incorreta.");
            return false;
        }
        
        String id = email + hashSenha(senha);
        
        
        CriarAcessoController.criarUsuario(email, id);
        

        
        System.out.println("Login bem-sucedido. Bem-vindo, " + email + "!");
        
        return true;
    }

}
