package CodeChef;

import java.util.*;
import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
/**
 * Space Complexity - O(root N)
 */
class SquareRootDecom <T extends Number & Comparable<? super T>>
{
    public int n,b;
    public T arr[];
    public T block[];
    private T add(T a,T b)
    {
        if (a.getClass() == Integer.class)
            return (T) (Integer)((Integer)a + (Integer)b);
        if (a.getClass() == Long.class)
            return (T) Long.valueOf(((Long)a).longValue() + ((Long)b).longValue());
        if (a.getClass() == Double.class)
            return (T) (Double)((Double)a + (Double)b);
        return null;
    }
    private T sub(T a,T b)
    {
        if (a.getClass() == Integer.class)
            return (T) (Integer)((Integer)a - (Integer)b);
        if (a.getClass() == Long.class)
            return (T) Long.valueOf(((Long)a).longValue() - ((Long)b).longValue());
        if (a.getClass() == Double.class)
            return (T) (Double)((Double)a - (Double)b);
        return null;
    }
    /**
     * O(n)
     */
    public SquareRootDecom(T input[])
    {
        n=input.length;
        b=(int)Math.sqrt(n);
        arr = (T[])Array.newInstance(input[0].getClass(),n);
        block = (T[])Array.newInstance(input[0].getClass(),(int)Math.ceil(n*1.0/b));
        int blk_idx=-1;
        for (int i=0;i<n;i++)
        {
            arr[i]=input[i];
            if (i%b==0)
                blk_idx++;
            block[blk_idx]=add(block[blk_idx],arr[i]);
        }
    }
    /**
     * O(root N)
     */
    public T query(int l,int r)
    {
        T sum = (T)(new Integer(0));
        while (l<r&&l%b!=0&&l!=0)
        {
            sum=add(sum,arr[l]);
            l++;
        }
        while (l+b<=r)
        {
            sum=add(sum,block[l/b]);
            l+=b;
        }
        while (l<=r)
        {
            sum=add(sum,arr[l]);
            l++;
        }
        return sum;
    }
    /**
     * O(1)
     */
    public void update(int idx, T val)
    {
        int blockNumber=idx/b;
        block[blockNumber]=add(block[blockNumber],sub(val,arr[idx]));
        arr[idx]=val;
    }
    public String toString()
    {
        String s="";
        for (T i:block)
            s=s+i+" ";
        return "[ "+s+"]";
    }
}