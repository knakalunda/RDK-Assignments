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
        System.out.println("----Welcome to the Weather App----\n");

        boolean running = true;

        while(running){
            showMenu();
            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    System.out.println("Search");
                    break;
                case "2":
                    System.out.println("Fav");
                    break;
                case "3":
                    System.out.println("List");
                    break;
                case "4":
                    System.out.println("Update");
                    break;
                case "5":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please Try Again.");
            }
        }
    }

    private void showMenu(){
        System.out.println("          MAIN MENU\n");
        System.out.println("1. Select City");
        System.out.println("2. Add to favorites");
        System.out.println("3. List favorites");
        System.out.println("4. Update Favorites");
        System.out.println("5. Exit App");
    }
}
