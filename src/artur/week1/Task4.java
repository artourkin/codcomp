package artur.week1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task4 {

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

    static double targetFunction(int a, int b, int c){
        double result;
        double sum=a*a+ b*b + c*c;
        result=Math.sqrt(sum);
        return result;


    }

    static int getElementFromArray(int[][] array, int index){

        int colIndex= index % 3;
        int rowIndex= index / 3;

        return array[rowIndex][colIndex];
    }
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();

            int[][] array=new int[3][3];

            for (int i=0; i<3; i++) {
                for (int j=0; j < 3; j++){
                    array[i][j]=in.nextInt();
                }
            }

            double maxValue=Double.MIN_VALUE;

            for (int i=0; i<9; i++) {
                for (int j = 0; j < 9; j++) {
                    for (int k = 0; k < 9; k++) {
                        if (i != j && i != k && j != k) {
                            if (i / 3 != j / 3 && i / 3 != k / 3 && k / 3 != j / 3) {
                                if (i % 3 != j % 3 && i % 3 != k % 3 && k % 3 != j % 3) {
                                    double v = targetFunction(getElementFromArray(array, i), getElementFromArray(array, j), getElementFromArray(array, k));
                                    if (maxValue<v)
                                    {
                                        maxValue=v;
                                    }
                                }
                            }
                        }
                    }
                }
            }




            out.println(maxValue);
        }
    }
}