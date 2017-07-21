package artur.week2;


import java.io.*;
import java.util.*;

/**
 * Created by artur on 28/03/2017.
 */
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
            FastScanner in = newInput();
            int count = in.nextInt();
            Deque<Integer> deque=new ArrayDeque<>();
            Deque<Integer> dequeForMum=new ArrayDeque<>();
            LinkedList<Integer> list=new LinkedList<>();
            for (int i=0;i<count;i++) {
                String phrase = in.next();
                if (phrase.equals("add")){
                    deque.push(in.nextInt());
                    normalizeDeques(dequeForMum,deque);
                } else if (phrase.equals("take")) {
                    deque.pop();
                    normalizeDeques(dequeForMum,deque);
                } else if (phrase.equals("mum!")){
                    Deque<Integer> tmp=dequeForMum;
                    dequeForMum=deque;
                    deque=tmp;
                    normalizeDeques(dequeForMum,deque);
                }
            }
            out.println(deque.size()+dequeForMum.size());
            StringBuilder sb=new StringBuilder();
            int size = dequeForMum.size();
            for (int i = 0; i < size; i++) {
                sb.append(dequeForMum.removeLast()+" ");
            }
            int size1 = deque.size();
            for (int i = 0; i < size1; i++) {
                sb.append(deque.removeLast()+" ");
            }
            out.print(sb.toString());
        }
    }



    private static void normalizeDeques(Deque<Integer> dequeLeft, Deque<Integer> dequeRight) {
        int sizeDequeLeft = dequeLeft.size();
        int sizeDequeRight = dequeRight.size();
        int delta = sizeDequeRight - sizeDequeLeft;

        if (delta > 0)   //New items were added to the right deque
        {
            if (sizeDequeRight>0 && delta%2==0){
                Integer integer = dequeRight.removeLast();
                dequeLeft.push(integer);
            }
        } else          //Items were deleted from the right deque
        {
            if (sizeDequeLeft>0 && Math.abs(delta)%2==1){
                Integer integer = dequeLeft.poll();
                dequeRight.addLast(integer);
            }

        }
    }
}








