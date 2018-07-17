package CodeChef;

import java.util.*;

/*
 *          {3,0,6,  5,0,8,  4,0,0},
            {5,2,0,  0,0,0,  0,0,0},
            {0,8,7,  0,0,0,  0,3,1},
            
            {0,0,3,  0,1,0,  0,8,0},
            {9,0,0,  8,6,3,  0,0,5},
            {0,5,0,  0,9,0,  6,0,0},
            
            {1,3,0,  0,0,0,  2,5,0},
            {0,0,0,  0,0,0,  0,7,4},
            {0,0,5,  2,0,6,  3,0,0}
 */
class Sudoku
{
    public static void main(String args[])
    {
        int board[][]={
            {0,2,3,  0,0,0,  0,8,0},
            {4,0,6,  0,3,0,  7,9,0},
            {0,0,5,  0,0,0,  0,0,4},
            
            {0,0,0,  5,0,0,  1,0,0},
            {9,0,0,  8,0,4,  0,0,6},
            {0,0,7,  0,0,3,  0,0,0},
            
            {3,0,0,  0,0,0,  8,0,0},
            {0,7,2,  0,5,0,  4,0,3},
            {0,6,0,  0,0,0,  9,2,0}
        };
        
        System.out.println(isValid(board));
        System.out.println("Started .. ");
        if (solve(board,0,0))
        {
            for (int i=0;i<9;i++)
            {
                if (i%3==0)
                    System.out.println();
                for (int j=0;j<9;j++)
                {
                    if (j%3==0)
                        System.out.print("  ");
                    System.out.print(board[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        else
        {
            System.out.println("no soln");
        }
        System.out.println("Ended .. ");
    }
    public static boolean solve(int board[][],int x,int y)
    {
        if (isComplete(board))
            return true;
        if (!isValid(board))
            return false;
        int a=nextX(board,x,y);
        int b=nextY(board,x,y);
        for (int k=1;k<=9;k++)
        {
            board[b][a]=k;
            if (solve(board,a,b))
                return true;
            board[b][a]=0;
        }
        return false;
    }
    public static int nextX(int board[][],int x,int y)
    {
        for (int i=y;i<9;i++)
            for (int j=x;j<9;j++)
                if (board[i][j]==0)
                    return j;
        return 0;
    }
    public static int nextY(int board[][],int x,int y)
    {
        for (int i=y;i<9;i++)
            for (int j=x;j<9;j++)
                if (board[i][j]==0)
                    return i;
        return 0;
    }
    public static boolean isComplete(int board[][])
    {
        for (int i=0;i<9;i++)
            for (int j=0;j<9;j++)
                if (board[i][j]==0)
                    return false;
        return isValid(board);
    }
    public static boolean isValid(int board[][])
    {
        int chk[]=new int[10];
        // Horizontal lines
        for (int i=0;i<9;i++)
        {
            chk=new int[10];
            for (int j=0;j<9;j++)
                chk[board[i][j]]++;
            for (int k=1;k<=9;k++)
                if (chk[k]>1)
                    return false;
        }
        // Vertical lines
        for (int j=0;j<9;j++)
        {
            chk=new int[10];
            for (int i=0;i<9;i++)
                chk[board[i][j]]++;
            for (int k=1;k<=9;k++)
                if (chk[k]>1)
                    return false;
        }
        // Blocks
        for (int k=0;k<3;k++)
        {
            for (int l=0;l<3;l++)
            {
                chk=new int[10];
                for (int i=k*3;i<(k+1)*3;i++)
                    for (int j=l*3;j<(l+1)*3;j++)
                        chk[board[i][j]]++;
                for (int n=1;n<=9;n++)
                    if (chk[n]>1)
                        return false;
            }
        }
        return true;
    }
}