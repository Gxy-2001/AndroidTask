## 创新之处

1. 开场动画
2. 视频播放页面：
   - 缓冲图标
   - 自动循环播放
   - 点击监听：
     - 单击：暂停/播放
     - 双击：点赞+动画
     - 连击：心型动画
3. 视频流页面：
   - 点击视频流中的某个视频可播放对应的视频


## 解决问题

1. 绘图和动画(**本质上就是在熟悉API**)
   1. app icon和开场图：高清图片
   2. 视频播放页面的UI图：
      - [阿里巴巴矢量图库](www.iconfont.cn)
   3. 动画：
      - 使用了ObjectAnimator，执行了包括
        - 旋转/抖动(rotation)
        - 平移(translationX/Y)
        - 缩放(scaleX/Y)
        - 透明(alpha)
        - 加速(`animator.setInterpolator(new AccelerateInterpolator());`)
        - 曲线移动(`animator.setInterpolator(new CycleInterpolator((float) (3 * Math.random())));`)
2. 生命周期：
   - 视频播放页面和视频列表页面的生命周期
   - 直接进入的就是视频播放页面，那如果在视频播放页面按返回键，是否直接退出app
   - 如果在视频列表页点击进入视频播放页，再返回，是直接瑞出app还是返回值播放页
   - 这问题我不知道该咋办，ZSdl带带我
3. 单击、双击、连击如何区分(**本质上就是在熟悉API**)
   - 没有使用：计算两次单机间的时间间隔
   - 搜索得到API：`OnGestureListener`手势监听
     - `onDoubleTap`
     - `onSingleTapConfirmed`

