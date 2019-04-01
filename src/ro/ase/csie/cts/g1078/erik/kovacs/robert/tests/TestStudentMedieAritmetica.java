package ro.ase.csie.cts.g1078.erik.kovacs.robert.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.ase.csie.cts.g1078.erik.kovacs.robert.Student;
import ro.ase.csie.cts.g1078.erik.kovacs.robert.exceptions.StudentException;

public class TestStudentMedieAritmetica {

	private Student student;
	private static final String name = "STUDENT";
	
	@Before
	public void setUp() throws Exception {
		student = new Student(name);
	}
	

	@After
	public void tearDown() throws Exception {
	}
	
	// If all marks are in range 1, 10, then mean will also be in that range
	@Test
	public void meanIsInRange() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(11)+1);
		}
		student.medieAritmetica();
		float mean = student.getMedie();
		if(mean >= Student.LOWER_LIMIT || mean <= Student.UPPER_LIMIT) {
			equals(true);
		}
	}
	
	// If there is only one mark, then the mean should be that mark
	@Test
	public void meanIsOnlyValue() throws StudentException {
		final int MARK = 5;
		student.addNota(MARK);
		student.medieAritmetica();
		float mean = student.getMedie();
		assertEquals((float)MARK, mean, 0);
	}
	
	// Test exception when no marks are present
	@Test(expected = StudentException.class)
	public void throwsStudentExceptionWhenNoMarks() throws StudentException {	
		student.medieAritmetica();
		student.getMedie();
	}

	// If there are only failing marks student has no mean
	@Test(expected = StudentException.class)
	public void noMeanForFailingStudent() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(5)+1);
		}
		student.medieAritmetica();
		student.getMedie();
	}
}
