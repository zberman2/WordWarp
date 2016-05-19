
import java.util.ArrayList;
import java.util.Random;
/**
 * Based on our dictionary (a List of Strings) a random 6 letter 
 * word will be chosen. Permutations of that word are then generated 
 * with 3, 4, 5 and 6 character words. These permutations are then 
 * cross-referenced by the dictionary and permutations that are 
 * actual words will then be put in a List of words used in the game.
 * 
 * @author Zack Berman
 * @version May 10, 2012
 */
public class Words
{
    private String letters, shuffledLetters;
    private ArrayList<String> dictionary;
    private ArrayList<String> words7, words6, words5, words4, words3;
    private ArrayList<String> gameWords, encrypted;
    private int points;

    /**
     * Constructs a Words object.
     * A six letter word is chosen from a list of 6 
     * letter words and is assigned to letters.
     * shuffledLetters is assigned to letters
     * words6, words5, words4, words3, gameWords, 
     * encrypted and dictionary are instantiated 
     * as blank ArrayLists of Strings
     * @param SixLetter Words an ArrayList of 6 character Strings
     */
    public Words(ArrayList<String> XLetterWords){
        letters = XLetterWords.get((int)(Math.random() * XLetterWords.size()));
        shuffledLetters = letters;
        words7 = new ArrayList<String>();
        words6 = new ArrayList<String>();
        words5 = new ArrayList<String>();
        words4 = new ArrayList<String>();
        words3 = new ArrayList<String>();
        gameWords = new ArrayList<String>();
        encrypted = new ArrayList<String>();
        dictionary = new ArrayList<String>();
        points = 0;
    }

    /**
     * Returns a copy of the word being used in the game
     * @return letters. */
    public String getWord(){return letters;}

    /**
     * Returns a copy of the shuffled Word
     * @return shuffledLetters */
    public String getShuffledWord(){return shuffledLetters;}

    /**
     * Returns a copy of the ArrayList of 7 letter words
     * @return words7 */
    public ArrayList<String> getWords7(){return words7;}

    /**
     * Returns a copy of the ArrayList of 6 letter words
     * @return words6 */
    public ArrayList<String> getWords6(){return words6;}

    /**
     * Returns a copy of the ArrayList of 5 letter words
     * @return words5 */
    public ArrayList<String> getWords5(){return words5;}

    /**
     * Returns a copy of the ArrayList of 4 letter words
     * @return words4 */
    public ArrayList<String> getWords4(){return words4;}

    /**
     * Returns a copy of the ArrayList of 3 letter words
     * @return words3 */
    public ArrayList<String> getWords3(){return words3;}

    /**
     * Returns a copy of the ArrayList of a words to be 
     * used in the Game
     * @return gameWords */
    public ArrayList<String> getGameWords(){return gameWords;}

    /**
     * Returns a copy of the ArrayList of encrypted gameWords
     * @return encrypted */
    public ArrayList<String> getEncrypted(){return encrypted;}

    /**
     * Returns the number of points earned by a player through 
     * correctly guessed words.
     * @return points */
    public int getPoints(){return points;}

    /**
     * Mutator method that sets the dictionary ArrayList of strings to 
     * the ArrayList of Strings in the parameter
     * @param dict words in the Dictionary (6 or fewer letters) */
    public void setDictionary(ArrayList<String> dict){dictionary = dict;}

    /**
     * Decrypts an individual word in the encrypted ArrayList
     * @param index Index of the encrypted ArrayList
     * at this index, the encrypted word is decrypted */
    public void solvedWord(int index, Time t){
        if (t.getSeconds() > 0){
            if (isSolved(index))return;
            encrypted.set(index,gameWords.get(index));
            if (gameWords.get(index).length() == 3)points += 10;
            if (gameWords.get(index).length() == 4)points += 25;
            if (gameWords.get(index).length() == 5)points += 50;
            if (gameWords.get(index).length() == 6)points += 100;
            if (gameWords.get(index).length() == 7)points += 200;
        }
        else encrypted.set(index,gameWords.get(index).toUpperCase());
    }

    public int getPossiblePoints(){
        return (words7.size() * 200) + 
        (words6.size() * 100) + (words5.size() * 50) + 
        (words4.size() * 25) + (words3.size() * 10);
    }

    /**
     * Boolean method that returns true if a word in the 
     * encrypted list has been solved
     * @param index Index of the encrypted list
     * @return true if the word at index has been solved. */
    public boolean isSolved(int index){return !encrypted.get(index).substring(0,1).equals("?");}

