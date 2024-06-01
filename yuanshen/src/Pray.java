import java.util.Random;
public class Pray {
    static Random r=new Random();
    static double five_P;//五星概率
    public static void pary(int number){
        for(int i = 0; i< number; i++){
            Pray_Probability.Pray_Frequency++;
            Pray_Probability.Pray_All++;
            if(Pray_Probability.Pray_Frequency<=73){
                five_P=Pray_Probability.five_PP;
            } else{
                five_P=Pray_Probability.five_PP2;
            }
            double four_P=Pray_Probability.four_PP;
            //如果四星保底机制触发，四星概率加大
            if(Pray_Probability.four_star_least==8){
                four_P=0.561;
            }
            if(Pray_Probability.four_star_least>=9){
                four_P=1;
            }
            //五星判断
            if(r.nextDouble()<five_P){
                Pray_Probability.Pray_Frequency=0;
                Pray_Probability.four_star_least++;
                Pray_Probability.tenth_five++;
            }
            //四星判断、重置四星保底
            else if (r.nextDouble()>five_P&&r.nextDouble()<(five_P+four_P)) {
                Pray_Probability.four_star_least=0;
                Pray_Probability.tenth_four++;
            }
            //三星判断
            else{
                Pray_Probability.four_star_least++;
                Pray_Probability.tenth_three++;
            }
        }
        Pray10_getFive();
        Pray10_getFour();
        Pray10_getThree();
        System.out.println("此次十抽抽到五星："+Pray_Probability.tenth_five+"个，四星："+Pray_Probability.tenth_four+"个，三星"+Pray_Probability.tenth_three+"个。祝您游玩愉快！！");
        System.out.println("抽到的是："+Pray_Probability.get_items.toString());
    }
    public static void Pray10_getFive(){
        System.out.println("获得"+Pray_Probability.tenth_five+"个五星");
        for(int i =0;i<Pray_Probability.tenth_five;i++){
            if(!Pray_Probability.isBBD){
                if(r.nextDouble()<0.5){
                    result("没歪");
                }else{
                    System.out.println("糟糕！歪了！");
                    Pray_Probability.Long_Five=r.nextInt(6);
                    result("歪了");
                    Pray_Probability.isBBD=true;
                }
            } else if (Pray_Probability.isBBD) {
                result("大保底");
                Pray_Probability.isBBD=false;
            }
        }
    }
    public static void result(String i){
        switch (i) {
            case "歪了":
                Pray_Probability.Long_Five = r.nextInt(6);
                System.out.printf("抽到了：%s%n", items.five_Hero[Pray_Probability.Long_Five][0]);
                Pray_Probability.get_items.add(items.five_Hero[Pray_Probability.Long_Five][0]);
                break;
            case "大保底":
                System.out.println("成功抽到UP角色:" + Pray_Probability.UP_Five);
                Pray_Probability.get_items.add(Pray_Probability.UP_Five);
                break;
            case "没歪":
                System.out.println("抽到了：" + Pray_Probability.UP_Five);
                Pray_Probability.get_items.add(Pray_Probability.UP_Five);
                break;
        }
    }
    public static void Pray10_getFour(){
        for(int i=0;i<Pray_Probability.tenth_four;i++){
            if (r.nextInt(items.four_Hero[0].length) > Pray_Probability.Rest_four) {
                Pray_Probability.get_items.add(Pray_Probability.up4star[r.nextInt(3)]);
            } else {
                if (Pray_Probability.isUPF == false) {
                    Pray_Probability.get_items.add(Pray_Probability.rest_Four.get(r.nextInt(Pray_Probability.Rest_four)));
                    Pray_Probability.isUPF = true;
                } else if (Pray_Probability.isUPF == true) {
                    Pray_Probability.get_items.add(Pray_Probability.up4star[r.nextInt(3)]);
                    Pray_Probability.isUPF = false;
                }
            }
        }
    }

    public static void Pray10_getThree(){
        for(int i =0;i<Pray_Probability.tenth_three;i++){
            Pray_Probability.get_items.add(items.three_items[r.nextInt(items.three_items.length)][0]);
        }
    }
}
