package mai.lesson7.lesson7;

import java.awt.BorderLayout;
import java.awt.Color ;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import mai.lesson7.Storage.LocalStorage;
import mai.lesson7.Storage.PostgresqlStorage;
import mai.lesson7.entity.SavableStudent;
import mai.lesson7.settings.Settings;

public class Program extends JFrame implements TableModelListener{
	
	private JPanel windowContent = new JPanel();
	private JTable table = new JTable(); 
	private JToolBar tool = new JToolBar();
	private JMenuBar menubar = new JMenuBar();
	private JButton buttonOpen = new JButton("Открыть");
	private JButton buttonSave = new JButton("Сохранить");
    private JButton buttonSerialize = new JButton("Сериализовать");
    private JButton buttonDeserialize = new JButton("Восстановить");
    private JButton buttonDatabaseLoad = new JButton("Открыть из БД");
    private JButton buttonDatabaseSave = new JButton("Сохранить в БД");
	private JButton buttonAdd = new JButton("Добавить");
	private JButton buttonRemove = new JButton("Удалить");
	private JComboBox combo = new JComboBox();
	
	
	private ArrayList<SavableStudent> data = new ArrayList<>(); 

	StudentTableModel model;

	Program(String title)
	{
		super(title);
		windowContent= new JPanel();
		// Добавил компановку
		BorderLayout bl = new BorderLayout();
		windowContent.setLayout(bl);

		windowContent.add("Center", new JScrollPane(table));   //Таблица в центр
		windowContent.add("North", tool);						//Панель инструментов на север
		this.setJMenuBar(menubar);

		JMenu menu=new JMenu("Menu");  
		JMenu submenu=new JMenu("Sub Menu");  
		JMenuItem i1=new JMenuItem("Item 1");  
		JMenuItem i2=new JMenuItem("Item 2");  
		JMenuItem i3=new JMenuItem("Item 3");  
		JMenuItem i4=new JMenuItem("Item 4");  
		JMenuItem i5=new JMenuItem("Item 5");  
		menu.add(i1); menu.add(i2); menu.add(i3);  
		submenu.add(i4); submenu.add(i5);  
		menu.add(submenu);  
		menubar.add(menu);  
		combo.setBounds(0, 0, 100, 40);

		loadTestData();
		model = new StudentTableModel(data);

		model.addTableModelListener(this);
		
		Font font = new Font("SansSerif", Font.PLAIN, 16);
		table.setFont(font);
		table.setRowHeight(30);
		table.getTableHeader().setFont(font);
		table.setModel(model);

		setContentPane(windowContent);

		tool.add(buttonOpen);
		tool.add(buttonSave);
        tool.add(buttonDeserialize);
        tool.add(buttonSerialize);
        tool.add(buttonDatabaseLoad);
        tool.add(buttonDatabaseSave);
		tool.addSeparator();
		tool.add(buttonAdd);
		tool.add(buttonRemove);
		tool.add(combo);
		tool.addSeparator();
	/*	buttonAdd.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.add();
				table.updateUI();
			}
		});*/

		//Пример лямбда выражений
		buttonAdd.addActionListener( e -> {
			model.add();
			table.updateUI();
		});

        buttonSave.addActionListener(e -> {
            LocalStorage storage = new LocalStorage(model.getData());
            try {
                storage.save();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Файл не найден");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка чтения файла");
            }
        });

        buttonOpen.addActionListener(e -> {
            LocalStorage storage = new LocalStorage(model.getData());
            try {
                storage.load();
                table.updateUI();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Файл не найден");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка чтения файла");
            }
        });

        buttonDatabaseLoad.addActionListener(e -> {
            PostgresqlStorage storage = new PostgresqlStorage(model.getData());
            storage.init();
            storage.load();
			table.updateUI();
            storage.close();
        });

		buttonDatabaseSave.addActionListener(e -> {
			PostgresqlStorage storage = new PostgresqlStorage(model.getData());
			storage.init();
			storage.insert();
			storage.update();
			storage.close();
		});

		buttonRemove.addActionListener(e -> {
			   int[] selectedRows = table.getSelectedRows();
			   for(int i : selectedRows)
			   	   model.remove(i);
			   table.updateUI();
		});
		
		
        buttonSerialize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalStorage storage = new LocalStorage(model.getData());
				try {
					storage.serialize();
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Файл не найден");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Произошла ошибка чтения файла");
				}
			}
		});
        
        

                
		buttonDeserialize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalStorage storage = new LocalStorage(model.getData());
                try {
                    storage.deserialize();
                    table.updateUI();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Файл не найден");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка чтения файла");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

		render(table.getColumnModel().getColumn(3), data);
		render(table.getColumnModel().getColumn(4), data);
		render(table.getColumnModel().getColumn(5), data);

		setExtendedState(this.MAXIMIZED_BOTH);   //Максимизировать окно
		pack();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setVisible(true);
	}

	private void  loadTestData()
	{
		data.clear();
		/*data.add(new SavableStudent("Иван", "Иванов", 5, 4, 5) );
		data.add(new SavableStudent("Петр", "Петров", 5, 3, 5) );
		data.add(new SavableStudent("Олег", "Кузнецов", 5, 5, 4) );
		data.add(new SavableStudent("Сергей", "Сергеев", 5, 3, 2) );*/
	}
	
	static void render(TableColumn col, final ArrayList<SavableStudent> data)
	{
		col.setCellRenderer( new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(
					JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, col);
				// По правому краю
				label.setHorizontalAlignment(JLabel.RIGHT);
				// У двоечников красные оценки
				if ( data.get(row).getRateMath() < 3  || data.get(row).getRatePhys() < 3 || data.get(row).getRateProg() < 3  ){
					label.setForeground(Color.RED);
				} 
				else{
					label.setForeground(Color.BLACK);
				}
				return label;
			}
		});
	}

	static public void main(String[] arg)
	{
		Program p = new Program("Информационная система");
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		table.updateUI();
		System.out.println("Таблица изменилась" + " " + arg0.getFirstRow() + "-" + arg0.getLastRow());
		for(int i = arg0.getFirstRow(); i <= arg0.getLastRow(); i++)
			if ( model.getData().get(i).getRowId() > 0 ) model.getData().get(i).setState(SavableStudent.State.Modified);
	}
}
