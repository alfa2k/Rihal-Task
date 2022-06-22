package ahmedFateen;

import java.io.File;
import java.io.*;
import net.sourceforge.tess4j.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.io.File;


public class TasksByRihal {
	
	public static void main(String args[]) throws IOException{
		
		File imageFile = new File("C:\\Users\\smart\\OneDrive\\Desktop\\ahmedFateen\\images\\RihalTest.png");
		
		ITesseract instance = new Tesseract();
		instance.setDatapath("C:\\Users\\smart\\OneDrive\\Desktop\\ahmedFateen\\tessdata");
		
		try {
			
			String textfile = instance.doOCR(imageFile);
			System.out.println(textfile);
			
			{
			      String text = "From: Al Amri, Salim <salim.amri@gmail.com>\nSent: 25 August 2021 17:20\nTo: Al Harthi, Mohammed <mohd4.king@rihal.om>\nCc: Al hajri, Malik <hajri990@ocaa.co.om>; Omar, Naif <nnnn49@apple.com>\nSubject: Conference Rooms Booking Details\nDear Mohammed,\nAs per our last discussion these are the available conference rooms available for booking along with their rates for full day:\nRoom: Luban, available on 26/09/2021. Rate: $4540\nRoom: Mazoon, available on 04/12/2021 and 13/02/2022. Rate: $3000\nRoom: Dhofar. Available on 11/11/2021. Rate: $2500 Room: Nizwa. Available on 13/12/2022. Rate: $1200\nPlease let me know which ones you are interested so we go through more details.\nBest regards,\nSalim Al Amri";
			      
			      String[] dates = getDate(text);
			      
			      //Date Extraction
			      System.out.println("---DATES---");
			      ConvertDate(dates[0]);
			      ConvertDate(dates[1]);
			      ConvertDate(dates[2]);
			      ConvertDate(dates[3]);
			      ConvertDate(dates[4]);   
			      
			      //Rooms Names 
			      System.out.println("\n---ROOMS---");
			      int FirstRoom = text.indexOf("Room: ");
			      System.out.println(text.substring(FirstRoom+6,385));
			      
			      int SecondRoom = text.indexOf("Room: ", FirstRoom+6);
			      System.out.println(text.substring(SecondRoom+6,436));
			      
			      int ThirdRoom = text.indexOf("Room: ", SecondRoom+6);
			      System.out.println(text.substring(ThirdRoom+6,502));
			      
			      int FourthRoom = text.indexOf("Room: ", ThirdRoom+6);
			      System.out.println(text.substring(FourthRoom+6,552));
			      
			      //Rooms Rates
			      System.out.println("\n---RATES---");
			      int FirstRate = text.indexOf("Rate: "); 
			      System.out.println(text.substring(FirstRate+6,423));
			      
			      int SecondRate = text.indexOf("Rate: ",FirstRate+6); 
			      System.out.print(text.substring(SecondRate+6,490));
			      
			      int ThirdRate = text.indexOf("Rate: ",SecondRate+6); 
			      System.out.println(text.substring(ThirdRate+6,541));
			      
			      int FourthRate = text.indexOf("Rate: ",ThirdRate+6); 
			      System.out.print(text.substring(FourthRate+6,591));
			      
			      //Extracting Names
			      System.out.println("\n---NAMES---");
			      
			      int FirstName = text.indexOf("Al ");
			      System.out.println(FirstName); 
			      System.out.print(text.substring(FirstName+9,20)+" ");
			      System.out.println(text.substring(FirstName, 13));
			      
			      int SecondName = text.indexOf("Al ",FirstName+9);
			      System.out.println(SecondName); 
			      System.out.print(text.substring(SecondName+11,94)+" ");
			      System.out.println(text.substring(SecondName, 84));
			      
			      int ThirdName = text.indexOf("Al ",SecondName+9);
			      System.out.println(ThirdName); 
			      System.out.print(text.substring(ThirdName+10,137)+" ");
			      System.out.println(text.substring(ThirdName, 129));
			      
			      //Extracting Emails
			      System.out.println("\n---EMAILS---");
			      Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(text);
			      while (m.find()) 
			      {
			         System.out.println(m.group());
			      }
			      
			   }
			   
		}catch(TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
	public static void ConvertDate(String txtdates)
	   {
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	      
	      try{
	      
	         Date localDate = formatter.parse(txtdates);
	         formatter.applyPattern("yyyy/MM/dd");
	         txtdates = formatter.format(localDate);
	         System.out.println(txtdates);  
	         } catch(ParseException e) 
	         {
	            e.printStackTrace();
	         }  
	   }
	   
	   private static String[] getDate(String texts) 
	   {
	      int count=0;
	      String[] allMatches = new String[5];
	      Matcher m = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d").matcher(texts);
	      while (m.find()) 
	      {  
	         
	         allMatches[count] = m.group();
	         count++;
	         
	      }   
	      return allMatches;
	   }

}
