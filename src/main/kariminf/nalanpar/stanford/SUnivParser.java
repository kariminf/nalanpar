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
import kariminf.nalanpar.UnivParser;

public class SUnivParser extends UnivParser {
	
	static final String model = 
			"../stanford-parser-full-2014-08-27/models/lexparser/englishFactored.ser.gz";

	
	private Iterator<Element> pointer = null;
	private ArrayList<Element> elements = new ArrayList<Element>();
	
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
	
	
	private void parseTree(Tree t){
		
		if (t.isPreTerminal()){
			
			Label l = t.label();
			String pos = null;
			
			if (l != null)
				pos = l.value();
			
			String val = t.children()[0].label().value();
			
			Element e = getLeafElement(pos, val);
			
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
			elements.add(e);
		}
			
		
		Tree[] childs = t.children();
		
		if (childs != null){
			for (Tree child: childs){
				parseTree(child);
			}
		}
		
		
		if (v != null){
			//System.out.println("\nEND:" + v);
			Element e = getPhraseElement(v, false);
			elements.add(e);
		}
			
	}
	
	
	private Element getPhraseElement(String pos, boolean begin){
		return null;
	}
	
	private Element getLeafElement(String pos, String val){
		return null;
	}

	
}
