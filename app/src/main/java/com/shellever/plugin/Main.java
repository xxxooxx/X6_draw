package com.shellever.plugin;
import static com.shellever.plugin.Utils.*;
/**
 * 本项目源码由@凉生 构建
 *
 */


class Main {
    private final Send send;
    private final long groupID;
    private final String msg;
    private final String nick;
    private final String KEY;
    private final long qq;
    private final long atqq;
    private final String zr;

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
        this.zr = getString(sdCardPath() + "/Clousx6/sd1/cookie/QQ","QQ");


        this.groupID = groupID;
        this.msg = msg;
        this.nick = nick;
        this.qq = qq;
        this.atqq = atqq;
        this.KEY = KEY;
        //获取X6框架配置的主人列表
    }

    /**
     *
     * 初始化数据的函数
     *
     */
    void initX6(){
        //初始化语句放在这里
        set(ROOT + "奶御小助手/img","图",9);
        set(ROOT + "奶御小助手/img","1","http://inews.gtimg.com/newsapp_ls/0/12582188428/0");
        set(ROOT + "奶御小助手/img","2","http://inews.gtimg.com/newsapp_ls/0/12582189133/0");
        set(ROOT + "奶御小助手/img","3","http://inews.gtimg.com/newsapp_ls/0/12582189986/0");
        set(ROOT + "奶御小助手/img","4","http://inews.gtimg.com/newsapp_ls/0/12582191059/0");
        set(ROOT + "奶御小助手/img","5","http://inews.gtimg.com/newsapp_ls/0/12582192301/0");
        set(ROOT + "奶御小助手/img","6","http://inews.gtimg.com/newsapp_ls/0/12582193009/0");
        set(ROOT + "奶御小助手/img","7","http://inews.gtimg.com/newsapp_ls/0/12582193730/0");
        set(ROOT + "奶御小助手/img","8","http://inews.gtimg.com/newsapp_ls/0/12582196524/0");
        set(ROOT + "奶御小助手/img","9","http://inews.gtimg.com/newsapp_ls/0/12582197109/0");
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
        //这个是判断消息是否来自主人的    接收主人的命令  充值 和 配置卸载这里面
        if (是否包含主人(zr,qq)) {
            if (msg.matches("初始化")) {
                initX6();
                send.s("初始化成功");
            }
            //这个是判断消息是否来自主人的    接收主人的命令  充值 和 配置卸载这里面
        }

        //菜单
        if (msg.matches("菜单")) {
            send.s("这里面写你的菜单");
        }



    }
}
