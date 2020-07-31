package com.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.java.blog.dao.MessageDao;
import com.java.blog.dto.Article;
import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.CateItem;
import com.java.blog.dto.Chat;
import com.java.blog.dto.Member;
import com.java.blog.dto.Message;

public class MessageService extends Service {

	private MessageDao messageDao;

	public MessageService(Connection dbConn) {
		messageDao = new MessageDao(dbConn);
	}

	public List<Message> getMessageList(int page, int itemsInAPage) {
		return messageDao.getMessageList(page, itemsInAPage);
	}

	public int getMessagesCount(int id) {
		return messageDao.getMessagesCount(id);
	}

}
