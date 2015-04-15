package kurs;
import java.util.function.BooleanSupplier;

import javax.swing.Timer;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import widgets.ChooseRandom;


public class Auto extends Actor {
	
	private Main gui;
	private Model model;
	private boolean zakaz;
	private QueueForTransactions quequeToExit;
	private Road road;
	private ChooseRandom rnd;
	private QueueForTransactions quequeToCassir;
	private QueueForTransactions quequeOfLost;
	private BooleanSupplier isZakaz;
	private BooleanSupplier isRoad;
	
	private QueueForTransactions quequeTimeToExit;
	private QueueForTransactions quequeTimeToCassir;
	
	
	void initCondition(){
		isZakaz =()->zakaz;
		isRoad =()->road.isOpen();
	}
	
	private void initFields(){
		quequeToExit = model.getQueueToExit();
		rnd = gui.getChooseRandom_3();
		road = model.getRoad();
		quequeToCassir = model.getQueueToCassir();
		quequeOfLost = model.getQueueOfLost();
		quequeTimeToCassir = model.getQueueTimeToCassir();
		quequeTimeToExit = model.getQueueTimeToExit();
	}

	public Auto(String string, Main gui, Model model) {
		setNameForProtocol(string);
		this.gui = gui;
		this.model = model;
		
	}

	@Override
	protected void rule() {
		initCondition();
		initFields();
		if(quequeToCassir.size()>=quequeToCassir.getMaxSize()){
			quequeOfLost.add(this);
		}
		quequeToCassir.add(this);
		double r1 = getDispatcher().getCurrentTime();
		quequeTimeToCassir.add(1);		
		try {
			waitForCondition(isZakaz,"kogda kassir primet zakaz");

			double t1 = getDispatcher().getCurrentTime();
			double v = t1-r1;
			for(int i = 0;i<(int)v;i++){
				quequeTimeToCassir.add(1);		
			}
			while(quequeTimeToCassir.size()!=0){
				quequeTimeToCassir.removeFirst();		
			}
			getDispatcher().printToProtocol("klient stoial v ocheredi k kassiru: " + (t1-r1));
			
			waitForCondition(isRoad, "perviy v ocheredi i put svoboden"); 
			
			getDispatcher().printToProtocol(this.getNameForProtocol()+" nachinaet otiezjat");
			double r = getDispatcher().getCurrentTime();
			quequeTimeToExit.add(1);
			holdForTime(rnd.next());
			model.getQueueToExit().remove(this);
			double t = getDispatcher().getCurrentTime();
			double a = t-r;
			getDispatcher().printToProtocol("klient stoial v ocheredi na viezd: " + a);
			for(int i = 0;i<(int)a;i++){
				quequeTimeToExit.add(1);		
			}
			while(quequeTimeToExit.size()!=0){
				quequeTimeToExit.removeFirst();		
			}
			getDispatcher().printToProtocol(this.getNameForProtocol()+" poehal");
			getDispatcher().printToProtocol("Klient obsluzivalsa na protiazhenii: " + (t-r1));
		} catch (DispatcherFinishException e) {
			return;
		}	
		
	}

	public void setZakaz(boolean b) {
		zakaz=true;
		
	}
}
