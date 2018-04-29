%option nounput yylineno
%{
#include "lexer.h"

%}

%% //---token definition start

"\/\/cmt:".*":\/"	;

[0-9]+	return LIT_INT;

\"[^\"\n]*\"	return LIT_STRING;

[ \t\n]+	; //WHITESPACE

"\/\/:"|":\/"	return DEL_CODE;
"\["|"\]"	return DEL_BRCKT;
"\("|"\)"	return DEL_PAREN;
"\{"|"\}" 	return DEL_CURLY;

"="	return ASSIGN_OP;

"+"|"-"|"/"|"\*"|"%"	return ARTH_OP_BI;
"++"|"--"	return ARTH_OP_UN;

"is>=" | "is<=" | "is==" | "is!=" | "is>" | "is<"	return REL_OP_BI;

"&&" | "||"	return LOGI_OP_BI;
"!"	return LOGI_OP_UN;

"while"|"for"	return KEY_FLOW_LOOP;
"if"|"else" return KEY_FLOW_COND;
"declare"	return KEY_DEC;
"Integer"	return KEY_DTYPE;
"store"|"yield"	return KEY_FUNC;

";"	return SEMICOLON;

[a-zA-Z$_][a-zA-Z0-9]*	return IDENTIFIER;

.	;	//GREEDY

%% //---token definition end

int yywrap(void) {
	return 1;
}