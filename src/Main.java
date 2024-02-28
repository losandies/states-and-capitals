import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
//      Create new scanner instance
        Scanner scanner = new Scanner(System.in);
//      Initiate the program
        boolean on = true;

        System.out.println("Array Display");
//      List out all states and capitals in original order
        for(String[] stateAndCapital : StatesAndCapitals.list) {
                System.out.println(stateAndCapital[0] + ", " + stateAndCapital[1]);
            }

        System.out.println("\nMap Display");
//      Create map object of states and capitals to fulfill part 2
        HashMap<String, String> stateCapitalMap = StatesAndCapitals.convertToMap();
        // Display content of the map
        StatesAndCapitals.displayMap(stateCapitalMap);
        // use TreeMap class to sort the map
        TreeMap<String, String> sortedStateCapitalMap = new TreeMap<>(stateCapitalMap);

        // While program is running, do this
        while(on) {
            System.out.println();
//      Menu for user to select whether they want to take the quiz or search for capitals by state
            System.out.println("What would you like to do?");
            System.out.println("1) Play the Capitals Quiz");
            System.out.println("2) Search for a Capital by State");
            System.out.println("3) Quit\n");
            System.out.print("Time to make a choice (1, 2, 3): ");

//      Check to see if scanner's next input is an integer
            if (scanner.hasNextInt()) {
                // Get user input
                int input = scanner.nextInt();
                // Consume the newline character
                scanner.nextLine();
                // Check user's input
                if (input == 1) {
                    startQuiz();
                } else if (input == 2) {
                    System.out.print("Enter the state that you would like to know the capital of: ");
                    String stateInput = scanner.nextLine();
                    StatesAndCapitals.searchCapital(sortedStateCapitalMap, stateInput);
                } else if (input == 3) {
                    // Quit program
                    on = false;
                } else {
                    System.out.println("Please enter a valid selection.");
                }
            } else {
                // Consume non-integer input to avoid Scanner getting stuck
                scanner.next();
                System.out.println("Please enter a valid selection (an integer).");
            }
        }
    }

    // Bubble sort method to sort a 2D array based on the second column
    public static String[][] bubbleSort(String[][] arr) {
        int n = arr.length;

        // Outer loop for the number of passes
        for (int i = 0; i < n - 1; i++) {
            // Inner loop for each pass, comparing and swapping elements
            for (int j = 0; j < n - i - 1; j++) {
                // Compare the capitals and swap if needed
                if (arr[j][1].compareTo(arr[j + 1][1]) > 0) {
                    String[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr; // Return the sorted array
    }
    // Quiz
    public static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        int score = 0;
        // Sort the array of states and capitals before the quiz
        String[][] sortedArray = bubbleSort(StatesAndCapitals.list);

        System.out.println("Welcome to the capitals quiz!");

        // Iterate through the sorted array and ask quiz questions
        for (String[] stateAndCapital : sortedArray) {
            System.out.print("What is the capital of " + stateAndCapital[0] + "?: ");
            String answer = scanner.nextLine();

            // Check the answer and update the score
            if (answer.equalsIgnoreCase(stateAndCapital[1])) {
                System.out.println("You got it!");
                score++;
            } else {
                System.out.println("Not quite! The correct answer was " + stateAndCapital[1] + ".");
            }
        }

        // Display the final score
        System.out.println("You scored a " + score + " on the quiz!");
    }
}