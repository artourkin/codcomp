package artur.week5;

import java.io.*;
import java.util.*;

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
            int maxW = in.nextInt();

            int[] array=new int[size];


            for (int i = 0; i < size; i++) {
              array[i]=(in.nextInt());
            }
            Random random=new Random();
            boolean sortable = quickSort(array,0,array.length-1,random, maxW);// isSortable(array,maxW);
            if (sortable)
                out.println("Yes");
            else
                out.println("No");

        }
    }

    private static boolean isSortable(int[] array, int  maxW) {
        int[] tmpArray=new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tmpArray[i]=array[i];
        }








        while(true){
            boolean updated=false;
            for (int i = 0; i < array.length-1; i++) {
                if(array[i]>array[i+1]){
                    long sum = array[i] + array[i + 1];
                    if (sum>maxW)
                        return false;
                    int tmp=array[i];
                    array[i]=array[i+1];
                    array[i+1]=tmp;
                    updated=true;
                }
            }
            if (updated==false)
                return true;

        }
    }

    public static boolean quickSort(int[] array, int left, int right, Random random, int maxW){
        int i=left;
        int j=right;
        int m;

        if (right-left==0)
            m=right;
        else
            m=random.nextInt(right-left)+left;
        int pivot=array[m];
        while(i<=j){
            while(array[i]<pivot)
                i++;
            while(array[j]>pivot)
                j--;
            if (i<=j){
                long sum = array[i] + array[i + 1];
                if (sum>maxW)
                    return false;
                int tmp = array[i];
                array[i]=array[j];
                array[j]=tmp;
                i++;
                j--;
            }
        }
        if (left<=j )
           return quickSort(array,left,j, random,maxW);
        if (i<=right)
           return quickSort(array,i,right, random,maxW);

        return true;
    }
}