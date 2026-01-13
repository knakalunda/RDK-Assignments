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
                    SearchWeather();
                    break;
                case "2":
                    updateList();
                    break;
                case "3":
                    listFavorites();
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please Try Again.");
                    //Pause for user
                    try { Thread.sleep(1000); } catch (InterruptedException e){}
            }
        }
        scanner.close();
    }

    private void showMenu(){
        System.out.println("          MAIN MENU\n");
        System.out.println("1. Select City");
        System.out.println("2. Manage favorites");
        System.out.println("3. List favorites");
        System.out.println("4. Exit App");
    }

    private void SearchWeather(){

    }
    private void updateList(){
        System.out.println("1. Add City");
        System.out.println("2. Remove a city");
        System.out.println("3. Return to menu");

        String choice = scanner.nextLine();

        switch(choice){
            case "1":
                addtoFav();
                break;
            case "2":
                removefromFav();
                break;
            case "3":
                System.out.println("Returning to main");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                //Pause for user
                try { Thread.sleep(1000); } catch (InterruptedException e){}
        }
    }

    private void addtoFav(){
        System.out.println("\n Enter City to add: ");
        String city = scanner.nextLine();

        if(favorites.size() >= 3){
            System.out.println("*** Maximum number of favorites added ***");
        }else if(favorites.contains(city)){
            System.out.println("***" + city + " is already in favorites. ");
        }else {
            favorites.add(city);
            System.out.println(city + " added to favorites list");
        }
    }
    private void removefromFav(){
        //Different error message than listFavorites()
        if(favorites.isEmpty()) {
            System.out.println("No favorites to remove.");
            return;
        }

        System.out.println("Select number to remove: \n");
        listFavorites();

        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if(index >= 0 && index < favorites.size()){
                String removed = favorites.remove(index);
                System.out.println("Removed: " + removed);
            }else {
                System.out.println("Invalid number");
            }
        } catch (NumberFormatException e){
            System.out.println("Please enter a valid number");
        }

    }

    private void listFavorites(){
        System.out.println("       YOUR FAVORITE CITIES");

        if(favorites.isEmpty()) {
            System.out.println("No favorites yet! Please try again");
        }else{
            for(int i = 0; i < favorites.size(); i++){
                System.out.println((i + 1) + ". " + favorites.get(i));
            //Add weather details
            }
        }
    }

}
