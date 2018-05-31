lmsApp.controller("borrowerController",function($scope,$http,lmsFactory,lmsConstants,$location,$window,Pagination){
		
			lmsFactory.getAll(lmsConstants.GET_ALL_BORROWERS).then(function(data){
				$scope.borrowers=data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.borrowers.length/$scope.pagination.perPage);
			})
			
			
			
			$scope.deleteBorrower= function(borrower){
				lmsFactory.deleteObject(lmsConstants.DELETE_BORROWER,borrower).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_BORROWERS).then(function(data){
						$scope.borrowers=data;
						$window.location="#/borrowerList";
					})
					
				})
				
			}
			
			$scope.editBorrower=function(b){
				$scope.borrower=b;
				
			}
			
			$scope.listBorrowers=function(){
				lmsFactory.getAll(lmsConstants.GET_ALL_BORROWERS).then(function(data){
					$scope.borrowers=data;
				})
				
			}
			
			
			$scope.updateBorrower=function(){
				lmsFactory.updateObject(lmsConstants.UPDATE_BORROWER,$scope.borrower).then(function(data){
					lmsFactory.getAll(lmsConstants.GET_ALL_BORROWERS).then(function(data){
						$scope.borrowers=data;
					})
				})
			}
			
			
			$scope.addBorrower=function(name,address,phone){
				var borrower={
						"name": name,
						"address": address,
						"phone": phone,
						
				}
				
				
				lmsFactory.saveObject(lmsConstants.ADD_BORROWER,borrower).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_BORROWERS).then(function(data){
						$scope.borrowers=data;
						$window.location="#/borrowerList";
					})
				
				})
			}
			
			$scope.searchBorrowers = function(borrowerName){
				lmsFactory.getAll(lmsConstants.GET_BORROWERS_BY_NAME+borrowerName).then(function(data){
					$scope.borrowers = data;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.borrowers.length/$scope.pagination.perPage);
				})
			}
			
		
	})