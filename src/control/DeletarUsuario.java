package control;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.JSONObject;

public class DeletarUsuario {
    private static final String DB_PATH = "usuarios.json"; 

    public static boolean deletarUsuario(String id) {
        try {
            // Lê o conteúdo do arquivo
            String conteudo = new String(Files.readAllBytes(Paths.get(DB_PATH)));
            JSONObject usuarios = new JSONObject(conteudo);

            Iterator<String> keys = usuarios.keys();
            boolean removido = false;

            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject user = usuarios.getJSONObject(key);

                if (user.getString("id").equalsIgnoreCase(id)) {
                    usuarios.remove(key); // REMOVE O USUÁRIO COMPLETO
                    removido = true;
                    break; // usuário encontrado e removido, pode sair do loop
                }
            }

            if (removido) {
                // Salva o JSON atualizado
                try (FileWriter file = new FileWriter(DB_PATH)) {
                    file.write(usuarios.toString(4));
                }
                System.out.println("Usuário com id " + id + " removido.");
                return true;
            } else {
                System.out.println("Usuário com id " + id + " não encontrado.");
                return false;
            }

        } catch (IOException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
    }
}
