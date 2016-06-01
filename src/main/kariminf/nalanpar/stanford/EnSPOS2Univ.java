package kariminf.nalanpar.stanford;

import kariminf.nalanpar.POSTransformer;
import kariminf.nalanpar.Types.ConjFeature;
import kariminf.nalanpar.Types.Det;
import kariminf.nalanpar.Types.Featured;
import kariminf.nalanpar.Types.NounFeature;
import kariminf.nalanpar.Types.PPFeature;
import kariminf.nalanpar.Types.VerbFeature;
import kariminf.nalanpar.Types.Phrasal;
import kariminf.nalanpar.Types.PhrasalFeature;
import kariminf.nalanpar.Types.Posable;
import kariminf.nalanpar.Types.Terminal;
import kariminf.nalanpar.UnivParser.Element;

public class EnSPOS2Univ implements POSTransformer {
	
	public static enum PennTreeBankTerminal {
		//Word Level
		//=============
		CC, //Coordinating conjunction
		CD, //Cardinal number
		DT, //Determiner
		EX, //Existential there
		FW, //Foreign word
		IN, //Preposition or subordinating conjunction
		JJ, //Adjective
		JJR, //Adjective, comparative
		JJS, //Adjective, superlative
		LS, //List item marker
		MD, //Modal
		NN, //Noun, singular or mass
		NNS, //Noun, plural
		NNP, //Proper noun, singular
		NNPS, //Proper noun, plural
		PDT, //Predeterminer
		POS, //Possessive ending
		PRP, //Personal pronoun
		PRP$, //Possessive pronoun (prolog version PRP-S)
		RB, //Adverb
		RBR, //Adverb, comparative
		RBS, //Adverb, superlative
		RP, //Particle
		SYM, //Symbol
		TO, //to
		UH, //Interjection
		VB, //Verb, base form
		VBD, //Verb, past tense
		VBG, //Verb, gerund or present participle
		VBN, //Verb, past participle
		VBP, //Verb, non-3rd person singular present
		VBZ, //Verb, 3rd person singular present
		WDT, //Wh-determiner
		WP, //Wh-pronoun
		WP$, //Possessive wh-pronoun (prolog version WP-S)
		WRB, //Wh-adverb
		
		//Still others
	}
	
	private static enum PennTreeBankPhrasal {
		ROOT,
		
		//Clause Level
		//=============
		S, //simple declarative clause, i.e. one that is not introduced by a (possible empty) subordinating conjunction or a wh-word and that does not exhibit subject-verb inversion.
		SBAR, //Clause introduced by a (possibly empty) subordinating conjunction.
		SBARQ, //Direct question introduced by a wh-word or a wh-phrase. Indirect questions and relative clauses should be bracketed as SBAR, not SBARQ.
		SINV, //Inverted declarative sentence, i.e. one in which the subject follows the tensed verb or modal.
		SQ, //Inverted yes/no question, or main clause of a wh-question, following the wh-phrase in SBARQ.

		//Phrase Level
		//=============
		ADJP, //Adjective Phrase.
		ADVP, //Adverb Phrase.
		CONJP, //Conjunction Phrase.
		FRAG, //Fragment.
		INTJ, //Interjection. Corresponds approximately to the part-of-speech tag UH.
		LST, //List marker. Includes surrounding punctuation.
		NAC, //Not a Constituent; used to show the scope of certain prenominal modifiers within an NP.
		NP, //Noun Phrase. 
		NX, //Used within certain complex NPs to mark the head of the NP. Corresponds very roughly to N-bar level but used quite differently.
		PP, //Prepositional Phrase.
		PRN, //Parenthetical. 
		PRT, //Particle. Category for words that should be tagged RP. 
		QP, //Quantifier Phrase (i.e. complex measure/amount phrase); used within NP.
		RRC, //Reduced Relative Clause. 
		UCP, //Unlike Coordinated Phrase. 
		VP, //Vereb Phrase. 
		WHADJP, //Wh-adjective Phrase. Adjectival phrase containing a wh-adverb, as in how hot.
		WHAVP, //Wh-adverb Phrase. Introduces a clause with an NP gap. May be null (containing the 0 complementizer) or lexical, containing a wh-adverb such as how or why.
		WHNP, //Wh-noun Phrase. Introduces a clause with an NP gap. May be null (containing the 0 complementizer) or lexical, containing some wh-word, e.g. who, which book, whose daughter, none of which, or how many leopards.
		WHPP, //Wh-prepositional Phrase. Prepositional phrase containing a wh-noun phrase (such as of which or by whose authority) that either introduces a PP gap or is contained by a WHNP.
		X, //Unknown, uncertain, or unbracketable. X is often used for bracketing typos and in bracketing the...the-constructions.
		
	}
	
