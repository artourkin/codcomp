package artur.week2;


import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by artur on 28/03/2017.
 */
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
            FastScanner in = newInput();
            int count = in.nextInt();
            MyStackT<Integer> myStackT=new MyStackT<Integer>();
            for (int i=0;i<count;i++)
            {
                String element = in.next();

                if (element.equals("-")){
                    Integer pop2 = myStackT.pop();
                    Integer pop1 = myStackT.pop();
                    Integer result =pop1-pop2;
                    myStackT.push(result);
                } else if (element.equals("+")){
                    Integer pop2 = myStackT.pop();
                    Integer pop1 = myStackT.pop();
                    Integer result =pop1+pop2;
                    myStackT.push(result);
                } else if (element.equals("*")){
                    Integer pop2 = myStackT.pop();
                    Integer pop1 = myStackT.pop();
                    Integer result =pop1*pop2;
                    myStackT.push(result);
                } else {
                    myStackT.push(Integer.parseInt(element));
                }

            }
            out.println(myStackT.pop());
        }
    }


    static class MyStackT<T>{
        LinkedList<T> data=new LinkedList<T>();
        public void push(T o){
            data.addLast(o);
        }
        public T pop(){
            if (data.size()>0)
                return data.removeLast();
            else return null;
        }

        public Integer size() {
            return data.size();
        }

        public T peek() {
            return data.peekLast();
        }
    }

}


