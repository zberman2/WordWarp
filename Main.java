import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import javax.swing.JOptionPane;
/**
 * Game class that starts the WordWarp game
 */
public class Main
{
    static Words w;
    static String shuffledWord;
    static boolean done;
    
    public static void main(String[] args) {
        WordWarp();
    }
    
    /**
     * Main method for WordWarp
     * Prompts the user for a difficulty level and operates the 
     * loop the returns an updated gameBoard every time the user 
     * guesses a word
     */
    private static void WordWarp(){
        done = false;
        Scanner sc = new Scanner(System.in);
        System.out.print("\f");
        System.out.println("Welcome to WordWarp! The goal of the game is to create as many words" +
            "\nas possible out of the given scrambled letters in the allotted time" +
            "\nPlease choose a difficulty level:");
        System.out.println("\nEnter \"1\" for Level 1 (5 letter word)");
        System.out.println("Enter \"2\" for Level 2 (6 letter word)");
        System.out.println("Enter \"3\" for Level 3 (7 letter word)");
        String level = sc.nextLine();
        if (level.equals("1")){
            w = start1();
            w.shuffleLetters();
            shuffledWord = w.getShuffledWord();
            System.out.println("\fYou will have 3 minutes to create as many 3-5 letter words as possible" + 
                "\nout of the given five letters. You may enter \"s\" to scramble the letters," + 
                "\nyou may enter \"p\" to pause the game, and you may enter \"q\" to quit the current" +
                "\ngame. For every word guessed correctly, you will receive points. 3 letter words are" +
                "\nworth 10, 4 letter words are worth 25 and 5 letter words are worth 50. After you quit," + 
                "\nor time runs out, all words that you failed to guess correctly will appear in all caps." + 
                "\nHit <Enter> to begin");
            sc.nextLine();
        }
        else if (level.equals("2")){
            w = start2();
            w.shuffleLetters();
            shuffledWord = w.getShuffledWord();
            System.out.println("\fYou will have 3 minutes to create as many 3-6 letter words as possible" + 
                "\nout of the given six letters. You may enter \"s\" to scramble the letters," + 
                "\nyou may enter \"p\" to pause the game, and you may enter \"q\" to quit the current" +
                "\ngame. For every word guessed correctly, you will receive points. 3 letter words are" +
                "\nworth 10, 4 letter words are worth 25, 5 letter words are worth 50 and 6 letter words" + 
                "\nare worth 100. After you quit, or time runs out, all words that you failed to guess" + 
                "\ncorrectly will appear in all caps. Hit <Enter> to begin");
            sc.nextLine();
        }
        else if (level.equals("3")){
            w = start3();
            w.shuffleLetters();
            shuffledWord = w.getShuffledWord();
            System.out.println("\fYou will have 3 minutes to create as many 3-7 letter words as possible" + 
                "\nout of the given seven letters. You may enter \"s\" to scramble the letters," + 
                "\nyou may enter \"p\" to pause the game, and you may enter \"q\" to quit the current" +
                "\ngame. For every word guessed correctly, you will receive points. 3 letter words are" +
                "\nworth 10, 4 letter words are worth 25, 5 letter words are worth 50, 6 letter words" + 
                "\nare worth 100 and seven letter words are worth 200. After you quit, or time runs out," + 
                "\nall words that you failed to guess correctly will appear in all caps." +
                "\nHit <Enter> to begin");
            sc.nextLine();
        }
        else WordWarp();
        Time t = new Time(181);
        while (!done){
            gameBoard();
            Scanner darkly = new Scanner(System.in);
            System.out.println(t.getTimeLeftMessage());
            System.out.println("Letters: " + shuffledWord + 
                "\nGuess: (\"s\" to shuffle, \"p\" to pause, and \"q\" to quit)");
            String guess = darkly.nextLine();
            
            if (guess.equalsIgnoreCase("q") || t.getSeconds() == 0)
            {
                t.stop();
                for (int i = 0; i<w.getGameWords().size(); i++)
                    if (!w.isSolved(i))w.solvedWord(i, t);
                gameBoard();
                System.out.print("Would you like to play again? (Y for yes and N for no): ");
                String z = darkly.nextLine();
                if (z.equalsIgnoreCase("Y"))
                {done = true; sc.close(); darkly.close(); WordWarp();}

                else{
                    done = true;
                    System.out.println("Thank you for playing!");
                }
            }
            else if (guess.equalsIgnoreCase("s"))
            {
                w.shuffleLetters();
                shuffledWord = w.getShuffledWord();
            }
            else if (guess.equalsIgnoreCase("p")){
                t.pause();
                darkly.nextLine();
                t.resume();
            }
            else{
                for (int i = 0; i<w.getGameWords().size(); i++)
                {
                    if(guess.equalsIgnoreCase(w.getGameWords().get(i)))
                        w.solvedWord(i, t);
                }
            }
            if (w.getPoints() == w.getPossiblePoints()){
                Scanner winner = new Scanner(System.in);
                done = true;
                t.stop(); gameBoard();
                System.out.println("You guessed all of the words!");
                System.out.print("Would you like to play again? (Y for yes and N for no): ");
                String zz = winner.nextLine();
                if (zz.equalsIgnoreCase("Y"))
                {done = true; sc.close(); darkly.close(); winner.close(); WordWarp();}

                else{
                    done = true;
                    System.out.println("Thank you for playing!");
                }
            }
        }
    }

