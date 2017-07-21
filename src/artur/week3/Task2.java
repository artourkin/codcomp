package artur.week3;

import java.io.*;
import java.util.StringTokenizer;

public class Task2 {

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
            int[] array=new int[size];
            int[] w=new int[size];
            int[] tmp=new int[size];
            for (int i=0;i<size;i++){
                array[i]=in.nextInt();
            }

            long inversions=mergesort(array,tmp, 0, size-1);
            out.print(inversions);

        }
    }

    private static long mergesort(int[] array, int[] tmp,int lowIndex, int highIndex) {
        if (lowIndex==highIndex)
            return 0;
        long inversions=0;
        int midIndex=(lowIndex+highIndex)/2;
        inversions+=mergesort(array,tmp,lowIndex,midIndex);
        inversions+=mergesort(array,tmp,midIndex+1,highIndex);
        inversions += merge(array, tmp, lowIndex, midIndex + 1, highIndex);
        return inversions;
    }

    private static long merge(int[] array,int[] tmp, int lowIndex, int midIndex, int highIndex) {
        long inversions=0;
        int leftPartIndex=lowIndex;
        int rightPartIndex=midIndex;
        int bufferIndex=lowIndex;

        int k=leftPartIndex;
        for (int i=lowIndex;i<=highIndex;i++){
            tmp[i]=array[i];
        }
        while(leftPartIndex<midIndex && rightPartIndex<=highIndex){
            if (tmp[leftPartIndex]<=tmp[rightPartIndex]){
                array[k]=tmp[leftPartIndex];
                leftPartIndex++;

            } else {
                    inversions+=midIndex-leftPartIndex;
                array[k]=tmp[rightPartIndex];
                rightPartIndex++;

            }
            k++;

        }
        while(leftPartIndex<midIndex){
            array[k]=tmp[leftPartIndex];
            leftPartIndex++;
            k++;
        }
        return inversions;
    }
}