package artur.week4;

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

    static class Edge{
        public Edge(int vertexFrom, int vertexTo, long weight) {
            this.vertexFrom = vertexFrom;
            this.vertexTo = vertexTo;
            this.weight = weight;
        }

        public int getVertexFrom() {
            return vertexFrom;
        }

        public void setVertexFrom(int vertexFrom) {
            this.vertexFrom = vertexFrom;
        }

        public int getVertexTo() {
            return vertexTo;
        }

        public void setVertexTo(int vertexTo) {
            this.vertexTo = vertexTo;
        }

        public long getWeight() {
            return weight;
        }

        public void setWeight(long weight) {
            this.weight = weight;
        }

        int vertexFrom;
        int vertexTo;
        long weight;

    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int vertices = in.nextInt();
            int edges = in.nextInt();
            int vertexID = in.nextInt() - 1;
            List<HashMap<Integer, Long>> adjacencyList = new ArrayList<HashMap<Integer, Long>>();

            //for (int i = 0; i < vertices; i++) {
            //    adjacencyList.add(new HashMap<Integer, Long>());
            //}
            ArrayList<Edge> edgesList=new ArrayList<>();
            for (int i = 0; i < edges; i++) {
                int edgeFrom = in.nextInt() - 1;
                int edgeTo = in.nextInt() - 1;
                long weight = in.nextLong();
                edgesList.add(new Edge(edgeFrom,edgeTo,weight));
            }


            Long[] longs = runBellmanFord(edgesList,vertices, vertexID);

            for (long aLong : longs) {
                if (aLong == Long.MAX_VALUE)
                    out.println('*');
                else if (aLong == Long.MIN_VALUE)
                    out.println('-');
                else
                    out.println(aLong);
            }


        }
    }

    private static Long[] runBellmanFord(List<Edge> edgeList, int vertices, int vertexID) {
        int length = vertices;
        Long[] distances = new Long[length];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Long.MAX_VALUE;
        }
        distances[vertexID] = 0L;
        boolean updated = false;
        for (int i = 0; i < length - 1; i++) {
            updated = false;
            for (Edge edge : edgeList) {
                Integer to = edge.getVertexTo();
                Integer from=edge.getVertexFrom();
                Long weight = edge.getWeight();
                if (distances[from]==Long.MAX_VALUE){
                    continue;
                }
                long l = distances[from] + weight;
                if (distances[from]>0 && weight > 0 && l < 0) {
                    l=Long.MAX_VALUE;
                } else
                if (distances[from]<0 && weight < 0 && l > 0) {
                    l=Long.MIN_VALUE;
                }

                if (l < distances[to]) {
                    distances[to] = l;
                    updated = true;
                }

            }
            if (updated == false) {
                return distances;
            }
        }
        updated=true;
        while(updated==true){
            updated = false;
            for (Edge edge : edgeList) {
                Integer to = edge.getVertexTo();
                Integer from=edge.getVertexFrom();
                Long weight = edge.getWeight();
                if (distances[from]==Long.MIN_VALUE && distances[to]!=Long.MIN_VALUE){
                    distances[to] = Long.MIN_VALUE;
                    updated = true;
                    continue;
                }
                if (distances[from]==Long.MAX_VALUE){
                    continue;
                }
                long l = distances[from] + weight;
                if (distances[from]>0 && weight > 0 && l < 0) {
                    l=Long.MAX_VALUE;
                } else
                if (distances[from]<0 && weight < 0 && l > 0) {
                    l=Long.MIN_VALUE;
                }
                    if (l < distances[to]) {
                        distances[to] = Long.MIN_VALUE;
                        updated = true;
                    }
            }


        }
        return distances;

    }


    public static boolean willAdditionOverflow(long left, long right) {
        if (right < 0 && right != Long.MIN_VALUE) {
            return willSubtractionOverflow(left, -right);
        } else {
            return (~(left ^ right) & (left ^ (left + right))) < 0;
        }
    }

    public static boolean willSubtractionOverflow(long left, long right) {
        if (right < 0) {
            return willAdditionOverflow(left, -right);
        } else {
            return ((left ^ right) & (left ^ (left - right))) < 0;
        }
    }


}