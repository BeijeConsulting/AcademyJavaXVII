package esempi.esempio3;
import static org.junit.Assert.*;
import org.junit.Test;
public class TestContoBancario {
	 @Test
	    public void testDeposita() {
	        ContoBancario conto = new ContoBancario("Mario", 1000.0);
	        conto.deposita(500.0);
	        assertEquals(1500.0, conto.getSaldo(), 0.01);
	    }

	    @Test
	    public void testPrelevaConSaldoSufficiente() {
	        ContoBancario conto = new ContoBancario("Luigi", 2000.0);
	        conto.preleva(1000.0);
	        assertEquals(1000.0, conto.getSaldo(), 0.01);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testPrelevaConSaldoInsufficiente() {
	        ContoBancario conto = new ContoBancario("Giovanni", 500.0);
	        conto.preleva(1000.0); // Dovrebbe lanciare un'eccezione
	    }
}
