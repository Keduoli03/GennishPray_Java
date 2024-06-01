import java.util.ArrayList;
import java.util.LinkedList;

public class Pray_Probability {
    public static String UP_Five="珊瑚宫心海";
    public static String UP_Four1="行秋";
    public static String UP_Four2="烟绯";
    public static String UP_Four3="绮良良";
    //粉球数量
    public static int PinkBall_Number=60;
    //原石数量
    public static int Primogems=0;
    public static int four_star_least=0;
    //抽卡次数
    //总数
    public static int Pray_All=0;
    //单次五星次数
    public static int Pray_Frequency=0;
    //抽卡概率
    //五星前73抽概率为线性
    public static final double five_PP=0.0048*Pray_Frequency+0.0141;
    //后面直到第90抽近似为以下公式
    public static final double five_PP2=-0.0046*Pray_Frequency*Pray_Frequency + 0.1172*Pray_Frequency+ 0.2849;
    //四星前8抽为固定5.1%
    public static final double four_PP=0.051;
    //判断目前是否是大保底
    public static boolean isBBD=false;
    //判断是不是up的四星
    public static boolean isUPF=false;

    //十抽中三四五星的数量
    public static int tenth_three=0;
    public static int tenth_four=0;
    public static int tenth_five=0;

    //保存抽到的角色
    static LinkedList get_items=new LinkedList();
    //六个常驻五星
    static int Long_Five;
    //其余四星数量，列表
    static int Rest_four=items.four_Hero[0].length-3;
    static ArrayList<String> rest_Four=new ArrayList<>();
    //Up四星
    static String[] up4star={UP_Four1,UP_Four2,UP_Four3};
}
