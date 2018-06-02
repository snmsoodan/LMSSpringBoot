lmsApp.controller("libraryBranchController",function($scope,$http,lmsFactory,lmsConstants,$location,$window,Pagination){
		
			lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
				$scope.branches=data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.branches.length/$scope.pagination.perPage);
			})
			
			
			lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
			$scope.books = data;
			$scope.selectedBooks=[];
			
			})
			
			
			$scope.deleteBranch= function(branch){
				lmsFactory.deleteObject(lmsConstants.DELETE_BRANCH,branch).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
						$scope.branches=data;
						$window.location="#/libraryBranchList";
					})
					
				})
				
			}
			
			$scope.editBranch=function(br){
				$scope.branch=br;
				
			}
			
			$scope.listBranches=function(){
				lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
					$scope.branches=data;
				})
				
			}
			
			
			$scope.updateBranch=function(){
				lmsFactory.updateObject(lmsConstants.UPDATE_BRANCH,$scope.branch).then(function(data){
					lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
						$scope.branches=data;
					})
				})
			}
			
			
			$scope.addBranch=function(branchName,branchAddress){
				var branch={
						"branchName": branchName,
						"branchAddress": branchAddress
				}
				
				
				lmsFactory.saveObject(lmsConstants.ADD_BRANCH,branch).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
						$scope.branches=data;
						$window.location="#/libraryBranchList";
					})
				
				})
			}
			
			$scope.searchBranches = function(name){
				lmsFactory.getAll(lmsConstants.GET_BRANCHES_BY_NAME+name).then(function(data){
					$scope.branches = data;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.branches.length/$scope.pagination.perPage);
				})
			}
			
		
	})