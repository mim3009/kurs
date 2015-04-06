import java.awt.Color;

import paint.Painter;
import process.Dispatcher;
import process.QueueForTransactions;
import process.Store;
import qusystem.MultiActor;
import stat.DiscretHisto;
import widgets.experiments.IExperimentable;
import widgets.trans.ITransProcesable;
import widgets.trans.TransProcessQueue;


public class Model implements IExperimentable, ITransProcesable{

	private Dispatcher dispatcher;
	private Main gui;
	private GeneratorAuto genAuto;
	private MultiActor multiCassir;
	private TransProcessQueue queueToCassir;
	private DiscretHisto dHistoForQueueToCassir;
	private QueueForTransactions queueToExit; 
	private DiscretHisto dHistoForQueueToExit;
	private Road road;
	private DiscretHisto dHistoForQueueOfLost;
	private QueueForTransactions queueOfLost;
	private Store queueRoad;
	

	public Road getRoad() {
		if(road == null){
			road = new Road("Дорога", gui, this);
		}
		return  road;
	}

	public TransProcessQueue getQueueToCassir() {
		if (queueToCassir == null) {
			queueToCassir = new TransProcessQueue();
			queueToCassir.setMaxSize(gui.getChooseData_2().getInt()*gui.getChooseData_1().getInt());
			queueToCassir.setNameForProtocol("Черга до касира");
			queueToCassir.setDispatcher(dispatcher);
			queueToCassir.setDiscretHisto(getHistoForQueueToCassir());
			queueToCassir.init();
		}
		return queueToCassir;
	}
	
	public QueueForTransactions getQueueToExit() {
		if (queueToExit == null) {
			queueToExit = new QueueForTransactions();
			queueToExit.setMaxSize(gui.getChooseData_3().getInt());
			queueToExit.setNameForProtocol("Черга на виїзд");
			queueToExit.setDispatcher(dispatcher);
			queueToExit.setDiscretHisto(getHistoForQueueToExit());
			queueToExit.init();
		}
		return queueToExit;
	}

	private DiscretHisto getHistoForQueueToExit() {
		if(dHistoForQueueToExit == null){
			dHistoForQueueToExit = new DiscretHisto();
		}
		return dHistoForQueueToExit;
	}
	
	public QueueForTransactions getQueueOfLost() {
		if (queueOfLost == null) {
			queueOfLost = new QueueForTransactions();
			queueOfLost.setNameForProtocol("Черга втрачених");
			queueOfLost.setDispatcher(dispatcher);
			queueOfLost.setDiscretHisto(getHistoForQueueToExit());
			queueOfLost.init();
		}
		return queueOfLost;
	}

	private DiscretHisto getHistoForQueueOfLost() {
		if(dHistoForQueueOfLost == null){
			dHistoForQueueOfLost = new DiscretHisto();
		}
		return dHistoForQueueOfLost;
	}
	
	
	
	

	public DiscretHisto getHistoForQueueToCassir() {
		if(dHistoForQueueToCassir == null){
			dHistoForQueueToCassir = new DiscretHisto();
		}
		return dHistoForQueueToCassir;
	}

	public GeneratorAuto getGenAuto() {
		if (genAuto == null) {
			genAuto = new GeneratorAuto("Генератор автомобилей", gui, this);
		}

		return genAuto;
	}

	public MultiActor getMultiCassir() {
		if (multiCassir == null) { 	multiCassir = new MultiActor();
		multiCassir.setNameForProtocol("MultiActor для бригади касиров");
		multiCassir.setOriginal(new Cassir("Касир", gui, this));
		multiCassir.setNumberOfClones(gui.getChooseData_1().getInt());
	}

		return multiCassir;
	}

	public Model(Dispatcher d, Main g) {
		if (d == null || g == null) {
			System.out.println("Не визначено диспетчера або GUI для Model");
			System.out.println("Подальша робота неможлива");
			System.exit(0);
		}
		dispatcher = d;
		gui = g;
		//Передаємо акторів до стартового списку диспетчера
		componentsToStartList();
	}

	private void componentsToStartList() {
		dispatcher.addStartingActor(getGenAuto());
		dispatcher.addStartingActor(getMultiCassir());
		dispatcher.addStartingActor(getRoad());
	}

	public void initForTest() {
		getQueueToCassir().setPainter(gui.getDiagram().getPainter());
		getQueueToExit().setPainter(gui.getDiagram_1().getPainter());
		getQueueOfLost().setPainter(gui.getDiagram_2().getPainter());
		Painter p = new Painter(gui.getDiagram_1());
		p.setColor(Color.red);
		p.placeToXY(0,0);
		getQueueRoad().setPainter(p);
	}

	public Store getQueueRoad() {
		if(queueRoad ==null){
			queueRoad = new Store();
			queueRoad.setNameForProtocol("Дорога");
			queueRoad.setDispatcher(dispatcher);
			
		}
		return queueRoad;
	}

	public void initForStat() {
		dispatcher.setProtocolFileName("");		
	}
	@Override
	public void initForExperiment(double factor) {
		multiCassir.setNumberOfClones((int) factor);
		
	}

	@Override
	public double getResultOfExperiment() {
		
		return dHistoForQueueToCassir.getAverage();
	}

	@Override
	public void initForTrans(double finishTime) {
		gui.getChooseData_4().setText(String.valueOf(finishTime));
		
	}

	@Override
	public void resetTransAccum() {
		getQueueToCassir().resetAccum();
	}

	@Override
	public double getTransResult() {
		return getQueueToCassir().getAvg();
	}
	

}
