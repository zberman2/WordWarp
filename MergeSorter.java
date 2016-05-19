/**
 * Joel Siegel
 * MergeSorter that sorts strings alphabetically
 */
public class MergeSorter
{
    private String[] a;
    /**
     * Constructor for the MergeSorter class
     */
    public MergeSorter(String[] strings)
    {
        a = strings;
    }

    /**
    Sorts the array managed by this merge sorter.
     */
    public void sortAlpha()
    {  
        if (a.length <= 1) return;
        String[] first = new String[a.length / 2];
        String[] second = new String[a.length - first.length];
        // Copy the first half of a into first, the second half into second
        for (int i = 0; i < first.length; i++) { first[i] = a[i]; }
        for (int i = 0; i < second.length; i++) 
        { 
            second[i] = a[first.length + i]; 
        }
        MergeSorter firstSorter = new MergeSorter(first);
        MergeSorter secondSorter = new MergeSorter(second);
        firstSorter.sortAlpha();
        secondSorter.sortAlpha();
        mergeAlpha(first, second);
    }

    /**
    Merges two sorted arrays into the array managed by this merge sorter. 
    @param first the first sorted array
    @param second the second sorted array
     */
    private void mergeAlpha(String[] first, String[] second)
    {  
        int iFirst = 0; // Next element to consider in the first array
        int iSecond = 0; // Next element to consider in the second array
        int j = 0; // Next open position in a

        // As long as neither iFirst nor iSecond is past the end, move
        // the smaller element into a
        while (iFirst < first.length && iSecond < second.length)
        {  
            if (first[iFirst].compareTo(second[iSecond])<0)
            {  
                a[j] = first[iFirst];
                iFirst++;
            }
            else
            {  
                a[j] = second[iSecond];
                iSecond++;
            }
            j++;
        }

        while (iFirst < first.length) 
        { 
            a[j] = first[iFirst];
            iFirst++; j++;
        }

        while(iSecond < second.length)
        {
            a[j] = second[iSecond];
            iSecond++; j++;
        }
    }
    
    /**
    Sorts the array managed by this merge sorter.
     */
    public void sortSize()
    {  
        if (a.length <= 1) return;
        String[] first = new String[a.length / 2];
        String[] second = new String[a.length - first.length];
        // Copy the first half of a into first, the second half into second
        for (int i = 0; i < first.length; i++) { first[i] = a[i]; }
        for (int i = 0; i < second.length; i++) 
        { 
            second[i] = a[first.length + i]; 
        }
        MergeSorter firstSorter = new MergeSorter(first);
        MergeSorter secondSorter = new MergeSorter(second);
        firstSorter.sortSize();
        secondSorter.sortSize();
        mergeSize(first, second);
    }

    /**
    Merges two sorted arrays into the array managed by this merge sorter. 
    @param first the first sorted array
    @param second the second sorted array
     */
    private void mergeSize(String[] first, String[] second)
    {  
        int iFirst = 0; // Next element to consider in the first array
        int iSecond = 0; // Next element to consider in the second array
        int j = 0; // Next open position in a

        // As long as neither iFirst nor iSecond is past the end, move
        // the smaller element into a
        while (iFirst < first.length && iSecond < second.length)
        {  
            if (first[iFirst].length()<second[iSecond].length())
            {  
                a[j] = first[iFirst];
                iFirst++;
            }
            else
            {  
                a[j] = second[iSecond];
                iSecond++;
            }
            j++;
        }

        while (iFirst < first.length) 
        { 
            a[j] = first[iFirst];
            iFirst++; j++;
        }

        while(iSecond < second.length)
        {
            a[j] = second[iSecond];
            iSecond++; j++;
        }
    }
    /**
     * Returns a string representation of the MergeSorter Class
     */
    public String toString()
    {
        String s = "";
        for (String d : a)
        s+=d + " ";
        return s;
    }
}