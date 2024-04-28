package combinatoric.complex;

import combinatoric.basic.NonRecursiveSearch;
import combinatoric.combinations.CombinationNoRepeat;
import combinatoric.helpers.ArrayHelper;
import combinatoric.placements.IPlacement;
import combinatoric.placements.PlacementWithRepeats;

import java.util.Objects;

abstract public class WordWithTwoLetterRepeat<TypeOfAlphabet> extends WordWithLetterRepeat<TypeOfAlphabet> implements NonRecursiveSearch {


    private TypeOfAlphabet secondRepeatLetter;
    private int countOfSecondRepeat;
    private CombinationNoRepeat<Integer> secondPositionsComb;

    public void setSecondRepeatLetter(TypeOfAlphabet secondRepeatLetter) {
        this.secondRepeatLetter = secondRepeatLetter;
    }

    public TypeOfAlphabet getSecondRepeatLetter() {
        return secondRepeatLetter;
    }

    public void setCountOfSecondRepeat(int countOfSecondRepeat) {
        this.countOfSecondRepeat = countOfSecondRepeat;
    }

    public int getCountOfSecondRepeat() {
        return countOfSecondRepeat;
    }

    public void setSecondPositionsComb(CombinationNoRepeat<Integer> secondPositionsComb) {
        this.secondPositionsComb = secondPositionsComb;
    }

    public CombinationNoRepeat<Integer> getSecondPositionsComb() {
        return secondPositionsComb;
    }

    @Override
    public boolean genNextObj() {
        IPlacement<TypeOfAlphabet> placementComb = getPlacementComb();
        CombinationNoRepeat<Integer> positionsComb = getPositionsComb();

        if (placementComb.genNextObj()) {
            //если удалось сформировать след перестановку, то расставляем символы
            fillByPositionsAndPlacements();
            return true;
        }

        if (secondPositionsComb.genNextObj()) {
            //если не удалось сформировать перестановку, но удалось сформировать след сочетание позиций
            placementComb.initialFill(); // - заново заполняем размещения остальных букв
            fillByPositionsAndPlacements();
            return true;
        }

        if (positionsComb.genNextObj()) {
            //если не удалось сформировать ни перестановку, не другое сочетание, но удалось сформировать начальное сочетание
            //получаем новый алфавит для получения позиций во втором сочетании
            Integer[] newSecondPositions = ArrayHelper.getDifference(createAplhabetOfPositions(), positionsComb.getCurrentObj());
            secondPositionsComb.setAlphabet(newSecondPositions); // меняем алфавит у второго сочетания
            secondPositionsComb.initialFill(); //заново начально заполняем
            placementComb.initialFill(); //заново заполняем размещения остальных букв
            fillByPositionsAndPlacements();
            return true;
        }

        return false;
    }

    /**
     * Заполнить текущий объект на основе позиций и размещений
     */
    protected void fillByPositionsAndPlacements() {
        TypeOfAlphabet[] lettersForPlacement = getPlacementComb().getCurrentObj();
        Integer[] firstPositions = getPositionsComb().getCurrentObj();
        Integer[] secondPositions = secondPositionsComb.getCurrentObj();
        TypeOfAlphabet[] currentObj = getCurrentObj();

        int inputLetterIndex = 0;
        for (Integer i = 0; i < getK(); i++) {
            if (ArrayHelper.inArray(firstPositions, i)) {
                currentObj[i] = getRepeatLetter();
            } else if (ArrayHelper.inArray(secondPositions, i)) {
                currentObj[i] = secondRepeatLetter;
            } else {
                currentObj[i] = lettersForPlacement[inputLetterIndex];
                inputLetterIndex++;
            }
        }
    }

    @Override
    public void nonRecursivePrintAllObjects() {
        for (int i = 0; i < getN(); i++) {
            TypeOfAlphabet firstRepeatLetter = getAlphabet()[i];
            for (int j = 1; j < getN(); j++) {
                TypeOfAlphabet secondRepeatLetter = getAlphabet()[j];
                if (Objects.equals(secondRepeatLetter, firstRepeatLetter)) {
                    continue;
                }
                setRepeatLetter(firstRepeatLetter);
                setSecondRepeatLetter(secondRepeatLetter);
                init();
                //заполнение начальных данных и вывод
                getPositionsComb().initialFill();
                secondPositionsComb.initialFill();
                getPlacementComb().initialFill();
                fillByPositionsAndPlacements();
                printObject();

                printWhileExistNextComb();
            }
        }
    }
}
