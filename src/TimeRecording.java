import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
            System.out.println("Enter your ID number or 'exit, end' to close program");
            String input = scanner.next();

            if(input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("end")) {
                System.out.println("End program");
                break;
            }

            try {
                int userID = Integer.parseInt(input);
                //sprawdz czy ID is correct
                if(isValidUserId(userID, validUserIds)) {
                    entryDates.put(userID, new Date());
                    System.out.println("User: " + userID + " " + userNames.get(userID) + " Entry date: " + getCurrentTimestamp());

                    int currentHour = getHourOfDay();
                    String greetingMessage = getMessageForUserBasedOnTime(currentHour);
                    System.out.println(greetingMessage);

                    int funMessage = getHourOfDay();
                    String messages = verifyTime(funMessage);
                    System.out.println(messages);

                    //save data to file
                    saveDateToFile(userID, userNames.get(userID),getCurrentTimestamp(), greetingMessage);

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

    private String getMessageForUserBasedOnTime(int hourOfDay) {
        if(hourOfDay < 12) {
            return "ENTRY";
        } else {
            return "EXIT";
        }
    }

    private int getHourOfDay() {
        Date date = new Date();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        return Integer.parseInt(hourFormat.format(date));
    }

    private String verifyTime(int time) {
        if(time > 22) {
            return "It's late you must go home :)";
        } else {
            return "Have fun";
        }
    }

    private void saveDateToFile(int userId, String userName, String entryDate, String greetingMessage) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt",true))) {
            writer.write("User ID: " + userId + " | Name: " + userName + " | Entry Date: " + entryDate + " | Greeting: " + greetingMessage + "\n");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
