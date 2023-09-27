package esempi.esempio3;
import static org.junit.Assert.*;
import org.junit.Test;
public class TestBanca {
    @Test
    public void testApriConto() {
        Banca banca = new Banca();
        banca.apriConto("Carlo", 1000.0);
        assertNotNull(banca);
    }

    @Test
    public void testTrovaConto() {
        Banca banca = new Banca();
        banca.apriConto("Anna", 2000.0);
        ContoBancario conto = banca.trovaConto("Anna");
        assertNotNull(conto);
        assertEquals(2000.0, conto.getSaldo(), 0.01);//0.01 tolleranza
    }
}
