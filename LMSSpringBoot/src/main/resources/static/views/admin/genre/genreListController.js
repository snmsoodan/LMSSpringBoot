lmsApp.controller("genreController",function($scope,$http,lmsFactory,lmsConstants,$location,$window,Pagination){
		
			lmsFactory.getAll(lmsConstants.GET_ALL_GENRES).then(function(data){
				$scope.genres=data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.genres.length/$scope.pagination.perPage);
			})
			
			
			lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
			$scope.books = data;
			$scope.selectedBooks=[];
			
			})
			
			
			$scope.deleteGenre= function(genre){
				lmsFactory.deleteObject(lmsConstants.DELETE_GENRES,genre).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_GENRES).then(function(data){
						$scope.genres=data;
						$window.location="#/genreList";
					})
					
				})
				
			}
			
			$scope.editGenre=function(g){
				$scope.genre=g;
				
			}
			
			$scope.listGenres=function(){
				lmsFactory.getAll(lmsConstants.GET_ALL_GENRES).then(function(data){
					$scope.genres=data;
				})
				
			}
			
			
			$scope.updateGenre=function(){
				lmsFactory.updateObject(lmsConstants.UPDATE_GENRE,$scope.genre).then(function(data){
					lmsFactory.getAll(lmsConstants.GET_ALL_GENRES).then(function(data){
						$scope.genres=data;
					})
				})
			}
			
			
			$scope.addGenre=function(genreName){
				var genre={
						"genreName": genreName,
						"books": $scope.selectedBooks
				}
				
				
				lmsFactory.saveObject(lmsConstants.ADD_GENRE,genre).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_GENRES).then(function(data){
						$scope.genres=data;
						$window.location="#/genreList";
					})
				
				})
			}
			
			$scope.searchGenres = function(name){
				lmsFactory.getAll(lmsConstants.GET_BOOKS_BY_NAME+name).then(function(data){
					$scope.genres = data;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.genres.length/$scope.pagination.perPage);
				})
			}
			
		
	})