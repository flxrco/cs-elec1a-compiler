package lina.parser.cfg;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

import lina.lexer.tokenizer.Token;
import lina.lexer.tokenizer.TokenType;
import lina.lexer.TokenStream;
import lina.parser.parsetree.ParseNode;
import lina.parser.parsetree.ParseNonTerminal;

public class NonTerminal implements CFGNode {

	private final String ruleName;
	private final List<CFGNode[]> ruleList;

	public NonTerminal(String ruleName) {
		this.ruleName = ruleName;
		ruleList = new ArrayList<>();
	}

	public NonTerminal(String ruleName, CFGNode... rules) {
		this(ruleName);
		addRules(rules);
	}


	public String getRuleName() {
		return ruleName;
	}

	public CFGNode[] addRules(CFGNode... rules) {
		ruleList.add(rules);
		return rules;
	}

	public List<CFGNode[]> getRuleList() {
		return ruleList;
	}

	@Override
	public ParseNode parse(TokenStream stream, List<String> errors) {
		ParseNonTerminal nt = new ParseNonTerminal(ruleName);
		
		for (CFGNode[] rules : ruleList) {
			if (rules[0].lookAhead(stream)) {
				for (CFGNode node : rules) {
					nt.addNode(node.parse(stream, errors));
				}

				return nt;
			}
		}

		Token tok = stream.peek();
		StringBuilder str = new StringBuilder();
		str.append(String.format("%d: no lookaheads remaining for \n", tok.getLineNo() + 1));
		str.append(stream.getPointer(tok, "          "));
		str.append(String.format("\tsymbol: %s %s\n", tok.getTypeLabel(), tok.getLexeme()));
		str.append(String.format("\tlocation: %s", tok.getCoordinates()));

		errors.add(str.toString());		

		return nt;
	}

	@Override
	public boolean lookAhead(TokenStream stream) {
		for (CFGNode[] rules : ruleList) {
			if (((CFGNode) rules[0]).lookAhead(stream)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return toString("");
	}

	public String toString(String preLine) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < ruleList.size(); i++) {
			CFGNode[] rules = ruleList.get(i);
			for (CFGNode node : rules) {
				if (node instanceof Terminal) {
					Terminal t = (Terminal) node;
					str.append(t.getType().name()).append(" ");
				} else {
					NonTerminal nt = (NonTerminal) node;
					str.append(nt.ruleName).append(" ");
				}

			}

			if (i < ruleList.size() - 1) {
				str.append(String.format("\n%s| ", preLine));
			}
		}
		return str.toString();
	}
}