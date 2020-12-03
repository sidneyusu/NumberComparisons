/*Author: Sidney Shane Dizon; UCID: 10149277; Assignment 1 CPSC 331 
 * 
 *  
 *  This program will address the problem of comparison, addition and subtraction of extremely large numbers 
 constructed from thousands of digits.*/

import java.util.Scanner;

public class CompAddSubtNumbers {

	public static void main(String[] args) {
		
		
		//Section I - Asks from user to insert two numbers 
		//Pre-Condition: Input of two Strings representing a decimal number that follows the ff. criteria:
		// i. First part is the integer part of the number 
		// ii. Second part is the decimal part of the number 
		// iii. The above mentioned two parts are separated by a dot “.” 
		// Only characters allowed to be used are:
		//  i. The decimal numbers {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} 
		//  ii. The symbol    .    (dot) 
		//Post-Condition: Stores the input into 4 variables, namely:
		// i. numBeforeDec1Final
		// ii. numBeforeDec2Final
		// iii. numAfterDec1Final
		// iv. numAfterDec2Final
		
		Scanner input = new Scanner(System.in);
		//Get input from user
		System.out.println("Enter the first number you wish to compare.");
		String num1 = input.nextLine();
		System.out.println("Enter the second number you wish to compare.");
		String num2 = input.nextLine();
		//String Variables are stored for comparison
		String numBeforeDec1 = getNumberBeforeDecimal(num1);
		String numBeforeDec2 = getNumberBeforeDecimal(num2);
		String numAfterDec1Temp = getNumberAfterDecimal(num1);
		String numAfterDec2Temp = getNumberAfterDecimal(num2);
		String numAfterDec1Final = getFinalNumber(numAfterDec2Temp, numAfterDec1Temp);
		String numAfterDec2Final = numAfterDec2Temp;
		String numBeforeDec1Final = getFinalNumberB(numBeforeDec2, numBeforeDec1);
		String numBeforeDec2Final = numBeforeDec2;
		//Get the final format for the numbers after the decimal ,with same number
		// of digits
		// For number after the decimal, if first number has more digits than the second 
		// number then make them of same number of digits by adding "0" at the end
		if (firstHasMoreDigits(numAfterDec1Temp, numAfterDec2Temp)){
			numAfterDec2Final = getFinalNumber(numAfterDec1Temp, numAfterDec2Temp);
			numAfterDec1Final = numAfterDec1Temp;
		}
		else if (numAfterDec1Temp.equals(numAfterDec2Temp)){
			numAfterDec1Final = numAfterDec1Temp;
			numAfterDec2Final = numAfterDec2Temp;
		}
		// For number before the decimal, if first number has more digits than the second 
		// number then make them of same number of digits by adding "0" at the end, and then reverse it
		if (firstHasMoreDigits(numBeforeDec1, numBeforeDec2)){
			numBeforeDec2Final = getFinalNumberB(numBeforeDec1, numBeforeDec2);
			numBeforeDec1Final = numBeforeDec1;
		}
		else if (numBeforeDec1.equals(numBeforeDec2)){
			numBeforeDec1Final = numBeforeDec1;
			numBeforeDec2Final = numBeforeDec2;
		}
		
		
		// Section II - Calculates which numbers is larger 
		//Pre-Condition: Values of the four String variables from the user input
		//Post-Condition: The String representation of the Larger Number once comparison is done
		
		//If the first number is the larger number, it will return the first number, otherwise 
		// return the second number
		String larger;
		if (firstIsLarger(numBeforeDec1, numBeforeDec2, numAfterDec1Final, numAfterDec2Final))
			larger = "Results:\nLarger Number is: "+ numBeforeDec1 +"."+ numAfterDec1Temp;
		else
			larger = "Results:\nLarger Number is: "+ numBeforeDec2 +"."+ numAfterDec2Temp;
		
		
		//Section III -  Adds the two numbers 
		//Pre-Condition: Values of the four String variables from the user input
		//Post-Condition: String representation of the sum of the numbers
		
		//Get Sum of Numbers After the Decimal
		String sumOfNumAfterDec = getSum(numAfterDec1Final, numAfterDec2Final);
		String sumOfNumBeforeDec = "";
		//Check for carry-overs for numbers before the decimal
		if (numAfterDec1Final.length() < sumOfNumAfterDec.length()){
			//Add 1 to the first number to compensate for the carry over
			String carryOver = getFinalNumber(numBeforeDec1Final, "1");
			String reversedCarryOver = reverseString(carryOver);
			String num1BeforeDecWithCarryOver = getSum(numBeforeDec1Final, reversedCarryOver);
			//Then now, get the sum of the numbers
			sumOfNumBeforeDec = getSum(num1BeforeDecWithCarryOver, numBeforeDec2Final);
			sumOfNumAfterDec = sumOfNumAfterDec.substring(1);
		}
		//else there is no carry-over so just add the numbers
		else
			sumOfNumBeforeDec = getSum(numBeforeDec1Final, numBeforeDec2Final);
		
		
		//Section IV -  Subtracts the smaller number from the larger number 
		//Pre-Condition: Values of the four String variables from the user input
		//Post-Condition: String representation of the difference of the numbers
		
		//Get Difference of numbers
		String diffOfNumAfterDec = "";
		String diffOfNumBeforeDec = "";
		//If the first number is larger, then just subtract the second number
		if (firstIsLarger(numBeforeDec1, numBeforeDec2, numAfterDec1Final, numAfterDec2Final)){
			diffOfNumAfterDec = getDifference(numAfterDec1Final, numAfterDec2Final);
			//Check for borrow-overs from the number After the Decimal
			if (diffOfNumAfterDec.substring(0, 1).equals("b")){
				String borrowOver = getFinalNumberB(numBeforeDec1Final, "1");
				String num1BeforeDecWithBorrowOver = getDifference(numBeforeDec1Final, borrowOver);
				//Now get the difference for the numbers before the decimal
				diffOfNumBeforeDec = getDifference(num1BeforeDecWithBorrowOver, numBeforeDec2Final);
				diffOfNumAfterDec = diffOfNumAfterDec.substring(1);
			}
			//there is no borrow-Overs
			else{
				diffOfNumBeforeDec = getDifference(numBeforeDec1Final, numBeforeDec2Final);
			}
		}
		//Else, the second number is larger so subtract the first number to the second
		else{
			diffOfNumAfterDec = getDifference(numAfterDec2Final, numAfterDec1Final);
			//Check for borrow-overs from the number After the Decimal
			if (diffOfNumAfterDec.substring(0, 1).equals("b")){
				String borrowOver = getFinalNumberB(numBeforeDec2Final, "1");
				String num2BeforeDecWithBorrowOver = getDifference(numBeforeDec2Final, borrowOver);
				//Now get the difference for the numbers before the decimal
				diffOfNumBeforeDec = getDifference(num2BeforeDecWithBorrowOver, numBeforeDec1Final);
				diffOfNumAfterDec = diffOfNumAfterDec.substring(1);
			}
			//there is no borrow-Overs
			else{
				diffOfNumBeforeDec = getDifference(numBeforeDec2Final, numBeforeDec1Final);
			}
		}
		
		
		//Section V - Prints on screen the results from sections (S2), (S3) and (S4) 
		//Pre-Condition: String Representation of the larger number, sum of numbers, and 
		// difference of the numbers
		//Post-Condition: Prints on the Screen the Results altogether
		
		//Print the larger number
		System.out.println(larger);
		//Print the sum of the numbers
		System.out.println("Sum: " + sumOfNumBeforeDec + "." + 
				sumOfNumAfterDec);
		//print the difference of the numbers
		System.out.println("Difference: " + diffOfNumBeforeDec + "." + diffOfNumAfterDec);
	
	
		input.close();
	}

