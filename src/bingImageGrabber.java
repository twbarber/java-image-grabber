/* 
	Bing Image Grabber - Version 0.1.0

	Main class. Gets BingID from user and makes objects.
	
	TODO: 
		- Make downloads / seed variable
		- Make iterations to run multiple querys
		- Make search options available to be entered manually
*/

import java.util.*;

public class bingImageGrabber {

	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
	
			System.out.println("\nBing Image Grabber 0.1.0\n");
			System.out.print("Enter Bing AppID: ");
			String userAccountKey = in.next();

			serialGrabber mySerialGrabber = new serialGrabber(userAccountKey, 30);
			mySerialGrabber.run();	
	}
}