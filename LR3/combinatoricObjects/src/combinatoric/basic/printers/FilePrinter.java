package combinatoric.basic.printers;

import combinatoric.basic.CombObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FilePrinter implements CombObjPrinter {
    public FilePrinter(String filePath) throws IOException {
        this.writer = new FileWriter(filePath);
    }

    private final FileWriter writer;

    public void printCombObj(CombObject combObject) {
        try {
            this.writer.write(Arrays.toString(combObject.getCurrentObj()));
            this.writer.write('\n');
            this.writer.flush();
        } catch (IOException e) {
            System.out.println("Файл не был найден");
        }
    }
}
