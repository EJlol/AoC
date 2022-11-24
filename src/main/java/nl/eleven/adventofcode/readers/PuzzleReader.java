package nl.eleven.adventofcode.readers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public interface PuzzleReader {

	static String getPuzzleContent(String url) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "session=53616c7465645f5f72378ff1d4d376c4d5dbc709d0eb6c0889b1ffe277795ab37a613ac8552511b5ef1630bb1935fbf94936ef54f2ceafd63ce156bfa8d9209c");

		return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
	}
}
