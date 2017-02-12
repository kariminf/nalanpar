package kariminf.nalanpar;

import kariminf.nalanpar.Types.ConjFeature;
import kariminf.nalanpar.Types.Featured;
import kariminf.nalanpar.Types.NounFeature;
import kariminf.nalanpar.Types.PPFeature;
import kariminf.nalanpar.Types.Phrasal;
import kariminf.nalanpar.Types.PhrasalFeature;
import kariminf.nalanpar.Types.Posable;
import kariminf.nalanpar.Types.Terminal;
import kariminf.nalanpar.Types.VerbFeature;
import kariminf.sentrep.univ.types.Determiner;
import kariminf.sentrep.univ.types.Pronoun;
import kariminf.sentrep.univ.types.VerbTense;

public abstract class UnivParser {
	
	protected ParseHandler handler;
	protected POSTransformer posTrans;
	
	public UnivParser(ParseHandler handler, POSTransformer posTrans){
		this.handler = handler;
		this.posTrans = posTrans;
	}
	
	/**
	 * 
	 * @param text
	 */
	public void parse(String text){
		if (! prepare(text))
			return;
		while (next()){
			Element e  = getElement();
			if (e == null)
				continue;
			
			Posable p = e.getPos();
			if (p == null)
				continue;
			
			if (p instanceof Phrasal){
				phrasalProcess(e, (Phrasal) p);
				continue;
			}
			
			if (p instanceof Terminal){
				terminalProcess(e, (Terminal) p );
				continue;
			}
			
		}
	}
	
	
	/**
	 * Process the phrasal element.
	 * @param e the element
	 * @param p the Phrasal Features
	 */
	private void phrasalProcess(Element e, Phrasal p){

		Featured f = e.getFeature();

		if (f == null || ! (f instanceof PhrasalFeature))
			return;

		switch(p){
		case S:
			if (((PhrasalFeature)f).getbegin())
				handler.beginSentence();
			else
				handler.endSentence();
			return;
		case NP:
			if (((PhrasalFeature)f).getbegin())
				handler.beginNP();
			else
				handler.endNP();
			return;
		case VP:
			if (((PhrasalFeature)f).getbegin())
				handler.beginVP();
			else
				handler.endVP();
			return;

		case ADJP:
			if (((PhrasalFeature)f).getbegin())
				handler.beginAdjP();
			else
				handler.endAdjP();
			return;

		case ADVP:	
			if (((PhrasalFeature)f).getbegin())
				handler.beginAdvP();
			else
				handler.endAdvP();
			return;

		case PP:
			String prep = f instanceof PPFeature? 
					((PPFeature)f).getPrep(): "";
			if (((PhrasalFeature)f).getbegin())
				handler.beginPP(prep);
			else
				handler.endPP();
			return;

		default:
			return;
		}


	}
	
	/**
	 * Process the terminal element
	 * @param e the element
	 * @param p the Terminal element
	 */
	private void terminalProcess(Element e, Terminal p){
		Featured f = e.getFeature();
		String val = e.getVal().trim();
		if (val == null || val.length() < 1)
			return;
		
		switch(p){
		case NOUN:
		{
			//Test for pronouns like: this, his, etc.
			boolean plural = false;
			boolean proper = false;
			Determiner def = Determiner.NONE;
			if (f != null && f instanceof NounFeature){
				NounFeature nf = (NounFeature) f;
				plural = nf.isPlural();
				proper = nf.isProper();
				def = nf.getDefined();
			}
			//TODO pronouns like: this, his, etc.
			handler.addNoun(val, def, plural, proper, null);
			break;
		}
		case ADJ:
			break;
		case ADP:
			break;
		case ADV:
			break;
		case AUX:
			break;
		case CONJ:
		{
			boolean conj = true;
			if (f != null && f instanceof ConjFeature){
				ConjFeature cf = (ConjFeature) f;
				conj = cf.getConjunction();
			}
			handler.conjected(conj);
			break;
		}
		case DET:
			break;
		case INTJ:
			break;
		case NUM:
		{
			//TODO Numbers
			handler.addNoun(val, Determiner.NONE, false, true, null);
			break;
		}
		case PART:
			break;
		case PRON:
		{
			Pronoun pronoun = new Pronoun();
			handler.addPronoun(pronoun);
			break;
		}
		case PUNCT:
			break;
		case SCONJ:
			break;
		case SYM:
			break;
		case VERB:
		{
			VerbTense tense = VerbTense.PRESENT;
			if (f instanceof VerbFeature){
				tense = ((VerbFeature) f).getTense();
			}
			handler.addVerb(val, tense);
			break;
		}
		case X:
			break;
		default:
			break;
			
		}
	}
	
	protected abstract boolean prepare(String text);
	protected abstract boolean next();
	protected abstract Element getElement();
	
	public static class Element {
		private String val = "";
		private Posable pos;
		private Featured feat;
		
		public Element (String val, Posable pos, Featured feat){
			this.val = val;
			this.pos = pos;
			this.feat = feat;
		}
		
		public String getVal(){
			return val;
		}
		
		public Posable getPos(){
			return pos;
		}
		
		public Featured getFeature(){
			return feat;
		}
	}

}
