package lina.parser.cfg;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import lina.lexer.TokenStream;
import lina.lexer.tokenizer.TokenType;
import lina.parser.parsetree.ParseNode;

public class Grammar {

	private final List<String> nameCache;
	private final List<Object[]> ruleCache;

	private Map<String, NonTerminal> nonTerminals;
	private NonTerminal head;

	public Grammar() {
		nonTerminals = new HashMap<>();
		nameCache = new ArrayList<>();
		ruleCache = new ArrayList<>();
	}

	public ParseNode parse(TokenStream stream) {
		List<String> errors = new ArrayList<>();
		ParseNode node =  head.parse(stream, errors);

		for (String err : errors) {
			System.out.println(err);
		}

		return errors.size() == 0 ? node : null;
	}

	public void addProduction(String name, Object... rules) {
		nameCache.add(name);
		ruleCache.add(rules);
	}

	public void buildGrammar() {
		nonTerminals = new HashMap<>();

		//first stage - create the nonTerminals
		for (int i = 0; i < nameCache.size(); i++) {
			nonTerminals.put(nameCache.get(i), new NonTerminal(nameCache.get(i)));
		}

		//second stage - populate the nonTerminals
		for (int i = 0; i < nameCache.size(); i++) {

			NonTerminal nt = nonTerminals.get(nameCache.get(i)); //fetch the NonTerminal mapped with that name

			/*
			if (nt == null) {
				//stop operation,
			}
			*/
			
			List<CFGNode> cache = new ArrayList<>(); //create a list as a storage of the classes that implements CFGNode
			
			for (Object obj : ruleCache.get(i)) {
				if (obj instanceof TokenType) {
					cache.add(new Terminal((TokenType) obj));
				} else if (obj instanceof String) {
					cache.add(nonTerminals.get(obj));
				} else {
					//throw an exception here
				}
			}
			CFGNode[] temp = new CFGNode[cache.size()];
			nt.addRules(cache.toArray(temp)); //turn the cache into an array, and add it as a rule in the NonTerminal object
		}

		head = nonTerminals.get(nameCache.get(0));
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		String cache = "";
		for (String key : nameCache) {
			if (key.equals(cache)) {
				continue;
			} else {
				cache = key;
				/*
				str.append(String.format("%s :- %s\n\n", key, nonTerminals.get(key).toString()));
				*/
				String header = String.format("%s : ", key);
				str.append(header);
				str.append(nonTerminals.get(key).toString(header.substring(0, header.length() - 2).replaceAll(".", " ")));
				str.append("\n\n");
			}
		}

		return str.toString();
	}
}