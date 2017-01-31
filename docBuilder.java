
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class docBuilder extends jParser 
{
	public static void main(String[] args)
	{
		try 
		{
		jParser parser = new jParser();
		
		PrintWriter pw = new PrintWriter(new File("test.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append(parser.station);
		sb.append(',');
		sb.append(parser.dateString);
		sb.append(',');
		sb.append(parser.time);
		sb.append(',');
		sb.append(parser.CLDC);
		sb.append(',');
		sb.append(parser.sunshine);
		sb.append(',');
		sb.append('\n');
		
		pw.write(sb.toString());
		pw.close();
		
		System.out.println("File saved!");
		
		}
		catch (IOException ioe)
		{
		    System.err.println(ioe);
		}
	}
}