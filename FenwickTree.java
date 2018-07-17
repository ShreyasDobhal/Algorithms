package CodeChef;

import java.util.*;
import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
/**
 * Space Complexity - O(n)
 */
class FenwickTree <T extends Number & Comparable<?super T>>
{
    public T[] tree;
    private final T def=(T)Integer.valueOf(0);
    private int n,nt;
    private T add(T a,T b)
    {
        if (def.getClass() == Integer.class)
            return (T) (Integer)((Integer)a + (Integer)b);
        if (def.getClass() == Long.class)
            return (T) Long.valueOf(((Long)a).longValue() + ((Long)b).longValue());
        if (def.getClass() == Double.class)
            return (T) (Double)((Double)a + (Double)b);
        return null;
    }
    private T sub(T a,T b)
    {
        if (def.getClass() == Integer.class)
            return (T) (Integer)((Integer)a - (Integer)b);
        if (def.getClass() == Long.class)
            return (T) Long.valueOf(((Long)a).longValue() - ((Long)b).longValue());
        if (def.getClass() == Double.class)
            return (T) (Double)((Double)a - (Double)b);
        return null;
    }
    private int getNext(int i)
    {
        return i+(i&((~i)+1));
    }
    private int getParent(int i)
    {
        return i-(i&((~i)+1));
    }
    /**
     * O(nlog n)
     */
    public void initTree(T a[])
    {
        n=a.length;
        nt=n+1;
        tree = (T[])Array.newInstance(def.getClass(),nt);
        Arrays.fill(tree,def);
        for (int i=0;i<n;i++)
        {
            T num=a[i];
            for (int j=i+1;j<=n;)
            {
                tree[j]=add(tree[j],num);
                j=getNext(j);   // adding AND of index and its 2's complement to get next index
            }
        }
    }
    /**
     * O(log n)
     */
    public void updateValue(T a[],int i,T num)
    {
        //int n=a.length;
        num=sub(num,a[i]);
        a[i]=add(a[i],num);
        for (int j=i+1;j<=n;)
        {
            tree[j]=add(tree[j],num);
            j=getNext(j);
        }
    }
    /**
     * O(log n)
     */
    public T sumQuery(int i,int j)
    {
        T sum=def;
        for (int k=j+1;k>0;)
        {
            sum=add(sum,tree[k]);
            k=getParent(k);
        }
        for (int k=i;k>0;)
        {
            sum=sub(sum,tree[k]);
            k=getParent(k);
        }
        return sum;
    }
    public String toString()
    {
        String str="";
        for (int i=0;i<nt;i++)
            str=str+tree[i]+" ";
        return "[ "+str+"]";
    }
}