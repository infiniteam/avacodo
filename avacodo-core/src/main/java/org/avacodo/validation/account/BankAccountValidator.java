/*******************************************************************************
 * Copyright (C) 2013 infiniteam
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * 
 * infiniteam on GitHub: https://github.com/infiniteam
 ******************************************************************************/
/*
 * Copyright 2006 - 2007 VÖB-ZVD GmbH. All rights reserved.
 */
package org.avacodo.validation.account;

import org.joda.time.*;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * implements the check digit methods for German bank accounts
 * published by the Bundesbank 
 * the check digit methods are updated four times a year  
 * make sure this class stays up-to-date
 */
class BankAccountValidator {
	private BankAccountValidator() {}
//    static final Log log = LogFactory.getLog(BankAccountValidator.class);
    
    public static final long MIN_ACCOUNT_NUMBER = 1;

    public static final long MAX_ACCOUNT_NUMBER = 9999999999L;

    private static final LocalDate MARCH_7_2005=new LocalDate(2005,3,7);

    private static final LocalDate DEC_4_2006=new LocalDate(2006,12,4);

    private static final LocalDate MARCH_9_2009=new LocalDate(2009,3,9);

    private static final LocalDate JUNE_7_2010=new LocalDate(2010,6,7);

    private static final LocalDate MARCH_7_2011=new LocalDate(2011,3,7);

    private static final LocalDate JUNE_6_2011=new LocalDate(2011,6,6);

    private static final LocalDate SEPT_5_2011=new LocalDate(2011,9,5);

    private static final LocalDate MARCH_4_2013=new LocalDate(2013,3,4);

    private static final LocalDate JUNE_3_2013=new LocalDate(2013,6,3);

    private static final LocalDate SEPT_9_2013=new LocalDate(2013,9,9);

    /**
     * Check an account number of a German bank account.
     * 
     * @param account number
     * @param check method identifier
     */
    static boolean checkAccountNumber(long accountNumber, String checkMethod) {
        return checkAccountNumber(accountNumber, checkMethod, 0);
    }

    /**
     * Check an account number of a German bank account.
     * The date parameter is currently ignored. When check methods change on a certain date, 
     * the date parameter can be used to determine which variant of the method has to be used 
     * @param account number
     * @param check method identifier
     * @param blz, bank code,  used only for a few check methods
     */
    public static boolean checkAccountNumber(long accountNumber, String checkMethod, int blz) {
        return checkAccountNumber(accountNumber, checkMethod, blz, LocalDate.now());
    }

    /**
     * Check an account number of a German bank account.
     * @param account number
     * @param check method identifier
     * @param blz, bank code, used only for a few check methods
     * @param date and time, check methods may change overthe years
     */
  //ALLOW_COMPLEX

    public static boolean checkAccountNumber(long accountNumber, String checkMethod, int blz, LocalDate date) {
        int accountLength = 0;
        int accountDigits[] = new int[10];
        int ix;
        int div = 1;

        if (accountNumber < MIN_ACCOUNT_NUMBER || accountNumber > MAX_ACCOUNT_NUMBER) {
            return false;
        }

        // split account number in its digits
        for (ix = 0; ix < 10; ix++) {
            accountDigits[ix] = (int) ((accountNumber / div) % 10);
            div *= 10;
            if (accountDigits[ix] > 0) {
                accountLength = ix + 1;
            }    
        }

        try {
            int checkMethodNumber = Integer.parseInt( checkMethod, 16 );

            switch( checkMethodNumber ) {
                case 0x00:
                    return checkMethod00( accountDigits, accountLength );

                case 0x01:
                    return checkMethod01( accountDigits, accountLength );

                case 0x02:
                    return checkMethod02( accountDigits, accountLength );

                case 0x03:
                    return checkMethod03( accountDigits, accountLength );

                case 0x04:
                    return checkMethod04( accountDigits, accountLength );

                case 0x05:
                    return checkMethod05( accountDigits, accountLength );

                case 0x06:
                    return checkMethod06( accountDigits, accountLength );

                case 0x07:
                    return checkMethod07( accountDigits, accountLength );

                case 0x08:
                    return checkMethod08( accountDigits, accountLength, accountNumber );

                case 0x09:
                    return checkMethod09( accountDigits, accountLength );

                case 0x10:
                    return checkMethod10( accountDigits, accountLength );

                case 0x11:
                    return checkMethod11( accountDigits, accountLength );

                // method 12 not used by any bank

                case 0x13:
                    return checkMethod13( accountDigits, accountLength );

                case 0x14:
                    return checkMethod14( accountDigits, accountLength );

                case 0x15:
                    return checkMethod15( accountDigits, accountLength );

                case 0x16:
                    return checkMethod16( accountDigits, accountLength );

                case 0x17:
                    return checkMethod17( accountDigits, accountLength );

                case 0x18:
                    return checkMethod18( accountDigits, accountLength );

                case 0x19:
                    return checkMethod19( accountDigits, accountLength );

                case 0x20:
                    return checkMethod20( accountDigits, accountLength );

                case 0x21:
                    return checkMethod21( accountDigits, accountLength );

                case 0x22:
                    return checkMethod22( accountDigits, accountLength );

                case 0x23:
                    return checkMethod23( accountDigits, accountLength );

                case 0x24:
                    return checkMethod24( accountDigits, accountLength );

                case 0x25:
                    return checkMethod25( accountDigits, accountLength );

                case 0x26:
                    return checkMethod26( accountDigits, accountLength );

                case 0x27:
                    return checkMethod27( accountDigits, accountLength );

                case 0x28:
                    return checkMethod28( accountDigits, accountLength );

                case 0x29:
                    return checkMethod29( accountDigits, accountLength );

                case 0x30:
                    return checkMethod30( accountDigits, accountLength );

                case 0x31:
                    return checkMethod31( accountDigits, accountLength );

                case 0x32:
                    return checkMethod32( accountDigits, accountLength );

                case 0x33:
                    return checkMethod33( accountDigits, accountLength );

                case 0x34:
                    return checkMethod34( accountDigits, accountLength );

                case 0x35:
                    return checkMethod35( accountDigits, accountLength );

                // method 36 not used by any bank
                case 0x36:
                    return checkMethod36( accountDigits, accountLength );

                case 0x37:
                    return checkMethod37( accountDigits, accountLength );

                case 0x38:
                    return checkMethod38( accountDigits, accountLength );

                case 0x39:
                    return checkMethod39( accountDigits, accountLength );

                case 0x40:
                    return checkMethod40( accountDigits, accountLength );

                case 0x41:
                    return checkMethod41( accountDigits, accountLength );

                case 0x42:
                    return checkMethod42( accountDigits, accountLength );

                case 0x43:
                    return checkMethod43( accountDigits, accountLength );

                case 0x44:
                    return checkMethod44( accountDigits, accountLength );

                case 0x45:
                    return checkMethod45( accountDigits, accountLength );

                case 0x46:
                    return checkMethod46( accountDigits, accountLength );

                case 0x47:
                    return checkMethod47( accountDigits, accountLength );

                case 0x48:
                    return checkMethod48( accountDigits, accountLength );

                case 0x49:
                    return checkMethod49( accountDigits, accountLength );

                case 0x50:
                    return checkMethod50( accountDigits, accountLength );

                case 0x51:
                    if( date.isBefore( JUNE_3_2013 ) ) {
                        return checkMethod51_0( accountDigits, accountLength );
                    }
                    return checkMethod51_1( accountDigits, accountLength );

                case 0x52:
                    return checkMethod52( accountDigits, accountLength, blz );

                case 0x53:
                    return checkMethod53( accountDigits, accountLength, blz );

                case 0x54:
                    return checkMethod54( accountDigits, accountLength );

                case 0x55:
                    return checkMethod55( accountDigits, accountLength );

                case 0x56:
                    return checkMethod56( accountDigits, accountLength );

                case 0x57:
                    if( date.isBefore( DEC_4_2006 ) ) {
                        return checkMethod57_0( accountDigits, accountLength, accountNumber );
                    }
                    if( date.isBefore( SEPT_9_2013 ) ) {
                        return checkMethod57_1( accountDigits, accountLength, accountNumber );
                    }
                    return checkMethod57_2( accountDigits, accountLength, accountNumber );

                case 0x58:
                    return checkMethod58( accountDigits, accountLength );

                case 0x59:
                    return checkMethod59( accountDigits, accountLength );

                case 0x60:
                    return checkMethod60( accountDigits, accountLength );

                case 0x61:
                    return checkMethod61( accountDigits, accountLength );

                case 0x62:
                    return checkMethod62( accountDigits, accountLength );

                case 0x63:
                    return checkMethod63( accountDigits, accountLength );

                case 0x64:
                    return checkMethod64( accountDigits, accountLength );

                case 0x65:
                    return checkMethod65( accountDigits, accountLength );

                case 0x66:
                    return checkMethod66( accountDigits, accountLength );

                case 0x67:
                    return checkMethod67( accountDigits, accountLength );

                case 0x68:
                    return checkMethod68( accountDigits, accountLength );

                case 0x69:
                    return checkMethod69( accountDigits, accountLength );

                case 0x70:
                    return checkMethod70( accountDigits, accountLength );

                case 0x71:
                    return checkMethod71( accountDigits, accountLength );

                case 0x72:
                    return checkMethod72( accountDigits, accountLength );

                case 0x73:
                    return checkMethod73( accountDigits, accountLength );

                case 0x74:
                    return checkMethod74( accountDigits, accountLength );

                case 0x75:
                    return checkMethod75( accountDigits, accountLength );

                case 0x76:
                    return checkMethod76( accountDigits, accountLength );

                case 0x77:
                    return checkMethod77( accountDigits, accountLength );

                case 0x78:
                    return checkMethod78( accountDigits, accountLength );

                case 0x79:
                    return checkMethod79( accountDigits, accountLength );

                case 0x80:
                    return checkMethod80( accountDigits, accountLength );

                case 0x81:
                    return checkMethod81( accountDigits, accountLength );

                case 0x82:
                    return checkMethod82( accountDigits, accountLength );

                case 0x83:
                    return checkMethod83( accountDigits, accountLength );

                case 0x84:
                    if( date.isBefore( JUNE_3_2013 ) ) {
                        return checkMethod84_0( accountDigits, accountLength );
                    }
                    return checkMethod84_1( accountDigits, accountLength );

                case 0x85:
                    return checkMethod85( accountDigits, accountLength );

                case 0x86:
                    return checkMethod86( accountDigits, accountLength );

                case 0x87:
                    return checkMethod87( accountDigits, accountLength );

                case 0x88:
                    return checkMethod88( accountDigits, accountLength );

                case 0x89:
                    return checkMethod89( accountDigits, accountLength );

                case 0x90:
                    return checkMethod90( accountDigits, accountLength );

                case 0x91:
                    return checkMethod91( accountDigits, accountLength );

                case 0x92:
                    return checkMethod92( accountDigits, accountLength );

                case 0x93:
                    return checkMethod93( accountDigits, accountLength );

                case 0x94:
                    return checkMethod94( accountDigits, accountLength );

                case 0x95:
                    if( date.isBefore( SEPT_9_2013 ) ) {
                        return checkMethod95_0( accountDigits, accountLength, accountNumber );
                    }
                    return checkMethod95_1( accountDigits, accountLength, accountNumber );

                case 0x96:
                    return checkMethod96( accountDigits, accountLength, accountNumber );

                case 0x97:
                    return checkMethod97( accountDigits, accountLength, accountNumber );

                case 0x98:
                    return checkMethod98( accountDigits, accountLength );

                case 0x99:
                    return checkMethod99( accountDigits, accountLength, accountNumber );
                case 0xA0:
                    return checkMethodA0( accountDigits, accountLength );
                case 0xA1:
                    return checkMethodA1( accountDigits, accountLength );
                case 0xA2:
                    return checkMethodA2( accountDigits, accountLength );
                case 0xA3:
                    return checkMethodA3( accountDigits, accountLength );
                case 0xA4:
                    return checkMethodA4( accountDigits, accountLength );
                case 0xA5:
                    return checkMethodA5( accountDigits, accountLength );
                case 0xA6:
                    return checkMethodA6( accountDigits, accountLength );
                case 0xA7:
                    return checkMethodA7( accountDigits, accountLength );
                case 0xA8:
                    if( date.isBefore( MARCH_7_2005 ) ) {
                        return checkMethodA8_0( accountDigits, accountLength );
                    }
                    return checkMethodA8_1( accountDigits, accountLength );
                case 0xA9:
                    return checkMethodA9( accountDigits, accountLength );
                case 0xB0:
                    return checkMethodB0( accountDigits, accountLength );
                case 0xB1:
                    return checkMethodB1( accountDigits, accountLength );
                case 0xB2:
                    return checkMethodB2( accountDigits, accountLength );
                case 0xB3:
                    return checkMethodB3( accountDigits, accountLength );
                case 0xB4:
                    return checkMethodB4( accountDigits, accountLength );
                case 0xB5:
                    return checkMethodB5( accountDigits, accountLength );
                case 0xB6:
                    if(date.isBefore( SEPT_5_2011 ) ) {
                        return checkMethodB6_0( accountDigits, accountLength, blz );
                    }
                    return checkMethodB6_1( accountDigits, accountLength, blz );
                case 0xB7:
                    return checkMethodB7( accountDigits, accountLength, accountNumber );
                case 0xB8:
                    if( date.isBefore( JUNE_6_2011 ) ) {
                        return checkMethodB8_0( accountDigits, accountLength );
                    }
                    return checkMethodB8_1( accountDigits, accountLength, accountNumber );
                case 0xB9:
                    return checkMethodB9( accountDigits, accountLength );
                case 0xC0:
                    return checkMethodC0( accountDigits, accountLength, blz );
                case 0xC1:
                    return checkMethodC1( accountDigits, accountLength );
                case 0xC2:
                    return checkMethodC2( accountDigits, accountLength );
                case 0xC3:
                    return checkMethodC3( accountDigits, accountLength );
                case 0xC4:
                    return checkMethodC4( accountDigits, accountLength );
                case 0xC5:
                    return checkMethodC5( accountDigits, accountLength );
                case 0xC6:
                    if( date.isBefore( MARCH_9_2009 ) ) {
                        return checkMethodC6_0( accountDigits, accountLength );
                    } else if( date.isBefore( JUNE_7_2010 ) ) {
                        return checkMethodC6_1( accountDigits, accountLength );
                    } else if( date.isBefore( JUNE_6_2011 ) ) {
                        return checkMethodC6_2( accountDigits, accountLength );
                    } else if( date.isBefore( MARCH_4_2013 ) ) {
                        return checkMethodC6_3( accountDigits, accountLength );
                    } else {
                        return checkMethodC6_4( accountDigits, accountLength );
                    }
                case 0xC7:
                    return checkMethodC7( accountDigits, accountLength );
                case 0xC8:
                    return checkMethodC8( accountDigits, accountLength );
                case 0xC9:
                    return checkMethodC9( accountDigits, accountLength );
                case 0xD0:
                    return checkMethodD0( accountDigits, accountLength );
                case 0xD1:
                    if( date.isBefore( JUNE_7_2010 ) ) {
                        return checkMethodD1_0( accountDigits, accountLength );
                    }
                    if( date.isBefore( MARCH_7_2011 ) ) {
                        return checkMethodD1_1( accountDigits, accountLength );
                    }
                    if( date.isBefore( SEPT_5_2011 ) ) {
                        return checkMethodD1_2( accountDigits, accountLength );
                    }
                    if( date.isBefore( MARCH_4_2013 ) ) {
                        return checkMethodD1_3( accountDigits, accountLength );
                    }
                    return checkMethodD1_4( accountDigits, accountLength );
                case 0xD2:
                    if( date.isBefore( SEPT_9_2013 ) ) {
                        return checkMethodD2_0( accountDigits, accountLength, accountNumber );
                    }
                    return checkMethodD2_1( accountDigits, accountLength, accountNumber );
                case 0xD3:
                    return checkMethodD3( accountDigits, accountLength );
                case 0xD4:
                    if( date.isBefore( JUNE_6_2011 ) ) {
                        return checkMethodD4_0( accountDigits, accountLength );
                    }
                    return checkMethodD4_1( accountDigits, accountLength );
                case 0xD5:
                    return checkMethodD5( accountDigits, accountLength );
                case 0xD6:
                    return checkMethodD6( accountDigits, accountLength );
                case 0xD7:
                    return checkMethodD7( accountDigits, accountLength );
                case 0xD8:
                    return checkMethodD8( accountDigits, accountLength );
                case 0xD9:
                    return checkMethodD9( accountDigits, accountLength );
                case 0xE0:
                    return checkMethodE0( accountDigits, accountLength );
                default: throw new NumberFormatException();
            }
        } catch( NumberFormatException nfe ) {
        	throw new AccountValidationException("unknown account check method "+checkMethod);
        }
    }
    
