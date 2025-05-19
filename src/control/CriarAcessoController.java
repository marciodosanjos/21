package control;
import static control.CriarUsuario.salvarUsuarios;
import static control.MostrarAcessosController.mostrarAcessos;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import model.Jogador;

import org.json.JSONObject;

public class CriarAcessoController {
    private static final String DB_PATH = "acessos.json";
    
    public static void salvarAcessos(JSONObject acessos) {
        try (FileWriter file = new FileWriter(DB_PATH)) {
            file.write(acessos.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean criarAcesso(String email, String id) {
        JSONObject acessos =  mostrarAcessos();
        if (acessos.has(email)) {
            System.out.println("Acesso já existe.");
            return false;
        }
        
        JSONObject novoAcesso = new JSONObject();
        
        model.Login novoLogin = new model.Login(email,id);
        novoAcesso.put("nome", novoLogin.getEmail());
        novoAcesso.put("id", novoLogin.getId());
        acessos.put(email, novoLogin);
        salvarAcessos(acessos);
        System.out.println("Gravando em: " + new java.io.File(DB_PATH).getAbsolutePath());
        System.out.println("Acesso cadastrado com sucesso.");
        System.out.println(acessos);

        return true;
    }
    
    public static String hashSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
}
