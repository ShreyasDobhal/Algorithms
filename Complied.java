package CodeChef;

import java.io.*;
import java.util.*;
import java.math.*;

class Complied
{
    public static void main()throws IOException
    {
        int arr[]={1,2,3,4,5,6,7,8,9,10};
        // Enhanced for loop
        for (int i:arr)// for each loop
            System.out.println(i);
        // BigInteger datatype to store very large numbers (tending to infinity)    
        BigInteger n = BigInteger.valueOf(987654); // Initializing BigInteger
        BigInteger N = new BigInteger("987654321987654321987654321"); // Initializing BigInteger a value exceeding the max value of int/long
        // Arithmetic operations on BigInteger
        n=n.add(BigInteger.valueOf(1));
        n=n.subtract(BigInteger.valueOf(1));
        n=n.multiply(BigInteger.valueOf(2));
        n=n.divide(BigInteger.valueOf(2));
        n=n.mod(BigInteger.valueOf(2));
        // Formatted Integer Literal and Floating Point Literal
        int a = 987_654_321;
        float b = 987.654_321f;
        // Formatted Output Function
        System.out.println(String.format("%.2f",b));
        System.out.printf("%.2f\n",b);
        // Splitting String with DeLimiter
        String s = "Mon Tue Wed Thur Fri Sat Sun";
        String brr[]=s.split(" ");
        for (String i:brr)
            System.out.println(i);
        // Creating List
        List<Integer> col = new ArrayList<Integer>();
        for (int i=0;i<10;i++)
            col.add(i);
        col.remove(1);
        for (int i : col)
            System.out.println(i);
            
        StringBuffer str = new StringBuffer(Integer.toString(1331));
        if ((str+"").equals(str.reverse()+""))
            System.out.println("Palindrome");
        
        int maxPower2=(int)Math.pow(2,Math.floor(Math.log(12)/Math.log(2)));
        System.out.println("Max Power of 2 : "+maxPower2);
        // Creating temporary array
        add(new int[]{1,2,3,4,5});
    }//main()
    public static long pow(long num,long p,long mod)
    {
        long ans=1;
        while (p>0)
        {
            if ((p&1)==0)
            {
                num=(num*num)%mod;
                p/=2;
            }
            else
            {
                ans=(ans*num)%mod;
                p-=1;
            }
        }
        return ans;
    }
    public static void Sieve(int sieve[])
    {
        int size=1000000;
        sieve[0]=1;
        sieve[1]=1;
        int len=(int)Math.sqrt(size);
        for (int i=2;i<=len;i++)
        {
            if (sieve[i]==0)
            {
                for (int j=i*2;j<size;j+=i)
                    sieve[j]=1;
            }
        }
    }
    public static void sort()
    {
        String str[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
        Arrays.sort(str,new Comparator<String>(){
            @Override
            public int compare(String a,String b){
                int l1=a.length();
                int l2=b.length();
                if (l1>l2)
                    return 1;
                else if(l1<l2)
                    return -1;
                else
                    return a.compareTo(b);
            }
        });
        for (String s:str)
            System.out.println(s);
    }
    private static int gcd(int a, int b)
    {
        while (b > 0)
        {
            int temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }
    private static int gcd(int[] input)
    {
        int result = input[0];
        for(int i = 1; i < input.length; i++)
        result = gcd(result, input[i]);
        return result;
    }
    public static void add(int a[])
    {
        int sum=0;
        for (int i:a)
            sum+=i;
        System.out.println(a);
    }
    // Insertion sort
    public static void sort(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }
    /**
     *  Adding any number of elements to a static array
     *  till -1 is encountered
     */
    public static void addElements(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println();
        int arr[]=new int[0];
        int i=0;
        int n;
        while ((n=sc.nextInt())!=-1)
        {
            arr=Arrays.copyOf(arr,arr.length+1);
            arr[i]=n;
            i++;
        }
        for (int num:arr)
            System.out.print(num+" ");
        System.out.println();
    }
    public static int[] primes=new int[175];
    // this array size is sufficient to check numbers till 1000000
    //http://www.di-mgt.com.au/primes1000.html
    // first call Primes() then only use isPrime
    public static boolean isPrime(int n)
    {
        if (n<2)
            return false;
        for (int i=1;i<primes.length;i++)
        {
            if (n<=primes[i])
                return true;
            if (n%primes[i]==0)
                return false;
        }
        return true;
    }
    public static void Primes()
    {
        int index=2;
        primes[1]=2;
        primes[2]=3;
        outer:  for (int i=2;index<primes.length-1;i++)
        {
            for (int j=1;j<=index;j++)
            {
                if (i%primes[j]==0)
                    continue outer;
            }
            // Prime number found
            primes[++index]=i;
        }
    }
    public static void search(int[] a,int n)
    {
        System.out.println(Arrays.binarySearch(a,n));
    }
    public static void Hashing()
    {
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        map.put("One",1);
        map.put("Two",2);
        map.put("Three",3);
        map.put("Two",4);
        System.out.println(map.get("Two"));
        System.out.println(map.get("Four")==null);
    }
    public static void set()
    {
        Set<String> s = new HashSet<String>();
        s.add("Shreyas");
        s.add("Dobhal");
        s.add("Shreyas");
        System.out.println(s.size());
        System.out.println(s.contains("Shreyas"));
        System.out.println(s.contains("Hello"));
    }
}//class