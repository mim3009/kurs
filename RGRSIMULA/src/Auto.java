import process.Actor;
import process.DispatcherFinishException;
import process.IWaitCondition;
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

	public Auto(String string, Main gui, Model model) {
		setNameForProtocol(string);
		this.gui = gui;
		this.model = model;
		
	}

	@Override
	protected void rule() {
		initFields();
		if(quequeToCassir.size()>=quequeToCassir.getMaxSize()){
			quequeOfLost.add(this);
		}
		quequeToCassir.add(this);		
		
		
		
		try {
			waitForCondition(new IWaitCondition() {

				@Override
				public boolean testCondition() {
					return zakaz;
				}
				
				public String toString(){
					return "Коли касир прийме заказ";
				}
				
			});
			
			waitForCondition(new IWaitCondition() {
	
				@Override
				public boolean testCondition() {
					return road.isOpen();
				}
				//quequeToExit.peekFirst() == Auto.this && road.isOpen();
				public String toString(){
					return "перший в черзі і шлях вільний";
				}
			});
			getDispatcher().printToProtocol(this.getNameForProtocol()+" починає відїзжати");
			holdForTime(rnd.next());
			model.getQueueToExit().remove(this);
			getDispatcher().printToProtocol(this.getNameForProtocol()+" поїхав");
		} catch (DispatcherFinishException e) {
			return;
		}
		
		
		
	}

	public void setZakaz(boolean b) {
		zakaz=true;
		
	}
	
	private void initFields(){
		quequeToExit = model.getQueueToExit();
		rnd = gui.getChooseRandom_3();
		road = model.getRoad();
		quequeToCassir = model.getQueueToCassir();
		quequeOfLost = model.getQueueOfLost();
	}

}
