%option noyywrap
%{

#ifdef PRINT
#define TOKEN(t) printf("Token: " #t "\n");
#else
#define TOKEN(t) return(t);
#endif

%}

id 			{letter}({letter}{digit})*
digit			[0-9]
letter			[a-zA-Z_]
number			{digit}+


%%
"print"			TOKEN(PRINT_T)
"int"			TOKEN(INT_T)
"+"			TOKEN(PLUS)
"-"			TOKEN(MINUS)
"*"			TOKEN(MULTIPLY)
"/"			TOKEN(DIVIDE)
"%"			TOKEN(MODULO)
";"			TOKEN(SEMICOLON)
"="			TOKEN(ASSIGN)
"<"			TOKEN(LESS_THAN)
"<="			TOKEN(LESS_THAN_EQUAL)
">"			TOKEN(GREATER_THAN)
">="			TOKEN(GREATER_THAN_EQUAL)
"!="			TOKEN(NOT_EQUAL)
"=="			TOKEN(EQUAL)
"&"			TOKEN(AND)
"|"			TOKEN(OR)
"!!"			TOKEN(SINGLE_LINE_COMMENT)
"!--"			TOKEN(MULTI_LINE_OPEN)
"--!"			TOKEN(MULTI_LINE_CLOSE)
if			TOKEN(IF_T)
then			TOKEN(THEN_T)
else			TOKEN(ELSE_T)
endif 			TOKEN(ENDIF_T)
while			TOKEN(WHILE_T)
endwhile		TOKEN(ENDWHILE_T)
{id}			TOKEN(ID)
{number}		TOKEN(NUMBER)

%%

