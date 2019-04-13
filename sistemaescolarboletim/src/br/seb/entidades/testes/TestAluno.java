package br.seb.entidades.testes;

import org.junit.Test;

import br.seb.entidades.Aluno;
import junit.framework.TestCase;

public class TestAluno extends TestCase {

	@Test
	public void testAluno() {
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno("nome", "dataNascimento", "login", "senha", 1);
		Aluno aluno3 = new Aluno("nome", "dataNascimento", 1);

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
	}
}