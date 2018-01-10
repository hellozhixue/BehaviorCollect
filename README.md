# BehaviorCollect
BehaviorCollect是自动化埋点统计用sdk,只需几行代码就可以实现整个应用的行为统计。ps: 如果不考虑Fragment的页面切换问题，则只需一行代码就可以。

接入方式：
1.将sdk文件夹复制到你的工程目录下
2. 不将Fragment按照界面处理时只需在你的Application中的OnCreate中加入如下即可：
 if(Build.VERSION.SDK_INT > 14) {  //埋点统计回调监听
            this.registerActivityLifecycleCallbacks(new MonitorActivityLifecycleCallbacks());
        }

3.如果将Fragment按照界面处理时，在你的BaseFragment中（本例为BaseViewFragment）按照例子更改那四个回调函数。
4.在Result类中加入你触发事件的处理逻辑。


说明：
1.本例只加了开机、开启页面、按钮点击这三种事件类型，根据项目需求，可自行添加如结束页面 页面停留时间等逻辑。
2.本例只处理了点击事件中的up事件，如需抓取down事件，可自行修改dispatch中Motion响应事件。
	
