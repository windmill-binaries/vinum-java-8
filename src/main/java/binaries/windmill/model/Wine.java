package binaries.windmill.model;

public class Wine {
	
	private WineColor color;
	private String name;
	private int year;
	private int price;
	
	public Wine() {
		
	}
	
	public Wine(WineColor color, String name, int year, int price) {
		this.color = color;
		this.name = name;
		this.year = year;
		this.price = price;
	}
	
	public WineColor getColor() {
		return color;
	}

	public void setColor(WineColor color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Wine[color=" + color +
				", name=" + name +
				", year=" + year +
				", price=" + price +
				"]";
	}

}
