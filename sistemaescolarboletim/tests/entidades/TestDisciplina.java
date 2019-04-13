package entidades;

import java.util.ArrayList;

import org.junit.Test;

import br.seb.entidades.Disciplina;
import br.seb.entidades.Nota;
import br.seb.entidades.Professor;
import junit.framework.TestCase;

public class TestDisciplina extends TestCase {

	@Test
	public void testDisciplina() {		
		Nota nota1 = new Nota(9,5,7,8,7,0);
		Nota nota2 = new Nota(10,10,10,10,0,0);
		ArrayList<Nota> notas = new ArrayList<Nota>();
		notas.add(nota1);
		notas.add(nota2);
		
		Professor professor = new Professor("nome", "dataNascimento", "login", "senha", 1);
		
		Disciplina disciplina1 = new Disciplina(1, "nome", 80, professor, notas);
		Disciplina disciplina2 = new Disciplina(2, "nome", 80, null);
		Disciplina disciplina3 = new Disciplina("nome");
		
		Disciplina disciplina4 = new Disciplina();
		disciplina4.setCodigoDisciplina(3);
		disciplina4.setNome("nome");
		disciplina4.setCargaHoraria(80);
		disciplina4.setProfessor(professor);
		disciplina4.setNotas(notas);
		
		assertEquals(1, disciplina1.getCodigoDisciplina());
		assertEquals("nome", disciplina1.getNome());
		assertEquals(80, disciplina1.getCargaHoraria());
		assertEquals(professor, disciplina1.getProfessor());
		assertEquals(notas, disciplina1.getNotas());
		assertEquals("codigo: 1, nome: nome, matricula do professor: 1\n", disciplina1.toString());
		assertEquals("nome", disciplina2.toString());
		assertEquals("nome", disciplina3.toString());
	}
}