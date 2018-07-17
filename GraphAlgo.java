package CodeChef;

import java.util.*;

class GraphAlgo 
{
    public static void Dijkstra(Graph graph)
    {
        System.out.println(graph.Dijkstra(0));
    }
    public static void BellmanFord(Graph graph)
    {
        System.out.println(graph.BellmanFord(1));
    }
    public static void FloydWarshall(Graph graph)
    {
        long dist[][]=graph.FloydWarshall();
        for (int i=0;i<graph.size();i++)
            System.out.println(Arrays.toString(dist[i]));
    }
    public static void Kosaraju(Graph graph)
    {
        Set<Set<Integer>> comp=graph.Kosaraju();
        for (Set<Integer> com:comp)
            System.out.println(com);
    }
    public static void BFS(Graph graph)
    {
        graph.BFS();
    }
    public static void TopologicalSort(Graph graph)
    {
        System.out.println(graph.TopologicalSort());
    }
    public static void main(String args[])
    {
        // Sample graph for dijkstra
        /*
        Graph graph=new Graph(8);
        graph.addEdge(0,1,5);
        graph.addEdge(1,0,5);
        graph.addEdge(0,3,9);
        graph.addEdge(3,0,9);
        graph.addEdge(0,4,2);
        graph.addEdge(4,0,2);
        graph.addEdge(1,2,2);
        graph.addEdge(2,1,2);
        graph.addEdge(2,3,3);
        graph.addEdge(3,2,3);
        graph.addEdge(3,5,2);
        graph.addEdge(5,3,2);
        graph.addEdge(4,5,3);
        graph.addEdge(5,4,3);
        graph.addEdge(6,7,2);
        */
        
        // Sample graph for sorting
        /*
        Graph graph=new Graph(8);
        graph.addEdge(0,2,0);
        graph.addEdge(1,2,0);
        graph.addEdge(1,3,0);
        graph.addEdge(2,4,0);
        graph.addEdge(3,5,0);
        graph.addEdge(4,5,0);
        graph.addEdge(4,7,0);
        graph.addEdge(5,6,0);
        */
        
        // Sample graph for Bellman Ford
        /*
        Graph graph=new Graph(5);
        graph.addEdge(3,4,2);
        graph.addEdge(4,3,1);
        graph.addEdge(2,4,4);
        graph.addEdge(0,2,5);
        graph.addEdge(1,2,-3);
        graph.addEdge(0,3,8);
        graph.addEdge(0,1,4);
        */
       
        //Sample graph for Floyd Warshall
        /*
        Graph graph=new Graph(4);
        graph.addEdge(0,1,3);
        graph.addEdge(0,2,6);
        graph.addEdge(1,2,-2);
        graph.addEdge(2,3,2);
        graph.addEdge(0,3,15);
        graph.addEdge(3,0,1);
        */
       
        //Sample graph for Kosaraju
        Graph graph=new Graph(11);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(1,3);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(5,3);
        graph.addEdge(6,5);
        graph.addEdge(6,7);
        graph.addEdge(7,8);
        graph.addEdge(8,9);
        graph.addEdge(9,6);
        graph.addEdge(9,10);
        
        //System.out.println(graph);
        //BFS(graph);
        //Dijkstra(graph);
        //Bellman(graph);
        //TopologicalSort(graph);
        //FloydWarshall(graph);
        Kosaraju(graph);
    }
}






