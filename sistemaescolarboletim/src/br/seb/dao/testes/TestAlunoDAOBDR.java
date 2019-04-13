package br.seb.dao.testes;

import org.junit.Test;

import br.seb.dao.AlunoDAOBDR;
import br.seb.entidades.Aluno;
import junit.framework.TestCase;

public class TestAlunoDAOBDR extends TestCase {

	private AlunoDAOBDR alunoTest = new AlunoDAOBDR();

	@Test
	public void testCadastrar() {
		Aluno aluno = new Aluno();
		aluno.setMatriculaAluno(90);
		assertEquals(false, alunoTest.cadastrar(aluno));
	}

	@Test
	public void testCadastrarOK() {
		Aluno aluno = new Aluno();
		aluno.setMatriculaAluno(1);
		assertEquals(false, alunoTest.cadastrar(aluno));
	}
}
