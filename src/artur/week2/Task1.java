package artur.week2;


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
            int count = in.nextInt();
            MyStack myStack=new MyStack();
            for (int i=0;i<count;i++)
            {
                String command = in.next();
                if (command.equals("+")){
                    myStack.push(in.next());

                } else if (command.equals("-")){
                    out.println(myStack.pop());

                }
            }
        }
    }

}

class MyStack{
    LinkedList data=new LinkedList();
    public void push(Object o){
        data.addLast(o);
    }
    public Object pop(){
        if (data.size()>0)
            return data.removeLast();
        else return null;
    }

}