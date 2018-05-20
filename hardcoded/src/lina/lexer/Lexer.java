package lina.lexer;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import lina.lexer.tokenizer.Line;

public class Lexer {

	

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