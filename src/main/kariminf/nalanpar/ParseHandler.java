package kariminf.nalanpar;

import kariminf.nalanpar.Types.Det;
import kariminf.nalanpar.Types.VerbTense;

public interface ParseHandler {
	
	/**
	 * This function marks the begining of a sentence
	 */
	public void beginSentence();
	
	/**
	 * This function marks the end of a sentence
	 */
	public void endSentence();
	
	/**
	 * This function marks the begining of a nominal phrase
	 */
	public void beginNP();
	
	/**
	 * This function marks the end of a nominal phrase
	 */
	public void endNP();
	
	/**
	 * This function marks the begining of a verbal phrase
	 */
	public void beginVP();
	
	/**
	 * This function marks the end of a verbal phrase
	 */
	public void endVP();
	
	/**
	 * This function marks the begining of an adjectival phrase
	 */
	public void beginAdjP();
	
	/**
	 * This function marks the end of an adjectival phrase
	 */
	public void endAdjP();
	
	/**
	 * This function marks the begining of an adverbial phrase
	 */
	public void beginAdvP();
	
	/**
	 * This function marks the end of an adverbial phrase
	 */
	public void endAdvP();

	/**
	 * This function marks the begining of a prepositional phrase
	 */
	public void beginPP(String prep);
	
	/**
	 * This function marks the end of a prepositional phrase
	 */
	public void endPP();
	
	/**
	 * This function is used to add a noun to the last nominal phrase
	 * @param val This is the value of the noun
	 * @param det Determined or not or undefined
	 * @param plural Is it plural?
	 * @param proper Is it a proper noun?
	 */
	public void addNoun(String val, Det det, boolean plural, boolean proper);
	
	/**
	 * This function is used to add a verb to the last verbal phrase
	 * @param val The value of the verb
	 * @param tense The tense of the verb
	 */
	public void addVerb(String val, VerbTense tense);
	
	/**
	 * Signal the 
	 * @param conj
	 */
	public void conjected(boolean conj);
	
	
	
}
