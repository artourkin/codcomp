package artur.week4;

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
            int vertices = in.nextInt();
            int edges = in.nextInt();
            int[][] adjacencyMatrix=new int [vertices][vertices];
            List<HashMap<Integer,Integer>> adjacencyList=new ArrayList<HashMap<Integer, Integer>>();

            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new HashMap<Integer, Integer>());
            }

            for (int i = 0; i < edges; i++) {
                int edgeFrom = in.nextInt()-1;
                int edgeTo = in.nextInt()-1;
                int weight = in.nextInt();
                adjacencyMatrix[edgeFrom][edgeTo]=weight;
              //  adjacencyMatrix[edgeTo][edgeFrom]=1;
                adjacencyList.get(edgeFrom).put(edgeTo, weight);
              //  adjacencyList.get(edgeTo).add(edgeFrom);
            }

            int[] color=new int[vertices];

            int[] ints = runDijkstraSparse(adjacencyList, 0);

            for (int anInt : ints) {
                    out.print(anInt + " ");
            }


        }
    }



    private static int[] runDijkstraSparse(List<HashMap<Integer, Integer>> adjacencyList, int vertexID) {
        int length = adjacencyList.size();
        int[] distances=new int[length];
        PriorityQueue<Tuple> priorityQueue=new PriorityQueue<Tuple>(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple o1, Tuple o2) {
                if (o1.getB()<o2.getB())
                    return -1;
                else if (o1.getB()>o2.getB())
                    return 1;
                else
                    return 0;
            }
        });
        distances[vertexID]=0;
        for (int i = 0; i < distances.length; i++) {
            if (i!=vertexID)
                distances[i]=Integer.MAX_VALUE;
            priorityQueue.add(new Tuple(i,distances[i]));
        }

        while(!priorityQueue.isEmpty()){
            Tuple poll = priorityQueue.poll();
            Integer vertex = poll.getA();

            HashMap<Integer, Integer> integerIntegerHashMap = adjacencyList.get(vertex);
            for (Map.Entry<Integer, Integer> integerIntegerEntry : integerIntegerHashMap.entrySet()) {
                Integer destination = integerIntegerEntry.getKey();
                Integer weight = integerIntegerEntry.getValue();
                int newDistance=distances[vertex]+weight;
                if (newDistance<0)
                    newDistance=Integer.MAX_VALUE;
                if (distances[destination]>newDistance){
                    distances[destination]=newDistance;
                    priorityQueue.remove(new Tuple(destination,distances[destination]));
                    priorityQueue.add(new Tuple(destination,newDistance));
                }
            }


        }

        return distances;
    }
}

class Tuple{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (A != tuple.A) return false;
        return B == tuple.B;

    }

    @Override
    public int hashCode() {
        int result = A;
        result = 31 * result + B;
        return result;
    }

    public Tuple(int a, int b) {

        A = a;
        B = b;
    }

    public int getA() {

        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    int A;
    int B;

}