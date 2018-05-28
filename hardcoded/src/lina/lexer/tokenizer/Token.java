package lina.lexer.tokenizer;

public class Token {
	private TokenType type;
	private int lineNo, startCol, endCol;
	private String lexeme;

	public Token(TokenType type, String lexeme, int lineNo, int startCol, int endCol) {
		this.type = type;
		this.lexeme = lexeme;
		this.lineNo = lineNo;
		this.startCol = startCol;
		this.endCol = endCol;
	}

	public Token(Token token) {
		this(token.type, token.lexeme, token.lineNo, token.startCol, token.endCol);
	}

	public TokenType getType() {
		return type;
	}

	public String getLexeme() {
		return lexeme;
	}

	public int getLineNo() {
		return lineNo;
	}

	public int getStartCol() {
		return startCol;
	}

	public int getEndCol() {
		return endCol;
	}

	public String getCoordinates() {
		return String.format("ln %d, col %d-%d\n", lineNo + 1, startCol + 1, endCol + 1);
	}

	public String getTypeLabel() {
		return type.getLabel();
	}

	public boolean compareType(TokenType type) {
		return this.type == type;
	}

	@Override
	public String toString() {
		return String.format("%s -> %s\n%s", lexeme, type.getLabel(), getCoordinates());
	}
}