@SuppressWarnings("unchecked")
class Graph
{
    private int size;
    Node nodes[];
    ArrayList<Edge> edges;
    public Graph(int n)
    {
        size=n;
        nodes=new Node[n];
        for (int i=0;i<n;i++)
            nodes[i]=new Node(i);
        edges=new ArrayList<>();
    }
    public void addEdge(int a,int b,int wt)
    {
        nodes[a].addEdge(nodes[b],wt);
        edges.add(new Edge(a,b,wt));
    }
    public void addEdge(int a,int b)
    {
        nodes[a].addEdge(nodes[b]);
        edges.add(new Edge(a,b));
    }
    public Node getNode(int i)
    {
        return nodes[i];
    }
    public int size()
    {
        return size;
    }
    /**
     * O(V+E)
     */
    public void BFS()
    {
        Set<Integer> set=new HashSet<>();
        Queue<Integer> queue=new LinkedList<>();
        set.add(0);
        queue.add(0);
        while (!queue.isEmpty())
        {
            int cur=queue.remove();
            System.out.println(cur);
            for (Node child:nodes[cur].neighbors)
            {
                int ch=child.index;
                if (!set.contains(ch))
                {
                    set.add(ch);
                    queue.add(ch);
                }
            }
        }
    }
    /**
     * O(E log V)
     * returns -1 if unreachable
     */
    public ArrayList<Integer> Dijkstra(int source)
    {
        Map<Node,Node> parent=new HashMap<>();
        int dist[]=new int[size];
        Arrays.fill(dist,-1);
        HeapMap heap=new HeapMap();
        for (int i=0;i<size;i++)
            heap.add(nodes[i]);
        heap.updateNode(nodes[source],0);
        parent.put(nodes[source],null);
        while(!heap.isEmpty())
        {
            int val=heap.minValue();
            if (val==Integer.MAX_VALUE)
                break;
            Node cur=heap.extractMin();
            dist[cur.index]=val;
            for (Node child:cur.neighbors)
            {
                if (heap.contains(child))
                    if (heap.decKey(child,dist[cur.index]+cur.weight.get(child)))
                        parent.put(child,cur);
            }
        }
        // Parent list created but not returned
        ArrayList<Integer> result=new ArrayList<>();
        for (int i:dist)
            result.add(i);
        return result;
    }
    /**
     * O(VE)
     * works with -ve edge wt also
     * returns Integer.MAX_VALUE if unreachable
     * returns null if it contains -ve cycle
     */
    public ArrayList<Long> BellmanFord(int source)
    {
        int V=size;
        long dist[]=new long[V];
        int parent[]=new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(parent,-1);
        dist[source]=0;
        for (int i=0;i<V-1;i++)
        {
            for (Edge edge:edges)
            {
                if (dist[edge.u]>=Integer.MAX_VALUE)
                    continue;
                if (dist[edge.v]>dist[edge.u]+edge.wt)
                {
                    dist[edge.v]=dist[edge.u]+edge.wt;
                    parent[edge.v]=edge.u;
                }
            }
        }
        // Checking for -ve cycles
        for (Edge edge:edges)
        {
            if (dist[edge.u]>=Integer.MAX_VALUE)
                continue;
            if (dist[edge.v]>dist[edge.u]+edge.wt)
                return null;
        }
        
        ArrayList<Long> result=new ArrayList<>();
        for (long i:dist)
            result.add(i);
        return result;
    }
    /**
     * O(V3)
     * All pairs shortest path
     * if diagonal elements become -ve then it has -ve cycle
     */
    public long[][] FloydWarshall()
    {
        int n=size;
        long dist[][]=new long[n][n];
        int parent[][]=new int[n][n];
        for (int i=0;i<n;i++)
        {
            Arrays.fill(dist[i],Integer.MAX_VALUE);
            Arrays.fill(parent[i],-1);
        }
        
        for (Edge edge:edges)
            dist[edge.u][edge.v]=edge.wt;
        for (int i=0;i<n;i++)
            dist[i][i]=0;
        for (int i=0;i<n;i++)
            for (Node child:nodes[i].neighbors)
                parent[i][child.index]=i;
        
            
        for (int k=0;k<n;k++)
        {
            for (int i=0;i<n;i++)
            {
                for (int j=0;j<n;j++)
                {
                    if (dist[i][k]>=Integer.MAX_VALUE || dist[k][j]>=Integer.MAX_VALUE)
                        continue;
                    if (dist[i][j]>dist[i][k]+dist[k][j])
                    {
                        dist[i][j]=dist[i][k]+dist[k][j];
                        parent[i][j]=parent[k][j];
                    }
                }
            }
        }
        for (int i=0;i<n;i++)
            if (dist[i][i]<0)
                return null;
        return dist;
    }
    /**
     * O(V+E)
     * Strongly Connected Components
     */
    public Set<Set<Integer>> Kosaraju()
    {
        Stack<Integer> finish=new Stack<>();
        Set<Integer> set=new HashSet<>();
        int n=size;
        for (int i=0;i<size;i++)
            set.add(i);
        while (!set.isEmpty())
        {
            int source=set.iterator().next();
            findFinishTime(source,finish,set);
        }
        reverseEdges(true);
        for (int i=0;i<size;i++)
            set.add(i);
        Set<Set<Integer>> components=new HashSet<>();
        while (!finish.isEmpty())
        {
            int source=finish.pop();
            Set<Integer> com=new HashSet<>();
            if (set.contains(source))
            {
                SCC(source,set,com);
                components.add(com);
            }
        }
        reverseEdges(false);
        return components;
    }
    private void findFinishTime(int cur,Stack<Integer> finish,Set<Integer> set)
    {
        if (set.contains(cur))
            set.remove(cur);
        for (Node no:nodes[cur].neighbors)
        {
            int child=no.index;
            if (set.contains(child))
            {
                set.remove(child);
                findFinishTime(child,finish,set);
            }
        }
        finish.push(cur);
    }
    private void reverseEdges(boolean flag)
    {
        int n=size;
        for (int i=0;i<n;i++)
            nodes[i].neighbors=new ArrayList<>();
        for (Edge edge:edges)
        {
            if (flag)
                nodes[edge.v].neighbors.add(nodes[edge.u]);
            else
                nodes[edge.u].neighbors.add(nodes[edge.v]); 
        }
    }
    private void SCC(int cur,Set<Integer> set,Set<Integer> comp)
    {
        if (set.contains(cur))
            set.remove(cur);
        for (Node node:nodes[cur].neighbors)
        {
            int child=node.index;
            if (set.contains(child))
            {
                set.remove(child);
                SCC(child,set,comp);
            }
        }
        comp.add(cur);
    }
    /**
     * O(V+E)
     */
    public ArrayList<Node> TopologicalSort()
    {
        Set<Integer> set=new HashSet<>();
        Stack<Integer> output=new Stack<>();
        for (int i=0;i<size;i++)
            set.add(i);
        while (!set.isEmpty())
        {
            int source=set.iterator().next();
            TopologicalSort(source,set,output);
        }
        ArrayList<Node> order=new ArrayList<>();
        while (!output.isEmpty())
            order.add(nodes[output.pop()]);
        return order;
    }
    private void TopologicalSort(int source,Set<Integer> visited,Stack<Integer> stack)
    {
        Node cur=nodes[source];
        if (visited.contains(cur.index))
            visited.remove(cur.index);
        for (Node child:cur.neighbors)
        {
            if (visited.contains(child.index))
            {
                visited.remove(child.index);
                TopologicalSort(child.index,visited,stack);
            }
        }
        stack.push(source);
    }
    @Override
    public String toString()
    {
        String ret="";
        for (int i=0;i<size;i++)
            ret=ret+i+") -> "+nodes[i].neighbors.toString()+"\n";
        return ret;
    }
    class HeapMap
    {
        private ArrayList<Node> heap;
        private Map<Node,Integer> map;
        private int size=0;
        private int lastIndex=0;
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
            node.keyValue=Integer.MAX_VALUE;
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
        public int minValue()
        {
            if (isEmpty())
                return Integer.MAX_VALUE;
            return heap.get(0).keyValue;
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
        /**
         * O(log n)
         */
        public boolean decKey(Node node,int value)
        {
            int index=map.get(node);
            if (node.keyValue>=value)
            {
                node.keyValue=value;
                heapifyUp(index);
                return true;
            }
            else
                return false;
        }
        /**
         * O(log n)
         */
        public boolean incKey(Node node,int value)
        {
            int index=map.get(node);
            if (node.keyValue<value)
            {
                node.keyValue=value;
                heapifyDown(index);
                return true;
            }
            else
                return false;
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
            return size<=0;
        }
        @Override
        public String toString()
        {
            String str="";
            int n=size;
            for (Node node:heap)
            {
                str=str+node.index+" - "+node.keyValue+"\n";
            }
            return str;
        }
        private void heapifyUp(int index)
        {
            while (index!=0)
            {
                if (heap.get(index).keyValue<heap.get(parentIndex(index)).keyValue)
                {
                    map.put(heap.get(index),parentIndex(index));
                    map.put(heap.get(parentIndex(index)),index);
                    Node tmp=heap.get(index);
                    heap.set(index,heap.get(parentIndex(index)));
                    heap.set(parentIndex(index),tmp);
                }
                index=parentIndex(index);
            }
        }
        private void heapifyDown(int index)
        {
            //int index=0;
            while (index<size)
            {
                int val=Integer.MAX_VALUE;
                if (leftChild(index)<size)
                    val=heap.get(leftChild(index)).keyValue;
                if (rightChild(index)<size)
                    val=Math.min(val,heap.get(rightChild(index)).keyValue);
                if (val<heap.get(index).keyValue)
                {
                    if (heap.get(leftChild(index)).keyValue<=heap.get(rightChild(index)).keyValue)
                    {
                        map.put(heap.get(leftChild(index)),index);
                        map.put(heap.get(index),leftChild(index));
                        Node tmp=heap.get(leftChild(index));
                        heap.set(leftChild(index),heap.get(index));
                        heap.set(index,tmp);
                        index=leftChild(index);
                    }
                    else
                    {
                        map.put(heap.get(rightChild(index)),index);
                        map.put(heap.get(index),rightChild(index));
                        Node tmp=heap.get(rightChild(index));
                        heap.set(rightChild(index),heap.get(index));
                        heap.set(index,tmp);
                        index=rightChild(index);
                    }
                }
                else
                    break;
            }
        }
        private int leftChild(int index)
        {
            return index*2+1;
        }
        private int rightChild(int index)
        {
            return index*2+2;
        }
        private int parentIndex(int index)
        {
            return (index-1)/2;
        }
    }
    class Edge
    {
        int u,v,wt;
        public Edge()
        {
        }
        public Edge(int u,int v)
        {
            this.u=u;
            this.v=v;
        }
        public Edge(int u,int v,int wt)
        {
            this.u=u;
            this.v=v;
            this.wt=wt;
        }
        @Override
        public String toString()
        {
            return u+"-"+v;
        }
    }
    class Node
    {
        int index=0;
        ArrayList<Node> neighbors;
        Map<Node,Integer> weight;
        int keyValue=0;
        public Node(int index)
        {
            this.index=index;
            neighbors=new ArrayList<>();
            weight=new HashMap<>();
        }
        public void addEdge(Node a)
        {
            neighbors.add(a);
            weight.put(a,0);
        }
        public void addEdge(Node a,int wt)
        {
            neighbors.add(a);
            weight.put(a,wt);
        }
        @Override
        public String toString()
        {
            return index+"";
        }
    }
}