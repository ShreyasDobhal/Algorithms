package CodeChef;

import java.util.*;

class KMP
{
    /**
     * O(m + n)
     */
    public static int KMPMatch(String a,String b)
    {
        int n=a.length();
        int m=b.length();
        int arr[]=prefixArray(b);
        for (int i=0,j=0;i<n;i++)
        {
            if  (a.charAt(i)==b.charAt(j))
            {
                j++;
                if (j==m)
                    return i-j+1;
            }
            else
            {
                if (j!=0)
                {
                    j=arr[j-1];
                    i--;
                }
            }
        }
        return -1;
    }
    /**
     * O(m)
     */
    public static int[] prefixArray(String b)
    {
        int m=b.length();
        int arr[]=new int[m];
        for (int i=1,j=0;i<m;i++)
        {
            if (b.charAt(i)!=b.charAt(j))
            {
                if (j==0)
                    arr[i]=0;
                else
                {
                    j=arr[j-1];
                    i--;
                }
            }
            else
            {
                arr[i]=j+1;
                j++;
            }
        }
        return arr;
    }
}