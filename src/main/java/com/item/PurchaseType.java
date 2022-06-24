package main.java.com.item;

import main.java.com.item.pets.enums.*;
import main.java.com.item.supplies.enums.*;


interface DesiredItem {
	Enum<?> desire();

}


public enum PurchaseType implements DesiredItem {
	Food(SupplyType.Food),
	Leash(SupplyType.Leash),
	CatLiter(SupplyType.CatLiter),
	Toy(SupplyType.Toy),
	Treat(SupplyType.Treat),
	Dog(AnimalType.Dog),
	Bird(AnimalType.Bird),
	Cat(AnimalType.Bird),
	Ferret(AnimalType.Ferret),
	Snake(AnimalType.Snake);

	private final String name;


	PurchaseType(Enum<?> type) {
		name = String.valueOf(type);
	}


	@Override
	public Enum<?> desire() {
		return this;
	}
}
