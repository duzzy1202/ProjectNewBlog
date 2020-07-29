package com.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.blog.dto.Article;
import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.CateItem;
import com.java.blog.dto.Member;
import com.java.blog.util.Util;

public class ArticleController extends Controller {

	public ArticleController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	public void beforeAction() {
		super.beforeAction();
		// 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
	}

	public String doAction() {
		switch (actionMethodName) {
		case "list":
			return doActionList();
		case "detail":
			return doActionDetail();
		case "write":
			return doActionWrite();
		case "doWrite":
			return doActionDoWrite();
		case "update":
			return doActionUpdate();
		case "doUpdate":
			return doActionDoUpdate();
		case "delete":
			return doActionDelete();
		case "writeReply":
			return doActionWriteReply();
		case "updateReply":
			return doActionUpdateReply();
		case "deleteReply":
			return doActionDeleteReply();
		}

		return "";
	}

	@Override
	public String getControllerName() {
		return "article";
	}

	private String doActionDeleteReply() {
		int replyId = Util.getInt(req, "replyId");
		int articleId = Util.getInt(req, "articleId");

		articleService.deleteReply(replyId);

		return "html:<script> alert('댓글이 삭제되었습니다.'); location.replace('detail?id=" + articleId + "'); </script>";
	}

	private String doActionUpdateReply() {
		int replyId = Util.getInt(req, "replyId");
		int articleId = Util.getInt(req, "articleId");
		String replyBody = req.getParameter("replyBody");

		articleService.UpdateReply(replyId, replyBody);

		return "html:<script> alert('댓글이 수정되었습니다.'); location.replace('detail?id=" + articleId + "'); </script>";
	}

	private String doActionWriteReply() {
		int articleId = Util.getInt(req, "articleId");
		String replyBody = req.getParameter("replyBody");
		int replyMemberId = Util.getInt(req, "replyMemberId");

		articleService.writeReply(articleId, replyBody, replyMemberId);

		return "html:<script> alert('댓글이 등록되었습니다.'); location.replace('detail?id=" + articleId + "'); </script>";
	}

	private String doActionDelete() {
		int articleId = Util.getInt(req, "id");

		/* 게시물 가져오기 */
		Article article = articleService.getForPrintArticle(articleId);
		int cateItemId = article.getCateItemId();

		articleService.deleteArticle(articleId);

		return "html:<script> alert('" + articleId + "번 게시물이 삭제되었습니다.'); location.replace('list?cateItemId="
				+ cateItemId + "'); </script>";
	}

	private String doActionDoUpdate() {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int cateItemId = Util.getInt(req, "cateItem");
		int articleId = Util.getInt(req, "articleId");

		int id = articleService.updateArticle(cateItemId, title, body, articleId);

		return "html:<script> alert('" + articleId + "번 게시물이 수정되었습니다.'); location.replace('detail?id=" + articleId
				+ "'); </script>";
	}

	private String doActionUpdate() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		/* 게시물 가져오기 */
		Article article = articleService.getForPrintArticle(id);
		req.setAttribute("article", article);

		return "article/update.jsp";
	}

	private String doActionWrite() {

		int currentMemberId = (int) session.getAttribute("loggedInMemberId");
		Member currentMember = articleService.getMemberById(currentMemberId);

		req.setAttribute("currentMember", currentMember);

		if (currentMember.getMailAuthStatus() == 0) {
			return "html:<script> alert('이메일 인증 후에 작성하실수 있습니다.'); history.back(); </script>";
		}

		List<CateItem> cateItems = articleService.getForPrintCateItems();
		req.setAttribute("cateItems", cateItems);

		return "article/write.jsp";
	}

	private String doActionDoWrite() {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int cateItemId = Util.getInt(req, "cateItem");

		int writerId = Util.getInt(req, "writerId");

		int id = articleService.insertWrittenArticle(cateItemId, title, body, writerId);

		return "html:<script> alert('" + id + "번 게시물이 생성되었습니다.'); location.replace('detail?id=" + id + "'); </script>";
	}

	private String doActionDetail() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		/* 댓글 페이징 */
		/*
		 * int page = 1; if (!Util.empty(req, "page") && Util.isNum(req, "page")) { page
		 * = Util.getInt(req, "page"); }
		 * 
		 * int itemsInAPage = 5; int totalCount =
		 * articleService.getForPrintListReplysCount(id); int totalPage = (int)
		 * Math.ceil(totalCount / (double) itemsInAPage);
		 * 
		 * req.setAttribute("totalCount", totalCount); req.setAttribute("totalPage",
		 * totalPage); req.setAttribute("page", page);
		 */

		/* 조회수 기능 */
		articleService.increaseHit(id);

		/* 게시물 가져오기 */
		Article article = articleService.getForPrintArticle(id);
		req.setAttribute("article", article);

		/* 댓글 리스트 가져오기 */
		List<ArticleReply> replys = articleService.getForPrintListReplys(id);
		req.setAttribute("replys", replys);

		/* 현재 로그인된 이용자 가져오기 */
		/*
		 * head.jspf 의 loggedInMember로 교체 HttpSession session = req.getSession();
		 * 
		 * int currentMemberId = -1; Member currentMember = null;
		 * 
		 * if ( session.getAttribute("loggedInMemberId") != null ) { currentMemberId =
		 * (int)session.getAttribute("loggedInMemberId"); currentMember =
		 * articleService.getMemberById(currentMemberId); }
		 * req.setAttribute("currentMember", currentMember);
		 */

		return "article/detail.jsp";
	}

	private String doActionList() {
		int page = 1;

		if (!Util.empty(req, "page") && Util.isNum(req, "page")) {
			page = Util.getInt(req, "page");
		}

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) {
			cateItemId = Util.getInt(req, "cateItemId");
		}

		String cateItemName = "전체";

		if (cateItemId != 0) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}
		req.setAttribute("cateItemName", cateItemName);

		String searchKeywordType = "";

		if (!Util.empty(req, "searchKeywordType")) {
			searchKeywordType = Util.getString(req, "searchKeywordType");
		}

		String searchKeyword = "";

		if (!Util.empty(req, "searchKeyword")) {
			searchKeyword = Util.getString(req, "searchKeyword");
		}

		int itemsInAPage = 10;
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, itemsInAPage, cateItemId,
				searchKeywordType, searchKeyword);
		req.setAttribute("articles", articles);

		List<ArticleReply> replys = articleService.getAllReplysCount();
		req.setAttribute("replysCount", replys);

		return "article/list.jsp";
	}
}
