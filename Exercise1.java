// Exercise1.java
// Objective: Apply basic Java knowledge
// Creates a Java program that generates a 2-Dimensional Array (Table form) of random ASCII characters.

package com.exercise.one;

import java.util.Scanner;

public class Exercise1 {
	
	public static void main(String args[]) 
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter input for table dimension: ");
		String dimension = scanner.nextLine();
		
		int dimensionValue = Integer.parseInt(dimension);			// array size
		String[][] table = new String[dimensionValue][dimensionValue];	// table array
		int elementLen = 3;						// initial size of elements inside the array
		
		// generate 3 random string  per cell
		int leftLimit = 33;				// from char '!'
		int rightLimit = 126;			// to   char 'z'
		
		// for each row in the table
		for (int i = 0; i < table.length; i++) {
			// for each cell in the row
			for (int j = 0; j < table[i].length; j++) {
				
				// characters that will be in each cell
				StringBuilder buffer = new StringBuilder(dimensionValue);
				
				for (int k=0; k < elementLen; k++) {
					
					// random number from 97 to 122 inclusive
					int randomLimitedInt = leftLimit + 
						(int) (Math.random() * (rightLimit - leftLimit + 1));
					
					buffer.append((char) randomLimitedInt);
				}
				
				// add the String of 3 characters into the cell
				table[i][j] = buffer.toString(); 
			}
		} // end generate 3 random string per cell
		
		// read the command from user as a string
		System.out.print("\nPick a command to proceed [search, edit, print, reset, exit]: ");
		String command = scanner.nextLine();
		
		while (true) {
			
			switch (command.toLowerCase()) {
				case "search":
					System.out.print("Enter search term: ");
					String query = scanner.nextLine();
					int count=0;
					int found=0;
					
					for (int i = 0; i < table.length; i++) {
						for (int j = 0; j < table[i].length; j++) {
							
							String mainStr = table[i][j];
							int mainStrLen = mainStr.length();
							int index = 0;
							count = 0;
							
							while (index <= mainStrLen - query.length() && 
								mainStr.contains(query)) {
									
								String mainSubStr = table[i][j].substring(index, index + query.length());
								
								if (query.equals(mainSubStr)) {
									count++;
								}
								
								index++;
							}
							
							if (count>0) {
								System.out.println("Found " + query + " on (" + i + ", "+j+
									") with " + count + " instances");
								found=1;
							} 
						}
					}
					
					if (found==0) {
						System.out.println("Nothing found.");
					}
									
					System.out.println();
					break; // end case search 
				
				case "edit":
					System.out.print("--Enter the index of the cell separated by comma(,): ");
					String cellToEdit = scanner.nextLine();
					String[] matrix = cellToEdit.split(",");
					int[] index = new int[matrix.length];
					
					for (int i = 0; i < matrix.length; i++) {
						index[i] = Integer.parseInt(matrix[i]);
					}
					
					int idx1 = index[0];
					int idx2 = index[1];
					
					while (true) {
						if(((table.length-1) >= idx1) && ((table.length-1) >= idx2)) {
							System.out.print("Enter the new value: ");
							table[index[0]][index[1]] = scanner.nextLine();
							break;
						} else {
							System.out.println("Index out of bounds try again\n");
						}
						
						System.out.print("--Enter the index of the cell separated by comma(,): ");
						cellToEdit = scanner.nextLine();
						matrix = cellToEdit.split(",");
						index = new int[matrix.length];
						
						for (int i = 0; i < matrix.length; i++) {
							index[i] = Integer.parseInt(matrix[i]);
						}
						
						idx1 = index[0];
						idx2 = index[1];
						
					} // end while loop
					
					System.out.println();
					break; // end case edit
					
				case "print":
					System.out.println("--Printing the table\n");
					for (int i = 0; i < dimensionValue; i++) {
						for (int j = 0; j < dimensionValue; j++) {
							System.out.print("[ " + table[i][j] + " ]");
							if (j < dimensionValue-1) {
								System.out.print(", ");
							}
						} // end for each cell
						System.out.println();
					} // end for each row
					System.out.println();
					break; // end case print
					
				case "reset":
					System.out.print("--Enter input for new table dimension: ");
					dimensionValue = Integer.parseInt(scanner.nextLine());
					table = new String[dimensionValue][dimensionValue];			
					System.out.println();
					
					// generate 3 random string  per cell
					for (int i = 0; i < table.length; i++) {
						for (int j = 0; j < table[i].length; j++) {
							
							StringBuilder buffer = new StringBuilder(dimensionValue);
							
							for (int k = 0; k < elementLen; k++) {
								
								int randomLimitedInt = leftLimit + 
									(int) (Math.random() * (rightLimit - leftLimit + 1));
								buffer.append((char) randomLimitedInt);
							}
							
							table[i][j] = buffer.toString();
						}
					}
					break; // end case reset
					
				case "exit":
					System.out.println("Exiting the program..");
					System.exit(0);
					// break;
					
				default:
					System.out.println("Invalid command\n");
					
			}	// end switch structure
			
			System.out.print("Pick a command to proceed [search, edit, print, reset, exit]: ");
			command = scanner.nextLine();
			
		} // end menu picking
		
	} // end method main
	
} // end class Exercise1