	/**
	 * This method gets the numbers before the decimal of the string
	 * @param inputStr string input of the user
	 * @return	Short value of the string before the decimal
	 */
	public static String getNumberBeforeDecimal(String inputStr){
		//keeps record of the numbers before the decimal, starts off with an empty string 
		String numBeforeDec = "";
		 Short i = 0 ;
		//while-loop to go over each number in the string
		while ( i<inputStr.length()){
			//method to get each number in the string
			String number = inputStr.substring(i, i+1);
			i = (short) (i+1);
			//If the decimal appears, stop adding numbers to numBeforeDec
			if (number.equals("."))
				break;
			//If the decimal point does not appear yet, concatenate the numbers to numBeforeDec
			else 
				numBeforeDec = numBeforeDec.concat(number);
		}
		
		//Return the value before the decimal
		return numBeforeDec;
	}
	/**
	 * This method gets the numbers after the decimal of the string
	 * @param inputStr string input of the user
	 * @return Short value of the string after the decimal
	 */
	public static String getNumberAfterDecimal(String inputStr){
		//keeps record of the numbers after the decimal, starts off with an empty string 
		String numAfterDec = "";
		Short i = 0;
			//while-loop to go over each number in the string
			while (i<inputStr.length()){
			//method to get each number in the string
			String number = inputStr.substring(i, i+1);
				////If the decimal appears, start adding numbers to numAfterDec
				if (number.equals(".")){
					numAfterDec = numAfterDec.concat(inputStr.substring(i+1));
					break;
				}
			i = (short) (i+1);
			}
		//Return the value after the decimal
		return numAfterDec;
	}
	
