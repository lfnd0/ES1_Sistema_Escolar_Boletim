package entidades;

import org.junit.Test;

import br.seb.entidades.Nota;
import junit.framework.TestCase;

public class TestNota extends TestCase {

	@Test
	public void testNota() {		
		Nota nota1 = new Nota();
		Nota nota2 = new Nota(10,10,10,10,0,0);
		
		nota1.setBimestre1(10);
		nota1.setBimestre2(10);
		nota1.setBimestre3(10);
		nota1.setBimestre4(10);
		nota1.setReavaliacao1(0);
		nota1.setReavaliacao2(0);
		
		assertEquals(10.0, nota2.getBimestre1());
		assertEquals(10.0, nota2.getBimestre2());
		assertEquals(10.0, nota2.getBimestre3());
		assertEquals(10.0, nota2.getBimestre4());
		assertEquals(0.0, nota2.getReavaliacao1());
		assertEquals(0.0, nota2.getReavaliacao2());
		assertEquals("Bimestre 1: 10.0, Bimestre 2: 10.0, Bimestre 3: 10.0, Bimestre 4: 10.0, Reavaliacao 1: 0.0, Reavaliacao 2: 0.0\n", nota1.toString());
	}
}