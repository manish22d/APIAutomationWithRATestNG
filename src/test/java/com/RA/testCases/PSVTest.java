package com.RA.testCases;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RA.testBase.BaseClass;
import com.RA.util.FTPUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class PSVTest extends BaseClass{
	CSVReader csvReader ;
	List<String[]> allPSVData;
	
	@BeforeClass
	public void setUpFiles() throws IOException, URISyntaxException {
		Reader reader = Files
				.newBufferedReader(Paths.get(ClassLoader.getSystemResource("psv/test.psv").toURI()));
		CSVParser parser = new CSVParserBuilder().withSeparator('|').withIgnoreQuotations(true).build();

		csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(parser).build();
		
		allPSVData = csvReader.readAll();
		
		
	}
	
	@Test
	public void verifyHeaderInPSV(Method method) {
		extentTest = extent.startTest(method.getName());
		List<String> headerInPSV = Arrays.asList(allPSVData.get(0));
		System.out.println(headerInPSV);

		JsonObject expectedHeaders = JsonParser.parseString(file.readJson("psv.json")).getAsJsonObject()
				.getAsJsonObject("fileConfigModelsMap").getAsJsonObject("vendor").getAsJsonObject("fieldsConfig");
		List<String> headers = expectedHeaders.entrySet().stream()
				.map(header -> header.getValue().getAsJsonObject().get("targetFieldName").getAsString())
				.collect(Collectors.toList());
		System.out.println(headers);
		System.out.println(headerInPSV.containsAll(headers));
		assertThat("",headerInPSV.containsAll(headers));
	}
	
	@Test
	public void validateData(Method method) {
		extentTest = extent.startTest(method.getName());
		
		List<String> data = Arrays.asList(allPSVData.get(1));
		data.forEach(System.out::println);
		
	}
	
	@Test
	public void testFTP(Method method) throws IOException {
		extentTest = extent.startTest(method.getName());
		
		FTPUtils ftp = new FTPUtils();
		ftp.connect();
		
		System.out.println(ftp.listDir("/download"));
		BufferedReader br = new BufferedReader(new InputStreamReader(ftp.readFile("/download/version.txt")));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    System.out.println(everything);
		} finally {
		    br.close();
		}
//		ftp.changeDirectory("/download");
//		System.out.println(ftp.listDir("/"));
		ftp.close();
		
	}
	
	

}
