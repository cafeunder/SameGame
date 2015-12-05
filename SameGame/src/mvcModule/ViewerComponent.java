package mvcModule;

public abstract class ViewerComponent {
	protected Controller.SceneChangeFacade scfacade;

	public ViewerComponent(Controller.SceneChangeFacade scfacade){
		this.scfacade = scfacade;
	}
	
	public abstract void draw();
}