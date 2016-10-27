package com.example.administrator.day_10_10.view;


import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView;

import com.example.administrator.day_10_10.R;

public class McoyProductContentPage implements McoySnapPageLayout.McoySnapPage {


	private Context context;

	private View rootView = null;
	private WebView webView = null;

	public McoyProductContentPage(Context context, View rootView) {
		this.context = context;
		this.rootView = rootView;
		webView = (WebView) this.rootView.findViewById(R.id.web_view);
		
	}

	@Override
	public View getRootView() {
		return rootView;
	}

	@Override
	public boolean isAtTop() {
		return webView.getScrollY() <= 0;
	}

	@Override
	public boolean isAtBottom() {
		return false;
	}
	
	
	
	
	

	
	
	
}
