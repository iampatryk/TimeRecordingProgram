import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TimeRecording {

    void getNumberIdFromUserAndSaveTimeAndDate() {

        Scanner scanner = new Scanner(System.in);

        //Tablica z poprawnymi ID
        int[] validUserIds = {243, 344, 76, 273, 9};
        //Mapa przechowujaca date
        Map<Integer, Date> entryDates = new HashMap<>();
        //Mapa przechowujaca Imie i Nazwisko
        Map<Integer, String> userNames = new HashMap<>();
        userNames.put(243, "John Wood");
        userNames.put(344, "Alex Garden");
        userNames.put(76, "Paul King");
        userNames.put(273, "Megan Alice");
        userNames.put(9, "Sam Smith");

        while (true) {
            // Pobierz numer ID od urzytkownika
            System.out.println("Enter your ID number or 'exit' to close program");
            String input = scanner.next();

            if(input.equalsIgnoreCase("exit")) {
                System.out.println("End program");
                break;
            }

            try {
                int userID = Integer.parseInt(input);
                //sprawdz czy ID is correct
                if(isValidUserId(userID, validUserIds)) {
                    entryDates.put(userID, new Date());
                    System.out.println("User: " + userID + " " + userNames.get(userID) + " Entry date: " + getCurrentTimestamp());
                } else {
                    System.out.println("Can't find this ID number, try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error");
            }
        }
        scanner.close();

    }

    private String getCurrentTimestamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
                return dateFormat.format(date);
    }

    private boolean isValidUserId(int userId, int[] validUserIds) {
        for (int validId : validUserIds) {
            if (userId == validId) {
                return true;
            }
        }
        return false;
    }
}
