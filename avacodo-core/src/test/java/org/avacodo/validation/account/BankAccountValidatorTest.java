/*
 * #%L
 * Avacodo
 * %%
 * Copyright (C) 2013 - 2015 infiniteam
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package org.avacodo.validation.account;
/**
 * testng test for BankAccountValidator
 * use test account numbers from Bundesbank check digit method documentation 
 * as far as they are provided
 * update this test whenever the documentation is updated (4 times a year)
 *
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.*;
import org.junit.Assert;
import org.junit.Test;

public class BankAccountValidatorTest {
    private static final LocalDate MARCH_6_2005;
    private static final LocalDate MARCH_7_2005;
    private static final LocalDate DEC_3_2006;
    private static final LocalDate DEC_4_2006;
    private static final LocalDate MARCH_8_2009;
    private static final LocalDate MARCH_9_2009;

    private static final LocalDate JUNE_6_2010;

    private static final LocalDate JUNE_7_2010;

    private static final LocalDate MARCH_6_2011;

    private static final LocalDate MARCH_7_2011;

    private static final LocalDate JUNE_5_2011;

    private static final LocalDate JUNE_6_2011;

    private static final LocalDate SEPT_4_2011;

    private static final LocalDate SEPT_5_2011;

    private static final LocalDate MARCH_4_2013;

    private static final LocalDate JUNE_3_2013;

    private static final LocalDate SEPT_9_2013;

    private static final LocalDate MARCH_3_2014;
    
    private static final LocalDate JUNE_9_2014;

    static {
        Calendar cal = new GregorianCalendar( 2005, Calendar.MARCH, 6 );
        cal.setTimeZone( TimeZone.getTimeZone( "CET" ) );
        MARCH_6_2005 = LocalDate.fromCalendarFields( cal );

        cal.set( Calendar.DAY_OF_MONTH, 7 );
        MARCH_7_2005 = LocalDate.fromCalendarFields( cal );

        cal.set( 2006, Calendar.DECEMBER, 4 );
        DEC_4_2006 = LocalDate.fromCalendarFields( cal );

        cal.set( Calendar.DAY_OF_MONTH, 3 );
        DEC_3_2006 = LocalDate.fromCalendarFields( cal );

        cal.set( 2009, Calendar.MARCH, 8 );
        MARCH_8_2009 = LocalDate.fromCalendarFields( cal );

        cal.set( Calendar.DAY_OF_MONTH, 9 );
        MARCH_9_2009 = LocalDate.fromCalendarFields( cal );

        cal.set( 2010, Calendar.JUNE, 6 );
        JUNE_6_2010 = LocalDate.fromCalendarFields( cal );

        cal.set( Calendar.DAY_OF_MONTH, 7 );
        JUNE_7_2010 = LocalDate.fromCalendarFields( cal );

        cal.set( 2011, Calendar.MARCH, 6 );
        MARCH_6_2011 = LocalDate.fromCalendarFields( cal );

        cal.set( Calendar.DAY_OF_MONTH, 7 );
        MARCH_7_2011 = LocalDate.fromCalendarFields( cal );

        cal.set( 2011, Calendar.JUNE, 5 );
        JUNE_5_2011 = LocalDate.fromCalendarFields( cal );

        cal.set( Calendar.DAY_OF_MONTH, 6 );
        JUNE_6_2011 = LocalDate.fromCalendarFields( cal );

        cal.set( 2011, Calendar.SEPTEMBER, 4 );
        SEPT_4_2011 = LocalDate.fromCalendarFields( cal );

        cal.set( Calendar.DAY_OF_MONTH, 5 );
        SEPT_5_2011 = LocalDate.fromCalendarFields( cal );

        cal.set( 2013, Calendar.MARCH, 4 );
        MARCH_4_2013 = LocalDate.fromCalendarFields( cal );

        cal.set( 2013, Calendar.JUNE, 3 );
        JUNE_3_2013 = LocalDate.fromCalendarFields( cal );

        cal.set( 2013, Calendar.SEPTEMBER, 9 );
        SEPT_9_2013 = LocalDate.fromCalendarFields( cal );

        cal.set( 2014, Calendar.MARCH, 3 );
        MARCH_3_2014 = LocalDate.fromCalendarFields( cal );
        
        cal.set( 2014, Calendar.JUNE, 9 );
        JUNE_9_2014 = LocalDate.fromCalendarFields( cal );
    }

    @Test
    public void testMethod00() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9290701L, "00", 0, new LocalDate()));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(539290858L, "00", 0, new LocalDate(0)));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501824L, "00", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501832L, "00", 0)); 
        // bad numbers, not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9290700L, "00", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(539290859L, "00", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501825L, "00", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501831L, "00", 0));
        // shown as valid example for method A1, error in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1010030054L, "0", 0)); 
        // out of range 
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(0L, "0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(123456789012345L, "0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(-9999999999L, "0"));
    }
    
    @Test
    public void testMethod01() {
        // account numbers from documentation of method B1
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7414398260L, "01", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8347251693L, "01", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(123456789L, "01", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2345678901L, "01", 0));
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5678901234L, "01", 0 ) );
        // from IBAN rule 0044 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2282022, "01", 0 ) );
    }
   
    @Test
    public void testMethod02() {
        // account numbers from documentation of method B2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(20012357L, "02", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(80012345L, "02", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(926801910L, "02", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1002345674L, "02", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(20012399L, "02", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012347L, "02", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012370L, "02", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(932100027L, "02", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3310123454L, "02", 0));
    }    
        
    @Test
    public void testMethod03() {
        // account numbers from documentation of method A7
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19010660L, "03", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19010876L, "03", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(209010892L, "03", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(209010893L, "03", 0));
        // additional bad numbers
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(19010661L, "03", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(19010875L, "03", 0));
    }
    
    @Test
    public void testMethod04() {
        // account numbers from documentation of method A2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3456789012L, "04", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567890L,"04", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(123456789L, "04", 0));
    }
    
    @Test
    public void testMethod05() {
        // account numbers from documentation of method B1
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1434253150L, "05", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2746315471L, "05", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7414398260L, "05", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8347251693L, "05", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5678901234L, "05", 0));
        
        // account number from documentation of method B5
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1151043211L, "05", 0));
    }
    
    @Test
    public void testMethod06() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(94012341L, "06", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5073321010L, "06", 0));
        // from IBAN rules, 0020 00 (Norisbank)
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1234517892, "06", 0 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 987614325L, "06", 0 ) );
        // bad numbers, not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(94012340L, "06", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5073321011L, "06", 0));
        // from documentation of method A4
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4711173L, "06", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7093330L, "06", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4711172L, "06", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8623420004L, "06", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1123458L, "06", 0));
    }
    
    @Test
    public void testMethod07() {
        // no examples in documentation
        // should be the same as method 02 for account numbers with less than 10 digits
        // account numbers from documentation of method B2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(20012357L, "07", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(80012345L, "07", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(926801910L, "07", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(20012399L, "07", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012347L, "07", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012370L, "07", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(932100027L, "07", 0));
    }
    
    @Test
    public void testMethod08() {
        // no examples in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1L, "08", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(59999L, "08", 0));
        // from documentation of method 00
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9290701L, "08", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(539290858L, "08", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501824L, "08", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501832L, "08", 0)); 
        // bad numbers, not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9290700L, "08", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(539290859L, "08", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501825L, "08", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501831L, "08", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(60000L, "08", 0));
        
    }
    
    @Test
    public void testMethod09() {
        // no checksum calculation
        // test minimum and maximum
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1L, "09", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9999999999L, "09", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(0L, "09", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(10000000000L, "09", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(-99L, "09", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(12356789012345L, "09", 0));
    }
    
    @Test
    public void testMethod10() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(12345008L, "10", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(87654008L, "10", 0));
        // invalid account numbers, not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(12345007L, "10", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(87654009L, "10", 0));
    }
    
    @Test
    public void testMethod11() {
        // no examples in documentation, test numbers of method 10
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(12345008L, "11", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(87654008L, "11", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(87654309L, "11", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(87654040L, "11", 0));
        // invalid account numbers, not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(12345007L, "11", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(87654009L, "11", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(88654300L, "11", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(87654049L, "11", 0));
    }
    
    // method 12 does not exist
    
    @Test
    public void testMethod13() {
        // no examples in documentation, test numbers adapted from method 00
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9290701L, "13", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(929070144L, "13", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9929070144L, "13", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501824L, "13", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501832L, "13", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(150182488L, "13", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(150183299L, "13", 0)); 
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1150182488L, "13", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2150183299L, "13", 0));
        // from IBAN rule 000500
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4217386, "13"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4217387, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(111198800, "13"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(111198700, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(420086100, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(421573704, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(421679200, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(130023500, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(104414, "13"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(104417, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(40050700, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(101337, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(10503101, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(52065002, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(930125001, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(930125000, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(930125006, "13"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(130023500, "13"));
        // real world example
        Assert.assertTrue( BankAccountValidator.checkAccountNumber(2002124584, "13", 0) );
        // bad numbers, not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(929070088L, "13", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5392908591L, "13", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(150182577L, "13", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(150183166L, "13", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1150182577L, "13", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2150183166L, "13", 0));  
    }
    
    @Test
    public void testMethod14() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(20012351L, "14", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(80012343L, "14", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(926801919L, "14", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1002345676L, "14", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(20021350L, "14", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(20012353L, "14", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012344L, "14", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(926801918L, "14", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1002345672L, "14", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(20012469L, "14", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(20021351L, "14", 0));
    }
    
    @Test
    public void testMethod15() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(94012343L, "15", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5073321016L, "15", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4711177L, "15", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7093335L, "15", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(94012340L, "15", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5073321011L, "15", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4711172L, "15", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7093334L, "15", 0));
    }
    
    @Test
    public void testMethod16() {
        // no test numbers in documentation
        // from documentation of method 06
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(94012341L, "16", 0));
        // adapted from documentation of method 06
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5073321011L, "16", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5073321010L, "16", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(94012340L, "16", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5073321012L, "16", 0));
        // from documentation of method A4
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4711173L, "16", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7093330L, "16", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4711172L, "16", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8623420004L, "16", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1123458L, "16", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(94012355L, "16", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(94012350L, "16", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(94012352L, "16", 0));
    }
    
    @Test
    public void testMethod17() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(446786040L, "17", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9446786049L, "17", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2312568140L, "17", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2312568940L, "17", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(446786343L, "17", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9446786143L, "17", 0));
    }
    
    @Test
    public void testMethod18() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(446786041L, "18", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567899L, "18", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1134567890L, "18", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(446786043L, "18", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567890L, "18", 0));
    }
    
    @Test
    public void testMethod19() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(240334000L, "19", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(200520016L, "19", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567898L, "19", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567890L, "19", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(240334001L, "19", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(200520015L, "19", 0));
        // from documentation of method B8
        // numbers with less than 10 digits should produce the same result as method 20
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(734192657L, "19", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(132572975L, "19", 0));
    }
    
    @Test
    public void testMethod20() {
        // from documentation of method B6
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9110000000L, "20", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9111000000L, "20", 0));
        // from documentation of method B8
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(734192657L, "20", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6932875274L, "20", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(132572975L, "20", 0));        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3145863029L, "20", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2938692523L, "20", 0));   
    }
    
    @Test
    public void testMethod21() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567893L, "21", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6932875274L, "21", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567894L, "21", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6932875272L, "21", 0));
    }
    
    @Test
    public void testMethod22() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567895L, "22", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6932875273L, "22", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567894L, "22", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6932875272L, "22", 0));
    } 
    
    @Test
    public void testMethod23() {
        // adapted from documentation of method A4
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4711173898L, "23", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7093330007L, "23", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4711172002L, "23", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1123458777L, "23", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(223455123L, "23", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(223450123L, "23", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(223454123L, "23", 0));
    }
    
    // Postbank
    @Test
    public void testMethod24() {
        // from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 138301, "24", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1306118605, "24", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3307118608L, "24", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9307118603L, "24", 0 ) );
        // others
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 138302, "24", 0 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1306118606, "24", 0 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3307118609L, "24", 0 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9307118604L, "24", 0 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9990138301L, "24", 0 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9387118603L, "24", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1336118616, "24", 0 ) );
    }
    
    @Test
    public void testMethod25() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(521382181L, "25", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1521382181L, "25", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(521382185L, "25", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(926821910L, "25", 0));
        // from documentation of B2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(20012357L, "25", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(80012345L, "25", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(926801910L, "25", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(20012399L, "25", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012347L, "25", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012370L, "25", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(932100027L, "25", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3310123454L, "25", 0));
    }
    
    @Test
    public void testMethod26() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(520309001L, "26", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111118111L, "26", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5501024L, "26", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(520309101L, "26", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1111118011L, "26", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5501924L, "26", 0));
    }
    
    @Test
    public void testMethod27() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2847169488L, "27", 0));
        // from documentation of method 0
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9290701L, "27", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(539290858L, "27", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501824L, "27", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501832L, "27", 0));        
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567895L, "27", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567896L, "27", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2847169487L, "27", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9290700L, "27", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(539290859L, "27", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501825L, "27", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501831L, "27", 0));
    }
    
    @Test
    public void testMethod28() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19999000L, "28", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9130000201L, "28", 0));  
        // adapted from documentation of method A4
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(471117300L, "28", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(709333099L, "28", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(471117245L, "28", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(112345800L, "28", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9401234288L, "28", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19999023L, "28", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(19999123L, "28", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9130000301L, "28", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9401234188L, "28", 0));
    }
    
    @Test
    public void testMethod29() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3145863029L, "29", 0));
         // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567895L, "29", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567896L, "29", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3145863028L, "29", 0));
    }
    
    @Test
    public void testMethod30() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567892L, "30", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000067892L, "30", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5678977885L, "30", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567893L, "30", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5678977886L, "30", 0));
    }
    
    @Test
    public void testMethod31() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000000524L, "31", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000000583L, "31", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(263160165L, "31", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000000525L, "31", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000000582L, "31", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(263160166L, "31", 0));
    }  
    
    @Test
    public void testMethod32() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9141405L, "32", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1709107983L, "32", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(122116979L, "32", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(121114867L, "32", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9030101192L, "32", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9245500460L, "32", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9141406L, "32", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1709107982L, "32", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(122116978L, "32", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(121114866L, "32", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9030101191L, "32", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9245500461L, "32", 0));
    }
    
    @Test
    public void testMethod33() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(48658L, "33", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(84956L, "33", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1324084956L, "33", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7891214450L, "33", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7891223450L, "33", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(48659L, "33", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(84955L, "33", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7891214451L, "33", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7891223451L, "33", 0));
    }  
    
    @Test
    public void testMethod34() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9913000700L, "34", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9914001000L, "34", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9913000712L, "34", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567112L, "34", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234568013L, "34", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9913000812L, "34", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9914001100L, "34", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234568113L, "34", 0));
    }
    
    @Test
    public void testMethod35() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(108443L, "35", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(107451L, "35", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(102921L, "35", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(102349L, "35", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(101709L, "35", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(101599L, "35", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1334567899L, "35", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(108442L, "35", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(107452L, "35", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(102920L, "35", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(102345L, "35", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(101708L, "35", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(101590L, "35", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1334567898L, "35", 0));
    }    
    
    @Test
    public void testMethod36() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(113178L, "36", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(146666L, "36", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234546666L, "36", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234546667L, "36", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(113177L, "36", 0));
    }
    
    @Test
    public void testMethod37() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(624315L, "37", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(632500L, "37", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7654632500L, "37", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7654632501L, "37", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(624314L, "37", 0));
    }
    
    @Test
    public void testMethod38() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(191919L, "38", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1100660L, "38", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567899L, "38", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(191918L, "38", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1100661L, "38", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567890L, "38", 0));
    }
    
    @Test
    public void testMethod39() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(200205L, "39", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(10019400L, "39", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567890L, "39", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(200206L, "39", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(10019401L, "39", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567899L, "39", 0));
    }
    
    @Test
    public void testMethod40() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1258345L, "40", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3231963L, "40", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4532319635L, "40", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4532319634L, "40", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1258342L, "40", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3231964L, "40", 0));
    }
    
    @Test
    public void testMethod41() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4013410024L, "41", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4016660195L, "41", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(166805317L, "41", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4019310079L, "41", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4019340829L, "41", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4019151002L, "41", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4119310079L, "41", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4113410024L, "41", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4116660195L, "41", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1166805317L, "41", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4019310078L, "41", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4019440829L, "41", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4019151003L, "41", 0));
    } 
    
    @Test
    public void testMethod42() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(59498L, "42", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(59510L, "42", 0));
        // from documentation of method 10
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(12345008L, "42", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(87654008L, "42", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(112345008L, "42", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(187654008L, "42", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(159498L, "42", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(159510L, "42", 0));
    }
    
    @Test
    public void testMethod43() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6135244L, "43", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9516893476L, "43", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9516895470L, "43", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9516895471L, "43", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(16135244L, "43", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9516893477L, "43", 0));
        
    }   
    
    @Test
    public void testMethod44() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(889006L, "44", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2618040504L, "44", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(889016L, "44", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2618040514L, "44", 0));
    }
    
    @Test
    public void testMethod45() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3545343232L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4013410024L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(994681254L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(12340L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000199999L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(100114240L, "45", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3545343233L, "45", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4013410025L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(994681255L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(12341L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000199998L, "45", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(100114241L, "45", 0));
    }
    
    @Test
    public void testMethod46() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(235468612L, "46", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(837890901L, "46", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1041447600L, "46", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1235468612L, "46", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(837890911L, "46", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1141447600L, "46", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(235468712L, "46", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(837890801L, "46", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1041447500L, "46", 0));
    }
    
    @Test
    public void testMethod47() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1018000L, "47", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1003554450, "47", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(111018001L, "47", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1003554451, "47", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1018010L, "47", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1003554460, "47", 0));
    }
    
    @Test
    public void testMethod48() {
        // no test numbers in documentation
        // from documentation of method 47
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1018000L, "48", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1003554450, "48", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1018010L, "48", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1003554460, "48", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(41018040L, "48", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1023554420, "48", 0));
    }
    
    @Test
    public void testMethod49() {
        // no test numbers in documentation
        // from documentation of method 0
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9290701L, "49", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(539290858L, "49", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501824L, "49", 0));
        // account numbers from documentation of method B1 (uses method 01)
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7414398260L, "49", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8347251693L, "49", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9290700L, "49", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(539290859L, "49", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501825L, "49", 0));

        Assert.assertFalse(BankAccountValidator.checkAccountNumber(123456789L, "49", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2345678901L, "49", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5678901234L, "49", 0)); 
    }
    
    @Test
    public void testMethod50() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4000005001L, "50",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4444442001L, "50",0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4000005111L, "50",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4444442111L, "50",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4444442L, "50",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4444450L, "50",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4000006001L, "50",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4444441001L, "50",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4444461L, "50",0));
    }

    @Test
    public void testMethod51_BeforeJune3_2013() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 1156071L, "51", 0, MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 1156136L, "51", 0, MARCH_4_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 156078L, "51", 0, MARCH_4_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 156071L, "51", 0, MARCH_4_2013 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 199100002L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 99100010L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2599100002L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 199100004L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2599100003L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3199204090L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 99345678L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 99100110, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 199100040L, "51", 0,
            MARCH_4_2013 ) );

        // others
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1156072L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1156137L, "51", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 156079L, "51", 0, MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 156077L, "51", 0, MARCH_4_2013 ) );
    }
    
    @Test
    public void testMethod51() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 1156071, "51", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 1156136, "51", 0, JUNE_3_2013 ) );        
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 1156078, "51", 0, JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 156079, "51", 0, JUNE_3_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 1234567, "51", 0, JUNE_3_2013 ) );
        // 1234566 is valid according to variant C
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 1234566, "51", 0, JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 12345678, "51", 0, JUNE_3_2013 ) );
        
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 340968, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 201178, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1009588, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 23456783, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 76543211, "51", 0,
            JUNE_3_2013 ) );
        
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 156071, "51", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 101345073, "51", 0, JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 123412345, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 67493647, "51", 0,
            JUNE_3_2013 ) );
        

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 199100002, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 99100010, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2599100002L, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 199100004, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2599100003L, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3199204090L, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 99345678L, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 99100110, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 199100040L, "51", 0,
            JUNE_3_2013 ) );

        // others
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 1156072L, "51", 0, JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 1156137L, "51", 0, JUNE_3_2013 ) );
        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 156077L, "51", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6000156071L, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7000156071L, "51", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9101345073L, "51", 0,
            JUNE_3_2013 ) );
    }

    @Test
    public void testMethod52() {
        // test number from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(43001500L, "52", 13051172));
        // test numbers from documentation of method C0
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(48726458L, "52", 13051172));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(82335729L, "52", 13051172));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(29837521L, "52", 13051172));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(48726458L, "52", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(48726458L, "52", -1));
        // from documentation of method B6
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9110000000L, "52", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9111000000L, "52", 0));
    }
    
    @Test
    public void testMethod53() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(382432256L, "53", 16052072));
        // from documentation of method B6
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(487310018L, "53", 80053782));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(467310018L, "53", 80053762));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(477310018L, "53", 80053772));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9110000000L, "53", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9111000000L, "53", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(477310018L, "53", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(477310018L, "53", -9));
    }
    
    @Test
    public void testMethod54() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4964137395L, "54", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4900010987L, "54", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4964137394L, "54", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4900010988L, "54", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4901010980L, "54", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5900010987L, "54", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4800010987L, "54", 0));
    }
    
    @Test
    public void testMethod55() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4444442L, "55"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567895L, "55"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4444443L, "55"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567896L, "55"));       
    }
    
    @Test
    public void testMethod56() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(290545005L, "56"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9718304037L, "56"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(290545004L, "56"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9718304030L, "56"));
    }
    
    @Test
    public void testMethod57_BeforeDec4_2006() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7500021766L, "57", 0, DEC_3_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9400001734L, "57", 0, DEC_3_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7800028282L, "57", 0, DEC_3_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8100244186L, "57", 0, DEC_3_2006));
         
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7777778800L, "57", 0, DEC_3_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5001050352L, "57", 0, DEC_3_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5045090090L, "57", 0, DEC_3_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1909700805L, "57", 0, DEC_3_2006));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7500121766L, "57", 0, DEC_3_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9410001734L, "57", 0, DEC_3_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7801028282L, "57", 0, DEC_3_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8111244186L, "57", 0, DEC_3_2006));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8888887890L, "57", 0, DEC_3_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9609700805L, "57", 0, DEC_3_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9109700805L, "57", 0, DEC_3_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9200000005L, "57", 0, DEC_3_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000000005L, "57", 0, DEC_3_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5100000005L, "57", 0, DEC_3_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8888770005L, "57", 0, DEC_3_2006));
    }
    
    @Test
    public void testMethod57_BeforeSept9_2013() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7500021766L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9400001734L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7800028282L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8100244186L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3251080371L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3891234567L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7777778800L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5001050352L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5045090090L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1909700805L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9322111030L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(185125434, "57", 0, DEC_4_2006));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5302707782L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6412121212L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1813499124L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2206735010L, "57", 0, DEC_4_2006));
       
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7500121766L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9410001734L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7801028282L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8111244186L, "57", 0, DEC_4_2006));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8888887890L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9619700805L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9109700805L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9200000005L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000000005L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5100000005L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8888770005L, "57", 0, DEC_4_2006));

        Assert.assertFalse(BankAccountValidator.checkAccountNumber(185125435L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(96089700805L, "57", 0, DEC_4_2006));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3101884999L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(112881230L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3112885000L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3100884999L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3113885000L, "57", 0, DEC_4_2006));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(12881230L, "57", 0, DEC_4_2006));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7432000000L, "57", 0, DEC_4_2006));
    }
    
    @Test
    public void testMethod57() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7500021766L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9400001734L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7800028282L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8100244186L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3251080371L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3891234567L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7777778800L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5001050352L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5045090090L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1909700805L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9322111030L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(185125434, "57", 0, SEPT_9_2013));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5302707782L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6412121212L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1813499124L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2206735010L, "57", 0, SEPT_9_2013));
       
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7500121766L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9410001734L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7801028282L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8111244186L, "57", 0, SEPT_9_2013));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8888887890L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9619700805L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9109700805L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9200000005L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000000005L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5100000005L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8888770005L, "57", 0, SEPT_9_2013));

        Assert.assertFalse(BankAccountValidator.checkAccountNumber(185125435L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(96089700805L, "57", 0, SEPT_9_2013));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3101884999L, "57", 0, SEPT_9_2013));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(112881230L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3112885000L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3100884999L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3113885000L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(12881230L, "57", 0, SEPT_9_2013));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7432000000L, "57", 0, SEPT_9_2013));
    }
        
    @Test
    public void testMethod58() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1800881120L, "58", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9200654108L, "58", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1015222224L, "58", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3703169668L, "58", 0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1801881120L, "58", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9210654108L, "58", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1115222224L, "58", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4703169668L, "58", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1800881121L, "58", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9200654109L, "58", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1015222223L, "58", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(37031696699L, "58", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1800881130L, "58", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(12343L, "58", 0));
    }
    
    @Test
    public void testMethod59() {
        // no test numbers in documentation
        // from documentation of method 00
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(539290858L, "59", 0));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(539290859L, "59", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9290700L, "59", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501831L, "59", 0));
    }   
    
    @Test
    public void testMethod60() {
        // no test numbers in documentation
        // from documentation of method 00
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9290701L, "60", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501824L, "60", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1501832L, "60", 0)); 
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1109290701L, "60", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(12345674L, "60", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1112345674L, "60", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1112345673L, "60", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9290700L, "60", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(539290859L, "60", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501825L, "60", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1501831L, "60", 0));
    }    
    
    @Test
    public void testMethod61() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2063099200L, "61"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(260760481L, "61"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2063099211L, "61"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2063099300L, "61"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(260760482L, "61"));
    }
    
    @Test
    public void testMethod62() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5029076701L, "62"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5129076711L, "62"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5029176701L, "62"));
    }

    @Test
    public void testMethod63() {
        // from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 123456600L, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1234566L, "63" ) );
        // from IBAN rules, 0020 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3500022, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 38150900, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 600103660, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 39101181, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 56002, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 752691, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5719083, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8007759, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 141590000, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 428790000, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 491551800, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 581448800, "63" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 380586800, "63" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 94012341, "63" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5073321010L, "63" ) );
        // others
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 123456611L, "63" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 123456700L, "63" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1234565L, "63" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2103456600L, "63" ) );
    }

    @Test
    public void testMethod64() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1206473010L, "64"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5016511020L, "64"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1206473101L, "64"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1216473010L, "64"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5116511020L, "64"));
    }
    
    @Test
    public void testMethod65() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567400L, "65"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567590L, "65"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567411L, "65"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567500L, "65"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567490L, "65"));
    }
    
    @Test
    public void testMethod66_BeforeMarch3_2014() {
        // from documentation
   	 Assert.assertTrue(BankAccountValidator.checkAccountNumber(100150502L, "66", 0, SEPT_9_2013 ));
       Assert.assertTrue(BankAccountValidator.checkAccountNumber(100154508L, "66", 0, SEPT_9_2013 ));
       Assert.assertTrue(BankAccountValidator.checkAccountNumber(101154508L, "66", 0, SEPT_9_2013 ));
       Assert.assertTrue(BankAccountValidator.checkAccountNumber(100154516L, "66", 0, SEPT_9_2013 ));
       Assert.assertTrue(BankAccountValidator.checkAccountNumber(101154516L, "66", 0, SEPT_9_2013 ));
       // others
       Assert.assertTrue(BankAccountValidator.checkAccountNumber(123457781L, "66", 0, SEPT_9_2013 ));
       Assert.assertTrue(BankAccountValidator.checkAccountNumber(123466780L, "66", 0, SEPT_9_2013 ));
       Assert.assertTrue(BankAccountValidator.checkAccountNumber(110150502L, "66", 0, SEPT_9_2013 ));
       Assert.assertFalse(BankAccountValidator.checkAccountNumber(100150501L, "66", 0, SEPT_9_2013 ));
       Assert.assertFalse(BankAccountValidator.checkAccountNumber(100154509L, "66", 0, SEPT_9_2013 ));
       Assert.assertFalse(BankAccountValidator.checkAccountNumber(101154507L, "66", 0, SEPT_9_2013 ));
       Assert.assertFalse(BankAccountValidator.checkAccountNumber(100154517L, "66", 0, SEPT_9_2013 ));
       Assert.assertFalse(BankAccountValidator.checkAccountNumber(101154515L, "66", 0, SEPT_9_2013 ));
       Assert.assertFalse(BankAccountValidator.checkAccountNumber(1101154516L, "66", 0, SEPT_9_2013 ));
       Assert.assertFalse(BankAccountValidator.checkAccountNumber(983393104, "66", 0, SEPT_9_2013 ));
    }

    @Test
    public void testMethod66() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(100150502L, "66", 0, MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(100154508L, "66", 0, MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(101154508L, "66", 0, MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(100154516L, "66", 0, MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(101154516L, "66", 0, MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(983393104, "66", 0, MARCH_3_2014 ));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123457781L, "66", 0, MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123466780L, "66", 0, MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(110150502L, "66", 0, MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(100150501L, "66", 0, MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(100154509L, "66", 0, MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(101154507L, "66", 0, MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(100154517L, "66", 0, MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(101154515L, "66", 0, MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1101154516L, "66", 0, MARCH_3_2014 ));
    }

    @Test
    public void testMethod67() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(929070188L, "67"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(150182477L, "67"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(150183266L, "67", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567411L, "67", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1929070188L, "67", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2150182477L, "67", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3150183266L, "67", 0)); 
    }
    
    @Test
    public void testMethod68() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8889654328L, "68"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(987654324L, "68"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(987654328L, "68"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8888664328L, "68"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8889654327L, "68"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(987654325L, "68"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(400000000L, "68"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(400000001L, "68"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(499999998L, "68"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(499999999L, "68"));
        
    }
    
    @Test
    public void testMethod69() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9721134869L, "69"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567900L, "69"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567006L, "69"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9721134868L, "69"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567800L, "69"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567007L, "69"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567911L, "69"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9300000000L, "69"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9300000001L, "69"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9399999998L, "69"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9399999999L, "69"));
    }
    
    @Test
    public void testMethod70() {
        // no test numbers in documentation
        // from documentation of method 06
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(94012341L, "70"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5073321010L, "70"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(94012340L, "70"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5073321011L, "70"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1235123453L, "70"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1236912346L, "70"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1245123453L, "70"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2236912346L, "70"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1235123454L, "70"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1236912347L, "70"));
    }
    
    @Test
    public void testMethod71() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7101234007L, "71"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8101234117L, "71"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7111234007L, "71"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7181234000L, "71"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7181235001L, "71"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7181236001L, "71"));
    }
    
    @Test
    public void testMethod72() {
        // no test numbers in documentation
        // adapted from method 00
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1119290701L, "72", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1129290701L, "72", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9871501824L, "72", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1231501832L, "72", 0)); 
        // bad numbers, not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1119290700L, "72", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9871501825L, "72", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1231501831L, "72", 0));
    }
    
    @Test
    public void testMethod73() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3503398L, "73"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1340967L, "73"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3503391L, "73"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1340968L, "73"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1340966L, "73"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3503392L, "73"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456L, "73"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(121212L, "73"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(987654321L, "73"));
        // from documentation of method 51
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100002L, "73",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100010L, "73",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100002L, "73",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100004L, "73",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100003L, "73",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199204090L, "73",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99345678L, "73",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100110, "73",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(199100040L, "73",0));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1113503398L, "73"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1113503399L, "73"));
    }    
    
    @Test
    public void testMethod74() {
        // from documentation before June 4, 2007
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(239314L, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(239319L, "74"));
        // from documentation of June 4, 2007
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1016L, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(26260L, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(242243, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(242248, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(18002113, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1821200043, "74"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1011, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(26265, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(18002118, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6160000024L, "74"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(239318L, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(239317L, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1111239314L, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1111239319L, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(539290858L, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(18L, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(854807, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(854802, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(854505, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(854500, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(144113, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(144118, "74"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(770680, "74"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(0, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9, "74", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(854808, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(854801, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(242244, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(854501, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(854506, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(144119, "74"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(144114, "74"));
    }
    
    @Test
    public void testMethod75() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(239319L, "75"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1239319L, "75"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(239319888L, "75"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(923931977L, "75"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1239318888L, "75"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(11239319L, "75"));
        // the documentation says 6, 7 or 9 digits, www.pruefziffer.de allows
        // this 5 digit number: 39313
        // not allowed in ATOS, BLZ 20050000, HSH-Nordbank 
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(39313L, "75"));
    }
    
    @Test
    public void testMethod76() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(12345600L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6543200L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9012345600L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7876543100L, "76"));
        // from IBAN rule 0005 00
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(732502200, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7325022, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8732502200L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4820379900L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1814706100, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2814706100L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3814706100L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4814706100L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5814706100L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6814706100L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7814706100L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8814706100L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9814706100L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(23165400, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(231654, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4350300, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(43503, "76"));        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(526400, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(998761700L, "76"));      
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(43434280, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(343428000, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99021000, "76"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(112345600L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(526500, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(726400, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3123456L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(96543201L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9012345700L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7876543200L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7876543111L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5876543100L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3876543100L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2876543100L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1876543100L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2213456L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(19145837L, "76"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(49145837L, "76"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4327L, "76"));
    }    
       
    @Test
    public void testMethod77() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(47678L, "77"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(47671L, "77"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(10338L, "77"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(13844L, "77"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(65354L, "77"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(69258L, "77"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111169258L, "77"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(11338L, "77"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(13845L, "77"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(75354L, "77"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(59258L, "77"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(47672L, "77"));
    }
    
    @Test
    public void testMethod78() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7581499L, "78"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9999999981L, "78"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(17581499L, "78"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7581498L, "78"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8999999981L, "78"));
    }
    
    @Test
    public void testMethod79() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3230012688L, "79"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4230028872L, "79"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5440001898L, "79"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6330001063L, "79"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7000149349L, "79"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8000003577L, "79"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1550167850L, "79"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9011200140L, "79"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3231012688L, "79"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4230128872L, "79"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5440101898L, "79"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6330101063L, "79"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7100149349L, "79"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8000013577L, "79"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1551167850L, "79"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9111200140L, "79"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(539290858L, "79"));
    }    
        
    @Test
    public void testMethod80() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(340968L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(340966L, "80"));
        // from documentation of method 73
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3503391L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1340968L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1340966L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3503392L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456L, "80"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(121212L, "80"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(987654321L, "80"));
        // from documentation of method 51
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100002L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100010L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100002L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100004L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100003L, "80"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199204090L, "80"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99345678L, "80"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100110, "80"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(199100040L, "80"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1113503391L, "80"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1113503399L, "80"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3503398L, "80"));
    }
    
    @Test
    public void testMethod81() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(646440L, "81"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1359100L, "81"));
        // from documentation of method 51
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100002L, "81"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100010L, "81"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100002L, "81"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100004L, "81"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100003L, "81"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199204090L, "81"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99345678L, "81"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100110, "81"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(199100040L, "81"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1110646440L, "81"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1646440L, "81"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1359101L, "81"));
    }
    
    @Test
    public void testMethod82() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123897L, "82"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199500501L, "82"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111123897L, "82"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(123896L, "82"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3199500502L, "82"));
    }
    
    @Test
    public void testMethod83() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1156071L, "83"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1156136L, "83"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(156078L, "83"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(156071L, "83"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100002L, "83"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(156072L, "83"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(156077L, "83"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111156136L, "83"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100003L, "83"));
    }
    
    @Test
    public void testMethod84_BeforeJune3_2013() {
        // from documentation
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 100005L, "84", 0, MARCH_4_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 393814L, "84", 0, MARCH_4_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 950360L, "84", 0, MARCH_4_2013 ) );
        // from documentation of method 51
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 199100002L, "84", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 99100010L, "84", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2599100002L, "84", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 199100004L, "84", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2599100003L, "84", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3199204090L, "84", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 99345678L, "84", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 99100110, "84", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 199100040L, "84", 0,
            MARCH_4_2013 ) );
        // others
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 393815L, "84", 0, MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 950361L, "84", 0, MARCH_4_2013 ) );
    }
    
    @Test
    public void testMethod84() {
        // from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 240699, "84", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 350982, "84", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 461059, "84", 0, JUNE_3_2013 ) );
        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 240965, "84", 0, JUNE_3_2013 ) );
        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 350980, "84", 0, JUNE_3_2013 ) );
        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 461053, "84", 0, JUNE_3_2013 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 240692, "84", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 350985, "84", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 461052, "84", 0, JUNE_3_2013 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 240961, "84", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 350984, "84", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 461054, "84", 0, JUNE_3_2013 ) );

        // from documentation of method 51
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 199100002L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 99100010L, "84", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2599100002L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 199100004L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2599100003L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3199204090L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 99345678L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 99100110, "84", 0, JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 199100040L, "84", 0,
            JUNE_3_2013 ) );
        // others
        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 393815L, "84", 0, JUNE_3_2013 ) );
        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 950361L, "84", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1234240961L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9876350984L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8888461054L, "84", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1240961, "84", 0, JUNE_3_2013 ) );
    }

    @Test
    public void testMethod85() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1156071L, "85"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1156136L, "85"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(156078L, "85"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(156071L, "85"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199100002L, "85"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(156072L, "85"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(156077L, "85"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111156136L, "85"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3199100003L, "85"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199100010L, "85"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3199100100L, "85"));
    }
    
    @Test
    public void testMethod86() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(340968L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1001171L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1009588L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123897L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(340960L, "86"));
        // from documentation of method 51
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100002L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100010L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100002L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100004L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100003L, "86"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199204090L, "86"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99345678L, "86"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100110, "86"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(199100040L, "86"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111009588L, "86"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1019588L, "86"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1340968L, "86"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1101171L, "86"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1123897L, "86"));
    }
    
    @Test
    public void testMethod87() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(406L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(51768L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(10701590L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(10720185L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(100005L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(393814L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(950360L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199500501L, "87"));
        // from documentation of method 51
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100002L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100010L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100002L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100004L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100003L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199204090L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99345678L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100110, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(199100040L, "87"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2406L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(151768L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(11701590L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(10721185L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(110005L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(393815L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(951360L, "87"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3199500502L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111393814L, "87"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1110701590L, "87"));
    }
    
    @Test
    public void testMethod88() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2525259L, "88"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000500L, "88"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(90013000L, "88"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(92525253L, "88"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99913003L, "88"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1112525259L, "88"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2525258L, "88"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1100500L, "88"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1190013000L, "88"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(90113000L, "88"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(92525252L, "88"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99913013L, "88"));
    }
    
    @Test
    public void testMethod89() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1098506L, "89"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(32028008L, "89"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(218433000L, "89"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1218433000L, "89"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1218433001L, "89"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(11098506L, "89"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1198506L, "89"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(32028018L, "89"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(218433100L, "89"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456L, "89"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(12345L, "89"));
    }
    
    @Test
    public void testMethod90_BeforeJune9_2014() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1975641L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1988654L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1924592L, "90", 0,
           MARCH_3_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(863530L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(784451L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(997664L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(863536L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(901568L, "90", 0,
           MARCH_3_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(654321L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(824491L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(820484L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(654328L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(820487L, "90", 0,
           MARCH_3_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(677747L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(840507L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(677742L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(726391L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(726393L, "90", 0,
           MARCH_3_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(996663L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(666034L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(924591L, "90", 0,
           MARCH_3_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100002L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100007L, "90", 0,
           MARCH_3_2014 ));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111975641L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111863530L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111654321L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111840507L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111996663L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1199100002L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1199111110L, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1199111120L, "90", 0,
           MARCH_3_2014 ));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(726390, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4923250, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3865960, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3865964, "90", 0,
           MARCH_3_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(465431, "90", 0,
      	  MARCH_3_2014 ));
    }

    @Test
    public void testMethod90() {
   	  // all account numbers that were valid before should still be valid, additionally some more
    	  // account numbers should be valid according to the new "method G"
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1975641L, "90", 0,
           JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1988654L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1924592L, "90", 0,
      	  JUNE_9_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1863530L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1784451L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(997664L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(863536L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(901568L, "90", 0,
      	  JUNE_9_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(654321L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(824491L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(820484L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(654328L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(820487L, "90", 0,
      	  JUNE_9_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(677747L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(840507L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(677742L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(726391L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(726393L, "90", 0,
      	  JUNE_9_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(996663L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(666034L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(924591L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(465431, "90", 0,
      	  JUNE_9_2014 ));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100002L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100007L, "90", 0,
      	  JUNE_9_2014 ));

        Assert.assertTrue(BankAccountValidator.checkAccountNumber(726390, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4923250, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3865960, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3865964, "90", 0,
      	  JUNE_9_2014 ));

        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111975641L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111863530L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111654321L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111840507L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111996663L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1199100002L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1199111110L, "90", 0,
      	  JUNE_9_2014 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1199111120L, "90", 0,
      	  JUNE_9_2014 ));                
    }
    
    @Test
    public void testMethod91() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2974118000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5281741000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9952810000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840017000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840023000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840041000L, "91"));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2974117000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5281770000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9952812000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840014000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840026000L, "91"));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8840019000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8840050000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8840087000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8840045000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840011000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840025000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840062000L, "91"));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8840012000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8840055000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8840080000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840010000L, "91"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234562111L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234563111L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8840012111L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8841112000L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8840019111L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8841119000L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2974118111L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2974117111L, "91"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234560789L, "91"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567789L, "91"));
    }
    
    @Test
    public void testMethod92() {
        // no test numbers in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234569L, "92"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1111234569L, "92"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7414398268L, "92"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1111234568L, "92"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7414398260L, "92"));
    }    
        
    @Test
    public void testMethod93() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6714790000L, "93", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(671479L, "93", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1277830000L, "93", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(127783L, "93", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1277910000L, "93", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(127791L, "93", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3067540000L, "93", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(306754L, "93", 0));
        // bad number from documentation of method A4, Variante 1
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8623420004L, "93", 0));
        // bad number from documentation of method A4, Variante 2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8623420000L, "93", 0));
        // bad numbers, not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6714780000L, "93", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(671477L, "93", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(127782000L, "93", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(127784L, "93", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1277900000L, "93", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(127792L, "93", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3067550000L, "93", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(0000306753L, "93", 0));
    }
    
    @Test
    public void testMethod94() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6782533003L, "94", 0));
        //others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6782533110L, "94", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6782533013L, "94", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567890L, "94", 0));
    }
    
    @Test
    public void testMethod95_BeforeSept9_2013() {
        // from documentation
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 68007003L, "95", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 847321750L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6450060494L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6454000003L, "95", 0,
            JUNE_3_2013 ) );
        // from IBAN rule 0031 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1210100047, "95", 0,
            JUNE_3_2013 ) );
        // from IBAN rule 0033 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2950219435L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1457032621, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 897, "95", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5803435253L, "95", 0,
            JUNE_3_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 39908140, "95", 0, JUNE_3_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 2711931, "95", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5800522694L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5801800000L, "95", 0,
            JUNE_3_2013 ) );
        // others
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 68007013L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1847321750L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6450160494L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6454001003L, "95", 0,
            JUNE_3_2013 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1L, "95", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2L, "95", 0, JUNE_3_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 1999998L, "95", 0, JUNE_3_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 1999999L, "95", 0, JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 2000000L, "95", 0, JUNE_3_2013 ) );

        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 8999999L, "95", 0, JUNE_3_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 9000000L, "95", 0, JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 25999999L, "95", 0, JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 26000000L, "95", 0,
            JUNE_3_2013 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 395999999L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 396000000L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 499999999L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 500000000L, "95", 0,
            JUNE_3_2013 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 699999999L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 700000000L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 799999999L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 800000000L, "95", 0,
            JUNE_3_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 987614325L, "95", 0,
            JUNE_3_2013 ) );
    }

    @Test
    public void testMethod95() {
        // from documentation
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 68007003L, "95", 0, SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 847321750L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6450060494L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6454000003L, "95", 0,
            SEPT_9_2013 ) );
        // from IBAN rule 0031 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1210100047, "95", 0,
            SEPT_9_2013 ) );
        // from IBAN rule 0033 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2950219435L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1457032621, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 897, "95", 0, SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5803435253L, "95", 0,
            SEPT_9_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 39908140, "95", 0, SEPT_9_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 2711931, "95", 0, SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5800522694L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5801800000L, "95", 0,
            SEPT_9_2013 ) );
        // others
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 68007013L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1847321750L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6450160494L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6454001003L, "95", 0,
            SEPT_9_2013 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1L, "95", 0, SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2L, "95", 0, SEPT_9_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 1999998L, "95", 0, SEPT_9_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 1999999L, "95", 0, SEPT_9_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 2000000L, "95", 0, SEPT_9_2013 ) );

        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 8999999L, "95", 0, SEPT_9_2013 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 9000000L, "95", 0, SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 25999999L, "95", 0, SEPT_9_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 26000000L, "95", 0,
            SEPT_9_2013 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 395999999L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 396000000L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 499999999L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 500000000L, "95", 0,
            SEPT_9_2013 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 699999999L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 700000000L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 799999999L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 800000000L, "95", 0,
            SEPT_9_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 987614325L, "95", 0,
            SEPT_9_2013 ) );
    }

    @Test
    public void testMethod96() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(254100L, "96"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9421000009L, "96"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(208L, "96"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(101115152L, "96"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(301204301L, "96"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1299998L, "96"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1300000L, "96"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99399999L, "96"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99400000L, "96"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1111254100L, "96"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9421000019L, "96"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000000208L, "96"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1101115152L, "96"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1301204301L, "96"));        
    }
    
    @Test
    public void testMethod97() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(24010019L, "97"));
        // others
        /* 1210 is a valid number for BLZ 50320200 in ATOS and at www.pruefziffer.de
           however the documentation states that account numbers are 5 to 10 digits long
        */
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1210L, "97"));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567895L, "97"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567894L, "97"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567830L, "97"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567831L, "97"));     
    }
    
    @Test
    public void testMethod98() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9619608118L, "98"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9619439213L, "98"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9619509976L, "98"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9619319999L, "98"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3009800016L, "98"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5989800173L, "98"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6719430018L, "98"));
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9619618118L, "98"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9619439214L, "98"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9619519976L, "98"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9619319998L, "98"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3009800116L, "98"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(598981173L, "98"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6719431018L, "98"));
    }    
    
    @Test
    public void testMethod99() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(68007003L, "99", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(847321750L, "99", 0));
        // from IBAN rule 0031 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6790149813L, "99", 0 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6791000000L, "99", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1210100047, "99", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1457032621, "99", 0 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1210100040, "99", 0 ) );
        // from IBAN rule 0032 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3200000012L, "99", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5951950, "99", 0 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 897, "99", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 847321750, "99", 0 ) );
        // from IBAN rule 0034 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1320815432, "99", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4340111112L, "99", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4340118001L, "99", 0 ) );
        // from IBAN rule 0035 00
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1050958422, "99", 0 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1490196966, "99", 0 ) );
        // unchecked numbers
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(396000000L, "99", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(398765431L, "99", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(499999999L, "99", 0));
        // bad numbers
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(395999999L, "99", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(500000000L, "99", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(94012342L, "99", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5073321011L, "99", 0));
        // from documentation of method 06
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(94012341L, "99", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5073321010L, "99", 0));
    }
    
    @Test
    public void testMethodA0() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(521003287L, "A0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(54500L, "A0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3287L, "A0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(18761L, "A0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(28290L, "A0", 0));
        // unchecked number
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(100L, "A0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(999L, "A0", 0));
        // bad numbers
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99L, "A0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000L, "A0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(521003286L, "A0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(54501L, "A0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3288L, "A0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(18760L, "A0", 0));
    }
    
    @Test
    public void testMethodA1() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(10030005L, "A1", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(10030997L, "A1", 0));
        // not in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1010030052L, "A1", 0));
        // error in documentation, shown as valid example
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1010030054L, "A1", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(110030005L, "A1", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(10030998L, "A1", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(30005L, "A1", 0));
        // bad number
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9999999L, "A1", 0));

    }
    
    @Test
    public void testMethodA2() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3456789019L, "A2", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5678901231L, "A2", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6789012348L, "A2", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3456789012L, "A2", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567890L,"A2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(123456789L, "A2", 0));
        // other bad numbers
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3456789018L, "A2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6789012347L, "A2", 0));
    }
    
    @Test
    public void testMethodA3() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567897L, "A3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456782L, "A3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567890L, "A3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456789L, "A3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9876543210L, "A3", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6543217890L, "A3", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(543216789L, "A3", 0));
    } 
    
    @Test
    public void testMethodA4() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4711173L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7093330L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4711172L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7093335L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1199503010L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8499421235L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(862342L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8997710000L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(664040000L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(905844L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5030101099L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1123458L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1299503117L, "A4", 0));
        
        // valid with method 93, Variante 2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8623420004L, "A4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8623420000L, "A4", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6099702031L, "A4", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(399443L, "A4", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(553313L, "A4", 0));
    }
    
    @Test
    public void testMethodA5() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9941510001L, "A5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9961230019L, "A5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9380027210L, "A5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9932290910L, "A5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(251437L, "A5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7948344L, "A5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(159590L, "A5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(51640L, "A5", 0));

        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9941510002L, "A5", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9961230020L, "A5", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(251438L, "A5", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7948345L, "A5", 0));
    }
    
    @Test
    public void testMethodA6() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(800048548L, "A6", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(855000014L, "A6", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(17L, "A6", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(55300030L, "A6", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(150178033L, "A6", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(600003555L, "A6", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(900291823L, "A6", 0));

        Assert.assertFalse(BankAccountValidator.checkAccountNumber(860000817L, "A6", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(810033652, "A6", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(305888L, "A6", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(200071280L, "A6", 0));
    }
    
    @Test
    public void testMethodA7() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19010008L, "A7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19010438L, "A7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19010660L, "A7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19010876L, "A7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(209010892L, "A7", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(209010893L, "A7", 0));
        // additional bad numbers
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(19010007L, "A7", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(19010877L, "A7", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(19010661L, "A7", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(19010437L, "A7", 0));
    }
    
    @Test
    public void testMethodA8_before_March7_2005() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7436661L, "A8", 0, MARCH_6_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7436670L, "A8", 0, MARCH_6_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199500501L, "A8", 0, MARCH_6_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7436660L, "A8", 0, MARCH_6_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7436678L, "A8", 0, MARCH_6_2005));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7436666L, "A8", 0, MARCH_6_2005));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7436677L, "A8", 0, MARCH_6_2005));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3199500502L, "A8", 0, MARCH_6_2005));
    }
    
    @Test
    public void testMethodA8() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7436661L, "A8", 0, MARCH_7_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7436670L, "A8", 0, MARCH_7_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1359100L, "A8", 0, MARCH_7_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7436660L, "A8", 0, MARCH_7_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7436678L, "A8", 0, MARCH_7_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3503398, "A8", 0, MARCH_7_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1340967, "A8", 0, MARCH_7_2005));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7436666L, "A8", 0, MARCH_7_2005));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7436677L, "A8", 0, MARCH_7_2005));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3503391L, "A8", 0, MARCH_7_2005));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1340966, "A8", 0, MARCH_7_2005));
        // from documentation of method 51, "Ausnahme"
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100002L, "A8",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(99100010L, "A8",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100002L, "A8",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(199100004L, "A8",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2599100003L, "A8",0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3199204090L, "A8",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99345678L, "A8",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(99100110, "A8",0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(199100040L, "A8",0));
        
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1231359100L, "A8", 0, MARCH_7_2005));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4567436660L, "A8", 0, MARCH_7_2005));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1237436677L, "A8", 0, MARCH_7_2005));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4563503391L, "A8", 0, MARCH_7_2005));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3832806, "A8", 0, MARCH_7_2005));
    }
    
    @Test
    public void testMethodA9() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5043608L, "A9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(86725L, "A9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(504360L, "A9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(822035L, "A9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(32577083L, "A9", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(86724L, "A9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(292497L, "A9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(30767208L, "A9", 0));
    }
    
    @Test
    public void testMethodB0() {
        // from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1197423162L, "B0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000000606L, "B0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000000406L, "B0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1035791538L, "B0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1126939724L, "B0", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1197423460L, "B0", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8137423260L, "B0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(600000606L, "B0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(51234309L, "B0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000000405L, "B0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1035791539L, "B0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8035791532L, "B0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(535791830L, "B0", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(51234901L, "B0", 0));
    }    
    
    @Test
    public void testMethodB1() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1434253150L, "B1", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2746315471L, "B1", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7414398260L, "B1", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8347251693L, "B1", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(123456789L, "B1", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2345678901L, "B1", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5678901234L, "B1", 0)); 
    }
    
    @Test
    public void testMethodB2() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(20012357L, "B2", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(80012345L, "B2", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(926801910L, "B2", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1002345674L, "B2", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8000990054L, "B2", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000481805L, "B2", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(20012399L, "B2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012347L, "B2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(80012370L, "B2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(932100027L, "B2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3310123454L, "B2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8000990057L, "B2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8011000126L, "B2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000481800L, "B2", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9980480111L, "B2", 0));
    }
    
    @Test
    public void testMethodB3() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000000060L, "B3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(140L, "B3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19L, "B3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1002798417L, "B3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8409915001L, "B3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9635000101L, "B3", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9730200100L, "B3", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2799899L, "B3", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000000111L, "B3", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9635100101L, "B3", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9730300100L, "B3", 0));
    }
    
    @Test
    public void testMethodB4() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9941510001L, "B4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9961230019L, "B4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9380027210L, "B4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9932290910L, "B4", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9941510002L, "B4", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9961230020L, "B4", 0));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(251437L, "B4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7948344L, "B4", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(51640L, "B4", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(251438L, "B4", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7948345L, "B4", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(159590L, "B4", 0));
    }   
    
    @Test
    public void testMethodB5() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(159006955L, "B5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2000123451L, "B5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1151043216L, "B5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000939033L, "B5", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7414398260L, "B5", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8347251693L, "B5", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2345678901L, "B5", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000293707L, "B5", 0));
        
        // false for Variante 1 but true for Variante 2 (method 0)
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1151043211L, "B5", 0));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456782L, "B5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(130098767L, "B5", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1045000252L, "B5", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(159004165L, "B5", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(23456787L, "B5", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(56789018L, "B5", 0));
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3045000333L, "B5", 0 ) );
    }

    @Test
    public void testMethodB6_BeforeSept5_2011() {
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9110000000L, "B6", 0,
            SEPT_4_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9111000000L, "B6", 0,
            SEPT_4_2011 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 487310018L, "B6", 80053782,
            SEPT_4_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 467310018L, "B6", 80053762,
            SEPT_4_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 477310018L, "B6", 80053772,
            SEPT_4_2011 ) );
    }

    @Test
    public void testMethodB6() {
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9110000000L, "B6", 0,
            SEPT_5_2011 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 269876545, "B6", 0, SEPT_5_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9111000000L, "B6", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 269456780L, "B6", 0,
            SEPT_5_2011 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 487310018, "B6", 80053782,
            SEPT_5_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 467310018, "B6", 80053762,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 477310018, "B6", 80053772,
            SEPT_5_2011 ) );
    }

    @Test
    public void testMethodB7() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(700001529L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(730000019L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1001008L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1057887L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1007222L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(810011825L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(800107653L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5922372L, "B7", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1057886L, "B7", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3815570L, "B7", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5620516L, "B7", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(740912243L, "B7", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(893524479L, "B7", 0));
        
        // without check digit, not in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(911L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(999999L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(699999998L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(699999999L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(900000000L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(900000001L, "B7", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567890L, "B7", 0));
    }
    
    @Test
    public void testMethodB8_BeforeJune6_2011() {
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(734192657L, "B8", 0, JUNE_5_2011));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6932875274L, "B8", 0, JUNE_5_2011));
        
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 132572975L, "B8", 0,
            JUNE_5_2011 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3145863029L, "B8", 0,
            JUNE_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2938692523L, "B8", 0,
            JUNE_5_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5100000000L, "B8", 0,
            JUNE_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5999999998L, "B8", 0,
            JUNE_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9010000000L, "B8", 0,
            JUNE_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9109999999L, "B8", 0,
            JUNE_5_2011 ) );
    }

    @Test
    public void testMethodB8_FromJune6_2011() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 734192657, "B8", 0, JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6932875274L, "B8", 0,
            JUNE_6_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 132572975, "B8", 0,
            JUNE_6_2011 ) );
        // valid according to method 29
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5011654366L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000412340L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9310305011L, "B8", 0,
            JUNE_6_2011 ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3145863029L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2938692523L, "B8", 0,
            JUNE_6_2011 ) );

        // without check digit
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5432198760L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9070873333L, "B8", 0,
            JUNE_6_2011 ) );
        // without check digit - not in documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5100000000L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5999999999L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9010000000L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9109999999L, "B8", 0,
            JUNE_6_2011 ) );
        // others
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5099999999L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6000000000L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9009999999L, "B8", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9110000001L, "B8", 0,
            JUNE_6_2011 ) );
    }

    @Test
    public void testMethodB9() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(87920187L, "B9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(41203755L, "B9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(81069577L, "B9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(61287958L, "B9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(58467232L, "B9", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(88034023L, "B9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(43025432L, "B9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(86521362L, "B9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(61256523L, "B9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(54352684L, "B9", 0));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7125633L, "B9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1253657L, "B9", 0));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4353631L, "B9", 0));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2356412L, "B9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5435886L, "B9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9435414L, "B9", 0));
        
        // not in documentation
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(988034023L, "B9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(803423L, "B9", 0));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8803L, "B9", 0));
    }    
    
    @Test
    public void testMethodC0() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(43001500L, "C0", 13051172));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(48726458L, "C0", 13051172));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(29837521L, "C0", 13051172));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(82335729L, "C0", 13051172));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(734192657L, "C0", 13051172));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6932875274L, "C0", 13051172));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(132572975L, "C0", 13051172));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3038752371L, "C0", 13051172));
        
    }
    
    @Test
    public void testMethodC1() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(446786040L, "C1"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(478046940L, "C1"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(701625830L, "C1"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(701625840L, "C1"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(882095630L, "C1"));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5432112349L, "C1"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5543223456L, "C1"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5654334563L, "C1"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5765445670L, "C1"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5876556788L, "C1"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(446786240L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(478046340L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(701625730L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(701625440L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(882095130L, "C1"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5432112341L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5543223458L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5654334565L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5765445672L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5876556780L, "C1"));
        // others
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5432112330L, "C1"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5432112339L, "C1"));
    }
    
    @Test
    public void testMethodC2() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5127485166L, "C2"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8738142564L, "C2"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(328705282L, "C2"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9024675131L, "C2"));
    }
    
    @Test
    public void testMethodC3() {
        // test numbers from documentation - Variante 1
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9294182, "C3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4431276, "C3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19919, "C3"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(17002, "C3"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(123451, "C3"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(122448, "C3"));
        // Variante 2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000420530L, "C3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000010006L, "C3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000577650L, "C3"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000734028L, "C3"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000733227L, "C3"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000731120L, "C3"));
        // not in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8009294185L, "C3"));
    }
    
    @Test
    public void testMethodC4() {
        // test numbers from documentation - Variante 1
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(19, "C4"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(292932, "C4"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(94455, "C4"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(17, "C4"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(292933, "C4"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(94459, "C4"));
        // Variante 2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000420530L, "C4"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000010006L, "C4"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000577650L, "C4"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000726558L, "C4"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9001733457L, "C4"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000732000L, "C4"));
        //  not in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8000292932L, "C4"));
    }
    
    @Test
    public void testMethodC5() {
        // test numbers from documentation - Variante 1
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(301168, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(302554, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(302589, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(507336, "C5"));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(300020050, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(300566000, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(302555000, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(302589000, "C5"));
        // Variante 2
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000061378, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000061412, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4450164064L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4863476104L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5000000028L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5000000391L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6450008149L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6800001016L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000100012L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000210017L, "C5"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000061457, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000061498, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4864446015L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4865038012L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5000001028L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5000001075L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6450008150L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6542812818L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000110012L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000300310L, "C5"));
        // Variante 3
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3060188103L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3070402023L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3081000783L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3081308871L, "C5"));
        
        // not in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7000000000L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7099999999L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7100000000L, "C5"));
        
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8500000000L, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8599999999L, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(8600000000L, "C5"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(29999999, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(30000000, "C5"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(59999999, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(60000000, "C5"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(18, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(10009, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(900001, "C5"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(923931977L, "C5"));        
    }
    
    @Test
    public void testMethodC6_beforeMarch9_2009() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7008199027L, "C6", 0, MARCH_8_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7002000023L, "C6", 0, MARCH_8_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7000202027L, "C6", 0, MARCH_8_2009 ));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7000062022L, "C6", 0, MARCH_8_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7006003027L, "C6", 0, MARCH_8_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7003306026L, "C6", 0, MARCH_8_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7001501029L, "C6", 0, MARCH_8_2009 ));
        // not in documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(17, "C6", 0, MARCH_8_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(18, "C6", 0, MARCH_8_2009 ));
    }
    
    @Test
    public void testMethodC6_March9_2009_June6_2010() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7000005024L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7008199027L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7002000023L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(7000202027L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000430223L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(9000781153L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2003455189L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(2004001016L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1031405209L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1082012201L, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(65516, "C6", 0, MARCH_9_2009 ));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(203178249, "C6", 0, JUNE_6_2010 ));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7000062022L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7006003027L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7003306026L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7001501029L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000641509L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9000260986L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2004306518L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2016001206L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1082311275L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000118821L, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(525111212, "C6", 0, MARCH_9_2009 ));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(91423614, "C6", 0, JUNE_6_2010 ));
        // other account numbers
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3110150986L, "C6", 0,
            JUNE_6_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3068459207L, "C6", 0,
            JUNE_6_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4642847318L, "C6", 0,
            JUNE_6_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8348300005L, "C6", 0,
            JUNE_6_2010 ) );
    }

    @Test
    public void testMethodC6_June7_2010_to_June5_2011() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7000005024L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7008199027L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7002000023L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000430223L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000781153L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2003455189L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2004001016L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1031405209L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1082012201L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 65516, "C6", 0, JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 203178249, "C6", 0, JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3110150986L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3068459207L, "C6", 0,
            JUNE_7_2010 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7000062022L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7006003027L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000641509L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000260986L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2004306518L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2016001206L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1082311275L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1000118821L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 525111212, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 91423614, "C6", 0, JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3462816371L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3622548632L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4642847318L, "C6", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8348300005L, "C6", 0,
            JUNE_7_2010 ) );
    }
    
    @Test
    public void testMethodC6_June6_2011_to_March3_2013() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 65516, "C6", 0, JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 203178249, "C6", 0, JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1031405209, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1082012201, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2003455189, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2004001016, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3110150986L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3068459207L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5035105948L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5286102149L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6028426119L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6861001755L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7008199027L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7002000023L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000430223L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000781153L, "C6", 0,
            JUNE_6_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 525111212, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 91423614, "C6", 0, JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1082311275, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1000118821, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2004306518, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2016001206, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3462816371L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3622548632L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4232300145L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4000456126L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5002684526L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5564123850L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6295473774L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6640806317L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7000062022L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7006003027L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8348300005L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8654216984L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000641509L, "C6", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 900026098L, "C6", 0,
            JUNE_6_2011 ) );
    }
    
    @Test
    public void testMethodC6() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 65516, "C6", 0, MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 203178249, "C6", 0, MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1031405209, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1082012201, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2003455189, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2004001016, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3110150986L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3068459207L, "C6", 0,
            MARCH_4_2013 ) );        
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5035105948L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5286102149L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4012660028L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4100235626L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6028426119L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6861001755L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7008199027L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7002000023L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8526080015L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8711072264L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000430223L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000781153L, "C6", 0,
            MARCH_4_2013 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 525111212, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator
            .checkAccountNumber( 91423614, "C6", 0, MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1082311275, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1000118821, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2004306518, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2016001206, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3462816371L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3622548632L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4232300158L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4000456126L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5002684526L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5564123850L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6295473774L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6640806317L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7000062022L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7006003027L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8348300002L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8654216984L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000641509L, "C6", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000260986L, "C6", 0,
            MARCH_4_2013 ) );
    }

    @Test
    public void testMethodC7() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3500022L, "C7"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(38150900L, "C7"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(600103660L, "C7"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(39101181L, "C7"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(94012341L, "C7"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5073321010L, "C7"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234517892, "C7"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(987614325, "C7"));
    }
    
    @Test
    public void testMethodC8() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3456789019L, "C8"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5678901231L, "C8"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3456789012L, "C8"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456789L, "C8"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567890L, "C8"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9012345678L, "C8"));
    }
    
    @Test
    public void testMethodC9() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(3456789019L, "C9"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5678901231L, "C9"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(123456789L, "C9"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3456789012L, "C9"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1234567890L, "C9"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9012345678L, "C9"));
    }
    
    @Test
    public void testMethodD0() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6100272324L, "D0"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6100273479L, "D0"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6100272885L, "D0"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6100273377L, "D0"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6100274012L, "D0"));
        // other account numbers
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(5761002734L, "D0"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(570027288L, "D0"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5600272887L, "D0"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(4700272887L, "D0"));
    }
    
    @Test
    public void testMethodD1_beforeJune7_2010() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000005016L, "D1", 0, JUNE_6_2010) );
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1000004019L, "D1", 0, JUNE_6_2010));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4000008014L, "D1", 0, JUNE_6_2010));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6100020013L, "D1", 0, JUNE_6_2010));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(8300042011L, "D1", 0, JUNE_6_2010));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(2000005018L, "D1", 0, JUNE_6_2010));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(5000064015L, "D1", 0, JUNE_6_2010));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(7400041011L, "D1", 0, JUNE_6_2010));
        
        // others
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(100020013L, "D1", 0, JUNE_6_2010));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(3100020013L, "D1", 0, JUNE_6_2010));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(9100020013L, "D1", 0, JUNE_6_2010));
    }

    @Test
    public void testMethodD1_June7_2010_to_March6_2011() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7000005021L, "D1", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3002000027L, "D1", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000430223L, "D1", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2003455182L, "D1", 0,
            JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1031405201L, "D1", 0,
            JUNE_7_2010 ) );
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 82012203, "D1", 0, JUNE_7_2010 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5000065514L, "D1", 0,
            JUNE_7_2010 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7000062025L, "D1", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4006003027L, "D1", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8003306026L, "D1", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2001501026L, "D1", 0,
            JUNE_7_2010 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000641509L, "D1", 0,
            JUNE_7_2010 ) );
        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 260986, "D1", 0, JUNE_7_2010 ) );

        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 2003455182L, "D1", 0, MARCH_6_2011 ) );
    }

    @Test
    public void testMethodD1March7_2011_toSept4_2011() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator
            .checkAccountNumber( 82012203, "D1", 0, MARCH_7_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1452683581, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3002000027L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4230001407L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5000065514L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6001526215L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000430223L, "D1", 0,
            MARCH_7_2011 ) );

        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 260986, "D1", 0, MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1062813622, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2001501026, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3012084101L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4006003027L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5814500990L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6128462594L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7000062025L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8003306026L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000641509L, "D1", 0,
            MARCH_7_2011 ) );

        // others
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2003455182L, "D1", 0,
            MARCH_7_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2129642505, "D1", 0,
            SEPT_4_2011 ) );

    }

    @Test
    public void testMethodD1Sept5_2011_toMarch3_2013() {
        // test numbers from documentation
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 82012203, "D1", 0, SEPT_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1452683581, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2129642505, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3002000027L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4230001407L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5000065514L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6001526215L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000430223L, "D1", 0,
            SEPT_5_2011 ) );

        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 260986, "D1", 0, SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1062813622, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2256412314L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3012084101L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4006003027L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5814500990L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6128462594L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7000062025L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8003306026L, "D1", 0,
            SEPT_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000641509L, "D1", 0,
            SEPT_5_2011 ) );
    }
    
    @Test
    public void testMethodD1() {
        // test numbers from documentation
        Assert
            .assertTrue( BankAccountValidator.checkAccountNumber( 82012203, "D1", 0, MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1452683581, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2129642505, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3002000027L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4230001407L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5000065514L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6001526215L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7126502149L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000430223L, "D1", 0,
            MARCH_4_2013 ) );

        Assert
            .assertFalse( BankAccountValidator.checkAccountNumber( 260986, "D1", 0, MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1062813622, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2256412314L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3012084101L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4006003027L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5814500990L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6128462594L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7000062035L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8003306026L, "D1", 0,
            MARCH_4_2013 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9000641509L, "D1", 0,
            MARCH_4_2013 ) );
    }

    @Test
    public void testMethodD2() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(189912137, "D2"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(235308215, "D2"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(4455667784L, "D2"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1234567897, "D2"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(51181008, "D2"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(71214205, "D2"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(6414241, "D2"));
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(179751314, "D2"));
    }
    
    @Test
    public void testMethodD3() {
        // test numbers from documentation
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1600169591, "D3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1600189151, "D3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(1800084079, "D3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6019937007L, "D3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6021354007L, "D3"));
        Assert.assertTrue(BankAccountValidator.checkAccountNumber(6030642006L, "D3"));
        
        Assert.assertFalse(BankAccountValidator.checkAccountNumber(1600166307, "D3"));
        // account numbers 1600176485 and 1600201934 from documentation are invalid for Variante 1
        // (method 0) but valid for Variante 2 (method 27)        
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6025017009L, "D3" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6028267003L, "D3" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6019835001L, "D3" ) );
    }

    @Test
    public void testMethodD4_Before_June6_2011() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3000005012L, "D4", 0,
            JUNE_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4143406984L, "D4", 0,
            JUNE_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4143406984L, "D4", 0,
            JUNE_5_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9002364588L, "D4", 0,
            JUNE_5_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1000062025L, "D4", 0,
            JUNE_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 0006003027L, "D4", 0,
            JUNE_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8003306026L, "D4", 0,
            JUNE_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9916524534L, "D4", 0,
            JUNE_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5212744564L, "D4", 0,
            JUNE_5_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3000255397L, "D4", 0,
            JUNE_5_2011 ) );
    }

    @Test
    public void testMethodD4() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1112048219, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2024601814, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3000005012L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4143406984L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5926485111L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6286304975L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7900256617L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8102228628L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9002364588L, "D4", 0,
            JUNE_6_2011 ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 359432843, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1000062023L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2204271250L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3051681017L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 4000123456L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 5212744564L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6286420010L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7859103459L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8003306026L, "D4", 0,
            JUNE_6_2011 ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9916524534L, "D4", 0,
            JUNE_6_2011 ) );
    }

    @Test
    public void testMethodD5() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5999242133L, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 5999718138L, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1799222116L, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9632004, "D5" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3299632008L, "D5" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1999204293, "D5" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 399242139, "D5" ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4711173, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7093330, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 127787, "D5" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8623420004L, "D5" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1123458, "D5" ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4711172, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7093335, "D5" ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 100062, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 100088, "D5" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 100084, "D5" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 100085, "D5" ) );

        // others
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8623410000L, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8623421124L, "D5" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8623421414L, "D5" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8623422114L, "D5" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 8623411110L, "D5" ) );
    }

    @Test
    public void testUnknownMethod() {
   	 testUnknownMethod( 43001500L, "Hihi", 13051172 );
   	 testUnknownMethod( 43001500L, null, 13051172 );
   	 testUnknownMethod( 42, "-1", 38011000 );
    }

    private void testUnknownMethod( final long account, final String method, final int bankCode ) {
   	 try {
   		 BankAccountValidator.checkAccountNumber(account, method, bankCode);
   		 Assert.fail("Expected an AccountValidationException");
   	 } catch( final AccountValidationException ex ) {
   	     // expected
   	 }
    }

    @Test
    public void testMethodD6() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3409, "D6" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 585327, "D6" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1650513, "D6" ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 3601671056L, "D6" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 4402001046L, "D6" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6100268241L, "D6" ) );

        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 7001000681L, "D6" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9000111105L, "D6" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9001291005L, "D6" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 33394, "D6" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 595795, "D6" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 16400501, "D6" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3615071237L, "D6" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6039267013L, "D6" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6039316014L, "D6" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 7004017653L, "D6" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9002720007L, "D6" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9017483524L, "D6" ) );
    }
    
    @Test
    public void testMethodD7() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 500018205, "D7" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 230103715, "D7" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 301000434, "D7" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 330035104, "D7" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 420001202, "D7" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 134637709, "D7" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 201005939, "D7" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 602006999, "D7" ) );
        
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 501006102, "D7" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 231307867, "D7" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 301005331, "D7" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 330034104, "D7" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 420001302, "D7" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 135638809, "D7" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 202005939, "D7" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 601006977, "D7" ) );
        
        // others
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1500018207, "D7" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2500018205L, "D7" ) );
    }

    @Test
    public void testMethodD8() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1403414848, "D8" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6800000439L, "D8" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 6899999954L, "D8" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 3012084101L, "D8" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1062813622, "D8" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 260986, "D8" ) );

        // others
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 10000000, "D8" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 99999999, "D8" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9999999, "D8" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 100000000, "D8" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 999999999, "D8" ) );
    }
    
    @Test
    public void testMethodD9() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1234567897, "D9" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 123456782, "D9" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 9876543210L, "D9" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1234567890, "D9" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 123456789, "D9" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1100132044, "D9" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1100669030, "D9" ) );
        
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 6543217890L, "D9" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 543216789, "D9" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1100789043, "D9" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1100914032, "D9" ) );
    }

    @Test
    public void testMethodE0() {
        // test numbers from documentation
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1234568013, "E0" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 1534568010, "E0" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 2610015, "E0" ) );
        Assert.assertTrue( BankAccountValidator.checkAccountNumber( 8741013011L, "E0" ) );

        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 1234769013, "E0" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 2710014, "E0" ) );
        Assert.assertFalse( BankAccountValidator.checkAccountNumber( 9741015011L, "E0" ) );
	}

	@Test
	public void testMethodE1() {
		// test numbers from documentation
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(134211909, "E1"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(100041104, "E1"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(100054106, "E1"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(200025107, "E1"));

		Assert.assertFalse(BankAccountValidator.checkAccountNumber(150013107, "E1"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(200035101, "E1"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(81313890, "E1"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(4268550840L, "E1"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(987402008, "E1"));
		
		// others
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(81403890, "E1"));
	}

	@Test
	public void testMethodE2() {
		// test numbers from documentation
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(3000260983L, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(3831745, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(51330335, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(1730773457, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(1987654327, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(2012345675, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(2220467998L, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(3190519693L, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(3011219713L, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(4131220086L, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(4110919419L, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(5000083836L, "E2"));
		Assert.assertTrue(BankAccountValidator.checkAccountNumber(5069696965L, "E2"));

		Assert.assertFalse(BankAccountValidator.checkAccountNumber(121314151, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(36958466, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(1000174716, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(1975312468, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(2260519349L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(2004002175, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(3780024149L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(3015024274L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(4968745438L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(4005012150L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(5000137454L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(5221398871L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(6221398879L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(6742185327L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(7793867322L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(7900695413L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(8001256238L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(8303808900L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(9703805111L, "E2"));
		Assert.assertFalse(BankAccountValidator.checkAccountNumber(9006126433L, "E2"));
	}
}
