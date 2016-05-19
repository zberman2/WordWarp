import java.util.ArrayList;
/**
   This class sorts an array, using the merge sort algorithm.
   Zack Berman March 14, 2012 */
public class MergeSorter2 {
   private ArrayList<String> a;

   /**
      Constructs a merge sorter.
      @param anArray the array to sort */
   public MergeSorter2(ArrayList<String> anArray){a = anArray;}
   
   /**
      Sorts the array managed by this merge sorter. */
   public void sort(){  
      if (a.size() <= 1) return;
      int x = a.size() / 2;
      int y = a.size() - x;
      ArrayList<String> first = new ArrayList<String>();
      ArrayList<String> second = new ArrayList<String>();
      // Copy the first half of a into first, the second half into second
      for (int i = 0; i < x; i++) { first.add(i, a.get(i)); }
      for (int i = 0; i < y; i++) 
         second.add(i, a.get(x+i)); 
      MergeSorter2 firstSorter = new MergeSorter2(first);
      MergeSorter2 secondSorter = new MergeSorter2(second);
      firstSorter.sort();
      secondSorter.sort();
      merge(first, second);
   }

   /**
      Merges two sorted arrays into the array managed by this merge sorter. 
      @param first the first sorted array
      @param second the second sorted array
   */
   private void merge(ArrayList<String> first, ArrayList<String> second)
   {  
      int iFirst = 0; // Next element to consider in the first array
      int iSecond = 0; // Next element to consider in the second array
      int j = 0; // Next open position in a

      // As long as neither iFirst nor iSecond is past the end, move
      // the smaller element into a
      while (iFirst < first.size() && iSecond < second.size()){  
         if (first.get(iFirst).compareTo(second.get(iSecond)) < 0){  
            a.set(j, first.get(iFirst));
            iFirst++;
         }
         else{  
            a.set(j, second.get(iSecond));
            iSecond++;
         }
         j++;
      }

      // Note that only one of the two loops below copies entries
      // Copy any remaining entries of the first array
      while (iFirst < first.size()) { 
         a.set(j, first.get(iFirst));
         iFirst++; j++;
      }
      // Copy any remaining entries of the second half
      while (iSecond < second.size()) { 
         a.set(j, second.get(iSecond));
         iSecond++; j++;
      }
   }
}
