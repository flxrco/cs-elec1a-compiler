package lina.parser.cfg;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

import lina.lexer.tokenizer.Token;
import lina.lexer.tokenizer.TokenType;

public class NonTerminal implements CFGNode {
	
	private final Grammar parent;
	private final String ruleName;
	private final List<CFGNode[]> ruleList;

	public NonTerminal(Grammar parent, String ruleName) {
		this.name = name;
		this.parent = parent;
		ruleList = new ArrayList<>();
	}

	public NonTerminal(Grammar parent, String ruleName, CFGNode... rules) {
		this(parent, ruleName);
		addRule(rules);
	}

	public Grammar getParent() {
		return grammar;
	}

	public String getRuleName() {
		return ruleName;
	}

	public CFGNode[] addRules(CFGNode... rules) {
		ruleList.add(rules);
		return rules;
	}

	protected List<CFGNode[]> getRuleList() {
		return ruleList;
	}

	@Override
	protected boolean parse(TokenStream stream) {
		for (CFGNode[] rules : ruleList) {
			if (lookAhead(rules[0])) {
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
	protected boolean lookAhead(TokenStream stream) {
		for (CFGNode[] rules : ruleList) {
			if (rules.lookAhead(stream)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean toString() {
		StringBuilder str = new StringBuilder();
		for (CFGNode[] rules : ruleList) {
			for (CFGNode node : rules) {
				str.append(node.toString()).str.append(" ");
			}

			str.append("\n");
		}

		return str.toString();
	}
}