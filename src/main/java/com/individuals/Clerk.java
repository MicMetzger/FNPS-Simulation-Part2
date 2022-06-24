package main.java.com.individuals;


import static main.java.com.Builders.NAME_TEMPLATE;

import java.security.*;



public class Clerk extends Employee {
  private String name = "";
  

  public Clerk(int workedDays, String name) {
    super(workedDays);
    this.name = name;
  }

  public Clerk(String name) {
    super();
    this.name = name;
  }

  public Clerk() {
    super();
    int num = new SecureRandom().nextInt(NAME_TEMPLATE.size());
    this.name = NAME_TEMPLATE.get(num);
    NAME_TEMPLATE.remove(num);
    super.base = this;
    super.ACTIVE = false;
    super.state = EmployeeState.IDLE;
  }

  @Override
  public String getName() {
    return name + ", the Clerk,";
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}
