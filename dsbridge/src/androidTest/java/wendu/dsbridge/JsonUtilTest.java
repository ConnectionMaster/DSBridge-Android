package wendu.dsbridge;

import org.json.JSONObject;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by allensun on 1/23/18.
 * on Tubitv.com, allengotstuff@gmail.com
 */
public class JsonUtilTest {
    @Test
    public void isValidJSON() throws Exception {
        boolean result = JsonUtil.isValidJSON(null);
        assertFalse(result);

        //
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","msg");
        result = JsonUtil.isValidJSON(jsonObject.toString());
        assertTrue(result);

        //
        result =  JsonUtil.isValidJSON("");
        assertFalse(result);


        result =  JsonUtil.isValidJSON("{\n" +
                "\t\"age\":100,\n" +
                "\t\"name\":\"mkyong.com\",\n" +
                "\t\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"]\n" +
                "}");
        assertTrue(result);

        result =  JsonUtil.isValidJSON("sdfsadfsadfsdf");
        assertFalse(result);
    }

}