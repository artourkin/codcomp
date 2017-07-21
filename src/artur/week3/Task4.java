package artur.week3;

import java.io.*;
import java.util.*;

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

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int size = in.nextInt();
            int k1 = in.nextInt()-1;
            int k2 = in.nextInt()-1;
            int A = in.nextInt();
            int B = in.nextInt();
            int C = in.nextInt();
            int a1 = in.nextInt();
            int a2 = in.nextInt();

            int[] array=new int[size];

            List<Integer> kstats=new ArrayList<Integer>();

            array[0]=a1;
            array[1]=a2;

            for (int i=2; i<size;i++){

                array[i]=A*array[i-2] + B*array[i-1]+C;
            }
            Random random=new Random(0);


            findKStatistic(array, 0, array.length - 1, k1, random);
            findKStatistic(array, 0, array.length - 1, k2, random);


            quickSort(array,k1,k2,random);

            for (int i=k1;i<=k2;i++) {
                kstats.add(array[i]);
            }

            for (Integer kstat : kstats) {
                out.print(kstat+" ");
            }

        }
    }
    public static void findKStatistic(int[] array, int left, int right, int k, Random random){
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
                int tmp = array[i];
                array[i]=array[j];
                array[j]=tmp;
                i++;
                j--;
            }
        }
        if (left<=j && k <=j)
            findKStatistic(array,left,j,k, random);
        if (i<=right && k >=i)
            findKStatistic(array,i,right,k, random);
    }

    public static void quickSort(int[] array, int left, int right, Random random){
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
                int tmp = array[i];
                array[i]=array[j];
                array[j]=tmp;
                i++;
                j--;
            }
        }
        if (left<=j )
            quickSort(array,left,j, random);
        if (i<=right)
            quickSort(array,i,right, random);
    }


}