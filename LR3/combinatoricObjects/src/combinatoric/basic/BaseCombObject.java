package combinatoric.basic;

abstract public class BaseCombObject extends CombObject {
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
