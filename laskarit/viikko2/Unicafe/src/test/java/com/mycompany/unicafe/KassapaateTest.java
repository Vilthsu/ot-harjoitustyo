package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti maksukortti;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(5 * 100);
    }

    @Test
    public void luotuKassapaateOlemassa() {
        assertTrue(kassapaate != null);
    }

    @Test
    public void luotuMaksukorttiOlemassa() {
        assertTrue(maksukortti != null);
    }

    @Test
    public void kassapaatteenSaldoOnOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kassapaatteenMyydytLounaatOnOikein() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassapaateVoiMyydaEdullisenLounaan() {
        assertEquals(110, kassapaate.syoEdullisesti(350));
        
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassapaateEiVoiMyydaEdullistaLounasta() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
        
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassapaateVoiMyydaMaukkaanLounaan() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
        
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassapaateEiVoiMyydaMaukastaLounasta() {
        assertEquals(350, kassapaate.syoMaukkaasti(350));

        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassapaateVoiMyydaEdullisenLounaanMaksukortilta() {
        assertTrue(kassapaate.syoEdullisesti(maksukortti));

        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassapaateEiVoiMyydaEdullistaLounastaMaksukortilta() {
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
        assertFalse(kassapaate.syoEdullisesti(maksukortti));

        assertEquals(2, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassapaateVoiMyydaMaukkaanLounaanMaksukortilta() {
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));

        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassapaateEiVoiMyydaMaukastaLounastaMaksukortilta() {
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));
        assertFalse(kassapaate.syoMaukkaasti(maksukortti));

        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassapaateVoiLadataRahaa() {
        kassapaate.lataaRahaaKortille(maksukortti, 1500);
        assertEquals(20 * 100, maksukortti.saldo());
    }

    @Test
    public void kassapaateVoiLadataRahaaJaKassapaatteenSaldoKasvaaOikein() {
        int maara = 1500;
        
        kassapaate.lataaRahaaKortille(maksukortti, maara);
        assertEquals(20 * 100, maksukortti.saldo());
        assertEquals(100000 + maara, kassapaate.kassassaRahaa());
    }

    @Test
    public void kassapaateEiVoiLadataRahaaNegatiivisellaArvolla() {
        int maara = -500;
        
        kassapaate.lataaRahaaKortille(maksukortti, maara);
        assertEquals(5 * 100, maksukortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
