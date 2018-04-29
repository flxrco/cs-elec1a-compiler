enum Type {
	DEL_CODE_OP = 1,	// //:
	DEL_CODE_CL = 2,	// ://

	DEL_PAREN_OP = 3,	// (
	DEL_PAREN_CL = 4,	// )

	DEL_BRCKT_OP = 5,	// [
	DEL_BRCKT_CL = 6, 	// ]

	DEL_CURLY_OP = 7,	// {
	DEL_CURLY_CL = 8,	// }

	ASSIGN = 9,			// =

	ARI_INC = 10,		// ++
	ARI_DEC = 11,		// --

	ARI_ADD = 12,		// +
	ARI_SUB = 13,		// -
	ARI_DIV = 14,		// /
	ARI_MUL = 15,		// *
	ARI_MOD = 16,		// %

	REL_GRTR_EQ = 17,	// is>=
	REL_LESS_EQ = 18,	// is<=
	REL_GRTR = 19,		// is>
	REL_LESS = 20,		// is<
	REL_EQ = 21,		// is==
	REL_NOT_EQ = 22,	// is!=

	LOG_AND = 23,		// &&
	LOG_OR = 24,		// ||

	LOG_NOT = 25,		// !

	KEY_WHILE = 26,		// while
	KEY_FOR = 27,		// for

	KEY_IF = 28,		// if
	KEY_ELSE = 29,		// else

	KEY_DECLARE = 30,	// declare
	KEY_STORE = 31,		// store
	KEY_YIELD = 32,		// yield
	KEY_CLASS = 33,		// class
	KEY_DATATYPE = 34,

	IDENTIFIER = 35,

	LIT_INT = 36,		
	LIT_STRING = 37,	

	SEMICOLON = 38,		// ;
	COLON = 39			// :
};