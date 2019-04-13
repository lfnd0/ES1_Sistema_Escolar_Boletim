package entidades;

import org.junit.Test;

import br.seb.entidades.Administrador;
import junit.framework.TestCase;

public class TestAdmistrador extends TestCase {

	@Test
	public void testAdministrador() {	
		
		Administrador adminstrador1 = new Administrador();
		Administrador adminstrador2 = new Administrador("nome", "dataNascimento");
		
		assertEquals("Administrador: Nome: null, data de nascimento: null", adminstrador1.toString());
		assertEquals("Administrador: Nome: nome, data de nascimento: dataNascimento", adminstrador2.toString());

	}
}