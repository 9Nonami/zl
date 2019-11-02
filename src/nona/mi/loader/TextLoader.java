package nona.mi.loader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TextLoader{

    public static String loadText(String path) {

        String stringao = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(TextLoader.class.getResourceAsStream(path)))){

            String read = "";
            while (((read = br.readLine()) != null)) {
                stringao += read;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("text reading error");
            System.exit(0);
        }

        return stringao;
    }

}
