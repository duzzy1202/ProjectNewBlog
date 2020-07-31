package com.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.dto.Article;
import com.java.blog.dto.ArticleReply;
import com.java.blog.dto.CateItem;
import com.java.blog.dto.Chat;
import com.java.blog.dto.Member;
import com.java.blog.dto.Message;
import com.java.blog.util.DBUtil;
import com.java.blog.util.SecSql;

public class MessageDao extends Dao {
	private Connection dbConn;

	public MessageDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public List<Message> getReceivedMessageList(int page, int itemsInAPage, int id) {
		SecSql secSql = new SecSql();
		
		int limitFrom = (page - 1) * itemsInAPage;
		
		secSql.append("SELECT MS.* ");
		secSql.append(", M.nickname AS extra__messageWriter ");
		secSql.append("FROM message AS MS");
		secSql.append("INNER JOIN member AS M");
		secSql.append("ON MS.fromMemberId = M.id");
		secSql.append("WHERE 1");
		secSql.append("AND toMemberId = ? ", id);
		secSql.append("ORDER BY id DESC ");
		secSql.append("LIMIT ?, ? ", limitFrom, itemsInAPage);
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, secSql);
		List<Message> messages = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			messages.add(new Message(row));
		}
		
		
		
		return messages;
	}

	public int getMessagesCount(int id) {
		SecSql secSql = new SecSql();
		
		secSql.append("SELECT COUNT(*) AS cnt ");
		secSql.append("FROM message ");
		secSql.append("WHERE toMemberId = ? ", id );

		int count = DBUtil.selectRowIntValue(dbConn, secSql);
		return count;
	}

	public List<Message> getSentMessageList(int page, int itemsInAPage) {
		SecSql secSql = new SecSql();
		
		int limitFrom = (page - 1) * itemsInAPage;
		
		secSql.append("SELECT MS.* ");
		secSql.append(", M.nickname AS extra__messageReceiver ");
		secSql.append("FROM message AS MS");
		secSql.append("INNER JOIN member AS M");
		secSql.append("ON MS.toMemberId = M.id");
		secSql.append("WHERE 1");
		secSql.append("ORDER BY id DESC ");
		secSql.append("LIMIT ?, ? ", limitFrom, itemsInAPage);
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, secSql);
		List<Message> messages = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			messages.add(new Message(row));
		}
		
		return messages;
	}

	public void sendMessage(String title, String body, int writerId, int receiverId) {
		SecSql secSql = new SecSql();

		secSql.append("INSERT INTO message ");
		secSql.append("SET regDate = NOW()");
		secSql.append(", title = ? ", title);
		secSql.append(", body = ? ", body);
		secSql.append(", fromMemberId = ? ", writerId);
		secSql.append(", toMemberId = ? ", receiverId);
		secSql.append(", readStatus = '0' ");
		secSql.append(", delStatus = '0' ");

		int id = DBUtil.insert(dbConn, secSql);
	}
}
