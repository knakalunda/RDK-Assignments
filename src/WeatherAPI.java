import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherAPI {
    private final String apiKey;
    private final HttpClient httpClient;

    public WeatherAPI(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getWeather(String cityName) throws Exception {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName.replace(" ", "%20")
                + "&appid=" + apiKey;


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        if(response.statusCode() != 200){
            throw new Exception("API Error " + response.statusCode());
        }
        return response.body();
    }

    public City parseWeatherData(String json){
        try {
            String name = extractValue(json, "\"name\" : \"", "\"");
            String country = extractValue(json, "\"country\":\"", "\"");

            String tempStr = extractValue(json, "\"temp\":", ",");
            String feelsStr = extractValue(json, "\"feels_like\":", ",");
            String humidStr = extractValue(json, "\"humidity\":", ",");

            double temp = Double.parseDouble(tempStr);
            double feelsLike = Double.parseDouble(feelsStr);
            int humidity = Integer.parseInt(humidStr);

            String description = extractValue(json, "\"description\":\"", "\"");
            if (!description.isEmpty()) {
                description = Character.toUpperCase(description.charAt(0)) +
                        description.substring(1);
            }

            String windStr = extractValue(json, "\"speed\":", ",");
            double windSpeed = Double.parseDouble(windStr);

            return new City(name, country, temp, description,
                    feelsLike, humidity, windSpeed);
        } catch (Exception e) {
            System.out.println("Warning: Could not parse weather data");
            return new City("Unknown", "??", 0, "no data", 0, 0, 0);
        }
    }

    private String extractValue(String json, String key, String endchar){
        int startIndex = json.indexOf(key);
        if(startIndex == -1) return "";

        startIndex += key.length();
        int endIndex = json.indexOf(key);
        if(endIndex == -1) endIndex = json.length();

        return json.substring(startIndex, endIndex);
    }
}