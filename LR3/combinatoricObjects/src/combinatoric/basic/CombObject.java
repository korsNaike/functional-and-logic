package combinatoric.basic;

import combinatoric.basic.printers.CombObjPrinter;
import java.util.Objects;

/**
 * Базовый класс для комбинаторных объектов
 */
abstract public class CombObject<TypeOfAlphabet> implements ICombObject<TypeOfAlphabet>{

    private TypeOfAlphabet[] alphabet;

    private int n;

    private TypeOfAlphabet[] currentObj;

    private CombObjPrinter printer;

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setAlphabet(TypeOfAlphabet[] alphabet) {
        this.alphabet = alphabet;
    }

    public TypeOfAlphabet[] getAlphabet() {
        return alphabet;
    }

    public TypeOfAlphabet[] getCurrentObj() {
        return currentObj;
    }

    public void setCurrentObj(TypeOfAlphabet[] currentObj) {
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
    public void replaceElementInCurrentObj(TypeOfAlphabet value, int index)
    {
        this.currentObj[index] = value;
    }

    /**
     * Получение следующего значения из алфавита
     *
     * @param curAlphabetValue текущий значение
     * @return Следующее значение в алфавите
     */
    public TypeOfAlphabet getNextSymbol(TypeOfAlphabet curAlphabetValue) {
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

    private int k;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    protected void printWhileExistNextComb() {
        while (genNextObj()) printObject();
    }
}
