import combinatoric.basic.printers.ConsolePrinter;
import combinatoric.combinations.CombinationNoRepeat;
import combinatoric.placements.PlacementWithRepeats;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        // testPlacementWithRepeats(true);
        testCombinationNoRepeat(true);
    }

    public static void testPlacementWithRepeats(boolean recursive)
    {
        PlacementWithRepeats pl = new PlacementWithRepeats();
        pl.setK(5);
        pl.setN(5);
        pl.setAlphabet(new String[]{"a", "b", "c", "d", "e"});
        pl.setPrinter(new ConsolePrinter());

        if (recursive) {
            pl.recursivePrintAllObjects();
        } else {
            pl.nonRecursivePrintAllObjects();
        }

    }

    public static void testCombinationNoRepeat(boolean recursive)
    {
        CombinationNoRepeat comb = new CombinationNoRepeat();
        comb.setK(3);
        comb.setN(5);
        comb.setAlphabet(new String[]{"a", "b", "c", "d", "e"});
        comb.setPrinter(new ConsolePrinter());

        if (recursive) {
            comb.recursivePrintAllObjects();
        } else {
            comb.nonRecursivePrintAllObjects();
        }

    }
}