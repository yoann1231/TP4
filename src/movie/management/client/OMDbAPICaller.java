package movie.management.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OMDbAPICaller {

    private final String apiKey;

    public OMDbAPICaller(String apiKey) {
        this.apiKey = apiKey;
    }

    public void searchMovie(String movieTitle, int year) {
        try {
            // 构建API请求URL
            String apiUrl = "http://www.omdbapi.com/?t=" + movieTitle + "&y=" + year + "&apikey=" + apiKey;

            // 创建URL对象
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 获取响应代码
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // 处理响应数据
                System.out.println(response.toString());
            } else {
                System.out.println("错误：" + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
