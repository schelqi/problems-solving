package dictionaries;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class SherlockAnagrams {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }


    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String s) {
        Map<Integer, List<String>> sub1 = new HashMap<>();
        int found = 0;

        for (int i = 0; i <= s.length() - 1; i++) {
            int end = i == 0 ? s.length() - 1 : s.length();
            for (int j = i + 1; j <= end; j++) {
                String str = s.substring(i, j);
                List<String> sub2;
                if (!sub1.containsKey(str.length())) {
                    sub2 = new ArrayList<>();
                } else {
                    sub2 = sub1.get(str.length());
                }

                sub2.add(str);
                sub1.put(str.length(), sub2);
            }
        }

        System.out.println(sub1);

        for (int i : sub1.keySet()) {
            List<String> sub2 = sub1.get(i);

            for (int j = 0; j < sub2.size() - 1; j++) {
                for (int k = j + 1; k < sub2.size(); k++) {
                    if (isAnagrams(sub2.get(j), sub2.get(k))) {
                        found++;
                    }
                }
            }
        }


        return found;

    }

    public static boolean isAnagrams(String s1, String s2) {

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return String.valueOf(arr1).equals(String.valueOf(arr2));


    }
}
