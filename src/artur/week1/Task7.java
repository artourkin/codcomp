package artur.week1;

import java.io.*;
import java.util.*;

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




    public static int[] antiprimes={1,2,4,6,12,24,36,48,60,120,180,240,360,720,840,
            1260,1680,2520,5040,7560,10080,15120,20160,25200,
            27720,45360,50400,55440,83160,110880,166320,
            221760,277200,332640,498960,554400,665280,720720,
            1081080,1441440,2162160, 2882880, 3603600,  4324320, 6486480, 7207200, 8648640};
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();

            Stack<Integer> stack=new Stack<>();
            int K = in.nextInt();

            int indexMaxAntiprimeWithinRange = findIndexMaxAntiprimeWithinRange(K, antiprimes);

            int antiprime = antiprimes[indexMaxAntiprimeWithinRange];

            int result = K - antiprime+1;

            out.println(result);
        }
    }

    static int findIndexMaxAntiprimeWithinRange( int range, int[] antiprimes){
        int i;
        for (i=0;i<antiprimes.length;i++){
            if (antiprimes[i]>range)
                break;
        }
        return i-1;
    }


}