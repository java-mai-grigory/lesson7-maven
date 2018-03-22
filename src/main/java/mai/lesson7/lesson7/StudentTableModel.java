package mai.lesson7.lesson7;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import mai.lesson7.entity.InvalidRate;
import mai.lesson7.entity.SavableStudent;
import mai.lesson7.entity.SavableStudent.State;
import mai.lesson7.entity.Student;


public class StudentTableModel extends AbstractTableModel{

	String[] colNames = {"Номер", "Имя", "Фамилия", "Математика", "Физика", "Программирование", "Среднее"};
	
	private ArrayList<SavableStudent> data = new ArrayList<>();
	
	public StudentTableModel(ArrayList<SavableStudent> data) {
		this.data = data;
	}
	
	ArrayList<SavableStudent> getData()
	{
		return data;
	}
	
	public void add() {
		SavableStudent stud = new SavableStudent("", ""); 
		data.add(stud);
		stud.setState(State.Added);
	}

	@Override
	public int getColumnCount() {
		
		return colNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col)
		{
		case 0:
			return row + 1;
		case 1:
			return data.get(row).getFname();
		case 2:
			return data.get(row).getSname();
		case 3:
			return data.get(row).getRateMath();
		case 4:
			return data.get(row).getRatePhys();
		case 5:
			if ( data.get(row).getRateProg() == -1 )  return "не сдан";  
			else return data.get(row).getRateProg();
		case 6:
			return String.format("%.2f", data.get(row).Avg());
		default:
			return 1;
		}
	}
	
	@Override
	public void setValueAt(Object value, int row, int col)
	{
		switch(col)
		{
		case 1:
			data.get(row).setFname(value.toString());
			break;
		case 2:
			data.get(row).setSname(value.toString());
			break;
		case 3:
			data.get(row).passMath( Integer.valueOf(value.toString()));
			break;
		case 4:
			data.get(row).passPhys( Integer.valueOf(value.toString()));
			break;
		case 5:       //Обратите внимание происходит информирование о неправильном вводе путем обработки исключений
			 try
        	 {
				 data.get(row).passProg( Integer.valueOf(value.toString()));
        	 }
        	 catch(NumberFormatException e)
        	 {
        		 JOptionPane.showMessageDialog(null, "Неправильный формат данных");
        	 }
			 catch(InvalidRate e)
        	 {
        		 JOptionPane.showMessageDialog(null, "Недопустимая оценка");
        	 }
		}
		TableModelEvent event = new TableModelEvent(this, row, row, col);
		fireTableChanged(event);
	}
	
	  @Override
      public boolean isCellEditable(int row, int col) {
          return col != 0 && col != 6;
      }
	
}
