package artur.week4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestGeneratorTask1 {


    static PrintWriter newOutput() throws IOException {
            return new PrintWriter("input.txt");
    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            Random r=new Random();
            int N=r.nextInt(100000)+1;
            int M=r.nextInt(100000);
            out.println(N+" "+ M );
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<M;i++){
                int source = r.nextInt(N-1) + 1;
                int target = r.nextInt(N - 1) + 1;
                out.println(source+" "+ target);
            }
        }
    }


}