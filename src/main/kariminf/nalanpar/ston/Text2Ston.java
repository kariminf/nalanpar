package kariminf.nalanpar.ston;

import java.io.File;
import java.util.HashSet;

import edu.stanford.nlp.process.Morphology;

import kariminf.langpi.wordnet.JWIRequestor;
import kariminf.langpi.wordnet.SqliteReqExceptions.LangNotFound;
import kariminf.langpi.wordnet.SqliteReqExceptions.NoSqliteBase;
import kariminf.langpi.wordnet.SqliteRequestor;
import kariminf.langpi.wordnet.WNRequestor;
import kariminf.nalanpar.ParseHandler;
import kariminf.nalanpar.Types.Det;
import kariminf.nalanpar.Types.VerbTense;
import kariminf.sentrep.ston.request.ReqCreator;

public class Text2Ston implements ParseHandler {

	private final static String wordnetPath = 
			"../LangPi/wordnetDB/wordnet.sqlite";
	private ReqCreator rq = new ReqCreator();
	private WNRequestor wordnetReq = null; 
	private int numAction = 0;
	private int numRole = 0;
	private boolean isNP = false;
	private boolean isVP = false;
	private HashSet<String> rel = new HashSet<String>();
	private HashSet<String> acts = new HashSet<String>();
	private HashSet<String> subjs = new HashSet<String>();
	
	public Text2Ston() {
		try {
			wordnetReq = SqliteRequestor.create("eng", wordnetPath);
		} catch (NoSqliteBase | LangNotFound e) {
			System.out.println(e.getMessage());
			wordnetReq = null; 
		}
	}

	@Override
	public void beginNP() {
		isNP = true;
		numRole++;
		
		if (isNP && ! isVP)
			subjs.add("r" + numRole);
		//System.out.println("Start of NP phrase");
		
	}

	@Override
	public void endNP() {
		isNP = false;
		if (isVP){
			HashSet<String> objs = new HashSet<String>();
			objs.add("r" + numRole);
			rq.addObjectConjunctions("act" + numAction, objs);
		}
		//System.out.println("End of NP phrase");
		
	}

	@Override
	public void beginSentence() {
		//System.out.println("new sentence");
		acts = new HashSet<String>();
		rq.addSentence("AFF");
	}

	@Override
	public void beginVP() {
		
		numAction++;
		isVP = true;
		acts.add("act" + numAction);
		
		
		//System.out.println("Start of VP phrase");
		
	}

	@Override
	public void endVP() {
		isVP = false;
		//System.out.println("End of VP phrase");
		
	}

	@Override
	public void endAdjP() {
		//System.out.println("Start of Adjective phrase");
		
	}

	@Override
	public void beginAdjP() {
		//System.out.println("End of Adjective phrase");
		
	}

	@Override
	public void beginAdvP() {
		//System.out.println("Start of Adverb phrase");
		
	}

	@Override
	public void endAdvP() {
		//System.out.println("End of Adverb phrase");
		
	}

	@Override
	public void beginPP() {
		//System.out.println("Start of prepositional phrase");
		
	}

	@Override
	public void endPP() {
		//System.out.println("End of prepositional phrase");
		
	}

	@Override
	public void addNoun(String val, Det det, boolean plural, boolean proper) {
		String id = "r" + numRole;
		int nounSynSet = 0;
		
		if (wordnetReq == null)
			return;
		
		if(proper){
			//TODO search for its type: city, person, animal, etc.
			
		} else {
			val = val.toLowerCase();
			Morphology m = new Morphology();
			String lemma = m.lemma(val, "VERB");
			nounSynSet = wordnetReq.getSynset(lemma, "NOUN");
			if (nounSynSet < 1){
				System.out.println("No noun: " + val);
				return;
			}
				
		}
		
		rq.addRolePlayer(id, nounSynSet);
		if (plural)
			rq.setQuantity(id, "PL");
		if(proper)
			rq.setRoleProperName(id, val);
		
		rq.setDefined(id, det.name());
		
	}
	
	public String getSTON(){
		return rq.getStructuredRequest();
	}

	@Override
	public void addVerb(String val, VerbTense tense) {
		String id = "act" + numAction;
		int verbSynSet = 0;
		
		if (wordnetReq == null)
			return;
		Morphology m = new Morphology();
		String lemma = m.lemma(val, "VERB");
		//System.out.println("Lemma: " + lemma);
		verbSynSet = wordnetReq.getSynset(lemma, "VERB");
		
		if (verbSynSet < 1){
			System.out.println("No verb: " + val);
			return;
		}
		
		rq.addAction(id, verbSynSet);
		
		switch(tense){
		case FUTURE:
			break;
		case PAST:
			rq.addVerbSpecif(id, "PA", "", false, false);
			break;
		case PRESENT:
			rq.addVerbSpecif(id, "PR", "", false, false);
			break;
		default:
			break;
		
		}
		
		if (! subjs.isEmpty()){
			rq.addSubjectConjunctions(id, subjs);
			subjs = new HashSet<String>();
		}
	}

	@Override
	public void endSentence() {
		rq.addSentActionConjunctions(true, acts);
		
	}

}
