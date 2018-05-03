import java.util.*;
import java.io.*;
import java.text.*;
import java.lang.Object;

//Christopher Salazar
//Description: The program reads a file that has a set of default hours for a work week, hours
//separated by commas. The next data is the employees' ids and names. Next is the availability 
//hours between a date range with the id attached to that line. The id can have multiple date ranges
//with different hours. Date ranges can be null. Each employees date ranges are separated by a space
//which the program uses to know how many blank spaces to keep count of. The last function in the
//main method what is suppose to do is read through the entire data set that is saved in an array 
//and print out each date between the date range with its accompanying hours.
public class Work {

	private static int totalEmployees = 0;
	private static String defaultHours = "";
	private static String [][] idNum;

	public static void main (String [] args) {
		
		try {
			Scanner in = new Scanner(new File("input.txt"));

			for(int i =0; i< 3; i++) {//the first 3 lines
				String currentLine = in.nextLine();
			}
			String currentLine1 = in.nextLine();
			setDefaultHours(currentLine1);
			System.out.println(getDefaultHours() + " DEFAULTHOURS");
			
			for(int i =0; i< 2; i++) {
				String currentLine = in.nextLine();
			}
			
			//vector needs to be dynamic for multiplie availability times
			idNum = new String [10][20]; //10 rows by 20 columns, 4-9 are hours
			employees(in, idNum);

			for(int j = 0; j < getTotal(); j++) {
				for(int k = 0; k < 3; k++) {
					System.out.println("Employee " + idNum[j][k]);
				}
			}
			availability(in, idNum);
			setArray(idNum);

		} catch (IOException e) {

		}
		find_available_work_hours(1, "12/16/2015", "01/15/2016");
		find_available_work_hours(2, "12/16/2015", "01/15/2016");
		System.out.println("This is the total number of employees " + totalEmployees);
	}

	//Function: availability
	//Parameters: Scanner in, String idNum [] []
	//Description: The functions checks the date ranges per paragraph while 
	//knowing the number of total employees deermined by the funtion employees.
	//Availability will add to the existing vector created in function employees and
	//add only the dates and hours and skip the id since it is already in the idNum array.
	public static void availability(Scanner in, String idNum [] []) {
		//column from 4 to 9 for the hours, 10 -19 new dates and hours 		
		in.nextLine();
		int nextRow = 0;//row of id employees. 0 equals id 1
		int nextColumn = 2;//start third column since id and name are already added.		
		int jumpSpaces = getTotal();//total number of empty lines = totalemployees
				
		while(jumpSpaces != 0) {
			int rowData = 1;//split line start second index
			String availLine = in.nextLine();
			String [] words = availLine.split(",");
			
			if(words.length == 1) {// empty line
				availLine = in.nextLine();//skip # line
				jumpSpaces--;
				nextRow++;//next employee data
				nextColumn = 2;
			} else {
				while(words.length > rowData) {
					idNum[nextRow][nextColumn] = words[rowData];
					if(rowData != words.length) {
						rowData++;//next index of line
						nextColumn++;
					} else {
						continue;
					}
				}
			}
		}

	}

	//Function: employees
	//Parameters: Scanner in, String idNum[][]
	//Description: The function reads in the 6th line where the list of ids with names
	//starts. It saves the id and name in a double array. It keeps count on the number
	//of total employees, this number will help in knowing how many empty spaces there are to follow
	//since each employee has different date ranges and are separated by a space. 
	public static void employees (Scanner in, String idNum [][]) {
			
			int rowCount = 0; //number of rows in array
			int employ = 0;//number of total employees, lines in paragraph
			
			String line2 = in.nextLine();
			while (!(line2.isEmpty())) {//while the current line is not empty
				int columnCount = 0;//number of columns in a row
				int rowData = 0;//index of the line split

				String [] words = line2.split(",");
				while(words.length > rowData) {//to avoid index out of bounds
					//insert words index into idNum approriate column
					idNum[rowCount][columnCount] = words[rowData];			
					rowData++;//move to next index of line split
					columnCount++;//add to next column of row of the employee data
				}
				employ++;//once end of line, go to next and cound employee
				rowCount++;//next row of employee data
				line2 = in.nextLine();//go to next line
			}
			setTotal(employ);//call function to set total number of employees
	}

	//Function: setTotal
	//Parameters: int total
	//Description: Sets the total number of employees
	public static void setTotal(int total) {
		totalEmployees = total;
	}

	//Function: getTotal
	//Parameters: empty
	//Description: Gets the total number of employees
	public static int getTotal() {
		return totalEmployees;
	}

	//Function: setDefaultHours
	//Parameters: String hours
	//Description: Sets the total number hours 
	public static void setDefaultHours(String hours) {
		defaultHours = hours;
	}

	//Function: getDefaultHours
	//Parameters: empty
	//Description: Gets the default hours
	public static String getDefaultHours() {
		return defaultHours;
	}

	//Function: getArray
	//Parameters: empty
	//Description: Gets the array that holds the employee's id,name,date ranges, and horus
	public static String[][] getArray () {
		return idNum;
	}

	//Function: setArray
	//Parameters: String ids [][]
	//Description: Set the array that holds employee's data, this is used to call the array within
	//a function.
	public static void setArray(String ids [][]) {
		idNum = ids;
	}	

	//Function: find_available_work_hours
	//Parameters: int user_id, String from, String to
	//Description: This function takes in an id number with a date range and should return
	//the dates in that range (inclusive) with the hours
	public static void find_available_work_hours(int user_id, String from, String to) {
		System.out.println("****Testing my find function****");
		Calendar startDate = Calendar.getInstance();
		String temp [][] = getArray();
		for(int search = 1; search < 20; search++) {
			System.out.print(temp[user_id-1][search] + " ");
		}
		System.out.println();
	}

}
