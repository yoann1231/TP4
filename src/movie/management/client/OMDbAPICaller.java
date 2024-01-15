package movie.management.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OMDbAPICaller {

    // Clé API OMDb
    private final String apiKey;
    // URL de requête de l'API OMDb
    private final String URL_STRING = "http://www.omdbapi.com/";

    public OMDbAPICaller(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Rechercher les informations sur un film
     *
     * @param movieTitle Titre du film
     * @param year       Année de sortie du film
     * @return Chaîne contenant les informations sur le film
     */
    public String searchMovie(String movieTitle, int year) {
        try {
            // Construire l'URL de la requête API
            String apiUrl = URL_STRING + "?t=" + movieTitle + "&y=" + year + "&apikey=" + apiKey;

            // Créer un objet URL
            URL url = new URL(apiUrl);
            // Ouvrir la connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Définir la méthode de requête comme GET
            connection.setRequestMethod("GET");
            // Obtenir le code de réponse HTTP
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lire le contenu de la réponse
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                return response.toString();
            } else {
                // Afficher le message d'erreur
                System.out.println("ERREUR：" + responseCode);
                return "ERREUR";
            }

        } catch (IOException e) {
            // Afficher les informations sur l'exception
            e.printStackTrace();
        }
        return "ERREUR";
    }

}
