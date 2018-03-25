package mai.lesson7.Storage;

import java.sql.*;
import java.util.ArrayList;

import mai.lesson7.entity.SavableStudent;
import mai.lesson7.settings.Settings;

public class PostgresqlStorage extends Storage{

	private   Connection connection;

	public PostgresqlStorage(ArrayList<SavableStudent> list) {
		super(list);
	}

	@Override
	public void init() {
		final Settings settings = Settings.getInstance();
		try {
			this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert() {
		for(SavableStudent stud: this.getData()) {
		    if (stud.getState() == SavableStudent.State.Added) {
                try (final PreparedStatement statement = this.connection.prepareStatement("insert into java_junior.student (fname, sname, math_rate, phys_rate, prog_rate) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, stud.getFname());
                    statement.setString(2, stud.getSname());
                    statement.setInt(3, stud.getRateMath());
                    statement.setInt(4, stud.getRatePhys());
                    statement.setInt(5, stud.getRateProg());
                    statement.executeUpdate();
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            stud.setRowId(generatedKeys.getInt(1));
                            stud.setState(SavableStudent.State.None);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw new IllegalStateException("Не могу создать запись");
            }
		}
		
	}

	@Override
	public void update() {
        for(SavableStudent stud: this.getData()) {
            if (stud.getState() == SavableStudent.State.Modified) {
                try (final PreparedStatement statement = this.connection.prepareStatement("update java_junior.student set fname = ?,  sname = ?,  math_rate = ?,  phys_rate = ?,  prog_rate = ? where stud_id = ?")) {
                    statement.setString(1, stud.getFname());
                    statement.setString(2, stud.getSname());
                    statement.setInt(3, stud.getRateMath());
                    statement.setInt(4, stud.getRatePhys());
                    statement.setInt(5, stud.getRateProg());
                    statement.setInt(6, stud.getRowId());
                    statement.executeUpdate();
                    stud.setState(SavableStudent.State.None);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throw new IllegalStateException("Не могу изменить запись");
            }
        }
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		this.getData().clear();
		try (final Statement statement = this.connection.createStatement();
			 final ResultSet rs = statement.executeQuery("select * from java_junior.student")) {
			while (rs.next()) {
				this.getData().add(new SavableStudent(rs.getInt("stud_id"), rs.getString("fname"), rs.getString("sname"), rs.getInt("math_rate"),  rs.getInt("phys_rate"),  rs.getInt("prog_rate") ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		// TODO Auto-generated method stub

	}

}
