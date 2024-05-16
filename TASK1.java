import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Random random = new Random();
        int RandomNumber = random.nextInt(101); // Generate a random number between 1 and 100
        int max = 5; // Maximum number of attempts allowed
        int attempts = max; // Initialize attempts left counter

        System.out.println("Welcome To Guess Number Game");
        System.out.println("You Will Be Asked To Guess A Number To Win The Game");
        System.out.println("You have Maximum 5 Attempt Limit");

        Scanner s = new Scanner(System.in);

        while (attempts > 0) {
            System.out.println("Enter your guess between 1 to 100:");
            int guess = s.nextInt();

            if (guess == RandomNumber) {
                System.out.println("OOhhOO! Your Number is Correct . You Win The Game!");
                break; // Exit the loop since the correct number is guessed
            } else if (guess < RandomNumber) { 
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }

            attempts--; // Decrease attempts left
            System.out.println("Attempts left: " + attempts);
        }

        if (attempts == 0) {
            System.out.println("Sorry, you've used all your attempts. The correct number was: " + RandomNumber);
        }

        s.close();
    }
}
