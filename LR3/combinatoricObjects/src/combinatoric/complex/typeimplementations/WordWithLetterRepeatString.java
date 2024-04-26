package combinatoric.complex.typeimplementations;

import combinatoric.combinations.CombinationNoRepeat;
import combinatoric.complex.WordWithLetterRepeat;
import combinatoric.helpers.ArrayHelper;
import combinatoric.placements.PlacementWithRepeats;

/**
 * Реализация WordWithLetterRepeat для строкового типа данных
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
        PlacementWithRepeats<String> placements = new PlacementWithRepeats<>();
        placements.setK(k - getCountOfRepeat());
        placements.setAlphabet(ArrayHelper.removeElement(alphabet, getRepeatLetter()));
        placements.setN(placements.getAlphabet().length);
        placements.setCurrentObj(new String[k]);

        setPlacementComb(placements);
    }

}
