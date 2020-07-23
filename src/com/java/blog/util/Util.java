package com.java.blog.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {
	public static boolean empty(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);

		return empty(paramValue);
	}

	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj instanceof String) {
			return ((String) obj).trim().length() == 0;
		}

		return true;
	}

	public static boolean isNum(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);

		return isNum(paramValue);
	}

	public static boolean isNum(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof Long) {
			return true;
		} else if (obj instanceof Integer) {
			return true;
		} else if (obj instanceof String) {
			try {
				Integer.parseInt((String) obj);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

		return false;
	}

	public static int getInt(HttpServletRequest req, String paramName) {
		return Integer.parseInt(req.getParameter(paramName));
	}

	public static void printEx(String errName, HttpServletResponse resp, Exception e) {
		try {
			resp.getWriter()
					.append("<h1 style='color:red; font-weight:bold; text-align:left;'>[에러 : " + errName + "]</h1>");

			resp.getWriter().append("<pre style='text-align:left; font-weight:bold; font-size:1.3rem;'>");
			e.printStackTrace(resp.getWriter());
			resp.getWriter().append("</pre>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static String getString(HttpServletRequest req, String paramName) {
		return req.getParameter(paramName);
	}

	public static int sendMail(String smtpServerId, String smtpServerPw, String from, String fromName, String to,
			String title, String body) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");

		Authenticator auth = new MailAuth(smtpServerId, smtpServerPw);

		Session session = Session.getDefaultInstance(prop, auth);

		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date());

			msg.setFrom(new InternetAddress(from, fromName));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(title, "UTF-8");
			msg.setText(body, "UTF-8");
			
			// 메일 내용을 html로 바꿔주는 코드
			// msg.setContent(body, "text/html; charset=euc-kr");

			Transport.send(msg);

		} catch (AddressException ae) {
			System.out.println("AddressException : " + ae.getMessage());
			return -1;
		} catch (MessagingException me) {
			System.out.println("MessagingException : " + me.getMessage());
			return -2;
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException : " + e.getMessage());
			return -3;
		}

		return 1;
	}

	public static String makeMailAuthCode(String loginId) {
		Random random = new Random();

    	int fCode = random.nextInt(9999);
    	int bCode = random.nextInt(9999);
    	
    	String Code = fCode + loginId + bCode;

		return Code;
	}
	
	public static String makeTempPw() {
		Random random = new Random();

		StringBuffer rdCode = new StringBuffer();

		for (int i = 0; i < 20; i++) {

			// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한
			// 숫자를 StringBuffer 에 append 한다.

			if (random.nextBoolean()) {

				rdCode.append((char) ((int) (random.nextInt(26)) + 97));

			} else {

				rdCode.append((random.nextInt(10)));
			}
		}
		
		String randomCode = rdCode.toString();
		
		return randomCode;
	}
}
