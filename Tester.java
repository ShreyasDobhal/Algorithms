package CodeChef;

import java.util.*;
import java.io.*;

class Tester
{
    private static final String dir="E:\\BlueJ\\Shreyas";
    private static HashMap<String,String> match;
    private static boolean nextTest=true;
    private static boolean done=false;
    
    public static String input="";
    public static final int testcases=30;
    /**
     * This method compares the output of prog1 and prog2
     */
    public static void runTest(String prog1,String prog2)throws Exception
    {
        System.out.println("Test started");
        Thread startTest=new Thread(new Runnable(){
            @Override
            public void run()
            {
                for (int i=0;i<testcases;i++)
                {
                    System.out.println(i);
                    try {
                        while (!nextTest)
                            Thread.sleep(10);
                    }
                    catch(Exception e)
                    {
                    }
                    nextTest=false;
                    //System.out.println(i+")");
                    match=new HashMap<>();
                    input=generateInput();
                    execProg(prog1,input);
                    execProg(prog2,input);
                }
                
            }
        });
        startTest.start();
        startTest.join();
        System.out.println("Test completed");
    }
    /**
     * Modify this method for generating random test cases
     */
    public static void generateInput(boolean flag)
    {
        System.out.println(generateInput());
    }
    public static String generateInput()
    {
        int l=10;//(int)(Math.random()*10)+1;
        String str="";
        str=str+l+"\n";
        for (int i=0;i<l;i++)
            str=str+randomStr()+"\n";
        return str+"\n";
    }
    public static String randomStr()
    {
        int l=(int)(Math.random()*50)+1;
        String str="";
        for (int i=0;i<l;i++)
            str=str+(char)((Math.random()*26)+'a');
        return str;
    }
    public static String generateInput(int ihdf)
    {
        String str="";
        int n=(int)(Math.random()*20)+5;
        str=n+"\n";
        for (int i=0;i<n;i++)
        {
            int tmp=(int)(Math.random()*100)+1;
            str=str+tmp+" ";
        }
        str=str+"\n";
        int q=(int)(Math.random()*10)+5;
        str=str+q+"\n";
        for (int i=0;i<q;i++)
        {
            int type=(int)(Math.random()*2)+1;
            str=str+type+" ";
            if (type==1)
            {
                int x=(int)(Math.random()*n)+1;
                int y=(int)(Math.random()*100)+1;
                str=str+x+" "+y+"\n";
            }
            else
            {
                int x=(int)(Math.random()*n)+1;
                int y=(int)(Math.random()*n)+1;
                int z=(int)(Math.random()*100)+1;
                str=str+Math.min(x,y)+" "+Math.max(x,y)+" "+z+"\n";
            }
        }
        //System.out.println(str);
        return str;
    }
    private static void execProg(String progName,String input)
    {
        Thread execute=new Thread(new Runnable(){
            @Override
            public void run()
            {
                String ans="";
                try 
                {
                    //System.out.println("Running "+progName);
                    ProcessBuilder pb=new ProcessBuilder("cmd","/c","java CodeChef."+progName);
                    pb.directory(new File(dir));
                    Process p=pb.start();
                    BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
                    byte buffer[]=input.getBytes();
                    OutputStream os=(p.getOutputStream());
                    os.write(buffer,0,buffer.length);
                    os.flush();
                    String str;
                    long t1=System.currentTimeMillis();
                    while ((str=br.readLine())!=null)
                    {
                        ans=ans+str+"\n";
                    }
                    //System.out.println(progName+" : \n"+ans);
                }
                catch (Exception e)
                {
                    System.out.println("error in "+progName);
                }
                gotResult(progName,ans);
            }
        });
        execute.start();
    }
    private static void gotResult(String progName,String ans)
    {
        match.put(progName,ans);
        if (match.size()==2)
            compareResult();
    }
    private static void compareResult()
    {
        int i=0;
        String tmp="";
        boolean result=false;
        //String ans1="abc",ans2="def";
        for (String s:match.keySet())
        {
            if (i==0)
                tmp=match.get(s);
            else
            {
                if (tmp.equals(match.get(s)))
                    result=true;
                else
                {
                    System.out.println("\n---\n"+tmp.compareTo(match.get(s))+"\n---\n");
                    result=false;
                    
                    //ans1=tmp;
                    //ans2=match.get(s);
                }
                break;
            }
            i++;
        }
        if (!result)
        {
            System.out.println("Wrong : \n"+input.substring(0,input.length()-1));
            //System.out.println("----\n"+ans1+"\n");
            //System.out.println("----\n"+ans2+"\n");
        }
        else
        {
            //System.out.println("Correct : "+input.substring(0,input.length()-1));
        }
        nextTest=true;
    }
}