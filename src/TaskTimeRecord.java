import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskTimeRecord {
    void timeRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");

        int[] cardNumber = {1212, 2323, 3434, 4545, 2222, 3333};



        while (true) {
            System.out.println("Entry your Job Card number or 'exit','end' to close program.");
            String jobCardString = scanner.nextLine();

            if (jobCardString.toLowerCase().equals("exit") || jobCardString.toLowerCase().equals("end") ) {
                break;
            }

            int jobCardNumber;
            try {
                jobCardNumber = Integer.parseInt(jobCardString);
            } catch (NumberFormatException e) {
                System.out.println("Entry correct number");
                continue;
            }

            if(!isValidCardNumber(jobCardNumber, cardNumber)) {
                System.out.println("Can't find this Job Card number. Try again.");
                continue;
            }

            System.out.println("Press Enter to start your job");
            scanner.nextLine();

            long timeStart = System.currentTimeMillis();
            System.out.println("Started! To finish write 'stop' ");

            while (true) {
                String commands = scanner.nextLine();
                if(commands.toLowerCase().equals("stop")) {
                    long timeStop = System.currentTimeMillis();
                    long timeCalc = (timeStop - timeStart) / 1000; //czas w sekundach || zeby bylo w min trzeba dodac /60

                    saveToFile(jobCardNumber,timeStart,timeStop,timeCalc);

                    System.out.println("Job finish. Your time: " + (timeStop - timeStart));
                    break;
                } else {
                    System.out.println("Error. Entry 'stop' to finish");
                }
            }
        }
    }

    private void saveToFile(int jobCardNumber, long timeStart, long timeStop, long timeCalc) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("timeRec.txt",true))) {
            writer.write("Job Card Number: " + jobCardNumber + " | Time Start: " + timeStart + " | Time Stop: " + timeStop + " | Time: " + (timeStop - timeStart) + " | TIME: " + timeCalc + " second" + "\n");

        } catch (IOException e) {
            System.out.println("Error !");
        }
    }

    private boolean isValidCardNumber(int jobCardNumber, int[] cardNumber) {
        for(int validJobCardNumber : cardNumber) {
            if (jobCardNumber == validJobCardNumber) {
                return true;
            }
        }
        return false;
    }
}
