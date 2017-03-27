package kariminf.nalanpar.stanford;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import kariminf.nalanpar.POSTransformer;
import kariminf.nalanpar.ParseHandler;
import kariminf.nalanpar.Types.Featured;
import kariminf.nalanpar.Types.NounFeature;
import kariminf.nalanpar.Types.Phrasal;
import kariminf.nalanpar.Types.Posable;
import kariminf.nalanpar.Types.Terminal;
import kariminf.nalanpar.stanford.EnSPOS2Univ.PennTreeBankTerminal;
import kariminf.sentrep.types.Determiner;
import kariminf.nalanpar.UnivParser;

public class SUnivParser extends UnivParser {
	
	static final String model = 
			"../stanford-parser-2014/models/lexparser/englishFactored.ser.gz";
	
	
	private Iterator<Element> pointer = null;
	private ArrayList<Element> elements = new ArrayList<Element>();
	
	private Determiner determiner = Determiner.NONE;
	
	private boolean prepPh = false;
	
	// This is used to capture if the last element is terminal or not
	private Element pastTerminal = null;
	
	public SUnivParser(ParseHandler handler, POSTransformer posTrans) {
		super(handler, posTrans);
	}

	@Override
	protected boolean prepare(String text) {
		String[] options = { "-maxLength", "80", "-retainTmpSubcategories" };
		LexicalizedParser lp = LexicalizedParser.loadModel(model, options);
		TreebankLanguagePack tlp = lp.getOp().langpack();

		Tokenizer<? extends HasWord> toke =
				tlp.getTokenizerFactory().getTokenizer(new StringReader(text));
		List<? extends HasWord> sentence = toke.tokenize();
		Tree parse = lp.parse(sentence);
		//parse.pennPrint();
		
		
		if (parse == null || parse.isEmpty()){
			parse = null;
			return false;
		}
		
		parseTree(parse);
		
		pointer = elements.iterator();
		
		return true;
	}
	

	@Override
	protected boolean next() {
		return pointer.hasNext();
	}
	

	@Override
	protected Element getElement() {
		return pointer.next();
	}
	
	/**
	 * 
	 * @param t
	 */
	private void parseTree(Tree t){
		
		if (t.isPreTerminal()){
			
			Label l = t.label();
			String pos = null;
			
			if (l != null)
				pos = l.value();
			try{
				PennTreeBankTerminal p = PennTreeBankTerminal.valueOf(pos);
				
			}
			catch (IllegalArgumentException e){
				//TODO it is a punctuation, deal with it after
				return;
			}
			
			String val = t.children()[0].label().value();
			
			Element e = getLeafElement(pos, val);
			
			
			
			Posable p = e.getPos();
			if (p == null)
				return;
			Terminal te = p instanceof Terminal? (Terminal) p: null;
			
			
			
			if (te == Terminal.DET){
				determiner  = posTrans.getDet(val);
				pastTerminal = e;
				return;
			}
			
			if (te == Terminal.NOUN){
				NounFeature nf = (NounFeature) e.getFeature();
				if (nf != null){
					switch (determiner){
					case NO:
						nf.setUnDefined();
						break;
					case YES:
						nf.setDefined();
						break;
					default:
						break;
					
					}
					
					determiner = Determiner.NONE;
				}
				
				if ( pastTerminal != null && pastTerminal.getPos() == Terminal.NOUN){
					
					Element e2 = posTrans.fuseTerminals(pastTerminal, e);
					if (e2 != e){
						elements.remove(elements.size()-1);	
						e = e2;
					}
				}
			}
			
			pastTerminal = e;
			
			if(prepPh){
				elements.remove(elements.size()-1);		
			}

			elements.add(e);
			
			return;
		}
		
		
		Label l = t.label();
		String v = null;
		
		if (l != null)
			v = l.value();
		
		if (v != null){
			//System.out.println("\nEND:" + v);
			Element e = getPhraseElement(v, true);
			if (e != null)
				prepPh = ((e.getPos() != null) && (((Phrasal) e.getPos()) == Phrasal.PP));
			elements.add(e);
		}
		
		pastTerminal = null;
		
		Tree[] childs = t.children();
		
		if (childs != null){
			for (Tree child: childs){
				//pastTerminal = null;
				parseTree(child);
				//pastTerminal = null;
			}
		}
		
		
		if (v != null){
			//System.out.println("\nEND:" + v);
			Element e = getPhraseElement(v, false);
			elements.add(e);
		}
			
	}
	
	/**
	 * 
	 * @param pos
	 * @param begin
	 * @return
	 */
	private Element getPhraseElement(String pos, boolean begin){
		return posTrans.getPhrasalElement(pos, begin);
	}
	
	/**
	 * 
	 * @param pos
	 * @param val
	 * @return
	 */
	private Element getLeafElement(String pos, String val){
		return posTrans.getTerminalElement(pos, val);
	}

	
}
