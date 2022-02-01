package yt.DeepHost.Youtube_Embed_Player;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import yt.DeepHost.Youtube_Embed_Player.Layout.activity_view;
import yt.DeepHost.Youtube_Embed_Player.library.Hide_Element;
import yt.DeepHost.Youtube_Embed_Player.library.Video;
import yt.DeepHost.Youtube_Embed_Player.library.VideoChromeClient;
import yt.DeepHost.Youtube_Embed_Player.library.VideoClient;
import yt.DeepHost.Youtube_Embed_Player.library.VideoListener;

@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION, description = "<p>DeepHost - Youtube Embed Player Extension <br><br> <a href = \"https://www.youtube.com/c/DeepHost\" target = \"_blank\">Youtube Channel Link</a> </a> <br><br></p>", iconName = "aiwebres/icon.png", nonVisible = true, version = 5)
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCESS_NETWORK_STATE")
public class Youtube_Embed_Player extends AndroidNonvisibleComponent implements Component, VideoListener {
    public static final int VERSION = 1;
    public String Video_ID = "";
    public Activity activity;
    public boolean autoplay = true;
    public ComponentContainer container;
    public Context context;
    public boolean isRepl = false;
    public ProgressBar progressBar;
    public WebView webView;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Youtube_Embed_Player(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.isRepl = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.Video_ID = r3
            r2 = r0
            r3 = 1
            r2.autoplay = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x003a
            r2 = r0
            r3 = 1
            r2.isRepl = r3
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: yt.DeepHost.Youtube_Embed_Player.Youtube_Embed_Player.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty
    public void AutoPlay(boolean id) {
        boolean z = id;
        this.autoplay = z;
    }

    @SimpleProperty
    public void Video_ID(String video_id) {
        String str = video_id;
        this.Video_ID = str;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @SimpleFunction
    public void Youtube_Embed_Player(AndroidViewComponent component) {
        View view;
        Video video;
        Object obj;
        Object obj2;
        new activity_view.layout(this.context);
        View v = view;
        this.webView = (WebView) v.findViewWithTag("webView");
        this.progressBar = (ProgressBar) v.findViewWithTag("progressBar");
        this.webView.getSettings().setLoadWithOverviewMode(true);
        this.webView.getSettings().setUseWideViewPort(true);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setAllowContentAccess(false);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.webView.getSettings().setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5");
        new Video();
        this.webView.loadUrl(video.url(this.Video_ID));
        Object obj3 = obj;
        new VideoClient(this.webView, this.autoplay, this);
        Object obj4 = obj2;
        new VideoChromeClient(this.activity, this.context, this.webView, this);
        ViewGroup viewGroup = (ViewGroup) component.getView();
        viewGroup.removeAllViews();
        viewGroup.addView(v);
    }

    @SimpleFunction
    public void onPause() {
        if (this.webView != null) {
            this.webView.loadUrl("javascript:(function() { document.querySelector('button[title=\"Pause (k)\"]').click(); })()");
        }
    }

    @SimpleFunction
    public void onResume() {
        if (this.webView != null) {
            this.webView.loadUrl("javascript:(function() { document.querySelector('button[title=\"Play (k)\"]').click(); })()");
        }
    }

    @SimpleFunction
    public void Stop_Playing() {
        if (this.webView != null) {
            this.webView.loadUrl("");
        }
    }

    @SimpleEvent
    public void onFullScreen() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "onFullScreen", new Object[0]);
    }

    @SimpleEvent
    public void onExitFullScreen() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "onExitFullScreen", new Object[0]);
    }

    @SimpleEvent
    public void onPlaying(String current, String duration) {
        Object[] objArr = new Object[2];
        objArr[0] = current;
        Object[] objArr2 = objArr;
        objArr2[1] = duration;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "onPlaying", objArr2);
    }

    public void OnFullScreen() {
        Object obj;
        Object obj2 = obj;
        new Hide_Element(this.webView);
        onFullScreen();
    }

    public void OnExitFullScreen() {
        Object obj;
        Object obj2 = obj;
        new Hide_Element(this.webView);
        onExitFullScreen();
    }

    public void OnLoading() {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(0);
        }
    }

    public void OnLoaded() {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }
    }

    public void OnPlaying(String current, String duration) {
        removechild(this.webView, "ytp-popup ytp-contextmenu");
        onPlaying(current, duration);
    }

    public void removechild(WebView webView2, String str) {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("javascript:(function() {  var elements = document.getElementsByClassName('");
        StringBuilder append2 = sb2.append(str);
        StringBuilder append3 = sb2.append("'); while(elements.length > 0)elements[0].parentNode.removeChild(elements[0]);  })()");
        webView2.loadUrl(sb2.toString());
    }
}
