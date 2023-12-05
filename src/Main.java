// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
import java.net.URI; // library to access API endpoints
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import  java.util.Scanner;
import java.util.*; // map, set, list...
public class Main {
    public static void main(String[] args) {
        String key = Credentials.Key;
        String host = Credentials.Host;
        Scanner scanner = new Scanner(System.in);
        String diet = scanner.nextLine();

        String[] availableDiets = new String[] {"Gluten Free", "Ketogenic", "Vegeterian", "Lacto-Vegeterian", "Ovo-Vegeterian", "Vegan", "Pescetarian", "Paleo", "Primal",
        "Low FODMAP", "Whole30"};

        boolean found = false;
        for (String each : availableDiets) {
            if (each.equalsIgnoreCase(diet)) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("diet " + diet + " is not available.");
            return;
        }
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch?diet=" + diet;
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