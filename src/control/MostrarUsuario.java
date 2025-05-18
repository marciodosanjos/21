

package control;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.JSONObject;


public class MostrarUsuario {
    private static final String DB_PATH = "usuarios.json";

    public static JSONObject mostrarUsuario (String id) {
    
        try {
            // Lê o conteúdo do arquivo
            String conteudo = new String(Files.readAllBytes(Paths.get(DB_PATH)));
            JSONObject usuarios = new JSONObject(conteudo);

            Iterator<String> keys = usuarios.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject user = usuarios.getJSONObject(key);

                if (user.getString("id").equalsIgnoreCase(id)) {
                    System.out.println(user);
                    return user;
                }
            }

            System.out.println("Usuário com email " + id + " não encontrado.");
            return null;

        } catch (IOException e) {
            System.err.println("Erro ao editar usuário: " + e.getMessage());
            return null;           
        }
        
    }
}
