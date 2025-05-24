package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import model.Jogador;
import org.json.JSONObject;

public class UsuarioController {

    private static final String DB_PATH = "usuarios.json";

    public static void salvarUsuarios(JSONObject usuarios) {
        try (FileWriter file = new FileWriter(DB_PATH)) {
            file.write(usuarios.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean criarUsuario(String nome, String email, String senha, int pontos) {
        JSONObject usuarios = mostrarUsuarios();
        if (usuarios.has(nome)) {
            System.out.println("Usuário já existe.");
            return false;
        }
        JSONObject novoUsuario = new JSONObject();

        Jogador novoJgador = new Jogador(nome, email, senha, 0);
        novoUsuario.put("nome", novoJgador.getNome());
        novoUsuario.put("email", novoJgador.getEmail());
        novoUsuario.put("senha", hashSenha(novoJgador.getSenha()));
        novoUsuario.put("pontos", 0);
        novoUsuario.put("id", novoJgador.getSenha() + novoJgador.getEmail());
        //novoUsuario.put("id",hashSenha(novoJgador.getSenha()) + novoJgador.getEmail());
        usuarios.put(nome, novoUsuario);
        salvarUsuarios(usuarios);
        System.out.println("Gravando em: " + new java.io.File(DB_PATH).getAbsolutePath());
        System.out.println("Usuário cadastrado com sucesso.");
        System.out.println(usuarios);

        return true;
    }

    public static JSONObject mostrarUsuario(String id) {

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

    public static JSONObject mostrarUsuarios() {
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                Files.write(Paths.get(DB_PATH), "{}".getBytes());
            }
            String content = new String(Files.readAllBytes(Paths.get(DB_PATH)));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

}
