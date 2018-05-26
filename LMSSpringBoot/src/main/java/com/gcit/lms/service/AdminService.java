package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;


@RestController
public class AdminService extends BaseController {

	@Autowired
	AuthorDAO adao;
	
	@Autowired
	GenreDAO gndao;
	
	@Autowired
	BookDAO bdao;
	
	@Autowired
	PublisherDAO pdao;
	
	@Autowired
	BookLoansDAO bldao;
	
	@Transactional
	@RequestMapping(value="/admin/saveAuthor", method=RequestMethod.POST,consumes="application/json")
	public void saveAuthor(@RequestBody Author author) throws SQLException
	{
			try {
				adao.addAuthor(author);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/admin/saveGenre", method=RequestMethod.POST,consumes="application/json")
	public void saveGenre(@RequestBody Genre genre) throws SQLException
	{
			try {
				gndao.addGenre(genre);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	@Transactional
	@RequestMapping(value="/admin/saveBook",method=RequestMethod.POST,consumes="application/json")
	public void saveBook(@RequestBody Book book) throws SQLException
	{
			try {
				Integer bookId=bdao.addBookWithId(book);
				
				for(Author a : book.getAuthors())
				{
					bdao.addBookAuthors(bookId, a.getAuthorId());
				}
				
				for(Genre g: book.getGenres())
				{
					bdao.addBookGenres(bookId, g.getGenreId());
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	

	
	@Transactional
	@RequestMapping(value="/admin/savePublisher",method=RequestMethod.POST,consumes="application/json")
	public void savePublisher(@RequestBody Publisher publisher) throws SQLException
	{
			try {
				pdao.addPublisher(publisher);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/admin/readPublishers",method=RequestMethod.GET,produces="application/json")
	public List<Publisher> readPublisher() throws SQLException
	{
			try {
				List<Publisher> publishers=pdao.ReadAllPublishers();
				return publishers;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	@Transactional
	@RequestMapping(value="/admin/readAuthors",method=RequestMethod.GET,produces="application/json")
	public List<Author> readAuthor() throws SQLException
	{
			try {
				List<Author> authors=adao.ReadAllAuthors();
				return authors;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	

	

	
	@Transactional
	@RequestMapping(value="/admin/readBookLoans",method=RequestMethod.GET,produces="application/json")
	public List<BookLoans> readAllBookLoans() throws SQLException
	{
			try {
				List<BookLoans> bookLoans=bldao.ReadAllBookLoans();
				return bookLoans;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	

	@Transactional
	@RequestMapping(value="/admin/readGenres",method=RequestMethod.GET,produces="application/json")
	public List<Genre> readGenre() throws SQLException
	{
			try {
				List<Genre> genres=gndao.ReadAllGenres();
				return genres;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	@Transactional
	@RequestMapping(value="/admin/readBooks",method=RequestMethod.GET,produces="application/json")
	public List<Book> readBook() throws SQLException
	{
			try {
				List<Book> books=bdao.ReadAllBooks();
				return books;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	@Transactional
	@RequestMapping(value="/admin/updatePublisher",method=RequestMethod.PUT,produces="application/json")
	public void updatePublisher(@RequestBody Publisher publisher) throws SQLException
	{
			try {
				pdao.updatePublisher(publisher);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	

	@Transactional
	@RequestMapping(value="/admin/updateGenre",method=RequestMethod.PUT,produces="application/json")
	public void updateGenre(@RequestBody Genre genre) throws SQLException
	{
			try {
				gndao.updateGenre(genre);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	

	
	
	@Transactional
	@RequestMapping(value="/admin/updateAuthor",method=RequestMethod.PUT,produces="application/json")
	public void updateAuthor(@RequestBody Author author) throws SQLException
	{
			try {
				adao.updateAuthor(author);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	

	@Transactional
	@RequestMapping(value="/admin/updateBook",method=RequestMethod.PUT,produces="application/json")
	public void updateBook(@RequestBody Book book) throws SQLException
	{
			try {
				bdao.updateBook(book);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	

	@Transactional
	@RequestMapping(value="/admin/deletePublisher",method=RequestMethod.DELETE,produces="application/json")
	public void deletePublisher(@RequestBody Publisher publisher) throws SQLException
	{
			try {
				pdao.deletePublisher(publisher);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/admin/deleteBook",method=RequestMethod.DELETE,produces="application/json")
	public void deleteBook(@RequestBody Book book) throws SQLException
	{
			try {
				bdao.deleteBook(book);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/admin/deleteAuthor",method=RequestMethod.DELETE,produces="application/json")
	public void deleteAuthor(@RequestBody Author author) throws SQLException
	{
			try {
				adao.deleteAuthor(author);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/admin/deleteGenre",method=RequestMethod.DELETE,produces="application/json")
	public void deleteGenre(@RequestBody Genre genre) throws SQLException
	{
			try {
				gndao.deleteGenre(genre);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	

	
}
