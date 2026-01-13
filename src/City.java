public class City {
    private String name;
    private String country;
    private double temp;
    private String weatherDescription;
    private double feelsLike;
    private double humidity;
    private double windSpeed;

    public City(String name, String country, double temp, String weatherDescription, double feelsLike, double humidity, double windSpeed) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.weatherDescription = weatherDescription;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getTemp() {
        return temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void getWeather() {
        System.out.println("\n===============");
        System.out.println("City: " + name + ", " + country);
        System.out.println("Temperature: " + temp + "°F");
        System.out.println("Feels Like: " + feelsLike + "°F");
        System.out.println("Weather: " + weatherDescription);
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Wind Speed: " + windSpeed + "mph");
        System.out.println("===============\n");
    }
}

