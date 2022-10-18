package executor;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Course;
import model.Student;

public class Executor {

	private static final Logger LOGGER = Logger.getLogger(Executor.class);
	private static final Scanner SCANNER = new Scanner(System.in);

	// generates mobile number.
	public String generateMobileNumber() {
		String mobileNumber = "";
		for (int count = 0; count < 10; count++) {
			mobileNumber += ThreadLocalRandom.current().nextInt(0, 10);
		}
		return mobileNumber.substring(0, 5) + " " + mobileNumber.substring(5, 10);
	}

	// Generates and returns a list of 10 sample courses.
	public List<Course> getSampleCourses() {
		List<Course> courses = new ArrayList<Course>();
		for (int count = 1; count <= 10; count++) {
			courses.add(new Course(count, "COURSE_" + count));
		}
		return courses;
	}

	// Generates and returns a list of 3 sample students with subscribed courses.
	public List<Student> getSampleStudents(Session session, boolean saveCourses) {
		List<Course> courses = getSampleCourses();
		List<Student> students = new ArrayList<Student>();

		Student student1 = new Student(1001, "Student_" + 1, "N/A");
		Student student2 = new Student(2002, "Student_" + 2, "N/A");
		Student student3 = new Student(3003, "Student_" + 3, "N/A");

		for (Course course : courses) {
			long courseID = course.getCourseID();

			// Condition for adding courses to student1.
			if (courseID >= 4 && courseID <= 8) {
				List<Course> courseListOfStudents = student1.getCourses();
				List<Student> studentListOfCourses = course.getStudents();
				courseListOfStudents.add(course);
				studentListOfCourses.add(student1);
//				course.setStudents(studentListOfCourses);
//				student1.setCourses(courseListOfStudents);
			}

			// Condition for adding courses to student2.
			if (courseID % 2 == 0) {
				List<Course> courseListOfStudents = student2.getCourses();
				List<Student> studentListOfCourses = course.getStudents();
				courseListOfStudents.add(course);
				studentListOfCourses.add(student2);
//				course.setStudents(studentListOfCourses);
//				student2.setCourses(courseListOfStudents);
			}

			// Condition for adding courses to student3.
			if (courseID % 2 != 0) {
				List<Course> courseListOfStudents = student3.getCourses();
				List<Student> studentListOfCourses = course.getStudents();
				courseListOfStudents.add(course);
				studentListOfCourses.add(student3);
//				course.setStudents(studentListOfCourses);
//				student1.setCourses(courseListOfStudents);
			}

			if (saveCourses == true) {
				session.save(course);
			}
		}

		if (saveCourses == true) {
			session.beginTransaction().commit();
		}

		LOGGER.info("\n\n\n\nOn hold! Press \"Enter\" to continue.");
		SCANNER.nextLine();

		students.add(student1);
		students.add(student2);
		students.add(student3);

		return students;
	}

	// Saves all students to DB.
	public void saveAllStudentsToDB(Session session, boolean saveCoursesbeforeCreatingEmployees) {
		List<Student> students = getSampleStudents(session, saveCoursesbeforeCreatingEmployees);
		for (Student student : students) {
			session.save(student);
		}
		session.beginTransaction().commit();
	}

	// Update each student's phone number.
	public void updateAllStudentsPhoneNumber(Session session) {
		for (int count = 1; count <= 3; count++) {
			Student student = (Student) session.get(Student.class, (long) ((count * 1000) + count));
			if (student == null) {
				return;
			}
			student.setPhone(generateMobileNumber());
			session.update(student);
		}
		session.beginTransaction().commit();
	}

	// Delete a random student.
	public void deleteRandomStudent(Session session) {
		long id = ThreadLocalRandom.current().nextLong(1, 4);
		id = (id * 1000) + id;
		Student student = (Student) session.get(Student.class, id);
		if (student == null) {
			return;
		}
		List<Course> courses = student.getCourses();
		if (courses.isEmpty()) {
			return;
		}
		for (Course course : courses) {
			course.getStudents().remove(student);
			session.save(course);
		}
		courses.clear();
		session.delete(student);
		session.beginTransaction().commit();
	}

	// Delete random courses.
	public void deleteRandomCourses(Session session, int numberOfDeletion) {
		if (numberOfDeletion <= 0 || numberOfDeletion > 11) {
			return;
		}

		while (numberOfDeletion > 0) {
			long random = ThreadLocalRandom.current().nextLong(1, 11);
			Course course = (Course) session.get(Course.class, random);
			if (course == null) {
				numberOfDeletion--;
				continue;
			}
			List<Student> students = course.getStudents();
			if (students.isEmpty()) {
				continue;
			}
			for (Student student : students) {
				student.getCourses().remove(course);
				session.save(student);
			}
			students.clear();
			session.delete(course);
			numberOfDeletion--;
		}
		session.beginTransaction().commit();
	}

