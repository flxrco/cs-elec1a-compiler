/* A Bison parser, made by GNU Bison 2.7.  */

/* Bison interface for Yacc-like parsers in C
   
      Copyright (C) 1984, 1989-1990, 2000-2012 Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_Y_TAB_H_INCLUDED
# define YY_YY_Y_TAB_H_INCLUDED
/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     INT_T = 258,
     PLUS = 259,
     MINUS = 260,
     MULTIPLY = 261,
     DIVIDE = 262,
     MODULO = 263,
     SEMICOLON = 264,
     ASSIGN = 265,
     LESS_THAN = 266,
     LESS_THAN_EQUAL = 267,
     GREATER_THAN = 268,
     GREATER_THAN_EQUAL = 269,
     NOT_EQUAL = 270,
     EQUAL = 271,
     AND = 272,
     OR = 273,
     SINGLE_LINE_COMMENT = 274,
     MULTI_LINE_OPEN = 275,
     MULTI_LINE_CLOSE = 276,
     IF_T = 277,
     THEN_T = 278,
     ELSE_T = 279,
     ENDIF_T = 280,
     WHILE_T = 281,
     ENDWHILE_T = 282,
     ID = 283,
     NUMBER = 284
   };
#endif
/* Tokens.  */
#define INT_T 258
#define PLUS 259
#define MINUS 260
#define MULTIPLY 261
#define DIVIDE 262
#define MODULO 263
#define SEMICOLON 264
#define ASSIGN 265
#define LESS_THAN 266
#define LESS_THAN_EQUAL 267
#define GREATER_THAN 268
#define GREATER_THAN_EQUAL 269
#define NOT_EQUAL 270
#define EQUAL 271
#define AND 272
#define OR 273
#define SINGLE_LINE_COMMENT 274
#define MULTI_LINE_OPEN 275
#define MULTI_LINE_CLOSE 276
#define IF_T 277
#define THEN_T 278
#define ELSE_T 279
#define ENDIF_T 280
#define WHILE_T 281
#define ENDWHILE_T 282
#define ID 283
#define NUMBER 284



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;

#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */

#endif /* !YY_YY_Y_TAB_H_INCLUDED  */
