import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task3 {
    public static void main(String[] args) {
        List<List<Float>> fullCashier = readFiles(args[0]+".txt",args[1]+".txt",args[2]+".txt",args[3]+".txt",args[4]+".txt");
        int count = 0;
        float max = 0;
        List<Integer> index = new ArrayList<>();
        for (List<Float> list : fullCashier){
            for (int i = 0; i < list.size(); i++) {
                if (max<list.get(i)){
                    max = list.get(i);
                    count = i+1;
                }
            }
            index.add(count);
        }
        for (int i :index){
            System.out.println(i);
        }
    }

    public static List<List<Float>> readFiles(String fullFileName1, String fullFileName2, String fullFileName3, String fullFileName4, String fullFileName5) {
        List<Float> listCashier1 = new ArrayList<>();
        List<Float> listCashier2 = new ArrayList<>();
        List<Float> listCashier3 = new ArrayList<>();
        List<Float> listCashier4 = new ArrayList<>();
        List<Float> listCashier5 = new ArrayList<>();
        List<String> fileList = Stream.of(fullFileName1, fullFileName2, fullFileName3, fullFileName4, fullFileName5).collect(Collectors.toList());
        List<List<Float>> fullCashier = Stream.of(listCashier1, listCashier2, listCashier3, listCashier4, listCashier5).collect(Collectors.toList());
        File file;

        try {
            for (int i = 0; i < fileList.size(); i++) {
                file = new File(fileList.get(i));
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    switch (i) {
                        case 0:
                            listCashier1.add(Float.valueOf(line));
                            break;
                        case 1:
                            listCashier2.add(Float.valueOf(line));
                            break;
                        case 2:
                            listCashier3.add(Float.valueOf(line));
                            break;
                        case 3:
                            listCashier4.add(Float.valueOf(line));
                            break;
                        case 4:
                            listCashier5.add(Float.valueOf(line));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullCashier;
    }
}
