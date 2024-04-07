package combinatoric.combinations;

import combinatoric.basic.BaseCombObject;
import combinatoric.basic.NonRecursiveSearch;
import combinatoric.basic.RecursiveSearch;

import java.util.Arrays;
import java.util.Objects;

/**
 * Комбинаторный объект - сочетание без повторений
 */
public class CombinationNoRepeat<TypeOfAlphabet> extends BaseCombObject<TypeOfAlphabet> implements NonRecursiveSearch, RecursiveSearch {

    @Override
    public boolean genNextObj() {
        int j = getK() - 1;
        int l = 1;

        TypeOfAlphabet[] currentObj = getCurrentObj();

        while ((j >= 0) && (Objects.equals(currentObj[j], getAlphabet()[getN() - l]))) {
            j--;
            l++;
        }

        if (j < 0) {
            return false;
        }

        currentObj[j] = getNextSymbol(currentObj[j]);
        for (int i = j + 1; i < getK(); i++) {
            currentObj[i] = getNextSymbol(currentObj[i - 1]);
        }
        return true;
    }


    /**
     * Нерекурсивный метод для вывода всех сочетаний без повторений
     */
    @Override
    public void nonRecursivePrintAllObjects() {
        TypeOfAlphabet[] alphabet = getAlphabet();
        TypeOfAlphabet[] currentObj = getCurrentObj();

        // первые K символов просто переносим в текущее сочетание
        if (getK() >= 0) System.arraycopy(alphabet, 0, currentObj, 0, getK());

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
        int n = getN();
        int k = getK();

        // достигли дна рекурсии, выводим сочетание
        if (curPos == getK()) {
            printObject();
            return;
        }

        TypeOfAlphabet[] currentComb = getCurrentObj();
        TypeOfAlphabet[] alphabet = getAlphabet();

        TypeOfAlphabet startChar = (curPos == 0) ? alphabet[0] : getNextSymbol(currentComb[curPos - 1]);

        int startNumber = Arrays.binarySearch(alphabet, startChar);
        int finishNumber = n - (k - curPos);

        // по очереди ставим возможные символы и вызываем рекурсию
        for (int i = startNumber; i <= finishNumber; i++) {
            currentComb[curPos] = alphabet[i];
            recursivePrintAllObjects(curPos + 1);
        }

    }
}
