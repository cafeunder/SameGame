package mvcModule;

import system.MouseFacade;

public abstract class Model{
	protected Controller.SceneChangeFacade scfacade;

	public Model(Controller.SceneChangeFacade scfacade){
		this.scfacade = scfacade;
	}
	
	public abstract void timerUpdate();
	public abstract void mousePressUpdate(MouseFacade mfacade);
	public abstract void mouseMoveUpdate(MouseFacade mfacade);
}