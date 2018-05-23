package lina.parser;

public class RecursiveDescent {

	//program := DEL_CODE_OP class DEL_CODE_CL
	private static boolean parseProgram(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.DEL_CODE_OP)) {
			stream.poll();
			if (parseClass(stream)) {
				if (stream.peek().compareType(TokenType.DEL_CODE_CL)) {
					stream.poll();

				} else {

				}
			} else {

			}
		} else {

		}
	}


	//class := KEY_CLASS DEL_BRCKT_OP IDENTIFIER DEL_BRCKT_CL DEL_CURLY_OP statements DEL_CURLY_CL
	private static boolean parseClass(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_CLASS)) {
			stream.poll();
			if (stream.peek().compareType(TokenType.DEL_BRCKT_OP)) {
				stream.poll();
				if (stream.peek().compareType(TokenType.IDENTIFIER)) {
					stream.poll();
					if (stream.peek().compareType(TokenType.DEL_BRCKT_CL)) {
						stream.poll();
						if (stream.peek().compareType(TokenType.DE_CURLY_OP)) {
							stream.poll();
							if (parseStatements(stream)) {
								if (stream.peek().compareType(TokenType.DEL_CURLY_CL)) {
									stream.poll();
								}
							}
						} else {
							//curly bracket expected
						}
					} else {
						//cl bracket expected
					}
				} else {
					//identifier expected
				}
			} else {
				//op bracket expected
			}
		} else {
			//class keyword expected
		}
	}

	//statements := statement statements_prime
	private static boolean parseStatements(Queue<Token> stream) {
		if (parseStatement(stream)) {
			if (parseStatementsPrime(stream)) {

			}
		}
	}

	//statements_prime := statement statements_prime |	/*epsilon*/
	private static boolean parseStatementsPrime(Queue<Token> stream) {
		if (parseStatement(stream)) {
			if (parseStatementsPrime(stream)) {
			}
		} else {
		}
	}

	/*
	statement 	:	if_statement
				|	for_statement
				|	while_statement
				|	declaration_statement
				|	inner_statement SEMICOLON
	*/
	private static boolean parseStatement(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_IF)) {
			parseIf(stream);
		} else if (stream.peek().compareType(TokenType.KEY_FOR)) {
			parseFor(stream);
		} else if (stream.peek().compareType(TokenType.KEY_WHILE)) {
			parseWhile(stream);
		} else if (stream.peek().compareType(TokenType.KEY_DECLARE)) {
			parseDeclare(stream);
		} else if (parseInnerStatement(stream)) {
			if (stream.peek().compareType(TokenType.SEMICOLON)) {
				stream.poll();
			}
		}
	}

	/*
	inner_statement	:	assignment_statement
					|	yield_method
	*/
	private static boolean parseInnerStatement(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_IDENTIFIER)) {
			if (parseAssignmentStatement(stream)) {

			}
		} else if (stream.peek().compareType(TokenType.METHOD_YIELD)) {
			if (parseYieldMethod(stream)) {

			}
		}
	}

	/*
	assignment_statement : IDENTIFIER ASSIGN assignment_value
	*/
	private static boolean parseAssignmentStatement(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_IDENTIFIER)) {
			stream.poll();
			if (stream.peek().compareTyoe(TokenType.ASSIGN)) {
				stream.poll();
				if (parseAssignmentValue()) {

				}
			}
		}
	}

	/*
	assignment_value	:	arithmetic_expression
						|	store_method
	*/
	private static boolean parseAssignmentValue(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.LIT_INT)) {
			if (parseArithmeticExpression(stream)) {

			}
		} else if (stream.peek().compareType(TokenType.METHOD_STORE)) {
			if (parseStoreMethod(stream)) {

			}
		}
	}
	
	//if_statement := KEY_IF conditions KEY_THEN inner_statement SEMICOLON elseif_statements
	private static boolean parseIf(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_IF)) {
			stream.poll();
			if (parseConditions(stream)) {
				if (stream.peek().compareType(TokenType.KEY_THEN)) {
					stream.poll();
					if (parseInnerStatement(stream)) {
						if (stream.peek().compareType(TokenType.SEMICOLON)) {
							stream.poll();
							if (parseElseIf(stream)) {
							}
						}
					}
				}
			}
		}
	}

	private static boolean parseElseIfs(Queue<Token> stream) {
		if (parseElseIf(stream)) {
			if (parseElseIfsPrime(stream)) {
			}
		}
	}

	private static boolean parseElseIfsPrime(Queue<Token> stream) {
		if (parseElseIf(stream)) {
			if (parseElseIfsPrime(stream)) {

			}
		} else {
			//epsilon
		}
	}

	private static boolean parseElseIf(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_ELSEIF)) {
			if (parseLogicalExpressions()) {

			}
		}
	}

	private static boolean parseWhile(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_WHILE)) {
			stream.poll();
			if (parseLogicalExpressions(stream)) {
				if (stream.peek().compareType(TokenType.KEY_DO)) {
					stream.poll();
					if (parseInnerStatement(stream)) {
						if (stream.peek().compareType(TokenType.KEY_SEMICOLON)) {

						}
					}
				}
			}
		}
	}

	private static boolean parseFor(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_FOR)) {
			stream.poll();
			if (parseArithmeticExpression(stream)) {
				if (stream.peek().compareType(TokenType.KEY_TO)) {
					stream.poll();
					if (parseArithmeticExpression(stream)) {
						if (stream.peek().compareType(TokenType.KEY_DO)) {
							stream.poll();
							if (parseInnerStatement(stream)) {
								if (stream.peek().compareType(TokenType.KEY_STEP)) {
									stream.poll();
									if (parseArithmeticExpression(stream)) {
										if (stream.peek().compareType(TokenType.KEY_END)) {
											stream.poll();
											if (stream.peek().compareType(TokenType.SEMICOLON)) {
												stream.poll();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static boolean parseDeclaration(Queue<Token> stream) {
		if (stream.peek().compareType(TokenType.KEY_DECLARE)) {
			stream.poll();
			if (stream.peek().compareType(TokenType.COLON)) {
				stream.poll();
				if (stream.peek().compareType(TokenType.DATATYPE_INTEGER)) {
					stream.poll();
					if (stream.peek().compareType(TokenType.COLON)) {
						stream.poll();
						if (stream.peek().compareType(TokenType.IDENTIFIER)) {
							stream.poll();
							if (stream.peek().compareType(TokenType.ASSIGN)) {
								stream.poll();
								if (parseAssignmentValue(stream)) {
									if (stream.peek().compareType(TokenType.SEMICOLON)) {
										
									}
								}
							}
						}
					}
				}
			}
		}
	}
}