package lina.lexer;

public enum TokenType {
	LIT_INT(null, "integer literal"),
	LIT_STRING(null, "string literal"),

	DEL_CODE_OP("//:", "open code block"),
	DEL_CODE_CL(":/", "closed code block"),

	DEL_BRCKT_OP("[", "open bracket"),
	DEL_BRCKT_CL("]", "closed bracket"),

	DEL_PAREN_OP("(", "open parenthesis"),
	DEL_PAREN_CL(")", "closed parenthesis"),

	DEL_CURLY_OP("{", "open curly"),
	DEL_CURLY_CL("}", "closed curly"),

	ARI_INC("++", "increment operator"),
	ARI_DEC("--", "decrement operator"),

	ARI_ADD("+", "addition operator"),
	ARI_SUB("-", "subtraction operator"),
	ARI_DIV("/", "division operator"),
	ARI_MOD("%", "modulo operator"),
	ARI_MUL("*", "multiplication operator"),

	REL_GRTR_EQ("is>=", "greater than or equal to"),
	REL_LESS_EQ("is<= less than or equal to"),
	REL_GRTR("is>", "greater than"),
	REL_LESS("is<", "less than"),
	REL_EQ("is==", "equal to"),
	REL_NOT_EQ("is!=", "not equal to"),

	ASSIGN("is=", "assignment operator"),

	LOG_AND("&&", "and operator"),
	LOG_OR("||", "or operator"),

	KEY_WHILE("while", "while keyword"),
	KEY_FOR("for", "for keyword"),
	KEY_END("end", "end keyword"),
	KEY_STEP("step", "step keyword"),
	KEY_TO("to", "to keyword"),
	KEY_DO("do", "do keyword"),

	KEY_IF("if", "if keyword"),
	KEY_ELSEIF("elseif", "else if keyword"),
	KEY_ELSE("else", "else keyword"),
	KEY_THEN("then", "then keyword"),

	KEY_DECLARE("declare", "declare keyword"),
	KEY_CLASS("class", "class keyword"),

	METHOD_STORE("store", "store keyword"),
	METHOD_YIELD("yield", "yield keyword"),

	DATATYPE_INTEGER("Integer", "integer datatype"),

	SEMICOLON(";", "semicolon"),
	COLON(":", "colon"),
	COMMA(",", "comma"),

	IDENTIFIER(null, "identifier");

	private final String pattern, label;

	private TokenType(String pattern, String label) {
		this.pattern = pattern;
		this.label = label;
	}

	public String getPattern() {
		return pattern;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public String toChar() {
		return.getPattern().charAt(0);
	}
}