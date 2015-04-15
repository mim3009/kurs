package kurs;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import paint.Painter;
import process.Actor;
import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import process.Store;
import stat.DiscretHisto;
import stat.Histo;
import stat.IHisto;
import widgets.experiments.IExperimentable;
import widgets.stat.IStatisticsable;
import widgets.trans.ITransProcesable;

public class Model implements IStatisticsable, IExperimentable,
		ITransProcesable {

	private Dispatcher dispatcher;
	private Main gui;
	private GeneratorAuto genAuto;
	private MultiActor multiCassir;
	private QueueForTransactions queueToCassir;
	private DiscretHisto dHistoForQueueToCassir;
	private QueueForTransactions queueToExit;
	private DiscretHisto dHistoForQueueToExit;
	private Road road;
	private DiscretHisto dHistoForQueueOfLost;
	private QueueForTransactions queueOfLost;
	private Store queueRoad;
	private QueueForTransactions queueTimeToExit;
	private DiscretHisto histoForQueueTimeToExit;
	private QueueForTransactions queueTimeToCassir;
	private DiscretHisto histoForQueueTimeToCassir;

	
	private DiscretHisto getHistoForQueueTimeToExit() {
		if (histoForQueueTimeToExit == null) {
			histoForQueueTimeToExit = new DiscretHisto();
		}
		return histoForQueueTimeToExit;
	}
	
	private DiscretHisto getHistoForQueueTimeToCassir() {
		if (histoForQueueTimeToCassir == null) {
			histoForQueueTimeToCassir = new DiscretHisto();
		}
		return histoForQueueTimeToCassir;
	}
	
	public QueueForTransactions getQueueTimeToExit() {
		if (queueTimeToExit == null) {
			queueTimeToExit = new QueueForTransactions();
			queueTimeToExit.setNameForProtocol("vremia ocheredi na viezd");
			queueTimeToExit.setDispatcher(dispatcher);
			queueTimeToExit.setDiscretHisto(getHistoForQueueTimeToExit());
			queueTimeToExit.init();
		}
		return queueTimeToExit;
	}
	
	public QueueForTransactions getQueueTimeToCassir() {
		if (queueTimeToCassir == null) {
			queueTimeToCassir = new QueueForTransactions();
			queueTimeToCassir.setNameForProtocol("vremia ocheredi do kassira");
			queueTimeToCassir.setDispatcher(dispatcher);
			queueTimeToCassir.setDiscretHisto(getHistoForQueueTimeToCassir());
			queueTimeToCassir.init();
		}
		return queueTimeToCassir;
	}
		
	
	public Road getRoad() {
		if (road == null) {
			road = new Road("Doroga", gui, this);
		}
		return road;
	}

	public QueueForTransactions<Actor> getQueueToCassir() {
		if (queueToCassir == null) {
			queueToCassir = new QueueForTransactions<>();
			queueToCassir.setMaxSize(gui.getChooseData_2().getInt()
					* gui.getChooseData_1().getInt());
			queueToCassir.setNameForProtocol("Ochered k kassiru");
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
			queueToExit.setNameForProtocol("Ochered na viezd");
			queueToExit.setDispatcher(dispatcher);
			queueToExit.setDiscretHisto(getHistoForQueueToExit());
			queueToExit.init();
		}
		return queueToExit;
	}

	private DiscretHisto getHistoForQueueToExit() {
		if (dHistoForQueueToExit == null) {
			dHistoForQueueToExit = new DiscretHisto();
		}
		return dHistoForQueueToExit;
	}

	public QueueForTransactions getQueueOfLost() {
		if (queueOfLost == null) {
			queueOfLost = new QueueForTransactions();
			queueOfLost.setNameForProtocol("Ochered poterianuh");
			queueOfLost.setDispatcher(dispatcher);
			queueOfLost.setDiscretHisto(getHistoForQueueToExit());
			queueOfLost.init();
		}
		return queueOfLost;
	}

	private DiscretHisto getHistoForQueueOfLost() {
		if (dHistoForQueueOfLost == null) {
			dHistoForQueueOfLost = new DiscretHisto();
		}
		return dHistoForQueueOfLost;
	}

	public DiscretHisto getHistoForQueueToCassir() {
		if (dHistoForQueueToCassir == null) {
			dHistoForQueueToCassir = new DiscretHisto();
		}
		return dHistoForQueueToCassir;
	}

	public GeneratorAuto getGenAuto() {
		if (genAuto == null) {
			genAuto = new GeneratorAuto("Generator avtomobiley", gui, this);
		}

		return genAuto;
	}

	public MultiActor getMultiCassir() {
		if (multiCassir == null) {
			multiCassir = new MultiActor();
			multiCassir.setNameForProtocol("MultiActor dlia brigadu kassirov");
			multiCassir.setOriginal(new Cassir("Kassir", gui, this));
			multiCassir.setNumberOfClones(gui.getChooseData_1().getInt());
		}

		return multiCassir;
	}

	public Model(Dispatcher d, Main g) {
		if (d == null || g == null) {
			System.out.println("Ne opredelen dispetcher ili GUI dlia Model");
			System.out.println("dalneyshaia rabota ne vozmojna");
			System.exit(0);
		}
		dispatcher = d;
		gui = g;
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
		getQueueTimeToExit().setPainter(gui.getDiagram_3().getPainter());
		getQueueTimeToCassir().setPainter(gui.getDiagram_4().getPainter());
		Painter p = new Painter(gui.getDiagram_1());
		p.setColor(Color.red);
		p.placeToXY(0, 0);
		getQueueRoad().setPainter(p);
	}

	public Store getQueueRoad() {
		if (queueRoad == null) {
			queueRoad = new Store();
			queueRoad.setNameForProtocol("Doroga");
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
	public Map<String, Double> getResultOfExperiment() {
		Map<String, Double> map = new HashMap<>();
		map.put("Histograma dlia dlinu chergi do kassira",
				dHistoForQueueToCassir.getAverage());
		map.put("Histograma dlia dlinu chergi na viezd",
				dHistoForQueueToExit.getAverage());
		map.put("Histograma dlia vremeni chergi na viezd",
				histoForQueueTimeToExit.getAverage());
		map.put("Histograma dlia vremeni chergi do kassira",
				histoForQueueTimeToCassir.getAverage());
		return map;
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
	public Map<String, Double> getTransResult() {
		Map<String, Double> map = new HashMap<>();
		map.put("Histograma dlia dlinu chergi do kassira",
				dHistoForQueueToCassir.getAverage());
		map.put("Histograma dlia dlinu chergi na viezd",
				dHistoForQueueToExit.getAverage());
		return map;
	}

	@Override
	public Map<String, IHisto> getStatistics() {
		Map<String, IHisto> map = new HashMap<>();
		map.put("Histograma dlia dlinu chergi do kassira",
				dHistoForQueueToCassir);
		map.put("Histograma dlia dlinu chergi na viezd", dHistoForQueueToExit);
		map.put("Histograma dlia vremeni chergi na viezd", histoForQueueTimeToExit);
		map.put("Histograma dlia vremeni chergi do kassira", histoForQueueTimeToCassir);
		return map;

	}

	@Override
	public void initForStatistics() {
	}

}
