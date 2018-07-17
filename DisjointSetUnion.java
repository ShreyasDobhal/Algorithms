package CodeChef;

import java.util.*;

/**
 * Space Complexity O(n)
 * Time Complexity O(m a(n))
 * m is the number of operations
 * n is the number of elements
 * a(n) is very slowing growing function (<=4)
 */
@SuppressWarnings("unchecked")
class DisjointSetUnion<T>
{
    public Map<T,Node> map=new HashMap<>();
    class Node
    {
        T data;
        Node parent;
        int rank;
    }
    /**
     * O(1)
     */
    public void makeSet(T data)
    {
        Node node=new Node();
        node.data=data;
        node.parent=node;
        node.rank=0;
        map.put(data,node);
    }
    /**
     * naive - O(n)
     * rank - O(log n)
     * path compression - O(a(n)) -> O(1)
     */
    public void union(T data1,T data2)
    {
        Node node1=map.get(data1);
        Node node2=map.get(data2);
        Node parent1=findSet(node1);
        Node parent2=findSet(node2);
        if (parent1.data==parent2.data)
            return;
        if (parent1.rank>=parent2.rank)
        {
            parent1.rank=(parent1.rank==parent2.rank)? parent1.rank+1 : parent1.rank;
            parent2.parent=parent1;
        }
        else
            parent1.parent=parent2;
    }
    /**
     * naive - O(n)
     * rank - O(log n)
     * path compression - O(a(n)) -> O(1)
     */
    public T findSet(T data)
    {
        return findSet(map.get(data)).data;
    }
    private Node findSet(Node node)
    {
        Node parent=node.parent;
        if (parent==node)
            return parent;
        node.parent=findSet(node.parent);
        return node.parent;
    }
}