    private static boolean checkMethod00( final int accountDigits[], final int accountLength ) {
        return checkMethod00Alike( accountDigits, 1, accountLength, 0 );
    }
    
    /**
     * Algorithm for methods based on method 00.
     * 
     * @param accountDigits array with the digits of the account number, right most digit at index 0
     * @param start start index for summing up
     * @param end end index for summing up
     * @param add additional summand added to the sum
     * @return true if the account number is valid, otherwise false
     */
    private static boolean checkMethod00Alike( final int accountDigits[], final int start,
        final int end, final int add ) {
        int sum = checkMethod00Sum( accountDigits, start, end ) + add;
        // use last digit of sum
        // last digit of account number must be 10 - this, or 0 if this is 10
        return (10 - sum % 10) % 10 == accountDigits[start - 1];
    }

    private static int checkMethod00Sum( final int accountDigits[], final int start, final int end ) {
        int sum = 0;
        // take account number from right to left
        // multiply digits with 2,1,2,1,...
        boolean factor2 = true;
        for( int ix = start; ix < end; ix++ ) {
            sum += method00Weight( accountDigits[ix], factor2 );
            factor2 = !factor2;
        }
        return sum;
    }

    private static int method00Weight( final int digit, final boolean factor2 ) {
        int weight = digit;
        if( factor2 ) {
            weight += digit;
            // take cross sum if weight > 9
            if( weight > 9 ) {
                weight -= 9;
            }
        }
        return weight;
    }

