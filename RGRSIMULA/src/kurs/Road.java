package kurs;
import process.Actor;
import process.Store;
import widgets.ChooseRandom;

public class Road extends Actor {

	private boolean open;
	private Main gui;
	private Model model;
	private ChooseRandom rnd;
	private Store queueRoad;

	public Road(String string, Main gui, Model model) {
		setNameForProtocol(string);
		this.gui = gui;
		this.model = model;
		rnd = gui.getChooseRandom_2();
		queueRoad = model.getQueueRoad();
	}

	public boolean isOpen() {
		return open;
	}

	@Override
	protected void rule() {
		getDispatcher().printToProtocol("Старт дороги"+String.valueOf(gui.getChooseData_4().getDouble()));
		while (getDispatcher().getCurrentTime() <= gui.getChooseData_4().getDouble()) {
			getDispatcher().printToProtocol("Дорога закрита");
			holdForTime(rnd.next());
			queueRoad.add(1);
			open = true;
			getDispatcher().printToProtocol("Дорога відкрита");
			holdForTime(rnd.next());
			open = false;
			queueRoad.remove(1);
		}

	}

}
