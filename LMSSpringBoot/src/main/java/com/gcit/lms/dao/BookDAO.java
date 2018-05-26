package com.gcit.lms.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.gcit.lms.entity.Book;

@Component
public class BookDAO extends BaseDAO<Book> implements ResultSetExtractor<List<Book>>{
	

	public void addBook(Book book) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update("insert into tbl_book (title,pubId) values(?,?)",new Object[] {book.getTitle(),book.getPublisher().getPublisherId()});
		
	}
	
	public Integer addBookWithId(Book book) throws SQLException, ClassNotFoundException
	{
//		return saveWithId("insert into tbl_book (title,pubId) values(?,?)",new Object[] {book.getTitle(),book.getPublisher().getPublisherId()});
		String insertSql="insert into tbl_book (title,pubId) values(?,?)";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		String id_column = "bookId";
		mySqlTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertSql, new String[] { id_column });
			ps.setString(1, book.getTitle());
			return ps;
		}, keyHolder);
		
		BigDecimal id=(BigDecimal) keyHolder.getKeys().get(id_column);
		return id.intValue();
	}
	
	
	public void addBookAuthors(Integer bookId, Integer authorId) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update("insert into tbl_book_authors  values(?,?)",new Object[] {bookId,authorId});
	}
	
	public void addBookGenres(Integer bookId, Integer genreId) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update("insert into tbl_book_genres  values(?,?)",new Object[] {genreId,bookId});
	}
	
	
	public void updateBook(Book book) throws SQLException, ClassNotFoundException
	{
		mySqlTemplate.update("update tbl_book set title = ?  where bookId = ?",new Object[] {book.getTitle(),book.getBookId()});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException
	{
		
		mySqlTemplate.update("delete from tbl_book where bookId=?",new Object[] {book.getBookId()});	
	}
	
	public List<Book> ReadAllBooks() throws ClassNotFoundException, SQLException
	{
		return mySqlTemplate.query("select * from tbl_book",this);	
	}
	
	public List<Book> ReadBooksByBookID(Integer bookId) throws ClassNotFoundException, SQLException //added for borrower return
	{
		return mySqlTemplate.query("select * from tbl_book where bookId=?",new Object[] {bookId},this);	
	}
	
	public List<Book> extractData(ResultSet rs) throws SQLException
	{
		
		List<Book> books=new ArrayList<>();
		while(rs.next()){
			Book book=new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			books.add(book);
		}
		return books;
	}
	
	
}