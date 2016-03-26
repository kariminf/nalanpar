package kariminf.nalanpar.stanford;

import java.io.StringReader;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;


public class TestStanfordParser {

	static final String model = 
			"../stanford-parser-full-2014-08-27/models/lexparser/englishFactored.ser.gz";
	static String text =
			"The mother that ate 5 apples play at the backyard.";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] options = { "-maxLength", "80", "-retainTmpSubcategories" };
		LexicalizedParser lp = LexicalizedParser.loadModel(model, options);
		TreebankLanguagePack tlp = lp.getOp().langpack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();

		Tokenizer<? extends HasWord> toke =
				tlp.getTokenizerFactory().getTokenizer(new StringReader(text));
		List<? extends HasWord> sentence = toke.tokenize();

		Tree parse = lp.parse(sentence);
		parse.pennPrint();
		
		/*for (Tree t : parse.children()){
			t.pennPrint();
			System.out.println("---------");
		}*/
		parseTree(parse);
		
		System.out.println();
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
		System.out.println(tdl);
		System.out.println();

		//System.out.println(parse.taggedYield());
	}
	
	public static void parseTree(Tree t){
		Label l = t.label();
		if (t.isLeaf()){
			if (l != null)
				System.out.print(l.value());
			return;
		}
		
		///String result = "(";
		//System.out.print("(");
		String v = null;
		
		if (l != null)
			v = l.value();
		
		if (v != null)
			System.out.println("BEGIN:" + v);
		
		Tree[] childs = t.children();
		if (childs != null){
			for (Tree child: childs){
				//System.out.print(" "); //result += " ";
				parseTree(child);
			}
			//result += " ";
		}
		
		if (v != null)
			System.out.println("\nEND:" + v);
		//System.out.println(")");
			
	}

}
