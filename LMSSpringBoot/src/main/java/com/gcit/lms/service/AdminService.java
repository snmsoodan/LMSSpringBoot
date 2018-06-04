package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
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
	
	@Autowired
	BorrowerDAO brdao;
	
	@Autowired
	LibraryBranchDAO lbdao;
	
	@Transactional
	@RequestMapping(value="/admin/saveAuthor", method=RequestMethod.POST,consumes="application/json")
	public void saveAuthor(@RequestBody Author author) throws SQLException
	{
			try {
				int authorId=adao.addAuthorWithId(author);
				
				for(Book b : author.getBooks())
				{
					bdao.addBookAuthors(b.getBookId(), authorId);
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/admin/saveGenre", method=RequestMethod.POST,consumes="application/json")
	public void saveGenre(@RequestBody Genre genre) throws SQLException
	{
			try {
				int genreId=gndao.addGenreWithId(genre);
				
				for(Book b : genre.getBooks())
				{
					bdao.addBookGenres(b.getBookId(), genreId);
				}
				
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
				//get the books related to the publisher
				for(Publisher pub:publishers)
				{
					List<Book> books=bdao.ReadBooksByPubID(pub.getPublisherId());
					pub.setBooks(books);
				}
				
				return publishers;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	
	@Transactional
	@RequestMapping(value="/admin/readPublishersByName",method=RequestMethod.GET,produces="application/json")
	public List<Publisher> readPublisherByNme(@RequestParam String name) throws SQLException
	{
			try {
				
				List<Publisher> publishers=new ArrayList<Publisher>();
				if(name.equals("undefined"))
				{
					publishers=pdao.ReadAllPublishers();
				}
				else {
					publishers=pdao.ReadAllPublishersByName(name);
				}
				
				//get the books related to the publisher
				for(Publisher pub:publishers)
				{
					List<Book> books=bdao.ReadBooksByPubID(pub.getPublisherId());
					pub.setBooks(books);
				}
				
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
				//get the books related to the author
				for(Author auth:authors)
				{
					List<Book> books=bdao.ReadBooksByAuthID(auth.getAuthorId());
					auth.setBooks(books);
				}
				
				return authors;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	

	
	@Transactional
	@RequestMapping(value="/admin/readAuthorsByName",method=RequestMethod.GET,produces="application/json")
	public List<Author> readAuthorsByName(@RequestParam String authorName) throws SQLException
	{
			try {
				
				List<Author> authors=new ArrayList<Author>();
				if(authorName.equals("undefined"))
				{
					authors=adao.ReadAllAuthors();
				}
				else {
					 authors=adao.readAuthorsByName(authorName);
				}
				
				//get the books related to the author
				for(Author auth:authors)
				{
					List<Book> books=bdao.ReadBooksByAuthID(auth.getAuthorId());
					auth.setBooks(books);
				}
				
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
				
				for(BookLoans bookLoan:bookLoans)
				{
					List<Book> books=bdao.ReadBooksByBookID(bookLoan.getBookId());
					bookLoan.setBook(books.get(0));
					
					List<Borrower> borrowers=brdao.ReadAllBorrowerById(bookLoan.getCardNo());
					bookLoan.setBorrower(borrowers.get(0));
					
					List<LibraryBranch> libraryBranch=lbdao.ReadLibraryBranchesById(bookLoan.getBranchId());
					bookLoan.setLibraryBranch(libraryBranch.get(0));
				}
			
				return bookLoans;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	@Transactional
	@RequestMapping(value="/admin/readBookLoansByUserId",method=RequestMethod.GET,produces="application/json")
	public List<BookLoans> readBookLoansByUserId(@RequestParam Integer cardNo) throws SQLException
	{
			try {
				List<BookLoans> bookLoans=bldao.ReadBookLoansByUserId(cardNo);
				
				for(BookLoans bookLoan:bookLoans)
				{
					List<Book> books=bdao.ReadBooksByBookID(bookLoan.getBookId());
					bookLoan.setBook(books.get(0));
					
					List<Borrower> borrowers=brdao.ReadAllBorrowerById(bookLoan.getCardNo());
					bookLoan.setBorrower(borrowers.get(0));
					
					List<LibraryBranch> libraryBranch=lbdao.ReadLibraryBranchesById(bookLoan.getBranchId());
					bookLoan.setLibraryBranch(libraryBranch.get(0));
				}
			
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
				//get the books related to the genre
				for(Genre genre:genres)
				{
					List<Book> books=bdao.ReadBooksByGenreID(genre.getGenreId());
					genre.setBooks(books);
				}
				
				return genres;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	@Transactional
	@RequestMapping(value="/admin/readGenresByName",method=RequestMethod.GET,produces="application/json")
	public List<Genre> readGenresByName(@RequestParam String name) throws SQLException
	{
			try {
				
				List<Genre> genres=new ArrayList<Genre>();
				if(name.equals("undefined"))
				{
					genres=gndao.ReadAllGenres();
				}
				else {
					genres=gndao.readGenresByName(name);
				}
				
				
				//get the books related to the genre
				for(Genre genre:genres)
				{
					List<Book> books=bdao.ReadBooksByGenreID(genre.getGenreId());
					genre.setBooks(books);
				}
				
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
				for(Book book:books)
				{
					//find all authors related to the book
					List<Author> authors=adao.ReadAuthorsByBookId(book.getBookId());
					book.setAuthors(authors);
					
					//find all genres related to the book
					List<Genre> genres=gndao.ReadGenresByBookId(book.getBookId());
					book.setGenres(genres);
					
					//find all publisher related to the book
					List<Publisher> publishers=pdao.ReadPublisherByBookId(book.getBookId());
					book.setPublisher(publishers.get(0));
					
				}
				
				
				return books;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	
	@Transactional
	@RequestMapping(value="/admin/readBooksByName",method=RequestMethod.GET,produces="application/json")
	public List<Book> readBookByName(@RequestParam String name) throws SQLException
	{
			try {
//				List<Book> books=bdao.ReadAllBooks();
				
				List<Book> books=new ArrayList<Book>();
				if(name.equals("undefined"))
				{
					books=bdao.ReadAllBooks();
				}
				else {
					books=bdao.readBooksByName(name);
				}
				
				for(Book book:books)
				{
					//find all authors related to the book
					List<Author> authors=adao.ReadAuthorsByBookId(book.getBookId());
					book.setAuthors(authors);
					
					//find all genres related to the book
					List<Genre> genres=gndao.ReadGenresByBookId(book.getBookId());
					book.setGenres(genres);
					
					//find all publisher related to the book
					List<Publisher> publishers=pdao.ReadPublisherByBookId(book.getBookId());
					book.setPublisher(publishers.get(0));
					
				}
				
				
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
	@RequestMapping(value="/admin/deletePublisher",method=RequestMethod.POST,produces="application/json")
	public void deletePublisher(@RequestBody Publisher publisher) throws SQLException
	{
			try {
				pdao.deletePublisher(publisher);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/admin/deleteBook",method=RequestMethod.POST,produces="application/json")
	public void deleteBook(@RequestBody Book book) throws SQLException
	{
			try {
				bdao.deleteBook(book);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
//	@Transactional
//	@RequestMapping(value="/admin/deleteAuthor",method=RequestMethod.DELETE,produces="application/json")
//	public void deleteAuthor(@RequestParam Integer authorId) throws SQLException
//	{
//		System.out.println(authorId);
//			try {
//				adao.deleteAuthor(authorId);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//	}
	
	@Transactional
	@RequestMapping(value="/admin/deleteAuthor",method=RequestMethod.POST,produces="application/json")
	public void deleteAuthor(@RequestBody Author author) throws SQLException
	{
			try {
				adao.deleteAuthor(author);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/admin/deleteGenre",method=RequestMethod.POST,produces="application/json")
	public void deleteGenre(@RequestBody Genre genre) throws SQLException
	{
			try {
				gndao.deleteGenre(genre);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	

	
}
