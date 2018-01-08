//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
/**
 * Created by wangzhixue  on 2017/12/26.
 */
package com.example.zhixue.behaviorcollect.Monitor;

import android.app.Activity;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
/**
 * Created by wangzhixue  on 2017/12/26.
 */
public  class TouchHandle {

	//统计埋点
	private static String mClassName = TouchHandle.class.getSimpleName();
	private ArrayList<OnViewClickListener> mListeners;
	public TouchHandle(){
	}

    public  void eventViewHit(Activity activity, MotionEvent event) {

		ViewGroup rootView = ViewUtils.getRootFrame(activity);
		if (event.getAction() == MotionEvent.ACTION_UP){
			//点击的view
			View view = getTargetView(rootView,event);
			if (null !=view && mListeners != null){
				final ArrayList<OnViewClickListener> list = mListeners;
				final int count = list.size();
				for (int i = 0; i < count; i++) {
					list.get(i).onButtonTouch(event,view);
				}
			}
		}
    }
	/*获取所有View和没有子View的ViewGroup*/
	public  int countView(ViewGroup viewGroup) {
		if (viewGroup == null) return 0;

		int sum = 0;
		int count = viewGroup.getChildCount();
		for (int i = 0; i < count; i++) {
			View view = viewGroup.getChildAt(i);
			if(!view.isShown()){
				continue;
			}
			String idName = ViewUtils.getSimpleResourceName(view.getContext(), view.getId());
			if (view instanceof ViewGroup) {
				sum += countView((ViewGroup)view)+1;
			}else {
				sum +=1;
			}
		}
		return sum;
	}

    /*获取所有View和没有子View的ViewGroup*/
    public View getTargetView(ViewGroup viewGroup, MotionEvent event) {
        if (viewGroup == null  ) return null;
		int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
			//view.isShown()  view在上层才获取其id   针对多个fragment切换情况
			if (!view.isShown()) continue;
			if (isContainView(view ,event)) {
				if (isTargetView(view)){
					return view;
				}else if (view instanceof ViewGroup){
					View targetView  = getTargetView((ViewGroup) view,event);
					if (null != targetView) return targetView ;
				}else {
					continue;
				}
            }
        }
        return null;
    }


    public boolean isTargetView(View view){
		return view.hasOnClickListeners();
    }

	public boolean isContainView(View view , MotionEvent event){
		double x = event.getRawX();
		double y = event.getRawY();
		Rect outRect = new Rect();
		view.getGlobalVisibleRect(outRect);
		return outRect.contains((int) x, (int) y);
	}

	public interface OnViewClickListener {
		/*
     * @param event   点击事件
     * @param view   定位的view
       */
		void onButtonTouch(MotionEvent event, View view);
	}

	public void registerViewClickListener(OnViewClickListener watcher) {
		if (mListeners == null) {
			mListeners = new ArrayList<OnViewClickListener>();
		}
		mListeners.add(watcher);
	}

	public void removeViewClickListener(OnViewClickListener watcher) {
		if (mListeners != null) {
			int i = mListeners.indexOf(watcher);
			if (i >= 0) {
				mListeners.remove(i);
			}
		}
	}

}
