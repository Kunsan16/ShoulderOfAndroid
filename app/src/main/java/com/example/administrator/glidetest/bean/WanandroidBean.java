package com.example.administrator.glidetest.bean;

import java.util.List;

/**
 * Created by moge on 2018/7/24.
 */

public class WanandroidBean {




    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","author":"尼姑哪里跑","chapterId":398,"chapterName":"速查","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":3175,"link":"https://www.cnblogs.com/anbylau2130/p/6078427.html","niceDate":"4分钟前","origin":"","projectLink":"","publishTime":1532399281000,"superChapterId":398,"superChapterName":"速查知识表","tags":[],"title":"速查 | Java字节码指令","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"developerHaoz","chapterId":261,"chapterName":"屏幕适配","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":3174,"link":"https://www.jianshu.com/p/f93683dcb8b6","niceDate":"11小时前","origin":"","projectLink":"","publishTime":1532356834000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android 刘海屏适配总结","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"HelloHuDi","chapterId":363,"chapterName":"创意汇","collect":false,"courseId":13,"desc":"利用android 5.0 API 提供完整的录制视频库","envelopePic":"http://www.wanandroid.com/blogimgs/23edd4b0-4cb7-4ba5-92e4-78848f77e475.png","fresh":true,"id":3172,"link":"http://www.wanandroid.com/blog/show/2244","niceDate":"13小时前","origin":"","projectLink":"https://github.com/HelloHuDi/ScreenCapture","publishTime":1532352019000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=363"}],"title":"Android 5.0+ 视频录制 ScreenCapture","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"HelloHuDi","chapterId":363,"chapterName":"创意汇","collect":false,"courseId":13,"desc":"提供 android View, ScrollView, HorizontalScrollView, ListView, RecyclerView, WebView 截图功能","envelopePic":"http://www.wanandroid.com/blogimgs/7580d279-e2c6-4626-97b3-c7e245fe42d1.png","fresh":true,"id":3171,"link":"http://www.wanandroid.com/blog/show/2243","niceDate":"13小时前","origin":"","projectLink":"https://github.com/HelloHuDi/ViewCapture","publishTime":1532351513000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=363"}],"title":"Android View截图 ViewCapture","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋公众号","chapterId":329,"chapterName":"Android 8.0","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":3169,"link":"https://mp.weixin.qq.com/s/MhWurQy9oOf9OuDsdBLU-w","niceDate":"17小时前","origin":"","projectLink":"","publishTime":1532336397000,"superChapterId":165,"superChapterName":"5.+高新技术","tags":[],"title":"Android 8.0适配指北","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"小编","chapterId":398,"chapterName":"速查","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3168,"link":"http://www.wanandroid.com/blog/show/2241","niceDate":"1天前","origin":"","projectLink":"","publishTime":1532232348000,"superChapterId":398,"superChapterName":"速查知识表","tags":[],"title":"速查 | Android 构建流程","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Mr.S","chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3167,"link":"https://juejin.im/post/5b50b017f265da0f7b2f649c","niceDate":"1天前","origin":"","projectLink":"","publishTime":1532228167000,"superChapterId":185,"superChapterName":"热门专题","tags":[],"title":"Android 性能优化最佳实践","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"wtus","chapterId":371,"chapterName":"Flutter项目","collect":false,"courseId":13,"desc":"使用Google跨平台框架Flutter仿写一个开眼视频(Eyepetizer )\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/f724de1b-1b67-40e0-ae91-15e067283cd2.png","fresh":false,"id":3166,"link":"http://www.wanandroid.com/blog/show/2240","niceDate":"2天前","origin":"","projectLink":"https://github.com/wtus/flutter_kaiyan","publishTime":1532224638000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=371"}],"title":"Flutter仿写一个开眼视频 flutter_kaiyan","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"firelotus","chapterId":385,"chapterName":"架构","collect":false,"courseId":13,"desc":"这个项目涉及到如下技术的实际应用：1、MVP 2、网络请求（Novate基于rxjava,okhttp,retrofit封装架构）3、DbFlow(可保存文件入SD卡) 4、6.0权限申请 5、XRecyclerView 6、万能Adapter7、异常处理 8、日志打印 9、屏幕适配 10、代码混淆 11、多渠道打包 12、内存泄露检测 13、热修复 14、升级更新 15、极光推送 工程更新完善中","envelopePic":"http://www.wanandroid.com/blogimgs/1458e7e2-2197-4761-b4a4-19d3a43c0d46.png","fresh":false,"id":3163,"link":"http://www.wanandroid.com/blog/show/2237","niceDate":"2天前","origin":"","projectLink":"https://github.com/firelotus/Meteorite","publishTime":1532165149000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=385"}],"title":"基于Android MVP的简单明了的指引性通用架构 Meteorite","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"涤生_Woo","chapterId":320,"chapterName":"内存管理","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3162,"link":"https://juejin.im/post/5b4aed466fb9a04fcb5b5377","niceDate":"2天前","origin":"","projectLink":"","publishTime":1532165123000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"一篇文章带你了解 Java 自动内存管理机制及性能优化","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"wuyr","chapterId":363,"chapterName":"创意汇","collect":false,"courseId":13,"desc":"其实这个效果不一定只能用来做主题切换，还可以用来做其他界面切换的过渡，大家可以发挥一下想象力，做出更多炫酷的动画效果。","envelopePic":"http://www.wanandroid.com/blogimgs/f7691cc5-79e4-4539-bec4-4ea7f373f517.png","fresh":false,"id":3161,"link":"http://www.wanandroid.com/blog/show/2236","niceDate":"2天前","origin":"","projectLink":"https://github.com/wuyr/RippleAnimation","publishTime":1532164494000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=363"}],"title":"自定义View实现炫酷的主题切换动画(仿酷安客户端)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"蒋志碧","chapterId":395,"chapterName":"事件总线","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3160,"link":"https://www.jianshu.com/p/a3cdf5add8bd","niceDate":"2天前","origin":"","projectLink":"","publishTime":1532164428000,"superChapterId":395,"superChapterName":"开源项目源码解析","tags":[],"title":"EventBus 3.0+ 源码详解（史上最详细图文讲解）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Raidriar","chapterId":232,"chapterName":"入门及知识点","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3159,"link":"https://www.jianshu.com/p/016a24ba7a25","niceDate":"2天前","origin":"","projectLink":"","publishTime":1532163893000,"superChapterId":232,"superChapterName":"Kotlin","tags":[],"title":"Kotlin泛型进阶：协变，逆变，以及点变形","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"weizongwei5","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"非常优雅的方案实现屏幕截图，利用android 5.0之后的录屏API实现截屏。\r\n","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":false,"id":3154,"link":"http://www.wanandroid.com/blog/show/2233","niceDate":"2018-07-19","origin":"","projectLink":"https://github.com/weizongwei5/AndroidScreenShot_SysApi","publishTime":1532003901000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"优雅的方案实现屏幕截图  AndroidScreenShot_SysApi","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"SherlockGougou","chapterId":380,"chapterName":"ImageView","collect":false,"courseId":13,"desc":"BigImage ImageView ViewPager 长图、大图查看器，优化内存，支持手势放大、查看原图等","envelopePic":"http://www.wanandroid.com/blogimgs/68a577a7-078b-44b4-8264-d20f7189cead.png","fresh":false,"id":3153,"link":"http://www.wanandroid.com/blog/show/2232","niceDate":"2018-07-19","origin":"","projectLink":"https://github.com/SherlockGougou/BigImageViewPager","publishTime":1532003886000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=380"}],"title":"一个图片浏览器，支持超大图、超长图 BigImageViewPager","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"limuyang2","chapterId":387,"chapterName":"对话框","collect":false,"courseId":13,"desc":"基于DialogFragment封装的库，丰富的属性，帮助你化繁为简\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/64379386-8d43-4916-9862-34abc2fe232a.png","fresh":false,"id":3151,"link":"http://www.wanandroid.com/blog/show/2230","niceDate":"2018-07-19","origin":"","projectLink":"https://github.com/limuyang2/LDialog","publishTime":1532003616000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=387"}],"title":"基于DialogFragment封装的库 LDialog","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"wuyr","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"思路:\r\n我们的SurfaceView里面有一个Rect二维数组, 用来存放这些矩形, 小猪离开手指之后, 就开始从小猪当前所在的矩形,用广度优先遍历, 找到一条最短的路径, 然后再根据这条路径在Rect数组中找到对应的矩形, 最后根据这些对应的矩形的坐标来确定出Path.","envelopePic":"http://www.wanandroid.com/blogimgs/4002c74f-49e5-4526-99a7-571d80d7127a.png","fresh":false,"id":3149,"link":"http://www.wanandroid.com/blog/show/2228","niceDate":"2018-07-18","origin":"","projectLink":"https://github.com/wuyr/CatchPiggy","publishTime":1531890977000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"《捉小猪》是一款基于SurfaceView的Android休闲小游戏.","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"wuyr","chapterId":363,"chapterName":"创意汇","collect":false,"courseId":13,"desc":"可定制性很高的圆弧滑动组件 ","envelopePic":"http://www.wanandroid.com/blogimgs/dcb431a4-5717-4cb8-ad24-10d0a0470fda.png","fresh":false,"id":3148,"link":"http://www.wanandroid.com/blog/show/2227","niceDate":"2018-07-18","origin":"","projectLink":"https://github.com/wuyr/FanLayout","publishTime":1531887503000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=363"}],"title":"FanLayout(风扇布局)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xiaohaibin","chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"【乐享】Kotlin 版本 每天一张精选妹纸图、一个精选短视频，知乎美文的精美应用\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/3c7ca182-cd7e-4372-9cad-8b72f3ca1f83.png","fresh":false,"id":3147,"link":"http://www.wanandroid.com/blog/show/2226","niceDate":"2018-07-17","origin":"","projectLink":"https://github.com/xiaohaibin/KotlinEnjoyLife","publishTime":1531803916000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":" Kotlin 版本 每天一张精选妹纸图、一个精选短视频，知乎美文的精美应用 KotlinEnjoyLife","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"alidili","chapterId":367,"chapterName":"资源聚合类","collect":false,"courseId":13,"desc":"Demo合集","envelopePic":"http://www.wanandroid.com/blogimgs/bf647c17-38e0-4ecd-98ab-fd4e77d1c72c.png","fresh":false,"id":3146,"link":"http://www.wanandroid.com/blog/show/2225","niceDate":"2018-07-17","origin":"","projectLink":"https://github.com/alidili/Demos","publishTime":1531803826000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=367"}],"title":"alidili 的 Demos 合集 ","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 74
         * size : 20
         * total : 1476
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * author : 尼姑哪里跑
             * chapterId : 398
             * chapterName : 速查
             * collect : false
             * courseId : 13
             * desc :
             * envelopePic :
             * fresh : true
             * id : 3175
             * link : https://www.cnblogs.com/anbylau2130/p/6078427.html
             * niceDate : 4分钟前
             * origin :
             * projectLink :
             * publishTime : 1532399281000
             * superChapterId : 398
             * superChapterName : 速查知识表
             * tags : []
             * title : 速查 | Java字节码指令
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private String projectLink;
            private long publishTime;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
