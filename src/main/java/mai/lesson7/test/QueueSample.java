package mai.lesson7.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import mai.lesson7.entity.SavableStudent;
import mai.lesson7.entity.Student;

public class QueueSample {

	public static void main(String[] args) {
		ArrayList<Student> data = new ArrayList<Student>();
		data.add(new SavableStudent("Иван", "Иванов", 5, 4, 4) );
		data.add(new SavableStudent("Петр", "Петров", 5, 4, 5) );
		data.add(new SavableStudent("Олег", "Кузнецов", 5, 3, 4) );
		data.add(new SavableStudent("Сергей", "Сергеев", 5, 3, 2) );
		Collections.sort(data);
		
		for(Student s : data)
			System.out.println(s);
		
		System.out.println("");
		
		PriorityQueue<Student> q = new PriorityQueue<Student>();
		q.add(new SavableStudent("Иван", "Иванов", 5, 4, 4) );
		q.add(new SavableStudent("Петр", "Петров", 5, 4, 5) );
		q.add(new SavableStudent("Олег", "Кузнецов", 5, 3, 4) );
		q.add(new SavableStudent("Сергей", "Сергеев", 5, 3, 2) );
		System.out.println(q.peek());
	}

}
