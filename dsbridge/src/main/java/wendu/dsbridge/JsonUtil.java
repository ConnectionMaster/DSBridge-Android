package wendu.dsbridge;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by allensun on 1/23/18.
 * on Tubitv.com, allengotstuff@gmail.com
 */
public class JsonUtil {

    private static final String ERROR = "error";

    public static boolean isValidJSON(@Nullable String toTestStr) {
        if (toTestStr == null) {
            return false;
        }

        try {
            new JSONObject(toTestStr);
        } catch (JSONException jsExcp) {
            try {
                new JSONArray(toTestStr);
            } catch (JSONException jsExcp1) {
                return false;
            }
        }
        return true;
    }

    public static JSONObject buildErrorMessage(String message) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(ERROR, message);
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                jsonObject.put(ERROR, e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        return jsonObject;
    }
}
