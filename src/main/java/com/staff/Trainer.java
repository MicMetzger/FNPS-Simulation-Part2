package main.java.com.staff;
import java.util.Random;




public class Trainer extends Employee {
	private String name = "";
	

	public Trainer() {
		super();
		int num = new Random().nextInt(NAME_TEMPLATE.size());
		this.name = NAME_TEMPLATE.get(num);
		NAME_TEMPLATE.remove(num);
		super.base = this;
	}


	@Override
	public String getName() {
		return name + ", the Trainer,";
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}

}
