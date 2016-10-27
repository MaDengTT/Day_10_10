package com.example.administrator.day_10_10.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * scrollview 套用listview时listview显示最大高度
 * @author PC-001
 *
 */
public class MyScrollListView extends ListView {

	
	public MyScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyScrollListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, mExpandSpec);
	}
	
//	@Override  
//    public boolean dispatchTouchEvent(MotionEvent ev) {  
//        if(ev.getAction() == MotionEvent.ACTION_MOVE){     
//            return true;   
//        }   
//        return super.dispatchTouchEvent(ev);  
//    } 
	
}
