%option nounput yylineno

%{
#include "lexer.h"

%}

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

"="	return ASSIGN;

"&&"	return LOG_AND;
"||"	return LOG_OR;
"!"		return LOG_NOT;

"while" return KEY_WHILE;
"for"	return KEY_FOR;

"if"		return KEY_IF;
"else"		return KEY_ELSE;

"declare"	return KEY_DECLARE;
"store"		return KEY_STORE;
"yield"		return KEY_YIELD;
"class"		return KEY_CLASS;

"Integer"	return KEY_DATATYPE;

";"	return SEMICOLON;
":"	return COLON;

[a-zA-Z$_][a-zA-Z0-9]*	return IDENTIFIER;

[ \t\n]+	; //WHITESPACE
.	;	//GREEDY

%% //---token definition end

int yywrap(void) {
	return 1;
}