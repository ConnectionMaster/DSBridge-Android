package wendu.jsbdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import wendu.dsbridge.CallbackHandler;
import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DWebView webView= (DWebView) findViewById(R.id.webview);
        webView.setJavascriptInterface(new JsApi());
        webView.clearCache(true);
        webView.loadUrl("file:///android_asset/test.html");
        webView.setJavascriptBridgeInitedListener(new CallbackHandler(){
            @Override
            public void onJsChannelReady() {
                JSONObject args = new JSONObject();
                try {
                    args.put("left", 1).put("right", "hello");
                } catch (JSONException e) {}

                webView.callHandler("addValue", args, new OnReturnValue(){
                    @Override
                    public void onValue(String retValue) {
                        Log.d("jsbridge", "sync callHandler succeed, return value is " + retValue);
                    }
                });

                webView.callHandler("addValueAsync", args, new OnReturnValue(){
                    @Override
                    public void onValue(String retValue) {
                        Log.d("jsbridge", "async callHandler succeed, return value is " + retValue);
                    }
                });
            }
        });

    }
}
