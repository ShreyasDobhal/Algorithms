package CodeChef;

import java.util.*;

class ModInverse
{
    /**
     * a and m must be coprime
     * O(log m)
     */
    public static int modInverse(int a, int m)
    {
        int m0=m;
        int y=0,x=1;
        if (m==1)
            return 0;
        while (a>1)
        {
            int q=a/m;
            int t=m;
            m=a%m;
            a=t;
            t=y;
            y=x-q*y;
            x=t;
        }
        if (x<0)
            x+=m0;
        return x;
    }
    /**
     * Fermats's Little Theorem
     * m must be prime
     * O(log m)
     */
    public static long modInverse(long a, long m)
    {
        return power(a,m-2,m);
    }
    public static long power(long x, long y, long m) 
    {
        if (y==0)
            return 1;
        long p=power(x,y/2,m)%m;
        p=(p*p)%m;
        if (y%2==0)
            return p;
        else
            return (x*p)%m;
    }
    public static long mod = 1000000007;
    public static long pow(long a,long b)
    {
    	if (b==0)
    		return 1;
    	long tmp=pow(a,b/2)%mod;
    	tmp=(tmp*tmp)%mod;
    	if (b%2==0)
    		return tmp;
    	else
    		return (tmp*a)%mod;
    }
}