package artur.week2;


import java.io.*;
import java.util.*;
import java.util.zip.Inflater;

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
            int count = in.nextInt();
            MyQueue myQueue=new MyQueue();
            for (int i=0;i<count;i++)
            {
                String command = in.next();
                if (command.equals("+")){
                    myQueue.push(in.nextInt());
                } else if (command.equals("-")){
                    myQueue.pop();
                } else if (command.equals("?")){
                    out.println(myQueue.min());
                }
            }
        }
    }

}

class MyQueue{
    LinkedList<Integer> inbox=new LinkedList<Integer>();
    LinkedList<Integer> outbox=new LinkedList<Integer>();

    LinkedList<Integer> minInbox=new LinkedList<Integer>();
    LinkedList<Integer> minOutbox=new LinkedList<Integer>();

    public void push(Integer o){
        inbox.addLast(o);
        Integer tmp = minInbox.peekLast();
        if (tmp==null){
            minInbox.addLast(o);
        } else {
            if (tmp>o)
                minInbox.addLast(o);
            else
                minInbox.addLast(tmp);
        }
    }
    public Integer pop(){
        if (outbox.isEmpty()){
            while(!inbox.isEmpty()){
                Integer last = inbox.removeLast();
                outbox.addLast(last);

                Integer lastMin = minOutbox.peekLast();
                if (lastMin==null)
                    minOutbox.addLast(last);
                else{
                    if (lastMin > last)
                        minOutbox.addLast(last);
                    else
                        minOutbox.addLast(lastMin);
                }
            }
            minInbox.clear();
        }
        minOutbox.removeLast();
        return outbox.removeLast();
    }

    public Integer min() {
        if (!minInbox.isEmpty() && !minOutbox.isEmpty())
            return Math.min(minInbox.peekLast(),minOutbox.peekLast());
        else
        {
            if (minOutbox.isEmpty())
            return minInbox.peekLast();
            else
                return minOutbox.peekLast();

        }

    }
}