    /**
     * Creates the basic gameBoard template.
     */
    private static void gameBoard()
    {
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        String line6 = "";
        String line7 = "";
        for (int j = 0; j<w.getEncrypted().size(); j++){
            if (j%7==0)
                line1+= w.getEncrypted().get(j)+ getSpaces(w.getEncrypted().get(j));
            else if (j%7 ==1)
                line2+= w.getEncrypted().get(j)+ getSpaces(w.getEncrypted().get(j));
            else if (j%7 ==2)
                line3+= w.getEncrypted().get(j)+ getSpaces(w.getEncrypted().get(j));
            else if (j%7 ==3)
                line4+= w.getEncrypted().get(j)+ getSpaces(w.getEncrypted().get(j));
            else if (j%7 ==4)
                line5+= w.getEncrypted().get(j)+ getSpaces(w.getEncrypted().get(j));
            else if (j%7 ==5)
                line6+= w.getEncrypted().get(j)+ getSpaces(w.getEncrypted().get(j));
            else if (j%7 ==6)
                line7+= w.getEncrypted().get(j)+ getSpaces(w.getEncrypted().get(j));
        }
        
        System.out.print("\f");
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line5);
        System.out.println(line6);
        System.out.println(line7);
        double per = (double)Math.round(((double)w.getPoints() / w.getPossiblePoints()) * 100);
        System.out.println("Points: " + w.getPoints() + " / " + w.getPossiblePoints() +
            " (" + per + "%)");
    }

    private static String getSpaces(String s){
        if (s.length() == 3) return "     ";
        else if (s.length() == 4) return "    ";
        else if (s.length() == 5) return "   ";
        else if (s.length() == 6) return "  ";
        else if (s.length() == 7) return " ";
        else return " ";
    }

    /**
     * Checks to see if the user has solved the board. Not implemented.
     */
    private static boolean solved()
    {
        for (String s: w.getEncrypted())
        {
            if (s.substring(0,1).equals("?"))
                return false;
        }
        return true;
    }

    /**
     *  Creates the initial set of words that a user will be trying to solve based off
     *  of a randomly selected 5 letter word in the dictionary.
     *  Difficulty Level 1
     */

    private static Words start1(){
        Dictionary a = new Dictionary();
        ArrayList<String> b = a.dictionary();
        ArrayList<String> fiveOrFewer = new ArrayList<>();
        ArrayList<String> fiveLetters = new ArrayList<>();
        for (String c : b)
        {
            c = c.substring(0,c.length()-1);
            if (c.length()<=5)    
                fiveOrFewer.add(c);
            if (c.length() == 5)
                fiveLetters.add(c);
        }
        Words words = new Words(fiveLetters);
        words.setDictionary(fiveOrFewer);
        words.makeWords1();
        if (words.getGameWords().size() < 16) return start1();
        return words;
    }

    /**
     *  Creates the initial set of words that a user will be trying to solve based off
     *  of a randomly selected 6 letter word in the dictionary.
     *  Difficulty Level 2
     */
    private static Words start2(){
        Dictionary a = new Dictionary();
        ArrayList<String> b = a.dictionary();
        ArrayList<String> sixOrFewer = new ArrayList<>();
        ArrayList<String> sixLetters = new ArrayList<>();
        for (String c : b)
        {
            c = c.substring(0,c.length()-1);
            if (c.length()<=6)    
                sixOrFewer.add(c);
            if (c.length() == 6)
                sixLetters.add(c);
        }
        Words words = new Words(sixLetters);
        words.setDictionary(sixOrFewer);
        words.makeWords2();
        if (words.getGameWords().size() < 15) return start2();
        return words;
    }

    /**
     *  Creates the initial set of words that a user will be trying to solve based off
     *  of a randomly selected 7 letter word in the dictionary.
     *  Difficulty Level 3
     */

    private static Words start3(){
        Dictionary a = new Dictionary();
        ArrayList<String> b = a.dictionary();
        ArrayList<String> sevenOrFewer = new ArrayList<>();
        ArrayList<String> sevenLetters = new ArrayList<>();
        for (String c : b)
        {
            c = c.substring(0,c.length()-1);
            if (c.length()<=7)    
                sevenOrFewer.add(c);
            if (c.length() == 7)
                sevenLetters.add(c);
        }
        Words words = new Words(sevenLetters);
        words.setDictionary(sevenOrFewer);
        words.makeWords3();
        if (words.getGameWords().size() < 15) return start3();
        return words;
    }
}