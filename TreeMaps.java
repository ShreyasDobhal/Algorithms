package CodeChef;

import java.util.*;

class TreeMaps
{
    public static void main(String args[])
    {
        TreeMap<Integer,Integer> map=new TreeMap<>();
        // Add element in O(log n)
        map.put(0,5);
        map.put(1,2);
        map.put(2,6);
        map.put(3,10);
        map.put(4,3);
        map.put(5,4);
        System.out.println(map);
        // Remove element in O(log n)
        map.remove(4);
        System.out.println(map);
        int num=1;
        // Find element in O(log n)
        System.out.println("map contains "+num+" : "+map.containsKey(num));
        // Get elemnt in O(log n)
        System.out.println(num+" -> "+map.get(num));
        // Smallest element in O(1)
        System.out.println("smallest "+map.firstKey());
        // Largest element in O(1)
        System.out.println("largest "+map.lastKey());
        
        // Iteration in O(n)
        Set<Integer> keys = map.keySet();
        for (int i:keys)
            System.out.println(i+" - "+map.get(i));
        // OR
        for (Map.Entry<Integer, Integer> m : map.entrySet())
            System.out.println(m.getKey()+" - "+m.getValue());
        // Closest element k>=x O(log n)
        System.out.println(map.ceilingEntry(4).getKey());
        // Closest element k<=x O(log n)
        System.out.println(map.floorEntry(4).getKey());
    }
}