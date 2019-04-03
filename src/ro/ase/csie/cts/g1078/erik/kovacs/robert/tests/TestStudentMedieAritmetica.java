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
	
	// Basic test with pre-computed expected value
	@Test
	public void meanIsCorrect() throws StudentException {
		final double EXPECTED = 6;
		student.addNota(5);
		student.addNota(7);
		student.medieAritmetica();
		double mean = student.getMedie();
		assertEquals(EXPECTED, mean, 0);
	}
	
	// If all marks are in range 1, 10, then mean will also be in that range
	@Test
	public void meanIsInRange() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(10)+1);
		}
		student.medieAritmetica();
		final double mean = student.getMedie();
		assertTrue(mean >= Student.LOWER_LIMIT || mean <= Student.UPPER_LIMIT);
	}
	
	// If there is only one mark, then the mean should be that mark
	@Test
	public void meanIsOnlyValue() throws StudentException {
		final int MARK = 5;
		student.addNota(MARK);
		student.medieAritmetica();
		final double mean = student.getMedie();
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
			student.addNota(new Random().nextInt(4)+1);
		}
		student.medieAritmetica();
		student.getMedie();
	}
	
	// Takes less than 20 millis for 10k marks
	@Test
	public void takesLessThan200Millis() throws StudentException {
		final int LEN = 10000;
		final int MAX_TIME_MILLIS = 200;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(10)+1);
		}
		
		long start = System.currentTimeMillis();
		student.medieAritmetica();
		long delta = System.currentTimeMillis() - start;
		if(delta < MAX_TIME_MILLIS) {
			assertTrue(true);
		}else {
			fail("Took more than 200 millis");
		}
	}
}
