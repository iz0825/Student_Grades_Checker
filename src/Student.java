import java.util.Arrays;

public class Student {
	//creating and initializing data members
	private String name;
	private String adminNum;
	private String[] Subjects;
	private String[] Grades_1;
	private String[] Grades_2;
	private int total1 = 0;
	private int total2 = 0;
	private int progress = 0;

	private int subjectCounter = 0;
	private int gradeCounter = 0;
	private int grade2Counter = 0;

	//constructors
	//default constructor
	public  Student() {
		this.name = null;
		adminNum = null;
		Subjects = null;
		Grades_1 = null;
		Grades_2 = null;		
	}
	//constructor that initializes the student name and adminNum and uses default number of subjects
	public Student(String n, String a) {
		this.name = n;
		adminNum = a;
		Subjects = new String[6];
		Grades_1 = new String[6];
		Grades_2 = new String[6];
	}
	//constructor that initializes the student name and adminNum but uses specific number of subjects
	public Student(String n, String a, int s) {
		this.name = n;
		adminNum = a;
		Subjects = new String[s];
		Grades_1 = new String[s];
		Grades_2 = new String[s];
	}

	//accessors
	public String getName() {
		return name;
	}
	public String getAdminNum() {
		return adminNum;
	}
	public String[] getSubjects() {
		return Subjects;
	}
	public String[] getGrades1() {
		return Grades_1;
	}
	public String[] getGrades2() {
		return Grades_2;
	}
	public int getTotal1() {
		return total1;
	}
	public int getTotal2() {
		return total2;
	}
	public int getProgress() {
		return progress;
	}

	//mutators
	public void setName(String n) {
		this.name = n;
	}
	public void setAdminNum(String ad) {
		adminNum = ad;
	}
	public void setSubject(String sub) {
		Subjects[subjectCounter] = sub;
		subjectCounter++;
	}
	public void setGrades1(String gra) {
		Grades_1[gradeCounter] = gra;
		gradeCounter++;
	}
	public void setGrades2(String gra2) {
		Grades_2[grade2Counter] = gra2;
		grade2Counter++;
	}
	public void setTotal1(int t1) {
		total1 = t1;
	}
	public void setTotal2(int t2) {
		total2 = t2;
	}
	public void setProgress(int pro) {
		progress = pro;
	}
	public void setNumSub(int num) {
		Subjects = Arrays.copyOfRange(Subjects, 0, num);
		Grades_1 = Arrays.copyOfRange(Grades_1, 0, num);
		Grades_2 = Arrays.copyOfRange(Grades_2, 0, num);
	}
}
