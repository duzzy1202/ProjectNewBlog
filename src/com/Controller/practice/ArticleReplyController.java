package com.Controller.practice;

import java.util.List;

import com.Dto.practice.Article;
import com.Dto.practice.CateItem;
import com.Main.practice.Factory;
import com.Main.practice.Request;
import com.Service.practice.ArticleReplyService;

public class ArticleReplyController extends Controller {
	ArticleReplyService articleReplyService;

	public ArticleReplyController() {
		articleReplyService = Factory.getArticleRelpyService();
	}

	public void doAction(Request reqeust) {
		if (reqeust.getActionName().equals("write")) {
			actionWriteReply(reqeust);
		} else if (reqeust.getActionName().equals("delete")) {
			actionDeleteReply(reqeust);
		} else if (reqeust.getActionName().equals("modify")) {
			actionModifyReply(reqeust);
		}
	}

	private void actionModifyReply(Request reqeust) {
		int replyNum = Integer.parseInt(reqeust.getArg1());
		
		System.out.println("수정할 내용 : ");
		String body = Factory.getScanner().nextLine();
		
		int deleteId = articleReplyService.ModifyReply(replyNum, body);
		
		if (deleteId == 0) {
			System.out.println("댓글이 존재하지 않습니다.");
		} else {
			System.out.printf("%d 번 댓글이 수정되었습니다.\n", replyNum);
		}
		
	}

	private void actionDeleteReply(Request reqeust) {
		int replyNum = Integer.parseInt(reqeust.getArg1());
		int deleteId = articleReplyService.Delete(replyNum);
		
		if (deleteId == 0) {
			System.out.println("댓글이 존재하지 않습니다.");
		} else {
			System.out.printf("%d 번 댓글이 삭제되었습니다.\n", replyNum);
		}
	}

	private void actionWriteReply(Request reqeust) {
		System.out.printf("댓글 내용 : ");
		String reply = Factory.getScanner().nextLine();

		int articleNum = Integer.parseInt(reqeust.getArg1());

		articleReplyService.writeReply(articleNum, reply);

		System.out.println("댓글이 등록되었습니다.");
	}

}