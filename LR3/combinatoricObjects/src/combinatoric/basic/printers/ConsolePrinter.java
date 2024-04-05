package combinatoric.basic.printers;

import combinatoric.basic.CombObject;
import java.util.Arrays;

public final class ConsolePrinter implements CombObjPrinter {

    public void printCombObj(CombObject combObj) {
        System.out.println(Arrays.toString(combObj.getCurrentObj()));
    }

}
