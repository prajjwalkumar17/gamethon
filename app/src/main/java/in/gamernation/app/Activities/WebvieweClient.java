package in.gamernation.app.Activities;

import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;

public class WebvieweClient extends WebViewClient {
    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return false;
    }

    // inject javascript method 'onUrlChange'
    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
    }

    ;

    // if your minSdkVersion is 24 you can only use this
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String url = view.getUrl();
        CommonMethods.LOGthesite(Constants.LOG, "old URL    " + url);
        return false;
    }

    public static class MyJavaScriptInterface {
        @JavascriptInterface
        public void onUrlChange(String url) {
            CommonMethods.LOGthesite(Constants.LOG, "new URL    " + url);
        }
    }


}