	@Override
	public Posable getType(String pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getTerminalElement(String pos, String val) {
		PennTreeBankTerminal p = PennTreeBankTerminal.valueOf(pos);
		Terminal t = null;
		Featured f = null;
		
		switch(p){
		
		case NN: //Noun, singular or mass
		{
			t = Terminal.NOUN;
			NounFeature nf = new NounFeature();
			f = nf;
			break;
		}
		case NNS: //Noun, plural
		{
			t = Terminal.NOUN;
			NounFeature nf = new NounFeature();
			nf.setPlural();
			f = nf;
			break;
		}
		case NNP: //Proper noun, singular
		{
			t = Terminal.NOUN;
			NounFeature nf = new NounFeature();
			nf.setProper();
			f = nf;
			break;
		}
		case NNPS: //Proper noun, plural
		{
			t = Terminal.NOUN;
			NounFeature nf = new NounFeature();
			nf.setProper();
			nf.setPlural();
			f = nf;
			break;
		}
		case VB: //Verb, base form
		{
			t = Terminal.VERB;
			VerbFeature vf = new VerbFeature();
			vf.setPresent();
			f = vf;
			break;
		}
			
		case VBD: //Verb, past tense
		{
			t = Terminal.VERB;
			VerbFeature vf = new VerbFeature();
			vf.setPast();
			f = vf;
			break;
		}
		case VBG: //Verb, gerund or present participle
		{
			t = Terminal.VERB;
			VerbFeature vf = new VerbFeature();
			vf.setPresent();
			f = vf;
			break;
		}
		case VBN: //Verb, past participle
		{
			t = Terminal.VERB;
			VerbFeature vf = new VerbFeature();
			vf.setPast();
			f = vf;
			break;
		}
		case VBP: //Verb, non-3rd person singular present
		{
			t = Terminal.VERB;
			VerbFeature vf = new VerbFeature();
			vf.setPresent();
			f = vf;
			break;
		}
		case VBZ: //Verb, 3rd person singular present
		{
			t = Terminal.VERB;
			VerbFeature vf = new VerbFeature();
			vf.setPresent();
			f = vf;
			break;
		}
		case CC:
		{
			t = Terminal.CONJ;
			ConjFeature cf = new ConjFeature();
			if (val.contains("and"))
				cf.setConjunction();
			f = cf;
			break;
		}
		case CD:
			break;
		case DT:
		{
			t = Terminal.DET;
			break;
		}
		case EX:
			break;
		case FW:
			break;
		case IN:
		{
			PPFeature pf = new PPFeature();
			pf.setPrep(val);
			Phrasal ph = Phrasal.PP;
			return new Element(val, ph, pf);
		}
		case JJ:
			break;
		case JJR:
			break;
		case JJS:
			break;
		case LS:
			break;
		case MD:
			break;
		case PDT:
			break;
		case POS:
			break;
		case PRP:
			break;
		case PRP$:
			break;
		case RB:
			break;
		case RBR:
			break;
		case RBS:
			break;
		case RP:
			break;
		case SYM:
			break;
		case TO:
			break;
		case UH:
			break;
		case WDT:
			break;
		case WP:
			break;
		case WP$:
			break;
		case WRB:
			break;
		default:
			break;
			
		}
		
		Element e = new Element(val, t, f);
		return e;
	}

	@Override
	public Element getPhrasalElement(String pos, boolean begin) {
		PennTreeBankPhrasal p = PennTreeBankPhrasal.valueOf(pos);
		Phrasal ph=null;
		
		switch (p){
		case NP:
			ph = Phrasal.NP;
			break;
		case VP:
			ph = Phrasal.VP;
			break;
		case ADJP:
			ph = Phrasal.ADJP;
			break;
		case ADVP:
			ph = Phrasal.ADVP;
			break;
		case PP:
			ph = Phrasal.PP;
			break;
		case CONJP:
			return null;
			//break;
		case FRAG:
			return null;
			//break;
		case INTJ:
			return null;
			//break;
		case LST:
			return null;
			//break;
		case NAC:
			return null;
			//break;
		case NX:
			return null;
			//break;
		case PRN:
			return null;
			//break;
		case PRT:
			return null;
			//break;
		case QP:
			return null;
			//break;
		case ROOT:
			return null;
			//break;
		case RRC:
			return null;
			//break;
		case S:
			ph = Phrasal.S;
			break;
		case SBAR:
			return null;
			//break;
		case SBARQ:
			return null;
			//break;
		case SINV:
			return null;
			//break;
		case SQ:
			return null;
			//break;
		case UCP:
			return null;
			//break;
		case WHADJP:
			return null;
			//break;
		case WHAVP:
			return null;
			//break;
		case WHNP:
			return null;
			//break;
		case WHPP:
			return null;
			//break;
		case X:
			return null;
			//break;
		default:
			return null;
			//break;
		}
		
		PhrasalFeature phF = new PhrasalFeature();
		if (! begin)
			phF.setEnd();
		Element e = new Element("", ph, phF);
		return e;
	}

	@Override
	public Det getDet(String val) {
		val = val.toLowerCase();
		if (val.equals("the"))
			return Det.Y;
		if (val.matches("a|an"))
			return Det.N;
		return Det.NONE;
	}

}
