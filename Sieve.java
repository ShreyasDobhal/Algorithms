package CodeChef;

import java.util.*;

class Sieve
{
    public static final int size=100;
    public static Set<Integer> primes;
    public static boolean sieve[];
    public static int sv[];
    /**
     * O(n loglog n)
     */
    public static void sieveEratosthenes()
    {
        primes=new HashSet<>();
        sieve=new boolean[size];
        for (int i=2;i<size;i++)
        {
            if (!sieve[i])
            {
                primes.add(i);
                for (int j=2*i;j<size;j+=i)
                    sieve[j]=true;
            }
        }
    }
    public static void sieveFactors()
    {
        sv=new int[size];
        for (int i=2;i<size;i++)
        {
            if (sv[i]==0)
            {
                for (int j=i;j<size;j+=i)
                    if (sv[j]==0)
                        sv[j]=i;
            }
        }
    }
    public static LinkedList factors(int n)
    {
        sieveFactors();
        LinkedList<Integer> fact=new LinkedList<>();
        while (sv[n]!=0)
        {
            fact.add(sv[n]);
            n/=sv[n];
        }
        return fact;
    }
    public static boolean isPrime(int n)
    {
        int l=(int)Math.sqrt(n);
        for (int i=2;i<=l;i++)
            if (n%i==0)
                return false;
        return true;
    }
    public static void driver()
    {
        Vector<Integer> primes=SegmentedSieve(24);
        for (int i:primes)
            System.out.println(i);
        
    }
    public static Vector<Integer> SegmentedSieve(int n)
    {
        Vector<Integer> primes=new Vector<>();
        int limit=(int)Math.sqrt(n)+1;
        
        SimpleSieve(primes,limit);
        int low=limit;
        int high=2*limit;
        
        while (low<n)
        {
            if (high>n)
                high=n;
            boolean sieve[]=new boolean[limit+1];
            for (int i:primes)
            {
                int j=(low/i)*i;
                if (j<low)
                    j+=i;
                for (;j<high;j+=i)
                    sieve[j-low]=true;
            }
            for (int i=low;i<high;i++)
                if (!sieve[i-low])
                    primes.add(i);
            low+=limit;
            high+=limit;
        }
        
        
        
        System.out.println(primes);
        return primes;
    }
    private static void SimpleSieve(Vector<Integer> primes,int n)
    {
        int l=(int)Math.sqrt(n);
        boolean sieve[]=new boolean[n+1];
        for (int i=2;i<=l;i++)
            if (!sieve[i])
                for (int j=2*i;j<=n;j+=i)
                    sieve[j]=true;
        for (int i=2;i<=n;i++)
            if (!sieve[i])
                primes.add(i);
    }
}