package Capstone;

import javax.swing.JOptionPane;

public class Capstone 
{
	public Capstone() 
	{
		String message;

		String theNumber;

		String tempNumber = "";

		String theResult = "";
		String theDecimal = "";

		message = "please input the number to convert to words";

		theNumber = inputNumberToConvert(message);

		message = "your number, " + theNumber;
		
		theResult = decodeNegatives(theNumber);
		theNumber = removeMinusSign(theNumber);

		theDecimal = decodeDecimals(theNumber);

		theNumber = removeDecimal(theNumber);

		// strip the decimal point and digit to the right of the decimal point

		theResult = convertThousandsToWords(theNumber, theResult);
		theResult = convertHundredsToWords(theNumber, theResult);

		theResult += theDecimal;

		if (message.length() == 1 && message.contains("0"))
		{
			theResult = "zero";
		}
		message += " in words is " + theResult;

		JOptionPane.showMessageDialog(null, message);
		
	}
	
	private String convertHundredsToWords(String theNumber, String theResult) 
	{
		// final three digits
		if (theNumber.length() > 3)
			theNumber = theNumber.substring(theNumber.length() - 3);
		//System.out.println("theNumber = " + theNumber);

		if (theNumber.length() > 0)
			theResult += threeCharNumberToWords(theNumber);
		return theResult;
	}

	private String convertThousandsToWords(String theNumber, String theResult) 
	{
		String thousands;
		if (theNumber.length() > 3) {
			thousands = theNumber.substring(0, theNumber.length() - 3);
			//System.out.println("thousands = " + thousands);

			theResult += threeCharNumberToWords(thousands);
			theResult += " thousand ";
		}
		return theResult;
	}

	private String removeDecimal(String theNumber) 
	{
		if (theNumber.contains("."))
			theNumber = theNumber.substring(0, theNumber.indexOf('.'));
		// System.out.println(theNumber);
		return theNumber;
	}

	private String removeMinusSign(String theResult) 
	{
		if (theResult.contains("-"))
			theResult = theResult.substring(1);

		return theResult;
	}

	public String threeCharNumberToWords(String theNumber) 
	{
		String theResult = "";
		int charNumber = 1;

		// (hundreds + tens + ones) "thousands" + (hundreds + tens + ones) + decimals

		theResult += decodeHundreds(theNumber);

		if (theNumber.length() > 1) {
			charNumber = 1;
			if (theNumber.length() == 2)
				charNumber = 0;
			// System.out.println(theNumber+"\t"+theNumber.charAt(charNumber));
			char theChar = theNumber.charAt(charNumber);
			theResult += decodeTensChar(theChar, theNumber) + " ";
		}

		if (theNumber.length() == 1 || theNumber.charAt(charNumber) != '1')
			theResult += decodeOnes(theNumber);

		return theResult;
	}

	public String inputNumberToConvert(String message) 
	{

		String temp;

		do {

			temp = JOptionPane.showInputDialog(message);

		} while (!canBeDouble(temp));

		return temp;
	}

	public boolean canBeDouble(String s) 
	{

		try {
			Double.parseDouble(s);
		} catch (Exception e) 
		{
			return false;
		} finally {

		}
		
		return true;
	}

	public String decodeOnes(String theNumber) 
	{

		if (theNumber.length() > 0) 
		{

			char theChar = findChar(theNumber, 1);

			return decodeOnesAndHundredsChar(theChar);
		}

		return "";
	}

	public String decodeTens(String theNumber) 
	{
		if (theNumber.length() > 1) {
			char theChar = theNumber.charAt(2);// findChar(theNumber, 2);

			if (theChar == '1') {
				return decodeTeens(theNumber);
			} else {
				return decodeTensChar(theChar, theNumber) + " ";
			}
		}

		return "";
	}

	public String decodeHundreds(String theNumber) 
	{
		if (theNumber.length() > 2) 
		{

			char theChar = findChar(theNumber, 3);

			return decodeOnesAndHundredsChar(theChar) + " hundred ";

		}
		
		return "";
	}

	public String decodeNegatives(String theNumber) 
	{
		if (theNumber.charAt(0) == '-')
			return " negative ";

		return "";
	}

	public String decodeDecimals(String theNumber) 
	{
		// return is the decimal in words, not the string
		if (theNumber.contains(".")) {

			if (theNumber.charAt(theNumber.length() - 1) == '.')
				return "";

			if (theNumber.charAt(theNumber.length() - 2) == '.')
				return " point " + decodeOnesAndHundredsChar(theNumber.charAt(theNumber.length() - 1));
		}

		return "";
	}

	public String decodeOnesAndHundredsChar(char theChar) 
	{
		String theWord;

		switch (theChar)
		{

		case '0':
			theWord = "";
			break;
		case '1':
			theWord = "one";
			break;
		case '2':
			theWord = "two";
			break;
		case '3':
			theWord = "three";
			break;
		case '4':
			theWord = "four";
			break;
		case '5':
			theWord = "five";
			break;
		case '6':
			theWord = "six";
			break;
		case '7':
			theWord = "seven";
			break;
		case '8':
			theWord = "eight";
			break;
		case '9':
			theWord = "nine";
			break;
		default:
			theWord = "";
			break;
		}

		return theWord;
	}

	public String decodeTensChar(char theChar, String theNumber) 
	{
		String theWord;

		switch (theChar) 
		{
		case '0':
			return "";
		case '1':
			return decodeTeens(theNumber);
		case '2':
			theWord = "twenty";
			break;
		case '3':
			theWord = "thirty";
			break;
		case '4':
			theWord = "fourty";
			break;
		case '5':
			theWord = "fifty";
			break;
		case '6':
			theWord = "sixty";
			break;
		case '7':
			theWord = "seventy";
			break;
		case '8':
			theWord = "eighty";
			break;
		case '9':
			theWord = "ninety";
			break;
		default:
			theWord = "";
			break;
		}

		return theWord;
	}

	public String decodeTeens(String theNumber) 
	{
		// given: the tens char is a '1'

		String theWord;

		char theChar = findChar(theNumber, 1);

		switch (theChar) 
		{

		case '0':
			theWord = "ten";
			break;
		case '1':
			theWord = "eleven";
			break;
		case '2':
			theWord = "twelve";
			break;
		case '3':
			theWord = "thirteen";
			break;
		case '4':
			theWord = "fourteen";
			break;
		case '5':
			theWord = "fifteen";
			break;
		case '6':
			theWord = "sixteen";
			break;
		case '7':
			theWord = "seventeen";
			break;
		case '8':
			theWord = "eighteen";
			break;
		case '9':
			theWord = "nineteen";
			break;
		default:
			theWord = "";
			break;
		}

		return theWord;
	}

	public char findChar(String theNumber, int theDigit) 
	{
		char theChar;

		int numChars = theNumber.length();

		numChars -= theDigit;

		theChar = theNumber.charAt(numChars);

		// System.out.println("theChar = " + theChar);

		return theChar;

	}

	public String convert(int theNumber) 
	{

		return null;

	}
}