package ro.ase.csie.cts.g1078.erik.kovacs.robert.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.ase.csie.cts.g1078.erik.kovacs.robert.Student;
import ro.ase.csie.cts.g1078.erik.kovacs.robert.exceptions.StudentException;

public class TestStudentNotaFrecventaMaxima {

	private Student student;
	private static final String name = "STUDENT";
	
	@Before
	public void setUp() throws Exception {
		student = new Student(name);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// Test exception when no marks are present
	@Test(expected = StudentException.class)
	public void throwsStudentExceptionWhenNoMarks() throws StudentException {	
		student.notaFrecventaMaxima();
	}
	
	// Test cross-check - same inputs yield same outputs
	@Test
	public void crossCheckTest() throws StudentException {
		final int TWO_LEN = 10;
		final int TWO = 2;
		final Student student2 = new Student(name + "2");
		for(int i = 0; i < TWO_LEN; i++) {
			student.addNota(TWO);
			student2.addNota(TWO);
		}
		student.addNota(5);
		student2.addNota(5);
		student.addNota(10);
		student2.addNota(10);
		
		assertTrue(student.notaFrecventaMaxima() == student2.notaFrecventaMaxima());
	}
	
	// Test inverse relationship
	@Test
	public void inverseRelationshipTest() throws StudentException {
		final int FIVE = 5;
		final int NINE = 9;
		final int FIVE_LEN = 2;
		final int NINE_LEN = 10;
		
		for(int i = 0; i < FIVE_LEN; i++) {
			student.addNota(FIVE);
		}
		for(int i = 0; i < NINE_LEN; i++) {
			student.addNota(NINE);
		}
		
		assertTrue(student.notaFrecventaMaxima() == NINE);
	}
	
	// If all marks are in range 1, 10, then mode will also be in that range
	@Test
	public void modeIsInRange() throws StudentException {
		final int LEN = 10;
		for(int i = 0; i < LEN; i++) {
			student.addNota(new Random().nextInt(10)+1);
		}
		float mean = student.notaFrecventaMaxima();
		assertTrue(mean >= Student.LOWER_LIMIT || mean <= Student.UPPER_LIMIT);
	}
	
	// If there is only one mark, then the mode should be that mark
	@Test
	public void modeIsOnlyValue() throws StudentException {
		final int MARK = 5;
		student.addNota(MARK);
		float mean = student.notaFrecventaMaxima();
		assertEquals((float)MARK, mean, 0);
	}
	
	// test i order matters
	@Test
	public void orderDoesNotMatter() throws StudentException {
		final int[] MARKS1 = {5, 5, 6, 8};
		final int[] MARKS2 = {6, 8, 5, 5};
		final int[] MARKS3 = {5, 8, 6, 5};
		
		final Student student2 = new Student(name + "2");
		final Student student3 = new Student(name + "3");
		
		for(int i = 0; i < MARKS1.length; i++) {
			student.addNota(MARKS1[i]);
			student2.addNota(MARKS2[i]);
			student3.addNota(MARKS3[i]);
		}
		
		float mode1 = student.notaFrecventaMaxima();
		float mode2 = student2.notaFrecventaMaxima();
		float mode3 = student3.notaFrecventaMaxima();
		
		// I hope you trust transitivity of identity operator :P
		assertTrue((mode1 == mode2) && (mode2 == mode3));
	}
	
	// Test if returns the greater of two numbers with equal frequency
	@Test
	public void returnsGreaterIfSameFrequencies() throws StudentException {
		final int GREATER = 8;
		student.addNota(5);
		student.addNota(GREATER);
		final float mode = student.notaFrecventaMaxima();
		assertEquals(GREATER, mode, 0);
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
		student.notaFrecventaMaxima();
		long delta = System.currentTimeMillis() - start;
		if(delta < MAX_TIME_MILLIS) {
			assertTrue(true);
		}else {
			fail("Took more than 200 millis");
		}
	}
}
