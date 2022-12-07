package nl.eleven.adventofcode.puzzles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class PuzzleService {

	private final String sessionId;

	Path path = Path.of("resources", "static", "input");

	public PuzzleService(@Value("${sessionId}") String sessionId) {
		this.sessionId = sessionId;
	}

	public List<String> read(int year, int day) {
		Path filename = path.resolve("year_" + year + "_day_" + day + ".txt");

		try {
			if (Files.exists(filename)) {
				System.out.println("Reading from cache");
				return Files.readAllLines(filename);
			} else {
				String body = retrievePuzzleContentFromUrl(year, day);
				System.out.println("Writing file...");
				Files.write(filename, body.getBytes());
				System.out.println("Ok!");
				return body.lines().toList();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return new ArrayList<>();
	}

	public String retrievePuzzleContentFromUrl(int year, int day) {
		String url = getUrl(year, day);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "session=" + this.sessionId);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
		String puzzleContent = response.getBody();

		if (response.getStatusCode().is2xxSuccessful() && puzzleContent != null && checkPathExists()) {
			Path filename = path.resolve("year")
					.resolve(String.valueOf(year))
					.resolve("day")
					.resolve(String.valueOf(day));
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename.toFile()))) {
				writer.write(puzzleContent);
			} catch (IOException ignored) {
			}
		}

		return puzzleContent;
	}

	private boolean checkPathExists() {
		if (!Files.exists(path)) {
			return path.toFile().mkdirs();
		}
		return true;
	}

	private String getUrl(int year, int day) {
		return "https://adventofcode.com/" + year + "/day/" + day + "/input";
	}
}
