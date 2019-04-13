package aplicacao;

import org.junit.Test;

import br.seb.aplicacao.Aplicacao;
import junit.framework.TestCase;

public class TestAplicacao extends TestCase {
	
	private Aplicacao aplicacao = new Aplicacao();
	
	@SuppressWarnings("static-access")
	@Test
	public void testAplicacao() {
		String args[] = new String[1];
		aplicacao.main(args);
		assertNotNull(aplicacao);
	}
}