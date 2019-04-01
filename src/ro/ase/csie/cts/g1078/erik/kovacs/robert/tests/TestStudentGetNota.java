package ro.ase.csie.cts.g1078.erik.kovacs.robert.tests;

import static org.junit.Assert.*;

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


	// Test exception
	@Test(expected = StudentException.class)
	public void throwsStudentException() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(11)+1);
		}
		student.getNota(LEN + 2);
	}
	
	// Test upper and lower limits
	@Test(expected = StudentException.class)
	public void failsBelowLowerLimit() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(11)+1);
		}
		student.getNota(-1);
	}
	
	@Test(expected = StudentException.class)
	public void failsAboveUpperLimit() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(11)+1);
		}
		student.getNota(LEN + 1);
	}

}
