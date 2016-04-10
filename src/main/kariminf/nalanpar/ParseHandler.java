package kariminf.nalanpar;

public interface ParseHandler {
	
	public void newSentence();
	public void beginNP();
	public void endNP();
	public void beginVP();
	public void endVP();
	public void endAdjP();
	public void beginAdjP();
	public void beginAdvP();
	public void endAdvP();

}
