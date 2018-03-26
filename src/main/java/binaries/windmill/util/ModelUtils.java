package binaries.windmill.util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModelUtils {

	public static <U, T extends Comparable<T>> List<U> sortList(List<U> list, Function<U, ? extends T> keyExtractor) {
		return list.stream()
				   .sorted(Comparator.comparing(keyExtractor))
				   .collect(Collectors.toList());
	}

}
