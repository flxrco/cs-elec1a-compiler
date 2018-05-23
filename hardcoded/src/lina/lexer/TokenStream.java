package lina.lexer;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import lina.lexer.tokenizer.Token;

public class TokenStream {
	
	private final List<Token> tokens;
	private final List<String> lines;
	
	private Queue<Token> tokenQueue;

	public TokenStream(List<Token> tokens, List<String> lines) {
		this.tokens = new ArrayList<>();
		this.lines = new ArrayList<>();
		this.tokens.addAll(tokens);
		this.lines.addAll(lines);
		resetQueue();
	}

	public String getPointer(Token token) {
		String line = lines.get(token.getLineNo()), lineNo = String.format("%d: ", token.getLineNo() + 1);
		int offsetStart = token.getStartCol() + lineNo.length(), 
			offsetEnd = token.getEndCol() + lineNo.length(),
			offsetLength = line.length() + lineNo.length();

		StringBuilder str = new StringBuilder();
		str.append(lineNo).append(line).append("\n");

		for (int i = 0; i < offsetLength; i++) {
			if (i == offsetStart) {
				str.append("^");
			} else if (i < offsetStart) {
				str.append(" ");
			} else {
				break;
			}
		}

		str.append("\n");

		return str.toString();
	}

	public String getLocation(Token token) {
		StringBuilder str = new StringBuilder();
		str.append(getPointer(token));
		str.append(token.getCoordinates());
		return str.toString();
	}

//queue functions
	public Queue<Token> getQueue() {
		return tokenQueue;
	}

	public Queue<Token> resetQueue() {
		tokenQueue = new LinkedList<>();

		for (Token token : tokens) {
			tokenQueue.add(token);
		}

		return tokenQueue;
	}

	public Token peek() {
		return tokenQueue.peek();
	}

	public Token poll() {
		return tokenQueue.poll();
	}

//toString-like functions

	public String linesToString() {
		StringBuilder str = new StringBuilder();
		
	for (int i = 0; i < lines.size(); i++) {
			str.append(String.format("%3d: ", i + 1)).append(lines.get(i)).append("\n");
		}

		return str.toString();
	}

	public String tokensToString() {
		StringBuilder str = new StringBuilder();

		for (Token token : tokens) {
			str.append(token.toString()).append("\n");
		}

		return str.toString();
	}
}