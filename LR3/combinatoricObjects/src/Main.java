import combinatoric.basic.printers.ConsolePrinter;
import combinatoric.placements.PlacementWithRepeats;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        PlacementWithRepeats pl = new PlacementWithRepeats();
        pl.setK(5);
        pl.setN(5);
        pl.setAlphabet(new String[]{"a", "b", "c", "d", "e"});
        pl.setPrinter(new ConsolePrinter());
        pl.recursivePrintAllObjects();
    }
}