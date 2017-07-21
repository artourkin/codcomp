package artur.week2;


import java.io.*;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by artur on 28/03/2017.
 */
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
            int count = in.nextInt();
            int[] snowMenOrigin=new int[count+1];
            int[] snowMenToAdd=new int[count+1];
            int[] snowMenMass=new int[count+1];
            snowMenMass[0]=0;
            snowMenOrigin[0]=0;
            snowMenToAdd[0]=0;
            long total=0;
            for (int i=0;i<count;i++)
            {
                Integer snowmanIDToClone = in.nextInt();
                Integer snowBallSizeToAdd = in.nextInt();

                if (snowmanIDToClone==0) {

                    snowMenOrigin[i+1]=snowmanIDToClone;
                    snowMenToAdd[i+1]=snowBallSizeToAdd;
                    snowMenMass[i+1]=snowBallSizeToAdd;

                } else {
                    if (snowBallSizeToAdd>0) {
                        snowMenOrigin[i+1]=snowmanIDToClone;
                        snowMenToAdd[i+1]=snowBallSizeToAdd;
                        snowMenMass[i+1] = snowMenMass[snowmanIDToClone] + snowBallSizeToAdd;
                    }
                    else {

                        int predecessor = snowMenOrigin[snowmanIDToClone];
                        int origin = snowMenOrigin[predecessor];
                        int toAdd=snowMenToAdd[predecessor];
                        // Integer keyPredecessor = snowMenOrigin[predecessor];// snowMenNew.get(predecessor).getKey();
                        //Integer valuePredecessor = snowMenToAdd[predecessor];// snowMenNew.get(predecessor).getValue();
                        snowMenOrigin[i+1]=origin;
                        snowMenToAdd[i+1]=toAdd;
                        snowMenMass[i+1] = snowMenMass[origin]+toAdd;
                    }
                }
                total +=snowMenMass[i+1];
            }

            out.println(total);
        }
    }


}


