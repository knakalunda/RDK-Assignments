//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
  static void main(String[] args) {

    String apiKey = "77bd31a2d6319efd86cc1868dbb0260f";
    System.out.println("Weather App Starting...\n");


    WeatherApp app = new WeatherApp(apiKey);
    app.run();
  }
}
