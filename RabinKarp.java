package CodeChef;

import java.util.*;
import static java.lang.Math.*;

class RabinKarp
{
    public static final int prime=3;
    public static final int mod=prime;
    /**
     * O(mn)
     */
    public static int matchString(String a,String b)
    {
        int n=a.length();
        int m=b.length();
        int hashA=0,hashB=0;
        for (int i=0;i<m;i++)
        {
            hashA+=a.charAt(i)*pow(prime,i);
            hashB+=b.charAt(i)*pow(prime,i);
        }
        for (int i=0;i<=n-m;i++)
        {
            if (hashA==hashB)
            {
                for (int j=0;j<m;j++)
                {
                    if (a.charAt(i+j)!=b.charAt(j))
                        break;
                    if (j==m-1)
                        return i;
                }
            }
            if (i+m==n)
                break;
            hashA=(hashA-a.charAt(i))/prime+(int)(a.charAt(i+m)*pow(prime,m-1));
        }
        return -1;
    }
}