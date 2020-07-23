package com.java.blog.service;

import com.java.blog.dto.Member;
import com.java.blog.util.Util;

public class MailService {
	private String gmailId;
	private String gmailPw;
	private String from;
	private String fromName;

	public MailService(String gmailId, String gmailPw, String from, String fromName) {
		this.gmailId = gmailId;
		this.gmailPw = gmailPw;
		this.from = from;
		this.fromName = fromName;
	}

	public int send(String to, String title, String body) {
		return Util.sendMail(gmailId, gmailPw, from, fromName, to, title, body);
	}

	public void writeFindIdMail(String email, String loginId) {
		String to = email;
		
		String title = "회원님의 아이디 입니다.";
		
		String body = "회원님의 아이디는 [ " + loginId + " ] 입니다.\n";
		body += "아이디나 비밀번호 등, 개인정보가 유출되지 않도록 조심하여 이용해주시길 바랍니다.\n";

		this.send(to, title, body);
	}

	public void writeFindPwMail(String email, String tempPwNotSec) {
		String to = email;
		
		String title = "요청하신 임시 비밀번호 입니다.";
		
		String body = "회원님의 임시 비밀번호는 [ " + tempPwNotSec + " ] 입니다. \n";
		body += "임시 비밀번호를 이용하여 로그인 하신 후, 반드시 비밀번호를 변경하여주시길 바랍니다. \n";
		body += "비밀번호 변경은 [내정보] > [정보 변경하기] 에서 가능합니다. \n";
		body += "이용해주셔서 감사합니다. \n";
		
		this.send(to, title, body);
	}

	public void writeWelcomeMail(String email, String name, String nickname) {
		String to = email;
		
		String title = "LCF 가입을 환영합니다!";
		
		String body = name + "(" + nickname + ")님! 가입을 환영합니다! ";
		body += "저희 LCF 이용 수칙은 아래와 같습니다. \n";
		body += " - 1 : 어쩌구 저쩌구 \n";
		body += " - 2 : 이러쿵 저러쿵 \n";
		body += " - 3 : 블라블라 \n";
		body += "이용 수칙을 지켜주시길 바라며 즐거운 하루되십시오. ^^ \n";
		
		this.send(to, title, body);
	}
	
	public void writeAuthMail(Member member) {
		String to = member.getEmail();
		String code = member.getMailAuthCode();
		String nickname = member.getNickname();
		String loginId = member.getLoginId();
			
		String title = "LCF 블로그 이메일 인증 메일입니다.";
		
		String body = "[ " + nickname + "]님, LCF를 이용해주셔서 감사합니다!\n";
		body += "아래의 링크를 클릭하여 이동하시면 인증이 완료됩니다.\n";
		body += "[ <a href=\"https://littlecampfire.my.iu.gy/blog/s/member/doAuthMail?code="+ code +"\" ] \n";
		body += "감사합니다.\n";
		
		this.send(to, title, body);
	}
}
