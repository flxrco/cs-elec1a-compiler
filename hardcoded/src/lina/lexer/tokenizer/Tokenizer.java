package lina.lexer.tokenizer;

import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
	private static final char EOL = '\0'; //stands for 'end of line'

	public static Token nextToken(Line line) {
		ignoreWhitespace(line);
		ignoreComments(line);
		
		Token cache = null;

		if ((cache = checkString(line)) != null) {
			return cache;
		} else if ((cache = checkInteger(line)) != null) {
			return cache;
		} else if ((cache = checkKeyword(line)) != null) {
			return cache;
		} else if ((cache = checkSymbol(line)) != null) {
			return cache;
		} else if ((cache = checkOperator(line)) != null) {
			return cache;
		} else if ((cache = checkIdentifier(line)) != null) {
			return cache;
		} else {
			return null;
		}
	}

	private static void ignoreComments(Line line) {
		if (line.getChar() == '$') { //confirms that the current character in the line is $
			while (line.peekNext() != EOL && line.peekNext() != '$') { //stops if the next character is the end of line or another $
				line.increment();
			}
			if (line.peekNext() == '$') {
				line.increment(); //makes the current character the $ character
				line.increment(); //makes the current character whatever is next to $
			} else if (line.peekNext() == EOL) {
				//return an error because another $ was not met
			}
		}
	}

	private static void ignoreWhitespace(Line line) {
		while (Character.isWhitespace(line.getChar())) {
			line.increment();
		}
	}

	private static Token checkKeyword(Line line) {
		int index = line.getIndex();

		TokenType[] types = { //an array containing all of the keywords in TokenType
			TokenType.KEY_WHILE, TokenType.KEY_FOR, TokenType.KEY_END, TokenType.KEY_STEP,
			TokenType.KEY_TO, TokenType.KEY_DO, TokenType.KEY_IF, TokenType.KEY_ELSEIF,
			TokenType.KEY_ELSE, TokenType.KEY_THEN, TokenType.KEY_DECLARE, TokenType.KEY_CLASS,
			TokenType.METHOD_YIELD, TokenType.METHOD_STORE, TokenType.DATATYPE_INTEGER
		};

		for (TokenType type : types) { //iterate through the array one by one and check if the remaining items in line matches them
			String pattern = type.getPattern();
			if (line.startsWith(pattern)) {
				if (!Character.isLetter(line.getChar(pattern.length())) && !Character.isDigit(line.getChar(pattern.length()))) { //checks if the next character after the prefix is a whitespace
					line.setIndex(index);
					return line.tokenize(type); //tokenizes the lexeme minus the whitespace after the prefix
				}
			}
		}

		line.setIndex(index);
		return null; //return null if there are no next matches
	}

	private static Token checkInteger(Line line) {
		int index = line.getIndex();

		if (Character.isDigit(line.getChar())) {
			int length = 1;
			while (Character.isDigit(line.peekNext())) {
				length++;
				line.increment();
			}

			line.setIndex(index);
			return line.tokenize(TokenType.LIT_INT, length);
		}

		line.setIndex(index);
		return null;
	}

	private static Token checkString(Line line) {
		int index = line.getIndex();

		if (line.getChar() == '"') {
			int length = 1;
			
			while (line.peekNext() != EOL && line.peekNext() != '"') {
				length++;
				line.increment();
			}

			if (line.peekNext() == '"') {
				line.increment(); //makes the current character the " character
				length++;
				line.setIndex(index);
				return line.tokenize(TokenType.LIT_STRING, length); //return the entire string including the double quotes
			} else if (line.peekNext() == EOL) {

				return null;
				//should return an error because another " was not met
			}
		}

		line.setIndex(index);
		return null;
	}

	private static Token checkIdentifier(Line line) {
		int index = line.getIndex();
		if (Character.isLetter(line.getChar()) || line.getChar() == '_' || line.getChar() == '$') {
			int length = 1;

			while(Character.isLetter(line.peekNext()) || Character.isDigit(line.peekNext())) {
				length++;
				line.increment();
			}

			line.setIndex(index);
			return line.tokenize(TokenType.IDENTIFIER, length);
		}

		line.setIndex(index);
		return null;
	}

	private static Token checkOperator(Line line) {
		int index = line.getIndex();

		TokenType[] types = { //an array containing all of the operators in TokenType
			TokenType.ARI_INC, TokenType.ARI_DEC, TokenType.ARI_ADD, TokenType.ARI_SUB, TokenType.ARI_DIV, TokenType.ARI_MOD, TokenType.ARI_MUL,
			TokenType.REL_GRTR_EQ, TokenType.REL_LESS_EQ, TokenType.REL_GRTR, TokenType.REL_LESS, TokenType.REL_EQ, TokenType.REL_NOT_EQ,
			TokenType.ASSIGN,
			TokenType.LOG_AND, TokenType.LOG_OR
		};

		for (TokenType type : types) { //iterate through the array one by one and check if the remaining items in line matches them
			String pattern = type.getPattern();
			if (line.startsWith(pattern)) {
				line.setIndex(index);
				return line.tokenize(type);
			}
		}

		line.setIndex(index);
		return null;
	}

	private static Token checkSymbol(Line line) {
		int index = line.getIndex();

		TokenType[] types = { //an array containing all of the symbols in TokenType
			TokenType.DEL_CODE_OP, TokenType.DEL_CODE_CL,
			TokenType.DEL_BRCKT_OP, TokenType.DEL_BRCKT_CL,
			TokenType.DEL_PAREN_OP, TokenType.DEL_PAREN_CL,
			TokenType.DEL_CURLY_OP, TokenType.DEL_CURLY_CL,
			TokenType.COMMA, TokenType.SEMICOLON, TokenType.COLON
		};

		for (TokenType type : types) { //iterate through the array one by one and check if the remaining items in line matches them
			String pattern = type.getPattern();
			if (line.startsWith(pattern)) {
				line.setIndex(index);
				return line.tokenize(type);
			}
		}

		line.setIndex(index);
		return null;
	}
}