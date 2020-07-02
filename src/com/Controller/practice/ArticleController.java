package com.Controller.practice;

import java.util.List;

import com.Dto.practice.Article;
import com.Dto.practice.ArticleReply;
import com.Dto.practice.CateItem;
import com.Main.practice.Factory;
import com.Main.practice.Request;
import com.Service.practice.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Factory.getArticleService();
	}

	public void doAction(Request reqeust) {
		if (reqeust.getActionName().equals("list")) {
			actionList(reqeust);
		} else if (reqeust.getActionName().equals("write")) {
			actionWrite(reqeust);
		} else if (reqeust.getActionName().equals("changeBoard")) {
			actionChangeBoard(reqeust);
		} else if (reqeust.getActionName().equals("currentBoard")) {
			actionCurrentBoard(reqeust);
		} else if (reqeust.getActionName().equals("delete")) {
			actionDelete(reqeust);
		} else if (reqeust.getActionName().equals("detail")) {
			actionDetail(reqeust);
		}
	}

	private void actionDetail(Request reqeust) {
		if (reqeust.getArg1() == null) {
			System.out.println("게시물 번호를 입력해주십시오.");
		} 
		int artNum = Integer.parseInt(reqeust.getArg1());
		List<Article> articles = articleService.getArticlesByBoardCode(Factory.getSession().getCurrentBoard().getCode());
		if (articles.size() < artNum) {
			System.out.println("존재하지 않는 게시물입니다.");
		}
		else {
			Article article = articleService.Detail(artNum);
			List<ArticleReply> articleReplys = articleService.getArticleReplysByArticleId(artNum);

			if (article == null) {
				System.out.println("게시물이 존재하지 않습니다.");
			} else {
				System.out.println("------------------------------");
				System.out.println(article);
				System.out.println("-------------댓글-------------");

				if (articleReplys.size() == 0) {
					System.out.println(" 등록된 댓글이 없습니다.");
				} else {
					for (ArticleReply articleReply : articleReplys) {
						System.out.println(articleReply);
					}
				}
				System.out.println("------------------------------");
			}
		}
	}

	private void actionModify(Request reqeust) {
		System.out.println("수정할 제목 : ");
		String title = Factory.getScanner().nextLine();
		System.out.println("수정할 내용 : ");
		String body = Factory.getScanner().nextLine();

		int id = Integer.parseInt(reqeust.getArg1());
		int modifyId = articleService.modify(title, body, id);

	}

	private void actionDelete(Request reqeust) {
		int id = Integer.parseInt(reqeust.getArg1());
		int deleteId = articleService.Delete(id);

		System.out.printf("%d 번 게시물이 삭제되었습니다.\n", deleteId);
	}

	private void actionList(Request reqeust) {
		CateItem currentCateItem = Factory.getSession().getCurrentBoard();
		List<Article> articles = articleService.getArticlesByCateItemId(currentCateItem.getId());

		System.out.printf("== %s 게시물 리스트 시작 ==\n", currentCateItem.getName());
		for (Article article : articles) {
			System.out.printf("%d, %s, %s\n", article.getId(), article.getRegDate(), article.getTitle());
		}
		System.out.printf("== %s 게시물 리스트 끝 ==\n", currentCateItem.getName());
	}

	private void actionWrite(Request reqeust) {
		System.out.printf("제목 : ");
		String title = Factory.getScanner().nextLine();
		System.out.printf("내용 : ");
		String body = Factory.getScanner().nextLine();

		// 현재 게시판 id 가져오기
		int boardId = Factory.getSession().getCurrentBoard().getId();

		// 현재 로그인한 회원의 id 가져오기
		int newId = articleService.write(boardId, title, body);

		System.out.printf("%d번 글이 생성되었습니다.\n", newId);
	}
}