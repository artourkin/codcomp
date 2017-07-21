package artur.week3;

import java.io.*;
import java.util.*;

public class TestGeneratorTask5 {


    static PrintWriter newOutput() throws IOException {
            return new PrintWriter("input.txt");
    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            Random r=new Random();
            int size = 50000;
            out.println(size);
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<size;i++){
                sb.append(r.nextInt(1000000000)+" ");
            }
            out.println(sb.toString());
            out.println(6661);






        }
    }


}