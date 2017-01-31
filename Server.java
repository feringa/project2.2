 
import java.io.*;
import java.net.*;
 
/**
 * Created by robin on 24-1-17.
 */
public class Server
{
    public static void main(String[] args) {	
    	boolean xmlVersionFound = false;
    	boolean check = false;
    	boolean rootFound = false;
    	boolean checkRoot = false;
    	
    	String clientSentence = "";
    	String rootTag = "</WEATHERDATA>";
        try
        {
            String text = "";
            ServerSocket sock = new ServerSocket(7789);
            boolean input = true;
            long measurmentValue = 1; 
            //now listen for connections
            while (input)
            {
                Socket client = sock.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while (input = true)
                {
                    clientSentence = inFromClient.readLine();
                    if (clientSentence != null) {
                        // System.out.println(clientSentence);
                    	if (clientSentence.contains("<WEATHERDATA>") || clientSentence.contains("</WEATHERDATA>")) {
                    		rootFound = true;
                    		if (rootFound==true && checkRoot==false){
                    			checkRoot=true;
                    		}
                    		else {
                    			continue;
                    		}
                    	}
                    	if (clientSentence.contains("<?xml version")) {
                        	xmlVersionFound = true;
                        	if (xmlVersionFound==true && check==true){
                        			continue;
                        	}
                    	
                    	/*if(clientSentence.contains("<MEASUREMENT>")) {
                    		String var1 = clientSentence;
                    		String var2 = "";
                    		var2 = var1.replace("</", "");
                    		var1 = var2.replace(">","");
                    	    var2 = var1 + String.valueOf(measurmentValue);
                    		String left = "<";
                    		String right = ">";
                    		var1 = left + var2 + right;
                    	}
                    	if(clientSentence.contains("</MEASUREMENT>")) {
                    		String var1 = clientSentence;
                    		String var2 = "";
                    		var2 = var1.replace("<", "");
                    		var1 = var2.replace("/","");
                    		var2 = var1.replace(">","");
                    	    var1 = var2 + String.valueOf(measurmentValue);
                    		String left = "<";
                    		String right = ">";
                    		var1 = left + var1 + right;
                    		measurmentValue++;
                    	}*/
                    	}
                    }
                    else {
                        input = false;
                    }
                    text = text.concat(clientSentence + "\n");
                    check=true;
                }
            }
            
            BufferedWriter out = new BufferedWriter(new FileWriter("E:\\input.xml"));
            out.write(text);
            out.close();
            
            //close socket
            sock.close();
        }
        catch (IOException ioe)
        {
            System.err.println(ioe);
        }
    }
}