package CodeChef;

/**
 * Assuming no updates occur
 * Space Complexity - O(n logn)
 */
class SparseTable
{
    private final int[][] sparse;
    private final int n;
    private final int[] input;
    public SparseTable(int[] input) 
    {
        this.input=input;
        this.n=input.length;
        this.sparse=preprocess(input, this.n);
    }
    /**
     * O(n logn)
     */
    private int[][] preprocess(int[] input,int n) 
    {
        int[][] sparse=new int[n][log2(n)+1];
        for (int i=0;i<input.length;i++) 
            sparse[i][0]=i;
        for (int j=1;1<<j<=n;j++) 
            for (int i=0;i+(1<<j)-1<n;i++) 
                if (input[sparse[i][j-1]]<input[sparse[i+(1<<(j-1))][j-1]])
                    sparse[i][j]=sparse[i][j-1];
                else 
                    sparse[i][j]=sparse[i+(1<<(j-1))][j-1];
        return sparse;
    }
    /**
     * O(1)
     */
    public int rangeQuery(int low,int high)
    {
        int l=high-low+1;
        int k=log2(l);
        if (input[sparse[low][k]]<=input[sparse[low+l-(1<<k)][k]])
            return input[sparse[low][k]];
        else 
            return input[sparse[high-(1<<k)+1][k]];
    }
    private static int log2(int n)
    {
        if(n <= 0)
            throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }
}