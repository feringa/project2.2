package XMLParser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class jParser {
	//declaratie van variabelen;

	public String station = "";
	public String date = "";
	public String dateString = "";
	public String time = "";
	public float CLDC = 0;
	public float sunshine = 0;
		
	   public void jParseBuild() {
		  
	      try {
	         File inputFile = new File("E:\\input.xml");

	         SAXBuilder saxBuilder = new SAXBuilder();

	         Document document = saxBuilder.build(inputFile);

	         Element classElement = document.getRootElement();

	         List<Element> dataList = classElement.getChildren();

	         for (int temp = 0; temp < dataList.size(); temp++) {    
	            Element data = dataList.get(temp);
	            station = data.getChildText("STN");
	            System.out.println("Station : " + station);
	            date = data.getChildText("DATE").replace("-", "");
	            dateString = date.replaceAll("\\d{2}(\\d{2})(\\d{2})(\\d{2})", "$3-$2-$1");
	            System.out.println("Date : " + dateString);
	            time = data.getChildText("TIME");
	            System.out.println("Time : " + time);
	            CLDC = Float.parseFloat(data.getChildText("CLDC"));
	            System.out.println("Cloud Coverage : " + CLDC);
	            //Hier een mogelijke berekening plaatsen van lumen (935710 lumen/cm^2 op aardoppervlakte) wordt verrekend met het percentage bewolking. 
	            sunshine = 935710/(CLDC/100);
	            System.out.println("Solar  : " + sunshine);
	         }
	      }catch(JDOMException e){
	         e.printStackTrace();
	      }catch(IOException ioe){
	         ioe.printStackTrace();
	      }catch(NumberFormatException nfe){  
	      }
	   }
	}