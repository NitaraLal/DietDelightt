
import java.net.URI; // library to access API endpoints
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import  java.util.Scanner;
import java.util.*; // map, set, list...
public class Main {
    public static void main(String[] args) {
        String diet = "";
        int maxCarbs = Integer.MAX_VALUE;

        String key = Credentials.Key;
        String host = Credentials.Host;

        if(args.length == 1) {
            diet = args[0];

        }
        else if(args.length == 2) {
            diet = args[0];
            maxCarbs = Integer.parseInt(args[1]);

        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello! Let me find you a recipe!");

            boolean dietFound = false;
            do { 
                System.out.print("Enter a diet: ");
                diet = scanner.nextLine();

                dietFound = DietValidation.validate(diet);

                if (!dietFound) {
                    System.out.println("Diet " + diet + " is not available.");
                    System.out.println("Supported diets are:");
                    DietValidation.printDiets();
                }


            } while(!dietFound);

            System.out.print("Do you want to filter by max carbs? (Y/N): ");
            String yesNo = scanner.nextLine();

            if (yesNo.equalsIgnoreCase("y")) {
                System.out.print("Enter max carbs: ");
                maxCarbs = scanner.nextInt();
            }
        }



        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch?diet=" + diet + "&maxCarbs=" + maxCarbs;

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                    .setHeader("X-RapidAPI-Key", key)
                    .setHeader("X-RapidAPI-Host", host)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }



}