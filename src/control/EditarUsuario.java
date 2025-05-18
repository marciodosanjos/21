package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class EditarUsuario {
    private static final String DB_PATH = "usuarios.json";

    public static boolean editarUsuario(String id, String novoNome, String novoEmail, String novaSenha, int novosPontos) {
        try {
            // Lê o conteúdo do arquivo
            String conteudo = new String(Files.readAllBytes(Paths.get(DB_PATH)));
            JSONObject usuarios = new JSONObject(conteudo);

            Iterator<String> keys = usuarios.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject user = usuarios.getJSONObject(key);


                if (user.getString("id").equalsIgnoreCase(id)) {
                    // Atualiza os campos
                    user.put("nome", novoNome);
                    user.put("email", novoEmail);
                    user.put("senha", hashSenha(novaSenha));
                    user.put("pontos", novosPontos);

                    // Salva novamente no arquivo
                    try (FileWriter fw = new FileWriter(DB_PATH)) {
                        fw.write(usuarios.toString(4));
                        System.out.println("Usuário atualizado com sucesso.");
                        return true;
                    }
                }
            }

            System.out.println("Usuário com email " + id + " não encontrado.");
            return false;

        } catch (IOException e) {
            System.err.println("Erro ao editar usuário: " + e.getMessage());
            return false;
        }
    }

    private static String hashSenha(String senha) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao hashear senha", e);
        }
    }
}
