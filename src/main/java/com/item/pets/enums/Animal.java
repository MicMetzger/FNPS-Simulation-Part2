package main.java.com.item.pets.enums;


import java.util.*;


public enum Animal implements AnimalInterface {
  Bulldog(AnimalType.Dog, Breed.Bulldog),
  Poodle(AnimalType.Dog, Breed.Poodle),
  Beagle(AnimalType.Dog, Breed.Beagle),
  Shibainu(AnimalType.Dog, Breed.Shibainu),
  Dog_NULL(AnimalType.Dog, Breed.typeNull),

  Abyssinian(AnimalType.Cat, Breed.Abyssinian),
  Bobtail(AnimalType.Cat, Breed.Bobtail),
  Curl(AnimalType.Cat, Breed.Curl),
  Birman(AnimalType.Cat, Breed.Birman),
  Cat_NULL(AnimalType.Cat, Breed.typeNull),

  Amazon(AnimalType.Bird, Breed.Amazon),
  Caique(AnimalType.Bird, Breed.Caique),
  Canary(AnimalType.Bird, Breed.Canary),
  Cockatoo(AnimalType.Bird, Breed.Cockatoo),
  Bird_NULL(AnimalType.Bird, Breed.typeNull),

  Sable(AnimalType.Ferret, Breed.Sable),
  Chocolate(AnimalType.Ferret, Breed.Chocolate),
  Champagne(AnimalType.Ferret, Breed.Champagne),
  Albino(AnimalType.Ferret, Breed.Albino),
  Ferret_NULL(AnimalType.Ferret, Breed.typeNull),

  Adder(AnimalType.Snake, Breed.Adder),
  Anaconda(AnimalType.Snake, Breed.Anaconda),
  BullSnake(AnimalType.Snake, Breed.BullSnake),
  Cobra(AnimalType.Snake, Breed.Cobra),
  Snake_NULL(AnimalType.Snake, Breed.typeNull);

  public              AnimalType   type;
  public              Breed        breed;
  public static final List<Animal> DOGS    = List.of(Bulldog, Poodle, Beagle, Shibainu, Dog_NULL);
  public static final List<Animal> CATS    = List.of(Abyssinian, Bobtail, Curl, Birman, Cat_NULL);
  public static final List<Animal> BIRDS   = List.of(Amazon, Caique, Canary, Cockatoo, Bird_NULL);
  public static final List<Animal> FERRETS = List.of(Sable, Chocolate, Champagne, Albino, Ferret_NULL);
  public static final List<Animal> SNAKES  = List.of(Adder, Anaconda, BullSnake, Cobra, Snake_NULL);


  Animal(AnimalType animal, Breed breed) {
    this.type  = animal;
    this.breed = breed;
  }


  Animal(AnimalType animal) {
    this.type  = animal;
    this.breed = Breed.typeNull;
  }


  public String toString() {
    return this.type.toString();
  }


  public boolean equals(String animal) {
    return this.type.toString().equals(animal);
  }


  @Override
  public Breed getType() {
    return this.breed;
  }


  public AnimalType getAnimal() {
    return this.type;
  }
}
