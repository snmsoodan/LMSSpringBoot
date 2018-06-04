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
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

@RestController
public class LibraryService extends BaseController{
	

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
	BookCopiesDAO bcdao;
	
	@Autowired
	LibraryBranchDAO lbdao;
	
	
	@Transactional
	@RequestMapping(value="/library/saveBookCopy",method=RequestMethod.POST,consumes="application/json")
	public void saveBookCopy(@RequestBody BookCopies bookCopy) throws SQLException
	{
			try {
				bcdao.addBookCopies(bookCopy);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	@Transactional
	@RequestMapping(value="/library/readBookCopiesById",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public List<BookCopies> readBookCopiesById(@RequestBody BookCopies bookCopy) throws SQLException
	{
			try {
				List<BookCopies> bookCopies=bcdao.ReadBookCopiesById(bookCopy.getBookId(), bookCopy.getBranchId());
				return bookCopies;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	@Transactional
	@RequestMapping(value="/library/updateBookCopies",method=RequestMethod.PUT,consumes="application/json")
	public void updateBookCopies(@RequestBody BookCopies bookCopy) throws SQLException
	{
			try {
				bcdao.updateBookCopies(bookCopy);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/library/updateBookCopies2",method=RequestMethod.PUT,consumes="application/json")
	public void updateBookCopies2(@RequestBody BookCopies bookCopy) throws SQLException
	{
			try {
				int noOfCopies=bcdao.ReadBookCopiesById(bookCopy.getBookId(), bookCopy.getBranchId()).get(0).getNoOfCopies();
				System.out.println(noOfCopies);
				noOfCopies+=1;
				bookCopy.setNoOfCopies(noOfCopies);
				
				bcdao.updateBookCopies(bookCopy);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/library/updateLibraryBranch",method=RequestMethod.PUT,consumes="application/json")
	public void updateLibraryBranch(@RequestBody LibraryBranch libraryBranch) throws SQLException
	{
			try {
				lbdao.updateLibraryBranch(libraryBranch);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	@Transactional
	@RequestMapping(value="/library/deleteLibraryBranch",method=RequestMethod.POST,consumes="application/json")
	public void deleteLibraryBranch(@RequestBody LibraryBranch libraryBranch) throws SQLException
	{
			try {
				lbdao.deleteLibraryBranch(libraryBranch);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	@Transactional
	@RequestMapping(value="/library/saveLibraryBranch",method=RequestMethod.POST,consumes="application/json")
	public void saveLibraryBranch(@RequestBody LibraryBranch libraryBranch) throws SQLException
	{
			try {
				lbdao.addLibraryBranch(libraryBranch);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	@Transactional
	@RequestMapping(value="/library/readLibraryBranch",method=RequestMethod.GET,produces="application/json")
	public List<LibraryBranch> readLibraryBranch() throws SQLException
	{
			try {
				List<LibraryBranch> libraryBranches=lbdao.ReadAllLibraryBranches();
				
				//get the books in the branch
				for(LibraryBranch branch: libraryBranches)
				{
					List<Book> books=bdao.ReadBooksByBranchID(branch.getBranchId());
					
					for(Book book: books)
					{
						List<BookCopies> bookCopies=bcdao.ReadBookCopiesById(book.getBookId(), branch.getBranchId());
						book.setNoOfCopies(bookCopies.get(0).getNoOfCopies());
					}
					
					
					branch.setBooks(books);
				}
				
				
				return libraryBranches;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
	
	@Transactional
	@RequestMapping(value="/library/readLibraryBranchesByName",method=RequestMethod.GET,produces="application/json")
	public List<LibraryBranch> readLibraryBranchesByName(@RequestParam String name) throws SQLException
	{
			try {
				List<LibraryBranch> libraryBranches=new ArrayList<LibraryBranch>();
				
				if(name.equals("undefined"))
				{
					libraryBranches=lbdao.ReadAllLibraryBranches();
				}
				else {
					libraryBranches=lbdao.readBranchesByName(name);
				}
				
				//get the books in the branch
				for(LibraryBranch branch: libraryBranches)
				{
					List<Book> books=bdao.ReadBooksByBranchID(branch.getBranchId());
					branch.setBooks(books);
				}
				
				
				return libraryBranches;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		return null;
	}

}
