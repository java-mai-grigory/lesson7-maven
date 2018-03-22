package mai.lesson7.entity;

public class InvalidRate extends RuntimeException{
	
	public InvalidRate()
	{
		super();
	}

	public String getMessage()
	{
		return "Invalid rating";
	}

	public String toString()
	{
		return "Неверный рейтинг";
	}
}
