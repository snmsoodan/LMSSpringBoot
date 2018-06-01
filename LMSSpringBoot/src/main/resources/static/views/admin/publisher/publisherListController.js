lmsApp.controller("publisherController",function($scope,$http,lmsFactory,lmsConstants,$location,$window,Pagination){
		
			lmsFactory.getAll(lmsConstants.GET_ALL_PUBLISHERS).then(function(data){
				$scope.publishers=data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.publishers.length/$scope.pagination.perPage);
			})
			
			
//			lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
//			$scope.books = data;
//			$scope.selectedBooks=[];
//			
//			})
			
			
			$scope.deletePublisher= function(publisher){
				lmsFactory.deleteObject(lmsConstants.DELETE_PUBLISHER,publisher).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_PUBLISHERS).then(function(data){
						$scope.publishers=data;
						$window.location="#/publisherList";
					})
					
				})
				
			}
			
			$scope.editPublisher=function(p){
				$scope.publisher=p;
				
			}
			
			$scope.listPublishers=function(){
				lmsFactory.getAll(lmsConstants.GET_ALL_PUBLISHERS).then(function(data){
					$scope.publishers=data;
				})
				
			}
			
			
			$scope.updatePublisher=function(){
				lmsFactory.updateObject(lmsConstants.UPDATE_PUBLISHER,$scope.publisher).then(function(data){
					lmsFactory.getAll(lmsConstants.GET_ALL_PUBLISHERS).then(function(data){
						$scope.publishers=data;
					})
				})
			}
			
			
			$scope.addPublisher=function(publisherName,publisherAddress,publisherPhone){
				var publisher={
						"publisherName": publisherName,
						"publisherAddress":publisherAddress,
						"publisherPhone":publisherPhone,
//						"books": $scope.selectedBooks
				}
				
				
				lmsFactory.saveObject(lmsConstants.ADD_PUBLISHER,publisher).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_PUBLISHERS).then(function(data){
						$scope.publishers=data;
						$window.location="#/publisherList";
					})
				
				})
			}
			
			$scope.searchPublishers = function(name){
				lmsFactory.getAll(lmsConstants.GET_PUBLISHERS_BY_NAME+name).then(function(data){
					$scope.publishers = data;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.publishers.length/$scope.pagination.perPage);
				})
			}
			
		
	})