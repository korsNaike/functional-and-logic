package combinatoric.basic;

import combinatoric.basic.printers.CombObjPrinter;
import java.util.Objects;

/**
 * Базовый класс для комбинаторных объектов
 */
abstract public class CombObject {

    private String[] alphabet;

    private int n;

    private String[] currentObj;

    private CombObjPrinter printer;

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setAlphabet(String[] alphabet) {
        this.alphabet = alphabet;
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public String[] getCurrentObj() {
        return currentObj;
    }

    public void setCurrentObj(String[] currentObj) {
        this.currentObj = currentObj;
    }

    public CombObjPrinter getPrinter() {
        return printer;
    }

    /**
     * Установить экземпляр для вывода комбинаторного объекта
     * @param printer
     */
    public void setPrinter(CombObjPrinter printer) {
        this.printer = printer;
    }

    /**
     * Заменить элемент в текущем комбинаторном объекте
     * @param value значение для замены
     * @param index индекс, на который поставить value
     */
    public void replaceElementInCurrentObj(String value, int index)
    {
        this.currentObj[index] = value;
    }

    /**
     * Получение следующего значения из алфавита
     *
     * @param curAlphabetValue текущий значение
     * @return Следующее значение в алфавите
     */
    public String getNextSymbol(String curAlphabetValue) {
        int i = 0;
        while ((i < this.n) && (!Objects.equals(this.alphabet[i], curAlphabetValue))) {
            i = i + 1;
        }
        return this.alphabet[i + 1];
    }

    /**
     * Вывести объект с помощью экземпляра, реализующего интерфейс CombObjPrinter;
     */
    public void printObject() {
        printer.printCombObj(this);
    }

    /**
     * Метод проверяет, можно ли сгенерировать следующий комбинаторный объект,
     * если можно, то записывает следующий объект в this.currentObj и возвращает true
     * если нет, то возвращает false
     *
     * @return boolean
     */
    abstract public boolean genNextObj();
}
