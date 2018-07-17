package CodeChef;

import java.util.*;

@SuppressWarnings("unchecked")
class Heap<T>
{
    private PriorityQueue<T> heap;
    private Map<T,Integer> map;
    public Heap()
    {
        heap=new PriorityQueue<>();
        map=new HashMap<>();
    }
    public Heap(Comparator c)
    {
        heap=new PriorityQueue<>(c);
        map=new HashMap<>();
    }
    /**
     * O(log n)
     */
    public void add(T a)
    {
        heap.add(a);
        if (map.get(a)==null)
            map.put(a,0);
        map.put(a,map.get(a)+1);
    }
    /**
     * O(log n)
     */
    public void remove(T a)
    {
        heap.remove(a);
        map.put(a,map.get(a)-1);
    }
    /**
     * Removes top value
     * O(log n)
     */
    public T poll()
    {
        return heap.poll();
    }
    /**
     * Returns top value
     * O(1)
     */
    public T peek()
    {
        return heap.peek();
    }
    /**
     * O(1)
     * Default complexity is O(n)
     */
    public boolean contains(T a)
    {
        return heap.contains(a);
    }
    public int size()
    {
        return heap.size();
    }
    public boolean isEmpty()
    {
        return heap.size()<=0;
    }
    /**
     * Updates value a to b
     * O(log n)
     */
    public void update(T a,T b)
    {
        remove(a);
        add(b);
    }
    public String toString()
    {
        String s="";
        for (Iterator i=heap.iterator();i.hasNext();)
            s=s+i.next()+" ";
        return "[ "+s+"]";
    }
    public static void main()
    {
        Heap<Block> heap=new Heap<>(new Comparator<Block>(){
            @Override
            public int compare(Block a,Block b)
            {
                return a.val-b.val;
            }
        });
        Block blocks[]=new Block[5];
        for (int i=0;i<5;i++)
        {
            blocks[i]=new Block((int)(Math.random()*10));
            heap.add(blocks[i]);
        }
        System.out.println(heap);
        
        blocks[0].val=20;
        System.out.println(heap);
        while (!heap.isEmpty())
            System.out.println(heap.poll());
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        PriorityQueue<Node> heap=new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node a,Node b) {
                return Integer.compare(a.val,b.val);
            }
        });
        int arr[]={1,5,4,2,3,7,8,9};
        char ch[]={'a','e','d','b','c','g','h','i'};
        int n=arr.length;
        for (int i=0;i<n;i++)
            heap.add(new Node(arr[i],ch[i]));
        for (int i=0;i<n;i++)
            System.out.println(heap.remove());
    }
}
class Block
{
    int val=0;
    public Block(int val)
    {
        this.val=val;
    }
    @Override
    public String toString()
    {
        return val+"";
    }
}