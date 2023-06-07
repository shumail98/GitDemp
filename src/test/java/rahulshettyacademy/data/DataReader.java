package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class DataReader {
	
	@Test
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//to read the file we have some thing in java called file utils 
		
		//here in below step we are reading json to string 
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		//STEP2 : Some External utilities are there which can convert from jsonContent to hashmap 
		//we are now converting string to hasmap we have Jackson Datbind
		
		ObjectMapper mapper = new JsonMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
		//here we are trying to grab 
		//{map,map} from json 
		
	}

}
