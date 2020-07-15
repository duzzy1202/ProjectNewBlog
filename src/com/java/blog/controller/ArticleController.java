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
		// 필요없다면 지워도 된다.
	}

	public String doAction() {
		switch (actionMethodName) {
		case "list":
			return doActionList(req, resp);
		case "detail":
			return doActionDetail(req, resp, 0);
		case "write":
			return doActionWrite(req, resp);
		case "doWrite":
			return doActionDoWrite(req, resp);
		case "update":
			return doActionUpdate(req, resp);
		case "doUpdate":
			return doActionDoUpdate(req, resp);
		case "delete":
			return doActionDelete(req, resp);
		case "writeReply":
			return doActionWriteReply(req, resp);
		case "updateReply":
			return doActionUpdateReply(req, resp);
		case "deleteReply":
			return doActionDeleteReply(req, resp);
		}

		return "";
	}

	private String doActionDeleteReply(HttpServletRequest req, HttpServletResponse resp) {
		int replyId = Util.getInt(req, "replyId");
		int articleId = Util.getInt(req, "articleId");
		
		articleService.deleteReply(replyId);
		
		return "html:<script> alert('댓글이 삭제되었습니다.'); location.replace('detail?id=" + articleId + "'); </script>";
	}

	private String doActionUpdateReply(HttpServletRequest req, HttpServletResponse resp) {
		int replyId = Util.getInt(req, "replyId");
		int articleId = Util.getInt(req, "articleId");
		String replyBody = req.getParameter("replyBody");
		
		articleService.UpdateReply(replyId, replyBody);
		
		return "html:<script> alert('댓글이 수정되었습니다.'); location.replace('detail?id=" + articleId + "'); </script>";
	}

	private String doActionWriteReply(HttpServletRequest req, HttpServletResponse resp) {
		int articleId = Util.getInt(req, "articleId");
		String replyBody = req.getParameter("replyBody");
		int replyMemberId = Util.getInt(req, "replyMemberId");
		
		articleService.writeReply(articleId, replyBody, replyMemberId);
		
		return "html:<script> alert('댓글이 등록되었습니다.'); location.replace('detail?id=" + articleId + "'); </script>";
	}

	private String doActionDelete(HttpServletRequest req, HttpServletResponse resp) {
		int cateItemId = Util.getInt(req, "cateItemId");
		int articleId = Util.getInt(req, "articleId");
		
		articleService.deleteArticle(articleId);
		
		return "html:<script> alert('" + articleId + "번 게시물이 삭제되었습니다.'); location.replace('list?cateItemId=" + cateItemId + "'); </script>";
	}

	private String doActionDoUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int cateItemId = Util.getInt(req, "cateItem");
		int articleId = Util.getInt(req, "articleId");

		int id = articleService.updateArticle(cateItemId, title, body, articleId);

		return "html:<script> alert('" + articleId + "번 게시물이 수정되었습니다.'); location.replace('detail?id=" + articleId + "'); </script>";
	}

	private String doActionUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("articleTitle");
		String body = req.getParameter("articleBody");
		int id = Util.getInt(req, "articleId");
		int cateItemId = Util.getInt(req, "cateItemId");
		
		req.setAttribute("id", id);
		req.setAttribute("title", title);
		req.setAttribute("body", body);
		req.setAttribute("cateItemId", cateItemId);
		
		return "article/update.jsp";
	}

	private String doActionWrite(HttpServletRequest req, HttpServletResponse resp) {	
		
		HttpSession session = req.getSession();
		if ( session.getAttribute("loggedInMemberId") == null ) {
			return "html:<script> alert('게시물을 작성하려면 로그인이 필요합니다.'); location.replace('/blog/s/member/login'); </script>";
		}
		
		int currentMemberId = (int)session.getAttribute("loggedInMemberId");
		Member currentMember = articleService.getMemberById(currentMemberId);
		
		req.setAttribute("currentMember", currentMember);
		
		List<CateItem> cateItems = articleService.getForPrintCateItems();
		req.setAttribute("cateItems", cateItems);
		
		return "article/write.jsp";
	}
	
	private String doActionDoWrite(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int cateItemId = Util.getInt(req, "cateItem");
		
		int writerId = Util.getInt(req, "writerId");

		int id = articleService.insertWrittenArticle(cateItemId, title, body, writerId);

		return "html:<script> alert('" + id + "번 게시물이 생성되었습니다.'); location.replace('detail?id=" + id + "'); </script>";
	}

	private String doActionDetail(HttpServletRequest req, HttpServletResponse resp, int replyUpdateMode) {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}
		
		int id = Util.getInt(req, "id");
		
		/* 댓글 페이징 */
		int page = 1;
		if (!Util.empty(req, "page") && Util.isNum(req, "page")) {
			page = Util.getInt(req, "page");
		}
		
		int itemsInAPage = 5;
		int totalCount = articleService.getForPrintListReplysCount(id);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);
		
		/* 조회수 기능 */
		articleService.increaseHit(id);

		/* 게시물 가져오기 */
		Article article = articleService.getForPrintArticle(id);
		req.setAttribute("article", article);
		
		/* 게시물 작성자 가져오기 */
		int memberId = article.getMemberId();
		Member writer = articleService.getMemberById(memberId);
		req.setAttribute("writer", writer);
		
		/* 댓글 리스트 가져오기 */
		List<ArticleReply> replys = articleService.getForPrintListReplys(id, itemsInAPage, page);
		req.setAttribute("replys", replys);
		
		/* 댓글 작성자들 가져오기 */
		List<Member> replyMembers = articleService.getReplyMembersByReplysList(replys);
		req.setAttribute("replyMembers", replyMembers);
		
		/* 현재 로그인된 이용자 가져오기 */
		HttpSession session = req.getSession();
		
		int currentMemberId = -1;
		Member currentMember = null;
		
	 	if ( session.getAttribute("loggedInMemberId") != null ) {
	 		currentMemberId = (int)session.getAttribute("loggedInMemberId");
	 		currentMember = articleService.getMemberById(currentMemberId);
	 	}
	 	req.setAttribute("currentMember", currentMember);
	 	
	 	/* 댓글 수정 모드  */
	 	int replyUpdateModeValue = replyUpdateMode;
	 	req.setAttribute("replyUpdateMode", replyUpdateModeValue);

		return "article/detail.jsp";
	}

	private String doActionList(HttpServletRequest req, HttpServletResponse resp) {
		int page = 1;

		if (!Util.empty(req, "page") && Util.isNum(req, "page")) {
			page = Util.getInt(req, "page");
		}

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) {
			cateItemId = Util.getInt(req, "cateItemId");
		}
		
		String cateItemName = "전체";
		
		if ( cateItemId != 0 ) {
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
		return "article/list.jsp";
	}
}
