package automationUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
public class UserCredentials {

    public static Map<String, String> getRandomUser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String>[] users = mapper.readValue(new File("/Users/yonix/IdeaProjects/PerceptosAssignment/src/test/java/users.json"), Map[].class);
        return users[(int) (Math.random() * users.length)];
    }
}
