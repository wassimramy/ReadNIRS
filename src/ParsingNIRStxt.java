import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ParsingNIRStxt {

	private String file;
	private String header;
	private String acquisationInformation;
	private String fileInformation;
	private String calibrationState;
	private String auxCalibrationValues;
	private String wfCalibrationValues;
	private String distanceSettings;
	private String dataBegins;
	private ArrayList<NIRSDataAttribute> attributes;
	private Scanner scan;
	
	public ParsingNIRStxt(String file) throws FileNotFoundException
	{
		this.setFile(file);
		this.attributes = new ArrayList<NIRSDataAttribute>();
		this.parsing();
		
	}
	
	private String parsingBlock(Scanner scan, String attribute)
	{
		String scannedData = null;
		while (scan.hasNextLine())
	        {
	        	
	        	scannedData = scan.nextLine();
	        	
	        	if (attribute == null)
	    		{
	        		attribute = scannedData;
	    		}
	        	else
	        	{
	        		attribute = attribute + "\n" +scannedData;
	        	}
	        	
	        	if (scannedData.isEmpty())
	        	{
	        		break;
	        	}
	        	
	        }
		return attribute;
	}
	
	private void parsingHeader(Scanner scan)
	{
		header = parsingBlock (scan, header);
		System.out.println(header);
	}
	
	private void parsingAcquisationInformation(Scanner scan)
	{
		acquisationInformation = parsingBlock (scan, acquisationInformation);
		System.out.println(acquisationInformation);
	}
	
	private void parsingFileInformation(Scanner scan)
	{
		fileInformation = parsingBlock (scan, fileInformation);
		System.out.println(fileInformation);
	}
	
	private void parsingCalibrationState(Scanner scan)
	{
		calibrationState = parsingBlock (scan, calibrationState);
		System.out.println(calibrationState);
	}
	private void parsingauxCalibrationValues(Scanner scan)
	{
		calibrationState = parsingBlock (scan, calibrationState);
		calibrationState = parsingBlock (scan, calibrationState);
		System.out.println(auxCalibrationValues);
	}
	
	private void parsingwfCalibrationValues(Scanner scan)
	{
		wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
		wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
		wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
		wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
		wfCalibrationValues = parsingBlock (scan, wfCalibrationValues);
		System.out.println(wfCalibrationValues);
	}
	
	private void parsingDistanceSettings(Scanner scan)
	{
		distanceSettings = parsingBlock (scan, distanceSettings);
		distanceSettings = parsingBlock (scan, distanceSettings);
		distanceSettings = parsingBlock (scan, distanceSettings);
		distanceSettings = parsingBlock (scan, distanceSettings);
		distanceSettings = parsingBlock (scan, distanceSettings);
		System.out.println(distanceSettings);
	}
	
	private void parsing() throws FileNotFoundException
	{
		String scannedData;
		scan = new Scanner (new File(file));
		String[] returnValue = null;
		String regularExpression = "\t";
		
		this.parsingHeader( scan);
		//scannedData = scan.nextLine();
		
		this.parsingAcquisationInformation(scan);
		
		this.parsingFileInformation(scan);
		
		this.parsingCalibrationState(scan);
		
		scan.nextLine();
		
		this.parsingauxCalibrationValues(scan);
		
		scan.nextLine();
		
		this.parsingwfCalibrationValues(scan);
		
		this.parsingDistanceSettings(scan);
		
		scan.nextLine();
		
		scan.nextLine();
		
		System.out.println("\n"+scan.nextLine());
		
		dataBegins = parsingBlock (scan, dataBegins);
		
		returnValue = dataBegins.split(regularExpression);
		
		for (int i = 0 ; i < returnValue.length ; i++)
		{
			NIRSDataAttribute attribute = new NIRSDataAttribute (returnValue[i]);
			attributes.add(attribute);
		}

		
		while (scan.hasNextLine())
        {
        	
        	scannedData = scan.nextLine();
        	
        	if (scannedData.equals("#DATA ENDS"))
        	{
        		break;
        	}
        	
   
            returnValue = scannedData.split(regularExpression);
            
            for (int i = 0 ; i < returnValue.length; i++)
            {
         	   attributes.get(i).addData(Double.parseDouble(returnValue[i]));
            }  
            
        }
		
		
	}
	
	public void printDataExternally ()
	{
		PrintWriter writer = null;
        for (int i = 0 ; i < attributes.size() ; i++)
        {
        	File output = new File ("Receptor "+i+".txt");
        	
        	try{
        		if (output.exists() == false)
        		{
        			output.createNewFile();
        		}
        		 writer = new PrintWriter (new FileWriter(output, true));
        	}
        	catch (IOException e)
        	{
        		e.printStackTrace();
        		
        	}
        	writer.println("Receptor #" + i);
        	attributes.get(i).printData(writer);
        	writer.println("Fetching of Receptor's #"+i+ " Data is Done");
        	writer.close();
        }
	}
	
	public void printData () 
	{
        for (int i = 0 ; i < attributes.size() ; i++)
        {
        	System.out.println(attributes.get(i).getAttributeName());
        	attributes.get(i).printData();
        	System.out.println("Fetching of "+attributes.get(i).getAttributeName()+ " Data is Done");
        }
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getAcquisationInformation() {
		return acquisationInformation;
	}

	public void setAcquisationInformation(String acquisationInformation) {
		this.acquisationInformation = acquisationInformation;
	}

	public String getFileInformation() {
		return fileInformation;
	}

	public void setFileInformation(String fileInformation) {
		this.fileInformation = fileInformation;
	}

	public String getCalibrationState() {
		return calibrationState;
	}

	public void setCalibrationState(String calibrationState) {
		this.calibrationState = calibrationState;
	}

	public String getAuxCalibrationValues() {
		return auxCalibrationValues;
	}

	public void setAuxCalibrationValues(String auxCalibrationValues) {
		this.auxCalibrationValues = auxCalibrationValues;
	}

	public String getDistanceSettings() {
		return distanceSettings;
	}

	public void setDistanceSettings(String distanceSettings) {
		this.distanceSettings = distanceSettings;
	}

	public String getDataBegins() {
		return dataBegins;
	}

	public void setDataBegins(String dataBegins) {
		this.dataBegins = dataBegins;
	}

	public ArrayList<NIRSDataAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<NIRSDataAttribute> attributes) {
		this.attributes = attributes;
	}
	
}
