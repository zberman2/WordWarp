
/**
 * The purpose of this code is to read a text file and copy the contents into
 * a StringBuffer and produce a string representation of the original file.
 * The original code can be found here : http://www.kodejava.org/examples/28.html
 * 
 * I put some minor tweaks into the code so instead of it returning single
 * StringBuffer, it has an array of them so that I can select individual words
 * from the dictionary.
 */
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.util.ArrayList;
public class Dictionary {
    private File file; 
    private ArrayList<StringBuffer> words;
    private StringBuffer contents; 
    private BufferedReader reader = null;     

    /**
     * Constructor for the dictionary class that gets the text file to be used.
     * Must make sure the directory is correct for the file.
     */
    public Dictionary()
    {
        file = new File("/Users/zack_berman/Documents/APCS/WordWarp/dictionary.txt");
        words =  new ArrayList<>();
        contents = new StringBuffer();
        reader = null;
    }

    /**
     * Returns an arrayList of strings containing all the words in the dictionary.
     */
    public ArrayList<String> dictionary()
    {
        ArrayList<String> a = new ArrayList<>();
        try { 
            reader = new BufferedReader(new FileReader(file)); 
            String text = null; 

            // repeat until all lines is read 
            while ((text = reader.readLine()) != null) { 
                words.add(contents.append(text) 
                    .append(System.getProperty( 
                            "line.separator")));
                contents =  new StringBuffer();        
            } 
        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (reader != null) { 
                    reader.close(); 
                } 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 

        for (StringBuffer b: words)
            a.add(b.toString());
        return a;    
    }
} 
