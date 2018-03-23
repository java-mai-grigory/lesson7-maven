package mai.lesson7.Storage;

import java.util.ArrayList;

import mai.lesson7.entity.SavableStudent;

public abstract class Storage implements ISerializable{
	
	private  final ArrayList<SavableStudent> data;

	public Storage(ArrayList<SavableStudent> data) {
		super();
		this.data = data;
	}
	
	public ArrayList<SavableStudent> getData() {
		return data;
	}

	
	
	
}
