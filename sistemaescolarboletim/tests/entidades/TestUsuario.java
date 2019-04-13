package entidades;

import java.util.ArrayList;

import org.junit.Test;

import br.seb.entidades.Aluno;
import br.seb.entidades.Disciplina;
import br.seb.entidades.Nota;
import junit.framework.TestCase;

public class TestUsuario extends TestCase {

	@Test
	public void testUsuario() {
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno("nome", "dataNascimento", "login", "senha", 1);
		Aluno aluno3 = new Aluno("nome", "dataNascimento", 1);
		
		Disciplina d1 = new Disciplina("nome");
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		disciplinas.add(d1);
		
		Nota n1 = new Nota();
		Nota n2 = new Nota();
		ArrayList<Nota> notas = new ArrayList<Nota>();
		notas.add(n1);
		notas.add(n2);
		
		aluno1.setNotas(notas);
		aluno1.setDisciplinas(disciplinas);
		
		aluno1.setNome("nome");
		aluno1.setDataNascimento("dataNascimento");
		aluno1.setLogin("login");
		aluno1.setSenha("senha");
		aluno1.setMatriculaAluno(1);
		
		System.out.println(aluno1);
		System.out.println(aluno2);
		System.out.println(aluno3);
		
		assertEquals("nome", aluno1.getNome());
		assertEquals("dataNascimento", aluno1.getDataNascimento());
		assertEquals("login", aluno1.getLogin());
		assertEquals("senha", aluno1.getSenha());
		assertEquals(1, aluno1.getMatriculaAluno());	
		assertEquals(disciplinas, aluno1.getDisciplinas());
		assertEquals(notas, aluno1.getNotas());
	}
}