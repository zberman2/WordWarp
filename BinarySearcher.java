import java.util.ArrayList;
/**
A class for executing binary searches through an array.
 */
public class BinarySearcher
{  
    private ArrayList<String> a;

    /**
    Constructs a BinarySearcher.
    @param anArray a sorted array of integers
     */
    public BinarySearcher(ArrayList<String> anArray)
    {
        a = anArray;
    }

    /**
    Finds a value in a sorted array, using the binary
    search algorithm.
    @param v the value to search
    @return the index at which the value occurs, or -1
    if it does not occur in the array
     */
    public boolean search(String v){  
        int low = 0;
        int high = a.size() - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            int diff = a.get(mid).compareTo(v);

            if (diff == 0) 
                return true;
            else if (diff < 0)
                low = mid + 1;
            else
                high = mid - 1;         
        }
        return false;
    }

    public static void test(){
        ArrayList<String> shortDict = new ArrayList<String>();
        shortDict.add("apples");
        shortDict.add("banana");
        shortDict.add("carrot");
        shortDict.add("orange");
        shortDict.add("zealot");
        
        BinarySearcher b = new BinarySearcher(shortDict);
        System.out.println(b.search("apples"));
        System.out.println(b.search("banana"));
        System.out.println(b.search("carrot"));
        System.out.println(b.search("orange"));
        System.out.println(b.search("appls"));
        System.out.println(b.search("zealot"));
    }
}