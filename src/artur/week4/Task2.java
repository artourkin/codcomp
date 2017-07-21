package artur.week4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task2 {

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
            List<List<Integer>> adjacencyList=new ArrayList<List<Integer>>();

            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < edges; i++) {
                int edgeFrom = in.nextInt()-1;
                int edgeTo = in.nextInt()-1;
                adjacencyList.get(edgeFrom).add(edgeTo);
                adjacencyList.get(edgeTo).add(edgeFrom);
            }

            int[] colorArray=new int[vertices];

            boolean b=true;
            for (int i = 0; i < vertices; i++) {
                if (colorArray[i]==0) {
                    b = isBipartite(adjacencyList, i, colorArray, 1);
                    if (!b) {
                        out.println("NO");
                        return;
                    }
                }
            }

            if (b){
                out.println("YES");
            }
        }
    }

    private static boolean isBipartite(List<List<Integer>> adjacencyList, int vertexID, int[] colorArray, int color) {
        colorArray[vertexID]=color;
        List<Integer> vertices = adjacencyList.get(vertexID);
        int size =vertices.size();
        for (int i=0;i<size;i++) {
            color=colorArray[vertexID];
            int nextVertex = vertices.get(i);
            if (!(colorArray[nextVertex] == 1 || colorArray[nextVertex]==2)){
                if (color==1)
                    color=2;
                else
                    color=1;
                if (! isBipartite(adjacencyList,nextVertex,colorArray,color))
                    return false;
            } else {
                int currentVertexColor = colorArray[vertexID];
                int nextVertexColor = colorArray[nextVertex];
                if (currentVertexColor==nextVertexColor)
                    return false;
            }


        }
        return true;
    }

}