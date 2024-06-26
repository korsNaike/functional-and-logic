package combinatoric.placements;

import combinatoric.basic.*;

import java.util.Objects;

/**
 * Комбинаторный объект - размещение без повторений
 */
public class PlacementWithRepeats<TypeOfAlphabet> extends CombObject<TypeOfAlphabet> implements IPlacement<TypeOfAlphabet> {


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

    public void initialFill() {
        for (int i = 0; i < getK(); i++) getCurrentObj()[i] = getAlphabet()[0];
    }


    /**
     * Нерекурсивный метод для вывода всех размещений с повторениями
     */
    @Override
    public void nonRecursivePrintAllObjects() {
        // первое размещение просто заполняем первым символом алфавита и выводим
        initialFill();
        printObject();

        // Выводим все размещения, пока они существуют
        printWhileExistNextComb();
    }

    /**
     * Рекурсивный метод для вывода всех размещений с повторениями
     */
    @Override
    public void recursivePrintAllObjects() {
        recursivePrintAllObjects(0);
    }

    private void recursivePrintAllObjects(int currentKIndex) {
        if (currentKIndex == getK()) {
            printObject();
        } else {
            for (int i = 0; i < getN(); i++) {
                replaceElementInCurrentObj(getAlphabet()[i], currentKIndex);
                recursivePrintAllObjects(currentKIndex + 1);
            }
        }
    }
}
