package movie.management.client;

import javax.json.Json;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OMDbAPICaller {

    private final String apiKey;
    private final String URL_STRING = "http://www.omdbapi.com/";

    public OMDbAPICaller(String apiKey) {
        this.apiKey = apiKey;
    }

    public String searchMovie(String movieTitle, int year) {
        try {
            String apiUrl = URL_STRING + "?t=" + movieTitle + "&y=" + year + "&apikey=" + apiKey;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                return response.toString();
            } else {
                System.out.println("ERROR：" + responseCode);
                return "ERROR";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

}
