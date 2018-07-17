package CodeChef;

class GCD
{
    /**
     * O(log n)
     */
    public static int gcd(int a, int b)
    {
        while (b>0)
        {
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
    /**
     * O(log n)
     */
    public static long gcd(long a, long b)
    {
        while (b>0)
        {
            long temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
    /**
     * use gcdExtended(a,b,1,1)
     * O(log n)
     */
    public static int gcdExtended(int a, int b, int x, int y)
    {
        if (a==0)
        {
            x=0;
            y=1;
            return b;
        }
        int x1=1,y1=1;
        int gcd=gcdExtended(b%a,a,x1,y1);
        x=y1-(b/a)*x1;
        y=x1;
        return gcd;
    }
}