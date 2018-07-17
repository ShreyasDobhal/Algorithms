package CodeChef;

import java.util.*;


class HeapDriver
{
    public static void main(String args[])
    {
        HeapMap heap=new HeapMap();
        heap.add(new Node(0,(int)(Math.random()*10),1));
        heap.add(new Node(1,(int)(Math.random()*10),2));
        heap.add(new Node(2,(int)(Math.random()*10),3));
        heap.add(new Node(3,(int)(Math.random()*10),4));
        heap.add(new Node(4,(int)(Math.random()*10),5));
        System.out.println(heap);
        Node node=(Node)heap.minNode();
        heap.updateNode(node,100);
        System.out.println(heap);
        while (!heap.isEmpty())
        {
            Node tmp=(Node)heap.extractMin();
            System.out.println(tmp+" "+tmp.info);
        }
    }
}
class Node extends HeapMap.Node
{
    int info=0;
    public Node(int index)
    {
        super(index);
    }
    public Node(int index,int keyValue)
    {
        super(index,keyValue);
    }
    public Node(int index,int keyValue,int info)
    {
        super(index,keyValue);
        this.info=info;
    }
}




class HeapMap
{
    private ArrayList<Node> heap;
    private Map<Node,Integer> map;
    private int size=0,lastIndex=0;
    public HeapMap()
    {
        heap=new ArrayList<>();
        map=new HashMap<>();
    }
    /**
     * O(log n)
     */
    public void add(Node node)
    {
        heap.add(lastIndex,node);
        map.put(node,lastIndex);
        heapifyUp(lastIndex);
        lastIndex++;
        size++;
    }
    /**
     * O(log n)
     */
    public Node extractMin()
    {
        if (isEmpty())
            return null;
        Node min=heap.get(0);
        map.remove(min);
        heap.set(0,heap.get(lastIndex-1));
        map.put(heap.get(lastIndex-1),0);
        
        Node tmp=new Node(-1);
        tmp.keyValue=Integer.MAX_VALUE;
        heap.set(lastIndex-1,tmp);
        map.put(tmp,lastIndex-1);
        
        heapifyDown(0);
        lastIndex--;
        size--;
        return min;
    }
    /**
     * O(1)
     */
    public Node minNode()
    {
        if (isEmpty())
            return null;
        return heap.get(0);
    }
    /**
     * O(1)
     */
    public boolean contains(Node node)
    {
        if (map.get(node)==null)
            return false;
        return true;
    }
    /**
     * O(log n)
     */
    public void updateNode(Node node,int value)
    {
        int index=map.get(node);
        if (node.keyValue>=value)
        {
            node.keyValue=value;
            heapifyUp(index);
        }
        else
        {
            node.keyValue=value;
            heapifyDown(index);
        }
    }
    public int size(){return size;}
    public boolean isEmpty(){return size<=0;}
    @Override
    public String toString()
    {
        String str="";
        for (Node node:heap)
            str=str+node.index+" - "+node.keyValue+"\n";
        return str;
    }
    private void heapifyUp(int index)
    {
        while (index!=0)
        {
            if (heap.get(index).keyValue<heap.get(parentIndex(index)).keyValue)
                swap(index,parentIndex(index));
            index=parentIndex(index);
        }
    }
    private void heapifyDown(int index)
    {
        while (index<size)
        {
            int val=Integer.MAX_VALUE;
            if (leftChild(index)<size)
                val=heap.get(leftChild(index)).keyValue;
            if (rightChild(index)<size)
                val=Math.min(val,heap.get(rightChild(index)).keyValue);
            if (val<heap.get(index).keyValue)
            {
                int index2=0;
                if (heap.get(leftChild(index)).keyValue<=heap.get(rightChild(index)).keyValue)
                    index2=leftChild(index);
                else
                    index2=rightChild(index);
                swap(index,index2);
                index=index2;
            }
            else
                break;
        }
    }
    private void swap(int index1,int index2)
    {
        map.put(heap.get(index1),index2);
        map.put(heap.get(index2),index1);
        Node tmp=heap.get(index1);
        heap.set(index1,heap.get(index2));
        heap.set(index2,tmp);
    }
    private int leftChild(int index){return index*2+1;}
    private int rightChild(int index){return index*2+2;}
    private int parentIndex(int index){return (index-1)/2;}
    static class Node
    {
        int index=0;
        int keyValue=0;
        public Node(int index)
        {
            this.index=index;
        }
        public Node(int index,int keyValue)
        {
            this.index=index;
            this.keyValue=keyValue;
        }
        @Override
        public String toString()
        {
            return index+","+keyValue+" ";
        }
    }
}