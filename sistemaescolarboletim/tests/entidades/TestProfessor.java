package entidades;

import java.util.ArrayList;

import org.junit.Test;

import br.seb.entidades.Disciplina;
import br.seb.entidades.Nota;
import br.seb.entidades.Professor;
import junit.framework.TestCase;

public class TestProfessor extends TestCase {

	@Test
	public void testProfessor() {
		Professor professor1 = new Professor();
		Professor professor2 = new Professor("nome", "dataNascimento", "login", "senha", 1);
		Professor professor3 = new Professor("nome", "dataNascimento", 1);
		
		Disciplina d1 = new Disciplina("nome");
		
		Nota n1 = new Nota();
		Nota n2 = new Nota();
		
		ArrayList<Nota> notas = new ArrayList<Nota>();
		notas.add(n1);
		notas.add(n2);
		
		professor1.setNotas(notas);
		professor1.setDisciplina(d1);
		
		professor1.setNome("nome");
		professor1.setDataNascimento("dataNascimento");
		professor1.setLogin("login");
		professor1.setSenha("senha");
		professor1.setMatriculaProfessor(1);
		
		System.out.println(professor1);
		System.out.println(professor2);
		System.out.println(professor3);
		
		assertEquals("nome", professor1.getNome());
		assertEquals("dataNascimento", professor1.getDataNascimento());
		assertEquals("login", professor1.getLogin());
		assertEquals("senha", professor1.getSenha());
		assertEquals(1, professor1.getMatriculaProfessor());	
		assertEquals(d1, professor1.getDisciplina());
		assertEquals(notas, professor1.getNotas());
	}
}