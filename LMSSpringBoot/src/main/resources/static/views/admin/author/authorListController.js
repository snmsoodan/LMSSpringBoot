lmsApp.controller("authorController",function($scope,$http,lmsFactory,lmsConstants,$location,$window,Pagination){
		
			lmsFactory.getAll(lmsConstants.GET_ALL_AUTHORS).then(function(data){
				$scope.authors=data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
			})
			
			
			lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
			$scope.books = data;
			$scope.selectedBooks=[];
			
			})
			
			
			$scope.deleteAuthor= function(author){
				lmsFactory.deleteObject(lmsConstants.DELETE_AUTHOR,author).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_AUTHORS).then(function(data){
						$scope.authors=data;
						$window.location="#/authorList";
					})
					
				})
				
			}
			
			$scope.editAuthor=function(a){
				$scope.author=a;
				
			}
			
			$scope.listAuthors=function(){
				lmsFactory.getAll(lmsConstants.GET_ALL_AUTHORS).then(function(data){
					$scope.authors=data;
				})
				
			}
			
			
			$scope.updateAuthor=function(){
				lmsFactory.updateObject(lmsConstants.UPDATE_AUTHOR,$scope.author).then(function(data){
					lmsFactory.getAll(lmsConstants.GET_ALL_AUTHORS).then(function(data){
						$scope.authors=data;
					})
				})
			}
			
			
			$scope.addAuthor=function(authorName){
				var author={
						"authorName": authorName,
						"books": $scope.selectedBooks
				}
				
				
				lmsFactory.saveObject(lmsConstants.ADD_AUTHOR,author).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_AUTHORS).then(function(data){
						$scope.authors=data;
						$window.location="#/authorList";
					})
				
				})
			}
			
			$scope.searchAuthors = function(authorName){
				lmsFactory.getAll(lmsConstants.GET_AUTHORS_BY_NAME+authorName).then(function(data){
					$scope.authors = data;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
				})
			}
			
		
	})