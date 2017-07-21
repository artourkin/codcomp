package artur.week1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task3 {

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

    static int findArraySum(int [] array){
        int result=0;
        for(int i=0; i< array.length;i++)
            result+=array[i];
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n = in.nextInt();

            List<Integer> listT=new ArrayList<Integer>(n);
            List<Integer> listP=new ArrayList<Integer>(n);

            int[] array=new int[n];

            int[] arrayT=new int[n];
            int[] arrayP=new int[n];

            for (int i=0; i<n; i++){
                arrayT[i]=in.nextInt();
               // array[i]=in.nextInt();
            }
            int allPs=0;
            for (int i=0; i<n; i++) {
                arrayP[i] = in.nextInt();
            }
            int indexSmallestDelta=-1;
            int smallestDelta=Integer.MAX_VALUE;
            for (int i=0; i< arrayP.length;i++){
                int delta= Math.abs(arrayP[i]- arrayT[i]);
                if (delta<smallestDelta){
                    smallestDelta=delta;
                    indexSmallestDelta=i;
                }
                if (arrayP[i] < arrayT[i]){
                    allPs--;
                    array[i]=arrayT[i];
                }
                else {
                    allPs++;
                    array[i]=arrayP[i];
                }
            }

            if (allPs%n==0){
                if (allPs>0){
                    array[indexSmallestDelta]=arrayT[indexSmallestDelta];
                }
                else {
                    array[indexSmallestDelta]=arrayP[indexSmallestDelta];
                }
            }


            int arraySum = findArraySum(array);

            out.println(arraySum);
        }
    }
}