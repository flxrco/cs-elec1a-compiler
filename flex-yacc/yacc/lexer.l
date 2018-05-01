%option nounput yylineno

%% //---token definition start

"\/\/cmt:".*":\/"	;

[0-9]+			return LIT_INT;
\"[^\"\n]*\"	return LIT_STRING;

"\/\/:"	return DEL_CODE_OP;
":\/"	return DEL_CODE_CL;

"\["	return DEL_BRCKT_OP;
"\]"	return DEL_BRCKT_CL;

"\("	return DEL_PAREN_OP;
"\)"	return DEL_PAREN_CL;

"\{"	return DEL_CURLY_OP;
"\}"	return DEL_CURLY_CL;

"++"	return ARI_INC;
"--"	return ARI_DEC;

"+"	return ARI_ADD;
"-"	return ARI_SUB;
"/"	return ARI_DIV;
"%"	return ARI_MOD;
"*"	return ARI_MUL;

"is>="	return REL_GRTR_EQ;
"is<="	return REL_LESS_EQ;
"is>"	return REL_GRTR;
"is<"	return REL_LESS;
"is=="	return REL_EQ;
"is!="	return REL_NOT_EQ;

"is="	return ASSIGN;

"&&"	return LOG_AND;
"||"	return LOG_OR;

"while" return KEY_WHILE;
"for"	return KEY_FOR;
"end"	return KEY_END;
"step"	return KEY_STEP;
"to"	return KEY_TO;
"do"	return KEY_DO;

"if"		return KEY_IF;
"else if"	return KEY_ELSEIF;
"else"		return KEY_ELSE;
"then"		return KEY_THEN;

"declare"	return KEY_DECLARE;
"store"		return KEY_STORE;
"yield"		return KEY_YIELD;
"class"		return KEY_CLASS;

"Integer"	return KEY_DATATYPE;

";"	return SEMICOLON;
":"	return COLON;
","	return COMMA;

[a-zA-Z$_][a-zA-Z0-9]*	return IDENTIFIER;

[ \t\n]+	; //WHITESPACE
.	return ANTITOKEN;	//GREEDY

%% //---token definition end

int yywrap(void) {
	return 1;
}