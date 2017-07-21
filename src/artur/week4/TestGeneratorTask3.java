package artur.week4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestGeneratorTask3 {


    static PrintWriter newOutput() throws IOException {
            return new PrintWriter("input.txt");
    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            Random r=new Random();
            int N=20;//r.nextInt(1998)+2;
            int M=50;//r.nextInt(4999)+1;

            int S=r.nextInt(N-1)+1;
            //Long maxWeight=Math.round(Math.pow(10,15));
            out.println(N+" "+ M + " " + S);
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<M;i++){
                int source = r.nextInt(N-1) + 1;
                int target = r.nextInt(N - 1) + 1;
                int weight=r.nextInt();
                out.println(source+" "+ target+ " " + weight);
            }
        }
    }


}