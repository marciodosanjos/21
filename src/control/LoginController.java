package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Iterator;
import org.json.JSONObject;

public class LoginController {

    private static final String DB_PATH = "usuarios.json";
    private static final String ACCESS_PATH = "acessos.json";

    public static void salvarAcessos(JSONObject acessos) {
        try (FileWriter file = new FileWriter(ACCESS_PATH)) {
            file.write(acessos.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean criarAcesso(String email, String id) {
        JSONObject acessos = mostrarAcessos();
        if (acessos.has(email)) {
            System.out.println("Acesso já existe.");
            return false;
        }

        JSONObject novoAcesso = new JSONObject();

        model.Login novoLogin = new model.Login(email, id);
        novoAcesso.put("nome", novoLogin.getEmail());
        novoAcesso.put("id", novoLogin.getId());
        acessos.put(email, novoLogin);
        salvarAcessos(acessos);
        System.out.println("Gravando em: " + new java.io.File(ACCESS_PATH).getAbsolutePath());
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

    public static boolean estaLogado() {

        if (!Files.exists(Paths.get(ACCESS_PATH))) {
            return false;
        }
        return true;
    }

    public static boolean login(String email, String senha) {

        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(DB_PATH)));
            JSONObject usuarios = new JSONObject(conteudo);
            Iterator<String> keys = usuarios.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject user = usuarios.getJSONObject(key);

                if (user.getString("email").equalsIgnoreCase(email)) {
                    System.out.println("Usuário encontrado.");

                    String senhaHash = user.getString("senha");
                    Boolean senhaEIgual = senhaHash.equalsIgnoreCase(String.valueOf(senha));


                    if (senhaEIgual) {
                        System.out.println("Senha incorreta.");
                        return false;
                    }

                    String id = email + hashSenha(senha);
                    criarAcesso(email, id);

                    System.out.println("Login bem-sucedido. Bem-vindo, " + email + "!");

                    return true;
                }

            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer login", e);
        }

        return true;
    }

    public static JSONObject mostrarAcessos() {
        try {
            File file = new File(ACCESS_PATH);
            if (!file.exists()) {
                System.out.println("Arquivo de acessos não encontrado. Criando um novo.");
                Files.write(Paths.get(ACCESS_PATH), "{}".getBytes());
            }
            String content = new String(Files.readAllBytes(Paths.get(ACCESS_PATH)));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}
