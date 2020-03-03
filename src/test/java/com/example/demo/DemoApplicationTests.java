package com.example.demo;

import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@NoArgsConstructor
public class DemoApplicationTests {

	private LinkedMultiValueMap<String,String> multiValueMap;

	@Before
	public void setupMultiMap() {
		multiValueMap = new LinkedMultiValueMap<>();
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void add() {
		multiValueMap.add("fruit","apple");
		multiValueMap.add("fruit","banana");
		multiValueMap.add("fruit","orange");
		assert multiValueMap.size()==1;
		List<String> expected = new ArrayList<>();
		expected.add("apple");
		expected.add("banana");
		expected.add("orange");
		assert expected.equals(multiValueMap.get("fruit"));
	}

}