	/**
	 * Check to see if the numbers has same number of digits
	 * @param num1 first number inputted by user
	 * @param num2 second number inputted by user
	 * @return returns true if the first number as more digits than the second, otherwise false
	 */
	public static Boolean firstHasMoreDigits (String num1, String num2){
		Short num1StrLng = (short) num1.length();
		Short num2StrLng = (short) num2.length();
		//If first number has more digits return true
		if (num1StrLng > num2StrLng)
			return true;
		return false;
	}
	/**
	 * Get method to acquire number with zeros to have equal length size for numbers after decimal
	 * @param num1 number with larger number of digits
	 * @param num2 number with smaller number of digits
	 * @return returns the final number wit zeros so that num1 and num2 have the same number of digits
	 */
	public static String getFinalNumber (String num1, String num2){
		Short num1StrLng = (short) num1.length();
		Short num2StrLng = (short) num2.length();
		String numFinal = num2;
		Short i=0;
				
		//Add zeros to have equal length of numbers
		while (i<num1StrLng-num2StrLng){
			numFinal = numFinal.concat("0");
			i = (short) (i+1);
		}
		return numFinal;
	}
	
	/**
	 * Get method to acquire number with zeros to have equal length size for numbers after decimal
	 * @param num1 number with larger number of digits
	 * @param num2 number with smaller number of digits
	 * @return returns the final number with zeros so that num1 and num2 have the same number of digits
	 */
	public static String getFinalNumberB (String num1, String num2){
		Short num1StrLng = (short) num1.length();
		Short num2StrLng = (short) num2.length();
		String numFinal = num2;
		String zero = "0";
		Short i=0;
				
		//Add zeros to have equal length of numbers
		while (i<num1StrLng-num2StrLng){
			numFinal = zero.concat(numFinal);
			i = (short) (i+1);
		}
		return numFinal;
	}
	
	
	
	/**
	 * This method returns TRUE if the first number is larger than the second
	 * @param num1	First number input of the user
	 * @param num2	Second number input of the user
	 * @return	Return True or False 
	 */
	public static Boolean firstIsLarger (String numBeforeDec1, String numBeforeDec2, 
			String numAfterDec1, String numAfterDec2){
		Boolean bool = null;
		// For number before the decimal, if first number is larger than the second number 
		// then return True
		if (numBeforeDec1.length() > numBeforeDec2.length()){
			bool = true;
		}
		// For number before the decimal, if first number has same digits as the second number 
		// then check each digits of the number
		else if (numBeforeDec1.length() == numBeforeDec2.length()){
			//If the first number is larger than the second number, then return true
			if (compareNumbers(numBeforeDec1, numBeforeDec2))
				bool = true;
			//If first is equal to the second number then compare numbers after the decimal
			else if (numBeforeDec1.equals(numBeforeDec2)){
					//If first number is larger than the second then return true or if
					// the two numbers are equal
					if ((compareNumbers(numAfterDec1, numAfterDec2)) || 
							numAfterDec1.equals(numAfterDec2))
						bool = true;
					else
						bool = false; 
			}	
			//else, first number is less than second number so return false
			else
				bool = false;
		}
		//else return false, because second number is larger than the first
		else
			bool = false;
	return bool;
	}
	
