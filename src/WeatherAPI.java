import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

import static javax.swing.UIManager.getString;

public class WeatherAPI {
    private final String apiKey;
    private final HttpClient httpClient;

    public WeatherAPI(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    public City getWeather(String cityName) throws Exception {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName.replace(" ", "%20")
                + "&appid=" + apiKey + "&units=imperial";


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
        return parseWeatherData(response.body());
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
            double windSpeed = windStr.isEmpty() ? 0.0 : Double.parseDouble(windStr);

            return new City(name, country, temp, description,
                    feelsLike, humidity, windSpeed);
        } catch (Exception e) {
            System.out.println("Warning: Could not parse weather data");
            return new City("Unknown", "??", 0, "no data", 0, 0, 0);
        }
    }

    private String extractValue(String json, String key, String endChar){
        int startIndex = json.indexOf(key);
        if(startIndex == -1) return "";

        startIndex += key.length();
        int endIndex = json.indexOf(endChar, startIndex);
        if(endIndex == -1) endIndex = json.length();

        return json.substring(startIndex, endIndex);
    }
}