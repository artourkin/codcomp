package artur.week3;

import java.io.*;
import java.util.*;

public class Task7 {

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

           // long start = System.nanoTime();
            FastScanner in = newInput();
            int size = in.nextInt();
            Jewel[] array = new Jewel[size];
            double min=Double.MAX_VALUE;
            double max=0;
            int k=in.nextInt();
            for (int i = 0; i < size; i++) {
                int v = in.nextInt();
                int w= in.nextInt();
                Jewel jewel = new Jewel(i + 1, v, w);
                array[i]=jewel;

                if (min>jewel.getEffectiveness())
                    min=jewel.getEffectiveness();
                if (max<jewel.getEffectiveness())
                    max=jewel.getEffectiveness();
            }

            double v = BinarySearch(array, k, min, max);


            for (int i=0;i<k;i++) {
                out.print(array[i].getId()+" ");
            }

        }
    }

    private static double BinarySearch(Jewel[] array, int k, double min, double max) {
        double L = min;
        double R = max;
        int Vmin = isTEnough(array, k, L);
        int Vmax = isTEnough(array, k, R);
        if (Vmin == 1) return Vmin;
        if (Vmax == -1) return Vmax;

        List<Integer> result=new ArrayList<Integer>();
        while (true) {
            double M = (L + R) / 2;
            if (M == L || M == R) return M;
            int v = isTEnough(array, k, M);
            if (v == 0) return M;
            if (v == 1) L = M;
            if (v == -1) R = M;

        }
    }



    private static int isTEnough(Jewel[] array, final int k, final double effectiveness) {
        Arrays.sort(array, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                double SV1 = o1.getValue() - effectiveness * o1.getWeight();
                double SV2 = o2.getValue() - effectiveness * o2.getWeight();
                if (SV1==SV2)
                    return 0;
                else if (SV1>SV2)
                    return 1;
                else
                    return -1;
            }
        });
        double v=0;
        for (int i=0;i<k;i++){
            v+=array[k].getValue()-effectiveness*array[k].getWeight();
        }
        if (v==0)
            return 0;
        else if (v<0)
            return -1;
        else
            return 1;
    }


    public static class Jewel{
        public Jewel(int id,int value, int weight){
            this.value=value;
            this.weight=weight;
            this.id=id;
            this.effectiveness=value/(weight*1.0);
        }
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
        int value;
        public int getWeight() {
            return weight;
        }
        public void setWeight(int weight) {
            this.weight = weight;
        }
        int weight;
        public double getEffectiveness(){
            return this.effectiveness;
        }

        public void setEffectiveness(double effectiveness) {
            this.effectiveness = effectiveness;
        }

        double effectiveness;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        int id;

        @Override
        public String toString() {
            StringBuilder sb=new StringBuilder();
            sb.append(value+" "+ weight);
            return sb.toString();
        }
    }


}