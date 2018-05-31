lmsApp.controller("authorController",function($scope,$http,lmsFactory,lmsConstants,$location,$window){
		
			lmsFactory.getAll(lmsConstants.GET_ALL_AUTHORS).then(function(data){
				$scope.authors=data;
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
			
		
	})