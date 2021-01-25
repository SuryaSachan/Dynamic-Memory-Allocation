import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
public class checker {
    public static void main(String args[]) throws java.io.FileNotFoundException
    {
        File a = new File("./rank_data1_final.txt");
        File r=new File("./out1.txt");
        Scanner s= new Scanner(r);
        Scanner sc = new Scanner(a);
        while(sc.hasNext()){
            String s1=sc.next();
            String s2=s.next();
            if(s1!=s2){
                System.out.println(s1+"...."+s2);
            }
        }
     //return 0;       
    }   
}
