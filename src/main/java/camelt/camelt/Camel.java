package camelt.camelt;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Camel {
	public static void main(String args[]) {

		try {
			CamelContext context = new DefaultCamelContext();
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					Myprocessor myProcessor = new Myprocessor();
					from("file:/home/yokoyama/camel/in/")
					.process(myProcessor)
					.to("file:/home/yokoyama/camel/out/");
				}
			});

			context.start();
			Thread.sleep(10000);
			context.stop();
		} catch (Exception camelException) {
			System.out.println("Exception trying to copy files - {0}" + camelException.toString());
		}
	}
}