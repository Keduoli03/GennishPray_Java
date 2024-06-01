import javax.swing.*;
import java.awt.*;

public class Main {
    static GameFrame frame=new GameFrame();
    public static void main(String[] args) {
        items.item_init();
        //筛选剩下的四星角色
        for(int i=0;i<items.four_Hero[0].length;i++){
            String currentString=items.four_Hero[0][i];
            if(!currentString.equals(Pray_Probability.UP_Four1)&&!currentString.equals(Pray_Probability.UP_Four2)&&!currentString.equals(Pray_Probability.UP_Four3)){
                Pray_Probability.rest_Four.add(currentString);
            }
        }
        //窗口启动
        frame.run();
    }
}