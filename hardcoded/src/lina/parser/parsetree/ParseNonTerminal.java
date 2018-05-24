package lina.parser.parsetree;

import java.util.List;
import java.util.ArrayList;

public class ParseNonTerminal implements ParseNode {

	private final String ruleName;
	private final List<ParseNode> nodes;

	public ParseNonTerminal(String ruleName) {
		nodes = new ArrayList<>();
		this.ruleName = ruleName;
	}

	public String getRuleName() {
		return ruleName;
	}

	public List<ParseNode> getNodes() {
		return nodes;
	}

	public void addNode(ParseNode node) {
		nodes.add(node);
	}

	public void addNodes(ParseNode... nodes) {
		for (ParseNode node : nodes) {
			addNode(node);
		}
	}

	public List<String> depthFirst() {
		List<String> list = new ArrayList<>();
		depthFirst(list);
		return list;
	}

	public void depthFirst(List<String> list) {
		list.add(toString());
		for (ParseNode node : nodes) {
			if (node instanceof ParseNonTerminal) {
				((ParseNonTerminal) node).depthFirst(list);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(String.format("%s : ", ruleName));
		
		for (ParseNode node : nodes) {
			if (node instanceof ParseNonTerminal) {
				str.append(((ParseNonTerminal) node).ruleName);
			} else {
				str.append(node.toString());
			}

			str.append(" ");
		}

		return str.toString();
	}
}