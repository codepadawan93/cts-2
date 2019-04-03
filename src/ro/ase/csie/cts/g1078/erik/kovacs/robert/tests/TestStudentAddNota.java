package ro.ase.csie.cts.g1078.erik.kovacs.robert.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.ase.csie.cts.g1078.erik.kovacs.robert.Student;
import ro.ase.csie.cts.g1078.erik.kovacs.robert.exceptions.StudentException;

public class TestStudentAddNota {
	
	private Student student;
	private static final String name = "STUDENT";
	
	@Before
	public void setUp() throws Exception {
		student = new Student(name);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// Test exception
	@Test(expected = StudentException.class)
	public void throwsStudentException() throws StudentException {
		student.addNota(-1);
	}
	
	// Test upper and lower limits
	@Test
	public void succeedsOnLowerLimit() throws StudentException {
		student.addNota(Student.LOWER_LIMIT);
	}
	
	@Test
	public void succeedsOnUpperLimit() throws StudentException {
		student.addNota(Student.UPPER_LIMIT);
	}
	
	// Test if the marks actually get added
	// Would throw an error if attempting to retrieve a non-existent mark
	@Test
	public void addsMarksProperly() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(10)+1);
		}
		for(int i = 0; i < LEN; i++) {
			student.getNota(i);
		}
	}
	
	// See if you add a number and you know the order in which you added it, 
	// if it does not change
	@Test
	public void addsMarkPredictably() throws StudentException {
		final int MARK = 8;
		student.addNota(MARK);
		assertEquals(student.getNota(0), MARK);
	}
}
