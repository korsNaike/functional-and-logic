package combinatoric.combinations;

import combinatoric.basic.BaseCombObject;
import combinatoric.basic.InitialFilling;
import combinatoric.basic.NonRecursiveSearch;
import combinatoric.basic.RecursiveSearch;

import java.util.Objects;

public class CombinationWithRepeats<TypeOfAlphabet> extends BaseCombObject<TypeOfAlphabet> implements NonRecursiveSearch, RecursiveSearch, InitialFilling {
    @Override
    public boolean genNextObj() {
        TypeOfAlphabet[] currentObj = getCurrentObj();
        TypeOfAlphabet[] alphabet = getAlphabet();

        if (Objects.equals(alphabet[getN() - 1], currentObj[0])) {
            return false;
        }

        TypeOfAlphabet minLetter = currentObj[0];
        int lastIndexMin = 0;
        for (int i = 0; i < getK(); i++) {
            if (Objects.equals(minLetter, currentObj[i])) {
                lastIndexMin = i;
            }
        }
        currentObj[lastIndexMin] = getNextSymbol(currentObj[lastIndexMin]);

        return true;
    }


    /**
     * Нерекурсивный метод для вывода всех сочетаний без повторений
     */
    @Override
    public void nonRecursivePrintAllObjects() {
        // первые K символов просто переносим в текущее сочетание и выводим
        initialFill();
        printObject();

        printWhileExistNextComb();
    }

    /**
     * Рекурсивный метод для вывода всех сочетаний без повторений
     */
    @Override
    public void recursivePrintAllObjects() {
        recursivePrintAllObjects(0);
    }

    private void recursivePrintAllObjects(int curPos) {
        // достигли дна рекурсии, выводим сочетание
        if (curPos == getK()) {
            printObject();
            return;
        }

        TypeOfAlphabet[] currentComb = getCurrentObj();
        TypeOfAlphabet[] alphabet = getAlphabet();

        // по очереди ставим возможные символы и вызываем рекурсию
        for (int i = 0; i < getN(); i++) {
            currentComb[curPos] = alphabet[i];
            recursivePrintAllObjects(curPos + 1);
        }
    }

    public void initialFill() {
        TypeOfAlphabet[] alphabet = getAlphabet();
        TypeOfAlphabet[] currentObj = getCurrentObj();

        for (int i = 0; i < getK(); i++) {
            currentObj[i] = alphabet[0];
        }
    }
}
