//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.zhixue.behaviorcollect.Monitor;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by wangzhixue  on 2017/12/26.
 */
public  class Monitor {

	private static final String TAG = Monitor.class.getName();

	public  static boolean collectMode = true;
	public  static boolean sShowLog = true;
	private static AtomicBoolean sStart = new AtomicBoolean(false);

	//当前fragment是否为显示界面
	private  boolean isFragmentStart = false;
	private ClickHandle mClickHandle;
	private String sLastPageName = "" ;   //上一页面名字
	private String sCurrentPageName = "" ;   //当前页面名字

	public  static boolean isCollectMode() {
		return collectMode;
	}
	public  static void setCollectMode(boolean collect) {
		collectMode = collect;
	}

	private Monitor() {
		mClickHandle = new ClickHandle();
		mClickHandle.registerViewClickListener(myViewClickListener);
	}
	public static Monitor getInstance() {
		return MonitorHolder.holder;
	}

	private static class MonitorHolder {
		private static Monitor holder = new Monitor();
		private MonitorHolder() {
		}
	}

	private void onActivityPageStart(Activity activity) {
		if (!collectMode)   return;  //非收集模式  退出
		try {
			sCurrentPageName = activity.getClass().getSimpleName();
			onAppStart();  //应用启动时上传
			onPageStart(activity);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void onActivityPageEnd() {
		sLastPageName = sCurrentPageName ;
	}

	public  void onFragmentPageStart(Fragment fragment) {
		//如果当前fragment已经开始 则退出   避免重复开始
		if (!fragment.getUserVisibleHint()||fragment.isHidden()||!fragment.isResumed()||isFragmentStart)return;
		isFragmentStart = true;
		try {
			String pageName =fragment.getClass().getSimpleName();
//			LogUtils.d("collect" , fragment.getClass().getSimpleName()+"-------------------------mFragmentViews="+mClickHandle.mFragmentViews.size()+",mAllViews= "+mClickHandle.mAllViews.size());
			sCurrentPageName = pageName;
			onPageStart(fragment);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public  void onFragmentPageEnd(Fragment fragment) {
		///如果当前fragment已经结束 则退出   避免重复结束
		if (!isFragmentStart)   return;  //非收集模式  退出
		isFragmentStart =false;
		sLastPageName = sCurrentPageName ;
	}


	public  void onActivityCreated(Activity activity){
		if (!collectMode)   return;  //非收集模式  退出
	}

	public  void onActivityResumed(Activity activity) {
		if (!collectMode)   return;  //非收集模式  退出
		onActivityPageStart(activity);
	}

	public  void onActivityPaused(Activity activity) {
		if (!collectMode)   return;  //非收集模式  退出
		onActivityPageEnd();
	}

	public  void dispatchTouchEvent(Activity activity, MotionEvent ev) {
		if (!collectMode)   return;  //非收集模式  退出
        /*获取当前点击位置，遍历布局，获取当前点击位置对应的view，根据view映射路径，与json文件中的对比*/
		if (mClickHandle != null){
			mClickHandle.eventViewHit(activity,ev);
		}
	}

	public void onFragmentResume(Fragment fragment) {
		if (!collectMode)   return;  //非收集模式  退出
		onFragmentPageStart(fragment);
			if (sShowLog) Log.d("collect" , fragment.getClass().getSimpleName()+"-------------------------onFragmentResume()");

	}

	public void onFragmentPaused(Fragment fragment) {
		if (!collectMode)   return;  //非收集模式  退出
		onFragmentPageEnd(fragment);
//		if (sShowLog) Log.d("collect" , fragment.getClass().getSimpleName()+"-------------------------onFragmentPaused()");
	}

	public void onFragmentHiddenChanged(Fragment fragment, boolean hidden) {
		if (!collectMode)   return;  //非收集模式  退出
//		if (sShowLog) Log.d("collect" , fragment.getClass().getSimpleName()+"-------------------------onFragmentHiddenChanged() hidden = "+hidden);
		if (!fragment.isResumed()) return;   //未初始化  退出
		if (hidden) {
			onFragmentPageEnd(fragment);
		} else {
			onFragmentPageStart(fragment);
		}
	}

	public void  setUserVisibleHint(Fragment fragment, boolean isVisibleToUser) {
		if (!collectMode)   return;  //非收集模式  退出
//		if (sShowLog)Log.d("collect" , fragment.getClass().getSimpleName()+"-------------------------setUserVisibleHint isVisibleToUser = "+isVisibleToUser);
		if (!fragment.isResumed()) return;   //未初始化  退出
		if (isVisibleToUser) {
			onFragmentPageStart(fragment);
		} else {
			onFragmentPageEnd(fragment);
		}
	}

	 ClickHandle.OnViewClickListener myViewClickListener = new ClickHandle.OnViewClickListener(){

		@Override
		public void onButtonTouch(MotionEvent event, View view){

			try {
				if (event.getAction() == MotionEvent.ACTION_UP ){
					String viewName = "";
					String viewType = "";
					String idName = ViewUtils.getSimpleResourceName(view.getContext(), view.getId());
//					if (sShowLog) Log.d("collect", "研究院数据上传成功_____id= "+idName);
					//获取文本值
					viewName =getButtonName(view );
//					if (sShowLog) Log.d("collect", "研究院数据上传成功____________________viewName= "+viewName);
					//动态生成view  比如listView
					if (view.hasOnClickListeners() && TextUtils.isEmpty(idName)){
						idName = viewName;
					}
					viewType = (view instanceof EditText) ? "text": "button";

					onClickButton(idName,viewName,viewType);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	};

	private static String getButtonName(View view ){
		String viewName = "";
		//获取文本值
		if (view instanceof TextView){
			viewName = ((TextView) view).getText().toString();
		}else if (view instanceof ViewGroup){
			ViewGroup viewGroup = (ViewGroup)view;
			int count = viewGroup.getChildCount();
			for (int i = 0; i < count; i++) {
				View itemView = viewGroup.getChildAt(i);
				String id = ViewUtils.getSimpleResourceName(itemView.getContext(), itemView.getId());
				if (itemView instanceof TextView){
					viewName = ((TextView) itemView).getText().toString();
					break;
				}else if (itemView instanceof ViewGroup){
					String name =getButtonName(itemView);
					if (null != name){
						return name;
					}
				}
			}
		}
		return viewName;
	}

	/**
	 * 生成上传数据行为数据
	 */
	public void  onPageStartInner() {
		if (sShowLog) Log.d("collect" , "uploadStr =： ");
	}

	/*
	*点击按钮时上传的数据
	 */
	public void onClickButton(String idName, String viewName, String viewType) {
		if (sShowLog) Log.d("collect" , "----view被点击 idName = "+idName+"，viewName = "+viewName+"，viewType = "+viewType);
	}

	/*
	获取带日期的时间
	 */
	public String getDateWithTime() {
		SimpleDateFormat formatterTime    =   new SimpleDateFormat("yyyy/MM/dd hh:mm");
		Date curDateTime    =   new Date(System.currentTimeMillis());//获取当前时间
		return  formatterTime.format(curDateTime);
	}
	/*
	获取格式化日期
	 */
	public String getFormatDate() {
		SimpleDateFormat formatter    =   new SimpleDateFormat("yyyyMMdd");
		Date curDate    =   new Date(System.currentTimeMillis());//获取当前时间
		return  formatter.format(curDate);
	}

	/*
	*打开app时上传的数据
	 */
	public void onAppStart() {
		if (!sStart.compareAndSet(false, true)) {
			return;
		}
		if (sShowLog) Log.d("collect" , "----app被打开了 "+getFormatDate());
//		Toast.makeText("----app被打开了 "+getFormatDate(),3).show();
	}

	/*
	*打开页面上传的数据
	 */
	public  void onPageStart(Object object) {

		if (sShowLog) Log.d("collect" , "----打开了新页面 "+object.getClass().getSimpleName()+"，上一页面="+sLastPageName);
	}
}
