package lina.lexer;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import lina.lexer.tokenizer.*;

public class Lexer {

	public static void main(String args[]) {
		try {
			List<Line> lines = convertLines(args[0]);
			List<Token> tokens = tokenize(lines);

			for (Token token : tokens) {
				System.out.println(token.toString());
			}

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static List<Token> tokenize(String path) throws IOException {
		return tokenize(convertLines(path));
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
			results.add(new Line(lines.get(i), i + 1));
		}

		return results;
	}

	private static List<Line> convertLines(String path) throws IOException {
		return convertLines(readLines(path));
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