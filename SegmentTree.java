package CodeChef;

import java.util.*;
import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
/**
 * Space Complexity - O(n) or O(4n)
 */
class SegmentTree <T extends Comparable<? super T>>
{
    public T[] segTree;
    private T defaultValue;
    private int n,nt;
    private void constructTree(T a[],int low,int high,int pos)
    {
        if (low==high)
        {
            segTree[pos]=a[low];
            return ;
        }
        int mid = (low+high)/2;
        constructTree(a,low,mid,2*pos+1);
        constructTree(a,mid+1,high,2*pos+2);
        segTree[pos]=segFunc(segTree[2*pos+1],segTree[2*pos+2]);
    }
    private T rangeQuery(int qlow,int qhigh,int low,int high,int pos)
    {
        if (qlow<=low&&qhigh>=high)
            return segTree[pos];
        if (qlow>high||qhigh<low)
            return defaultValue;
        int mid=(low+high)/2;
        return segFunc(rangeQuery(qlow,qhigh,low,mid,2*pos+1),rangeQuery(qlow,qhigh,mid+1,high,2*pos+2));
    }
    private void updateValue(int low,int high,int pos,int index,T val)
    {
        int mid=(low+high)/2;
        if (index==low&&index==high)
        {
            segTree[pos]=val;
            updateParents(pos);
        }
        else if (index<=mid)
            updateValue(low,mid,2*pos+1,index,val);
        else
            updateValue(mid+1,high,2*pos+2,index,val);
    }
    private void updateParents(int pos)
    {
        int i=pos;
        while (i!=0)
        {
            i=(i-1)/2;
            T val1=segTree[2*i+1];
            T val2=segTree[2*i+2];
            segTree[i]=segFunc(val1,val2);
        }
    }
    /**
     * O(n)
     */
    public void initTree(T a[],T def)
    {
        defaultValue=def;
        n=a.length;
        if ((n&(n-1))==0)
            nt=n*2-1;
        else
        {
            nt=1<<(int)(Math.floor(Math.log(n)/Math.log(2))+1);
            nt=nt*2-1;
        }
        segTree = (T[])Array.newInstance(defaultValue.getClass(),nt);
        Arrays.fill(segTree,defaultValue);
        constructTree(a,0,n-1,0);
    }
    /**
     * Change this to store something else
     * O(?)
     */
    public T segFunc(T a,T b)
    {
        return add(a,b);
    }
    private T add(T a,T b)
    {
        if (defaultValue.getClass() == Integer.class)
            return (T) (Integer)((Integer)a + (Integer)b);
        if (defaultValue.getClass() == Long.class)
            return (T) Long.valueOf(((Long)a).longValue() + ((Long)b).longValue());
        if (defaultValue.getClass() == Double.class)
            return (T) (Double)((Double)a + (Double)b);
        return null;
    }
    /**
     * O(log n)
     */
    public void updateValue(int index,T val)
    {
        updateValue(0,n-1,0,index,val);
    }
    /**
     * O(log n)
     */
    public T rangeQuery(int low,int high)
    {
        return rangeQuery(low,high,0,n-1,0);
    }
    /**
     * O(n)
     */
    public String toString()
    {
        String str="";
        for (int i=0;i<nt;i++)
            str=str+segTree[i]+" ";
        return "[ "+str+"]";
    }
}