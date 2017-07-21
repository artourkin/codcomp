package artur.week4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
            int vertices = in.nextInt();
            int edges = in.nextInt();
            List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();

            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < edges; i++) {
                int edgeFrom = in.nextInt() - 1;
                int edgeTo = in.nextInt() - 1;
                adjacencyList.get(edgeFrom).add(edgeTo);
            }

           // //List<Integer> visitedVertices = new ArrayList<>();
           // List<Integer> exitedVertices = new ArrayList<>();
           // int[] visitedVertices=new int[vertices];
            int[] color=new int[vertices]; //1 visited, 2 exited
            boolean b = false;
            for (int i = 0; i < vertices; i++) {
                if (color[i]==0) {
                    int[] parents = new int[vertices];
                    parents[i] = -1;
                    b = DFSHasCycles(adjacencyList, parents, i, color);
                    if (b) {
                        out.println("YES");
                        Stack<Integer> stack=new Stack<Integer>();
                        Integer lastVertexOfCycle=-1;
                        Integer firstVertexOfCycle=-1;
                        for (int z=0;z<color.length;z++){
                            if (color[z]==5) {
                                lastVertexOfCycle = z;
                                firstVertexOfCycle=z;
                                break;
                            }
                            if (color[z]==3)
                                lastVertexOfCycle=z;
                            if (color[z]==4)
                                firstVertexOfCycle=z;
                        }
                       // Integer lastVertexOfCycle = exitedVertices.get(exitedVertices.size() - 2);
                       // Integer firstVertexOfCycle = exitedVertices.get(exitedVertices.size() - 1);
                        StringBuilder sb=new StringBuilder();
                        int i1 = lastVertexOfCycle + 1;
                        stack.push(i1);
                        int parent = parents[lastVertexOfCycle];
                        while (parent != parents[firstVertexOfCycle]) {
                            int toPrint = parent + 1;
                            stack.push(toPrint);
                            parent = parents[parent];
                        }

                        while(!stack.empty()){
                                sb.append(stack.pop() + " ");
                        }

                        out.print(sb);
                        return;
                    }
                }
            }

            if (!b)
                out.println("NO");


        }


    }

    private static void DFS(List<List<Integer>> adjacencyList, int vertexID, List<Integer> result) {
        result.add(vertexID);
        List<Integer> vertices = adjacencyList.get(vertexID);
        int size =vertices.size();
        for (int i=0;i<size;i++) {
            int nextVertex = vertices.get(i);
            if ( !result.contains(nextVertex))
                DFS(adjacencyList,nextVertex,result);
        }
    }

    private static boolean DFSHasCycles(List<List<Integer>> adjacencyList, int[] parents, int vertexID, int[] color) {
        color[vertexID]=1;
        List<Integer> vertices = adjacencyList.get(vertexID);
        int size =vertices.size();
        for (int i=0;i<size;i++) {
            int nextVertex = vertices.get(i);
            if (color[nextVertex]!=2 && color[nextVertex]==1) {
                if (vertexID==nextVertex)
                    color[vertexID]=5;
                else {
                    color[vertexID] = 3;
                    color[nextVertex] = 4;
                }
                //exited.add(vertexID);
                //exited.add(nextVertex);
               // parents[nextVertex]=vertexID;
                return true;
            }
            if ( color[nextVertex]==0){
                parents[nextVertex]=vertexID;
                if ( DFSHasCycles(adjacencyList,parents,nextVertex,color))
                    return true;
            }
        }
        color[vertexID]=2;
        return false;
    }

}