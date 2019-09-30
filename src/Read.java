import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class is used to read files, and return certain information depending on the method.
 * @author Cameron Orr
 *
 */
public class Read {
	
	public static ArrayList<String> getInput(String file){
		
		
		try {
			
					File listOfAccounts = new File(file);
					
					@SuppressWarnings("resource")
					BufferedReader getInfo = new BufferedReader(
							new FileReader(listOfAccounts));
					
					ArrayList<String> fileLine = new ArrayList<String>();
					
					String accountInfo = getInfo.readLine();
					
					while(accountInfo != null){
						fileLine.add(accountInfo);
						accountInfo = getInfo.readLine();
					}
					return fileLine;
				}
				
		catch (FileNotFoundException e) {
			
			System.out.println("Couldn't Find the File");
			System.exit(0);
		}
		
		catch(IOException e){
			
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		
		}
			return null;		
	}
public static ArrayList<String> getScore(String username, String file){
		
		
			try {
				
				File listOfAccounts = new File(file);
				
				@SuppressWarnings("resource")
				BufferedReader getInfo = new BufferedReader(
						new FileReader(listOfAccounts));
				
				ArrayList<String> fileLine = new ArrayList<String>();
				
				String accountInfo = getInfo.readLine();
				int wins = 0;
				int losses = 0;
				String nameScore = null;
				
				while(accountInfo != null){
					if(accountInfo.contains(username)) {
						String[] elements = (accountInfo.split(","));
						wins += Integer.parseInt(elements[elements.length - 2]);
						losses += Integer.parseInt(elements[elements.length - 1]);
						nameScore = elements[0];
						
					}
					accountInfo = getInfo.readLine();
				}
				Integer.toString(wins);
				Integer.toString(losses);
				fileLine.add(nameScore + " " + wins + "-" + losses);
				return fileLine;
			}
			
		catch (FileNotFoundException e) {
		
		System.out.println("Couldn't Find the File");
		System.exit(0);
		}
		
		catch(IOException e){
		
		System.out.println("An I/O Error Occurred");
		System.exit(0);
		
		}
		return null;		
		}
	
public static ArrayList<String> getLeaderboard(String file){
	
	
	try {
		
				File listOfAccounts = new File(file);
				
				@SuppressWarnings("resource")
				BufferedReader getInfo = new BufferedReader(
						new FileReader(listOfAccounts));
				
				ArrayList<String> fileLine = new ArrayList<String>();
				
				String accountInfo = getInfo.readLine();
				
				while(accountInfo != null){
						String[] elements = (accountInfo.split(","));
						String nameScore = elements[0] + " " + elements[elements.length - 2] + "-" + elements[elements.length - 1];
						fileLine.add(nameScore);
						
					accountInfo = getInfo.readLine();
				}
				return fileLine;
			}
			
	catch (FileNotFoundException e) {
		
		System.out.println("Couldn't Find the File");
		System.exit(0);
	}
	
	catch(IOException e){
		
		System.out.println("An I/O Error Occurred");
		System.exit(0);
	
	}
		return null;		
}
	public static boolean compareInput(String usernameInput, String loginInfo){
		
		File listOfAccounts = new File(loginInfo);
		try {
					
					@SuppressWarnings("resource")
					BufferedReader getInfo = new BufferedReader(
							new FileReader(listOfAccounts));

					
					String accountInfo = getInfo.readLine();
					
					while(accountInfo != null){
						
						if(accountInfo.contains(usernameInput)) {
							getInfo.close();
							return true;
						}
						accountInfo = getInfo.readLine();
					}
				}
				
				// Can be thrown by FileReader
		catch (FileNotFoundException e) {
			
			System.out.println("Couldn't Find the File");
			System.exit(0);
		}
		
		catch(IOException e){
			
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		
		}
			return false;		
	}
}
