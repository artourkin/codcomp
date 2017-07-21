package artur.week3;

import java.io.*;
import java.util.*;

public class Task8 {

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
            int width = in.nextInt();
            List[] lists=new List[width];

            for (int i = 0; i < size; i++) {
                int index = i % width;
                if (lists[index]==null)
                    lists[index]=new ArrayList<Integer>();
                lists[index].add(in.nextInt());
            }

            for (List list : lists) {
                Collections.sort(list);
            }

            int[] array = combineListsIntoArray(lists);
            boolean sorted = isSorted(array);
            if (sorted)
                out.print("YES");
            else
                out.print("NO");

        }
    }
    private static int[] combineListsIntoArray(List[] lists){
        int size=0;
        for (List list : lists) {
            size += list.size();
        }
        int[] result=new int[size];
        int width = lists.length;
        for (int i = 0; i < lists.length; i++) {
            ArrayList<Integer> list = (ArrayList<Integer>) lists[i];
            int index=i;
            for (int j = 0; j < list.size(); j++) {
                Integer integer = list.get(j);
                result[index]=integer;
                index+=width;
            }
        }
        return result;
    }

    private static boolean isSorted(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            if (array[i]>array[i+1])
                return false;
        }
        return true;

    }

}