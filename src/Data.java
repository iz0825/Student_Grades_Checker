import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
//importing referenced libraries used to handle CSV files
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter; 

public class Data {
	//initializing the two ArrayLists used to store Subject and Student objects that are often referenced and used later
	private static ArrayList<Subject> Subjects = new ArrayList<Subject>();
	private static ArrayList<Student> Students = new ArrayList<Student>();

	//initializing the opening FileChooser window for users to select specific CSV file to read data from
	private static JFileChooser fileChooser = new JFileChooser();

	//declaring the File data members
	private static File outFile1;
	private static File outFile2;
	private static File outFile3;

	//initializing the starting window
	private static Start starter = new Start();

	public static void main(String[] args) throws IOException, InterruptedException, BadLocationException {

		//letting the user select the CSV file that will be used - if user decides to cancel choosing, the program will stop running
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			read(fileChooser.getSelectedFile());
		}

		else {
			System.exit(0);
		}

		//setting the starter window visible to start running the program
		starter.setVisible(true);

	}

	private static void read(File inFile) throws IOException {
		
		//try-catch block used to avoid file errors
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inFile));
			//indicate that the values in the file are separated by commas
			CSVReader csvReader = new CSVReader(reader, ',');

			//read the first line of the file, which always correspond to the subjects
			String[] line1 = csvReader.readNext();	
			//creating a new Subject object by passing the content of each String value to the Subject class constructor - eliminate all values that contains "2" to avoid duplicating the same subject
			for (int i = 2; i < line1.length; i++) {
				if (!line1[i].contains("2")) {
					Subject subject = new Subject(line1[i].substring(0, line1[i].indexOf(" Ov")));
					//adding the new Subject object created to the ArrayList
					Subjects.add(subject);
				}
			}

			String[] line;
			//keep reading the next line in the CSV file until reaches the bottom or the summary line at the end of each exported report CSV file
			while ((line = csvReader.readNext())!= null && !(line[0].equals(""))) {
				//creating a new Student object by passing the name and admission number of the students to the Student class constructor
				Student student = new Student(line[0], line[1]);
				//adding the new Student object created to the ArrayList
				Students.add(student);
				for (int i = 2; i < line.length; i++) {
					//adding the subject and first report grade that the student has by using mutators that modifies the properties of the student
					if (!(line[i] .equals("")) && !(line1[i].contains("2"))) {
						student.setSubject((Subjects.get(i-2)).getName());
						student.setGrades1(line[i]);
					}
					//adding the second report grade
					else if (!(line[i] .equals("")) && line1[i].contains("2")) {
						student.setGrades2(line[i]);
					}
				}
				
				int count = 0;
				//counting the total number of subjects that the student takes
				for (int i = 0; i < (student.getSubjects().length); i ++) {
					if (student.getSubjects()[i] != null) {
						count ++;
					}
				}
				//changing the default number of subjects that the student takes if the number counted is different from the default value
				if (count != 6 ) {
					student.setNumSub(count);
				}
				//setting other properties to the Student object by calling other methods
				student.setTotal1(calculateTotal(convertInt(student.getGrades1())));
				student.setTotal2(calculateTotal(convertInt(student.getGrades2())));
				student.setProgress(calculateProgress(student.getTotal1(), student.getTotal2()));
			}

			csvReader.close();

		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//creating the three files that will be used to store information later to the name passes as arguments
	public static void createFiles(String o1, String o2, String o3) {
		outFile1 = new File(o1);
		outFile2 = new File(o2);
		outFile3 = new File(o3);	
	}

	//first option - search for the student and collect all the information as separate String variables that will be displayed on the result window
	public static String option1(String student) throws IOException, InterruptedException {
		Student stu = searchStudent(student);
		String text1 = "Subjects: ";
		String text2 = "Grades_1: ";
		String text3 = "Grades_2: ";
		for (int i = 0; i < stu.getSubjects().length; i ++) {
			if (i == 5) {
				text1 = text1 + stu.getSubjects()[i];
				text2 = text2 + stu.getGrades1()[i];
				text3 = text3 + stu.getGrades2()[i];
			}
			else {
				text1 = text1 + stu.getSubjects()[i] + ", ";
				text2 = text2 + stu.getGrades1()[i] + ", ";
				text3 = text3 + stu.getGrades2()[i] + ", ";
			}
		}
		write1(outFile1, stu);
		String text4 = "Total_1: " + stu.getTotal1();
		String text5 = "Total_2: " + stu.getTotal2();
		return (text1 + "\n" + text2 + "\n" + text4 + "\n" + text3 + "\n" + text5);
	}

	//second option - sort the ArrayList based on the first total grade and create a 2D array that will be used to display the results and write into the file. Then, repeat the process for second total grade
	public static void option2() throws IOException, BadLocationException {
		Collections.sort(Students, Comparator1.getInstance());
		String[][] rank1 = new String[Students.size()][2];
		String[][] rank2 = new String[Students.size()][2];
		for (int i = 0; i < Students.size(); i ++) {
			rank1[i][0] = Students.get(i).getAdminNum();
			String line = Students.get(i).getAdminNum();
			int grade_raw = Students.get(i).getTotal1();
			rank1[i][1] = Integer.toString(grade_raw);
			line = line + "\t" + Integer.toString(Students.get(i).getTotal1()) + "\n";
			if (grade_raw <= 24) {
				starter.getWindow().getOpt2().appendString2(line);
			}
			else {
				starter.getWindow().getOpt2().appendString(line);
			}
		}
		Collections.sort(Students, Comparator2.getInstance());
		starter.getWindow().getOpt2().appendString("\n" + "===Second Report of the year: ===" + "\n");
		for (int i = 0; i < Students.size(); i ++) {
			rank2[i][0] = Students.get(i).getAdminNum();
			String line = Students.get(i).getAdminNum();
			int grade_raw = Students.get(i).getTotal2();
			rank2[i][1] = Integer.toString(grade_raw);
			line = line + "\t" + Integer.toString(Students.get(i).getTotal2()) + "\n";
			if (grade_raw <= 24) {
				starter.getWindow().getOpt2().appendString2(line);
			}
			else {
				starter.getWindow().getOpt2().appendString(line);
			}
		}

		write2(outFile2, rank1, rank2);
	}

	//third option - sort the ArrayList based on progress made and create a 2D array that will be used to display the results and write into the file
	public static void option3() throws IOException, BadLocationException {
		Collections.sort(Students, Comparator3.getInstance());
		String[][] rank3 = new String[Students.size()][2];
		for (int i = 0; i < Students.size(); i ++) {
			rank3[i][0] = Students.get(i).getAdminNum();
			String line = Students.get(i).getAdminNum();
			int progress_raw = Students.get(i).getProgress();
			rank3[i][1] = Integer.toString(progress_raw);
			line = line + "\t" + Integer.toString(Students.get(i).getProgress()) + "\n";
			if (progress_raw < 0) {
				starter.getWindow().getOpt3().appendString2(line);
			}
			else {
				starter.getWindow().getOpt3().appendString(line);
			}
		}
		write2(outFile3, rank3);
	}

	//search for the student by comparing the adminNum of each Student object stored in the ArrayList with the one typed in by the user
	public static Student searchStudent(String adnum) {
		int index = 0;
		for (int i = 0; i < Students.size(); i ++) {
			if ((Students.get(i).getAdminNum()).equals(adnum)) {
				break;
			}
			else {
				index ++;
			}
		}
		//if the adminNum entered by the user does not match any of the existing ones, null is returned; else the corresponding Student object is returned
		if (index == Students.size()) {
			return null;
		}
		else {
			return Students.get(index);
		}
	}

	//method that writes the specific information about the student searched
	private static void write1(File file, Student student) throws IOException {

		try {
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(file));
			//indicate that the values in the file are separated by commas
			CSVWriter csvWriter1 = new CSVWriter(writer1,',');

			//write a String as a line into the file
			csvWriter1.writeNext(student.getSubjects());
			csvWriter1.writeNext(student.getGrades1());
			csvWriter1.writeNext(student.getGrades2());

			csvWriter1.flush();
			csvWriter1.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	//method that writes the ranking of students based on the total grades
	private static void write2(File file, String[][] result, String[][] result2) throws IOException {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			CSVWriter csvWriter = new CSVWriter(writer,',');
			String[] title = new String[2];
			title[0] = "Student's Admission Number";
			title[1] = "Total Grade for Report #1";

			csvWriter.writeNext(title);	
			for (int i = 0; i < Students.size(); i++) {
				csvWriter.writeNext(result[i]);
			}

			title[1] = "Total Grade for Report #2";
			csvWriter.writeNext(title);
			for (int i = 0; i < Students.size(); i++) {
				csvWriter.writeNext(result2[i]);
			}

			csvWriter.flush();
			csvWriter.close();

		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	//method that writes the ranking of the progress made by each student
	private static void write2(File file, String[][] result) throws IOException {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			CSVWriter csvWriter = new CSVWriter(writer,',');

			String[] title = new String[2];
			title[0] = "Student's Admission Number";
			title[1] = "Progress";
			csvWriter.writeNext(title);
			for (int i = 0; i < Students.size(); i++) {
				csvWriter.writeNext(result[i]);
			}
			csvWriter.flush();
			csvWriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	//method that converts the String array storing a student's grades to an int array that can be used for calculations - additional "+" and "-" after the grade number is removed
	private static int[] convertInt(String[] str) {
		int[] intStr = new int[str.length];
		for (int i = 0; i < str.length; i ++) {
			//substring used first to only take the first letter from any grades - the number only from grades like "7-" and "6+"
			//the number is then converted to integer using parseInt()
			int num = Integer.parseInt(str[i].substring(0, 1));
			//integer converted stored in new integer array
			intStr[i] = num;
		}
		return intStr;
	}

	//method that calculates the total grade of a student by summing the int array
	private static int calculateTotal(int[] grades) {
		int total = 0;
		for (int i = 0; i < grades.length; i ++) {
			total += grades[i];
		}
		return total;
	}

	//method that calculates the progress a student has made by subtracting the second total grade from the first total grade
	private static int calculateProgress(int g1, int g2) {
		int progress = (g2 - g1);
		return progress;
	}
}