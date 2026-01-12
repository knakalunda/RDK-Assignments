import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class WeatherApp{
    private Scanner scanner;
    private List <String> favorites;

    public WeatherApp() {
        this.scanner = new Scanner(System.in);
        this.favorites = new ArrayList<>();
    }
    public void run() {
        System.out.println("testing");
    }
}
