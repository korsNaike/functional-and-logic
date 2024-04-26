package combinatoric.helpers;

public class ArrayHelper {

    public static boolean inArray(String[] array, String element) {
        BaseArrayHelper<String> helper = new BaseArrayHelper<String>(array);
        return helper.inArray(element);
    }

    public static boolean inArray(Integer[] array, Integer element) {
        BaseArrayHelper<Integer> helper = new BaseArrayHelper<Integer>(array);
        return helper.inArray(element);
    }

    public static String[] removeElement(String[] array, String value) {
        BaseArrayHelper<String> helper = new BaseArrayHelper<String>(array);
        return helper.removeElement(value);
    }

    public static Integer[] removeElement(Integer[] array, Integer value) {
        BaseArrayHelper<Integer> helper = new BaseArrayHelper<Integer>(array);
        return helper.removeElement(value);
    }

}
