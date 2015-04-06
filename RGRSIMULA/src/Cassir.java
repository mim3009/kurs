import process.Actor;
import process.DispatcherFinishException;
import process.IWaitCondition;
import process.QueueForTransactions;


public class Cassir extends Actor {

	private Main gui;
	private Model model;
	private QueueForTransactions queueToObsl;
	private IWaitCondition isAuto;
	private QueueForTransactions queueToExit;
	private IWaitCondition isPlaceToOut;
	private QueueForTransactions queueToCassir;

	public Cassir(String string, Main gui, Model model) {
		setNameForProtocol(string);
		this.gui = gui;
		this.model = model;
		queueToExit = model.getQueueToExit();
		queueToCassir = model.getQueueToCassir();
	}

	@Override
	protected void rule() {

		initCondition();
		
		while (getDispatcher().getCurrentTime()<=gui.getChooseData_4().getDouble()){
			
			try {
				waitForCondition(isAuto);
	
				Auto auto = (Auto) queueToCassir.removeFirst();
				getDispatcher().printToProtocol("Кассир починає роботу з клієнтом");
				getDispatcher().printToProtocol("Кассир прийняв замовлення");
				holdForTime(gui.getChooseRandom_1().next());
				waitForCondition(isPlaceToOut);
				auto.setZakaz(true);
				getDispatcher().printToProtocol("Кассир виконав замовлення");
				queueToCassir.remove(auto);
				queueToExit.add(auto);
		} catch (DispatcherFinishException e) {
				return;
			}
	}
		
	}

	private void initCondition() {
		isAuto = new IWaitCondition() {
			
			@Override
			public boolean testCondition() {
				
				return queueToCassir.size()>0;
			}
			public String toString() {
				return "Коли буде автомобіль для обслуговування";
			}
		};
		isPlaceToOut = new IWaitCondition() {
			
			@Override
			public boolean testCondition() {
				return queueToExit.size()<queueToExit.getMaxSize();
				
			}
			public String toString() {
				return "Коли буде місце в черзі на виїзд";
			}
			
		};
		
	}
	
	
	
}