    /**
     * Shuffles the word being used in the game
     * ex.) letters = "orange" shuffledLetters = "orange"
     * after shuffleLetters(), shuffledLetters = "groane" */
    public void shuffleLetters(){
        Random gen = new Random();
        ArrayList<String> chars = new ArrayList<String>();
        for (int i = 0; i < letters.length(); i++)
            chars.add(letters.substring(i,i+1));
        String s = "";
        for (int j = 0; j < letters.length(); j++){
            int x = gen.nextInt(chars.size());
            s += chars.remove(x);
        }
        shuffledLetters = s;
    }

    /**
     * Permutations algorithm that adds all permutations of a 
     * String (String z) to an ArrayList (perms)
     * @param a Beginning String (initially "")
     * @param z Ending String (initially the String to be permutated)
     * @param perms ArrayList of permutations
     */
    public void permutations(String a, String z, ArrayList<String> perms){
        if (z.length() <= 1){
            if(!inSaved(a+z, perms))
                perms.add(a + z);
        }
        else{
            for (int i = 0; i < z.length(); i++){
                String temp = z.substring(0,i) + z.substring(i+1);
                permutations(a + z.charAt(i), temp, perms);
            }
        }
    }

    /**
     * Checks if a String is in a certain ArrayList of strings
     * @param s String to be checked for
     * @param saved ArrayList to search through
     * @return true if s is in saved
     */
    public boolean inSaved(String s, ArrayList<String> saved){
        for (String str : saved){
            if (s.equals(str))
                return true;
        }
        return false;
    }

    /**
     * Difficulty Level 1
     * Creates 5,4, and 3 letter permutations of the 5 
     * letter String letters. 
     * MergeSorter is used to sort these lists.
     * If a permutation is a word in the dictionary, it is added 
     * to gameWords.  If not, it is thrown out.
     * encrypted is made with 3,4, and 5 character ? mark 
     * Strings to match the length of gameWords
     */

    public void makeWords1(){
        permutations("", letters, words5);
        for (int j = 0; j < letters.length(); j++){
            letters = rotate(letters);
            permutations("",letters.substring(1), words4);
        }
        int x = words5.size() / 20;
        for (int k = 0; k < words4.size(); k+=x)
            permutations("",words4.get(k).substring(1), words3);
        MergeSorter2 w5 = new MergeSorter2(words5);
        MergeSorter2 w4 = new MergeSorter2(words4);
        MergeSorter2 w3 = new MergeSorter2(words3);
        w5.sort(); w4.sort(); w3.sort();

        BinarySearcher bDict = new BinarySearcher(dictionary);
        
        for (int t = 0; t < words3.size(); t++){
            if (bDict.search(words3.get(t)))
            {gameWords.add(words3.get(t)); encrypted.add("???");}
            else {words3.remove(t); t--;}
        }
        for (int s = 0; s < words4.size(); s++){
            if (bDict.search(words4.get(s))) 
            {gameWords.add(words4.get(s)); encrypted.add("????");}
            else {words4.remove(s); s--;}
        }
        for (int r = 0; r < words5.size(); r++){
            if (bDict.search(words5.get(r))) 
            {gameWords.add(words5.get(r)); encrypted.add("?????");}
            else {words5.remove(r); r--;}
        }
        
    }

    /**
     * Difficulty Level 2
     * Creates 6,5,4, and 3 letter permutations of the 6 
     * letter String letters. 
     * MergeSorter is used to sort these lists.
     * If a permutation is a word in the dictionary, it is added 
     * to gameWords.  If not, it is thrown out.
     * encrypted is made with 3,4,5, and 6 character ? mark 
     * Strings to match the length of gameWords
     */
    public void makeWords2(){
        permutations("", letters, words6);
        for (int j = 0; j < letters.length(); j++){
            letters = rotate(letters);
            permutations("",letters.substring(1), words5);
        }
        int x = words6.size() / 30;
        for (int k = 0; k < words5.size(); k+=x)
            permutations("",words5.get(k).substring(1), words4);
        int y = x / 6;
        for (int l = 0; l < words4.size(); l+=y)
            permutations("",words4.get(l).substring(1), words3);
        MergeSorter2 w6 = new MergeSorter2(words6);
        MergeSorter2 w5 = new MergeSorter2(words5);
        MergeSorter2 w4 = new MergeSorter2(words4);
        MergeSorter2 w3 = new MergeSorter2(words3);
        w6.sort(); w5.sort(); w4.sort(); w3.sort();

        BinarySearcher bDict = new BinarySearcher(dictionary);
        
        for (int t = 0; t < words3.size(); t++){
            if (bDict.search(words3.get(t)))
            {gameWords.add(words3.get(t)); encrypted.add("???");}
            else {words3.remove(t); t--;}
        }
        for (int s = 0; s < words4.size(); s++){
            if (bDict.search(words4.get(s))) 
            {gameWords.add(words4.get(s)); encrypted.add("????");}
            else {words4.remove(s); s--;}
        }
        for (int r = 0; r < words5.size(); r++){
            if (bDict.search(words5.get(r))) 
            {gameWords.add(words5.get(r)); encrypted.add("?????");}
            else {words5.remove(r); r--;}
        }
        for (int q = 0; q < words6.size(); q++){
            if (bDict.search(words6.get(q))) 
            {gameWords.add(words6.get(q)); encrypted.add("??????");}
            else {words6.remove(q); q--;}
        }
        
    }

