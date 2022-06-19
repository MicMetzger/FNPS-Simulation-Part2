package main.java.com.store;
import main.java.com.item.Item;
import main.java.com.item.pet.Pet;
import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;
import java.util.List;




public class SimState {
	// Package level access, static, state control variables
	static State newDay, startDay, endDay, processDelivery, feedAnimals, visitBank, checkRegister, doInventory, openStore, cleanStore, goEndSimulation;
	static State       currentState;
	static State       endState;
	static State       previousState;
	static List<State> stateList;
	static boolean     RUNNING;
	Store store;


	public SimState(Store sim) {
		stateList 		= new ArrayList<State>();
		store           = sim;
		newDay          = new NewDay(this);
		startDay        = new StartDay(this);
		endDay          = new EndDay(this);
		feedAnimals		= new FeedAnimals(this);
		visitBank       = new VisitBank(this);
		checkRegister   = new CheckRegister(this);
		doInventory     = new DoInventory(this);
		processDelivery = new ProcessDelivery(this);
		cleanStore 		= new CleanStore(this);
		openStore  		= new OpenStore(this);
		goEndSimulation = new GoEndSimulation(this);
		// RUNNING = true;

		stateList.add(startDay);
		stateList.add(endDay);
		stateList.add(feedAnimals);
		stateList.add(visitBank);
		stateList.add(checkRegister);
		stateList.add(doInventory);
		stateList.add(openStore);
		stateList.add(cleanStore);
		stateList.add(goEndSimulation);
		goNewDay();
	}


	public void setStoreState(State state) {
		previousState = currentState;
		currentState  = state;
	}


	public void goNewDay() {
		currentState = newDay;
		currentState.enterState();
	}



	public State goStartDay()        {return startDay;}


	public State goProcessDelivery() {
		// previousState = currentState;
		return processDelivery;
	}


	public State goCheckRegister()   {return checkRegister;}


	public State goVisitBankState()  {

		return visitBank;
	}


	public State goFeedAnimals()  {return feedAnimals;}


	public State goDoInventory()     {return doInventory;}

	public State goOpenStore() 		 {return openStore; }

	public State goCleanStore() 	 {return cleanStore;}

	public State goEndDay()          {return endDay;}


	public State goEndSimulation()	{ return goEndSimulation;}

	public void goEnterState()       {currentState.enterState();}



	// public void update() {stateList.forEach(state -> state.update(this));}

}






class NewDay implements State {
	SimState simState;


	NewDay(SimState simState) {
		this.simState = simState;
	}


	@Override
	public void enterState() {
		System.out.println("\n##################################################");
		if(simState.store.day == 30) {
			simState.setStoreState(simState.goEndSimulation());
			exitState();
		}

		simState.store.day++;
		simState.store.selectStaff();
		System.out.println("Day: " + simState.store.day + "\n");
		nextState();
	}


	@Override
	public void exitState() {
		System.out.println("##################################################\n");

		// simState.startTheDay();
		//TODO : temporary 
		simState.goEnterState();
	}


	@Override
	public void nextState() {
		// simState.update();
		simState.setStoreState(simState.goStartDay());
		exitState();
	}

}




/**
 * Start day.
 * <p>
 * Daily route starting point.
 */
class StartDay implements State {
	SimState simState;


	public StartDay(SimState simState) {
		this.simState = simState;
	}


	@Override
	public void enterState() {
		System.out.println("\n#################################################");

		System.out.println("Total Store Cash: " + simState.store.getCash());
		if (simState.store.cash < 200.0) {
			simState.setStoreState(simState.goVisitBankState());
			simState.goEnterState();
		}
		nextState();
	}


	@Override
	public void exitState() {
		System.out.println("##################################################\n");

		simState.goEnterState();

		// TODO: update information and report. Afterwards, call nextState()
	}


	@Override
	public void nextState() {
		// simState.update();
		simState.setStoreState(simState.goProcessDelivery());
		simState.goEnterState();
		exitState();
	}

}



class ProcessDelivery implements State {

	SimState simState;


	public ProcessDelivery(SimState simState) {
		this.simState = simState;
	}


	@Override
	public void enterState() {
		System.out.println("\n##################################################");
		simState.store.currentStaff.processDeliveries();
		simState.setStoreState(SimState.previousState);
		nextState();
	}


	@Override
	public void exitState() {
		System.out.println("##################################################\n");

		simState.store.updateMailBox();
		simState.store.updateInventory();
		simState.setStoreState(simState.goFeedAnimals());
		simState.goFeedAnimals();


		// simState.setStoreState(SimState.previousState);

	}


	@Override
	public void nextState() {
		System.out.println("The employee returns to finish his other activities.");
		exitState();
	}


}


class FeedAnimals implements State {
	SimState simState;


	public FeedAnimals(SimState simState) {
		this.simState = simState;
	}


	@Override
	public void enterState() {
		System.out.println("\n##################################################");
		simState.store.currentStaff.feedAnimals();
		nextState();
	}


	@Override
	public void exitState() {
		simState.setStoreState(simState.goCheckRegister());
		simState.goEnterState();
		// TODO: update information and report. Afterwards, call nextState()
	}


