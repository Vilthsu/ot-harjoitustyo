package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10 * 100); // 10 eur
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void kortinSaldoOnOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(15 * 100); // 15 eur
        assertEquals("saldo: 25.0", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(250); // 2.50 eur
        assertEquals(750, kortti.saldo());
        
        kortti.otaRahaa(10 * 100); // 10 eur
        assertEquals(750, kortti.saldo());
        
        // Kortilta otetaan rahaa 2.50
        assertTrue(kortti.otaRahaa(250));
        
        // Kortilta yritetään ottaa rahaa 10.00
        assertFalse(kortti.otaRahaa(10 * 100));
    }
}
