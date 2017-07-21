package artur.week3;

import java.io.*;
import java.util.StringTokenizer;

public class Task5 {

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
            int maxT = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                array[i] = in.nextInt();
                if (array[i] > maxT) {
                    maxT = array[i];
                }
            }
            int k = in.nextInt();

           // long inputIsFinished = System.nanoTime();

            int[] result = BinarySearch(array, k, 1, maxT);

           // long calculatedIsFinished = System.nanoTime();

            out.print(result[1]);

         //   long outputIsFinished = System.nanoTime();


            //System.out.println("input time, ms: " + (inputIsFinished - start) / 1000000);
            //System.out.println("calculation time, ms: " + (calculatedIsFinished - inputIsFinished) / 1000000);
            //System.out.println("output time, ms: " + (outputIsFinished - calculatedIsFinished) / 1000000);
            //System.out.println("total time, ms: " + (outputIsFinished - start) / 1000000);
        }
    }

    private static int[] BinarySearch(int[] array, int k, int minT, int maxT) {
        int L = minT;
        int R = maxT;
        int Vmin = isTEnough(array, k, L);
        int Vmax = isTEnough(array, k, R);
        if (Vmin == 1) return new int[]{-1, minT};
        if (Vmin == 0) return new int[]{minT, minT};
        if (Vmax == -1) return new int[]{maxT, -1};
        if (Vmax == 0) return new int[]{maxT, maxT};

        while (true) {
            int M = (L + R) / 2;
            if (M == L || M == R) return new int[]{L, R};
            Integer v = isTEnough(array, k, M);
            if (v == 0) return new int[]{M, M};
            if (v == -1) L = M;
            if (v == 1) R = M;

        }
    }

    private static Integer isTEnough(int[] array, int k, int t) {


        long balance=t;
        if (k==1 || k==0)
            balance=0;

        for (int i = 0; i < array.length; i++) {
            long remainingHumidity=array[i]-t;
            if (remainingHumidity>=0){
                long X=0;
                if (k==1 || k==0){
                    X=remainingHumidity;
                } else {
                    X=remainingHumidity/ (k-1);
                    if (remainingHumidity % (k-1)>0)
                        X++;
                }
                balance-=X;
            } else if (k==1 || k==0)
                balance-=remainingHumidity;
        }
        if (balance<0)
            return -1;
        if (balance>0)
            return 1;
        return 0;
    }


}