import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static Scanner OpenFile(File movies) {
        Scanner fileScanner = null;
        {
            try {
                fileScanner = new Scanner(movies);
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                //e.printStackTrace();
            }
        }
        return fileScanner;
    }

    public static void main(String[] args) {
        //open file movies.txt
        File movies = new File("movies.txt");
        //read the file to count total movies
        Scanner fileScanner = OpenFile(movies);
        //count total lines/movies
        int lineCount = 0;
        while (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
            lineCount++;
        }
        //random number between 1 and total lines
        int random = (int) (Math.random() * lineCount + 1);
        //read the file from the start again
        fileScanner = OpenFile(movies);
        String guessThis = "";
        //get random line in the file as movies name to guess
        int i = 0;
        while (i != random) {
            guessThis = fileScanner.nextLine();
            i++;
        }
        //initialize user guessing string
        char[] guessing = new char[guessThis.length()];
        for (i = 0; i < guessThis.length(); i++) {
            guessing[i] = '_';
        }
        //initializing for user input
        String wrongGuesses = "";
        int wrongCount = 0;
        System.out.println("We have random choose a movies title" +
                "\n" + new String(guessing) +
                "\nPlease guess a character: ");
        while (!guessThis.equals(new String(guessing))) {
            //scan user input
            Scanner userGuess = new Scanner(System.in);
            String temp = userGuess.nextLine();
            //handle wrong user input
            while (temp.length() > 1) {
                System.out.println("Wrong format, please input a character: ");
                userGuess = new Scanner(System.in);
                temp = userGuess.nextLine();
            }
            //matching
            boolean match = false;
            for (i = 0; i < guessThis.length(); i++) {
                if (guessThis.charAt(i) == temp.charAt(0)) {
                    guessing[i] = temp.charAt(0);
                    match = true;
                }
            }
            //break if winning
            if (guessThis.equals(new String(guessing))){
                break;
            }
            //wrong guess
            if (!match && !((wrongGuesses.indexOf(temp.charAt(0))) >= 0)) {
                wrongGuesses = wrongGuesses.concat(temp + " ");
                wrongCount++;
            }
            System.out.println("Your guessing so far: " + new String(guessing) +
                    "\nYour have " + wrongCount + " wrong guesses are: " + wrongGuesses);
            System.out.println("Please guess another character: ");
        }
        //ending
        System.out.println("The name of the movie is: " + guessThis +
                "\nYou won! Congratulation!!");
    }
}