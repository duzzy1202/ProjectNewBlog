package com.Service.practice;

import com.Dao.practice.ArticleReplyDao;
import com.Dto.practice.ArticleReply;
import com.Main.practice.Factory;

public class ArticleReplyService {
	ArticleReplyDao articleReplyDao;
	
	public ArticleReplyService() {
		this.articleReplyDao = Factory.getArticleReplyDao();
	}
	
	public void writeReply(int articleNum, String reply) {
		ArticleReply articleReply = new ArticleReply(articleNum, reply);
		articleReplyDao.writeReply(articleReply);
	}

	public int Delete(int replyNum) {
		return articleReplyDao.Delete(replyNum);
	}

	public int ModifyReply(int replyNum, String body) {
		return articleReplyDao.modifyReply(replyNum, body);
	}
	
}
