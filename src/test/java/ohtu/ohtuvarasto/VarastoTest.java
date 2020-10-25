package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenVarasto() {
        var varasto = new Varasto(0.0);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoAlkuSaldolla() {
        var varasto = new Varasto(4.0, 2.5);
        assertEquals(4.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(2.5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenVarastoAlkuSaldolla() {
        var varasto = new Varasto(-1.0, 1.0);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(-1.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkuSaldo() {
        var varasto = new Varasto(2.0, -1);
        assertEquals(2.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianSuuriAlkuSaldo() {
        var varasto = new Varasto(2.0, 2.1);
        assertEquals(2.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(2.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisääNegatiivinenMäärä() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisääLiikaa() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaNegatiivinenMäärä() {
        varasto.lisaaVarastoon(2);
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void otaLiikaa() {
        varasto.lisaaVarastoon(3);
        assertEquals(3, varasto.otaVarastosta(4), vertailuTarkkuus);
    }

    @Test
    public void otaOsa() {
        varasto.lisaaVarastoon(3);
        assertEquals(2, varasto.otaVarastosta(2), vertailuTarkkuus);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsitysToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
        varasto.lisaaVarastoon(5.5);
        assertEquals("saldo = 5.5, vielä tilaa 4.5", varasto.toString());
    }
}