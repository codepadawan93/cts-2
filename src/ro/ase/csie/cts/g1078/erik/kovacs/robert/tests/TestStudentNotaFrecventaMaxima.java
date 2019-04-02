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
		Student student2 = new Student(name + "2");
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
		
		for(int i = 0; i < FIVE_LEN; i++) { // good that primitive array HAS the length property exposed publicly
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
			student.addNota(new Random().nextInt(11)+1);
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

}
