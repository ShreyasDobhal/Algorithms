package CodeChef;

import java.util.*;
import java.io.*;
 
class LongestPalindromicSubSeq
{
    /**
     * O(n2)
     */
    public static int longestIncreasingSubSeq(String str,int n)
    {
        int dp[][]=new int[n][n];
        for (int i=0;i<n;i++)
            dp[i][i]=1;
        for (int k=2;k<=n;k++)
            for (int i=0;i<n-k+1;i++)
            {
                int j=i+k-1;
                if (str.charAt(i)!=str.charAt(j))
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                else if (k==2)
                    dp[i][j]=2;
                else
                    dp[i][j]=2+dp[i+1][j-1];
            }
        return dp[0][n-1];
    }
} 