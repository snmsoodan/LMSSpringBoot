lmsApp.constant("lmsConstants",{
	GET_ALL_AUTHORS: "http://localhost:8080/lms/admin/readAuthors",
	DELETE_AUTHOR: "http://localhost:8080/lms/admin/deleteAuthor",
	UPDATE_AUTHOR: "http://localhost:8080/lms/admin/updateAuthor",
	ADD_AUTHOR: "http://localhost:8080/lms/admin/saveAuthor",
	GET_AUTHORS_BY_NAME: "http://localhost:8080/lms/admin/readAuthorsByName?authorName=",
	
	
	GET_ALL_BORROWERS: "http://localhost:8080/lms/borrower/readBorrower",
	DELETE_BORROWER: "http://localhost:8080/lms/borrower/deleteBorrower",
	UPDATE_BORROWER: "http://localhost:8080/lms/borrower/updateBorrower",
	ADD_BORROWER: "http://localhost:8080/lms/borrower/saveBorrower",
	GET_BORROWERS_BY_NAME: "http://localhost:8080/lms/borrower/readBorrowerByName?name=",
	
	GET_ALL_PUBLISHERS: "http://localhost:8080/lms/admin/readPublishers",
	DELETE_PUBLISHER: "http://localhost:8080/lms/admin/deletePublisher",
	UPDATE_PUBLISHER: "http://localhost:8080/lms/admin/updatePublisher",
	ADD_PUBLISHER: "http://localhost:8080/lms/admin/savePublisher",
	GET_PUBLISHERS_BY_NAME: "http://localhost:8080/lms/admin/readPublishersByName?name=",
	
	GET_ALL_BRANCHES: "http://localhost:8080/lms/library/readLibraryBranch",
	DELETE_BRANCH: "http://localhost:8080/lms/library/deleteLibraryBranch",
	UPDATE_BRANCH: "http://localhost:8080/lms/library/updateLibraryBranch",
	ADD_BRANCH: "http://localhost:8080/lms/library/saveLibraryBranch",
	GET_BRANCHES_BY_NAME: "http://localhost:8080/lms//library/readLibraryBranchesByName?name=",
	
	GET_ALL_BOOKS: "http://localhost:8080/lms/admin/readBooks",
	DELETE_BOOKS: "http://localhost:8080/lms/admin/deleteBook",
	UPDATE_BOOK: "http://localhost:8080/lms/admin/updateBook",
	ADD_BOOK: "http://localhost:8080/lms/admin/saveBook",
	GET_BOOKS_BY_NAME: "http://localhost:8080/lms/admin/readBooksByName?name=",
	
	
	GET_ALL_GENRES: "http://localhost:8080/lms/admin/readGenres",
	DELETE_GENRES: "http://localhost:8080/lms/admin/deleteGenre",
	UPDATE_GENRE: "http://localhost:8080/lms/admin/updateGenre",
	ADD_GENRE: "http://localhost:8080/lms/admin/saveGenre",
	GET_BOOKS_BY_NAME: "http://localhost:8080/lms/admin/readGenresByName?name=",
	
	GET_ALL_LOANS: "http://localhost:8080/lms/admin/readBookLoans",
	UPDATE_DUE_DATE: "http://localhost:8080/lms/borrower/changeDueDate",
	GET_LOANS_BY_USERID: "http://localhost:8080/lms/admin/readBookLoansByUserId",
	
	GET_LOAN_BY_USER: "http://localhost:8080/lms/borrower/readBookLoanByUser?cardNo=",
	GET_BORROWER_BY_ID: "http://localhost:8080/lms/borrower/readBorrowerById?cardNo=",
	RETURN_BOOK_LOAN: "http://localhost:8080/lms/borrower/returnBookLoan",
	SAVE_BOOK_LOAN: "http://localhost:8080/lms/borrower/saveBookLoan",
	
	
	ADD_BOOK_COPY: "http://localhost:8080/lms/library/saveBookCopy",
	UPDATE_BOOK_COPY: "http://localhost:8080/lms/library/updateBookCopies",
	GET_BOOK_COPY_BY_ID: "http://localhost:8080/lms/library/readBookCopiesById",
	UPDATE_BOOK_COPY2: "http://localhost:8080/lms/library/updateBookCopies2",
	
	
})