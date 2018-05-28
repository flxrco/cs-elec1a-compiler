package lina.lexer;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import lina.lexer.tokenizer.*;

public class Lexer {

	public static void main(String args[])  {
		try {
			TokenStream stream = analyze(args[0]);

			System.out.println(stream.linesToString());
			System.out.println(stream.tokensToString());
			
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} 

	}

	public static TokenStream analyze(String path) throws IOException {
		return analyze(readLines(path));
	}

	private static TokenStream analyze(List<String> stringLines) {
		List<Line> lines = convertLines(stringLines);
		return new TokenStream(tokenize(lines), stringLines);
	}

	private static List<Token> tokenize(List<Line> lines) {
		List<Token> tokens = new ArrayList<>();

		for (Line line : lines) {
			Token cache = null;
			while ((cache = Tokenizer.nextToken(line)) != null) {
				tokens.add(cache);
			}
		}

		return tokens;
	}

	private static List<Line> convertLines(List<String> lines) {
		List<Line> results = new ArrayList<>();

		for (int i = 0; i < lines.size(); i++) {
			results.add(new Line(lines.get(i), i));
		}

		return results;
	}

	private static List<String> readLines(String path) throws IOException {
		List<String> lines = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String buffer = null;
			while ((buffer = br.readLine()) != null) {
				lines.add(buffer);
			}
		}

		return lines;
	}
}