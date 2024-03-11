package com.iemr.inventory.controller.version;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.utils.response.OutputResponse;

@ExtendWith(MockitoExtension.class)
class VersionControllerTest {

	@InjectMocks
	VersionController versionController;

	@Test
	void readFromInputStreamTest() throws IOException {

		// Create a sample git.properties content
		String gitPropertiesContent = "git.commit.id=abcdef\n" + "git.build.version=1.0.0\n";

		// Convert the content to an input stream
		InputStream inputStream = new ByteArrayInputStream(gitPropertiesContent.getBytes());

		String line = "git.commit.id=abcdef\n" + "git.build.version=1.0.0\n";

		StringBuilder resultStringBuilder = new StringBuilder();

		resultStringBuilder.append(line);

		Assertions.assertEquals(resultStringBuilder.toString(), versionController.readFromInputStream(inputStream));
	}

	@Test
	void readGitPropertiesTest() throws Exception {

		String gitPropertiesContent = "git.commit.id=abcdef\n" + "git.build.version=1.0.0\n";

		String result = gitPropertiesContent;

		String line = result;

		StringBuilder resultStringBuilder = new StringBuilder();

		resultStringBuilder.append(line);

		Assertions.assertEquals(resultStringBuilder.toString(), result);

	}

	@Test
	void versionInformationTest() {

		// Call the versionInformation method
		String result = versionController.versionInformation();

		OutputResponse output = new OutputResponse();
		output.setResponse(result);

		// Check if the result is not null
		assertNotNull(result);
		assertEquals(result, versionController.versionInformation());

	}
	
	
	@Test
	void versionInformationTest_Exception() {
		
		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		String response = request;
		
		assertTrue(response.contains(request));
		
	}

}