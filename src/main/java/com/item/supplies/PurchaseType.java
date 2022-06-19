package main.java.com.item.supplies;
import main.java.com.item.pet.Animal;




interface DesiredItem {
	Enum<?> desire();
}




public enum PurchaseType implements DesiredItem {
	Food(SupplyType.Food), Leash(SupplyType.Leash), CatLiter(SupplyType.CatLiter), Toy(SupplyType.Toy),
	Dog(Animal.Dog), Bird(Animal.Bird), Cat(Animal.Bird);

	private final String name;


	PurchaseType(Enum<?> type) {name = String.valueOf(type);}


	@Override
	public Enum<?> desire() {
		return this;
	}
}
