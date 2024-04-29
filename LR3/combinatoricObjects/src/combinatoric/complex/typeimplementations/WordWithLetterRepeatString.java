package combinatoric.complex.typeimplementations;

import combinatoric.combinations.CombinationNoRepeat;
import combinatoric.complex.WordWithLetterRepeat;
import combinatoric.helpers.ArrayHelper;
import combinatoric.placements.PlacementWithRepeats;
import combinatoric.placements.PlacementWithoutRepeats;

/**
 * Реализация WordWithLetterRepeat для строкового типа данных
 * Например, вывод всех слов, где ровно 1 буква повторяется 2 раза, остальные не повторяются
 */
public class WordWithLetterRepeatString extends WordWithLetterRepeat<String> {

    @Override
    public void init() {
        int k = getK();
        String[] alphabet = getAlphabet();
        setCurrentObj(new String[k]);

        //инициализация объекта для позиций
        CombinationNoRepeat<Integer> positions = new CombinationNoRepeat<>();
        positions.setK(getCountOfRepeat());
        positions.setN(k);
        positions.setAlphabet(createAplhabetOfPositions());
        positions.setCurrentObj(new Integer[k]);

        setPositionsComb(positions);

        //инициализация объекта для расстановок букв
        PlacementWithoutRepeats<String> placements = new PlacementWithoutRepeats<>();
        placements.setK(k - getCountOfRepeat());
        placements.setAlphabet(ArrayHelper.removeElement(alphabet, getRepeatLetter()));
        placements.setN(placements.getAlphabet().length);
        placements.setCurrentObj(new String[k]);

        setPlacementComb(placements);
    }

}
