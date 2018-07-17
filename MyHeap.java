package CodeChef;

import java.util.*;

class MyHeap
{
    int heap[];
    int size=10000;
    int index=0;
    public MyHeap()
    {
        heap=new int[size];
        Arrays.fill(heap,Integer.MAX_VALUE);
    }
    public void add(int n)
    {
        heap[index]=n;
        heapifyUp();
        index++;
    }
    public int poll()
    {
        int val=heap[0];
        heap[0]=heap[index-1];
        heap[index-1]=Integer.MAX_VALUE;
        heapifyDown();
        index--;
        return val;
    }
    public boolean isEmpty()
    {
        return index<=0;
    }
    private void heapifyUp()
    {
        int index=this.index;
        while (index!=0)
        {
            if (heap[parent(index)]>heap[index])
            {
                int tmp=heap[parent(index)];
                heap[parent(index)]=heap[index];
                heap[index]=tmp;
            }
            index=parent(index);
        }
    }
    private void heapifyDown()
    {
        int index=0;
        while (index<size)
        {
            int val=Math.min(heap[leftChild(index)],heap[rightChild(index)]);
            if (val<heap[index])
            {
                if (heap[leftChild(index)]<=heap[rightChild(index)])
                {
                    int tmp=heap[leftChild(index)];
                    heap[leftChild(index)]=heap[index];
                    heap[index]=tmp;
                    index=leftChild(index);
                }
                else
                {
                    int tmp=heap[rightChild(index)];
                    heap[rightChild(index)]=heap[index];
                    heap[index]=tmp;
                    index=rightChild(index);
                }
            }
            else
                break;
        }
    }
    public String toString()
    {
        String str="[ ";
        for (int i=0;i<index;i++)
            str=str+heap[i]+" ";
        str=str+"]";
        return str;
    }
    private int leftChild(int index){return 2*index+1;}
    private int rightChild(int index){return 2*index+2;}
    private int parent(int index){return (index-1)/2;}
    public static void main(String args[])
    {
        MyHeap heap=new MyHeap();
        for (int i=0;i<10;i++)
            heap.add((int)(Math.random()*10));
        System.out.println(heap);
        while (!heap.isEmpty())
        {
            System.out.println(heap.poll());
        }
    }
}