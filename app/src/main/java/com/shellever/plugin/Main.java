package com.shellever.plugin;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.alibaba.fastjson.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static com.shellever.plugin.Utils.*;
class Main {
    private final Send send;
    private final long groupID;
    private final String msg;
    private final String nick;
    private final String KEY;
    private final long qq;
    private final long atqq;

    /**
     *  @param send          发言用的一个函数
     * @param groupID       消息来源群号
     * @param msg           消息
     * @param nick          昵称
     * @param qq            QQ
     * @param atqq          被at人的QQ
     */
    Main(Send send, long groupID, String msg, String nick, long qq, long atqq,String KEY) {

        this.send = send;
        this.groupID = groupID;
        this.msg = msg;
        this.nick = nick;
        this.qq = qq;
        this.atqq = atqq;
        this.KEY = KEY;



    }

/**
 *               processMsg    消息接收器 框架里面勾选的群都能处理
 *              .表示除\n以外的任何字符
 *               *表示0个到无穷个
 *               +表示1个到无穷个
 *               |表示左侧或右侧任意一个（可以与括号配合使用）
 *              [0-9]表示任意一个数字
 *              [0-9A-Za-z]表示任意一个数字或字母
 */
void processMsg() {
    if(msg.matches("抽签")){
        try {
            int randomInt =getRandomInt(1,100);
            set(ROOT+"奶御抽签/观音灵签/"+groupID,qq,randomInt);
            Document doc =  Jsoup.connect("https://www.buyiju.com/guanyin/"+randomInt+".html").get();
            String data = doc.select("p").text();
            String qianname = getSubString(data,"观音灵签","诗曰");
            String shiyue = getSubString(data,"诗曰","签语");
            String qianyu = getSubString(data,"签语","解签");
            send.s("【观音灵签】\\r" +
                    qianname+"\\r" +
                    "【诗曰】\\r"+
                    shiyue+"\\r" +
                    "【签语】\\r"+qianyu+
                    "\\r\\r请发送【解签】");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    if(msg.matches("解签")){
        int randomInt = getInt(ROOT+"奶御抽签/观音灵签/"+groupID,qq);
        try {
            Document doc =  Jsoup.connect("https://www.buyiju.com/guanyin/"+randomInt+".html").get();
            String data = doc.select("p").text();
            String qianname = getSubString(data,"观音灵签","诗曰");
            String jieqian = getSubString(data,"解签","仙机");
            send.s("【观音灵签】\\r" +
                    qianname+"\\r" +
                    "【解签】"+"\\r"+jieqian);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    if(msg.matches("关帝抽签")){
        int randomInt =getRandomInt(1,100);
        set(ROOT+"奶御抽签/关帝灵签/"+groupID,qq,randomInt);
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.buyiju.com/guandi/"+randomInt+".html").get();
            String data = doc.select("p").text();
            String qianname = getSubString(data,"关帝灵签第","【签词】");
            String qianci = getSubString(data,"【签词】","【圣意】");
            String shengyi = getSubString(data,"【圣意】","【解曰】");
            send.s("【关帝灵签】\\r" +
                    qianname+"\\r" +
                    "【签词】"+"\\r" +
                    qianci+"\\r" +
                    "【圣意】"+ "\\r" +
                    shengyi+"\\r\\r请发送【关帝解签】");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = doc.select("p").text();
    }
    if(msg.matches("关帝解签")){
        int randomInt =getInt(ROOT+"奶御抽签/关帝灵签/"+groupID,qq,0);
        set(ROOT+"奶御抽签/关帝灵签/"+groupID,qq,randomInt);
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.buyiju.com/guandi/"+randomInt+".html").get();
            String data = doc.select("p").text()+"【尾部】";
            String qianname = getSubString(data,"关帝灵签第","【签词】");

            String jieqian = getSubString(data,"【解签】","【东坡解】");
            send.s("【关帝灵签】\\r" +
                    qianname+"\\r" +
                    "【解签】"+ "\\r" +
                    jieqian);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    if(msg.matches("月老抽签")){
        int randomInt =getRandomInt(1,100);
        set(ROOT+"奶御抽签/月老灵签/"+groupID,qq,randomInt);
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.buyiju.com/yuelao/"+randomInt+".html").get();
            String data = doc.select("p").text();
            String qianname = getSubString(data,"月老灵签","【签词】");
            String qianci = getSubString(data,"【签词】","【解签】");
            send.s("【月老灵签】\\r" +
                    qianname+"\\r" +
                    "【签词】"+"\\r" +
                    qianci+
                   "\\r\\r请发送【月老解签】");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = doc.select("p").text();
    }
    if(msg.matches("月老解签")){
        int randomInt =getInt(ROOT+"奶御抽签/月老灵签/"+groupID,qq,0);
        set(ROOT+"奶御抽签/月老灵签/"+groupID,qq,randomInt);
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.buyiju.com/yuelao/"+randomInt+".html").get();
            String data = doc.select("p").text();
            String qianname = getSubString(data,"月老灵签","【签词】");
            if (!data.startsWith("【注】")){
                data +="【注】";
            }
            String jieqian = getSubString(data,"【解签】","【注】");
            send.s("【月老灵签】\\r" +
                    qianname+"\\r" +
                    "【解签】"+ "\\r" +
                    jieqian);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    if (msg.matches("观音灵签")){
        send.s("观音灵签告诉你的，一个是你目前的遭遇，一个是解决问题，趋福避祸的方法，不是定数，而是变数。是佛教文化与本土文化相融合，用佛学的智慧来解释卦象。与其说《观音灵签》是占卜之书，还不如说是佛学智慧对未来境遇的瞻望，进而力有所指。种下“善因”，何愁没有“善果”！");
    }
    if (msg.matches("月老灵签")){
        send.s("人们感念月老成就世间的姻缘，故民间多有给他塑像立庙，以求佑护家庭平安；而未婚的男女，经常会前往月老庙，祈求月老灵签，希望给予指示，一生中的姻缘在何方。旧时杭州的西湖边上，便有一座月老祠，怀春的痴情男女，进去烧香、抽签、许愿的，络绎不绝。月老祠中的月老灵签一百枝，第一枝月老灵签是：“关关雎鸠，在河之洲。窈窕淑女，君子好逑。”第一百枝月老灵签是：“愿天下有情人都成眷属。”表达了人们对美好姻缘的向往。未婚的您，来试着抽一枝月老灵签吧。");
    }
    if (msg.matches("关帝灵签")){
        send.s("关帝俗称关公，是世人崇拜的英雄，为保护人民的神祗，也是做生意之人必须信奉的武财神，保证商品童叟无欺，跟土地公一样保佑生意兴隆、招财进宝，香火兴盛、历久不衰。\\r" +
                "关帝灵签是一个很古老的占卜项目，奶御·关公灵签的签词特选取了现存通行于闽南及台湾的泉州通淮关岳庙诗签版本，系清·光绪间泉州通淮关岳庙印制的秘本《关帝灵签》。");
    }
    if(msg.matches("奶御抽签")){
        send.s("       奶御抽签\\r" +
               "观音灵签 关帝灵签\\r" +
               "月老灵签 抽签方法");
    }
    if(msg.matches("抽签方法")){
        send.s("观音灵签 发送【抽签】\\r" +
                "关帝灵签 发送【关帝抽签】\\r" +
                "月老灵签 发送【月老抽签】");
    }

}
    public static String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
}
