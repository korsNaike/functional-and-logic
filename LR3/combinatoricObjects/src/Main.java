import combinatoric.basic.printers.ConsolePrinter;
import combinatoric.basic.printers.FilePrinter;
import combinatoric.combinations.CombinationNoRepeat;
import combinatoric.combinations.CombinationWithRepeats;
import combinatoric.complex.typeimplementations.Word2LetterRepeatString;
import combinatoric.complex.typeimplementations.WordOneLetterRepeat;
import combinatoric.complex.typeimplementations.WordWithLetterRepeatString;
import combinatoric.placements.PlacementWithRepeats;
import combinatoric.placements.PlacementWithoutRepeats;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        try {
            testPlacementWithRepeats(false, true);
            testCombinationNoRepeat(true, true);
            testPlacemenetsWithoutRepeats(false, true);
            testCombinationWithRepeats(true, true);
            testWord3A(true);
            testWordWith2LetterRepeat(true);
            testWordWithLetterRepeat(true);
        } catch (IOException exception) {
            System.out.println("404! file not found");
        }
    }

    public static void testWord3A(boolean toFile) throws IOException {
        WordOneLetterRepeat wordComb = new WordOneLetterRepeat();

        wordComb.setK(6);
        wordComb.setN(6);
        wordComb.setRepeatLetter("a");
        wordComb.setCountOfRepeat(3);
        wordComb.setAlphabet(new String[]{"a", "b", "c", "d", "e", "f"});
        if (!toFile) {
            wordComb.setPrinter(new ConsolePrinter());
        } else {
            String filePath = "X:\\Prolog\\LR3\\combinatoricObjects\\src\\files\\word3a.txt";
            wordComb.setPrinter(new FilePrinter(filePath));
        }

        wordComb.nonRecursivePrintAllObjects();
    }

    public static void testPlacementWithRepeats(boolean recursive, boolean toFile) throws IOException {
        PlacementWithRepeats<Integer> pl = new PlacementWithRepeats<Integer>();
        int k = 5;
        pl.setK(k);
        pl.setN(5);
        pl.setAlphabet(new Integer[]{1, 2, 3, 4, 5});
        pl.setCurrentObj(new Integer[k]);
        if (!toFile) {
            pl.setPrinter(new ConsolePrinter());
        } else {
            String filePath = "X:\\Prolog\\LR3\\combinatoricObjects\\src\\files\\placement_with_repeats.txt";
            pl.setPrinter(new FilePrinter(filePath));
        }


        if (recursive) {
            pl.recursivePrintAllObjects();
        } else {
            pl.nonRecursivePrintAllObjects();
        }
    }

    public static void testCombinationNoRepeat(boolean recursive, boolean toFile) throws IOException {
        CombinationNoRepeat<Integer> comb = new CombinationNoRepeat<>();
        int k = 3;
        comb.setK(k);
        comb.setN(5);
        comb.setAlphabet(new Integer[]{1, 2, 3, 4, 5});
        comb.setCurrentObj(new Integer[k]);
        if (!toFile) {
            comb.setPrinter(new ConsolePrinter());
        } else {
            String filePath = "X:\\Prolog\\LR3\\combinatoricObjects\\src\\files\\combination_no_repeat.txt";
            comb.setPrinter(new FilePrinter(filePath));
        }

        if (recursive) {
            comb.recursivePrintAllObjects();
        } else {
            comb.nonRecursivePrintAllObjects();
        }

    }

    public static void testWordWithLetterRepeat(boolean toFile) throws IOException {
        WordWithLetterRepeatString wordComb = new WordWithLetterRepeatString();

        wordComb.setK(6);
        wordComb.setN(6);
        wordComb.setCountOfRepeat(2);
        wordComb.setAlphabet(new String[]{"a", "b", "c", "d", "e", "f"});
        if (!toFile) {
            wordComb.setPrinter(new ConsolePrinter());
        } else {
            String filePath = "X:\\Prolog\\LR3\\combinatoricObjects\\src\\files\\word_with_letter_repeat.txt";
            wordComb.setPrinter(new FilePrinter(filePath));
        }

        wordComb.nonRecursivePrintAllObjects();
    }

    public static void testWordWith2LetterRepeat(boolean toFile) throws IOException {
        Word2LetterRepeatString wordComb = new Word2LetterRepeatString();

        wordComb.setK(6);
        wordComb.setN(6);
        wordComb.setCountOfRepeat(2);
        wordComb.setCountOfSecondRepeat(2);
        wordComb.setAlphabet(new String[]{"a", "b", "c", "d", "e", "f"});
        if (!toFile) {
            wordComb.setPrinter(new ConsolePrinter());
        } else {
            String filePath = "X:\\Prolog\\LR3\\combinatoricObjects\\src\\files\\word_with_2_letter_repeat.txt";
            wordComb.setPrinter(new FilePrinter(filePath));
        }

        wordComb.nonRecursivePrintAllObjects();
    }

    public static void testPlacemenetsWithoutRepeats(boolean recursive, boolean toFile) throws IOException {
        PlacementWithoutRepeats<String> pl = new PlacementWithoutRepeats<>();

        int k = 4;
        pl.setK(k);
        pl.setN(6);
        pl.setAlphabet(new String[]{"a", "b", "c", "d", "e", "f"});
        pl.setCurrentObj(new String[k]);
        if (!toFile) {
            pl.setPrinter(new ConsolePrinter());
        } else {
            String filePath = "X:\\Prolog\\LR3\\combinatoricObjects\\src\\files\\placement_without_repeats.txt";
            pl.setPrinter(new FilePrinter(filePath));
        }

        if (recursive) {
            pl.recursivePrintAllObjects();
        } else {
            pl.nonRecursivePrintAllObjects();
        }
    }

    public static void testCombinationWithRepeats(boolean recursive, boolean toFile) throws IOException {
        CombinationWithRepeats<String> comb = new CombinationWithRepeats<>();

        int k = 4;
        comb.setK(k);
        comb.setN(6);
        comb.setAlphabet(new String[]{"a", "b", "c", "d", "e", "f"});
        comb.setCurrentObj(new String[k]);
        if (!toFile) {
            comb.setPrinter(new ConsolePrinter());
        } else {
            String filePath = "X:\\Prolog\\LR3\\combinatoricObjects\\src\\files\\comb_with_repeats.txt";
            comb.setPrinter(new FilePrinter(filePath));
        }

        comb.nonRecursivePrintAllObjects();
    }
}