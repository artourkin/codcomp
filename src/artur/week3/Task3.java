package artur.week3;

import java.io.*;
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

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int size = in.nextInt();

            int[]array=createArray(size);

            for (int i=0; i<array.length;i++){
                out.print(array[i] + " ");
            }
        }
    }

    private static int[] createArray(int size) {
        int[] result=new int[size];

        for (int n=1;n<=size;n++){
            if (n==1){
                result[0]=1;
            } else if (n==2){
                result[1]=2;
            } else {
                result[n-1]=n;
                int pivotIndex=(n-1)/2;

                int tmp=result[pivotIndex];
                result[pivotIndex]=result[n-1];
                result[n-1]=tmp;

            }



        }
  /*      if (size==1){
            result[0]=1;
            return result;
        }
        else if (size==2) {
            result[0] = 1;
            result[1]=2;
        } else {
            int[] array = createArray(size - 1);
            int length = array.length;
            for (int i=0;i<length;i++)
                result[i]=array[i];
            result[size-1]=size;
            result=swap(result, size-1, (size-1)/2);
        }*/
        return result;
    }

    private static int[] swap(int[] result, int i, int j) {

        return result;
    }


}