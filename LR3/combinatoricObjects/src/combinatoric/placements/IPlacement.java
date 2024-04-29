package combinatoric.placements;

import combinatoric.basic.ICombObject;
import combinatoric.basic.InitialFilling;
import combinatoric.basic.NonRecursiveSearch;
import combinatoric.basic.RecursiveSearch;

/**
 * Обобщающий интерфейс для размещений
 * @param <TypeOfAlphabet>
 */
public interface IPlacement<TypeOfAlphabet> extends InitialFilling, RecursiveSearch, NonRecursiveSearch, ICombObject<TypeOfAlphabet> {
}