package combinatoric.combinations;

import combinatoric.basic.ICombObject;
import combinatoric.basic.InitialFilling;
import combinatoric.basic.NonRecursiveSearch;
import combinatoric.basic.RecursiveSearch;

/**
 * Общий интерфейс для сочетаний
 * @param <TypeOfAlphabet>
 */
public interface ICombination<TypeOfAlphabet> extends InitialFilling, RecursiveSearch, NonRecursiveSearch, ICombObject<TypeOfAlphabet> {
}
