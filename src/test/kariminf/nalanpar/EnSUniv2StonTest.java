package kariminf.nalanpar;

import kariminf.nalanpar.stanford.EnSPOS2Univ;
import kariminf.nalanpar.stanford.SUnivParser;
import kariminf.nalanpar.ston.Text2Ston;

public class EnSUniv2StonTest {

	private static final String sent = "Mother ate food.";
	
	public static void main(String[] args) {
		EnSPOS2Univ posTranformer = new EnSPOS2Univ();
		Text2Ston handler = new Text2Ston();
		SUnivParser parser = new SUnivParser(handler, posTranformer);
		
		parser.parse(sent);
		System.out.println("STON:");
		System.out.println(handler.getSTON());

	}

}