    /**
     * Difficulty Level 3
     * Creates 7, 6,5,4, and 3 letter permutations of the 7 
     * letter String letters. 
     * MergeSorter is used to sort these lists.
     * If a permutation is a word in the dictionary, it is added 
     * to gameWords.  If not, it is thrown out.
     * encrypted is made with 3,4,5,6 and 7 character ? mark 
     * Strings to match the length of gameWords
     */

    public void makeWords3(){
        permutations("", letters, words7);
        for (int j = 0; j < letters.length(); j++){
            letters = rotate(letters);
            permutations("",letters.substring(1), words6);
        }
        int x = words7.size() / 42;
        for (int k = 0; k < words6.size(); k+=x)
            permutations("",words6.get(k).substring(1), words5);
        int y = x / 5;
        for (int l = 0; l < words5.size(); l+=y)
            permutations("",words5.get(l).substring(1), words4);
        int z = y / 4;
        for (int m = 0; m < words4.size(); m+=z)
            permutations("",words4.get(m).substring(1), words3);
        MergeSorter2 w7 = new MergeSorter2(words7);
        MergeSorter2 w6 = new MergeSorter2(words6);
        MergeSorter2 w5 = new MergeSorter2(words5);
        MergeSorter2 w4 = new MergeSorter2(words4);
        MergeSorter2 w3 = new MergeSorter2(words3);
        w7.sort(); w6.sort(); w5.sort(); w4.sort(); w3.sort();

        BinarySearcher bDict = new BinarySearcher(dictionary);
        
        for (int t = 0; t < words3.size(); t++){
            if (bDict.search(words3.get(t)))
            {gameWords.add(words3.get(t)); encrypted.add("???");}
            else {words3.remove(t); t--;}
        }
        for (int s = 0; s < words4.size(); s++){
            if (bDict.search(words4.get(s))) 
            {gameWords.add(words4.get(s)); encrypted.add("????");}
            else {words4.remove(s); s--;}
        }
        for (int r = 0; r < words5.size(); r++){
            if (bDict.search(words5.get(r))) 
            {gameWords.add(words5.get(r)); encrypted.add("?????");}
            else {words5.remove(r); r--;}
        }
        for (int q = 0; q < words6.size(); q++){
            if (bDict.search(words6.get(q))) 
            {gameWords.add(words6.get(q)); encrypted.add("??????");}
            else {words6.remove(q); q--;}
        }
        for (int r = 0; r < words7.size(); r++){
            if (bDict.search(words7.get(r))) 
            {gameWords.add(words7.get(r)); encrypted.add("???????");}
            else {words7.remove(r); r--;}
        }
        
    }
    
    /**
     * Returns a string that is a rotated version of 
     * the inputed string. ex) 1234 --> 2341
     * @param orig Original String
     * @return str Rotated String
     */
    public String rotate(String orig){
        String temp = "";
        for (int i = 0; i < orig.length()-1; i++){
            temp += orig.substring(i+1, i+2);
        }
        String str = temp + orig.substring(0,1);
        return str;
    }

    public static void Tester(){
        ArrayList<String> shortDict = new ArrayList<String>();
        shortDict.add("aepikda");
        Dictionary a = new Dictionary();
        ArrayList<String> b = a.dictionary();
        ArrayList<String> sevenOrFewer = new ArrayList<>();
        ArrayList<String> sevenLetters = new ArrayList<>();
        for (String c : b)
        {
            c = c.substring(0,c.length()-2);
            if (c.length()<=7)    
                sevenOrFewer.add(c);
            if (c.length() == 7)
                sevenLetters.add(c);
        }
        Words test = new Words(shortDict);
        test.setDictionary(sevenOrFewer);
        test.makeWords3();
        System.out.println(test.getGameWords());
    }
}
