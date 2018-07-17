package CodeChef;

import java.util.*;
 
class BaseConversion
{
    public static long BintoDec(String s)
    {
        long num=0;
        for (int i=0;i<s.length();i++)
            num=(num<<1)|(s.charAt(i)-'0');
        return num;
    }
    public static String DectoBin(long n)
    {
        String s="";
        while (n!=0)
        {
            s=(n&1)+s;
            n>>=1;
        }
        return s;
    }
    public static int setBits(int n)
    {
        return Integer.bitCount(n);
    }
}