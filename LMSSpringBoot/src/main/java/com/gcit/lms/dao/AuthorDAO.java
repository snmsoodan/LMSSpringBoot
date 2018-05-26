package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.gcit.lms.entity.Author;

@Component
public class AuthorDAO extends BaseDAO<Author> implements ResultSetExtractor<List<Author>>{
	

	public void addAuthor(Author author) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update("insert into tbl_author (authorName) values(?)",new Object[] {author.getAuthorName()});
	}
	
	public void updateAuthor(Author author) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update("update tbl_author set authorName = ? where authorId = ?",new Object[] {author.getAuthorName(),author.getAuthorId()});
		
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException
	{
		
		mySqlTemplate.update("delete from tbl_author where authorId=?",new Object[] {author.getAuthorId()});	
	}
	
	public List<Author> ReadAllAuthors() throws ClassNotFoundException, SQLException
	{
		
		return mySqlTemplate.query("select * from tbl_author",this);	
	}
	
	public List<Author> readAuthorsByName(String authorName) throws ClassNotFoundException, SQLException
	{
		authorName="%"+authorName+"%";
		
		return mySqlTemplate.query("select * from tbl_author where authorName like ?",new Object[] {authorName},this);	
	}
	
	
	public Author readAuthorsById(Integer authorId) throws ClassNotFoundException, SQLException
	{
		List<Author> authors= mySqlTemplate.query("select * from tbl_author where authorId = ?",new Object[] {authorId},this);
		if(authors!=null)
		{
			return authors.get(0);
		}
		return null;
	}
	
	public List<Author> extractData(ResultSet rs) throws SQLException
	{
		List<Author> authors=new ArrayList<>();
		while(rs.next()){
			Author author=new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}
	
	
	
	

}