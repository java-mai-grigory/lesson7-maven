package mai.lesson7.Storage;

import java.util.ArrayList;

import mai.lesson7.entity.SavableStudent;

public abstract class Storage implements ISerializable{
	
	private ArrayList<SavableStudent> data = new ArrayList<>();

	public Storage(ArrayList<SavableStudent> data) {
		super();
		this.data = data;
	}
	
	public ArrayList<SavableStudent> getData() {
		return data;
	}

	
	public void setList(ArrayList<SavableStudent> data) {
		this.data = data;
	}
	
}