	// Shows a random course with it's subscribed students.
	public void showRandomCourseWithStudents(Session session) {
		long random = ThreadLocalRandom.current().nextLong(1, 11);
		Course course = (Course) session.get(Course.class, random);
		if (course == null) {
			return;
		}
		String text = "\n\n\n\nSample course with student details:\n";
		text += "-----------------------------------\n" + course;
		List<Student> students = course.getStudents();
		for (Student student : students) {
			if (student == null) {
				continue;
			}
			text += "\n|_____>" + student;
		}
		LOGGER.info(text + "\n\n\n\n");
	}

	// Shows a random student with subscribed courses.
	public void showRandomStudentWithCourses(Session session) {
		long random = ThreadLocalRandom.current().nextLong(1, 4);
		random = (random * 1000) + random;
		Student student = (Student) session.get(Student.class, random);
		if (student == null) {
			return;
		}
		String text = "\n\n\n\nSample student with courses details:\n";
		text += "--------------------------------------------\n" + student;
		List<Course> courses = student.getCourses();
		for (Course course : courses) {
			if (course == null) {
				continue;
			}
			text += "\n|_____>" + course;
		}
		LOGGER.info(text + "\n\n\n\n");
	}

	public static void main(String[] args) {

		// Setting up logger.
		BasicConfigurator.configure();
		LOGGER.setLevel(Level.ALL);

		// Setting up connection.
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Setting up executor object.
		Executor executor = new Executor();

		// Adding sample students with subscribed courses to database.
		LOGGER.info("\n\n\n\nPress \"Enter\" to add sample students with subscribed courses to database.\n");
		SCANNER.nextLine();
		executor.saveAllStudentsToDB(session, true);

		LOGGER.info("\n\n\n\nPress \"Enter\" to show a random student with courses.\n");
		SCANNER.nextLine();
		executor.showRandomStudentWithCourses(session);

		LOGGER.info("\n\n\n\nPress \"Enter\" to show a random course with it's subscriber students.\n");
		SCANNER.nextLine();
		executor.showRandomCourseWithStudents(session);

		// Updating each student's mobile number.
		LOGGER.info("\n\n\n\nPress \"Enter\" to update each student's mobile number to database.\n");
		SCANNER.nextLine();
		executor.updateAllStudentsPhoneNumber(session);

		LOGGER.info("\n\n\n\nPress \"Enter\" to show a random student with courses.\n");
		SCANNER.nextLine();
		executor.showRandomStudentWithCourses(session);

		LOGGER.info("\n\n\n\nPress \"Enter\" to show a random course with it's subscriber students.\n");
		SCANNER.nextLine();
		executor.showRandomCourseWithStudents(session);

		// Deleting a random student.
		LOGGER.info("\n\n\n\nPress \"Enter\" to delete a random student.\n");
		SCANNER.nextLine();
		executor.deleteRandomStudent(session);

		LOGGER.info("\n\n\n\nPress \"Enter\" to show a random student with courses.\n");
		SCANNER.nextLine();
		executor.showRandomStudentWithCourses(session);

		LOGGER.info("\n\n\n\nPress \"Enter\" to show a random course with it's subscriber students.\n");
		SCANNER.nextLine();
		executor.showRandomCourseWithStudents(session);

		// Deleting random courses.
		LOGGER.info("\n\n\n\nPress \"Enter\" to delete random courses.\n");
		SCANNER.nextLine();
		executor.deleteRandomCourses(session, 5);

		LOGGER.info("\n\n\n\nPress \"Enter\" to show a random student with courses.\n");
		SCANNER.nextLine();
		executor.showRandomStudentWithCourses(session);

		LOGGER.info("\n\n\n\nPress \"Enter\" to show a random course with it's subscriber students.\n");
		SCANNER.nextLine();
		executor.showRandomCourseWithStudents(session);

		// Exiting.
		LOGGER.info("\n\n\n\nPress \"Enter\" to exit.\n");
		SCANNER.nextLine();

		// Closing all connections.
		session.close();
		sessionFactory.close();
		configuration = null;

		// Closing scanner.
		SCANNER.close();

		// Message showing successful execution.
		LOGGER.info("\n\n\n\nExecution successful.");
	}

}