    private static boolean checkMethod01( final int accountDigits[], final int accountLength ) {
        int ix;
        int sum = 0;
        int factor[] = {1,3,7};
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix%3];
        }
        // use last digit of sum
        // last digit of account number must be 10 - this, or 0 if this is 10
        return (10 - sum % 10) % 10 == accountDigits[0];
    }

    private static boolean checkMethod02( final int accountDigits[], final int accountLength ) {
        int ix;
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,8,9,2};
        int pz;
        
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 11
        pz = 11 - (sum % 11);
        if(pz == 11) {
            pz = 0;
        }    
        // number invalid if pz is 10
        return pz == accountDigits[0];
    }

    private static boolean checkMethod03( final int accountDigits[], final int accountLength ) {
        int ix;
        int sum = 0;
        
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (1+ (ix%2));
        }
        // use last digit of sum
        // last digit of account number must be 10 - this, or 0 if this is 10
        return (10 - sum %10) %10 == accountDigits[0];
    }

    private static boolean checkMethod04( final int accountDigits[], final int accountLength ) {
        int ix;
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,2,3,4};
        int pz;
        
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        pz = 11 - (sum %11);
        if(pz == 11) {
            pz = 0;
        }    
        return (pz == accountDigits[0]);
    }

    private static boolean checkMethod05( final int accountDigits[], final int accountLength ) {
        int ix;
        int sum = 0;
        int factor[] = {1,7,3};
        
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix%3];
        }
        // use last digit of sum
        // last digit of account number must be 10 - this, or 0 if this is 10
        return (10 - sum %10) %10 == accountDigits[0];
    }

    private static boolean checkMethod06( final int accountDigits[], final int accountLength ) {
        int ix;
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,2,3,4};
        
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        
        return method06CheckDigit(sum) == accountDigits[0];
    }
    
    private static int method06CheckDigit(int sum) {
        //  check digit is 11 - sum modulo 11, 0 if this is 10 or 11
        int pz = 11 - (sum %11);
        if(pz > 9) {
            pz = 0;
        }
        return pz;
    }

    private static boolean checkMethod07( final int accountDigits[], final int accountLength ) {
        int ix;
        int sum = 0;
        int pz;
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 11
        pz = 11 - (sum %11);
        if(pz == 11) {
            pz = 0;
        }    
        return (pz == accountDigits[0]);
    }

    private static boolean checkMethod08( final int accountDigits[], final int accountLength,
        final long accountNumber ) {
        return (accountNumber < 60000) || checkMethod00( accountDigits, accountLength );
    }

    private static boolean checkMethod09( final int accountDigits[], final int accountLength ) {
        // no check sum calculation
        return true;
    }

    // based on 06
    private static boolean checkMethod10(int accountDigits[], int accountLength) {
        int sum = 0;
        
        for(int ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit af account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[0];
    }


    // based on 06, almost like 10
    private static boolean checkMethod11(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int pz;

        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        pz = 11 - (sum %11);
        if(pz == 10) {
            pz = 9;
        }    
        if(pz == 11) {
            pz = 0;
        }    
        return (pz == accountDigits[0]);
    }

    // method 12 not defined

    // based on 00
    private static boolean checkMethod13(int accountDigits[], int accountLength) {
        // take account number (without last 3 and first digit), from right to left
        // multiply digits with 2,1,2,1,...
        // use last digit of sum
        // 3rd digit from the right of account number must be 10-this, or 0 if this is 10
       return checkMethod00Alike( accountDigits, 3, 9, 0 )
            // the last two digits might be 00 and might have been omitted
            || checkMethod00Alike( accountDigits, 1, 7, 0 );
    }

    private static boolean checkMethod14(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int pz;
        // or ix<accountLength -3 ?
        for(ix=1;ix<7;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        pz = 11 - (sum % 11);
        if(pz == 11) {
            pz = 0;
        }    
        return (pz == accountDigits[0]);
    }

    // based on 06
    private static boolean checkMethod15(int accountDigits[], int accountLength)  {
        int ix;
        int sum = 0;
        
        for(ix=1;ix<5;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[0];
    }

    // based on 06
    private static boolean checkMethod16(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,2,3,4};
        int pz;
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 11
        pz = 11 - (sum % 11);
        // documentation is unclear about pz == 10
        // is accountDigits[0]=0 also allowed ?
        // comparing with method 23 and checking with www.pruefziffer.de
        // leeds to: accountDigits[0] must equal accountDigits[1]
        // but ATOS also allows 0 as last digit
        if(pz == 10) {
            return accountDigits[0] == accountDigits[1];
        }    
        if(pz > 9) {
            pz = 0;
        }    
        return (pz == accountDigits[0]);
    }

    private static boolean checkMethod17(int accountDigits[], int accountLength)  {
        // digits 2-7 from left to right, multiply with 1,2,1,...
        // same as from right to left
        int sum = checkMethod00Sum( accountDigits, 3, 9 ) - 1;
        // digit 8 of account number must be 10 - sum modulo 11, 0 if this is 10
        int pz = (10 - (sum % 11)) % 10;
        return (pz == accountDigits[2]);
    }

    // based on 01
    private static boolean checkMethod18(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor[] = {1,3,9,7};
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix%4];
        }
        // use last digit of sum
        // last digit of account number must be 10 - this, or 0 if this is 10
        return ((10 - sum %10) %10 == accountDigits[0]);
    }

    // based on 06
    private static boolean checkMethod19(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,8,9,1};

        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[0];
    }

    // based on 06
    private static boolean checkMethod20(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,8,9,3};
        
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[0];
    }

    // based on 00
    private static boolean checkMethod21(int accountDigits[], int accountLength) {
        // take account number (without last digit), from right to left
        // multiply digits with 2,1,2,1,...
        int sum = checkMethod00Sum( accountDigits, 1, accountLength );
        // take cross sum of sum until < 10
        // sum has not more than two digits
        //System.out.println("sum: "+sum);
        while(sum > 9) {
            sum = sum % 10 + sum / 10;
        }    
        //System.out.println("after cross sum, sum: "+sum);
        // use last digit of sum
        // last digit af account number must be 10 - this, or 0 if this is 10
        return ((10 - sum) == accountDigits[0]);
    }

    private static boolean checkMethod22(int accountDigits[], int accountLength) {
        int sum = 0;
        int weight;
        int ix;
        // take account number (without last digit), from right to left
        // multiply digits with 3,1,3,1,...
        for(ix=1;ix<accountLength;ix++) {
            weight = accountDigits[ix]*(1+ 2*(ix % 2));
            // take last digit of weight
            sum += weight % 10;
        }
        // last digit af account number must be 10 - this, or 0 if this is 10
        return ((10 - (sum % 10)) % 10 == accountDigits[0]);
    }

    // based on 16
    private static boolean checkMethod23(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int pz;
        
        // start with 4th digit from the right, multiply with 2,3,4,5,6,7
        for(ix=4;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix-2);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 11
        pz = 11 - (sum % 11);
        if(pz == 10) {
            return accountDigits[3] == accountDigits[4];
        }
        if(pz > 9) {
            pz = 0;
        }    
        return (pz == accountDigits[3]);
    }

    // Postbank
    private static boolean checkMethod24(int accountDigits[], int accountLength) {
        int sum = 0;
        int ix;
        int begin = -1;
        int factor[] = {1,2,3,1,2,3,1,2,3};

        // exceptions
        if(accountDigits[9] > 2 && accountDigits[9] < 7) {
            accountDigits[9] = 0;
        } else if( accountDigits[9] == 9 ) {
            // in this case the spec says: "Die Stelle 4 ist ungleich 0."
            if( accountDigits[6] == 0 ) {
                return false;
            }

            accountDigits[9] = 0;
            accountDigits[8] = 0;
            accountDigits[7] = 0;
        }
        // begin with 1st nonzero, left to right, multiply with 1,2,3 add 1,2,3 modulo 11
        for(ix=9; ix>0 ;ix--) {
            if(ix > begin && accountDigits[ix] > 0) {
                begin = ix;
            }    
            if(ix <= begin) {
                sum += ((accountDigits[ix] + 1)* factor[begin-ix]) % 11;
            }    
        }
        // last digit of account must be last digit of sum
        return (sum % 10 == accountDigits[0]);
    }

    private static boolean checkMethod25(int accountDigits[], int accountLength) {
        if(accountLength > 9) {
            return false;
        }
        
        int ix;
        int sum = 0;
        int pz;
        for(ix=1;ix<9;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        pz = 11 - (sum %11);
        if(pz == 11) {
            pz = 0;
        }    
        if(pz == 10) {
            pz = 0;
            // Arbeitsziffer (second of 10 digits must be 8 or 9)
            if(accountDigits[8] < 8) {
                 return false;
            }    
        }
        return (pz == accountDigits[0]);
    }

    // based on 06
    private static boolean checkMethod26(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,2};
        int pz;

        // if first two digits are 0, shift digits
        if((accountDigits[9] == 0) && (accountDigits[8] == 0)) {
            for(ix=9;ix>1;ix--) {
                accountDigits[ix] = accountDigits[ix-2];
            }
        }
        for(ix=3;ix<10;ix++) {
            sum += accountDigits[ix] * factor[ix-3];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        pz = 11 - (sum %11);
        if(pz > 9) {
            pz = 0;
        }    
        return (pz == accountDigits[2]);
    }

    private static boolean checkMethod27(int accountDigits[], int accountLength) {   
        return (accountDigits[9] == 0) ? checkMethod00(accountDigits, accountLength)
                    : checkMethod29(accountDigits, accountLength); 
    }
    
    // based on 06, many "Volksbank", etc.
    private static boolean checkMethod28(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        
        for(ix=3;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix-1);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[2];
    }
    
    private static boolean checkMethod29(int accountDigits[], int accountLength) {
        int sum = 0;
        int ix;
        int t[][] = { {0,1,5,9,3,7,4,8,2,6},
                        {0,1,7,6,9,8,3,2,5,4},
                        {0,1,8,4,6,2,9,5,7,3},
                        {0,1,2,3,4,5,6,7,8,9} };
              
        // "iterated transformation", "M10H"
        for(ix=1;ix<accountLength;ix++) {
            sum += t[(ix-1) % 4][accountDigits[ix]];
        }
        // use last digit of sum
        // last digit af account number must be 10 - this, or 0 if this is 10
        return (10 - sum %10) % 10 == accountDigits[0];
    }
    
    private static boolean checkMethod30(int accountDigits[], int accountLength) {
        // from left to right, factor 2,0,0,0,0,1,2,1,2;
        int sum = accountDigits[9] * 2;
        for(int ix=4;ix>0;ix--) {
            sum += accountDigits[ix] * (ix % 2 + 1);
        }
        // use last digit of sum
        // last digit af account number must be 10 - this, or 0 if this is 10
        return (10 - sum %10) %10 == accountDigits[0];
    }
    
    private static boolean checkMethod31(int accountDigits[], int accountLength) {
        int sum = 0;
        
        // right to left, except last digit, multiply with 9,8,7,6,...
        for(int ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (10-ix);
        }
        // last digit of account number must be sum modulo 11, number invalid if this is 10
        return (sum % 11 == accountDigits[0]);
    }
    
    // based on 06
    private static boolean checkMethod32(int accountDigits[], int accountLength) {
        return checkMethod06(accountDigits, 7);
    }
    
    // based on 06
    private static boolean checkMethod33(int accountDigits[], int accountLength) {
        return checkMethod06(accountDigits, 6);
    }
    
     // based on 06, many "Volksbank", etc.
    private static boolean checkMethod34(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor[] = {2,4,8,5,10,9,7};
        
        for(ix=3;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-3];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[2];
    }
    
    private static boolean checkMethod35(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int pz;
        
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        pz = sum %11;
        return (pz == accountDigits[0]) || ((pz == 10) && (accountDigits[0] == accountDigits[1]));
    }
    
    // method 36 not used by any bank
    private static boolean checkMethod36(int accountDigits[], int accountLength) {
        return checkMethod40(accountDigits, 5);
    }
   
    private static boolean checkMethod37(int accountDigits[], int accountLength) {
        return checkMethod40(accountDigits, 6);
    }
    
    private static boolean checkMethod38(int accountDigits[], int accountLength) {
        return checkMethod40(accountDigits, 7);
    }

    private static boolean checkMethod39(int accountDigits[], int accountLength) {
        return checkMethod40(accountDigits, 8);
    }
    
    private static boolean checkMethod40(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor[] = {2,4,8,5,10,9,7,3,6};
        
        // use digits 1-9
        for(ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[0];
    }
    
    // based on 00
    private static boolean checkMethod41(int accountDigits[], int accountLength) {        
        // exception
        int end = (accountDigits[6] == 9) ? 7 : accountLength;
        return checkMethod00( accountDigits, end );
    }
    
    private static boolean checkMethod42(int accountDigits[], int accountLength) {
        int sum = 0;
        
        for(int ix=1;ix<9;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[0];
    }
    
    private static boolean checkMethod43(int accountDigits[], int accountLength) {
        int sum = 0;
        
        for(int ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * ix;
        }
        // last digit of account number must be 10 - sum modulo 10, 0 if this is 10
        int pz = (10 - (sum %10)) % 10;
        return (pz == accountDigits[0]);
    }
    
    // based on 33, same as 37
    private static boolean checkMethod44(int accountDigits[], int accountLength) {

        return checkMethod37(accountDigits, accountLength);
    }
    
    // based on 00
    private static boolean checkMethod45( int accountDigits[], int accountLength ) {
        // exceptions: first digit is 0 or 5th digit is 1
        // else: method 0
        return accountDigits[9] == 0 || accountDigits[5] == 1
            || checkMethod00( accountDigits, accountLength );
    }
    
    // based on 06
    private static boolean checkMethod46(int accountDigits[], int accountLength) {
        int sum = 0;
        
        for(int ix=3;ix<8;ix++) {
            sum += accountDigits[ix] * (ix-1);
        }
        // 3rd digit from the right of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[2];
    }
    
    // based on 06
    private static boolean checkMethod47(int accountDigits[], int accountLength) {
        int sum = 0;
        
        for(int ix=2;ix<7;ix++) {
            sum += accountDigits[ix] * ix;
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[1];
    }
    
    // based on 06
    private static boolean checkMethod48(int accountDigits[], int accountLength) {
        int sum = 0;
        
        for(int ix=2;ix<8;ix++) {
            sum += accountDigits[ix] * ix;
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[1];
    }
    
    // based on 00 and 01
    private static boolean checkMethod49(int accountDigits[], int accountLength) {   
        return checkMethod00(accountDigits, accountLength) 
                || checkMethod01(accountDigits, accountLength);
    }           
    
    // based on 06
    private static boolean checkMethod50(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        
        for(ix=4;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix-2);
        }
        // 7th digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11           
        if( method06CheckDigit( sum ) == accountDigits[3]) { 
            return  true;
        }    
        if(accountLength < 8) {
            sum = 0;
            // last 3 digits might be 000 and might have been omitted 
            for(ix=1;ix<7;ix++) {
                sum += accountDigits[ix] * (ix+1);
            }
            // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
            return method06CheckDigit(sum) == accountDigits[0];   
        }
        return false;
    }

    // valid before June 3, 2013
    private static boolean checkMethod51_0(int accountDigits[], int accountLength) {
        
        // exception: 3th digit is 9
        if(accountDigits[7] == 9) {
            return method51Exc(accountDigits, accountLength);
        }    
        // method A, if wrong method B, if still wrong method C
        return checkMethod06(accountDigits, 7) 
                || checkMethod33(accountDigits, accountLength)
                || method51C_0(accountDigits);
    }

    // valid from June 3, 2013
    private static boolean checkMethod51_1(int accountDigits[], int accountLength) {
        
        // exception: 3th digit is 9
        if(accountDigits[7] == 9) {
            return method51Exc(accountDigits, accountLength);
        }    
        // method A, if wrong method B, if still wrong method C
        return checkMethod06(accountDigits, 7) 
                || checkMethod33(accountDigits, accountLength)
                || checkMethod00( accountDigits, 7 )
                || method51D_1(accountDigits);
    }
    
    private static boolean method51Exc(int accountDigits[], int accountLength) {
        return checkMethod10(accountDigits, 8) || ((accountLength > 8) 
                && checkMethod10(accountDigits, accountLength));
    }

    // variant 51 D valid from June 3, 2013
    private static boolean method51D_1(int accountDigits[]) {       
        return accountDigits[9] < 7 && method51C_0( accountDigits );
    }

    // variant 51 C valid before June 3, 2013
    private static boolean method51C_0(int accountDigits[]) {
        int sum = 0;
        // use digits 5-9
        for(int ix=1;ix<6;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit of account number must be 7 - sum modulo 7, 0 if this is 7
        int pz = (7 - (sum % 7) ) % 7;
        
        return pz == accountDigits[0];
    }
   
    // some Sparkassen
    private static boolean checkMethod52(int accountDigits[], int accountLength, int blz) {
        int sum = 0;
        int sum2;
        int eserAlt[] = new int[12];
        int accLen2 = 0;
        int div = 1;
        int factor[] = {2,4,8,5,10,9,7,3,6,1,2,4};
        
        // exception
        if(accountLength == 10 && accountDigits[9] == 9) {
            return checkMethod20(accountDigits, accountLength);
        }    
        // blz must be vvv5yyyy, account number has 8 digits
        if(((blz / 10000) % 10) != 5 || accountLength != 8) {
            return false;
        }    
        // build account number according to ESER Alt system
        // account number has format zPxxxxxxx
        // find out number of leading zeros after P
        int ix = accountLength-3;
        while((accLen2 == 0) && (ix > -1)) {
            if(accountDigits[ix] > 0)
                accLen2 = ix+1;
            ix--;
        }
        //System.out.println("accLen2: "+accLen2);        
        // ESER Alt number has format yyyyzPxxxxxx (number of x depends on leading zeros after P)
        //System.out.print("ESER Alt number (from right to left): ");    
        for(ix=0;ix<accLen2; ix++) {
            eserAlt[ix] = accountDigits[ix];
            // System.out.print(eserAlt[ix]+",");           
        }
        eserAlt[accLen2] = 0;   
        eserAlt[accLen2+1] = accountDigits[accountLength-1];
        //System.out.print(eserAlt[accLen2]+","+eserAlt[accLen2+1]+",");          
        // ESER Alt account number starts with last 4 digits of blz
        for(ix=accLen2 +2; ix< accLen2 +6; ix++) {
            eserAlt[ix] = (blz / div) % 10;
            //System.out.print(eserAlt[ix]+",");               
            div *= 10;
        }
        //System.out.print("\n");        
        
        for(ix=0;ix<accLen2 + 6;ix++) {
            sum += eserAlt[ix] * factor[ix];
        }
        sum2 = (sum % 11) + accountDigits[accountLength-2] * factor[accLen2];
        //System.out.println("sum: "+sum+", sum2: "+sum2);
        return (sum2 % 11) == 10;
    }
    
    // some Sparkassen
    private static boolean checkMethod53(int accountDigits[], int accountLength, int blz) {
        int ix;
        int sum = 0;
        int sum2;
        int eserAlt[] = new int[12];
        int accLen2 = 0;
        int div = 100;
        int factor[] = {2,4,8,5,10,9,7,3,6,1,2,4};
        
        // exception
        if(accountLength == 10 && accountDigits[9] == 9) {
            return checkMethod20(accountDigits, accountLength);
        }    
        // blz must be vvv5yyyy, account number has 9 digits
        if(((blz / 10000) % 10) != 5 || accountLength != 9) {
            return false;
        }    
        // build account number according to ESER Alt system
        // account number has format zTPxxxxxxx
        // find out number of leading zeros after P
        ix = accountLength-4;
        while(accLen2 == 0 && ix > -1) {
            if(accountDigits[ix] > 0)
                accLen2 = ix+1;
            ix--;
        }
// System.out.println("accLen2: "+accLen2);        
        // ESER Alt number has format yyTyzPxxxxxx (number of x depends on leading zeros after P)
// System.out.print("ESER Alt number (from right to left): ");    
        for(ix=0;ix<accLen2; ix++) {
            eserAlt[ix] = accountDigits[ix];
// System.out.print(eserAlt[ix]+",");           
        }
        eserAlt[accLen2] = 0; 
        eserAlt[accLen2+1] = accountDigits[accountLength-1];
// System.out.print(eserAlt[accLen2]+","+eserAlt[accLen2+1]+",");          
        eserAlt[accLen2+2] = blz % 10;
        eserAlt[accLen2+3] = accountDigits[accountLength-2];
// System.out.print(eserAlt[accLen2+2]+","+eserAlt[accLen2+3]+",");              
        for(ix=accLen2 +4; ix< accLen2 +6; ix++) {
            eserAlt[ix] = (blz / div) % 10;
// System.out.print(eserAlt[ix]+",");               
            div *= 10;
        }
// System.out.print("\n");        
        
        for(ix=0;ix<accLen2 + 6;ix++) {
            sum += eserAlt[ix] * factor[ix];
        }
        sum2 = (sum % 11) + accountDigits[accountLength-3] * factor[accLen2];
// System.out.println("sum: "+sum+", sum2: "+sum2);
        return (sum2 % 11) == 10;
    }
    
    private static boolean checkMethod54( int accountDigits[], int accountLength ) {
        if( accountDigits[9] != 4 || accountDigits[8] != 9 ) {
            return false;
        }
        int sum = 0;
        for( int ix = 1; ix < 8; ix++ ) {
            sum += accountDigits[ix] * (2 + ((ix - 1) % 6));
        }
        // last digit of account number must be 11 - sum modulo 11
        return (11 - (sum % 11)) == accountDigits[0];
    }
    
    private static boolean checkMethod55(int accountDigits[], int accountLength) {
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,8,7,8};
        
        for(int ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[0];
    }
    
    private static boolean checkMethod56(int accountDigits[], int accountLength) {
        int sum = 0;
        int factor[] = {2,3,4,5,6,7,2,3,4};
        
        for(int ix=1;ix<accountLength;ix++) {
            sum += accountDigits[ix] * factor[ix-1];
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        int pz = 11 - (sum %11);
        // number invalid if pz is 10 or 11 unless 1st digit is 9
        if((pz > 9) && (accountDigits[9] == 9)) { 
            pz -= 3;
        }    
        return (pz == accountDigits[0]);
    }
    
    // method 57, used by Citibank, used before Dec. 4, 2006
    private static boolean checkMethod57_0(int accountDigits[], int accountLength, long accountNumber) {
        /* no check digit for account numbers starting with 00 - 50, 91 or 96 - 99
         * or where numbers start with 777777 or 888888
         */
        if(accountNumber < 5100000000L || accountNumber >= 9600000000L 
                || (accountDigits[9] == 9 && accountDigits[8] == 1)
                || (accountNumber >= 7777770000L && accountNumber < 7777780000L)
                || (accountNumber >= 8888880000L && accountNumber < 8888890000L)) {
            return true;
        }
        
        return method57Alg1(accountDigits, accountLength);
    }
    
    private static boolean method57Alg1(int accountDigits[], int accountLength) {
        int sum = 0;
        int weight;
        
        /* multiply with 1,2,1,... from left to right
        we do it from right to left which gives us the same result
        and enables us to stop early for short account numbers
        */
       for(int ix=1;ix<accountLength;ix++) {
           weight = accountDigits[ix]* (2 - ix % 2);
           // take cross sum if weight > 9
           sum += (weight > 9) ? weight -9 : weight;
       }
       return (10 - (sum % 10)) % 10 == accountDigits[0];
    }
    
    //  method 57, used by Citibank, valid from Dec. 4, 2006
    private static boolean checkMethod57_1(int accountDigits[], int accountLength, long accountNumber) {
        /* account numbers starting with 00 are false */
        if(accountLength < 9) {
            return false;
        }
        
        /* no check digit for account numbers starting with 40, 50, 91 or 99
         * or where numbers start with 777777 or 888888 and for number 0185125434
         */
        if((accountNumber >= 4000000000L && accountNumber < 4100000000L)
                || (accountNumber >= 5000000000L && accountNumber < 5100000000L)
                || (accountNumber >= 9100000000L && accountNumber < 9200000000L)
                || accountNumber >= 9900000000L
                || (accountNumber >= 7777770000L && accountNumber < 7777780000L)
                || (accountNumber >= 8888880000L && accountNumber < 8888890000L)
                || accountNumber == 185125434) {
            return true;
        }
        /* Variante 1 if account number starts with 51, 55, 61, 64, 65, 66, 70, 73,
         *  75 to 82, 88, 94 or 95
         * 
         */
        if((accountDigits[9] == 5 && (accountDigits[8] == 1 || accountDigits[8] == 5))
                || (accountNumber >= 6100000000L && accountNumber < 6200000000L)
                || (accountNumber >= 6400000000L && accountNumber < 6700000000L)
                || (accountDigits[9] == 7 && (accountDigits[8] == 0 || accountDigits[8] == 3))
                || (accountNumber >= 7500000000L && accountNumber < 8300000000L)
                || (accountNumber >= 8800000000L && accountNumber < 8900000000L)
                || (accountNumber >= 9400000000L && accountNumber < 9600000000L)) {
            
            return method57Alg1(accountDigits, accountLength);
        }
        
        /* Variante 2 if account number starts with
         * 32 to 39, 41 to 49, 52, 53, 54, 56 bis 60, 62, 63, 67, 68, 69,
         * 71, 72, 74, 83 bis 87, 89, 90, 92, 93, 96, 97 or 98
         * in other words: all account numbers >= 3200000000 that have not been
         * checked so far
         */
        if (accountNumber >= 3200000000L) {
            /*
             * multiply with 1,2,1,... from left to right we do it from right to
             * left which gives us the same result, check digit is the 3rd digit
             */
            int sum = 0;
            int weight;
            
            for (int ix = 0; ix < 7; ix++) {
                weight = accountDigits[ix] * (1 + ix % 2);
                // take cross sum if weight > 9
                sum += (weight > 9) ? weight - 9 : weight;
            }
            if(accountLength > 8) {
                weight = accountDigits[8] * 2;
                sum += (weight > 9) ? weight - 9 : weight;
                sum += accountDigits[9];
            }
            return (10 - sum %10) % 10 == accountDigits[7];

        }
        
        /* account numbers lower than 3200000000, i.e. the rest:
         * 3rd and 4th digit are between 01 und 12 and 
         * 7th to 9th digit are lower than 500
         */
        int num1 = accountDigits[7] * 10 + accountDigits[6];
        return (num1 > 0 && num1 <= 12 && accountDigits[3] < 5);
    }
    
    //  method 57, used by Targobank, valid from Sept. 9, 2013
    private static boolean checkMethod57_2(int accountDigits[], int accountLength, long accountNumber) {
        /* account numbers starting with 00 are false */
        if(accountLength < 9) {
            return false;
        }
        
        /* no check digit for account numbers starting with 40, 50, 91 or 99
         * or where numbers start with 777777 or 888888 and for number 0185125434
         */
        if((accountNumber >= 4000000000L && accountNumber < 4100000000L)
                || (accountNumber >= 5000000000L && accountNumber < 5100000000L)
                || (accountNumber >= 9100000000L && accountNumber < 9200000000L)
                || accountNumber >= 9900000000L
                || (accountNumber >= 7777770000L && accountNumber < 7777780000L)
                || (accountNumber >= 8888880000L && accountNumber < 8888890000L)
                || accountNumber == 185125434) {
            return true;
        }
        /* Variante 1 if account number starts with 51, 55, 61, 64, 65, 66, 70, 73 to 82
         * 88, 94 or 95
         * 
         */
        if((accountDigits[9] == 5 && (accountDigits[8] == 1 || accountDigits[8] == 5))
                || (accountNumber >= 6100000000L && accountNumber < 6200000000L)
                || (accountNumber >= 6400000000L && accountNumber < 6700000000L)
                /* changed from 73, 75 to 82 to 73 to 82 */
                || (accountNumber >= 7000000000L && accountNumber < 7100000000L)
                || (accountNumber >= 7300000000L && accountNumber < 8300000000L)
                || (accountNumber >= 8800000000L && accountNumber < 8900000000L)
                || (accountNumber >= 9400000000L && accountNumber < 9600000000L)) {
            
            return method57Alg1(accountDigits, accountLength);
        }
        
        /* Variante 2 if account number starts with
         * 32 to 39, 41 to 49, 52, 53, 54, 56 bis 60, 62, 63, 67, 68, 69,
         * 71, 72, 83 bis 87, 89, 90, 92, 93, 96, 97 or 98
         * in other words: all account numbers >= 3200000000 that have not been
         * checked so far
         */
        if (accountNumber >= 3200000000L) {
            /*
             * multiply with 1,2,1,... from left to right we do it from right to
             * left which gives us the same result, check digit is the 3rd digit
             */
            int sum = 0;
            int weight;
            
            for (int ix = 0; ix < 7; ix++) {
                weight = accountDigits[ix] * (1 + ix % 2);
                // take cross sum if weight > 9
                sum += (weight > 9) ? weight - 9 : weight;
            }
            if(accountLength > 8) {
                weight = accountDigits[8] * 2;
                sum += (weight > 9) ? weight - 9 : weight;
                sum += accountDigits[9];
            }
            return (10 - sum %10) % 10 == accountDigits[7];

        }
        
        /* account numbers lower than 3200000000, i.e. the rest:
         * 3rd and 4th digit are between 01 und 12 and 
         * 7th to 9th digit are lower than 500
         */
        int num1 = accountDigits[7] * 10 + accountDigits[6];
        return (num1 > 0 && num1 <= 12 && accountDigits[3] < 5);
    }

    
    // based on 2
    private static boolean checkMethod58(int accountDigits[], int accountLength) {
        int sum = 0;
        
        // account number has at least 6 digits
        if(accountLength < 6) {
            return false;
        }    
        for(int ix=1;ix<6;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 11
        int pz = 11 - (sum %11);
        if(pz == 11) {
            pz = 0;
        }    
        // number invalid if pz is 10    
        return (pz == accountDigits[0]);
    }
    
    // based on 00
    private static boolean checkMethod59(int accountDigits[], int accountLength) {
        return (accountLength < 9) || checkMethod00(accountDigits, accountLength);
    }
    
    // based on 00
    private static boolean checkMethod60(int accountDigits[], int accountLength) {
        return checkMethod00(accountDigits, 8);
    }

    // based on 00
    private static boolean checkMethod61( int accountDigits[], int accountLength ) {
        // take account number (without last 2 digits), from right to left
        // multiply digits with 2,1,2,1,...
        // if second last digit is equal 8, digit 0 and 1 join the calculation as well
        if( accountDigits[1] == 8 ) {
            final int weight = method00Weight( accountDigits[0], true );
            return checkMethod00Alike( accountDigits, 3, accountLength, 8 + weight );
        }

        return checkMethod00Alike( accountDigits, 3, accountLength, 0 );
    }

    //based on 00
    private static boolean checkMethod62(int accountDigits[], int accountLength) {
        // take account number (without last 3 digits), from right to left
        // multiply digits with 2,1,2,1,...
        return checkMethod00Alike( accountDigits, 3, 8, 0 );
    }


    // Deutsche Bank
    private static boolean checkMethod63( int accountDigits[], int accountLength ) {
        // account number has not more than 9 digits
        if( accountLength > 9 ) {
            return false;
        }
        // take account number (without last 3 digits), from right to left
        // multiply digits with 2,1,2,1,...
        if( checkMethod00Alike( accountDigits, 3, accountLength, 0 ) ) {
            return true;
        }
        // the last two digits might be 00 and might have been omitted
        // -> same as method 00
        return (accountLength < 8) && checkMethod00( accountDigits, accountLength );
    }

    // based on 06
    private static boolean checkMethod64(int accountDigits[], int accountLength) {
    	int sum = 0;
    	int factor[] = {2,4,8,5,10,9};
    
    	for(int ix=4;ix<10;ix++){
    	    sum+=accountDigits[ix]*factor[ix-4];
    	}
    
        return method06CheckDigit(sum) == accountDigits[3];
    }

    // based on 00
    private static boolean checkMethod65( int accountDigits[], int accountLength ) {
        // take account number (without last 3 digits), from right to left
        // multiply digits with 2,1,2,1,...
        if( accountDigits[1] == 9 ) {
            int weight = method00Weight( accountDigits[0], true );
            return checkMethod00Alike( accountDigits, 3, accountLength, 9 + weight );
        }

        return checkMethod00Alike( accountDigits, 3, accountLength, 0 );
    }

    private static boolean checkMethod66(int accountDigits[], int accountLength) {
        if(accountLength > 9) {
            return false;
        }
        
        int sum = 0;
        // factor 2,3,4,5,6,0,0,7
    	for (int ix=1;ix<6;ix++){
    	    sum += accountDigits[ix] * (ix+1);
    	}
        sum += accountDigits[8] * 7;
        
        return ((11 - sum % 11) % 10 == accountDigits[0]);
    }

    //based on 00
    private static boolean checkMethod67(int accountDigits[], int accountLength) {
        return checkMethod00Alike( accountDigits, 3, accountLength, 0 );
    }

    // based on 00
    private static boolean checkMethod68( int accountDigits[], int accountLength ) {

        if( accountLength == 9 && accountDigits[8] == 4 ) {
            return true;
        }
        if( accountLength == 10 ) {
            return (accountDigits[6] == 9) && checkMethod00( accountDigits, 7 );
        }
        // Variante 1 (voll pruefbar):
        return checkMethod00( accountDigits, accountLength )
            || checkMethod00Alike( accountDigits, 1, 6, accountDigits[8] );

    }

    // based on 28
    private static boolean checkMethod69(int accountDigits[], int accountLength) {
        boolean result = false;

        if(accountLength==10 && accountDigits[8]==3 && accountDigits[9]==9) {
            return true;
        }    

        if(!(accountLength==10 && accountDigits[8]==7 && accountDigits[9]==9)) {
            // Variante 1
            result = checkMethod28(accountDigits, accountLength);
        }
        // Variante 2 if necessary
        return result || checkMethod29(accountDigits, accountLength);
    }

    // based on 06
    private static boolean checkMethod70(int accountDigits[], int accountLength) {
        if(accountLength == 10 && (accountDigits[6] == 5
            || (accountDigits[6] == 6 && accountDigits[5] == 9))) {
            // if 4th digit is 5 or 4th and 5th digit is 69
            // do not use digit 1 to 3 for checksum calculation
            return checkMethod06(accountDigits, 7);
        }
        return checkMethod06( accountDigits, accountLength );
    }

    // based on 06
    private static boolean checkMethod71(int accountDigits[], int accountLength) {
        int sum = 0;

        for(int ix=3;ix<9;ix++) {
            sum += accountDigits[ix] * (ix-2);
        }
        // 3rd digit from the right of account number must be 11 - sum modulo 11, 0 if this is 11, 1 if this is 10 
        int pz = (11 - (sum %11));
        if(pz == 11) {
            pz = 0;
        }    
        if(pz == 10) {
            pz = 1;
        }    
        return (pz == accountDigits[0]);
    }

    //based on 00   
    private static boolean checkMethod72(int accountDigits[], int accountLength) {
        return checkMethod00(accountDigits, 7);
    }


    //based on 00 and 51    
    private static boolean checkMethod73(int accountDigits[], int accountLength) {
        if(accountDigits[7] == 9) {
            // exception like in method 51
            return method51Exc(accountDigits, accountLength);
        }
        
        // Variante 1, 2, 3
        return checkMethod00(accountDigits, 7)
                || checkMethod00(accountDigits, 6)
                || method73V3(accountDigits);
            
    }
    
    private static boolean method73V3(int accountDigits[]) {
        int sum = 0;
        int weight;
        // use digits 5-9, factor 2,1,2,1,2
        for(int ix=1;ix<6;ix++) {
            weight = accountDigits[ix] * (1 + (ix % 2));
            // take cross sum if weight > 9
            sum += (weight > 9) ? weight -9 : weight;
        }
        // last digit of account number must be 7 - sum modulo 7, 0 if this is 7
        int pz = (7 - (sum % 7) ) % 7;
        
        return (pz == accountDigits[0]);
    }

    //based on 00
    private static boolean checkMethod74(int accountDigits[], int accountLength) {
        // the documentation says 6 to 10 digits, www.pruefziffer.de allows
        // this 5 digit number: 39313
        // ATOS allows e.g. 18
        // the documentation changed: from June 4, 2007
        // account numbers with 2 - 10 digits are valid,
        // no need to change anything (1 digit numbers except 0 fail the test, 0 is explicitly
        // excluded before this method is called)
        
        int sum = checkMethod00Sum( accountDigits, 1, accountLength );
        // use last digit of sum
        // last digit of account number must be 10 - this, or 0 if this is 10
        if ((10 - sum %10) % 10 == accountDigits[0]) {
            return  true;
        }
        // exception if accountLength is 6
        // documentation says: "is die Summe der Produkte auf die nächste Halbdekade hochzurechnen"
        // interpretation: the "nächste Halbdekade" is the next higher number ending with 5
        // this seems to be in accordance with ATOS and www.pruefziffer.de
    	return (accountLength == 6) && ((15 - (sum %10)) % 10 == accountDigits[0]);
    }

    //based on 00 
    private static boolean checkMethod75( int accountDigits[], int accountLength ) {

        if( (accountLength == 6) || (accountLength == 7) ) {
            return checkMethod00( accountDigits, 6 );
        }

        if( accountLength == 9 ) {
            if( accountDigits[8] == 9 ) {
                return checkMethod00Alike( accountDigits, 3, 8, 0 );
            }
            return checkMethod00Alike( accountDigits, 4, 9, 0 );
        }
        // bad length
        return false;
    }

    private static boolean checkMethod76( int accountDigits[], int accountLength ) {
        return method76Internal( accountDigits, 2 ) ||
            // last two digits might be 00 and might have been omitted
            (accountLength < 9) && method76Internal( accountDigits, 0 );
    }

    private static boolean method76Internal( int accountDigits[], int offset ) {
        /*
         * Excerpt from Bundesbank document:
         * Zusammensetzung der Kontonummer:
         * S = Stammnummer (5-, 6- oder 7-stellig; die letzte Stelle
         * dieser Nummer ist die Prüfziffer, sie wird jedoch nicht
         * in die Prüfzifferberechnung einbezogen)
         * A = Kontoart (1-stellig)*)       werden nicht in die
         * P = Prüfziffer                   Prüfzifferberech-
         * U = Unterkontonummer (2-stellig) nung einbezogen
         * *) Die Kontoart kann den Wert 0, 4, 6, 7, 8 oder 9 haben.
         * Darstellung der Kontonummer:
         * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
         * 5stell. Stammnr.: A 0 0 S S S S P U U
         * 6stell. Stammnr.: A 0 S S S S S P U U
         * 7stell. Stammnr.: A S S S S S S P U U
         */
        
        // first digit of a 10 digit account number must be 0,4,6,7,8 or 9
        final int varHighest = 7 + offset;
        if( !(accountDigits[varHighest] == 0 || accountDigits[varHighest] == 4 || accountDigits[varHighest] > 5) ) {
            return false;
        }
        if( accountDigits[varHighest - 1] == 0 && accountDigits[varHighest - 2] == 0
            && accountDigits[varHighest - 3] == 0 ) {
            // "Stammnummer" too short
            return false;
        }

        int sum = 0;
        for( int ix = 1 + offset; ix < varHighest; ix++ ) {
            sum += accountDigits[ix] * (ix + 1 - offset);
        }
        // 3rd digit from the right of account number must be sum modulo 11
        final int pz = sum % 11;
        // "Ist der Rest 10, kann die Kontonummer nicht geprüft werden."
        // -> if this is 10, the account number is invalid
        return pz == accountDigits[offset];
    }


    private static boolean checkMethod77(int accountDigits[], int accountLength)  {
        int ix;
        int sum = 0;
        int factor2[] = {5,4,3,4,5};
      
    	//calculation one
    	for (ix=0; ix<5;ix++){
    	    sum += accountDigits[ix] * (ix+1);
    	}
    	if(sum%11==0) {
            return true;
        }
        
    	//calculation two
	    sum=0;
	    for (ix=0; ix<5;ix++){
	        sum += accountDigits[ix] * factor2[ix];
	    }
	    return (sum%11==0);
    }

    // based on 00
    private static boolean checkMethod78(int accountDigits[], int accountLength)  {
        return (accountLength == 8) || checkMethod00( accountDigits, accountLength );
    }

    // based on 00
    private static boolean checkMethod79( int accountDigits[], int accountLength ) {
        if( accountDigits[9] == 0 ) {
            return false;
        }

        // take account number (without last digit), from right to left
        // multiply digits with 2,1,2,1,...
        // Variante 1
        if( accountDigits[9] > 2 && accountDigits[9] < 9 ) {
            return checkMethod00( accountDigits, accountLength );
        }
        // Variante 2
        return checkMethod00Alike( accountDigits, 2, accountLength, 0 );
    }

    // based on 00
    private static boolean checkMethod80(int accountDigits[], int accountLength)  {
        if(accountDigits[7] == 9) {
            // exception like in method 51
            return method51Exc(accountDigits, accountLength);
        }
        
        // Methode A, B
        return checkMethod00(accountDigits, 6) || method73V3(accountDigits);
    }


   // based on 32
    private static boolean checkMethod81(int accountDigits[], int accountLength) {
        if(accountDigits[7] == 9) {
            // exception like in method 51
            return method51Exc(accountDigits, accountLength);
        }
        
        return checkMethod32(accountDigits, accountLength);
    }

    // based on 33
    private static boolean checkMethod82(int accountDigits[], int accountLength) {
        //if digits 3 and 4 equal 99 return method 10:
        if ((accountDigits[6]==9)&&(accountDigits[7]==9)) {
            return checkMethod10(accountDigits,accountLength);
        }    
        
        return checkMethod33(accountDigits,accountLength);
    }

    // based on 32 and 33
    private static boolean checkMethod83(int accountDigits[], int accountLength) {
        
        //Method A (method 32), Method B (method 33), Method C (based on 33)
        // or Method A (Sachkonten):
        return checkMethod32(accountDigits, accountLength)
                || checkMethod33(accountDigits, accountLength)
                || method51C_0(accountDigits)
                || ((accountDigits[6] == 9 && accountDigits[7] == 9)
                        && checkMethod10(accountDigits, 8));
    }

    // valid before June 3, 2013, based on 33
    private static boolean checkMethod84_0( int accountDigits[], int accountLength ) {
        if( accountDigits[7] == 9 ) {
            // exception like in method 51
            return method51Exc( accountDigits, accountLength );
        }

        return checkMethod33( accountDigits, accountLength ) || method51C_0( accountDigits );
    }

    // valid from June 3, 2013, based on 33
    private static boolean checkMethod84_1( int accountDigits[], int accountLength ) {
        if( accountDigits[7] == 9 ) {
            // exception like in method 51
            return method51Exc( accountDigits, accountLength );
        }
        // TODO the description of variant C is misleading, check for a clarification
        return checkMethod33( accountDigits, accountLength ) || method51C_0( accountDigits )
            || checkMethod03( accountDigits, 6 );
    }

    // based on 32, 33
    private static boolean checkMethod85(int accountDigits[], int accountLength) {
        //exception if digits 3 and 4 equal 99:
        if ((accountDigits[6]==9)&&(accountDigits[7]==9)){
            return checkMethod02(accountDigits, 8);
        }

        // Method A (method 32), Method B (method 33), Method C
        return checkMethod32(accountDigits, accountLength)
                || checkMethod33(accountDigits, accountLength)
                || method51C_0(accountDigits);
    }


    // based on 00 and 32
    private static boolean checkMethod86(int accountDigits[], int accountLength)  {

	    if(accountDigits[7] == 9) {
	        // exception like in method 51
	        return method51Exc(accountDigits, accountLength);
	    }
        
	    //Method 1 (based on 00):
        // take digits 4-9, from right to left   
        // multiply digits with 2,1,2,1,...
	    //  Method 2 (method 32)
        return checkMethod00(accountDigits, 7) 
                ||checkMethod32(accountDigits, accountLength);
    }


    private static boolean checkMethod87(int accountDigits[], int accountLength) {
        if (accountDigits[7] == 9) {
            // exception like in method 51
            return method51Exc(accountDigits, accountLength);
        }
        
        return method87A(accountDigits, accountLength)
            || checkMethod33(accountDigits, accountLength)
            || method51C_0(accountDigits);
    }
    
    private static boolean method87A(int accountDigits[], int accountLength) {
        int ix;
        int c2;
        int d2 = 0;
        int a5 = 0;
        int p;
        int first = 3;
        int[] tab1 = { 0, 4, 3, 2, 6 };
        int[] tab2 = { 7, 1, 5, 9, 8 };
        int[] tmpDigits = new int[10];

        // changing the order of accountDigits:
        for (ix = 0; ix < 10; ix++) {
            tmpDigits[ix] = accountDigits[9 - ix];
        }

        // Method A:
        while (tmpDigits[first] == 0) {
            first++;
        }
        c2 = (first + 1) % 2;
        // from left to right
        for (ix = first; ix < 9; ix++) {
            switch (tmpDigits[ix]) {
            case 0:
            case 1:
            case 5:
                tmpDigits[ix] += 5;
                break;
            case 6:
                tmpDigits[ix] = 1;
                break;
            default:break;
            }
            if (d2 == c2) {
                if (tmpDigits[ix] > 5) {
                    if (c2 == 0 && d2 == 0) {
                        c2 = 1;
                        d2 = 1;
                        a5 += 12 - tmpDigits[ix];
                    } else {
                        c2 = 0;
                        d2 = 0;
                        a5 += tmpDigits[ix];
                    }
                } else {
                    if (c2 == 0 && d2 == 0) {
                        c2 = 1;
                    } else {
                        c2 = 0;
                    }
                    a5 += tmpDigits[ix];
                }
            } else {
                if (tmpDigits[ix] > 5) {
                    if (c2 == 0) {
                        c2 = 1;
                        d2 = 0;
                        a5 += tmpDigits[ix] - 12;
                    } else {
                        c2 = 0;
                        d2 = 1;
                        a5 -= tmpDigits[ix];
                    }
                } else {
                    if (c2 == 0) {
                        c2 = 1;
                    } else {
                        c2 = 0;
                    }
                    a5 -= tmpDigits[ix];
                }
            }
        }
        while (a5 < 0 || a5 > 4) {
            if (a5 > 4) {
                a5 -= 5;
            } else {
                a5 += 5;
            }    
        }
        if (d2 == 0) {
            p = tab1[a5];
        } else {
            p = tab2[a5];
        }    
        if (p == tmpDigits[9]) {
            return true;
        }
        
        if (tmpDigits[3] == 0) {
            if (p > 4) {
                p -= 5;
            } else {
                p += 5;
            }    
            if (p == tmpDigits[9]) {
                return true;
            }    
        }
        return false;
    }



    //based on 06
   private static boolean checkMethod88(int accountDigits[], int accountLength) {
    	if (accountDigits[7]==9) {
            return checkMethod10(accountDigits, 8);
        }
        return checkMethod32(accountDigits,accountLength);
    }

    //based on 06
   private static boolean checkMethod89(int accountDigits[], int accountLength) {
        int ix;
        int weight;
        int sum = 0;

        if(accountLength <  7 || accountLength == 10) {
            return true;
        }
        
        if (accountLength == 8 || accountLength == 9) {
            return checkMethod10(accountDigits,accountLength);
        }
        
        // else: accountLength = 7
        for(ix=1; ix<7; ix++) {
            weight= accountDigits[ix] * (ix+1);
            // cross sum
            if (weight >9 ) {
                int mod = weight % 10;
                weight =  mod + (weight - mod) / 10;
            }
            sum += weight;
        }
        // last digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        return method06CheckDigit(sum) == accountDigits[0];
    }

    // based on 06
    private static boolean checkMethod90(int accountDigits[], int accountLength) {
        
        // method F (sachkonten):
        if(accountDigits[7] == 9) {
            return checkMethod10(accountDigits, 8);
        }
        
        //method A:
        if(checkMethod06(accountDigits, 7)) {
            return true;
        }    
        //method B:
        if(checkMethod06(accountDigits, 6)) {
            return true;
        }
        //method C:
        // use digits 5-9
        int sum = 0;
        for(int ix=1;ix<6;ix++) {
            sum += accountDigits[ix] * (ix+1);
        }
        if ((7 - (sum % 7)) % 7 == accountDigits[0]) {
            return true;
        }

        //method D:
        if ((9 - (sum %9)) % 9 == accountDigits[0]) {
            return true;
        }

        //method E:
        // use digits 5-9
        return checkMethod03(accountDigits, 6);
    }

    //based on 06
    private static boolean checkMethod91(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor3[] = {2,3,4,0,5,6,7,8,9,10};
        
        //method A:
        // 2,3,4,5,6,7
        for(ix=4; ix<accountLength; ix++) {
            sum += accountDigits[ix] * (ix-2);
        }
      
        if (method06CheckDigit(sum) == accountDigits[3]) {
            return true;
        }

        //method B:
        // 7,6,5,4,3,2
        sum=0;
        for(ix=4; ix<accountLength; ix++) {
            sum += accountDigits[ix] * (11-ix);
        }
           
        if (method06CheckDigit(sum) == accountDigits[3]) {
            return true;
        }

        //method C:
        sum=0;
        for(ix=0; ix<accountLength; ix++) {
            sum += accountDigits[ix] * factor3[ix];
        }
           
        if(method06CheckDigit(sum) == accountDigits[3]) {
            return true;
        }
        
        // method D
        int factorD[] = {2,4,8,5,10,9};
        sum=0;
        for(ix=4; ix < accountLength; ix++) {
            sum += accountDigits[ix] * factorD[ix - 4];
        }
        
        return (method06CheckDigit(sum) == accountDigits[3]);
    }

    //based on 00
    private static boolean checkMethod92(int accountDigits[], int accountLength) {
        return checkMethod01(accountDigits, 7);
    }

    // based on 06
    private static boolean checkMethod93(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        
        // Variante 1b
        if(accountLength < 7  && checkMethod33(accountDigits,accountLength)) {
            return true;
        }    
        // Variante 1a     
        for(ix=5;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix-3);
        }
        // 6th digit of account number must be 11 - sum modulo 11, 0 if this is 10 or 11
        if(method06CheckDigit(sum) == accountDigits[4]) {
            return true;
        }
        // Variante 2b
        sum = 0;
        if(accountLength < 7) {
            // use digits 5-9
            for(ix=1;ix<6;ix++) {
                sum += accountDigits[ix] * (ix+1);
            }
            // last digit of account number must be 7 - sum modulo 7, 0 if this is 7
            if((7 - (sum %7 )) % 7 == accountDigits[0]) {
                return true;
            }    
        }
        // Variante 2a
        // use digits 1-5
        sum = 0;
        for(ix=5;ix<accountLength;ix++) {
            sum += accountDigits[ix] * (ix-3);
        }
        // 6th digit of account number must be 7 - sum modulo 7, 0 if this is 7
        return ((7 - (sum %7 )) % 7 == accountDigits[4]);
    }

    //based on 00
    private static boolean checkMethod94(int accountDigits[], int accountLength) {
        int sum = 0;
        int weight;
        int ix;
        
        // take account number (without last digit), from right to left
        // multiply digits with 1,2,1,2,1,...
        for(ix=1;ix<accountLength;ix++) {
            weight = accountDigits[ix] * (2-(ix % 2));
            // take cross sum if weight > 9
            sum += (weight > 9) ? weight -9 : weight;
        }
       // last digit af account number must be 10 - this, or 0 if this is 10
        return ((10 - sum % 10) % 10 == accountDigits[0]);
    }

    // based on 06
    private static boolean checkMethod95_0(int accountDigits[], int accountLength, long accountNumber) {
        // some numbers have no check digit
        return ((accountNumber >= 1 && accountNumber <= 1999999)
            || (accountNumber >= 9000000 && accountNumber <= 25999999)
            || (accountNumber >= 396000000 && accountNumber <= 499999999)
            || (accountNumber >= 700000000 && accountNumber <= 799999999))
            || checkMethod06(accountDigits, accountLength);
    }
    
    // based on 06, valid from Sept. 9, 2013
    private static boolean checkMethod95_1(int accountDigits[], int accountLength, long accountNumber) {
        // some numbers have no check digit
        return ((accountNumber >= 1 && accountNumber <= 1999999)
            || (accountNumber >= 9000000 && accountNumber <= 25999999)
            || (accountNumber >= 396000000 && accountNumber <= 499999999)
            || (accountNumber >= 700000000 && accountNumber <= 799999999)
            || (accountNumber >= 910000000 && accountNumber <= 989999999)) /* <- new exception */
            || checkMethod06(accountDigits, accountLength);
    }
    
    private static boolean checkMethod96(int accountDigits[], int accountLength, long accountNumber) {
        // Variante 3
        return  ((accountNumber >= 1300000) && (accountNumber < 99400000))
        // Variante 1
                || checkMethod19(accountDigits, accountLength)
        // Variante 2
            || checkMethod00(accountDigits, accountLength);
    }
    
    private static boolean checkMethod97(int accountDigits[], int accountLength, long accountNumber) {      
        return (accountLength > 4 && accountDigits[0] == (((int) (accountNumber / 10)) % 11) % 10);
    }
    
    // based on 01
    private static boolean checkMethod98(int accountDigits[], int accountLength) {
        int ix;
        int sum = 0;
        int factor[] = {7,3,1};
        
        for(ix=1; ix<8; ix++) {
            sum += accountDigits[ix] * factor[ix % 3];
        }
        // last digit of account number must be 10 - this, or 0 if this is 10
        // if not, use method 32
        return ((10 - sum % 10) % 10 == accountDigits[0]) 
            || checkMethod32(accountDigits, accountLength);
    }
    
    //  based on 06
    private static boolean checkMethod99(int accountDigits[], int accountLength,
            long accountNumber) {
        return (accountNumber >= 396000000 && accountNumber <= 499999999)
            || checkMethod06(accountDigits, accountLength);
    }
    
    private static boolean checkMethodA0(int accountDigits[], int accountLength) {
        if(accountLength == 3) {
            return true;
        } 
        
        int sum = 0;
        int factor[] = {2,4,8,5,10};
        
        for(int ix=1;ix<6;ix++) {
            sum += accountDigits[ix] * factor[ix -1];
        }
        // last digit of account number must be 11 - sum / 11, or 0 if sum / 11 is 0 or 1
        int pz = 11 - (sum %11);
        if(pz > 9) {
            pz = 0;
        }    
        //System.out.println("sum: "+sum+", pz: "+pz);
        return (pz == accountDigits[0]);
    }
    
    // based on 00
    private static boolean checkMethodA1(int accountDigits[], int accountLength) {
        return (accountLength == 10 || accountLength == 8)
            && checkMethod00(accountDigits, accountLength);
    }
    
    // based on 00 and 04
    private static boolean checkMethodA2(int accountDigits[], int accountLength) {
        return checkMethod00(accountDigits, accountLength)
        	|| checkMethod04(accountDigits, accountLength);
    }
    
    // based on 00 and 10
    private static boolean checkMethodA3(int accountDigits[], int accountLength) {
        return checkMethod00(accountDigits, accountLength)
        	|| checkMethod10(accountDigits, accountLength);
    }
    
    //  based on 06 and 93
    private static boolean checkMethodA4(int accountDigits[], int accountLength) {
        // use digit 4 or 5 to 9 with check method 06
        int useLength = 7;
        if(accountDigits[6] == 9 && accountDigits[7] == 9) {
            useLength = 6;
        }
        boolean result = checkMethod06(accountDigits, useLength);
        
        if(!result) {
            int sum = 0;
            for(int ix=1; ix < 7; ix++) {
                sum += accountDigits[ix] * (ix + 1);
            }    
            result = (7 - sum % 7) % 7 == accountDigits[0];
            if(!result) {
                result = checkMethod93(accountDigits, accountLength);
            }
        }
        return result;
    }
    
    // based on 0 0and 10
    private static boolean checkMethodA5(int accountDigits[], int accountLength) {
        boolean result = checkMethod00(accountDigits, accountLength);
        if(!result && !(accountLength == 10 && accountDigits[9] == 9)) {
            result = checkMethod10(accountDigits, accountLength);
        }
        return result;            
    }
    
    // based on 00 and 01
    private static boolean checkMethodA6(int accountDigits[], int accountLength) {
        return (accountDigits[8] == 8) ? checkMethod00(accountDigits, accountLength)
                : checkMethod01(accountDigits, accountLength);
    }
    
    // based on 00 and 03
    private static boolean checkMethodA7(int accountDigits[], int accountLength) {
        return checkMethod00(accountDigits, accountLength)
            	|| checkMethod03(accountDigits, accountLength);
    }
    
    // based on 81 and 73, valid before March 7, 2005
    private static boolean checkMethodA8_0( int accountDigits[], int accountLength ) {
        boolean result = checkMethod81(accountDigits, accountLength);
        if(!result && !(accountDigits[7] == 9)) {
            result = checkMethod73(accountDigits, accountLength);
        }
        return result; 
    }
    
    // based on 06 and 00, valid from March 7, 2005
    private static boolean checkMethodA8_1( int accountDigits[], int accountLength ) {
        // exception: 3th digit is 9
        if( accountDigits[7] == 9 ) {
            return method51Exc( accountDigits, accountLength );
        }
        // Variante 1 and Variante 2
        return checkMethod06( accountDigits, 7 ) || checkMethod00( accountDigits, 7 );
    }
    
    // based on 01 and 06
    private static boolean checkMethodA9(int accountDigits[], int accountLength) {
        return checkMethod01(accountDigits, accountLength)
    	|| checkMethod06(accountDigits, accountLength); 
    }
    
    // based on 06
    private static boolean checkMethodB0(int accountDigits[], int accountLength) {
        boolean result = (accountLength == 10) && accountDigits[9] != 8;
        if(result && (accountDigits[2] == 0 || accountDigits[2] == 4
                || accountDigits[2] == 5 || accountDigits[2] > 6)) {
            result = checkMethod06(accountDigits, accountLength); 
        } // else check method 09, always returns true
        return result;
    }
    
    //  based on 05 and 01
    private static boolean checkMethodB1(int accountDigits[], int accountLength) {
        return checkMethod05(accountDigits, accountLength)
    	|| checkMethod01(accountDigits, accountLength); 
    }
    
    // based on 02 and 00
    private static boolean checkMethodB2(int accountDigits[], int accountLength) {
        return (accountDigits[9] < 8) ? checkMethod02(accountDigits, accountLength)
                : checkMethod00(accountDigits, accountLength); 
    }
    
    //  based on 32 and 06
    private static boolean checkMethodB3(int accountDigits[], int accountLength) {
        return (accountDigits[9] < 9) ? checkMethod32(accountDigits, accountLength)
                : checkMethod06(accountDigits, accountLength); 
    }
    
    //  based on 00 and 02
    private static boolean checkMethodB4(int accountDigits[], int accountLength) {
        return (accountDigits[9] == 9) ? checkMethod00(accountDigits, accountLength)
                : checkMethod02(accountDigits, accountLength); 
    }
    
    // based on 05 and 00
    private static boolean checkMethodB5(int accountDigits[], int accountLength) {
        return checkMethod05(accountDigits, accountLength) || ( accountDigits[9] < 8 
                && checkMethod00(accountDigits, accountLength));
    }
    
    // based on 20 and 53, valid before Sept. 5, 2011
    private static boolean checkMethodB6_0(int accountDigits[], int accountLength, int blz) {
        return (accountDigits[9] > 0) ? checkMethod20(accountDigits, accountLength)
                : checkMethod53(accountDigits, accountLength, blz);
    }

    // based on 20 and 53, valid from Sept. 5, 2011
    private static boolean checkMethodB6_1( final int accountDigits[], final int accountLength,
        final int blz ) {
        if( accountDigits[9] > 0
            || (accountDigits[8] == 2 && accountDigits[7] == 6 && accountDigits[6] == 9 && accountDigits[5] > 0) ) {
            return checkMethod20( accountDigits, accountLength );
        }
        return checkMethod53( accountDigits, accountLength, blz );
    }

    // based on 01
    private static boolean checkMethodB7(int accountDigits[], int accountLength, long accountNumber) {
        if((accountNumber >= 1000000 && accountNumber <= 5999999) || (accountNumber >= 700000000 
                && accountNumber <= 899999999)) {
            return checkMethod01(accountDigits, accountLength);
        }
        return true;
    }

    // based on 20 and 29, valid before June 6, 2011
    private static boolean checkMethodB8_0( int accountDigits[], int accountLength ) {
        return checkMethod20( accountDigits, accountLength )
            || checkMethod29( accountDigits, accountLength );
    }

    // based on 20 and 29, valid from June 6, 2011
    private static boolean checkMethodB8_1( final int accountDigits[], final int accountLength,
        final long accountNumber ) {
        return checkMethod20( accountDigits, accountLength )
            || checkMethod29( accountDigits, accountLength )
            || (accountDigits[9] == 5 && accountDigits[8] > 0)
            || (accountNumber >= 9010000000L && accountNumber <= 9109999999L);
    }

    private static boolean checkMethodB9(int accountDigits[], int accountLength) {
        int sum = 0;
        int pz;
        if(accountLength == 8) {
            int weight[] = {2,1,3} ;
            // weight 1,3,2,1,3,2,1            
            for(int ix = 1; ix < 8; ix++) {
                sum += ((accountDigits[ix] + 1) * weight[ix % 3]) % 11;
            }
            pz = sum % 10;   
        } else if(accountLength == 7) {
            for(int ix = 1; ix < 7; ix++) {
                sum +=(accountDigits[ix] * ix);
            }
            pz = sum % 11;
        } else {
            return false;
        }
        
        if(pz == accountDigits[0]) {
            return true;
        }
        
        pz += 5;
        if(pz > 9) {
            pz -= 10;
        }
        return pz == accountDigits[0];
    }
    
    private static boolean checkMethodC0(int accountDigits[], int accountLength, int blz) {
        return (accountLength == 8 && checkMethod52(accountDigits, accountLength, blz)) 
                || checkMethod20(accountDigits, accountLength);
    }
    
    private static boolean checkMethodC1(int accountDigits[], int accountLength) {
        if(accountDigits[9] != 5) {
            // Variante 1
            return checkMethod17(accountDigits, accountLength);
        }
        // Variante 2
        int sum = 0;
        int weight;
        
        // take account number (without last digit), from left to right
        // multiply digits with 1,2,1,2,1,...
        for(int ix=1; ix<accountLength; ix++) {
            weight = accountDigits[ix] * (2 - ix % 2);
            // take cross sum if weight > 9
            sum += (weight > 9) ? weight -9 : weight;
        }
        
        sum -= 1;
        int rem = sum % 11;
        int pz = (rem == 0) ? 0 : 10 - rem;
        return pz == accountDigits[0];
    }
    
    /*
     * based on 22 and 00
     */
    private static boolean checkMethodC2(int accountDigits[], int accountLength) {
        return checkMethod22(accountDigits, accountLength) 
                || checkMethod00(accountDigits, accountLength);
    }
    
    /*
     * valid from March 5, 2007, based on 00 and 58
     */
    private static boolean checkMethodC3(int accountDigits[], int accountLength) {
        return (accountDigits[9] == 9)
            ? checkMethod58( accountDigits, accountLength ) : checkMethod00( accountDigits,
                accountLength );
    }
    
    /*
     * valid from March 5, 2007, based on 15 and 58
     */
    private static boolean checkMethodC4( int accountDigits[], int accountLength ) {
        return (accountDigits[9] == 9)
            ? checkMethod58( accountDigits, accountLength ) : checkMethod15( accountDigits,
                accountLength );
    }
    
    /*
     * valid from September 3, 2007, based on 75, 29 and 00
     */
    private static boolean checkMethodC5( int accountDigits[], int accountLength ) {
        if ( (accountLength == 6 && accountDigits[5] < 9)
                || (accountLength == 9 && accountDigits[8] < 9) ) {
            return checkMethod75(accountDigits, accountLength);
        }
        if (accountDigits[9] == 1 || (accountDigits[9] > 3 && accountDigits[9] < 7)
                || accountDigits[9] == 9) {
            return checkMethod29(accountDigits, accountLength);
        }
        if (accountDigits[9] == 3 ) {
            return checkMethod00(accountDigits, accountLength);
        }
        return ( (accountLength == 8 && (accountDigits[7] > 2 && accountDigits[7] < 6)) || (accountDigits[9] == 7 && accountDigits[8] == 0)
                || (accountDigits[9] == 8 && accountDigits[8] == 5)); 
    }
    
    /*
     * valid from September 3, 2007, valid before March 9, 2009, based on 00
     */
    private static boolean checkMethodC6_0(int accountDigits[], int accountLength) {
        // take account number (without last and first digit), from right to left
        // multiply digits with 2,1,2,1,...
        int end = ( accountLength <= 9 ) ? accountLength : 9;
       
        // add "5499570" on the left
        // with weight factors and cross sums: add 0 + 7 + 1 + 9 + 9 + 4 + 1 = 31
        return checkMethod00Alike( accountDigits, 1, end, 31 );
    }
    
    /*
     * valid from March 9, 2009 until June 6, 2010, based on 00
     */
    private static boolean checkMethodC6_1(int accountDigits[], int accountLength) {        
        // first digit 0:
        // add "4451970" on the left
        // with weight factors and cross sums: add 0 + 7 + 9 + 1 + 1 + 4 + 8 = 30
        
        // first digit 1:
        // add "4451981" on the left
        // with weight factors and cross sums: add 2 + 8 + 9 + 1 + 1 + 4 + 8 = 33
        
        // first digit 2:
        // add "4451992" on the left
        // with weight factors and cross sums: add 4 + 9 + 9 + 1 + 1 + 4 + 8 = 36
        
        // first digit 7:
        // add "5499570" on the left
        // with weight factors and cross sums: add 0 + 7 + 1 + 9 + 9 + 4 + 1 = 31
       
        // first digit 9:
        // add "5499579" on the left
        // with weight factors and cross sums: add 9 + 7 + 1 + 9 + 9 + 4 + 1 = 40
        
        int add;        
        switch( accountDigits[9] ) {
            case 0:
                add = 30;
                break;
            case 1:
                add = 33;
                break;
            case 2:
                add = 36;
                break;
            case 7:
                add = 31;
                break;
            case 9:
                add = 40;
                break;
            default:
                // first digit 3,4,5,6 8 is not allowed
                return false;              
        }        
        // take account number (without last and first digit), from right to left
        // multiply digits with 2,1,2,1,...
        int end = ( accountLength <= 9 ) ? accountLength : 9;
        
        return checkMethod00Alike( accountDigits, 1, end, add );
    }

    /*
     * valid from June 7, 2010 until June 5, 2011, based on 00
     */
    private static boolean checkMethodC6_2(int accountDigits[], int accountLength) {        
        // first digit 0:
        // add "4451970" on the left
        // with weight factors and cross sums: add 0 + 7 + 9 + 1 + 1 + 4 + 8 = 30
        
        // first digit 1:
        // add "4451981" on the left
        // with weight factors and cross sums: add 2 + 8 + 9 + 1 + 1 + 4 + 8 = 33
        
        // first digit 2:
        // add "4451992" on the left
        // with weight factors and cross sums: add 4 + 9 + 9 + 1 + 1 + 4 + 8 = 36
        
        // first digit 3:
        // add "4451993" on the left
        // with weight factors and cross sums: add 6 + 9 + 9 + 1 + 1 + 4 + 8 = 38
        
        // first digit 7:
        // add "5499570" on the left
        // with weight factors and cross sums: add 0 + 7 + 1 + 9 + 9 + 4 + 1 = 31
       
        // first digit 9:
        // add "5499579" on the left
        // with weight factors and cross sums: add 9 + 7 + 1 + 9 + 9 + 4 + 1 = 40
        
        int add;        
        switch( accountDigits[9] ) {
            case 0:
                add = 30;
                break;
            case 1:
                add = 33;
                break;
            case 2:
                add = 36;
                break;
            case 3:
                add = 38;
                break;                
            case 7:
                add = 31;
                break;
            case 9:
                add = 40;
                break;
            default:
                // first digit 4,5,6 8 is not allowed
                return false;              
        }        
        // take account number (without last and first digit), from right to left
        // multiply digits with 2,1,2,1,...
        int end = ( accountLength <= 9 ) ? accountLength : 9;
        
        return checkMethod00Alike( accountDigits, 1, end, add );
    }
    
    /*
     * valid from June 6, 2011, based on 00
     */
    private static boolean checkMethodC6_3(int accountDigits[], int accountLength) {
        // first digit 0:
        // add "4451970" on the left
        // with weight factors and cross sums: add 0 + 7 + 9 + 1 + 1 + 4 + 8 = 30
        
        // first digit 1:
        // add "4451981" on the left
        // with weight factors and cross sums: add 2 + 8 + 9 + 1 + 1 + 4 + 8 = 33
        
        // first digit 2:
        // add "4451992" on the left
        // with weight factors and cross sums: add 4 + 9 + 9 + 1 + 1 + 4 + 8 = 36
        
        // first digit 3:
        // add "4451993" on the left
        // with weight factors and cross sums: add 6 + 9 + 9 + 1 + 1 + 4 + 8 = 38

        // first digit 5:
        // add "4344990" on the left
        // with weight factors and cross sums: add 0 + 9 + 9 + 4 + 8 + 3 + 8 = 41
        
        // first digit 6:
        // add "4344991" on the left
        // with weight factors and cross sums: add 2 + 9 + 9 + 4 + 8 + 3 + 8 = 43

        // first digit 7:
        // add "5499570" on the left0
        // with weight factors and cross sums: add 0 + 7 + 1 + 9 + 9 + 4 + 1 = 31
       
        // first digit 9:
        // add "5499579" on the left
        // with weight factors and cross sums: add 9 + 7 + 1 + 9 + 9 + 4 + 1 = 40
        
        int add;        
        switch( accountDigits[9] ) {
            case 0:
                add = 30;
                break;
            case 1:
                add = 33;
                break;
            case 2:
                add = 36;
                break;
            case 3:
                add = 38;
                break;
            case 5:
                add = 41;
                break;
            case 6:
                add = 43;
                break;
            case 7:
                add = 31;
                break;
            case 9:
                add = 40;
                break;
            default:
                // first digit 4 or 8 is not allowed
                return false;
        }        
        // take account number (without last and first digit), from right to left
        // multiply digits with 2,1,2,1,...
        int end = ( accountLength <= 9 ) ? accountLength : 9;
        
        return checkMethod00Alike( accountDigits, 1, end, add );
    }
    
    /*
     * valid from March 4, 2013, based on 00
     */
    private static boolean checkMethodC6_4(int accountDigits[], int accountLength) {
        // first digit 0:
        // add "4451970" on the left
        // with weight factors and cross sums: add 0 + 7 + 9 + 1 + 1 + 4 + 8 = 30
        
        // first digit 1:
        // add "4451981" on the left
        // with weight factors and cross sums: add 2 + 8 + 9 + 1 + 1 + 4 + 8 = 33
        
        // first digit 2:
        // add "4451992" on the left
        // with weight factors and cross sums: add 4 + 9 + 9 + 1 + 1 + 4 + 8 = 36
        
        // first digit 3:
        // add "4451993" on the left
        // with weight factors and cross sums: add 6 + 9 + 9 + 1 + 1 + 4 + 8 = 38

        // first digit 4:
        // add "4344992" on the left
        // with weight factors and cross sums: add 4 + 9 + 9 + 4 + 8 + 3 + 8 = 45

        // first digit 5:
        // add "4344990" on the left
        // with weight factors and cross sums: add 0 + 9 + 9 + 4 + 8 + 3 + 8 = 41
        
        // first digit 6:
        // add "4344991" on the left
        // with weight factors and cross sums: add 2 + 9 + 9 + 4 + 8 + 3 + 8 = 43

        // first digit 7:
        // add "5499570" on the left0
        // with weight factors and cross sums: add 0 + 7 + 1 + 9 + 9 + 4 + 1 = 31

        // first digit 8:
        // add "4451994" on the left
        // with weight factors and cross sums: add 8 + 9 + 9 + 1 + 1 + 4 + 8 = 40
       
        // first digit 9:
        // add "5499579" on the left
        // with weight factors and cross sums: add 9 + 7 + 1 + 9 + 9 + 4 + 1 = 40

        int add;
        switch( accountDigits[9] ) {
            case 0:
                add = 30;
                break;
            case 1:
                add = 33;
                break;
            case 2:
                add = 36;
                break;
            case 3:
                add = 38;
                break;
            case 4:
                add = 45;
                break;
            case 5:
                add = 41;
                break;
            case 6:
                add = 43;
                break;
            case 7:
                add = 31;
                break;
            case 8:
            case 9:
                add = 40;
                break;
            default:
                throw new IllegalArgumentException( "'" + accountDigits[9]
                    + "' is an invalid first digit" );
        }
        // take account number (without last and first digit), from right to left
        // multiply digits with 2,1,2,1,...
        int end = ( accountLength <= 9 ) ? accountLength : 9;
        
        return checkMethod00Alike( accountDigits, 1, end, add );
    }

    /*
     * valid from December 3, 2007, based on 63 and 06
     */
    private static boolean checkMethodC7( int accountDigits[], int accountLength ) {
        return checkMethod63( accountDigits, accountLength )
            || checkMethod06( accountDigits, accountLength );
    }
    
    /*
     * valid from June 9, 2008, based on 00, 04 and 07
     */
    private static boolean checkMethodC8( int accountDigits[], int accountLength ) {
        return checkMethod00( accountDigits, accountLength )
            || checkMethod04( accountDigits, accountLength )
            || checkMethod07( accountDigits, accountLength );
    }
    
    /*
     * valid from June 9, 2008, based on 00 and 07
     */
    private static boolean checkMethodC9( int accountDigits[], int accountLength ) {
        return checkMethod00( accountDigits, accountLength )
            || checkMethod07( accountDigits, accountLength );
    }
    
    /*
     * valid from Sept 8, 2008, based on 20
     */
    private static boolean checkMethodD0( int accountDigits[], int accountLength ) {
        return (accountDigits[9] == 5 && accountDigits[8] == 7)
            || checkMethod20( accountDigits, accountLength );
    }

    /*
     * valid from Sept 8, 2008 until June 6 2010, based on 00
     */
    private static boolean checkMethodD1_0( int accountDigits[], int accountLength ) {
        if( accountDigits[9] == 0 || accountDigits[9] == 3 || accountDigits[9] == 9 ) {
            return false;
        }
        // add "428259" on the left of the account number and treat it as in check method 0
        // with weight factors and cross sums: add 8 + 2 + 7 + 2 + 1 + 9 = 29 to the sum
        return checkMethod00Alike( accountDigits, 1, accountLength, 29 );
    }

    /*
     * valid from June 7 2010, based on 00
     */
    private static boolean checkMethodD1_1( int accountDigits[], int accountLength ) {
        final int addOn;
        if( accountDigits[9] == 0 || (accountDigits[9] > 2 && accountDigits[9] < 6)
            || accountDigits[9] == 9 ) {
            // first digit 0, 3, 4, 5, 9: add 436338 on the left of the account number
            // with weight factors and cross sums: add 8 +3 + 3 + 3 + 6 + 8 = 31 to the sum
            addOn = 31;
        } else {
            // first digit 1, 2, 6, 7, 8: add "428259" on the left of the account number
            // with weight factors and cross sums: add 8 + 2 + 7 + 2 + 1 + 9 = 29 to the sum
            addOn = 29;
        }
        return checkMethod00Alike( accountDigits, 1, accountLength, addOn );
    }
    
    /*
     * valid from March 7 2011, based on 00
     */
    private static boolean checkMethodD1_2( int accountDigits[], int accountLength ) {
        if( accountDigits[9] == 2 || accountDigits[9] == 7 || accountDigits[9] == 8 ) {
            return false;
        }
        // first digit 0, 1, 3, 4, 5, 6, 9: add 436338 on the left of the account number
        // with weight factors and cross sums: add 8 +3 + 3 + 3 + 6 + 8 = 31 to the sum
        return checkMethod00Alike( accountDigits, 1, accountLength, 31 );
    }
    
    /*
     * valid from Sept 5 2011, based on 00
     */
    private static boolean checkMethodD1_3( final int accountDigits[], final int accountLength ) {
        if( accountDigits[9] == 7 || accountDigits[9] == 8 ) {
            return false;
        }
        // first digit 0, 1, 2, 3, 4, 5, 6, 9: add 436338 on the left of the account number
        // with weight factors and cross sums: add 8 +3 + 3 + 3 + 6 + 8 = 31 to the sum
        return checkMethod00Alike( accountDigits, 1, accountLength, 31 );
    }
    /*
     * valid from March 4 2013, based on 00
     */
    private static boolean checkMethodD1_4( final int accountDigits[], final int accountLength ) {
        // first digit 0, 1, 2, 3, 4, 5, 6, 9: add 436338 on the left of the account number
        // with weight factors and cross sums: add 8 +3 + 3 + 3 + 6 + 8 = 31 to the sum
        return accountDigits[9] != 8 && checkMethod00Alike( accountDigits, 1, accountLength, 31 );
    }

    /*
     * valid from Dec 8, 2008, based on 95, 00 and 68
     */
    private static boolean checkMethodD2_0( int accountDigits[], int accountLength, long accountNumber ) {
        return checkMethod95_0( accountDigits, accountLength, accountNumber )
            || checkMethod00( accountDigits, accountLength )
            || checkMethod68( accountDigits, accountLength );
    }
    
    /*
     * valid from Sept 9, 2013, based on 95, 00 and 68
     */
    private static boolean checkMethodD2_1( int accountDigits[], int accountLength, long accountNumber ) {
        return checkMethod95_1( accountDigits, accountLength, accountNumber )
            || checkMethod00( accountDigits, accountLength )
            || checkMethod68( accountDigits, accountLength );
    }
    
    /*
     * valid from Dec 8, 2008, based on 00 and 27
     */
    private static boolean checkMethodD3( int accountDigits[], int accountLength ) {
        return checkMethod00( accountDigits, accountLength )
            || checkMethod27( accountDigits, accountLength );
    }
    
    /*
     * valid from June 7, 2010 to June 5, 2011, based on 00
     */
    private static boolean checkMethodD4_0( int accountDigits[], int accountLength ) {
        // 3,4,5,9 are allowed as first digit
        if( accountDigits[9] < 3 || (accountDigits[9] > 5 && accountDigits[9] < 9 ) ) {
            return false;
        }
        // add "428259" on the left of the account number and treat it as in check method 0
        // with weight factors and cross sums: add 8 + 2 + 7 + 2 + 1 + 9 = 29 to the sum
        return checkMethod00Alike( accountDigits, 1, accountLength, 29 );
    }

    /*
     * valid from June 6, 2011, based on 00
     */
    private static boolean checkMethodD4_1( final int accountDigits[], final int accountLength ) {
        // 0 not allowed as first digit
        // add "428259" on the left of the account number and treat it as in check method 0
        // with weight factors and cross sums: add 8 + 2 + 7 + 2 + 1 + 9 = 29 to the sum
        return accountDigits[9] != 0 && checkMethod00Alike( accountDigits, 1, accountLength, 29 );
    }

    /*
     * valid from December 6, 2010, based on 06
     */
    private static boolean checkMethodD5( int accountDigits[], int accountLength ) {
        if( accountDigits[7] == 9 && accountDigits[6] == 9 ) {
            // Variante 1 - weight 2, 3, 4, 5, 6, 7, 8, 0, 0
            return checkMethod10( accountDigits, 8 );
        }

        // Variante 2 - Modulus 11, weight 2, 3, 4, 5, 6, 7, 0, 0, 0
        // Variante 3 - Modulus 7, weight 2, 3, 4, 5, 6, 7, 0, 0, 0
        // Variante 4 - Modulus 10, weight 2, 3, 4, 5, 6, 7, 0, 0, 0
        int sum = 0;

        for( int ix = 1; ix < 7; ix++ ) {
            sum += accountDigits[ix] * (ix + 1);
        }

        return method06CheckDigit( sum ) == accountDigits[0]
            || (7 - (sum % 7)) % 7 == accountDigits[0]
            || (10 - (sum % 10)) % 10 == accountDigits[0];

    }

    /*
     * valid from March 7, 2011, based on 07, 03, 00
     */
    private static boolean checkMethodD6( int accountDigits[], int accountLength ) {
        return checkMethod07( accountDigits, accountLength )
            || checkMethod03( accountDigits, accountLength )
            || checkMethod00( accountDigits, accountLength );
    }
    
    /*
     * valid from June 6, 2011
     */
    private static boolean checkMethodD7( final int accountDigits[], final int accountLength ) {
        final int varSum = checkMethod00Sum( accountDigits, 1, accountLength );
        
        return varSum % 10 == accountDigits[0];
    }

    /*
     * valid from June 6, 2011
     */
    private static boolean checkMethodD8( final int accountDigits[], final int accountLength ) {
        return accountLength == 8
            || (accountLength == 10 && checkMethod00( accountDigits, accountLength ));
    }

    /*
     * valid from June 4, 2012, based on 0, 10 and 18
     */
    private static boolean checkMethodD9( final int accountDigits[], final int accountLength ) {
        return checkMethod00( accountDigits, accountLength )
            || checkMethod10( accountDigits, accountLength )
            || checkMethod18( accountDigits, accountLength );
    }

    /*
     * valid from March 4, 2013, based on 00
     */
    private static boolean checkMethodE0( int accountDigits[], int accountLength ) {
        return checkMethod00Alike( accountDigits, 1, accountLength, 7 );
    }
}
