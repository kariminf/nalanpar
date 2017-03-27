package kariminf.nalanpar;

import kariminf.nalanpar.stanford.EnSPOS2Univ;
import kariminf.nalanpar.stanford.SUnivParser;
import kariminf.nalanpar.ston.Text2Ston;

public class EnSUniv2StonTest {

	//private static final String sent = "the boy play football at home.";
	private static final String sent = 
			//"the boy and the father play football and basketball at home.";
			//"the dog at home ate meat.";
			"Born in Cairo in 1911, Naguib Mahfouz began writing when he was seventeen.";
	
	public static void main(String[] args) {
		EnSPOS2Univ posTranformer = new EnSPOS2Univ();
		Text2Ston handler = new Text2Ston();
		SUnivParser parser = new SUnivParser(handler, posTranformer);
		
		parser.parse(sent);
		System.out.println("STON:");
		System.out.println(handler.getSTON());

	}

}
