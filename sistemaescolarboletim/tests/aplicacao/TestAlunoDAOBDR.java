package aplicacao;

import java.util.ArrayList;

import org.junit.Test;

import br.seb.dao.AlunoDAOBDR;
import br.seb.entidades.Aluno;
import junit.framework.TestCase;

public class TestAlunoDAOBDR extends TestCase {

	private AlunoDAOBDR alunoTest = new AlunoDAOBDR();

	@Test
	public void testCadastrarErroMatriculaNaoCosta() {
		Aluno aluno = new Aluno();
		aluno.setMatriculaAluno(90);
		assertEquals(false, alunoTest.cadastrar(aluno));
	}

	@Test
	public void testCadastrarErroAlunoJaCadastrado() {
		Aluno aluno = new Aluno();
		aluno.setMatriculaAluno(1);
		assertEquals(false, alunoTest.cadastrar(aluno));
	}
	
	@Test
	public void testCadastrarErroDadoIncorreto() {
		Aluno aluno = new Aluno();
		assertEquals(false, alunoTest.cadastrar(aluno));
	}
	
	@Test
	public void testCadastrarOK() {
		Aluno aluno = new Aluno();
		aluno.setLogin("paulo");
		aluno.setSenha("123");
		aluno.setMatriculaAluno(4);
		assertEquals(true, alunoTest.cadastrar(aluno));
	}
	
	@Test
	public void testLoginOK() {
		assertEquals(1, alunoTest.login("danillo", "123"));
	}
	
	@Test
	public void testLoginErrado1() {
		assertEquals(0, alunoTest.login("danilo", "55433"));
	}
	
	@Test
	public void testLoginErrado2() {
		assertEquals(0, alunoTest.login("danilo", "55433"));
	}
	
	@Test
	public void testListarNotasAlunoRetornoNull() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>(alunoTest.listarNotasAluno(12));
		assertNotNull("[]", alunos);
	}
	
	@Test
	public void testListarNotasAlunoRetorno1OK() {
		assertNotNull(alunoTest.listarNotasAluno(1));
	}
	
	@Test
	public void testListarNotasAlunoRetorno2OK() {
		assertNotNull(alunoTest.listarNotasAluno(2));
		System.out.println(alunoTest.listarNotasAluno(2));
	}
	
}
