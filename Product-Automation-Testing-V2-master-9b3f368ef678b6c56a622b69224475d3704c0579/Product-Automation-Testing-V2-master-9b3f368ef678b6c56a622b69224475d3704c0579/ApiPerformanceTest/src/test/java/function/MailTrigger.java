package function;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.JsonObject;

import bo.ResponceBO;

public class MailTrigger {
	DoPerformanceCheck doCheck = new DoPerformanceCheck();
	public boolean validateMailAddress(JsonObject envDetail) {
		try {
			DoPerformanceCheck doCheck = new DoPerformanceCheck();
			String toAddress= envDetail.get(doCheck.toAddress).getAsString();
			if(toAddress.trim().isEmpty()) {
				return false;
			}
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public String generateMailContent(List<ResponceBO> responce, JsonObject envDetail) {
		   StringBuilder html = new StringBuilder();
		 
			html.append("Hi Team ,");
			html.append("<html><head>");
			html.append("<title>My Header</title>"); 
			html.append("<body>");
			html.append("<br/><br/>");
			html.append("<table width='100%' cellpadding='0' cellspacing='0' border='0'>");
			html.append(
					"<tr><td><table border='2' width='800' cellpadding='4' cellspacing='4' bgColor='#FFFFFF' style='border-collapse: collapse' bordercolor='#454545' align='left'>");
			html.append("<tr bgColor=#454545 class='centerheading' align='left' style='padding: 5px 20px 5px 20px;'>");
			html.append("<td width='50' style='color: #FFFFFF;' ><b>"+envDetail.get(doCheck.environmentName).getAsString()+" APi Health Check</b></td>");
			html.append("</tr>");
			html.append("<tr align='left'>");
			html.append("<td width='80' style='color: #454545;'>App Start Time : " 
					+ " - App End Time :  &nbsp Status :" +  "  </b></td>");
			html.append("</tr>");
			html.append("</table>");
			html.append("<tr>");
			html.append("<td height='5'></td></tr>");
			html.append("<tr><td></td></tr>");
			html.append("<tr><td height='5'></td></tr>");
			html.append("<tr><td><table border='2' width='70%' cellpadding='2' cellspacing='1' bgColor='#B6AFA9' style='border-collapse: collapse'   align='left'>");
			html.append("<tr bgColor=#06407a  class='centerheading' align='center'>");
			html.append("<td width='5%' style='color: #FFFFFF;'><b>#</b></td>");
			html.append("<td width='10%' style='color: #FFFFFF;'><b>Module</b></td>");
			html.append("<td width='20%' style='color: #FFFFFF;'><b>Description</b></td>");
			html.append("<td width='5%' style='color: #FFFFFF;'><b>Api responce Code</b></td>");
			html.append("<td width='5%' style='color: #FFFFFF;'><b>dev status</b></td>");
			html.append("<td width='5%' style='color: #FFFFFF;'><b>request</b></td> ");
			html.append("<td width='25%' style='color: #FFFFFF;'><b>Reason</b></td>");
			html.append("<td width='25%' style='color: #FFFFFF;'><b>Exception</b></td>");
			for(int i=0 ;i<responce.size();i++) { 
			  html.append("</tr>");
			   String expection ="";
			    if(responce.get(i).getExceptionMessage().length() > 100) {
			    	expection= responce.get(i).getExceptionMessage().replaceAll("<.*?>", "").substring(0, 100);
			    }else {
					expection =responce.get(i).getExceptionMessage().replaceAll("<.*?>", "");
				}
			    String reason ="";
			    if(responce.get(i).getReason().length() > 100) {
			    	reason= responce.get(i).getReason().replaceAll("<.*?>", "").substring(0, 100);
			    }else {
			    	reason =responce.get(i).getReason().replaceAll("<.*?>", "");
				}
			    
	            html.append("<tr   align='left' bgColor=#7DCEA0 bordercolor='#454545' >");
				html.append("<td align='center' width='5%' style='color: #212F3D;'><b> "+(i+1)+"</b></td>" );
				html.append("<td align='left' width='10%' style='color: #212F3D;'><b>"+responce.get(i).getModuleName()+"</b></td>"  );
				html.append("<td align='left' width='20%' style='color: #212F3D;;'><b>"+responce.get(i).getDescription()+"</b></td>"   );
				html.append("<td align='center' width='5%' style='color: #212F3D;'><b>"+responce.get(i).getApiCallCode()+"</b></td>");
				html.append("<td align='center' width='5%' style='color: #212F3D;'><b>"+responce.get(i).getDevResponceCode()+"</b></td>");
				html.append("<td align='center' width='5%' style='color: #212F3D;'><b>"+responce.get(i).getRequestType()+"</b> </td>");
				html.append("<td align='left' width='25%' style='color: #212F3D;'><b>"+reason+"</b></td>");
				html.append("<td align='left' width='25%' style='color: #212F3D;'><b>"+expection+"   </b></td>");
				 
              html.append("</tr>");
			}
		 
			html.append("</table>");
			html.append("</td>");
			html.append("</tr>");
			html.append("<tr>");
			html.append("<td height='6'></td>");
			html.append("</tr>");
			html.append("</table>");
			html.append("<br> ");
			html.append("</td>");
			html.append("</tr>");
			html.append("<tr>");
			html.append("<td height='6'></td>");
			html.append("</tr>");
			html.append("</table>");
			html.append("Regards, "); 
			html.append("<br>");
			html.append("<br>");
			html.append("Prism Team ");
			html.append("<br>");
			html.append("<i>*This is an auto generated mail from Prism*</i>");
			html.append("</body></html>");
		
		return html.toString();
		 
		
	}

	public void sendMail(String mailContent, JsonObject envDetail) {
		final String username = "prismalert@datazoic.com";
        final String password = "DzPrism$123";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prismalert@datazoic.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(envDetail.get(doCheck.toAddress).getAsString()));
            message.setSubject("QA Automation Api performance check");
            message.setContent(mailContent, "text/html ; charset=utf-8");
            System.out.println(mailContent);
            //Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
	}

}
