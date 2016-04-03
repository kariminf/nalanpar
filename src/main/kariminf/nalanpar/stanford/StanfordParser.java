package kariminf.nalanpar.stanford;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import kariminf.nalanpar.POSTransformer;
import kariminf.nalanpar.ParseHandler;
import kariminf.nalanpar.Types.POSType;
import kariminf.nalanpar.Types.Phrase;
import kariminf.nalanpar.UnivParser;

public class StanfordParser extends UnivParser {
	
	static final String model = 
			"../stanford-parser-full-2014-08-27/models/lexparser/englishFactored.ser.gz";

	
	private int pointer = -1;
	private ArrayList<Element> elements = new ArrayList<Element>();
	
	public StanfordParser(ParseHandler handler, POSTransformer posTrans) {
		super(handler, posTrans);
	}

	@Override
	protected boolean prepare(String text) {
		pointer = -1;
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
		
		return true;
	}
	

	@Override
	protected boolean next() {
		pointer++;
		//TODO verify the pointer < size_array & verify size_array > 0
		return true;
	}
	

	@Override
	protected Element getElement() {
		//TODO complete
		
		return null;
	}
	
	
	private void parseTree(Tree t){
		Label l = t.label();
		
		String v = null;
		
		if (l != null)
			v = l.value();
		
		if (v != null)
			System.out.println("BEGIN:" + v);
		
		Tree[] childs = t.children();
		if (childs != null){
			for (Tree child: childs){
				//System.out.print(" "); //result += " ";
				if (child.isLeaf()){
					if (child != null)
						System.out.print(child.label().value());
					
					continue;
				}
				parseTree(child);
			}
			//result += " ";
		}
		
		if (v != null)
			System.out.println("\nEND:" + v);
		//System.out.println(")");
			
	}

	
}
