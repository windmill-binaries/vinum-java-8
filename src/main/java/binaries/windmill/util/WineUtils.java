package binaries.windmill.util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import binaries.windmill.model.Wine;
import binaries.windmill.model.WineColor;

public class WineUtils {

	public static List<Wine> findWhites(List<Wine> wines) {
		return wines.stream()
			 .filter(wine -> wine.getColor().equals(WineColor.WHITE))
			 .collect(Collectors.toList());
	}
	
	public static List<Wine> filterWinesWithLimit(List<Wine> wines, Predicate<Wine> predicate, int limit) {
		return wines.stream()
				    .filter(predicate)
				    .limit(limit)
				    .collect(Collectors.toList());
	}
	
	public static List<Wine> filterWines(List<Wine> wines, Predicate<Wine> predicate) {
		return wines.stream()
			 .filter(predicate)
			 .collect(Collectors.toList());
	}
	
	public static Optional<Integer> sumWinePrices(List<Wine> wines) {
		return wines.stream()
			 .map(Wine::getPrice)
			 .reduce(Integer::sum);
	}
	
	public static Optional<Integer> wineWithLongestName(List<Wine> wines) {
		return wines.stream()
			 .map(wine -> wine.getName())
			 .map(String::length)
			 .reduce(Integer::max);
	}
	
	public static List<Wine> sortWines(List<Wine> wines, Comparator<Wine> comparator) {
		return wines.stream()
			 .sorted(comparator)
			 .collect(Collectors.toList());
	}
}
