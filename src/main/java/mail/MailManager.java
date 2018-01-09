package mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailManager {

    private String smtpHost;
    private String smtpPort;
    private String login;
    private String password;
    private String thema;
    private String url;

    private Session session;

    public void init(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Authenticator auth = new EmailAuthenticator(login, password);
        session = Session.getDefaultInstance(properties, auth);
        session.setDebug(false);
    }

    public void sendToken(String to, String token){
        try{
            InternetAddress emailFrom = new InternetAddress(login);
            InternetAddress emailTo   = new InternetAddress(to);
            Message message = new MimeMessage(session);
            message.setFrom(emailFrom);
            message.setRecipient(Message.RecipientType.TO, emailTo);
            message.setSubject(thema);

            Multipart mmp = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(url + "?token=" + token, "text/plain; charset=utf-8");
            mmp.addBodyPart(bodyPart);
            message.setContent(mmp);
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
