package artur.week2;


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by artur on 28/03/2017.
 */
public class Task7 {


    static class FastScanner {
        static BufferedReader br;
        static StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        public FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    static FastScanner newInput() throws IOException {
        return new FastScanner(new File("input.txt"));
    }
    static PrintWriter newOutput() throws IOException {
        return new PrintWriter("output.txt");
    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int count = in.nextInt();

            /**
             * The idea is to count the number of '0' cups needed to even all the stacks.
             * When a new stack is created, it will require (maxHeight-1) '0' cups to become as large as all the other stacks.
             * When a '0' cup is added to any stack, it will require (stack-1) '0' cups to make all the other stacks even again.
             * The variable zerosToEvenStacks accumulates these numbers of '0' cups.
             *
             *
             */
            int stacks=0;
            int maxHeight=0;
            int zerosToEvenStacks=0;
            int [] cups=new int[count];
            for (int i=0;i<count;i++) {
                int cup = in.nextInt();
                cups[i] = cup;
            }
            if (cups[0] == 0) {
                stacks = 1;
            }

            for (int i=0;i<count; i++) {
                if (cups[i]==0){
                    if (zerosToEvenStacks==0) {
                        maxHeight += 1;
                        zerosToEvenStacks-=(stacks-1);
                    }
                    else
                    zerosToEvenStacks++;

                } else
                {
                    stacks++;
                    if (stacks==1){
                        maxHeight++;
                    }
                    zerosToEvenStacks-=(maxHeight-1);
                }
            }
            out.print(maxHeight);

        }
    }





}


