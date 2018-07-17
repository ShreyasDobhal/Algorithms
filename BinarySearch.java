package CodeChef;

import java.util.*;

class BinarySearch
{
    /**
     * O(log n)
     */
    public static boolean binSearch(int a[],int num)
    {
        int n=a.length;
        int low=0,high=n-1;
        int mid=0;
        while (low<=high)
        {
            mid=(low+high)/2;
            if (a[mid]==num)
                return true;
            else if (a[mid]<num)
                low=mid+1;
            else
                high=mid-1;
        }
        return false;
    }
    /**
     * returns index of value less than or equal to num
     * lowerBound(a,0,n-1,x)
     * O(log n)
     */
    public static int lowerBound(int a[],int lo,int hi,int x)
    {
        int mid;
        if (lo>hi)
            return hi;
        mid=(lo+hi)/2;
        if (a[mid]==x)
            return mid;//lowerBound(a,lo,mid-1,x); // if strictly less value is required
        else if (a[mid]>x)
            return lowerBound(a,lo,mid-1,x);
        else
            return lowerBound(a,mid+1,hi,x);
    }
    /**
     * returns index of value greater than or equal to num
     * upperBound(a,0,n-1,x)
     * O(log n)
     */
    public static int upperBound(int a[],int lo,int hi,int x)
    {
        int mid;
        if (lo>hi)
            return lo;
        mid=(lo+hi)/2;
        if (a[mid]==x)
            return mid;//upperBound(a,mid+1,hi,x);
        else if (a[mid]>x)
            return upperBound(a,lo,mid-1,x);
        else
            return upperBound(a,mid+1,hi,x);
    }
    public static int lower(int a[],int lo,int hi,int x)
    {
        return Arrays.binarySearch(a,lo,hi,x);
    }
    public static int lower(TreeSet<Integer> set,int x)
    {
        Object tmp=set.floor(x);;
        if (tmp==null)
            return -1;
        return (Integer)tmp;
    }
    public static int upper(TreeSet<Integer> set,int x)
    {
        Object tmp=set.ceiling(x);
        if (tmp==null)
            return -1;
        return (Integer)tmp;
    }
    public static void main(String args[])
    {
        TreeSet<Integer> set=new TreeSet<>();
        set.add(2);
        set.add(3);
        set.add(5);
        set.add(7);
        set.add(11);
        set.add(13);
        set.add(17);
        set.add(19);
        int l=20,r=22;
        int a=set.tailSet(l).size();
        int b=set.tailSet(r).size();
        if (set.contains(r))
            b--;
        System.out.println(a-b);
        Iterator it=set.tailSet(20).iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
        System.out.println(set.tailSet(19));
        for (int i=0;i<=20;i++)
            System.out.println(i+" "+upper(set,i));
    }
    public static void main()
    {
        TreeSet<Integer> map = new TreeSet<>();
        map.add(5);
        map.add(3);
        map.add(7);
        map.add(11);
        map.add(2);
        System.out.println(lower(map,6));
    }
    public static void run()
    {
        Arr list=new Arr();
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(11);
        list.add(13);
        list.add(17);
        list.add(19);
        System.out.println(list);
        System.out.println(list.n(3,7));
        System.out.println(list.n(4,7));
        System.out.println(list.n(3,8));
        System.out.println(list.n(4,8));
        list.add(6,true);
        list.add(12,true);
        list.add(20,true);
        list.add(1,true);
        System.out.println(list);
        list.remove(17);
        System.out.println(list);
        System.out.println(Collections.binarySearch(list,4));
    }
    
    
}
class Arr extends ArrayList<Integer>
{
    @Override
    public boolean add(Integer i)
    {
        return super.add(i);
    }
    public Integer remove(int i)
    {
        int index=Collections.binarySearch(this,(Integer)i);
        return super.remove(index);
    }
    public void add(Integer i,boolean f)
    {
        int index=Collections.binarySearch(this,i);
        if (index<0)
        {
            index*=-1;
            index--;
        }
        super.add(index,i);
    }
    public int n(int l,int r)
    {
        int a=Collections.binarySearch(this,(Integer)l);
        int b=Collections.binarySearch(this,(Integer)r);
        if (a<0)
            a=(a*-1)-1;
        if (b<0)
            b=(b*-1)-1-1;
        return b-a+1;
    }
}