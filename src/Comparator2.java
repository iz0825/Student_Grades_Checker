import java.util.Comparator;
public class Comparator2 implements Comparator<Student> {

	//creating a new instance of the comparator as only one instance is needed
	private static final Comparator2 instance = new Comparator2();

	//accessor that returns the Comparator instance created earlier
	public static Comparator2 getInstance() {
		return instance;
	}

	//default constructor
	private Comparator2() {
	}

	@Override
	//setting the conditions for the comparison - in this case, compares the second total grade that each pair of the Student objects has from highest to lowest
	public int compare(Student o1, Student o2) {
		return (-1) * Integer.compare(o1.getTotal2(), o2.getTotal2());
	}

}