package ro.ase.csie.cts.g1078.erik.kovacs.robert;

import java.util.ArrayList;

import ro.ase.csie.cts.g1078.erik.kovacs.robert.exceptions.StudentException;

public class Student {
	public static final int LOWER_LIMIT = 1;
	public static final int UPPER_LIMIT = 10;
	
	private String nume;
	private ArrayList<Integer> note;
	private float medie;
	
	public Student(String nume) {
		this.nume = nume;
		note = new ArrayList<Integer>();
	}

	public Student(String nume, ArrayList<Integer> note) {
		this.nume = nume;
		this.note = new ArrayList<Integer>();
		for(Integer n: note)
			this.note.add(n);
	}

	public String getNume() {
		return nume;
	}
	
	public void addNota(int nota) throws StudentException{
		if(nota < LOWER_LIMIT || UPPER_LIMIT > 10) {
			throw new StudentException(String.format("Mark must be in range [%d, %d]", LOWER_LIMIT, UPPER_LIMIT));
		}
		note.add(nota);
	}
	
	public int getNota(int i) throws StudentException{
		if(i < 0 || i >= note.size()) {
			throw new StudentException("Impossible to retrieve mark as it does not exist");
		}
		return note.get(i);
	}
	
	// No reason to set this from outside...
	private void setMedie(float medie) {
		this.medie = medie;
	}	
	
	public float getMedie() {
		return medie;
	}
	
	public int nrDisciplinePromovate(){
		int nrDiscipline = 0;
		for(Integer n: note)
			if(n >= 5)
				nrDiscipline++;
		
		return nrDiscipline;
	}
	
	// This should really just return the value... seems to me medie is no
	// longer used after this inside the class so would spare one method call
	public void medieAritmetica() throws StudentException{
		int nrDisciplinePromovate = this.nrDisciplinePromovate();
		if(note.size() == 0) {
			throw new StudentException("You cannot get the mean of an empty sequence");
		}
		float m = 0;
		for(Integer n: note) {
			if(n >= 5)
				m += n;
		}
		if(nrDisciplinePromovate == 0) {
			throw new StudentException("You cannot get the mean since no subjects were passed by student");
		}
		this.medie = m / this.nrDisciplinePromovate();
	}
	
	public int notaFrecventaMaxima() throws StudentException{
		if(note.size() == 0) throw new StudentException("Cannot find max frequency if there are no marks");
		// Iterate through the collection
		int nota = 0;
		int maxFreq = 0;
		for(int i = 0; i < note.size(); i++) {
			int n = note.get(i);
			int freq = 0;
			for(int j = 0; j < note.size(); j++) {
				int _n = note.get(j);
				if(_n == n) {
					freq++;
				}
			}
			// If current mark has greater frequency than selected mark, it becomes next selected mark
			if(freq > maxFreq) {
				maxFreq = freq;
				nota = n;
			} else if (freq == maxFreq) {
				// If equals, remember the greater of the two
				nota = nota > n ? nota : n;
			}
		}
		return nota;
	}
	
	@Override
	public String toString() {
		String output = this.nume + " note: ";
		for(Integer n: note)
			output += n + " ";
		return output;
	}
}
