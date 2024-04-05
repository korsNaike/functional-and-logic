package combinatoric.placements;

import combinatoric.basic.BaseCombObject;
import combinatoric.basic.NonRecursiveSearch;
import combinatoric.basic.RecursiveSearch;

import java.util.Objects;

public class PlacementWithRepeats extends BaseCombObject implements NonRecursiveSearch, RecursiveSearch {


    @Override
    public boolean genNextObj() {
        int j = getK() - 1;
        //находим НЕ последнюю букву алфавита
        while ((j >= 0) && (Objects.equals(getCurrentObj()[j], getAlphabet()[getN() - 1]))) {
            j--;
        }

        //если таких символов не осталось
        if (j < 0) {
            return false;
        }

        // получаем следующий символ из алфавита
        getCurrentObj()[j] = getNextSymbol(getCurrentObj()[j]);

        // оставшиеся символы забиваем первым значением алфавита
        for (int i = j + 1; i < getK(); i++) getCurrentObj()[i] = getAlphabet()[0];

        return true;
    }


    /**
     * Нерекурсивный метод для вывода всех размещений с повторениями
     */
    @Override
    public void nonRecursivePrintAllObjects() {
        // первое размещение просто заполняем первым символом алфавита и выводим
        setCurrentObj(new String[getK()]);
        for (int i = 0; i < getK(); i++) getCurrentObj()[i] = getAlphabet()[0];

        printObject();

        // генерируем следующее размещение и выводим
        while (genNextObj()) printObject();
    }

    /**
     * Рекурсивный метод для вывода всех размещений с повторениями
     */
    @Override
    public void recursivePrintAllObjects() {
        setCurrentObj(new String[getK()]);
        recursivePrintAllObjects(0);
    }

    private void recursivePrintAllObjects(int currentKIndex) {
        if (currentKIndex == getK()) {
            printObject();
        } else {
            for (int i = 0; i < getN(); i++) {
                replaceElementInCurrentObj(getAlphabet()[i], currentKIndex);
                recursivePrintAllObjects(currentKIndex + 1);
                replaceElementInCurrentObj("", currentKIndex);
            }
        }
    }
}
