import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task4 {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> listMap = readFiles(args[0] + ".txt");
        Map<Integer, List<Integer>> result = getMapAnswer(listMap);
        List<List<Integer>> answer = getListAnswer(result);
        for (List<Integer> list : answer) {
            String s1 = String.valueOf(list.get(0));
            String s2 = String.valueOf(list.get(1));
            char[] chars1 = s1.toCharArray();
            char[] chars2 = s2.toCharArray();
            s1 = getTime(chars1);
            s2 = getTime(chars2);
            System.out.println(s1 + " " + s2);
        }
    }

    public static String getTime(char[] chars) {
        String res = "";
        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                res = chars[0] + ":";
            } else {
                res += chars[i];
            }
        }
        return res;
    }

    public static List<List<Integer>> getListAnswer(Map<Integer, List<Integer>> listMap) {
        int start1 = 0;
        int end1 = 0;
        int start2 = 0;
        int end2 = 0;
        int count = 0;
        int numFirst = 0;
        int numChange = 0;
        List<List<Integer>> fullList = new ArrayList<>();
        List<Integer> listStart = new ArrayList<>();
        List<Integer> listEnd = new ArrayList<>();
        for (List<Integer> list : listMap.values()) {
            numFirst = list.get(0) / 100;

            if (count == 0) {
                start1 = list.get(0);
                end1 = list.get(list.size() - 1);
                count++;
                numChange = numFirst;
                continue;
            }
            if (numFirst == numChange) {
                start2 = list.get(0);
                end2 = list.get(list.size() - 1);
                if (start1 < start2 && start2 < end1) {
                    start1 = start2;
                    listStart.add(start1);
                    if (end2 > end1) {
                        end1 = end2;
                        listEnd.add(end1);
                    }
                }
            } else {
                start1 = list.get(0);
                end1 = list.get(list.size() - 1);
                numChange = numFirst;
                fullList.add(Stream.of(listStart.get(0), listEnd.get(listEnd.size() - 1)).collect(Collectors.toList()));
                listStart = new ArrayList<>();
                listEnd = new ArrayList<>();
            }

        }
        if (listEnd.isEmpty()) {
            listEnd.add(end2);
        }
        fullList.add(Stream.of(listStart.get(0), listEnd.get(listEnd.size() - 1)).collect(Collectors.toList()));
        return fullList;
    }

    public static Map<Integer, List<Integer>> getMapAnswer(Map<Integer, List<Integer>> listMap) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> resultList = new LinkedHashMap<>();
        int count = 1;
        for (List<Integer> list : listMap.values()) {

            for (int i = list.get(0); i <= list.get(list.size() - 1); i++) {
                result.add(i);
            }
            resultList.put(count, result);
            count++;
            result = new ArrayList<>();
        }
        return resultList;
    }

    public static Map<Integer, List<Integer>> readFiles(String fullFileName) {
        Map<Integer, List<Integer>> listMap = new LinkedHashMap<>();
        File file1 = new File(fullFileName);
        List<Integer> list = new ArrayList<>();
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            String line;
            int count = 1;
            while ((line = reader1.readLine()) != null) {
                String[] strings = line.split(" ");
                for (int i = 0; i < strings.length; i++) {
                    String[] arr = strings[i].split(":");
                    String result = arr[0] + arr[1];
                    list.add(Integer.valueOf(result));
                }
                listMap.put(count, list);
                count++;
                list = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listMap;
    }
}
