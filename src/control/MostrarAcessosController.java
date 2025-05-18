
package control;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class MostrarAcessosController {
    
    private static final String DB_PATH = "acessos.json";

      public static JSONObject mostrarAcessos() {
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
