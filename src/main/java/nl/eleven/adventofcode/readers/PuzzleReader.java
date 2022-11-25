package nl.eleven.adventofcode.readers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

public interface PuzzleReader {

	static String getPuzzleContent(String url, String session) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "session=" + session);

		return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
	}

	static Stream<String> read(String url, String session) {
		String body = PuzzleReader.getPuzzleContent(url, session);
		if (body != null) {
			return body.lines();
		} else {
			return Stream.empty();
		}
	}
}
