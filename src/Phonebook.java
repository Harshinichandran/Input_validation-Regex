import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
	public class Phonebook
	{
		public static void main(String[] args)
		{
			String choice=null;
			try {
					choice = args[0]; 
					//Displays error message if user choice is not add/list/delete
					if(!(choice.toLowerCase().equals("add"))&&!(choice.toLowerCase().equals("delete"))&&!(choice.toLowerCase().equals("list"))) 
					{
						System.err.println("Choice (Add/List/Delete) was not provided.");
						Help();
						System.exit(1);
					}
			 	}
			catch (Exception e)
			{
				//Displays error message if empty value for choice is provided
				if(choice==null) {
					System.err.println("Choice (Add/List/Delete) was not provided. Args[0] cannot be empty");
					Help();
					System.exit(1);
					
				}
				
			}
		
			
			if(choice.toLowerCase().equals("add"))
			{
				String Name = null,PhNum=null;
				try {
					Name = args[1];
					PhNum = args[2];
						if((Name!=null)&&(PhNum!=null))
						{
							Add(Name,PhNum);
							
						}
					}
				catch (Exception e)
				{
						if((Name==null)||(PhNum==null))
						{
							System.err.println("Invalid Inputs for add. Name or Number argument is missing");
							System.out.println("Please use the following format during execution: add Name Number");
							System.exit(1);
						}
				}
				 
			}
			if(choice.toLowerCase().equals("delete"))
			{
				String Value=null, Value2=null;
				try {
					Value2=args[2]; //displays error message if two values are provided for the delete function.
					System.err.println("Argument two should be empty for delete");
					System.exit(1);
					
				}
				catch(Exception e)
				{
					//System.out.println(e);
				}
							  
				try {
				  Value=args[1];
				
				if(Value!=null) 
				  {
					int delname=ValidName(Value); //Checks if user called Delete() function with name as input. 
					
						if(delname==0)
						{
							DeleteName(Value);
							
						}
						else if(delname==1)
						{
							int delnum=ValidNumber(Value); //Checks if user called Delete() function with number as input
								if(delnum==0)
								{
									DeleteNumber(Value);
									
								}
								else 
								{
									System.err.println("Regular Expression check for Name and Number failed.");
									System.out.println(" Please provide a valid Name/Number for Delete");
									System.exit(1);
									
								}
						}
				  }
			  }
				
			  catch(Exception e)
			  {
				 //Displays error message if no input is provided for the delete function
				  if((Value==null))
					{
						System.err.println("Name or Number to delete is not specified");
						System.out.println("Please use the following format during execution: delete Name or delete Number");
						System.exit(1);
						
					}
				
			  }
				
			}
				
			
			
			 if(choice.toLowerCase().equals("list"))
			{
				 String Name=null,Number=null;
				//List function has no arguments and displays error message if any arguments are provided. 
				 try
				 {
					 Name=args[1];
					 Number=args[2];
					 System.err.println("Invalid Inputs for List. Argument for Name and Number should be empty");
					 System.exit(1);
				 }
				 catch(Exception e)
				 {
					 if((Name!=null)||(Number!=null))
					 {
						 System.err.println("Invalid Inputs for List. Argument for Name and Number should be empty");
						 System.exit(1);
					 }
					 else 
					 {
						 List();
					 }
				 
				 } 						 
				
				 
            }
		
		
	   }
		
		public static void Help()
		{
			System.out.println("*------------------Inputs------------------*");
			System.out.println("Enter inputs in one of the followng format");
			System.out.println("Java Phonebook Add args1 args2		(args1=> Name; args2=> Phone Number)");
			System.out.println("Java Phonebook List");
			System.out.println("Java Phonebook Delete args1		(args1=> Name/Phone number)");
		}
				
	public static void List()
		{
			File file = new File("Phonebook.txt");
			String path = file.getAbsolutePath(); //Gets the absolute path of the text file if it is present in the same directory as the class file
			        	BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(path));
						} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
						String line = null;
						try 
						{
							while ((line = br.readLine()) != null) 
							{
								System.out.println(line);
							}
							System.exit(0);
						} 
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(1);
						}
		}	
	
	 public static void Add(String name, String num) 
	 {
		/*ValidName() & ValidNumber()functions compares the inputs from the command line arguments to their respective regular expressions */
		 
		 int checkname=ValidName(name);  
		 int checknum=ValidNumber(num);
		 
		if(checkname==0&&checknum==0)
		{
		 String text=null;
		 text= name.concat("\t");
		 text=text.concat(num);
		 	BufferedWriter bw = null;
			FileWriter fw = null;
			File file = new File("Phonebook.txt");
			try
			{
				fw = new FileWriter(file.getAbsolutePath(), true);
				bw = new BufferedWriter(fw);					 
		        	bw.write(text);
		        	bw.write("\r\n");     		
		        	bw.close();
		           	System.out.println("Contact details with "+name+" "+num+" was successfully added to file");
		        	System.exit(0);
		      } 
			
		      catch (IOException e) 
			{
  				System.err.println(e);
  				System.exit(1);
			}
			
		}
	    
		else if(checkname==1)
		{
			 System.err.println("Failed Regular Expression Check. Provided name is not in the given valid form");
			 System.exit(1);
		}
		else if(checknum==1)
		{
			 System.err.println("Failed Regular Expression Check. Entered number is not of valid form");
			 System.exit(1);
		}
	 }
			
	
	 public static void DeleteName(String s)
	 {
		 String line;
		 String text="";
		 String NametoDel=s.toLowerCase();
		 BufferedWriter bw = null;
			FileWriter fw = null;
			File file = new File("Phonebook.txt");
			try
			{
				FileReader fr = new FileReader(file.getAbsolutePath());
				FileReader fr1 = new FileReader(file.getAbsolutePath());
				Scanner input = new Scanner(fr);
				Scanner CheckEOF= new Scanner(fr1);
	        	fw = new FileWriter(file.getAbsolutePath(), true);
				bw = new BufferedWriter(fw); 
	        	int count=0,flag=0;
					        	
	        	while (CheckEOF.hasNextLine()) //Gets the total number of lines in the File
	        	{
	        	    flag++;
	        	    CheckEOF.nextLine();
	        	}
	        	
	        	if (flag==0)
	        	{
	        		System.err.println("File is Empty. No information to delete");
	        		System.exit(1);
	        	}
				 while(input.hasNextLine()) //Checks and deletes the Name to be deleted in the file
				{
					count++;
					line=input.nextLine();
					StringTokenizer st = new StringTokenizer(line,"\t"); //Retrieves name and phone number from each line using "\t" as delimiter
					String name=st.nextToken();
					
					if(name.toLowerCase().equals(NametoDel))
						{
							line = "";
							count=0;
							System.out.println("Contact details of person with the Name "+s+" is deleted");
						}
					if (count!=0)
						{
							bw.write(line);
							bw.newLine();
						}
					
					if(count==flag) // Displays error message when non-existing data is accessed by user
					 {
						 System.err.println("Data does not exist in the list");
						 System.exit(1);
					 }
					
				}
				 
				 FileOutputStream File = new FileOutputStream(file.getAbsolutePath());
				 File.write(text.getBytes());
				 input.close();
				 bw.close();
				 File.close();
				 fr.close();
				 fr1.close();
				System.exit(0);
			}
			 catch (IOException e) 
			{

				e.printStackTrace();
				System.exit(1);

			}
	 }
			
	 public static void DeleteNumber(String n)
			 {
				 String line;
				 String text="";
				 String NumtoDel=n.toLowerCase();
				 BufferedWriter bw = null;
					FileWriter fw = null;
					File file = new File("Phonebook.txt");
					try
					{
						FileReader fr = new FileReader(file.getAbsolutePath());
						FileReader fr1 = new FileReader(file.getAbsolutePath());
						Scanner input = new Scanner(fr);
						Scanner CheckEOF= new Scanner(fr1);
			        	fw = new FileWriter(file.getAbsolutePath(), true);
						bw = new BufferedWriter(fw); 
			        	int count=0,flag=0;
						
			        	while (CheckEOF.hasNextLine()) //Gets the total number of lines in the File
			        	{
			        	    flag++;
			        	    CheckEOF.nextLine();
			        	}
				
			        	if (flag==0)
			        	{
			        		System.err.println("File is Empty. No information to delete");
			        		System.exit(1);
			        	}
						
			        	while(input.hasNextLine()) //Checks and deletes the Number to be deleted in the file
						{
							 count++;
							line=input.nextLine();
							StringTokenizer st = new StringTokenizer(line,"\t");//Retrieves name and phone number from each line using "\t" as delimiter 
							String temp=st.nextToken();
							String number=st.nextToken();//Phone Number Token
								if(number.toLowerCase().equals(NumtoDel))
								{
									line = "";
									count=0;
									System.out.println("Contact details of person with Phone number "+n+" is deleted");
		                        }
								
								if (count!=0)
								{
									bw.write(line);
									bw.newLine();
								}
							
								if(count==flag)  // Displays error message when non-existing data is accessed by user
								{
									System.err.println("Data does not exist in the list");
									System.exit(1);
								}
						}
						 
						 FileOutputStream File = new FileOutputStream(file.getAbsolutePath());
						 File.write(text.getBytes());
						 input.close();
						
				         bw.close();
						 File.close();
						 System.exit(0);
					}
					 catch (IOException e) 
					{

						e.printStackTrace();
						System.exit(1);

					}
	           }
	 
		

			 
  	 public static int ValidName(String s)
	 {
  		 //Compares the "Name" input from the command line arguments to the regular expression
		Pattern pattern = Pattern.compile("^[A-Z]\\'?[a-zA-Z]{1,20}\\,?\\s?([A-Z]\\'?[a-zA-Z]{1,20}?[-|\\s]?)?[a-zA-Z]{1,20}?\\.?$");
  		Matcher matcher = pattern.matcher(s);
		    
		    if (matcher.matches()) 
		    {
		    	return 0;
		    }
		    else 
		    {	
		    	return 1;
		    }
	 }
  	 
  	 public static int ValidNumber(String num)
  	 
  	 {
  		//Compares the "Name" input from the command line arguments to the regular expression
  		Pattern pattern = Pattern.compile("(^\\d{5}$)|(^\\d{5}\\.\\d{5}$)|(^\\+[1-9]{1,2}\\s?\\(|^[0][1][1]\\s[1]?\\s?\\(?|^[1]\\s?\\(|^\\(?)([1-9]\\d{1,2})?\\)?[-\\s]?(\\d{3})[-|\\s](\\d{4})$");
  		    Matcher matcher = pattern.matcher(num);
  		
  	    	if (matcher.matches()) 
  	    	{
  	    		return 0;
  	    	}
  	    	else 
  	    	{	
  	    		return 1;
  	    	}
  	 }
}
  		
  	

  		 
  	 
	 
	
