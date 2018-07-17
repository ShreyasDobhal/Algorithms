package CodeChef;

import java.util.*;

class AVLTree <T extends Comparable<? super T>> 
{
    private int size=0;
    public Node root;
    private Node rightRotate(Node y)
    {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    private Node leftRotate(Node x)
    {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
    private int getBalance(Node N)
    {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }
    private Node insert(Node node, T key)
    {
        if (node == null)
            return (new Node(key));
        if (key.compareTo(node.key)<0)
            node.left = insert(node.left, key);
        else if (key.compareTo(node.key)>0)
            node.right = insert(node.right, key);
        else // Equal keys not allowed
            return node;
        node.height = 1 + Math.max(height(node.left),height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.left.key)<0)
            return rightRotate(node);
        if (balance < -1 && key.compareTo(node.right.key)>0)
            return leftRotate(node);
        if (balance > 1 && key.compareTo(node.left.key)>0)
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key)<0)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    private Node minValueNode(Node node)
    {
        Node current = node;
        while (current.left != null)
           current = current.left;
        return current;
    }
    private Node deleteNode(Node root, T key)
    {
        if (root == null)
            return root;
        if (key.compareTo(root.key)<0)
            root.left = deleteNode(root.left, key);
        else if (key.compareTo(root.key)>0)
            root.right = deleteNode(root.right, key);
        else
        {
            if ((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else   // One child case
                    root = temp; // Copy the contents of
            }
            else
            {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
        if (root == null)
            return root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    private String preOrder(Node node)
    {
        String s="";
        if (node != null)
        {
            s=(node.key + " ");
            s=s+preOrder(node.left);
            s=s+preOrder(node.right);
        }
        return s;
    }
    private T indexRange(Node root,T l,T r)
    {
        if (root.key.compareTo(l)>=0&&root.key.compareTo(r)<=0)
            return root.key;
        else if (root.key.compareTo(l)<0&&root.right!=null)
            return indexRange(root.right,l,r);
        else if (root.key.compareTo(r)>0&&root.left!=null)
            return indexRange(root.left,l,r);
        else
            return null;
    }
    private int height(Node N)
    {
        if (N == null)
             return 0;
        return N.height;
    }
    
    
    /**
     * O(log n)
     */
    public void add(T item)
    {
        root=insert(root,item);
        size++;
    }
    /**
     * O(log n)
     */
    public void remove(T item)
    {
        root=deleteNode(root,item);
        size--;
    }
    /**
     * O(1)
     */
    public int size()
    {
        return size;
    }
    /**
     * O(1)
     */
    public boolean isEmpty()
    {
        if (size==0)
            return true;
        return false;
    }
    /**
     * O(log n)
     */
    public boolean contains(T item)
    {
        Node root=this.root;
        while (root!=null)
        {
            if (root.key==item)
                return true;
            else if (root.key.compareTo(item)<0)
                root=root.right;
            else
                root=root.left;
        }
        return false;
    }
    /**
     * Returns a value in the given range
     * null if no such value exsists
     * O(log n)
     */
    public T getValueInRange(T l,T r)
    {
        return indexRange(root,l,r);
    }
    public String toString()
    {
        return "[ "+preOrder(root)+"]";
    }
    public class Node
    {
        int height;
        T key;
        Node left, right;
        Node(T d)
        {
            key = d;
            height = 1;
        }
    }
}