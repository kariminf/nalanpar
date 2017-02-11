package kariminf.nalanpar;

import kariminf.sentrep.univ.types.Determiner;
import kariminf.sentrep.univ.types.Pronoun;
import kariminf.sentrep.univ.types.VerbTense;

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
	
	public static class PPFeature extends PhrasalFeature {
		private String prep = "";
		
		public void setPrep(String prep){
			this.prep = prep;
		}
		
		public String getPrep(){
			return prep;
		}
	}
	
	
	public static class VerbFeature implements Featured {
		private VerbTense tense = VerbTense.PRESENT;
		
		public void setPresent(){
			this.tense = VerbTense.PRESENT;
		}
		
		public void setPast(){
			this.tense = VerbTense.PAST;
		}
		
		public void setFuture(){
			this.tense = VerbTense.FUTURE;
		}
		
		public VerbTense getTense(){
			return tense;
		}
		
	}
	
	public static class NounFeature implements Featured {
		private Determiner def = Determiner.NONE;
		private boolean proper = false;
		private boolean plural = false;
		
		public void setDefined(){
			this.def = Determiner.YES;
		}
		
		public void setUnDefined(){
			this.def = Determiner.NO;
		}
		
		public Determiner getDefined(){
			return def;
		}
		
		public void setProper(){
			this.proper = true;
		}
		
		public boolean isProper(){
			return this.proper;
		}
		
		public void setPlural(){
			this.plural = true;
		}
		
		public boolean isPlural(){
			return plural;
		}
		
	}
	
	public static class PronounFeature implements Featured {
		private Pronoun pronoun;
		
		public void setPronoun(Pronoun pronoun){
			this.pronoun = pronoun;
		}
		
		public Pronoun getPronoun(){
			return pronoun;
		}
		
		
	}
	
	public static class ConjFeature implements Featured {
		private boolean conj = false;
		
		public void setConjunction(){
			this.conj = true;
		}
		
		public boolean getConjunction(){
			return conj;
		}
		
	}

	public static enum Phrasal implements Posable {
		S,
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
		//PROPN, // proper noun
		PUNCT, // punctuation
		SCONJ, // subordinating conjunction
		SYM, // symbol
		VERB, // verb
		X; // other
	}
	
}