	/**
	 * Compares two numbers whether the first number is larger than the second
	 * @param num1 first number to compare
	 * @param num2 second number to be compared by the first number
	 * @return returns true if the first number if larger, otherwise false
	 */
	public static Boolean compareNumbers (String num1, String num2){
		Short i = 0;
		Boolean bool = false;
		
		while (i < num1.length()){
			//Parse to Short to get each digit in the String
			Short first = Short.parseShort(num1.substring(i, i+1));
			Short second = Short.parseShort(num2.substring(i, i+1));
			//if the digit of the first number is larger then return true
			if(first > second){
				bool = true;
				break;
			}
			//if the digits of both numbers are equal then continue to compare
			else if (first == second)
				i = (short) (i+1);
			//if the digit of the second number is larger, then return false
			else{
				bool = false;
				break;
			}	
		}
		return bool;
	}
	

	/**
	 * Calculates the sum of two numbers
	 * @param num1 first number
	 * @param num2 second number
	 * @return returns a String representation of the sum
	 */
	public static String getSum(String num1, String num2){
		Short i = (short) num1.length();
		String sumStr = "";
		boolean carryOver = false;
		
		while (i>0){
			//Parse to Short to get each digit in the string
			Short first = Short.parseShort(num1.substring(i-1, i));
			Short second = Short.parseShort(num2.substring(i-1, i));
			//Add the numbers
			Short sum = (short) (first + second);
			
			if (!carryOver){
				//Check for carry overs
				if (sum > 9){
					sumStr = sumStr.concat(Short.toString(sum).substring(1));
					carryOver = true;
					i = (short) (i-1);
				}
				//If no carry Overs
				else{
					sumStr = sumStr.concat(Short.toString(sum));
					carryOver = false;
					i = (short) (i-1);
				}
			}
			else{
				sum = (short) (sum+1);
				if (sum > 9){
					sumStr = sumStr.concat(Short.toString(sum).substring(1));
					carryOver = true;
					i = (short) (i-1);
				}
				//If no carry Overs
				else{
					sumStr = sumStr.concat(Short.toString(sum));
					carryOver = false;
					i = (short) (i-1);
				}
			}
		}
		
		if (carryOver)
			sumStr = sumStr.concat("1");
		//return the reverse of the sumStr
		return reverseString(sumStr);
		
	}
	
	/**
	 * Reverses the order of the String
	 * @param inputStr String to be reversed
	 * @return Returns a String representation of the reversed String
	 */
	public static String reverseString(String inputStr){
		Short i = (short) inputStr.length();
		String reversedStr = "";
		
		while (i>0){
			reversedStr = reversedStr.concat(inputStr.substring(i-1, i));
			i = (short) (i-1);
		}
		return reversedStr;	
	}
	
	/**
	 * Calculates the difference between two numbers
	 * @param num1 first number
	 * @param num2 second number
	 * @return returns a String representation of the difference
	 */
	public static String getDifference(String num1, String num2){
		Short i = (short) num1.length();
		String differenceStr = "";
		boolean borrowOver = false;
		
		while (i>0){
			//Parse to Short to get each digit in the string
			Short first = (short) Short.parseShort(num1.substring(i-1, i));
			Short second = (short) Short.parseShort(num2.substring(i-1, i));
			
			if (!borrowOver){
				//Check if there is a need to borrow
				if(first < second){
					first = (short) (first + 10);
					Short diff = (short) (first - second);
					borrowOver = true;
					differenceStr = differenceStr.concat(diff.toString());
					i = (short) (i-1);
				}
				//Else there is no need to borrow, so just subtract the numbers
				else{
					Short diff = (short) (first - second);
					differenceStr = differenceStr.concat(diff.toString());
					borrowOver = false;
					i = (short) (i-1);
				}
			}
			else{
				//subtract 1 from the 1st number to compensate for the borrow from the previous digit
				first = (short) (first - 1);
				//Check if there is a need to borrow
				if(first < second){
					first = (short) (first + 10);
					Short diff = (short) (first - second);
					borrowOver = true;
					differenceStr = differenceStr.concat(diff.toString());
					i = (short) (i-1);
				}
				//Else there is no need to borrow, so just subtract the numbers
				else{
					Short diff = (short) (first - second);
					differenceStr = differenceStr.concat(diff.toString());
					borrowOver = false;
					i = (short) (i-1);
				}
				
			}	
		}
		//If there is borrow over then put a marking 
		if (borrowOver)
			differenceStr = differenceStr.concat("b");
		return reverseString(differenceStr);
	}

	
	
}
