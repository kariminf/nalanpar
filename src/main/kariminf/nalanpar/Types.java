package kariminf.nalanpar;

public class Types {
	
	public interface Posable {
		
	}
	
	public interface Featured {
		
	}
	
	public static class PhrasalFeature implements Featured {
		private boolean begin = true;
		
		public void setEnd(){
			this.begin = false;
		}
		
		public boolean getbegin(){
			return begin;
		}
	}
	
	public static class VerbFeature implements Featured {
		private int tense = 0;
		
		public void setPresent(){
			this.tense = 0;
		}
		
		public void setPast(){
			this.tense = 1;
		}
		
	}

	public static enum Phrasal implements Posable {
		NP, //Noun phrase
		VP, //Verbe phrase
		ADJP,//Adjective phrase
		ADVP, //Adverbe phrase
		PP; //Prepositional phrase
	}
	
	public static enum Terminal implements Posable {
		ADJ, // adjective
		ADP, // adposition
		ADV, // adverb
		AUX, // auxiliary verb
		CONJ, // coordinating conjunction
		DET, // determiner
		INTJ, // interjection
		NOUN, // noun
		NUM, // numeral
		PART, // particle
		PRON, // pronoun
		PROPN, // proper noun
		PUNCT, // punctuation
		SCONJ, // subordinating conjunction
		SYM, // symbol
		VERB, // verb
		X; // other
	}


}
