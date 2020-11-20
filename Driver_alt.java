import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Driver_alt {
    public static void printarray(A1DynamicMem fox) {
        //Dictionary free1 = fox.freeBlk.getFirst();
        //Dictionary alloc1 = fox.allocBlk.getFirst();
        for (Dictionary d = fox.freeBlk.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.address + " ");
            System.out.print(d.address + d.size + " ");
            //System.out.print(d.key + " ");
            System.out.print("    ");
        }
        System.out.println(" ");
        for (Dictionary d = fox.allocBlk.getFirst(); d != null; d = d.getNext()) {
            System.out.print(d.address + " ");
            System.out.print(d.address + d.size + " ");
            //System.out.print(d.key + " ");
            System.out.print("    ");
        }
        System.out.println("");
        System.out.println("");
    }
    public static void main(String args[]) throws IOException{
        File myObj = new File("./test.in.txt");
        //File r=new File("./ans2.txt");
        //Scanner s= new Scanner(r);
        Scanner sc = new Scanner(myObj);
        int numTestCases,c=0;
        numTestCases = sc.nextInt();
        while (numTestCases-- > 0) {
            int size;
            size = sc.nextInt();
            A1DynamicMem obj = new A1DynamicMem(size,1);
            int numCommands = sc.nextInt();
            while (numCommands-- > 0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    default:
                        break;
                }
                //int t=s.nextInt();
                //if(t!=result)
                //c++;
                //System.out.println(result+ " "+c+" "+t);
                System.out.println(result);
                //System.out.println(command+" "+argument+":"+result);
                //printarray(obj);
            }

        }
        sc.close();
       // s.close();
    }
}