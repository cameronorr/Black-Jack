import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * This class is used to write to a file when called, and will write in different ways based on the method
 * @author Cameron Orr
 *
 */
public class Write {

	public static void createFile(String file, ArrayList<String> answer){
		
	try{
				
				File listOfAccounts = new File(file);
				
				PrintWriter infoToWrite = new PrintWriter(
				new BufferedWriter(
						new FileWriter(listOfAccounts)));
				
				infoToWrite.println(answer);
				infoToWrite.close();
			}
		
			catch(IOException e){
				System.out.println("An I/O Error Occurred");
				System.out.println(e);
				
				System.exit(0);
			
			}
		
		}
	public static void createFile(String fileName, String usernameInput, String passwordInput, int wins, int losses){
		
		try{
					
					// Creates a File object that allows you to work with files on the hard drive
					
					File listOfAccounts = new File(fileName);
					
					// FileWriter is used to write streams of characters to a file
					// BufferedWriter gathers a bunch of characters and then writes
					// them all at one time (Speeds up the Program)
					// PrintWriter is used to write characters to the console, file
			
					PrintWriter infoToWrite = new PrintWriter(
					new BufferedWriter(
							new FileWriter(listOfAccounts, true)));
					
					infoToWrite.print(usernameInput);
					infoToWrite.print(",");
					infoToWrite.print(passwordInput);
					infoToWrite.print(",");
					infoToWrite.print(wins);
					infoToWrite.print(",");
					infoToWrite.println(losses);
					infoToWrite.close();
				}
			
				// You have to catch this when you call FileWriter
				
				catch(IOException e){
					System.out.println("An I/O Error Occurred");
					System.out.println(e);
					// Closes the program
					
					System.exit(0);
				
				}
			
			}
	
	}