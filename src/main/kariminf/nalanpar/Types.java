package kariminf.nalanpar;

public class Types {
	
	public interface Posable {
		
	}
	
	public static enum POSType {
		NP, //Noun phrase
		VP, //Verbe phrase
		ADJP,//Adjective phrase
		ADVP, //Adverbe phrase
		NN,
		NNS,
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
		VERB // verb
	}
	public static enum Phrase implements Posable {
		NP, //Noun phrase
		VP, //Verbe phrase
		ADJP,//Adjective phrase
		ADVP, //Adverbe phrase
		WHNP;
		
		private boolean begin = true;
		public Phrase setEnding(){
			this.begin = false;
			return this;
		}
		
		public boolean isBegin(){
			return begin;
		}
	}
	
	public static enum Verb implements Posable {
		VBD
	}
	
	public static enum Noun implements Posable {
		NN,
		NNS,
	}

}
