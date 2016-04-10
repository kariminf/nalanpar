package kariminf.nalanpar.ston;

import kariminf.nalanpar.ParseHandler;

public class Text2Ston implements ParseHandler {

	@Override
	public void beginNP() {
		System.out.println("Start of NP phrase");
		
	}

	@Override
	public void endNP() {
		System.out.println("End of NP phrase");
		
	}

	@Override
	public void newSentence() {
		System.out.println("new sentence");
		
	}

	@Override
	public void beginVP() {
		System.out.println("Start of VP phrase");
		
	}

	@Override
	public void endVP() {
		System.out.println("End of VP phrase");
		
	}

	@Override
	public void endAdjP() {
		System.out.println("Start of Adjective phrase");
		
	}

	@Override
	public void beginAdjP() {
		System.out.println("End of Adjective phrase");
		
	}

	@Override
	public void beginAdvP() {
		System.out.println("Start of Adverb phrase");
		
	}

	@Override
	public void endAdvP() {
		System.out.println("End of Adverb phrase");
		
	}

}
