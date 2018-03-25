package mai.lesson7.Storage;

public interface ISerializable {
	public void init();
	public void close();
	public void insert() throws Exception;
	public void update();
	public void remove();
	public void load() throws Exception;
	public void save() throws Exception;

}
