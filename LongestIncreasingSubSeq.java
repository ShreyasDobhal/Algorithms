package CodeChef;

import java.util.*;
import java.io.*;
 
class LongestIncreasingSubSeq
{
    /**
     * O(n2)
     */
    public static int longestIncreasingSubSeq(int a[],int n)
    {
        int memo[]=new int[n];
        int ans=0;
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<i;j++)
                if (a[i]>a[j])
                    memo[i]=Math.max(memo[i],memo[j]);
            memo[i]+=1;
            ans=Math.max(ans,memo[i]);
        }
        return ans;
    }
} 