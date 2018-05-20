package lina.lexer;

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

	public TokenType getType() {
		return type;
	}

	public String getLexme() {
		return lexeme;
	}

	public int getLineNo() {
		return lineNo;
	}

	public int startCol() {
		return startCol;
	}

	public int endCol() {
		return endCol;
	}
}