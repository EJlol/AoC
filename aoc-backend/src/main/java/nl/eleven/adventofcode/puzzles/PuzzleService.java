package nl.eleven.adventofcode.puzzles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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

	private static final Logger logger = LoggerFactory.getLogger(PuzzleService.class);

	private final String sessionId;

	Path path = Path.of("src", "main", "resources", "static", "input");

	public PuzzleService(@Value("${sessionId}") String sessionId) {
		this.sessionId = sessionId;
	}

	public List<String> read(int year, int day) {
		Path filename = path.resolve("year_" + year + "_day_" + day + ".txt");

		try {
			if (Files.exists(filename)) {
				logger.info("Reading from cache");
				return Files.readAllLines(filename);
			} else {
				String body = retrievePuzzleContentFromUrl(year, day);
				logger.info("Writing file...");
				Files.write(filename, body.getBytes());
				logger.info("Ok!");
				return body.lines().toList();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return new ArrayList<>();
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

	private String retrievePuzzleContentFromUrl(int year, int day) {
		String url = getUrl(year, day);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "session=" + this.sessionId);

		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
			String puzzleContent = response.getBody();

			if (response.getStatusCode().is2xxSuccessful() && puzzleContent != null && checkPathExists()) {
				Path filename = path.resolve("year")
						.resolve(String.valueOf(year))
						.resolve("day")
						.resolve(String.valueOf(day));
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename.toFile()))) {
					writer.write(puzzleContent);
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}

			return puzzleContent;
		} catch (RestClientException e) {
			throw new CouldNotRetrievePuzzleInputException(e.getMessage());
		}
	}
}
