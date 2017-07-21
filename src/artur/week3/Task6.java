package artur.week3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task6 {

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

           // long start = System.nanoTime();
            FastScanner in = newInput();
            int size = in.nextInt();
            int[] array = new int[size];
            long planks=in.nextLong();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                array[i] = in.nextInt();
                if (array[i] < min) {
                    min = array[i];
                }
            }
            Arrays.sort(array);
           // long inputIsFinished = System.nanoTime();

            int[] result = BinarySearch(array, planks, 0, min);

           // long calculatedIsFinished = System.nanoTime();

            out.print(result[0]);

         //   long outputIsFinished = System.nanoTime();


            //System.out.println("input time, ms: " + (inputIsFinished - start) / 1000000);
            //System.out.println("calculation time, ms: " + (calculatedIsFinished - inputIsFinished) / 1000000);
            //System.out.println("output time, ms: " + (outputIsFinished - calculatedIsFinished) / 1000000);
            //System.out.println("total time, ms: " + (outputIsFinished - start) / 1000000);
        }
    }

    private static int[] BinarySearch(int[] array, long planks, int minT, int maxT) {
        int L = minT;
        int R = maxT;
        int Vmin = isTEnough(array, planks, L);
        int Vmax = isTEnough(array, planks, R);
        if (Vmin == 1) return new int[]{-1, minT};
        if (Vmin == 0) return new int[]{minT, minT};
        if (Vmax == -1) return new int[]{maxT, -1};
        if (Vmax == 0) return new int[]{maxT, maxT};

        while (true) {
            int M = (L + R) / 2;
            if (M == L || M == R) return new int[]{L, R};
            Integer v = isTEnough(array, planks, M);
            if (v == 0) return new int[]{M, M};
            if (v == -1) L = M;
            if (v == 1) R = M;

        }
    }

    private static Integer isTEnough(int[] array, long planks, int x) {



        long prevValue=array[0];
        if (x>prevValue)
            return 1;
        if (x!=0)
        planks-=prevValue;
        for (int i=1;i<array.length;i++){
            long delta = array[i] - prevValue;
            if (delta<=x){
                planks-=x;
                prevValue=array[i]+x;
            } else {
                planks-=delta;
                prevValue=array[i]+delta;
            }
            if (planks<0)
                return 1;
        }
        if (planks>0)
            return -1;
        return 0;
    }


}