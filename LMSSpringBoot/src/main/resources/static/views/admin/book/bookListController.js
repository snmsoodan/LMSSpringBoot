lmsApp.controller("bookController",function($scope,$http,lmsFactory,lmsConstants,$location,$window,Pagination){
		
			lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
				$scope.books=data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
			})
			
			
			lmsFactory.getAll(lmsConstants.GET_ALL_PUBLISHERS).then(function(data){
			$scope.publishers = data;
			$scope.selectedPublisher={};
			
			})
			
			lmsFactory.getAll(lmsConstants.GET_ALL_AUTHORS).then(function(data){
			$scope.authors = data;
			$scope.selectedAuthors=[];
			
			})
			
			lmsFactory.getAll(lmsConstants.GET_ALL_GENRES).then(function(data){
			$scope.genres = data;
			$scope.selectedGenres=[];
			
			})
			
			$scope.deleteBook= function(book){
				console.log(book);
				lmsFactory.deleteObject(lmsConstants.DELETE_BOOKS,book).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
						$scope.books=data;
						$window.location="#/bookList";
					})
					
				})
				
			}
			
			$scope.editBook=function(b){
				$scope.book=b;
				
			}
			
			$scope.listBooks=function(){
				lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
					$scope.books=data;
				})
				
			}
			
			
			$scope.updateBook=function(){
				lmsFactory.updateObject(lmsConstants.UPDATE_BOOK,$scope.book).then(function(data){
					lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
						$scope.books=data;
					})
				})
			}
			
			
			$scope.addBook=function(title){
				var book={
						"title": title,
						"publisher": $scope.selectedPublisher,
						"authors": $scope.selectedAuthors,
						"genres": $scope.selectedGenres
				}
				
				lmsFactory.saveObject(lmsConstants.ADD_BOOK,book).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
						$scope.books=data;
						$window.location="#/bookList";
					})
				
				})
			}
			
			$scope.searchBooks = function(name){
				lmsFactory.getAll(lmsConstants.GET_BOOKS_BY_NAME+name).then(function(data){
					$scope.books = data;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
				})
			}
			
		
	})