package main.java.com.item.pets.enums;


public enum Breed implements AnimalInterface {
	// ~DOGS~	
  Poodle("Poodle"),
	Beagle("Beagle"),
	Shibainu("Shibainu"),
	Bulldog("Bulldog"),
	// ~END OF DOGS~	
	// ~CATS~	
	Abyssinian("Abyssinian"),
	Bobtail("Bobtail"),
	Curl("Curl"),
	Birman("Birman"),
  // ~END OF CATS~
  // ~BIRDS~
  Amazon("Amazon Parrot"),
	Caique("Caique"),
	Canary("Canary"),
  Cockatoo("Cockatoo"),
	// ~END OF BIRDS~	
	// ~FERRETS~
	Sable("Sable"),
	Chocolate("Chocolate"),
	Champagne("Champagne"),
	Albino("Albino"),
	// ~END OF FERRETS~	
	// ~SNAKES~
	Adder("Death Adder"),
	Anaconda("Anaconda"),
	BullSnake("Bull snake"),
	Cobra("Cobra"),
	// ~END OF SNAKES~	
	typeNull("");
	
	
  public final String type;

  Breed(final String breed) {
    this.type = breed;
  }


	@Override
	public Breed getType() {
		return Breed.valueOf(type);
	}
}
