import java.util.Comparator;
public class Comparator3 implements Comparator<Student> {

	//creating a new instance of the comparator as only one instance is needed
	private static final Comparator3 instance = new Comparator3();

	//accessor that returns the Comparator instance created earlier
	public static Comparator3 getInstance() {
		return instance;
	}

	//default constructor
	private Comparator3() {
	}

	@Override
	//setting the conditions for the comparison - in this case, compare the progress that each pair of the Student objects has from highest to lowest
	public int compare(Student o1, Student o2) {
		return (-1) * Integer.compare(o1.getProgress(), o2.getProgress());
	}

}