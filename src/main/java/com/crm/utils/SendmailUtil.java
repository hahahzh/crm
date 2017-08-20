package com.crm.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class SendmailUtil {

    // 设置服务器
    private static String KEY_SMTP = "mail.smtp.host";
    private static String VALUE_SMTP = "lemonade-dev.com";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
    private String SEND_USER = "admin@lemonade-dev.com";
    private String SEND_UNAME = "嘿嘿嘿";
    private String SEND_PWD ;//
    // 建立会话
    private MimeMessage message;
    private Session s;

    /*
     * 初始化方法
     */
    public SendmailUtil() {
        Properties props = System.getProperties();
        props.setProperty(KEY_SMTP, VALUE_SMTP);
        props.put(KEY_PROPS, "true");
        props.put("mail.smtp.auth", "false");
        s =  Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
            }});
        s.setDebug(true);
        message = new MimeMessage(s);
    }

    /**
     * 发送邮件
     *
     * @param headName
     *            邮件头文件名
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    public void doSendHtmlEmail(String headName, String sendHtml, String receiveUser) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            //设置自定义发件人昵称
            String nick="";
            try {
                nick=javax.mail.internet.MimeUtility.encodeText("亿马联盟");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            message.setFrom(new InternetAddress(nick+" <"+from+">"));

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=GBK");
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("send success!");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendMutiMessage(String title,String message ,String reciver) {

          MultiPartEmail email = new MultiPartEmail();
         /* String[] multiPaths = new String[] { "D:/1.jpg", "D:/2.txt" };

           List<EmailAttachment> list = new ArrayList<EmailAttachment>();
           for (int j = 0; j < multiPaths.length; j++) {
                   EmailAttachment attachment = new EmailAttachment();
                   //判断当前这个文件路径是否在本地  如果是：setPath  否则  setURL;
                   if (multiPaths[j].indexOf("http") == -1) {
                           attachment.setPath(multiPaths[j]);
                       } else {
                           try {
                               attachment.setURL(new URL(multiPaths[j]));
                          } catch (MalformedURLException e) {
                               e.printStackTrace();
                         }
                       }
                   attachment.setDisposition(EmailAttachment.ATTACHMENT);
                   attachment.setDescription("Picture of John");
                   list.add(attachment);
               }*/

           try {
                   email.setSmtpPort(25);
                   // 这里是发送服务器的名字：
                   email.setHostName("lemonade-dev.com");
                   // 编码集的设置
                   email.setCharset("utf-8");
                   // 收件人的邮箱
                   email.addTo(reciver);
                   // 发送人的邮箱
                   email.setFrom("admin@lemonade-dev.com");
                   // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码

                   //email.setAuthentication("13761671636@163.com", "Tz1994710");
                   email.setSubject(title);
                   // 要发送的信息
                   email.setMsg(message);
                   /*for (int a = 0; a < list.size(); a++) //添加多个附件
                       {
                           email.attach(list.get(a));
                       }*/
                   // 发送
                   email.send();
               } catch (EmailException e) {
                       e.printStackTrace();
                   }
            }

}