import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task2 {

    static Set<Float> set = new LinkedHashSet<>();
    static Map<Integer, List<Float>> map = new LinkedHashMap<>();

    public static void main(String[] args) {
        readFiles(args[0]+".txt", args[1]+".txt");
        getAnswer();
    }

    public static void getAnswer() {
        List<Float> list = new ArrayList<>(set);
        float a = list.get(0);
        float b = list.get(1);
        for (Map.Entry<Integer, List<Float>> pair : map.entrySet()) {
            List<Float> points = pair.getValue();
            if ((points.get(0) == a || points.get(0) == b) && (points.get(1) > a && points.get(1) < b)) {
                System.out.println(1 + " - сторона");
            } else if ((points.get(0) == a || points.get(0) == b) && (points.get(1) == a || points.get(1) == b)) {
                System.out.println(0 + " - вершина");
            } else if ((points.get(0) <= a || points.get(0) >= b) && (points.get(1) <= a || points.get(1) >= b)) {
                System.out.println(3 + " - снаружи");
            } else if ((points.get(0) > a && points.get(0) < b) && (points.get(1) > a && points.get(1) < b)) {
                System.out.println(2 + " - внутри");
            }
        }
        set = new LinkedHashSet<>();
        map = new LinkedHashMap<>();
    }

    public static void readFiles(String fullFileName1, String fullFileName2) {
        List<Float> floatList = new ArrayList<>();
        File file1 = new File(fullFileName1);
        File file2 = new File(fullFileName2);
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            String line1;
            String line2;
            while ((line1 = reader1.readLine()) != null) {
                String[] res = line1.split(" ");
                for (String re : res) {
                    set.add(Float.valueOf(re));
                }
            }
            int count = 1;
            while ((line2 = reader2.readLine()) != null) {
                String[] res = line2.split(" ");
                for (String re : res) {
                    floatList.add(Float.valueOf(re));
                }
                map.put(count, floatList);
                count++;
                floatList = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
