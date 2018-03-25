package mai.lesson7.entity;

import java.io.Serializable;

public class SavableStudent extends Student implements Serializable{

	public enum State {Added, Deleted, Modified, None}
	
	private State state;
	
	private int RowId;

	public SavableStudent()
	{

	}

	public SavableStudent(String fname, String sname) {
		super(fname, sname);
		RowId = -1;
		state = State.None;
	}

	public SavableStudent(String fname, String sname, int i, int j, int k) {
		super(fname, sname, i, j, k);
		RowId = -1;
		state = State.None;
	}

    public SavableStudent(int id, String fname, String sname, int i, int j, int k) {
        super(fname, sname, i, j, k);
        RowId = id;
        state = State.None;
    }


    public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

    public void setAdded() {
        this.state = State.Added;
    }

	public int getRowId() {
		return RowId;
	}

	public void setRowId(int rowId) {
		RowId = rowId;
	}

}
