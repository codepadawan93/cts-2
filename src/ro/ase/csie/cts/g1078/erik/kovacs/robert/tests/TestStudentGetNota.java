package ro.ase.csie.cts.g1078.erik.kovacs.robert.tests;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.ase.csie.cts.g1078.erik.kovacs.robert.Student;
import ro.ase.csie.cts.g1078.erik.kovacs.robert.exceptions.StudentException;

public class TestStudentGetNota {

	private Student student;
	private static final String name = "STUDENT";
	
	@Before
	public void setUp() throws Exception {
		student = new Student(name);
	}
	

	@After
	public void tearDown() throws Exception {
	}

	// Test exception for mark at non existing offset
	@Test(expected = StudentException.class)
	public void throwsStudentException() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(10)+1);
		}
		student.getNota(LEN + 2);
	}
	
	// Test if we get an exception if we just call the getter without having added a mark
	@Test(expected = StudentException.class)
	public void throwsExceptionIfMarksNotIntilialised() throws StudentException {
		student.getNota(0);
	}
	
	// Test if when we set a value and then retrieve it it is the same
	@Test
	public void setOneGetOne() throws StudentException {
		final int MARK = 10;
		student.addNota(MARK);
		assertEquals(MARK, student.getNota(0));
	}
	
	// Test upper and lower limits
	@Test(expected = StudentException.class)
	public void failsBelowLowerLimit() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(10)+1);
		}
		student.getNota(-1);
	}
	
	@Test(expected = StudentException.class)
	public void failsAboveUpperLimit() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(10)+1);
		}
		student.getNota(LEN + 1);
	}

}
