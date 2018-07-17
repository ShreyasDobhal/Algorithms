package CodeChef;

import java.util.*;
import java.io.*;
 
class EditDistance
{
    /**
     * O(nm)
     */
    public static int editDistance(String a,String b)
    {
        int n=a.length();
        int m=b.length();
        int arr[][]=new int[n+1][m+1];
        for (int i=0;i<=n;i++)
            arr[i][0]=i;
        for (int i=0;i<=m;i++)
            arr[0][i]=i;
            
        for (int i=1;i<=n;i++)
        {
            for (int j=1;j<=m;j++)
            {
                if (a.charAt(i-1)==b.charAt(j-1))
                    arr[i][j]=arr[i-1][j-1];
                else
                    arr[i][j]=Math.min(arr[i-1][j],Math.min(arr[i][j-1],arr[i-1][j-1]))+1;
            }
        }
        return arr[n][m];
    }
} 