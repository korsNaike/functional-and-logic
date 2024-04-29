package combinatoric.complex.typeimplementations;

import combinatoric.combinations.CombinationNoRepeat;
import combinatoric.complex.WordWithTwoLetterRepeat;
import combinatoric.helpers.ArrayHelper;
import combinatoric.placements.PlacementWithRepeats;
import combinatoric.placements.PlacementWithoutRepeats;

/**
 * Реализация класса WordWithTwoLetterRepeat для строкового типа данных
 * Например, вывод всех слов, где ровно 2 буквы повторяются 2 раза, остальные не повторяются
 */
public class Word2LetterRepeatString extends WordWithTwoLetterRepeat<String> {

    @Override
    public void init() {
        int k = getK();
        String[] alphabet = getAlphabet();
        setCurrentObj(new String[k]);

        //инициализация объекта для позиций первой буквы
        CombinationNoRepeat<Integer> positions = new CombinationNoRepeat<>();
        positions.setK(getCountOfRepeat());
        positions.setN(k);
        positions.setAlphabet(createAplhabetOfPositions());
        positions.setCurrentObj(new Integer[positions.getK()]);
        positions.initialFill();
        setPositionsComb(positions);

        // инициализация объекта для позиций второй буквы
        CombinationNoRepeat<Integer> secondPositions = new CombinationNoRepeat<>();
        secondPositions.setK(getCountOfSecondRepeat());
        secondPositions.setN(k - getCountOfRepeat());
        secondPositions.setAlphabet(ArrayHelper.getDifference(createAplhabetOfPositions(), positions.getCurrentObj()));
        secondPositions.setCurrentObj(new Integer[secondPositions.getK()]);
        secondPositions.initialFill();
        setSecondPositionsComb(secondPositions);

        //инициализация объекта для расстановок букв
        String[] newAlphabet = ArrayHelper.removeElement(alphabet, getRepeatLetter()); //удаляем первую букву
        String[] newNewAlphabet = ArrayHelper.removeElement(newAlphabet, getSecondRepeatLetter()); //удаляем вторую букву

        PlacementWithoutRepeats<String> placements = new PlacementWithoutRepeats<>();
        placements.setK(k - getCountOfRepeat() - getCountOfSecondRepeat());
        placements.setAlphabet(newNewAlphabet);
        placements.setN(placements.getAlphabet().length);
        placements.setCurrentObj(new String[k]);

        setPlacementComb(placements);
    }
}
