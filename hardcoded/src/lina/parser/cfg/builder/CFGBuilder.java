package lina.parser.cfg.builder;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import lina.parser.*;
import lina.parser.cfg.*;

import lina.lexer.tokenizer.TokenType;

public class CFGBuilder {
	/*
	private static class Builder {
		private List<String> nameCache;
		private List<Object[]> ruleCache;

		public Builder() {
			nameCache = new ArrayList<>();
			ruleCache = new ArrayList<>();
		}

		public void addProduction(String name, Object... rules) {
			nameCache.add(name);
			ruleCache.add(rules);
		}

		public Grammar renderNodes() {
			Map<String, NonTerminal> nonTerminals = new HashMap<>();

			//first stage - create the nonTerminals
			for (int i = 0; i < nameCache.size(); i++) {
				nonTerminals.put(nameCache.get(i), new NonTerminal(nameCache.get(i)));
			}

			//second stage - populate the nonTerminals
			for (int i = 0; i < nameCache.size(); i++) {
				NonTerminal nt = nonTerminals.get(nameCache.get(i));
				List<CFGNode> cache = new ArrayList<>();
				for (Object obj : ruleCache.get(i)) {
					if (obj instanceof TokenType) {
						cache.add(new Terminal((TokenType) obj, ""));
					} else {
						if (nonTerminals.get(obj) == null) {
							System.out.println(String.format("RH: %s, LH: %s", nameCache.get(i), obj.toString()));
						}
						cache.add(nonTerminals.get(obj));
					}
				}
				CFGNode[] arr = new CFGNode[cache.size()];
				nt.addRules(cache.toArray(arr));
			}

			String prev = "";
			for (String key : nameCache) {
				if (prev.equals(key)) {
					continue;
				}

				prev = key;
				System.out.println(String.format("%s := %s", key, nonTerminals.get(key).toString()));
			}

			return new Grammar(nonTerminals.get(nameCache.get(0))); //retrieve starting production and input it in a Grammar object
		}
	}

	private static Builder builder = new Builder();

	private static void add(String name, Object... rules) {
		builder.addProduction(name, rules);
	}

	private static Grammar render() {
		return builder.renderNodes();
	}
	*/
}