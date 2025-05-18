package control;
import static control.MostrarUsuarios.mostrarUsuarios;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import model.Jogador;

import org.json.JSONObject;

public class CriarUsuario {
    private static final String DB_PATH = "usuarios.json";
    
    public static void salvarUsuarios(JSONObject usuarios) {
        try (FileWriter file = new FileWriter(DB_PATH)) {
            file.write(usuarios.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean criarUsuario(String nome, String email, String senha, int pontos) {
        JSONObject usuarios =  mostrarUsuarios();
        if (usuarios.has(nome)) {
            System.out.println("Usuário já existe.");
            return false;
        }
        JSONObject novoUsuario = new JSONObject();
        
        Jogador novoJgador = new Jogador("Marcio dos Anjos", "ma@gmail.com", "1234", 0);
        novoUsuario.put("nome", novoJgador.getNome());
        novoUsuario.put("email", novoJgador.getEmail());
        novoUsuario.put("senha", hashSenha(novoJgador.getSenha()));
        novoUsuario.put("pontos", 0);
        novoUsuario.put("id",novoJgador.getSenha() + novoJgador.getEmail());
        //novoUsuario.put("id",hashSenha(novoJgador.getSenha()) + novoJgador.getEmail());
        usuarios.put(nome, novoUsuario);
        salvarUsuarios(usuarios);
        System.out.println("Gravando em: " + new java.io.File(DB_PATH).getAbsolutePath());
        System.out.println("Usuário cadastrado com sucesso.");
        System.out.println(usuarios);

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
