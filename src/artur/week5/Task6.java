package artur.week5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Task6 {

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
            int verticesColumns = in.nextInt();
            int verticesRows = in.nextInt();
            List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();

            for (int i = 0; i < verticesColumns*verticesRows; i++) {
                adjacencyList.add(new ArrayList<Integer>());
            }
            int[][] array=new int[verticesRows][verticesColumns];
            for (int i = 0; i < verticesRows; i++) {
                for (int j = 0; j < verticesColumns; j++) {
                    array[i][j] = in.nextInt()-1;
                }
            }
            for (int i = 0; i < verticesRows; i++) {
                for (int j = 0; j < verticesColumns; j++) {
                    int currentVertex = array[i][j];
                    if ((i-1)>=0 && (array[i-1][j]==currentVertex-1 || array[i-1][j]==currentVertex+1)) {
                        int nextVertex = array[i - 1][j];
                        adjacencyList.get(currentVertex).add(nextVertex);
                    }
                    if ((i+1)<=verticesRows-1 &&(array[i+1][j]==currentVertex-1 || array[i+1][j]==currentVertex+1)) {
                        int nextVertex = array[i + 1][j];
                        adjacencyList.get(currentVertex).add(nextVertex);
                    }
                    if ((j+1)<=verticesColumns-1 &&(array[i][j+1]==currentVertex-1 || array[i][j+1]==currentVertex+1)) {
                        int nextVertex = array[i][j+1];
                        adjacencyList.get(currentVertex).add(nextVertex);
                    }
                    if ((j-1)>=0 &&(array[i][j-1]==currentVertex-1 || array[i][j-1]==currentVertex+1)) {
                        int nextVertex = array[i][j-1];
                        adjacencyList.get(currentVertex).add(nextVertex);
                    }
                }
            }
            int vertices=verticesColumns*verticesRows;
            int[] color=new int[vertices]; //1 visited, 2 exited
            int count=0;
            for (int i = 0; i < vertices; i++) {
                if (color[i]==0) {
                    int tmpCount = DFSCount(adjacencyList, i, color);
                    if (tmpCount>count)
                        count=tmpCount;
                }
            }

                out.println(count);


        }


    }

    private static int DFSCount(List<List<Integer>> adjacencyList, int vertexID, int[] color) {
        int result=1;
        color[vertexID]=1;

        List<Integer> vertices = adjacencyList.get(vertexID);
        int size =vertices.size();
        for (int i=0;i<size;i++) {
            int nextVertex = vertices.get(i);
            if ( color[nextVertex]==0)
                result+=DFSCount(adjacencyList,nextVertex,color);
        }
        return result;
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