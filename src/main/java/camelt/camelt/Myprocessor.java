package camelt.camelt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;

//import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.impl.DefaultCamelContext;

import org.apache.camel.Processor;


public class Myprocessor implements Processor {
	public void process(Exchange exchange) {


		File file = exchange.getIn().getBody(File.class);
		
		FileInputStream fis = null;
		FileOutputStream fop = null;
		String str = "";

		try {
			fis = new FileInputStream(file);
			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				str += (char) content;
			}
			String[] name = str.toUpperCase().split("");
			String reverseName = "";
			for (int i = name.length - 1; i >= 0; i--) {
				reverseName += name[i];
			}

			fop = new FileOutputStream(file);
			byte[] contentInBytes = reverseName.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}