package mai.lesson7.Storage;

import java.io.*;
import java.util.ArrayList;

import mai.lesson7.entity.SavableStudent;
import mai.lesson7.entity.Student;
import mai.lesson7.settings.Settings;

public class LocalStorage extends Storage {

	public LocalStorage(ArrayList<SavableStudent> data) {
		
		super(data);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() throws FileNotFoundException, IOException {
		String file = Settings.getInstance().value("savfile");
		try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(file))) {
			stream.writeInt(this.getData().size());
			for (SavableStudent stud : this.getData()) {
				stream.writeUTF(stud.getFname());
				stream.writeUTF(stud.getSname());
				stream.writeInt(stud.getRateMath());
				stream.writeInt(stud.getRatePhys());
				stream.writeInt(stud.getRateProg());
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public void insert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
	}

	@Override
	public void load() throws FileNotFoundException, IOException {
		{
			String file = Settings.getInstance().value("savfile");
			try (DataInputStream stream = new DataInputStream(new FileInputStream(file))) {
				int size = stream.readInt();
				this.getData().clear();
				for(int i = 0; i < size; i++) {
					SavableStudent stud = new SavableStudent();
					stud.setFname(stream.readUTF());
					stud.setSname(stream.readUTF());
					stud.passMath(stream.readInt());
					stud.passPhys(stream.readInt());
					stud.passProg(stream.readInt());
					this.getData().add(stud);
				}
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
	}

	public void serialize() throws FileNotFoundException, IOException {
		{
			String file = Settings.getInstance().value("savfile");
			try(ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(file)) )
			{
				ArrayList<SavableStudent> dat = this.getData();
			    oos.writeObject(dat);
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
	}

    public void deserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
        {
            String file = Settings.getInstance().value("savfile");
            try(ObjectInputStream ios = new ObjectInputStream( new FileInputStream(file)) )
            {
            	ArrayList<SavableStudent> data = this.getData();
            	ArrayList<SavableStudent> loaded = (ArrayList<SavableStudent>) ios.readObject();
            	data.addAll(loaded);
               } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e) {
                throw e;
            }
        }
    }
}
