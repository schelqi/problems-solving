package arrays;

import java.io.*;
import java.util.*;

import java.util.stream.*;

import static java.util.stream.Collectors.toList;


public class NewYearChaos {

    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {

        int bribes = 0;
        List<Integer> iq = new ArrayList<>(q);
        for (int i = 0; i < q.size(); i++) {
            int cp = i;
            int ip = q.get(i) - 1;
            int diff = Math.abs(cp - ip);

            if (cp < ip && diff > 2) {
                System.out.println("Too chaotic");
                return;
            }

            for (int j = i - 1; j >= q.get(i) - 2 && j >= 0; j--) {
                if (q.get(j) > q.get(i))
                    bribes++;
            }

        }

        System.out.println(bribes);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
