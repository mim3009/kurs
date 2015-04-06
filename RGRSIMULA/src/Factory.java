import process.Dispatcher;
import qusystem.IModelFactory;


public class Factory implements IModelFactory {
	private Main gui;
	public Factory(Main gui) {
		this.gui = gui;
	}
	public Model createModel(Dispatcher dispatcher) {
		Model newModel = new Model(dispatcher, gui);
		return newModel;
	}
}

