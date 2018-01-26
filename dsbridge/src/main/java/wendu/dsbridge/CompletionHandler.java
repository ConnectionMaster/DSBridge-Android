package wendu.dsbridge;

import org.json.JSONObject;

/**
 * Created by du on 16/12/31.
 */

public interface  CompletionHandler {
    void complete(String value);
    void complete();
    void setProgressData(String value);
}
