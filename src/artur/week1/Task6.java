package artur.week1;

import java.io.*;
import java.util.*;

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


    static boolean isLetter(String s, String[] keyboard){
        for(int i=0;i<keyboard.length;i++){
            if (s!=null && keyboard[i].contains(s)){
                return true;
            }
        }
        return false;
    }

    static int[] getCoordinates(String a, String[] keyboard){   //  w,h
        int[] result=new int[2];
        for(int i=0;i<keyboard.length;i++){
            if (keyboard[i].contains(a)){
                result[0]=keyboard[i].indexOf(a);
                result[1]=i;
            }
        }
        return result;
    }

    static int getDistance(String a, String b, String[] keyboard){
        int[] coordinatesA = getCoordinates(a, keyboard);
        int[] coordinatesB = getCoordinates(b, keyboard);
        return Math.max(Math.abs(coordinatesA[0]-coordinatesB[0]), Math.abs(coordinatesA[1]-coordinatesB[1]));
    }

    static int calcDistanceForTemplate(String template,  String[] keyboard){
        int result=0;

        String letterA=null;
        String letterB=null;

        for (int i=0; i< template.length();i++){
            if (letterA!=null){
                letterB= String.valueOf(template.charAt(i));
                if (isLetter(letterB,keyboard)){
                    result+=getDistance(letterA,letterB,keyboard);
                    letterA=letterB;
                    letterB=null;
                } else {
                    continue;
                }
            } else{
                letterA= String.valueOf(template.charAt(i));
                if (isLetter(letterA,keyboard)){
                    continue;
                }  else{
                    letterA=null;

                }
            }
        }


        return result;


    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int w=in.nextInt();
            int h=in.nextInt();
            String[] keyboard=new String[h];
            for (int i=0; i< h; i++){
                keyboard[i]=in.next();
            }
            ArrayList<String> languages=new ArrayList<String>();
            ArrayList<String> templates=new ArrayList<String>();
            ArrayList<Integer> results=new ArrayList<Integer>();
            //LinkedHashMap<String, String> languages=new LinkedHashMap<String, String>();
            //LinkedHashMap<String, Integer> results=new LinkedHashMap<>();
            for (int i=0;i<3;i++){
                String languageName=in.next();
                String templateStart=in.next();
                String tmp=in.next();
                String buffer="";
                while(!tmp.equals("%TEMPLATE-END%")){
                    buffer+=(tmp);
                    tmp=in.next();
                }
                languages.add(languageName);
                templates.add(buffer);
                //languages.put(languageName, buffer);

                results.add(calcDistanceForTemplate(buffer,keyboard));
            }


            int min=Integer.MAX_VALUE;

            for (Integer result : results) {
                if (result < min)
                    min=result;
            }
            for (Integer result : results) {
                if (result==min){
                    out.println(languages.get(results.indexOf(result)));
                    out.println(result);
                    return;
                }
            }
        }
    }
}