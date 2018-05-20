package lina.lexer;

import java.util.*;

public class Lexer {

	private static final char EOL = '\0'; //stands for 'end of line'

	private Token nextToken(Line line) {
		while (line.next()) {

		}
	}

	private void ignoreComments(Line line) {
		if (line.getChar() == '$') { //confirms that the current character in the line is $

			while (line.peekNext() != EOL && line.peekNext() != '$') { //stops if the next character is the end of line or another $
				//do nothing 
			}

			if (line.peekNext() == '$') {
				line.increment(); //makes the current character the $ character
				line.increment(); //makes the current character whatever is next to $
			} else if (line.peekNext() == EOL) {
				//return an error because another $ was not met
			}
		}
	}

	private Token checkKeywords(Line line) {
		static TokenType[] types = { //an array containing all of the keywords in TokenType
			TokenType.KEY_WHILE, TokenType.KEY_FOR, TokenType.KEY_END, TokenType.KEY_STEP,
			TokenType.KEY_TO, TokenType.KEY_DO, TokenType.KEY_IF, TokenType.KEY_ELSEIF,
			TokenType.KEY_ELSE, TokenType.KEY_THEN, TokenType.KEY_DECLARE, TokenType.KEY_CLASS,
			TokenType.METHOD_YELD, TokenType.METHOD_STORE, TokenType.DATATYPE_INTEGER
		}

		for (TokenType type : types) { //iterate through the array one by one and check if the remaining items in line matches them
			String pattern = type.getPattern();
			if (line.startsWith(pattern)) {
				if (Character.isWhitespace(line.getChar(pattern.length() + 1))) { //checks if the next character after the prefix is a whitespace
					return line.tokenize(type); //tokenizes the lexeme minus the whitespace after the prefix
				}
			}
		}

		return null; //return null if there are no next matches
	}

	private Token checkInteger(Line line) {
		if (Chracter.isDigit(line.getChar())) {
			int length = 1;
			while (Character.isDigit(line.peekNext())) {
				length++;
			}

			return list.tokenize(TokenType.LIT_INT, length);
		}

		return null;
	}

	private Token checkString(Line line) {
		if (line.getChar() == '"') {
			int length = 1;
			
			while (line.peekNext() != EOL && line.peekNext() != '"') {
				length++;
			}

			if (line.peekNext() == '"') {
				line.increment(); //makes the current character the " character
				length++;

				return line.tokenize(TokenType.LIT_STRING, length); //return the entire string including the double quotes
			} else if (line.peekNext() == EOL) {

				return null;
				//should return an error because another " was not met
			}
		}

		return null;
	}

	private Token checkIdentifier(Line line) {
		if (Character.isLetter(line.getChar()) || line.getChar() == '_' || line.getChar() == '$') {
			int length = 1;

			while(Character.isLetter(line.peekNext()) || Character.isDigit(line.peekNext())) {
				length++;
			}

			if (Character.isWhitespace(line.peekNext())) {
				line.tokenize(TokenType.IDENTIFIER, length);

			} else {
				return null;
			}
		}

		return null;
	}

	private Token checkOperator(Line line) {
		static TokenType[] types = { //an array containing all of the keywords in TokenType
			TokenType.ARI_INC, TokenType.ARI_DEC, TokenType.ARI_ADD, TokenType.ARI_SUB, TokenType.ARI_DIV, TokenType.ARI_MOD, TokenType.ARI_MUL,
			TokenType.ARI_GRTR_EQ, TokenType.ARI_LESS_EQ, TokenType.ARI_GRTR, TokenType.ARI_LESS, TokenType.ARI_EQ, TokenType.ARI_NOT_EQ,
			TokenType.ASSIGN,
			TokenType.LOG_AND, TokenType.LOG_OR
		}

		for (TokenType type : types) { //iterate through the array one by one and check if the remaining items in line matches them
			String pattern = type.getPattern();
			if (line.startsWith(pattern)) {
				return line.tokenize(type);
			}
		}
	}

	private Token checkSymbol(Line line) {
		static TokenType[] types = { //an array containing all of the keywords in TokenType
			TokenType.DEL_CODE_OP, TokenType.DEL_CODE_CL,
			TokenType.DEL_BRCKT_OP, TokenType.DEL_BRCKT_CL,
			TokenType.DEL_PAREN_OP, TokenType.DEL_PAREN_CL,
			TokenType.DEL_CURLY_OP, TokenType.DEL_CURLY_CL
		}

		for (TokenType type : types) { //iterate through the array one by one and check if the remaining items in line matches them
			String pattern = type.getPattern();
			if (line.startsWith(pattern)) {
				return line.tokenize(type);
			}
		}
	}

	//---utility functions

	private static List<Line> convertLines(List<String> lines) {
		List<Line> results = new ArrayList<>();

		for (int i = 0; i < lines.size(); i++) {
			results.add(new Line(lines.get(i), i + 1));
		}

		return results;
	}

	private static List<Line> convertLines(String path) throws IOException {
		return convertLines(readLines(path));
	}

	private static List<String> readLines(String path) throws IOException {
		List<String> lines = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String buffer = null;
			while ((buffer = lines.readLine()) != null) {
				lines.add(buffer);
			}
		}

		return lines;
	}
}

class Line {
	private String content;
	private int lineNo, index;

	public Line(String content, int lineNo) {
		this.content = content;
		this.lineNo = lineNo;
		index = 0;
	}

	public String getContent() {
		return content;
	}

	public String getRemaining() {
		return content.substring(index, content.length());
	}

	public int getLineNo() {
		return lineNo;
	}

	public int getIndex() {
		return index;
	}

	public boolean setIndex(int index) {
		if (index >= 0 && index < content.length()) {
			this.index = index;
			return true;
		} else {
			return false;
		}
	}

	public boolean increment() {
		return setIndex(index + 1);
	}

	public boolean decrement() {
		return setIndex(index - 1);
	}

	public char getChar() {
		return getChar(0);
	}

	public char getChar(int offset) {
		int ind = offset + index;

		return ind >= 0 && ind < content.length() ? content.charAt(ind) : '\0';
	}

	public char peekNext() {
		return getChar(1);
	}

	public char peekPrev() {
		return getChar(-1);
	}

	public char getNext() {
		return increment() ? getChar() : '\0';
	}

	public char getPrev() {
		return decrement() ? getChar() : '\0';
	}

	public Token tokenize(TokenType type, int lexmeLength) {
		int end = index + lexemeLength - 1;
		index += lexemeLength;
		return new Token(type, content.substring(index, end), lineNo, index, end);
	}

	public Token tokenize(TokenType type) {
		return tokenize(type, type.getPattern().length());
	}

	public boolean startsWith(String prefix) {
		return content.startsWith(prefix, index);
	}
}