package mai.lesson7.entity;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable{
    private String fname;
    private String sname;

    private int ratePhys, rateMath, rateProg;

    public Student()
	{

	}

    public Student(String fname, String sname) {
        this.fname = fname;
        this.sname = sname;
        ratePhys = rateMath = rateProg = -1;
    }
    
    public Student(String fname, String sname, int ratePhys, int rateMath, int rateProg) {
 		super();
 		this.fname = fname;
 		this.sname = sname;
 		this.ratePhys = ratePhys;
 		this.rateMath = rateMath;
 		this.rateProg = rateProg;
 	}

    
    /**
     * @param ratePhys сдал физику с оценкой
     */
    public void passPhys(int ratePhys) {
        if (ratePhys >=2 && ratePhys <= 5)
            this.ratePhys = ratePhys;
    }
    
    /**
     * @return Плохой или хороший студент 
     */
    public boolean isBad() {
       return (ratePhys <= 3) || (rateMath <= 3) || (rateProg <= 3);
    }
 

    public void setFname(String fname) {
		this.fname = fname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	/**
     * @param сдал математику с оценкой
     */
    public void passMath(int rateMath) {
    	if (rateMath >=2 && rateMath <= 5)
    		this.rateMath = rateMath;
    }
 
    
    public String getFname() {
		return fname;
	}


	public String getSname() {
		return sname;
	}


	public int getRatePhys() {
		return ratePhys;
	}


	public int getRateMath() {
		return rateMath;
	}


	public int getRateProg() {
		return rateProg;
	}


	/**
     * @param rateProg сдал программирование с оценкой
     */
    public void passProg(int rateProg) throws InvalidRate {
       	if (rateProg >=2 && rateProg <= 5)
       		this.rateProg = rateProg;
       	else
       		throw new InvalidRate();
    }
    
    /**
     * @return среднюю оценку
     */
    public double Avg()
    {
        return   1.0 * (rateMath + ratePhys + rateProg) / 3;
    }

    
    //Возвр. строку для печатии
@Override
	public String toString()
	{
		return String.format("%-20s M(%d)  Ф(%d) П(%d) Ср.(%.1f)", fname + " " + sname, rateMath, ratePhys, rateProg, this.Avg());
	}
	
	@Override
	public int compareTo(Student that ) {
		if ( this.Avg() > that.Avg() ) return 1;
		else if ( this.Avg() < that.Avg() ) return -1;
		else return 0;
	}

    
}

