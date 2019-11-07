# docket_setting
#  需求

![](https://user-gold-cdn.xitu.io/2019/11/7/16e44924ed49dd45?w=914&h=876&f=png&s=166795)
写一个可以动态设置展示小票内容的页面。
# 需求分析``（注意只是小票展示和设置，不包括小票的打印）``
``打印这块有需求，可以联系我，我后面再加上，主要是我太懒了,haha``  
需求并不难，但是实现起来却相当花时间：（[点我白拿](https://github.com/drchengit/docket_setting)）  
* 第一点，锯齿带阴影

![](https://user-gold-cdn.xitu.io/2019/11/7/16e44bb65db5ae48?w=296&h=129&f=png&s=13430)
* 第二点，票据的宽度切换

![](https://user-gold-cdn.xitu.io/2019/11/7/16e44ce4a4a4c2be?w=963&h=543&f=gif&s=152683)

* 第三点，两个布局，都可以滑动，可以动态同步。
 
![](https://user-gold-cdn.xitu.io/2019/11/7/16e44ce89edc47e7?w=963&h=543&f=gif&s=811403) 

![](https://user-gold-cdn.xitu.io/2019/11/7/16e44ceb2285b186?w=963&h=543&f=gif&s=879075)

[白拿点我](https://github.com/drchengit/docket_setting)

# 难点攻克
## 锯齿带阴影   
 想法：
 
    //使用android 自定义View 绘制阴影的api
    paint2.setShadowLayer(effect, offset_x, offset_y, Color.parseColor("#33000000"));
    //注意关闭硬件加速
      setLayerType(LAYER_TYPE_SOFTWARE, null);
 用一个路径圈出一个上下带锯齿、左右是直线的path,画笔上设置阴影，并drawPath（）就可以实现锯齿带阴影。
 
## 宽度切换
直接设置控件宽度并刷。  
宽度变化的同时，控件内容的padding 也是一起改变的，滑动控件中内容的布局要采用wight（比重）来布局，这一点需要讲究。

        /*切换不同宽度模式*/
    public void changeWithMode(boolean isFiveEt){
       ViewGroup.LayoutParams params = this.getLayoutParams();
       params.width = isFiveEt?ArmUtils.dip2px(this.getContext(),290)+effect*2:ArmUtils.dip2px(this.getContext(),400)+effect*2;
       this.setLayoutParams(params);
      if(isFiveEt){
          setPadding(effect+ArmUtils.dip2px(this.getContext(),16), ArmUtils.dip2px(this.getContext(),23), effect+ArmUtils.dip2px(this.getContext(),16), ArmUtils.dip2px(this.getContext(),32));
      }else{
          setPadding(effect+ArmUtils.dip2px(this.getContext(),24), ArmUtils.dip2px(this.getContext(),23), effect+ArmUtils.dip2px(this.getContext(),24), ArmUtils.dip2px(this.getContext(),32));
      }
      invalidate();
    }
## 布局滑动并可以动态变化
布局分为**展示的滑动区域** 和**设置的滑动区域**    
区域分： **头部**、**上**、**中**、**下**、**尾部**  
每部分的数据长度不定所以采用recyclerView 公用一个数据源来控，每部数据改变，对面区域的同部位的布局刷新。     
区域可滑动，所以每区域使用**区域ReyclerView**嵌套**部位Reyclerview**封装度和刷新方面要自由一些。考虑到高效采用局部刷新。  
 项目： https://github.com/drchengit/docket_setting 
 
 # 结尾
 
 感谢分享： https://www.jianshu.com/p/87f738905e7f  
 项目地址： https://github.com/drchengit/docket_setting 
 
 我是drchen，一个温润的男子，版权所有，未经允许不得抄袭。
