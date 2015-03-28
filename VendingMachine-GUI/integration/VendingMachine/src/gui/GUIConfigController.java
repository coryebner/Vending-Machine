package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUIConfigController {
	public static void main(String [] args){
		String typeInput = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer typeInputInt = 0; //The integer equivalent of the input string
		
		int reportingConfig = 0;

		do{
			System.out.println("Please enter the vending machine configuration you wish to boot:");
			System.out.println(
					"  1  - VMRUS-SFF-P/C\n" +
					"  2  - VMRUS-SFF-P/CI\n" +
					"  3  - VMRUS-SFF-P/PI\n" +
					"  4  - VMRUS-COM-P/MI\n" +
					"  5  - VMRUS-COM-P/M\n" +
					"  6  - VMRUS-TOC-P/MI\n" +
					"  7  - VMRUS-TOC-P/I\n" +
					"  8  - VMRUS-COM-C/MI\n" +
					"  9  - VMRUS-COM-C/M\n" +
					"  10 - VMRUS-TOC-C/MI\n" +
					"  11 - VMRUS-TOC-C+\n" +
					"  12 - VMRUS-TOC-C+/I\n");
			try{
				typeInput = br.readLine();
				typeInputInt = Integer.parseInt(typeInput);
			}catch(Exception e){
				e.printStackTrace();}
		}while(!(typeInputInt > 0 && typeInputInt < 13)); //Enforce 1 - 12
		
		do{
			System.out.println("Select your remote reporting configuration:\n"
					+ "Enter 1 for Every Transaction\n"
					+ "      2 for Every Hundred Transactions\n"
					+ "      3 for Every Day At 4:00am (Not Supporting Yet)\n");
			try{
				String tmp = br.readLine();
				reportingConfig = Integer.parseInt(tmp);
			}catch(Exception e){
				e.printStackTrace();}
		}while(reportingConfig != 1 && reportingConfig !=2 ); //enforce choices
		
		switch(typeInputInt){
		case 1:
			//
			break;
		case 2:
			//
			break;
		case 3:
			//
			break;
		case 4:
			//
			break;
		case 5:
			//
			break;
		case 6:
			//
			break;
		case 7:
			//
			break;
		case 8:
			//
			break;
		case 9:
			//
			break;
		case 10:
			//
			break;
		case 11:
			//
			break;
		case 12:
			//
			break;
		default:
			System.out.println("Invalid input - this state should never be reached");
		}
	}
}
