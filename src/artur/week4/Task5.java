package artur.week4;

import java.io.*;
import java.util.*;

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

            //int[] ints = runDijkstraSparse(adjacencyList, 0);

           // for (int anInt : ints) {
           //         out.print(anInt + " ");
           // }


        }
    }






    private static int[] runDijkstraDense(List<HashMap<Integer, Integer>> adjacencyList, int vertexID, int[] color) {
        int length = adjacencyList.size();
        int[] distances=new int[length];
        ArrayList<Integer> vertices=new ArrayList<Integer>();
        for (int i = 0; i < distances.length; i++) {
            distances[i]=Integer.MAX_VALUE;
            vertices.add(i);
        }
        distances[vertexID]=0;


        while(!vertices.isEmpty()){
            int minDistance=Integer.MAX_VALUE;
            Integer vertex=-1;
            for (Integer vertice : vertices) {
                if (distances[vertice]<minDistance) {
                    minDistance = distances[vertice];
                    vertex=vertice;
                }
            }
            vertices.remove(vertex);
            color[vertex]=1;
            HashMap<Integer, Integer> integerIntegerHashMap = adjacencyList.get(vertex);
            for (Map.Entry<Integer, Integer> integerIntegerEntry : integerIntegerHashMap.entrySet()) {
                Integer destination = integerIntegerEntry.getKey();
                Integer weight = integerIntegerEntry.getValue();
                int newDistance=distances[vertex]+weight;
                if (distances[destination]>newDistance){
                    distances[destination]=newDistance;
                }
            }


        }


        int min=Integer.MAX_VALUE;
        int minIndex=-1;
        for (int i = 0; i < color.length; i++) {
            if (color[i]==0 && distances[i]<min){

            }
        }
        return distances;
    }






}