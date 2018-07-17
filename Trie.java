package CodeChef;

import java.util.*;


class Trie
{
    HashMap<Character,Trie> map;
    boolean isWord;
    public Trie()
    {
        map=new HashMap<>();
    }
    /**
     * O(m)
     * m - length of string
     */
    public void add(String s)
    {
        int l=s.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            if (root.map.get(c)==null)
                root.map.put(c,new Trie());
            root=root.map.get(c);
        }
        root.isWord=true;
    }
    /**
     * O(m)
     * m - length of string
     */
    public void remove(String s)
    {
        int l=s.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            if (root.map.get(c)==null)
                return ;
            root=root.map.get(c);
        }
        root.isWord=false;
    }
    /**
     * O(m)
     * m - length of string
     */
    public boolean contains(String s)
    {
        int l=s.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            if (root.map.get(c)==null)
                return false;
            root=root.map.get(c);
        }
        return root.isWord;
    }
    /**
     *  O(m)
     *  m - length of string
     */
    public boolean hasPrefix(String s)
    {
        int l=s.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            if (root.map.get(c)==null)
                return false;
            root=root.map.get(c);
        }
        return true;
    }
    @Override
    public String toString()
    {
        Queue<Trie> tries=new LinkedList<>();
        Queue<String> queue=new LinkedList<>();
        Queue<String> output=new LinkedList<>();
        queue.add("");
        tries.add(this);
        while (!tries.isEmpty())
        {
            Trie root=tries.remove();
            String str=queue.remove();
            for (Character ch:root.map.keySet())
            {
                queue.add(str+ch);
                tries.add(root.map.get(ch));
            }
            if (root.isWord)
                output.add(str);
        }
        String ret="";
        for (String s:output)
            ret=ret+s+" ";
        return "[ "+ret+"]";
    }
    public Queue<String> wordSuggest(String prefix)
    {
        int l=prefix.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=prefix.charAt(i);
            if (root.map.get(c)==null)
                return null;
            root=root.map.get(c);
        }
        
        
        Queue<Trie> tries=new LinkedList<>();
        Queue<String> queue=new LinkedList<>();
        Queue<String> output=new LinkedList<>();
        Queue<String> ans=new LinkedList<>();
        
        queue.add("");
        tries.add(root);
        while (!tries.isEmpty())
        {
            root=tries.remove();
            String str=queue.remove();
            for (Character ch:root.map.keySet())
            {
                queue.add(str+ch);
                tries.add(root.map.get(ch));
            }
            if (root.isWord)
                output.add(str);
        }
        
        for (String s:output)
            ans.add(prefix+s);
        return ans;
    }
}