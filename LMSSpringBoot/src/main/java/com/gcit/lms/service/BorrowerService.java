package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.Date;
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
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;

@RestController
public class BorrowerService extends BaseController {
	
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
	
	@Autowired
	BorrowerDAO brdao;
	
	@Transactional
	@RequestMapping(value="/borrower/saveBookLoan",method=RequestMethod.POST,consumes="application/json")
	public void saveBookLoan(@RequestBody BookLoans bookLoan) throws SQLException
	{
			try {
				bldao.addBookLoans(bookLoan);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/borrower/returnBookLoan",method=RequestMethod.PUT,consumes="application/json")
	public void returnBookLoan(@RequestBody BookLoans bookLoan) throws SQLException //this method updates dateIn 
	{
			try {
				bldao.returnBookLoans(bookLoan);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/borrower/loanBookCopies",method=RequestMethod.PUT,consumes="application/json")
	public void loanBookCopies(@RequestBody BookCopies bookCopy) throws SQLException //this method updates noOfCopies
	{
			try {
				bcdao.LoanBookCopies(bookCopy);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/borrower/readBookLoanUserBranch",method=RequestMethod.GET, produces="application/json")
	public List<BookLoans> ReadBookLoansByUserBranch(@RequestParam("branchId") Integer branchId, @RequestParam("cardNo") Integer cardNo) throws SQLException
	{
			try {
				List<BookLoans> bookLoans=bldao.ReadBookLoansByUserBranch(branchId, cardNo);
				return bookLoans;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
	@Transactional
	@RequestMapping(value="/borrower/readBookByBookId",method=RequestMethod.GET, produces="application/json")
	public List<Book> ReadBookByBookID(@RequestParam Integer bookId) throws SQLException  //for return book
	{
			try {
				List<Book> books=bdao.ReadBooksByBookID(bookId);
				return books;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	@Transactional
	@RequestMapping(value="/borrower/readBorrower",method=RequestMethod.GET, produces="application/json")
	public List<Borrower> readBorrower() throws SQLException
	{
			try {
				List<Borrower> borrowers=brdao.ReadAllBorrowers();
				return borrowers;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	@Transactional
	@RequestMapping(value="/borrower/changeDueDate",method=RequestMethod.PUT, consumes="application/json")
	public void changeDueDate(@RequestBody BookLoans bookloan) throws SQLException
	{
			try {
				bldao.changeDueDate(bookloan);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/borrower/updateBorrower",method=RequestMethod.PUT, consumes="application/json")
	public void updateBorrower(@RequestBody Borrower borrower) throws SQLException
	{
			try {
				brdao.updateBorrower(borrower);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/borrower/deleteBorrower",method=RequestMethod.DELETE, consumes="application/json")
	public void deleteBorrower(@RequestBody Borrower borrower) throws SQLException
	{
			try {
				brdao.deleteBorrower(borrower);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value="/borrower/saveBorrower",method=RequestMethod.POST, consumes="application/json")
	public void saveBorrower(@RequestBody Borrower borrower) throws SQLException
	{
			try {
				brdao.addBorrower(borrower);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

}
