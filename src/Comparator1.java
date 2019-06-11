import java.util.Comparator;
public class Comparator1 implements Comparator<Student> {
	
	//creating a new instance of the comparator as only one instance is needed
	private static final Comparator1 instance = new Comparator1();

	//accessor that returns the Comparator instance created earlier
	public static Comparator1 getInstance() {
		return instance;
	}

	//default constructor
	private Comparator1() {
	}
	
	@Override
	//setting the conditions for the comparison - in this case, compares the first total grade that each pair of the Student objects has from highest to lowest
	public int compare(Student o1, Student o2) {
		return (-1) * Integer.compare(o1.getTotal1(), o2.getTotal1());
	}

}