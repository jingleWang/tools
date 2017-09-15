import java.io.*;
import java.util.*;

/**
 * Created by Ben on 2017/8/2.
 */
public class DataService {
    public static List<String> getData(String path) {

        List<String> list = new ArrayList<>();

        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String[] array = tempString.split(" ");
                list.add(array[0]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }

    public static Map<String, String> getWrongWords(String path) {
        Map<String, String> map = new HashMap<>();

        File file = new File(path);
        BufferedReader reader = null;
        int i = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String[] array = tempString.split(" ");
                map.put(array[0], array[1]);
                i++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return map;
    }

    public static void writeMap(String path, Map<String, String> map) {
        writeMap(path, map , ": ");
    }

    public static void writeMap(String path, Map<String, String> map, String devide) {
          /* 输出数据 */
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));
            for (String name : map.keySet()) {
                bw.write(name + devide + map.get(name));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }

    public static void writeQueue(String path, Queue<String> queue) {
           /* 输出数据 */
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));
            for (String word : queue) {
                bw.write(word);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }
}
