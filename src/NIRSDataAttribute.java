import java.io.PrintWriter;
import java.util.ArrayList;

public class NIRSDataAttribute {

	private String attributeName;
	private ArrayList<Double> data;
	
	public NIRSDataAttribute (String attributeName)
	{
		this.setAttributeName(attributeName);
		this.data = new ArrayList<Double>();
	}
	
	public void printData ()
	{
		for (int i = 0 ; i < data.size() ; i++)
		{
			System.out.println(attributeName + " " + i + " " + data.get(i).toString());
		}
	}
	
	public void printData (PrintWriter writer)
	{
		for (int i = 0 ; i < data.size() ; i++)
		{
			writer.println(attributeName + " " + i + " " + data.get(i).toString());
		}
	}
	
	public void addData (Double receivedData)
	{
		this.data.add(receivedData);
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public ArrayList<Double> getData() {
		return data;
	}

	public void setData(ArrayList<Double> data) {
		this.data = data;
	}
	
	
}
