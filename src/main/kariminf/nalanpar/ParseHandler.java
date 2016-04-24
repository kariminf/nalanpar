package kariminf.nalanpar;

import kariminf.nalanpar.Types.Det;
import kariminf.nalanpar.Types.VerbTense;

public interface ParseHandler {
	
	public void beginSentence();
	public void endSentence();
	
	public void beginNP();
	public void endNP();
	
	public void beginVP();
	public void endVP();
	
	public void endAdjP();
	public void beginAdjP();
	
	public void beginAdvP();
	public void endAdvP();

	public void beginPP();
	public void endPP();
	
	public void addNoun(String val, Det det, boolean plural, boolean proper);
	public void addVerb(String val, VerbTense tense);
	
}
