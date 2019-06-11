public class Subject {
	private String name;

	//constructors
	//default constructor
	public Subject() {
		this.name = null;
	}
	//constructor that specifically sets the name of the Subject object
	public Subject(String n) {
		this.name = n;
	}
	
	//accessor
	public String getName() {
		return name;
	}
	
	//mutator
	public void setName(String n) {
		this.name = n;
	}
}
