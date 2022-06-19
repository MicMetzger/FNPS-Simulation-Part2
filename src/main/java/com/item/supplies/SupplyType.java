package main.java.com.item.supplies;
public enum SupplyType {
	Food("Food"),
	Leash("Leash"),
	CatLiter("Cat Liter"),
	Toy("Toy");
	
	private final String name;


	SupplyType(String type) {
		name = type;
	}
}


