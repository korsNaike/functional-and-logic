import combinatoric.basic.printers.ConsolePrinter;
import combinatoric.combinations.CombinationNoRepeat;
import combinatoric.complex.typeimplementations.WordWithLetterRepeatString;
import combinatoric.placements.PlacementWithRepeats;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        //testPlacementWithRepeats(false);
        // testCombinationNoRepeat(true);
        testWordWithLetterRepeat();
    }

    public static void testPlacementWithRepeats(boolean recursive)
    {
        PlacementWithRepeats<Integer> pl = new PlacementWithRepeats<Integer>();
        int k = 5;
        pl.setK(k);
        pl.setN(5);
        pl.setAlphabet(new Integer[]{1, 2, 3, 4, 5});
        pl.setCurrentObj(new Integer[k]);
        pl.setPrinter(new ConsolePrinter());

        if (recursive) {
            pl.recursivePrintAllObjects();
        } else {
            pl.nonRecursivePrintAllObjects();
        }

    }

    public static void testCombinationNoRepeat(boolean recursive)
    {
        CombinationNoRepeat<Integer> comb = new CombinationNoRepeat<>();
        int k = 3;
        comb.setK(k);
        comb.setN(5);
        comb.setAlphabet(new Integer[]{1, 2, 3, 4, 5});
        comb.setCurrentObj(new Integer[k]);
        comb.setPrinter(new ConsolePrinter());

        if (recursive) {
            comb.recursivePrintAllObjects();
        } else {
            comb.nonRecursivePrintAllObjects();
        }

    }

    public static void testWordWithLetterRepeat()
    {
        WordWithLetterRepeatString wordComb = new WordWithLetterRepeatString();

        wordComb.setK(6);
        wordComb.setN(6);
        wordComb.setRepeatLetter("a");
        wordComb.setCountOfRepeat(3);
        wordComb.setAlphabet(new String[]{"a", "b", "c", "d", "e", "f"});
        wordComb.setPrinter(new ConsolePrinter());

        wordComb.nonRecursivePrintAllObjects();
    }
}