package mvcModule;

public abstract class Model{
	protected Controller.SceneChangeFacade scfacade;

	public Model(Controller.SceneChangeFacade scfacade){
		this.scfacade = scfacade;
	}
	
	public abstract void update();
}