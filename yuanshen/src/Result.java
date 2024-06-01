import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Result {
    static JFrame result;
    static JLabel jLabel;

    static int ItemNum=0;//角色在items类的数组序列
    static int item_star = 0;//角色星级
    static String mulu="";//物品图片
    static  String big_small="Big\\";
    static String bk_mulu="";//武器背景目录
    static String Type_mulu="";//物品、物品背景、属性等图片路径,这里其实你要是不嫌乱可以将所有图片放一个文件夹里，代码和文件夹总得疯一个
    static int Item_Width=1500;
    static int Item_Height=800;//这里因为武器和角色图片尺寸不太一样，看看就懂了(PS:这里你可以自己改，然后图片会因此效果不一样的)
    static String[] names=convertLinkedListToArray(Pray_Probability.get_items);
    //获得名字的序列的方法
    public static int findPosition(String[] names, String[][] arr,int n) {
        int position = -1;
        String element=names[n];
            for (int i = 0; i < arr.length; i++) {
                if (element.equals(arr[i][0])) {
                    position = i;
                    break;
                }
        }
        return position;
    }
    //批量读取角色名字的方法(字符串
    public static String[] convertLinkedListToArray(LinkedList<String> linkedList) {
        // 创建一个字符串数组，长度为链表的大小
        String[] resultArray = new String[linkedList.size()];
        // 遍历链表，将元素逐个添加到数组中
        int index = 0;
        for (String item : linkedList) {
            resultArray[index] = item;
            index++;
        }

        return resultArray;
    }

    //判断物品级别、位置的方法(Ps:你要是把所有的东西放一个文件夹里就不需要这个方法了,不过要注意武器图片大小和角色是不一样的，要有区别)
    public static void finditem_star(int n){
        ItemNum =findPosition(names, items.five_Hero,n);
        if(ItemNum>=0){
            item_star=5;
            mulu="yuanshen\\src\\resources\\Hero\\Five\\"+big_small+items.five_Hero[ItemNum][0];
            Type_mulu="yuanshen\\src\\resources\\Type\\Small\\"+items.five_Hero[ItemNum][1];
        }else if(ItemNum <0){
            ItemNum=findPosition(names,items.four_Hero,n);
            if(ItemNum>=0){
                item_star=4;
                mulu="yuanshen\\src\\resources\\Hero\\Four\\"+big_small+items.four_Hero[ItemNum][0];
                Type_mulu="yuanshen\\src\\resources\\Type\\Small\\"+items.four_Hero[ItemNum][1];
                if(ItemNum>=33&& big_small.equals("Big\\")){
                    Item_Width=500;Item_Height=725;
                    bk_mulu="yuanshen\\src\\resources\\Type\\"+big_small+items.four_Hero[ItemNum][1];
                }
            }else if(ItemNum<0){
                item_star=3;
                ItemNum=findPosition(names,items.three_items,n);
                mulu="yuanshen\\src\\resources\\Hero\\Three\\"+big_small+items.three_items[ItemNum][0];
                bk_mulu="yuanshen\\src\\resources\\Type\\"+big_small+items.three_items[ItemNum][1];
                Type_mulu="yuanshen\\src\\resources\\Type\\Small\\"+items.three_items[ItemNum][1];
                Item_Width=500;Item_Height=725;
            }
        }
    }

    public  static void Result() {
        result = new JFrame();

        // 窗口大小设置
        result.setSize(util.Frame_Width, util.Frame_Height);
        // 标题设置
        result.setTitle(util.FrameTitle);
        // 窗口初始位置居中
        result.setLocationRelativeTo(Main.frame);
        // 窗口大小固定
        result.setResizable(false);
        //移除边框
        result.setUndecorated(true);

        ImageIcon img = new ImageIcon(util.result_bk_Image);
        // 要设置的背景图片
        JLabel imgLabel = new JLabel(img);
        // 将背景图放在标签里。
        result.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        // 将背景标签添加到LayeredPane面板里。
        imgLabel.setBounds(0, 0, util.Frame_Width, util.Frame_Height);
        // 设置背景标签的位置
        Container contain = result.getContentPane();
        ((JPanel) contain).setOpaque(false);
        // 将内容面板设为透明。将LayeredPane面板中的背景显示出来。
        jLabel = new JLabel();

        // 窗口关闭事件
        JButton closeButton=new JButton("关闭");
        ImageIcon closeButtonIcon=new ImageIcon("yuanshen\\src\\resources\\关闭.png");
        closeButton.setIcon(closeButtonIcon);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setBounds(result.getWidth()-75,10,77,65);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.dispose();
            }
        });
        result.add(closeButton);

        Timer timer = new Timer(6000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                names=convertLinkedListToArray(Pray_Probability.get_items);
                if(names.length==1){
                    big_small="Big\\";
                    finditem_star(0);
                    //物品名字(这个本来是最后写的，但是发现放后面会导致字体被遮挡)
                    JLabel ItemName;
                    ItemName = new JLabel(names[0]);
                    JPanel namePanel=new JPanel();
                    ItemName.setFont(ItemName.getFont().deriveFont(36.0f));//文字大小
                    ItemName.setForeground(Color.WHITE);

                    namePanel.add(ItemName);
                    namePanel.setOpaque(false);
                    namePanel.setBounds(130,390,200,80);
                    result.add(namePanel);

                    //角色、武器属性图标
                    BufferedImage typeImage=util.ImageScaler.scaleImage(Type_mulu+".png",115,115);
                    ImageIcon typeImageIcon = new ImageIcon(typeImage);
                    JLabel type = new JLabel();
                    type.setIcon(typeImageIcon);
                    JPanel typePanel=new JPanel();
                    typePanel.setOpaque(false);
                    typePanel.add(type);
                    result.add(typePanel);
                    typePanel.setBounds(60,360,120,120);

                    //星级图标
                    for(int i=1;i<=item_star;i++){
                        BufferedImage StarImage=util.ImageScaler.scaleImage("yuanshen\\src\\resources\\Star.png",24,21);
                        ImageIcon StarImageIcon = new ImageIcon(StarImage);
                        JLabel star=new JLabel(StarImageIcon);
                        JPanel starPanel=new JPanel();
                        starPanel.add(star);
                        starPanel.setOpaque(false);
                        result.add(starPanel);
                        starPanel.setBounds(125+26*i,450,25,26);
                    }
                    //设置面板布局居中
                    GridBagConstraints gbc=new GridBagConstraints();
                    gbc.gridx=0;gbc.gridy=0;
                    gbc.anchor= GridBagConstraints.CENTER;

                    //角色、武器大图
                    JPanel HeroPanel=new JPanel(new GridBagLayout());
                    HeroPanel.setOpaque(false);//面板透明设置
                    BufferedImage itemImage=util.ImageScaler.scaleImage(mulu+".png",Item_Width,Item_Height);
                    ImageIcon itemImageIcon = new ImageIcon(itemImage);
                    JLabel hero = new JLabel();
                    hero.setIcon(itemImageIcon);
                    HeroPanel.add(hero,gbc);

                    //判断是否加入武器背景
                    if(!bk_mulu.equals("")){
                        BufferedImage bkImage=util.ImageScaler.scaleImage(bk_mulu+".png",700,700);
                        ImageIcon bkImageIcon = new ImageIcon(bkImage);
                        JLabel bk=new JLabel(bkImageIcon);
                        HeroPanel.add(bk,gbc);
                    }
                    result.add(HeroPanel);
                    //归零(否则会出问题
                    ItemNum=0;item_star = 0;mulu=" ";bk_mulu="";big_small="";Type_mulu="";Item_Width=1500;Item_Height=800;
                }else{
                    JPanel tenthPanel=new JPanel(new FlowLayout());
                    tenthPanel.setOpaque(false);
                    for(int i=0;i<10;i++){
                        big_small="\\";
                        finditem_star(i);
                        JPanel tenthtypePanel=new JPanel();
                        JPanel tenthstartypePanel=new JPanel(new FlowLayout());
                        tenthtypePanel.setOpaque(false);tenthstartypePanel.setOpaque(false);

                        BufferedImage tenthbkImage=util.ImageScaler.scaleImage("yuanshen\\src\\resources\\Type\\Small\\框"+".png",110,660);
                        BufferedImage tenthitemsImage=util.ImageScaler.scaleImage(mulu+".png",154,520);
                        BufferedImage tenthtypeImage=util.ImageScaler.scaleImage(Type_mulu+".png",70,70);
                        BufferedImage startype;

                        if(item_star==5){
                            startype=util.ImageScaler.scaleImage("yuanshen\\src\\resources\\Type\\Small\\金色框"+".png",114,1140);

                        } else if (item_star==4) {
                            startype=util.ImageScaler.scaleImage("yuanshen\\src\\resources\\Type\\Small\\紫色框"+".png",114,1140);
                        }else{
                            startype=util.ImageScaler.scaleImage("yuanshen\\src\\resources\\Type\\Small\\蓝色框"+".png",114,1140);
                        }
                        ImageIcon tenthbkImageIcon=new ImageIcon(tenthbkImage);
                        ImageIcon tenthitemsImageIcon=new ImageIcon(tenthitemsImage);
                        ImageIcon tenthtypeImageIcon=new ImageIcon(tenthtypeImage);
                        ImageIcon tenthstartypeImageIcon=new ImageIcon(startype);


                        JLabel tenthbkJabel=new JLabel(tenthbkImageIcon);
                        JLabel tethtypeJabel=new JLabel(tenthtypeImageIcon);
                        JLabel tenthitemsJabel=new JLabel(tenthitemsImageIcon);
                        JLabel tenthstartypeJabel=new JLabel(tenthstartypeImageIcon);

                        tenthbkJabel.setLayout(new BorderLayout());

                        for(int j=1;j<=item_star;j++){
                            int temp=115*i;
                            BufferedImage StarImage=util.ImageScaler.scaleImage("yuanshen\\src\\resources\\Star.png",14,14);
                            ImageIcon StarImageIcon = new ImageIcon(StarImage);
                            JLabel star=new JLabel(StarImageIcon);
                            JPanel starPanel=new JPanel();
                            starPanel.add(star);
                            starPanel.setOpaque(false);
                            result.add(starPanel);
                            starPanel.setBounds(53+21*j+temp,550,16,21);
                        }
                        tenthbkJabel.add(tenthitemsJabel);
                        tenthPanel.add(tenthbkJabel);
                        tenthtypePanel.add(tethtypeJabel);
                        tenthtypePanel.setBounds(80+116*i,480,70,70);
                        result.add(tenthtypePanel);

                        tenthstartypePanel.setBounds(48+116*i,-240,140,1140);
                        tenthstartypePanel.add(tenthstartypeJabel);
                        result.add(tenthstartypePanel);

                        //归零(否则会出问题
                        ItemNum=0;item_star = 0;mulu="";bk_mulu="";Type_mulu="";Item_Width=1500;Item_Height=800;
                    }
                    result.add(tenthPanel);
                }

                //将这个窗体始终保持在最上面
                result.setAlwaysOnTop(true);
                result.setVisible(true);
                Pray_Probability.get_items.clear();
                names = null;
            }
        });
        timer.setRepeats(false); // 设置定时器只执行一次
        timer.start(); // 启动定时器
    }
}