	@Override
	public void nextState() {
		simState.store.updateInventory();
		simState.store.updateSickAnimal();
		simState.store.updateCash();
		exitState();
	}

}



class CheckRegister implements State {
	SimState simState;
	double   totalWithdrawn = 0;


	public CheckRegister(SimState simState) {
		this.simState = simState;
	}


	@Override
	public void enterState() {
		System.out.println("##################################################\n");
		if (!simState.store.checkRegister()) {
			System.out.println("Register cash is low... ");
			simState.setStoreState(simState.goVisitBankState());
			exitState();
		}
		else {
			System.out.println("Cash is sufficient.");
			nextState();
		}
	}


	@Override
	public void exitState() {
		System.out.println("##################################################\n");

		// TODO: update information and report. Afterwards, call nextState()
		simState.goEnterState();
	}


	@Override
	public void nextState() {
		totalWithdrawn = simState.store.bankWithdrawal;
		System.out.println("Total Bank Withdraw: " + totalWithdrawn);
		System.out.println("Cash: " + simState.store.getCash());
		simState.setStoreState(simState.goDoInventory());
		exitState();

	}

}


class VisitBank implements State {
	SimState simState;


	public VisitBank(SimState simState) {
		this.simState = simState;
	}


	@Override
	public void enterState() {
		simState.store.goToBank();
		exitState();
	}


	@Override
	public void exitState() {
		System.out.println("##################################################\n");

		// simState.update();
		simState.setStoreState(SimState.previousState);
	}


	@Override
	public void nextState() {

	}

}



class DoInventory implements State {
	SimState simState;


	public DoInventory(SimState simState) {
		this.simState = simState;
	}


	@Override
	public void enterState() {
		System.out.println("\n##################################################");
		simState.store.currentStaff.doInventory();
		nextState();
	}


	@Override
	public void exitState() {
		System.out.println("##################################################\n");
		simState.goEnterState();

		// TODO: update information and report. Afterwards, call nextState()
	}


	@Override
	public void nextState() {
		simState.setStoreState(simState.goOpenStore());
		simState.store.updateCash();
		simState.store.updateInventory();
		exitState();
	}

}


class OpenStore implements State{
	SimState simState;

	public OpenStore(SimState simState) {
		this.simState = simState;
	}

	@Override
	public void enterState() {
		System.out.println("##################################################\n");
		simState.store.openStore();
		nextState();
	}

	@Override
	public void exitState() {
		System.out.println("##################################################\n");
		simState.goEnterState();
	}

	@Override
	public void nextState() {
		simState.store.updateCash();
		simState.setStoreState(simState.goCleanStore());
		exitState();
	}
}

class CleanStore implements State {
	SimState simState;

	public CleanStore(SimState simState) {
		this.simState = simState;
	}

	@Override
	public void enterState() {
		System.out.println("##################################################\n");
		simState.store.currentStaff.cleanStore();
		nextState();
	}

	@Override
	public void exitState() {
		System.out.println("##################################################\n");
		simState.setStoreState(simState.goEndDay());
		simState.goEnterState();
	}

	@Override
	public void nextState() {
		simState.store.updateInventory();
		simState.store.updateSickAnimal();
		exitState();
	}
}


/**
 * End day.
 * Completion of daily route.
 * <p>
 * Clean-up and preparation for sequence restart.
 */
class EndDay implements State {
	SimState simState;


	public EndDay(SimState simState) {
		this.simState = simState;
	}


	@Override
	public void enterState() {
		System.out.println("##################################################");
		System.out.println("The workday comes to an end...");
		// TODO: 4
		// empty register and store cash in Store
		simState.store.updateCash();
		nextState();
	}


	@Override
	public void exitState() {
		System.out.println("##################################################\n");

		simState.goNewDay();
	}


	@Override
	public void nextState() {

		// simState.setStoreState(simState.goEndDay());
		exitState();
	}

}


class GoEndSimulation implements State {

	SimState simState;

	GoEndSimulation(SimState simState) {
		this.simState = simState;
	}

	@Override
	public void enterState() {
		System.out.println("---------–------------STATS-----------------------\n");
		System.out.println("Total Cash: $" + simState.store.cash);
		System.out.println("Total withdrawal: $"+ simState.store.bankWithdrawal);
		System.out.println("\n---------–------------SALES-----------------------");
		for(Item item:simState.store.getSoldItems()) {
			System.out.println(item.getName() + " $" + item.getSalePrice() + ", Sold on: DAY " + item.getDaySold());
		}
		System.out.println("\n--------–--------Remaining Items------------------");
		for(Item item:simState.store.getInventory()) {
			System.out.println(item.getName() + ", Value: $" + item.getListPrice());
		}
		System.out.println("\n----–--------Remaining Sick Animals---------------");
		for(Item item:simState.store.getSick()) {
			System.out.println(((Pet) item).getBreed() + ", Value: $" + item.getListPrice());
		}
		nextState();
	}

	@Override
	public void exitState() {
		System.exit(0);
	}

	@Override
	public void nextState() {
		exitState();
	}
}


