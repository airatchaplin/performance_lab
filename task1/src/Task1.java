import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        List<Integer> list;
        for (String arg : args) {
            list = readFile(arg+".txt");
            getAll(list);
        }
    }

    public static void getAll(List<Integer>list){
        getpPercentiles(list);
        getMediana(list);
        getMax(list);
        getMin(list);
        getMean(list);
        list = new ArrayList<>();
    }

    public static void getpPercentiles(List<Integer> list) {
        Collections.sort(list);
        double per = 0.9;
        BigDecimal result = BigDecimal.valueOf(per*(list.size()-1)+1);
        result = result.subtract(BigDecimal.valueOf(list.size()-1));
        result = result.setScale(2, RoundingMode.FLOOR);
        double k = list.get(list.size()-1)- (list.get(list.size()-2));
        BigDecimal e = BigDecimal.valueOf(list.get(list.size()-2));
        result = result.multiply(BigDecimal.valueOf(k));
        result = e.add(result);
        result= result.setScale(2,RoundingMode.FLOOR);
        System.out.println(result);
    }

    public static void getMax(List<Integer> list){
        BigDecimal result = new BigDecimal(Collections.max(list));
        result= result.setScale(2,RoundingMode.FLOOR);
        System.out.println(result);
    }

    public static void getMin(List<Integer> list){
        BigDecimal result = new BigDecimal(Collections.min(list));
        result= result.setScale(2,RoundingMode.FLOOR);
        System.out.println(result);
    }

    public static void getMean(List<Integer> list){
        double mean = 0;
        for (int number : list){
            mean= mean + number;
        }
        mean /= list.size();
        BigDecimal result = new BigDecimal(mean);
        result= result.setScale(2,RoundingMode.FLOOR);
        System.out.println(result);
    }

    public static void getMediana(List<Integer>list){
        Collections.sort(list);
        double sr;
        if (list.size()%2==0){
            sr = list.get(list.size()/2)+list.get(list.size()/2-1);
        }
        sr  = list.get(list.size()/2);
        BigDecimal result = new BigDecimal(sr);
        result= result.setScale(2,RoundingMode.FLOOR);
        System.out.println(result);
    }

    public static List<Integer> readFile(String fullFileName) {
        List<Integer> list = new ArrayList<>();
        File file = new File(fullFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
