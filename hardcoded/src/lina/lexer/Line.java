package lina.lexer.tokenizer;

public class Line {
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

	public boolean hasNext() {
		return index + 1 < content.length();
	}

	public boolean hasPrev() {
		return index - 1 >= content.length();
	}

	public Token tokenize(TokenType type, int lexemeLength) {
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