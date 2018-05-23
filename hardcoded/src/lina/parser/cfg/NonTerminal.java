package lina.parser.cfg;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

import lina.lexer.tokenizer.Token;
import lina.lexer.tokenizer.TokenType;
import lina.lexer.TokenStream;

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
	public boolean parse(TokenStream stream) {
		for (CFGNode[] rules : ruleList) {
			if (rules[0].lookAhead(stream)) {
				for (CFGNode node : rules) {
					if (node.parse(stream)) {
						//continue parsing if parse yields true
					} else {

						//parsing will stop if an error was detected
						return false;
					}
				}

				//if the loop stops naturally, it means that the parsing process for this production is successful
				return true;
			}
		}

		//if all possible look-aheads have been performed and not a single one showed up, return false
		/*
			make an exception reporter here
		*/

		return false;
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