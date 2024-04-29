package combinatoric.basic;

public interface ICombObject<TypeOfAlphabet> {

    /**
     * Метод проверяет, можно ли сгенерировать следующий комбинаторный объект,
     * если можно, то записывает следующий объект в this.currentObj и возвращает true
     * если нет, то возвращает false
     *
     * @return boolean
     */
    boolean genNextObj();

    TypeOfAlphabet[] getAlphabet();

    void setAlphabet(TypeOfAlphabet[] alphabet);

    TypeOfAlphabet[] getCurrentObj();

    void setCurrentObj(TypeOfAlphabet[] currentObj);

    void setN(int n);

    int getN();

    int getK();

    void setK(int k);
}
