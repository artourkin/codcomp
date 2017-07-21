package artur.week1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Task8 {

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
    static int LIMIT=18000;
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int numberOfTasks = in.nextInt();
            ArrayList<Integer> tasks=new ArrayList<Integer>();
            for (int i = 0; i < numberOfTasks; i++) {
                tasks.add(in.nextInt());
            }

            Collections.sort(tasks);

            int result=0;

            int currentCapacity=0;

            while(currentCapacity<LIMIT && tasks.size()>0) {
                result++;
                currentCapacity+=tasks.remove(0);
            }
            if (currentCapacity>LIMIT)
                result--;

            out.println(result);
        }
    }
}