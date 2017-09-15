import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * Created by Wang Jinglu on 2017/9/7.
 */
public class test {
    public class Model {
        private Object data;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Model{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }

    public void operate(Model model) {
//        model = new Model();
        model.setData("xixi");
    }

    public static void main(String[] args) {
        Model model = new test().new Model();
        model.setData(new test().new Model());
        new test().operate((Model) model.getData());
        System.out.println(model);
    }
}
