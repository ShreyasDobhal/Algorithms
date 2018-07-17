package CodeChef;

import java.util.*;
import java.io.*;
 
class LongestCommonSubSeq
{
    /**
     * O(nm)
     */
    public static int longestCommonSubSeq(String a,String b)
    {
        int n=a.length();
        int m=b.length();
        int arr[][]=new int[n+1][m+1];
        for (int i=1;i<=n;i++)
        {
            for (int j=1;j<=m;j++)
            {
                if (a.charAt(i-1)==b.charAt(j-1))
                    arr[i][j]=arr[i-1][j-1]+1;
                else
                    arr[i][j]=Math.max(arr[i-1][j],arr[i][j-1]);
            }
        }
        return arr[n][m];
    }
} 