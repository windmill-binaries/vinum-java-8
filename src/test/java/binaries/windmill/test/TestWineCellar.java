package binaries.windmill.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

import binaries.windmill.model.Wine;
import binaries.windmill.model.WineColor;
import binaries.windmill.util.ModelUtils;
import binaries.windmill.util.WineUtils;

public class TestWineCellar {
	
	private List<Wine> wineCellar;
	
	@Before
	public void before() {
		wineCellar = new ArrayList<>();
		
		Wine wine1 = new Wine(WineColor.WHITE, "Virtus Sauvignon Blanc", 2014, 768);
		Wine wine2 = new Wine(WineColor.WHITE, "Virtus Pinot Grigio", 2017, 1005);
		Wine wine3 = new Wine(WineColor.WHITE, "Virtus Gewürztraminer", 2017, 915);
		Wine wine4 = new Wine(WineColor.RED, "Virtus Credo Red", 2015, 2150);
		wineCellar.add(wine1);
		wineCellar.add(wine2);
		wineCellar.add(wine3);
		wineCellar.add(wine4);
	}
	
	@Test
	public void shouldTestFilter() {
		List<Wine> whites = WineUtils.findWhites(wineCellar);
		
		assertFalse(whites.isEmpty());
		assertThat(whites.size(), equalTo(3));
	}
	
	@Test
	public void shouldTestLimit() {
		// refactor down function to invoke constant first, then parameter second, to avoid null pointer exceptions
		List<Wine> whites = WineUtils.filterWinesWithLimit(wineCellar, wine -> wine.getColor().equals(WineColor.WHITE), 2);
		
		assertFalse(whites.isEmpty());
		assertThat(whites.size(), equalTo(2));
	}
	
	@Test
	public void shouldTestForRoseWines() {
		Predicate<Wine> predicate = wine -> wine.getColor().equals(WineColor.ROSE);
		List<Wine> result = WineUtils.filterWines(wineCellar, predicate);
		
		assertTrue(result.isEmpty());
		assertThat(result.size(), equalTo(0));
	}
	
	@Test
	public void shouldTestForWinePrice() {
		Predicate<Wine> predicate = wine -> wine.getPrice() > 1000;
		List<Wine> result = WineUtils.filterWines(wineCellar, predicate);
		
		assertFalse(result.isEmpty());
		assertThat(result.size(), equalTo(2));
		assertThat(result.get(0).getName(), equalTo("Virtus Pinot Grigio"));
	}
	
	@Test
	public void shouldTestCellarCummulativePriceValue() {
		int actual = 0;
		for (Wine wine: wineCellar) {
			actual += wine.getPrice();
		}
		
		Optional<Integer> expected = WineUtils.sumWinePrices(wineCellar);
		assertTrue(expected.isPresent());
		assertThat(actual, equalTo(expected.get()));
	}
	
	@Test
	public void shouldTestWineSettersGetters() {
		Wine actual = new Wine();
		actual.setColor(WineColor.ROSE);
		actual.setName("Virtus Rose");
		actual.setPrice(750);
		actual.setYear(2014);
		
		assertThat(actual.getColor(), equalTo(WineColor.ROSE));
		assertThat(actual.getName(), equalTo("Virtus Rose"));
		assertThat(actual.getPrice(), equalTo(750));
		assertThat(actual.getYear(), equalTo(2014));
	}
	
	@Test
	public void shouldTestWineWithLongestName() {
		int actual = wineCellar.get(0).getName().length();
		
		Optional<Integer> expected = WineUtils.wineWithLongestName(wineCellar);
		
		assertTrue(expected.isPresent());
		assertThat(actual, equalTo(expected.get()));
	}
	
	@Test
	public void shouldTestWineListSorted() {
		// sort wines by price
		List<Wine> actual = ModelUtils.sortList(wineCellar, Wine::getPrice);
		assertFalse(actual.isEmpty());
		assertThat(768, equalTo(actual.get(0).getPrice()));
		assertThat(915, equalTo(actual.get(1).getPrice()));
		assertThat(1005, equalTo(actual.get(2).getPrice()));
		assertThat(2150, equalTo(actual.get(3).getPrice()));
		
		// sort wines by year
		actual = ModelUtils.sortList(wineCellar, Wine::getYear);
		assertFalse(actual.isEmpty());
		assertThat(2014, equalTo(actual.get(0).getYear()));
		assertThat(2015, equalTo(actual.get(1).getYear()));
		assertThat(2017, equalTo(actual.get(2).getYear()));
		assertThat(2017, equalTo(actual.get(3).getYear()));
		
		// sort wines by name
		actual = ModelUtils.sortList(wineCellar, Wine::getName);
		assertFalse(actual.isEmpty());
		assertThat("Virtus Credo Red", equalTo(actual.get(0).getName()));
		assertThat("Virtus Gewürztraminer", equalTo(actual.get(1).getName()));
		assertThat("Virtus Pinot Grigio", equalTo(actual.get(2).getName()));
		assertThat("Virtus Sauvignon Blanc", equalTo(actual.get(3).getName()));
	}

}
