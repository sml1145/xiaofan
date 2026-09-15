package com.xiaofan.bangfan;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: CityCoordDB.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/xiaofan/bangfan/CityCoordDB;", "", "()V", "Companion", "P", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class CityCoordDB {
    public static final Companion Companion = new Companion(null);
    private static final HashMap<String, P> DB = new HashMap<>();

    /* compiled from: CityCoordDB.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/xiaofan/bangfan/CityCoordDB$P;", "", "lat", "", "lon", "display", "", "(DDLjava/lang/String;)V", "getDisplay", "()Ljava/lang/String;", "getLat", "()D", "getLon", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class P {
        private final String display;
        private final double lat;
        private final double lon;

        public P(double lat, double lon, String display) {
            Intrinsics.checkNotNullParameter(display, "display");
            this.lat = lat;
            this.lon = lon;
            this.display = display;
        }

        public final String getDisplay() {
            return this.display;
        }

        public final double getLat() {
            return this.lat;
        }

        public final double getLon() {
            return this.lon;
        }
    }

    private CityCoordDB() {
    }

    /* compiled from: CityCoordDB.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0005J(\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0006\u0010\u0010\u001a\u00020\u0011R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/xiaofan/bangfan/CityCoordDB$Companion;", "", "()V", "DB", "Ljava/util/HashMap;", "", "Lcom/xiaofan/bangfan/CityCoordDB$P;", "Lkotlin/collections/HashMap;", "lookup", "name", "put", "", "lat", "", "lon", "display", "size", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void put(String name, double lat, double lon, String display) {
            CityCoordDB.DB.put(name, new P(lat, lon, display));
        }

        public final P lookup(String name) {
            if (name == null) {
                return null;
            }
            String key = StringsKt.replace$default(StringsKt.trim((CharSequence) name).toString(), " ", "", false, 4, (Object) null);
            if (!(key.length() == 0)) {
                P it = (P) CityCoordDB.DB.get(key);
                if (it != null) {
                    return it;
                }
                String[] suffixes = {"特别行政区", "自治州", "地区", "市", "县", "区", "盟", "旗"};
                for (String suffix : suffixes) {
                    if (StringsKt.endsWith$default(key, suffix, false, 2, (Object) null) && key.length() > suffix.length()) {
                        HashMap hashMap = CityCoordDB.DB;
                        String substring = key.substring(0, key.length() - suffix.length());
                        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                        P it2 = (P) hashMap.get(substring);
                        if (it2 != null) {
                            return it2;
                        }
                    }
                }
                return null;
            }
            return null;
        }

        public final int size() {
            return CityCoordDB.DB.size();
        }
    }

    static {
        Companion.put("北京", 39.9042d, 116.4074d, "北京");
        Companion.put("上海", 31.2304d, 121.4737d, "上海");
        Companion.put("天津", 39.3434d, 117.3616d, "天津");
        Companion.put("重庆", 29.563d, 106.5516d, "重庆");
        Companion.put("广州", 23.1291d, 113.2644d, "广州");
        Companion.put("石家庄", 38.0428d, 114.5149d, "石家庄");
        Companion.put("太原", 37.8706d, 112.5489d, "太原");
        Companion.put("呼和浩特", 40.8424d, 111.749d, "呼和浩特");
        Companion.put("沈阳", 41.8057d, 123.4315d, "沈阳");
        Companion.put("长春", 43.8868d, 125.3245d, "长春");
        Companion.put("哈尔滨", 45.8038d, 126.535d, "哈尔滨");
        Companion.put("南京", 32.0603d, 118.7969d, "南京");
        Companion.put("杭州", 30.2741d, 120.1551d, "杭州");
        Companion.put("合肥", 31.8206d, 117.2272d, "合肥");
        Companion.put("福州", 26.0745d, 119.2965d, "福州");
        Companion.put("南昌", 28.682d, 115.8579d, "南昌");
        Companion.put("济南", 36.6512d, 117.1201d, "济南");
        Companion.put("郑州", 34.7466d, 113.6254d, "郑州");
        Companion.put("武汉", 30.5928d, 114.3055d, "武汉");
        Companion.put("长沙", 28.2282d, 112.9388d, "长沙");
        Companion.put("南宁", 22.817d, 108.3665d, "南宁");
        Companion.put("海口", 20.0174d, 110.3492d, "海口");
        Companion.put("成都", 30.5728d, 104.0668d, "成都");
        Companion.put("贵阳", 26.647d, 106.6302d, "贵阳");
        Companion.put("昆明", 24.8801d, 102.8329d, "昆明");
        Companion.put("拉萨", 29.652d, 91.1721d, "拉萨");
        Companion.put("西安", 34.3416d, 108.9398d, "西安");
        Companion.put("兰州", 36.0611d, 103.8343d, "兰州");
        Companion.put("西宁", 36.6171d, 101.7782d, "西宁");
        Companion.put("银川", 38.4872d, 106.2309d, "银川");
        Companion.put("乌鲁木齐", 43.8256d, 87.6168d, "乌鲁木齐");
        Companion.put("台北", 25.033d, 121.5654d, "台北");
        Companion.put("香港", 22.3193d, 114.1694d, "香港");
        Companion.put("澳门", 22.1987d, 113.5439d, "澳门");
        Companion.put("深圳", 22.5431d, 114.0579d, "深圳");
        Companion.put("厦门", 24.4798d, 118.0894d, "厦门");
        Companion.put("宁波", 29.8683d, 121.544d, "宁波");
        Companion.put("青岛", 36.0671d, 120.3826d, "青岛");
        Companion.put("大连", 38.914d, 121.6147d, "大连");
        Companion.put("泉州", 24.8741d, 118.6757d, "泉州");
        Companion.put("漳州", 24.5128d, 117.6473d, "漳州");
        Companion.put("莆田", 25.4541d, 119.0078d, "莆田");
        Companion.put("三明", 26.2654d, 117.6389d, "三明");
        Companion.put("龙岩", 25.0918d, 117.0297d, "龙岩");
        Companion.put("南平", 26.6418d, 118.1781d, "南平");
        Companion.put("宁德", 26.6617d, 119.5479d, "宁德");
        Companion.put("东莞", 23.0207d, 113.7518d, "东莞");
        Companion.put("佛山", 23.0218d, 113.1218d, "佛山");
        Companion.put("珠海", 22.2707d, 113.5767d, "珠海");
        Companion.put("中山", 22.5174d, 113.3927d, "中山");
        Companion.put("惠州", 23.1115d, 114.4161d, "惠州");
        Companion.put("汕头", 23.3535d, 116.6818d, "汕头");
        Companion.put("江门", 22.579d, 113.0816d, "江门");
        Companion.put("湛江", 21.2707d, 110.3594d, "湛江");
        Companion.put("茂名", 21.663d, 110.9254d, "茂名");
        Companion.put("肇庆", 23.0472d, 112.4654d, "肇庆");
        Companion.put("梅州", 24.2887d, 116.1226d, "梅州");
        Companion.put("汕尾", 22.786d, 115.3751d, "汕尾");
        Companion.put("河源", 23.7463d, 114.6978d, "河源");
        Companion.put("阳江", 21.8579d, 111.9822d, "阳江");
        Companion.put("清远", 23.6818d, 113.056d, "清远");
        Companion.put("韶关", 24.8105d, 113.5975d, "韶关");
        Companion.put("潮州", 23.6569d, 116.6226d, "潮州");
        Companion.put("揭阳", 23.5498d, 116.3727d, "揭阳");
        Companion.put("云浮", 22.915d, 112.0444d, "云浮");
        Companion.put("苏州", 31.299d, 120.5853d, "苏州");
        Companion.put("无锡", 31.4912d, 120.3119d, "无锡");
        Companion.put("常州", 31.8106d, 119.9741d, "常州");
        Companion.put("徐州", 34.2058d, 117.2848d, "徐州");
        Companion.put("南通", 31.9803d, 120.8943d, "南通");
        Companion.put("扬州", 32.3942d, 119.4128d, "扬州");
        Companion.put("镇江", 32.1894d, 119.425d, "镇江");
        Companion.put("泰州", 32.4558d, 119.9232d, "泰州");
        Companion.put("盐城", 33.3495d, 120.1636d, "盐城");
        Companion.put("淮安", 33.55d, 119.015d, "淮安");
        Companion.put("连云港", 34.5967d, 119.2216d, "连云港");
        Companion.put("宿迁", 33.9636d, 118.2754d, "宿迁");
        Companion.put("温州", 27.9938d, 120.6994d, "温州");
        Companion.put("绍兴", 30.0003d, 120.5801d, "绍兴");
        Companion.put("嘉兴", 30.7469d, 120.7555d, "嘉兴");
        Companion.put("湖州", 30.8722d, 120.0867d, "湖州");
        Companion.put("金华", 29.079d, 119.6474d, "金华");
        Companion.put("台州", 28.6563d, 121.4208d, "台州");
        Companion.put("衢州", 28.9354d, 118.8596d, "衢州");
        Companion.put("丽水", 28.4679d, 119.9229d, "丽水");
        Companion.put("舟山", 29.9853d, 122.2068d, "舟山");
        Companion.put("烟台", 37.4638d, 121.4479d, "烟台");
        Companion.put("潍坊", 36.7068d, 119.1619d, "潍坊");
        Companion.put("临沂", 35.1042d, 118.3564d, "临沂");
        Companion.put("淄博", 36.8133d, 118.0548d, "淄博");
        Companion.put("威海", 37.5128d, 122.1207d, "威海");
        Companion.put("济宁", 35.4146d, 116.5871d, "济宁");
        Companion.put("泰安", 36.1944d, 117.0876d, "泰安");
        Companion.put("东营", 37.4346d, 118.6747d, "东营");
        Companion.put("日照", 35.4164d, 119.5269d, "日照");
        Companion.put("德州", 37.4355d, 116.3592d, "德州");
        Companion.put("聊城", 36.4567d, 115.9854d, "聊城");
        Companion.put("滨州", 37.3833d, 117.9711d, "滨州");
        Companion.put("菏泽", 35.2333d, 115.4811d, "菏泽");
        Companion.put("枣庄", 34.8107d, 117.323d, "枣庄");
        Companion.put("洛阳", 34.6197d, 112.454d, "洛阳");
        Companion.put("开封", 34.7971d, 114.3074d, "开封");
        Companion.put("新乡", 35.3027d, 113.9268d, "新乡");
        Companion.put("安阳", 36.0997d, 114.3925d, "安阳");
        Companion.put("焦作", 35.2159d, 113.2418d, "焦作");
        Companion.put("许昌", 34.0357d, 113.8526d, "许昌");
        Companion.put("平顶山", 33.7662d, 113.1842d, "平顶山");
        Companion.put("南阳", 32.9908d, 112.5285d, "南阳");
        Companion.put("商丘", 34.4143d, 115.65d, "商丘");
        Companion.put("信阳", 32.1473d, 114.0913d, "信阳");
        Companion.put("驻马店", 33.0114d, 114.0248d, "驻马店");
        Companion.put("周口", 33.6204d, 114.6497d, "周口");
        Companion.put("漯河", 33.5816d, 114.0168d, "漯河");
        Companion.put("濮阳", 35.7533d, 115.0293d, "濮阳");
        Companion.put("鹤壁", 35.7482d, 114.2975d, "鹤壁");
        Companion.put("三门峡", 34.7736d, 111.2d, "三门峡");
        Companion.put("唐山", 39.6305d, 118.1804d, "唐山");
        Companion.put("保定", 38.8737d, 115.4646d, "保定");
        Companion.put("邯郸", 36.6256d, 114.5391d, "邯郸");
        Companion.put("秦皇岛", 39.9354d, 119.6004d, "秦皇岛");
        Companion.put("沧州", 38.3037d, 116.8388d, "沧州");
        Companion.put("邢台", 37.0682d, 114.5048d, "邢台");
        Companion.put("廊坊", 39.5386d, 116.6837d, "廊坊");
        Companion.put("衡水", 37.739d, 115.6705d, "衡水");
        Companion.put("张家口", 40.8244d, 114.8876d, "张家口");
        Companion.put("承德", 40.9515d, 117.9635d, "承德");
        Companion.put("绵阳", 31.4677d, 104.6796d, "绵阳");
        Companion.put("德阳", 31.1279d, 104.3979d, "德阳");
        Companion.put("宜宾", 28.7513d, 104.6417d, "宜宾");
        Companion.put("南充", 30.8372d, 106.1107d, "南充");
        Companion.put("泸州", 28.8717d, 105.4421d, "泸州");
        Companion.put("达州", 31.2089d, 107.4679d, "达州");
        Companion.put("乐山", 29.5521d, 103.7656d, "乐山");
        Companion.put("内江", 29.58d, 105.058d, "内江");
        Companion.put("自贡", 29.339d, 104.7784d, "自贡");
        Companion.put("遂宁", 30.5333d, 105.5928d, "遂宁");
        Companion.put("眉山", 30.0768d, 103.8485d, "眉山");
        Companion.put("攀枝花", 26.5823d, 101.7186d, "攀枝花");
        Companion.put("宜昌", 30.6919d, 111.2864d, "宜昌");
        Companion.put("襄阳", 32.009d, 112.122d, "襄阳");
        Companion.put("荆州", 30.3348d, 112.2409d, "荆州");
        Companion.put("黄冈", 30.4536d, 114.8724d, "黄冈");
        Companion.put("十堰", 32.6292d, 110.798d, "十堰");
        Companion.put("孝感", 30.9244d, 113.9268d, "孝感");
        Companion.put("荆门", 31.0354d, 112.2043d, "荆门");
        Companion.put("鄂州", 30.3965d, 114.8949d, "鄂州");
        Companion.put("黄石", 30.1996d, 115.0389d, "黄石");
        Companion.put("咸宁", 29.8414d, 114.3225d, "咸宁");
        Companion.put("随州", 31.6902d, 113.3826d, "随州");
        Companion.put("恩施", 30.295d, 109.4884d, "恩施");
        Companion.put("株洲", 27.8274d, 113.134d, "株洲");
        Companion.put("湘潭", 27.8297d, 112.9441d, "湘潭");
        Companion.put("衡阳", 26.8935d, 112.5722d, "衡阳");
        Companion.put("岳阳", 29.3571d, 113.1288d, "岳阳");
        Companion.put("常德", 29.0317d, 111.6986d, "常德");
        Companion.put("邵阳", 27.2389d, 111.4677d, "邵阳");
        Companion.put("益阳", 28.5539d, 112.3552d, "益阳");
        Companion.put("郴州", 25.7705d, 113.0145d, "郴州");
        Companion.put("永州", 26.4203d, 111.6132d, "永州");
        Companion.put("怀化", 27.5501d, 110.0013d, "怀化");
        Companion.put("娄底", 27.6973d, 111.9935d, "娄底");
        Companion.put("张家界", 29.117d, 110.4792d, "张家界");
        Companion.put("芜湖", 31.3526d, 118.4331d, "芜湖");
        Companion.put("蚌埠", 32.9155d, 117.3893d, "蚌埠");
        Companion.put("马鞍山", 31.6705d, 118.507d, "马鞍山");
        Companion.put("淮南", 32.6255d, 116.9999d, "淮南");
        Companion.put("安庆", 30.543d, 117.0633d, "安庆");
        Companion.put("铜陵", 30.9453d, 117.8122d, "铜陵");
        Companion.put("阜阳", 32.8908d, 115.8142d, "阜阳");
        Companion.put("宿州", 33.6464d, 116.9641d, "宿州");
        Companion.put("滁州", 32.3018d, 118.317d, "滁州");
        Companion.put("六安", 31.7349d, 116.5232d, "六安");
        Companion.put("亳州", 33.8456d, 115.7829d, "亳州");
        Companion.put("淮北", 33.9558d, 116.7983d, "淮北");
        Companion.put("黄山", 29.7147d, 118.3375d, "黄山");
        Companion.put("赣州", 25.8311d, 114.9348d, "赣州");
        Companion.put("九江", 29.705d, 116.0019d, "九江");
        Companion.put("上饶", 28.4546d, 117.9434d, "上饶");
        Companion.put("宜春", 27.8137d, 114.4163d, "宜春");
        Companion.put("吉安", 27.1138d, 114.9926d, "吉安");
        Companion.put("抚州", 27.9482d, 116.3581d, "抚州");
        Companion.put("新余", 27.8178d, 114.9171d, "新余");
        Companion.put("萍乡", 27.6228d, 113.8546d, "萍乡");
        Companion.put("景德镇", 29.2687d, 117.1784d, "景德镇");
        Companion.put("鹰潭", 28.2601d, 117.0692d, "鹰潭");
        Companion.put("鞍山", 41.1087d, 122.9956d, "鞍山");
        Companion.put("抚顺", 41.8708d, 123.9572d, "抚顺");
        Companion.put("本溪", 41.2941d, 123.7665d, "本溪");
        Companion.put("丹东", 40.0005d, 124.355d, "丹东");
        Companion.put("锦州", 41.095d, 121.1268d, "锦州");
        Companion.put("营口", 40.6674d, 122.235d, "营口");
        Companion.put("阜新", 42.0216d, 121.6708d, "阜新");
        Companion.put("辽阳", 41.2681d, 123.2371d, "辽阳");
        Companion.put("盘锦", 41.1245d, 122.0707d, "盘锦");
        Companion.put("铁岭", 42.2998d, 123.726d, "铁岭");
        Companion.put("朝阳", 41.5765d, 120.4508d, "朝阳");
        Companion.put("葫芦岛", 40.711d, 120.8369d, "葫芦岛");
        Companion.put("吉林", 43.8378d, 126.5496d, "吉林");
        Companion.put("四平", 43.1668d, 124.3504d, "四平");
        Companion.put("通化", 41.7283d, 125.9397d, "通化");
        Companion.put("松原", 45.1418d, 124.8251d, "松原");
        Companion.put("白城", 45.6196d, 122.839d, "白城");
        Companion.put("辽源", 42.8877d, 125.1435d, "辽源");
        Companion.put("白山", 41.94d, 126.424d, "白山");
        Companion.put("大庆", 46.5891d, 125.1038d, "大庆");
        Companion.put("齐齐哈尔", 47.354d, 123.918d, "齐齐哈尔");
        Companion.put("牡丹江", 44.5516d, 129.608d, "牡丹江");
        Companion.put("佳木斯", 46.7997d, 130.3217d, "佳木斯");
        Companion.put("绥化", 46.6538d, 126.9688d, "绥化");
        Companion.put("鸡西", 45.2951d, 130.9696d, "鸡西");
        Companion.put("双鸭山", 46.6466d, 131.1591d, "双鸭山");
        Companion.put("鹤岗", 47.3499d, 130.2979d, "鹤岗");
        Companion.put("伊春", 47.7275d, 128.8411d, "伊春");
        Companion.put("黑河", 50.2452d, 127.5286d, "黑河");
        Companion.put("宝鸡", 34.3619d, 107.2378d, "宝鸡");
        Companion.put("咸阳", 34.3296d, 108.7089d, "咸阳");
        Companion.put("渭南", 34.4994d, 109.5098d, "渭南");
        Companion.put("榆林", 38.285d, 109.734d, "榆林");
        Companion.put("延安", 36.5853d, 109.4898d, "延安");
        Companion.put("汉中", 33.0676d, 107.0236d, "汉中");
        Companion.put("安康", 32.6847d, 109.0293d, "安康");
        Companion.put("商洛", 33.8683d, 109.9405d, "商洛");
        Companion.put("铜川", 34.8968d, 108.9453d, "铜川");
        Companion.put("大同", 40.0768d, 113.3001d, "大同");
        Companion.put("临汾", 36.088d, 111.519d, "临汾");
        Companion.put("运城", 35.0268d, 111.0068d, "运城");
        Companion.put("晋中", 37.6877d, 112.7527d, "晋中");
        Companion.put("长治", 36.1954d, 113.1163d, "长治");
        Companion.put("晋城", 35.4907d, 112.8515d, "晋城");
        Companion.put("阳泉", 37.8567d, 113.5805d, "阳泉");
        Companion.put("朔州", 39.3316d, 112.4329d, "朔州");
        Companion.put("忻州", 38.4177d, 112.7341d, "忻州");
        Companion.put("吕梁", 37.5244d, 111.1443d, "吕梁");
        Companion.put("柳州", 24.3263d, 109.4158d, "柳州");
        Companion.put("桂林", 25.2742d, 110.299d, "桂林");
        Companion.put("梧州", 23.4749d, 111.2791d, "梧州");
        Companion.put("北海", 21.4813d, 109.1199d, "北海");
        Companion.put("玉林", 22.6545d, 110.1808d, "玉林");
        Companion.put("百色", 23.902d, 106.6184d, "百色");
        Companion.put("贵港", 23.1115d, 109.5989d, "贵港");
        Companion.put("钦州", 21.9797d, 108.654d, "钦州");
        Companion.put("河池", 24.6959d, 108.0853d, "河池");
        Companion.put("防城港", 21.6872d, 108.3538d, "防城港");
        Companion.put("来宾", 23.7504d, 109.2219d, "来宾");
        Companion.put("贺州", 24.4036d, 111.5666d, "贺州");
        Companion.put("曲靖", 25.49d, 103.7961d, "曲靖");
        Companion.put("玉溪", 24.3528d, 102.5428d, "玉溪");
        Companion.put("大理", 25.6065d, 100.2679d, "大理");
        Companion.put("丽江", 26.8721d, 100.2299d, "丽江");
        Companion.put("保山", 25.112d, 99.1618d, "保山");
        Companion.put("昭通", 27.3382d, 103.7175d, "昭通");
        Companion.put("普洱", 22.8251d, 100.9665d, "普洱");
        Companion.put("西双版纳", 22.0074d, 100.7975d, "西双版纳");
        Companion.put("遵义", 27.7254d, 106.9272d, "遵义");
        Companion.put("六盘水", 26.5849d, 104.8307d, "六盘水");
        Companion.put("安顺", 26.2531d, 105.9476d, "安顺");
        Companion.put("毕节", 27.3019d, 105.3048d, "毕节");
        Companion.put("铜仁", 27.7183d, 109.1897d, "铜仁");
        Companion.put("天水", 34.5809d, 105.725d, "天水");
        Companion.put("酒泉", 39.7326d, 98.494d, "酒泉");
        Companion.put("嘉峪关", 39.7731d, 98.2891d, "嘉峪关");
        Companion.put("庆阳", 35.709d, 107.6429d, "庆阳");
        Companion.put("平凉", 35.5428d, 106.6653d, "平凉");
        Companion.put("白银", 36.5448d, 104.1386d, "白银");
        Companion.put("张掖", 38.9258d, 100.4498d, "张掖");
        Companion.put("武威", 37.9283d, 102.6347d, "武威");
        Companion.put("包头", 40.6571d, 109.8403d, "包头");
        Companion.put("鄂尔多斯", 39.6086d, 109.781d, "鄂尔多斯");
        Companion.put("赤峰", 42.2586d, 118.8889d, "赤峰");
        Companion.put("通辽", 43.6525d, 122.2431d, "通辽");
        Companion.put("呼伦贝尔", 49.2122d, 119.7656d, "呼伦贝尔");
        Companion.put("巴彦淖尔", 40.7432d, 107.3877d, "巴彦淖尔");
        Companion.put("乌兰察布", 41.0341d, 113.1326d, "乌兰察布");
        Companion.put("喀什", 39.4704d, 75.9898d, "喀什");
        Companion.put("伊宁", 43.9219d, 81.3242d, "伊宁");
        Companion.put("阿克苏", 41.1674d, 80.2636d, "阿克苏");
        Companion.put("库尔勒", 41.7259d, 86.1746d, "库尔勒");
        Companion.put("克拉玛依", 45.5798d, 84.8892d, "克拉玛依");
        Companion.put("吐鲁番", 42.9513d, 89.1896d, "吐鲁番");
        Companion.put("哈密", 42.8187d, 93.515d, "哈密");
        Companion.put("三亚", 18.2528d, 109.5119d, "三亚");
        Companion.put("儋州", 19.5209d, 109.5807d, "儋州");
        Companion.put("呼市", 40.8424d, 111.749d, "呼和浩特");
        Companion.put("乌市", 43.8256d, 87.6168d, "乌鲁木齐");
        Companion.put("冰城", 45.8038d, 126.535d, "哈尔滨");
        Companion.put("春城", 24.8801d, 102.8329d, "昆明");
        Companion.put("蓉城", 30.5728d, 104.0668d, "成都");
        Companion.put("鹏城", 22.5431d, 114.0579d, "深圳");
        Companion.put("羊城", 23.1291d, 113.2644d, "广州");
        Companion.put("星城", 28.2282d, 112.9388d, "长沙");
        Companion.put("泉城", 36.6512d, 117.1201d, "济南");
    }
}
