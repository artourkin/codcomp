package artur.week3;

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {

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

            StringBuilder sb=new StringBuilder();
            mergesort(array,tmp, sb,0, size-1);



            Error er=new Error();
            for (int i=0;i<size;i++){
                sb.append(array[i] + " ");
            }
            out.print(sb.toString());

        }
    }

    private static void mergesort(int[] array, int[] tmp, StringBuilder sb,int lowIndex, int highIndex) {
        if (lowIndex==highIndex)
            return;
        int midIndex=(lowIndex+highIndex)/2;
        mergesort(array,tmp,sb,lowIndex,midIndex);
        mergesort(array,tmp, sb,midIndex+1,highIndex);
        merge(array,tmp, lowIndex, midIndex+1,highIndex, sb);

    }

    private static void merge(int[] array,int[] tmp, int lowIndex, int midIndex, int highIndex, StringBuilder sb) {
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
        sb.append((lowIndex+1) + " ");
        sb.append((highIndex+1) + " ");
        sb.append((array[lowIndex]) + " ");
        sb.append((array[highIndex]) + " \n");
    }
}