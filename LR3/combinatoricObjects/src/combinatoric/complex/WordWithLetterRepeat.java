package combinatoric.complex;

import combinatoric.basic.BaseCombObject;
import combinatoric.basic.NonRecursiveSearch;
import combinatoric.combinations.CombinationNoRepeat;
import combinatoric.helpers.ArrayHelper;
import combinatoric.placements.IPlacement;
import combinatoric.placements.PlacementWithRepeats;

abstract public class WordWithLetterRepeat<TypeOfAlphabet> extends BaseCombObject<TypeOfAlphabet> implements NonRecursiveSearch {

    private TypeOfAlphabet repeatLetter;
    private int countOfRepeat;

    private CombinationNoRepeat<Integer> positionsComb;

    private IPlacement<TypeOfAlphabet> placementComb;

    public void setRepeatLetter(TypeOfAlphabet repeatLetter) {
        this.repeatLetter = repeatLetter;
    }

    public TypeOfAlphabet getRepeatLetter() {
        return repeatLetter;
    }

    public int getCountOfRepeat() {
        return countOfRepeat;
    }

    public void setCountOfRepeat(int countOfRepeat) {
        this.countOfRepeat = countOfRepeat;
    }

    public void setPositionsComb(CombinationNoRepeat<Integer> positionsComb) {
        this.positionsComb = positionsComb;
    }

    public void setPlacementComb(IPlacement<TypeOfAlphabet> placementComb) {
        this.placementComb = placementComb;
    }

    public IPlacement<TypeOfAlphabet> getPlacementComb() {
        return placementComb;
    }

    public CombinationNoRepeat<Integer> getPositionsComb() {
        return positionsComb;
    }

    @Override
    public boolean genNextObj() {

        if (placementComb.genNextObj()) {
            //если удалось сформировать след перестановку, то расставляем символы
            fillByPositionsAndPlacements();
            return true;
        }

        if (positionsComb.genNextObj()) {
            //если не удалось сформировать перестановку, но удалось сформировать след сочетание позиций
            placementComb.initialFill(); // - заново заполняем размещения остальных букв
            fillByPositionsAndPlacements();
            return true;
        }

        return false;
    }

    /**
     * Заполнить текущий объект на основе позиций и размещений
     */
    protected void fillByPositionsAndPlacements() {
        TypeOfAlphabet[] lettersForPlacement = placementComb.getCurrentObj();
        Integer[] busyPositions = positionsComb.getCurrentObj();
        TypeOfAlphabet[] currentObj = getCurrentObj();

        int inputLetterIndex = 0;
        for (Integer i = 0; i < getK(); i++) {
            if (ArrayHelper.inArray(busyPositions, i)) {
                currentObj[i] = repeatLetter;
            } else {
                currentObj[i] = lettersForPlacement[inputLetterIndex];
                inputLetterIndex++;
            }
        }
    }

    @Override
    public void nonRecursivePrintAllObjects() {
        for (int i = 0; i < getN(); i++) {
            setRepeatLetter(getAlphabet()[i]);
            //создание вспомогательных объектов происходит в init
            init();

            //заполнение начальных данных и вывод
            positionsComb.initialFill();
            placementComb.initialFill();
            fillByPositionsAndPlacements();
            printObject();

            printWhileExistNextComb();
        }
    }

    /**
     * Инициализировать комбинаторные объекты для начала работы
     */
    abstract public void init();

    /**
     * Создать алфавит позиций (массив с int по возрастанию)
     */
    public Integer[] createAplhabetOfPositions() {
        Integer[] alphabetOfPositions = new Integer[getK()];

        for (int i = 0; i < getK(); i++) {
            alphabetOfPositions[i] = i;
        }

        return alphabetOfPositions;
    }
}
