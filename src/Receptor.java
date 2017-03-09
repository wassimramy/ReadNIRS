import java.io.PrintWriter;
import java.util.ArrayList;

public class Receptor {

	private int ID;
	private ArrayList<Double> data;
	
	public Receptor (int ID)
	{
		this.ID = ID ;
		this.data = new ArrayList<Double>();
	
	}

	public void addData (Double receivedData)
	{
		this.data.add(receivedData);
	}
	
	public void printData ()
	{
		for (int i = 0 ; i < data.size() ; i++)
		{
			System.out.println("Data Entry #" + i + " " + data.get(i).toString());
		}
	}
	
	public void printData (PrintWriter writer)
	{
		for (int i = 0 ; i < data.size() ; i++)
		{
			writer.println("Data Entry #" + i + " " + data.get(i).toString());
		}
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<Double> getData() {
		return data;
	}

	public void setData(ArrayList<Double> data) {
		this.data = data;
	}
	
}
