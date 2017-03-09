
import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		String file = "NIRS.txt";
		//String file = "101random_fl_2.txt";
		//ParsingEEGtxt parsing = new ParsingEEGtxt(file);
		//parsing.printDataExternally();
		ParsingNIRStxt parsing = new ParsingNIRStxt(file);
		parsing.printData();
	}

}
