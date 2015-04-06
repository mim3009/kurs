package kurs;
import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;


public class Cassir extends Actor {

	private Main gui;
	private Model model;
	private QueueForTransactions queueToObsl;
	private QueueForTransactions queueToExit;
	private QueueForTransactions queueToCassir;
	private BooleanSupplier isAuto;
	private BooleanSupplier isPlaceToOut;
	
	private void initCondition() {
		isAuto =()-> queueToCassir.size()>0;
		isPlaceToOut =()-> queueToExit.size()<queueToExit.getMaxSize();
	}

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
				
				waitForCondition(isAuto,"���� ���� ��������� ��� ��������������");
	
				Auto auto = (Auto) queueToCassir.removeFirst();
				getDispatcher().printToProtocol("������ ������ ������ � �볺����");
				getDispatcher().printToProtocol("������ ������� ����������");
				holdForTime(gui.getChooseRandom_1().next());
				
				waitForCondition(isPlaceToOut, "���� ���� ���� � ���� �� ����");
				
				auto.setZakaz(true);
				getDispatcher().printToProtocol("������ ������� ����������");
				queueToCassir.remove(auto);
				queueToExit.add(auto);
		} catch (DispatcherFinishException e) {
				return;
			}
	}
		
	}

	
	
	
	
}
