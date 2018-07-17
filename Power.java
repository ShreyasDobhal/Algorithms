package CodeChef;

class Power
{
    /**
     * Modular Exponentiation
     * if mod=0 num raised to power p is returned
     * else (num raised to power p)%mod is returned
     * O(log n)
     */
    public static long pow(long num,long p,long mod)
    {
        long ans=1;
        while (p>0)
        {
            if ((p&1)==0)
            {
                num=((num%mod)*(num%mod))%mod;
                p/=2;
            }
            else
            {
                ans=((ans%mod)*(num%mod))%mod;
                p-=1;
            }
        }
        return ans;
    }
    /**
     * O(log n)
     */
    public static long pow(long x,int y)
    {
        if (y==0)
            return 1;
        long tmp=pow(x,y/2);
        if (y%2==0)
            return tmp*tmp;
        else
            return tmp*tmp*x;
    }
    /**
     * O(log n)
     */
    public static double pow(double x,int y)
    {
        if (y==0)
            return 1;
        double tmp=pow(x,y/2);
        if (y%2==0)
            return tmp*tmp;
        else
        {
            if (y<0)
                return tmp*tmp/x;
            else
                return tmp*tmp*x;
        }
    }
    /**
     * O(1)
     */
    public static int log2(int n)
    {
        if(n <= 0)
            throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }
}