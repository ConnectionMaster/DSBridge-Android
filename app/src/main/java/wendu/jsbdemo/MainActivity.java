package wendu.jsbdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

import wendu.dsbridge.CallbackHandler;
import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;
import wendu.dsbridge.VideoEnabledWebChromeClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DWebView webView= (DWebView) findViewById(R.id.webview);
        ViewGroup fullscreenView = (ViewGroup)findViewById(R.id.fullscreen);
        webView.setJavascriptInterface(new JsApi());
        webView.clearCache(true);
        webView.setWebChromeClient(new VideoEnabledWebChromeClient(webView,fullscreenView,null,webView));
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://m.youtube.com");
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
