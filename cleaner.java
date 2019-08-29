import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cleaner {

    public static void clean() {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("clear");
        try {

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}