
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JButton;

import static java.awt.Image.SCALE_DEFAULT;

public class GameFrame extends JFrame implements Runnable {
    //视频路径
    public String video_mulu;
    //常驻角色id
    public static int Long_id;
    public static boolean compareString(String str, String[][] array) {
        if(str!=null){
            for (String s : array[0]) {
                if (str.equals(s)) {
                    return true;
                }
            }
        }
            return false;
    }

    public void run() {
        //窗口大小设置
        this.setSize(util.Frame_Width, util.Frame_Height);
        //标题设置
        this.setTitle(util.FrameTitle);
        //窗口初始位置居中
        this.setLocationRelativeTo(null);
        //窗口大小固定
        this.setResizable(false);
        //窗口关闭事件
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗体图标设置
        Image icon = Toolkit.getDefaultToolkit().getImage("\\image\\logo.png");
        this.setIconImage(icon);
        final JDialog util_Dialog=new JDialog(this);
        JButton guanbi = new JButton("确定");
        util_Dialog.add(guanbi);

        //设置背景图
        BufferedImage resizedImage=util.ImageScaler.scaleImage(util.bk_image,util.Frame_Width,util.Frame_Height);
        //要设置的背景图片
        JLabel imgLabel = new JLabel(new ImageIcon(resizedImage));
        //将背景图放在标签里。
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        //将背景标签添加到jframe的LayeredPane面板里。
        imgLabel.setBounds(0, 0, util.Frame_Width, util.Frame_Height);
        // 设置背景标签的位置
        Container contain = this.getContentPane();
        ((JPanel) contain).setOpaque(false);
        // 将内容面板设为透明。将LayeredPane面板中的背景显示出来。
        //在窗口中插入一张面板，以后按钮都在上面设置
        JPanel background = new JPanel();
        this.add(background);
        //初始化窗口布局
        background.setLayout(null);
        //右上角的一堆设置
        BufferedImage PrimogemsImage=util.ImageScaler.scaleImage("yuanshen/src/resources/原石.png",40,40);
        ImageIcon PrimogemsImageIcon=new ImageIcon(PrimogemsImage);
        JLabel PrimogemsImageJabel=new JLabel(PrimogemsImageIcon);
        background.add(PrimogemsImageJabel);
        PrimogemsImageJabel.setBounds(940,20,40,40);

        BufferedImage PinkBallImage=util.ImageScaler.scaleImage("yuanshen/src/resources/粉球.png",40,40);
        ImageIcon PinkBallImageIcon=new ImageIcon(PinkBallImage);
        JLabel PinkBallImageJabel=new JLabel(PinkBallImageIcon);
        background.add(PinkBallImageJabel);
        PinkBallImageJabel.setBounds(1120,20,40,40);

        JLabel PrimogemsNum=new JLabel(String.valueOf(Pray_Probability.Primogems));
        PrimogemsNum.setBounds(981,27,70,20);
        PrimogemsNum.setFont(new Font("黑体",Font.PLAIN,16));

        JLabel PinkBallNum=new JLabel(String.valueOf(Pray_Probability.PinkBall_Number));
        PinkBallNum.setBounds(1170,27,70,20);
        PinkBallNum.setFont(new Font("黑体",Font.PLAIN,16));
        // 创建一个定时器
        Timer Jabeltimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 在这里更新标签的内容
                PrimogemsNum.setText(String.valueOf(Pray_Probability.Primogems));
                PinkBallNum.setText(String.valueOf(Pray_Probability.PinkBall_Number));
            }
        });
        // 启动定时器
        Jabeltimer.start();
        background.add(PrimogemsNum);
        background.add(PinkBallNum);

        //按键设置
        JButton Ten_bt = new JButton("祈愿十次");
        JButton One_bt = new JButton("祈愿一次");
        JButton history = new JButton("抽卡记录");
        JButton detail = new JButton("卡池详情");
        JButton Primogems=new JButton("原石");
        JButton change=new JButton("更换卡池");

        Primogems.setBounds(1050,20,40,40);
        One_bt.setBounds(750, 550, util.Icon_Width, util.Icon_Height);
        Ten_bt.setBounds(950, 550, util.Icon_Width, util.Icon_Height);
        history.setBounds(10, 550, util.Icon_Width, util.Icon_Height);
        detail.setBounds(210, 550, util.Icon_Width, util.Icon_Height);
        change.setBounds(410,550,util.Icon_Width, util.Icon_Height);
        //为按钮设置图标
        One_bt.setIcon(util.get_icon(util.One_image));
        Ten_bt.setIcon(util.get_icon(util.Ten_image));
        history.setIcon(util.get_icon(util.History_image));
        detail.setIcon(util.get_icon(util.Detail_image));
        change.setIcon(util.get_icon("yuanshen/src/resources/change.png"));
        BufferedImage PrimogesButtonImage=util.ImageScaler.scaleImage("yuanshen/src/resources/加号.png",35,35);
        ImageIcon PrimogemsIcon=new ImageIcon(PrimogesButtonImage);
        Primogems.setIcon(PrimogemsIcon);
        //按钮透明
        One_bt.setContentAreaFilled(false);
        Ten_bt.setContentAreaFilled(false);
        history.setContentAreaFilled(false);
        detail.setContentAreaFilled(false);
        change.setContentAreaFilled(false);
        Primogems.setContentAreaFilled(false);
        //按钮边框线隐藏
        One_bt.setBorderPainted(false);
        Ten_bt.setBorderPainted(false);
        history.setBorderPainted(false);
        change.setBorderPainted(false);
        detail.setBorderPainted(false);
        Primogems.setBorderPainted(false);

        background.add(One_bt);
        background.add(Ten_bt);
        background.add(history);
        background.add(detail);
        background.add(Primogems);
        background.add(change);
        //按钮获取焦点
        history.requestFocus();
        detail.requestFocus();
        One_bt.requestFocus();
        Ten_bt.requestFocus();
        //按键监听
        One_bt.addActionListener(e -> {
            if(Pray_Probability.PinkBall_Number<=0&&Pray_Probability.Primogems<160){
                    util_Dialog.setModal(true);
                    util_Dialog.setSize(500,100);
                    util_Dialog.getContentPane().removeAll();
                    JLabel jLabel=new JLabel();
                    util_Dialog.add(jLabel);
                    jLabel.setText("<html>您的资金不足，请向作者打款后继续游玩(滑稽)</html>");
                    util_Dialog.setLocationRelativeTo(null);
                    util_Dialog.setVisible(true);
            }else if(Pray_Probability.PinkBall_Number<=0&&Pray_Probability.Primogems>160){
                util_Dialog.setModal(true);
                util_Dialog.setSize(270,110);
                util_Dialog.getContentPane().removeAll();
                JLabel jLabel=new JLabel();
                util_Dialog.add(jLabel);
                jLabel.setText("<html>您的粉球数量不足将使用160原石进行一次抽奖</html>");
                JButton button=new JButton("原石兑换");
                button.addActionListener(e1 -> {
                    util_Dialog.dispose(); // 关闭对话框
                    Pray_Probability.Primogems-=160;
                    Pray.pary(1);
                    Result.Result();
                    set_video(1);
                });
                util_Dialog.add(button);
                util_Dialog.setLocationRelativeTo(null);
                util_Dialog.setVisible(true);
            }else if(Pray_Probability.PinkBall_Number>=1){
                Pray_Probability.PinkBall_Number--;
                Pray.pary(1);
                Result.Result();
                set_video(1);
            }
        });
        Ten_bt.addActionListener(e -> {
            if(Pray_Probability.PinkBall_Number>=10){
                Pray_Probability.PinkBall_Number-=10;
                Pray.pary(10);
                Result.Result();
                set_video(10);
            } else if (Pray_Probability.PinkBall_Number!=0&&Pray_Probability.PinkBall_Number<10&&(Pray_Probability.Primogems/160+Pray_Probability.PinkBall_Number)>=10) {
                util_Dialog.setModal(true);
                util_Dialog.setSize(300,110);
                util_Dialog.getContentPane().removeAll();
                JLabel jLabel=new JLabel();
                util_Dialog.add(jLabel);
                jLabel.setText("<html>您的粉球数量不足将使用"+160*(10-Pray_Probability.PinkBall_Number)+"原石补足进行十次抽奖</html>");
                JButton button=new JButton("原石兑换");
                button.addActionListener(e1 -> {
                    util_Dialog.dispose(); // 关闭对话框
                    Pray_Probability.Primogems-=160*(10-Pray_Probability.PinkBall_Number);
                    Pray_Probability.PinkBall_Number=0;
                    Pray.pary(10);
                    Result.Result();
                    set_video(10);
                });
                util_Dialog.add(button);
                util_Dialog.setLocationRelativeTo(null);
                util_Dialog.setVisible(true);
            } else if (Pray_Probability.Primogems>=1600) {
                util_Dialog.setModal(true);
                util_Dialog.setSize(290,110);
                util_Dialog.getContentPane().removeAll();
                JLabel jLabel=new JLabel();
                util_Dialog.add(jLabel);
                jLabel.setText("<html>您的粉球数量不足将使用1600原石进行十次抽奖</html>");
                JButton button=new JButton("原石兑换");
                button.addActionListener(e1 -> {
                    util_Dialog.dispose(); // 关闭对话框
                    Pray_Probability.Primogems-=1600;
                    Pray.pary(10);
                    Result.Result();
                    set_video(10);
                });
                util_Dialog.add(button);
                util_Dialog.setLocationRelativeTo(null);
                util_Dialog.setVisible(true);
            } else if (Pray_Probability.Primogems<1600&&Pray_Probability.PinkBall_Number<10&&(Pray_Probability.Primogems/160+Pray_Probability.PinkBall_Number)<10) {
                util_Dialog.setModal(true);
                util_Dialog.setSize(290,80);
                util_Dialog.getContentPane().removeAll();
                JLabel jLabel=new JLabel();
                util_Dialog.add(jLabel);
                jLabel.setText("<html>您的资金不足十抽，请向作者打款后继续游玩(滑稽)</html>");
                util_Dialog.setLocationRelativeTo(null);
                util_Dialog.setVisible(true);
            }
        });
        history.addActionListener(e -> {
            util_Dialog.setModal(true);
            util_Dialog.setSize(200,150);
            util_Dialog.getContentPane().removeAll();
            JLabel jLabel=new JLabel();
            util_Dialog.add(jLabel);
            jLabel.setText("<html>本卡池总抽数为：" + Pray_Probability.Pray_Frequency + "<br>四星保底抽卡数已到：" + Pray_Probability.four_star_least + "<br>全部总抽数为：" + Pray_Probability.Pray_All + "</html>");
            util_Dialog.setLocationRelativeTo(null);
            util_Dialog.setVisible(true);
        });
        detail.addActionListener(e -> {
            util_Dialog.setModal(true);
            util_Dialog.setSize(350,200);
            util_Dialog.getContentPane().removeAll();
            JLabel jLabel=new JLabel();
            util_Dialog.add(jLabel);
            jLabel.setText("<html>原神抽卡模拟器"+"版本：v1.0beta"+"<br>作者：珂朵莉" +"<br>应用介绍：本项目旨在模拟原神抽卡系统，包括抽卡流程、动画、概率都力争还原"+
                            "<br>v1.00:更新了结果窗口、原石粉球机制，增加了更换卡池的功能"+
                            "<br>修复了有几率十抽不出四星的bug"+
                            "<br>版本预期：应该本人不会在进行任何修改了"+
                            "<br>希望后来有人借鉴的时候，能够将一些问题完善吧"+
                            "<br>最后再次由衷的感谢您的游玩！！"+"</html>");
            util_Dialog.setLocationRelativeTo(null);
            util_Dialog.setVisible(true);
        });
        Primogems.addActionListener(e -> {
            util_Dialog.setSize(300, 95);
            util_Dialog.getContentPane().removeAll();
            JLabel jLabel = new JLabel();
            util_Dialog.add(jLabel);
            jLabel.setText("<html>请输入数字，并选择是原石数量还是粉球数量" + "</html>");
            util_Dialog.setLocationRelativeTo(null);

            // 创建两个按钮
            JButton button1 = new JButton("原石数量");
            JButton button2 = new JButton("粉球数量");

            // 添加按钮的事件监听器
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 在这里处理按钮1的事件
                    String inputStr = JOptionPane.showInputDialog(null, "请输入一个整数");
                    if (inputStr != null) {
                        Pray_Probability.Primogems+= Integer.parseInt(inputStr);
                    }
                    util_Dialog.dispose(); // 关闭对话框
                }
            });

            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 在这里处理按钮2的事件
                    String inputStr = JOptionPane.showInputDialog(null, "请输入一个整数");
                    if (inputStr != null) {
                        Pray_Probability.PinkBall_Number+=Integer.parseInt(inputStr);
                    }
                    util_Dialog.dispose(); // 关闭对话框
                }
            });
            // 将按钮添加到对话框中
            util_Dialog.getContentPane().setLayout(new FlowLayout());
            util_Dialog.getContentPane().add(button1);
            util_Dialog.getContentPane().add(button2);
            // 显示对话框
            util_Dialog.setVisible(true);
        });
        change.addActionListener(e -> {
                    util_Dialog.setSize(380, 150);
                    util_Dialog.getContentPane().removeAll();
                    JPanel textPanel=new JPanel();
                    JPanel buttonPanel=new JPanel();
                    JLabel jLabel = new JLabel();
                    textPanel.add(jLabel);
                    jLabel.setText("<html>请选择想修改的角色，并填入正确的角色名称" + "</html>");
                    util_Dialog.setLocationRelativeTo(null);

                    // 创建两个按钮

                    JButton button1 = new JButton("五星角色");
                    JButton button2 = new JButton("四星角色1");
                    JButton button3=new JButton("四星角色2");
                    JButton button4 = new JButton("四星角色3");

                    // 添加按钮的事件监听器
                    button1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // 在这里处理按钮1的事件
                            String inputStr = JOptionPane.showInputDialog(null, "请保证输入的是正确的角色");
                            boolean istrue=compareString(inputStr,items.five_Hero);
                            if (inputStr != null&&istrue==true) {
                                Pray_Probability.UP_Five=inputStr;
                                System.out.println("此次修改的角色为"+Pray_Probability.UP_Five);
                            }else {
                                JOptionPane.showMessageDialog(null,"输入的角色不正确，请重新输入");
                            }
                        }
                    });

                    button2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // 在这里处理按钮2的事件
                            String inputStr = JOptionPane.showInputDialog(null, "请保证输入的是正确的角色");
                            boolean istrue=compareString(inputStr,items.four_Hero);
                            if (inputStr != null&&istrue==true) {
                                Pray_Probability.UP_Four1=inputStr;
                                System.out.println("此次修改的角色为"+Pray_Probability.UP_Four1);
                            }else {
                                JOptionPane.showMessageDialog(null,"输入的角色不正确，请重新输入");
                            }
                        }
        });
                    button3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // 在这里处理按钮3的事件
                            String inputStr = JOptionPane.showInputDialog(null, "请保证输入的是正确的角色");
                             boolean istrue=compareString(inputStr,items.four_Hero);
                             if (inputStr != null&&istrue==true) {
                                 Pray_Probability.UP_Four2=inputStr;
                                 System.out.println("此次修改的角色为"+Pray_Probability.UP_Four2);
                                }else {
                                 JOptionPane.showMessageDialog(null,"输入的角色不正确，请重新输入");
                             }
                }
            });
            button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 在这里处理按钮4的事件
                    String inputStr = JOptionPane.showInputDialog(null, "请保证输入的是正确的角色");
                    boolean istrue=compareString(inputStr,items.four_Hero);
                    if (inputStr != null&&istrue==true) {
                        Pray_Probability.UP_Four3=inputStr;
                        System.out.println("此次修改的角色为"+Pray_Probability.UP_Four3);
                    }else {
                        JOptionPane.showMessageDialog(null,"输入的角色不正确，请重新输入");
                    }
                }
            });
            util_Dialog.getContentPane().setLayout(new GridLayout(2,1));
            buttonPanel.setLayout(new GridLayout(1,4));
            buttonPanel.add(button1);
            buttonPanel.add(button2);
            buttonPanel.add(button3);
            buttonPanel.add(button4);
            util_Dialog.add(textPanel);
            util_Dialog.add(buttonPanel);
            // 显示对话框
            util_Dialog.setVisible(true);
        });
        //background面板透明
        background.setOpaque(false);
        //窗口可视化
        this.setVisible(true);
    }
    //设置播放哪段视频
    public void set_video(int n) {
        if (n == 10) {
            if (Pray_Probability.tenth_five > 0) {
                Pray_Probability.tenth_five = 0;
                Pray_Probability.tenth_four = 0;
                Pray_Probability.tenth_three = 0;
                video_mulu = util.tenth_gold;
            } else {
                Pray_Probability.tenth_four = 0;
                Pray_Probability.tenth_three = 0;
                video_mulu = util.tenth_purple;
            }
        }
        if (n == 1) {
            if (Pray_Probability.tenth_five>0) {
                Pray_Probability.tenth_five = 0;
                video_mulu = util.once_gold;
            } else if (Pray_Probability.tenth_five==0&&Pray_Probability.tenth_four>0) {
                Pray_Probability.tenth_four = 0;
                video_mulu = util.once_purple;
                Long_id = Pray_Probability.Long_Five;
            } else if (Pray_Probability.tenth_five==0&&Pray_Probability.tenth_four==0&&Pray_Probability.tenth_three>0){
                Pray_Probability.tenth_three=0;
                video_mulu = util.once_blue;
            }
        }
        // 创建并添加一个按钮，在按钮上展示图片动画
        ImageAnimationButton button = new ImageAnimationButton();
        this.getContentPane().add(button);
    }
    private class ImageAnimationButton extends JLabel {
        private File[] imageFiles;
        private final Timer Videotimer;

        public ImageAnimationButton() {
            setHorizontalAlignment(CENTER);
            // 读取图片
            //先批量读取
            File folder = new File(util.mulu + video_mulu);
            String[] fileNames = folder.list();
            Arrays.sort(fileNames);
            // 创建一个文件数组，用于保存图片文件
            imageFiles = new File[fileNames.length];
            for (int i = 0; i < fileNames.length; i++) {
                File imageFile = new File(folder, fileNames[i]);
                imageFiles[i] = imageFile;
            }
            // 对图片文件数组进行排序，按照自然顺序排列
            Arrays.sort(imageFiles, new Comparator<File>() {
                public int compare(File file1, File file2) {
                    String name1 = file1.getName();
                    String name2 = file2.getName();
                    // 提取文件名中的数字部分进行比较
                    int number1 = Integer.parseInt(name1.replaceAll("[^0-9]", ""));
                    int number2 = Integer.parseInt(name2.replaceAll("[^0-9]", ""));
                    return Integer.compare(number1, number2);
                }
            });
            // 创建一个模态对话框，在对话框中展示图片动画
            JDialog dialog = new JDialog(GameFrame.this, "动画展示", true);
            dialog.setUndecorated(true);
            JLabel label = new JLabel();
            dialog.getContentPane().add(label);
            // 使用 Timer 类实现图片动画
            // 100 毫秒
            int delay = 10;
            Videotimer = new Timer(delay, new ActionListener() {
                private int index = 0;
                public void actionPerformed(ActionEvent e) {
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(imageFiles[index]);
                        image.getScaledInstance(util.Frame_Width, util.Frame_Height, SCALE_DEFAULT); // 缩放图片
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    label.setIcon(new ImageIcon(image));
                    index = (index + 1) % imageFiles.length;
                    //播放完自动关闭
                    if (index == 0) {
                        // 关闭对话框
                        Videotimer.stop();
                        dialog.dispose();
                    }
                }
            });
            Videotimer.start();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    dialog.setSize(util.Frame_Width, util.Frame_Height);
                    dialog.setLocationRelativeTo(GameFrame.this);
                    dialog.setVisible(true);
                }
            });
        }
        @Override
        public void removeNotify() {
            super.removeNotify();
            Videotimer.stop();
        }
    }
}
