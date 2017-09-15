import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Ben on 2017/8/2.
 */
public class Main {

    //结果
    final static ConcurrentHashMap<String, String> result = new ConcurrentHashMap<>();
    //返回不存在原词
    final static ConcurrentHashMap<String, String> diffs = new ConcurrentHashMap<>();
    //系数为-1的词
    final static ConcurrentLinkedDeque notExist = new ConcurrentLinkedDeque();

    final static WordsCorrectionService correctionService = new WordsCorrectionService();

    static String root = "C:\\Users\\Ben\\Desktop\\纠错\\";

    //输入路径
    static String v = "idf_item_v.txt";
    static String n = "idf_item_n.txt";
    static String worngWord = "wrongword.txt";

    //输出路径
    static String out = "out-2.txt";
    static String diffPath = "diff-2.txt";
    static String notExistPath = "notExist-2.txt";

    public static void main(String[] args) {
        testWrongWords();
    }

    public static void testWrongWords() {

        final Map<String,String> wrongWords = DataService.getWrongWords(root + worngWord);

        final List<String> data = new ArrayList<>();

        for (Map.Entry<String,String> entry : wrongWords.entrySet()) {
            data.add(entry.getKey());
        }

        List<Future<Boolean>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        //线程池执行
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (final String word : data) {
            Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    StringBuilder stringBuilder = new StringBuilder();
                    long nanoTime = -1;
                    try {
                        long startTime = System.currentTimeMillis();
                        CorrectionModel model = correctionService.wordCorrect(word);

                        recordDiff(model, wrongWords.get(word), true);
                        record(model,true);
                        recordNotExistWord(model, true);

                        nanoTime = System.currentTimeMillis() - startTime;
                        System.out.println(" cost:" + nanoTime + "ms");
                    } catch (Exception e) {
                        stringBuilder.append("");
                        //e.printStackTrace();
                    }
                    return true;
                }
            });
            futures.add(future);
        }

        //等待所有线程完成
        for (Future<Boolean> future : futures) {
            while (!future.isDone())
                ;//Future返回如果没有完成，则一直循环等待，直到Future返回完成
        }

        long endTime = System.currentTimeMillis();

        DataService.writeMap(root + out, result);
        DataService.writeMap(root + diffPath, diffs);
        DataService.writeQueue(root + notExistPath, notExist);


        System.out.println("end" + "cost:" + (endTime - startTime) / 1000 + "sec");
    }

    public static void testRightWords() {
        List<String> v_data = DataService.getData(root + v);
        List<String> n_data = DataService.getData(root + n);


        final List<String> data = new ArrayList<>();
        data.addAll(v_data);
        data.addAll(n_data);



        List<Future<Boolean>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        //线程池执行
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (final String word : data) {
            Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    StringBuilder stringBuilder = new StringBuilder();
                    long nanoTime = -1;
                    try {
                        long startTime = System.currentTimeMillis();


                        CorrectionModel model = correctionService.wordCorrect(word);

                        //操作
                        record(model, true);
                        recordNotExistWord(model, false);

                        nanoTime = System.currentTimeMillis() - startTime;
                        System.out.println(" cost:" + nanoTime + "ms");
                    } catch (Exception e) {
                        stringBuilder.append("");
                        //                        e.printStackTrace();
                    }
                    return true;
                }
            });
            futures.add(future);
        }

        //等待所有线程完成
        for (Future<Boolean> future : futures) {
            while (!future.isDone())
                ;//Future返回如果没有完成，则一直循环等待，直到Future返回完成
        }

        long endTime = System.currentTimeMillis();

        DataService.writeMap(root + out, result);
        DataService.writeMap(root + diffPath, diffs);
        DataService.writeQueue(root + notExistPath, notExist);


        System.out.println("end" + "cost:" + (endTime - startTime) / 1000 + "sec");
    }


    public static void recordNotExistWord(CorrectionModel model, boolean coeff) {
        if (model.getCoeff() == -1) {
            StringBuilder builder = new StringBuilder();
            builder.append(model.getWord());
            if (coeff) {
                builder.append("(" + model.getCoeff() + ")");
            }
            notExist.add(builder.toString());
        }
    }

    public static void recordDiff(CorrectionModel model, boolean coeff) {
        String cmp = model.getWord();
        recordDiff(model, cmp, coeff);
    }

    public static void recordDiff(CorrectionModel model, String cmp, boolean coeff) {
        List<CorrectionModel.Correction> list = model.getCorrections();
        boolean diff = true;
        for (CorrectionModel.Correction correction : list) {
            if (cmp.equals(correction.getWord())) {
                diff = false;
            }
        }
        if (diff) {
            StringBuilder stringBuilder1 = new StringBuilder();

            for (CorrectionModel.Correction correction1 : list) {
                stringBuilder1.append(correction1.getWord());
                if (coeff) {
                    stringBuilder1.append("(" + correction1.getCoeff() + ")");
                }
                stringBuilder1.append(" ");
            }

            diffs.put(model.getWord() + "(coeff=" + model.getCoeff() + ",cmp=" + cmp + ")", stringBuilder1.toString());
        }

    }

    public static void record(CorrectionModel model, boolean coeff) {
        List<CorrectionModel.Correction> list = model.getCorrections();
        boolean diff = true;
        StringBuilder builder = new StringBuilder();
        String word = model.getWord();

        for (CorrectionModel.Correction correction : list) {

            builder.append(correction.getWord());

            if (coeff) {
                builder.append("(" + correction.getCoeff() + ")");
            }
            builder.append(" ");
        }

        if (coeff) {
            word += "(" + model.getCoeff() + ")";
        }

        result.put(word, builder.toString());

        System.out.print(word + " " + builder.toString());
    }


}
