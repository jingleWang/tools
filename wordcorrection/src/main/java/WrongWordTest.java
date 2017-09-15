import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by Ben on 2017/8/17.
 */
public class WrongWordTest {

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
    static String worngWord = "newWrong.txt";

    //输出路径
    static String out = "out-2.txt";
    static String diffPath = "diff-2.txt";
    static String notExistPath = "notExist-2.txt";

    static Map<String, String> wrongWords = DataService.getWrongWords(root + worngWord);

    static List<String> v_data = DataService.getData(root + v);
    static List<String> n_data = DataService.getData(root + n);


    public static void main(String[] args) {
        List<String> words = new ArrayList<>();


                words.addAll(wrongWords.keySet());
//        words.addAll(n_data);
//        words.addAll(v_data);

        Map<String, String> newDic = new HashMap<>();

        for (String word : words) {
            CorrectionModel model = correctionService.wordCorrect(word);

            record(model, true);
            recordDiff(diffs, model, new Selector() {
                @Override
                public boolean select(Object a, Object b) {
                    CorrectionModel model = (CorrectionModel) a;
                    for (CorrectionModel.Correction correction : model.getCorrections()) {
                        if (correction.getWord().equals(b)) {
                            return false;
                        }
                    }
                    return true;
                }
            }, wrongWords.get(word), true);

            recordDiff(notExist, model, new Selector() {
                @Override
                public boolean select(Object a, Object b) {
                    CorrectionModel model = (CorrectionModel) a;
                    if (model.getCoeff() == -1) {
                        return true;
                    }
                    return false;
                }
            }, null, true);

//            makeDic(newDic, model);
        }

        DataService.writeMap(root + out, result);
        DataService.writeMap(root + diffPath, diffs);
        DataService.writeQueue(root + notExistPath, notExist);
//        DataService.writeMap(root + "newWrong.txt", newDic, " ");
    }

    public static void record(CorrectionModel model, boolean coeff) {
        if (model == null)
            return;
        String word = model.getWord();
        word += "(" + wrongWords.get(word);
        if (coeff) {
            word += ",coeff=" + model.getCoeff();
        }
        word += ")";

        StringBuilder builder = new StringBuilder();

        for (CorrectionModel.Correction correction : model.getCorrections()) {
            builder.append(correction.getWord());
            if (coeff) {
                builder.append("(" + correction.getCoeff() + ")");
            }
            builder.append(" ");
        }

        result.put(word, builder.toString());
        System.out.println(word + ": " + builder.toString());
    }

    public static void recordDiff(Object database, CorrectionModel model, Selector selector, Object cmp, boolean coeff) {
        if (model == null)
            return;

        if (selector.select(model, cmp)) {
            String word = model.getWord();
            word += "(" + wrongWords.get(word);
            if (coeff) {
                word += ",coeff=" + model.getCoeff();
            }
            word += ")";

            StringBuilder builder = new StringBuilder();

            for (CorrectionModel.Correction correction : model.getCorrections()) {
                builder.append(correction.getWord());
                if (coeff) {
                    builder.append("(" + correction.getCoeff() + ")");
                }
                builder.append(" ");
            }
            if (database instanceof Map) {
                ((Map) database).put(word, builder.toString());
            } else if (database instanceof List) {
                ((List) database).add(word);
            } else if (database instanceof Queue) {
                ((Queue) database).add(word);
            }
        }
    }



    public static void makeDic(Map map, CorrectionModel model) {
        if (model == null)
            return;
        for (CorrectionModel.Correction correction : model.getCorrections()) {
            if (!correction.getWord().equals(model.getWord())) {
                map.put(correction.getWord(), model.getWord());
                System.out.println(correction.getWord()+" "+model.getWord());
            }
        }
    }
}
