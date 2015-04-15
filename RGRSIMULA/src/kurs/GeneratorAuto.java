package kurs;
import process.Actor;


public class GeneratorAuto extends Actor{

	private Main gui;
	private Model model;

	public GeneratorAuto(String string, Main gui, Model model) {
		setNameForProtocol(string);
		this.gui = gui;
		this.model = model;
	}

	@Override
	protected void rule() {
		int i = 0;
		while(getDispatcher().getCurrentTime() < gui.getChooseData_4().getDouble()){
			holdForTime(gui.getChooseRandom().next());
			Auto auto = new Auto("Auto"+String.valueOf(++i), gui , model);
			getDispatcher().addStartingActor(auto);
		}
		
	}

}
