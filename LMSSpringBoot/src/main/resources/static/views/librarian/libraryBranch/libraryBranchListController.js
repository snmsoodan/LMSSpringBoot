lmsApp.controller("libraryController",function($scope,$http,lmsFactory,lmsConstants,$location,$window,Pagination){
		
			lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
				$scope.branches=data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.branches.length/$scope.pagination.perPage);
			})
			
			
			lmsFactory.getAll(lmsConstants.GET_ALL_BOOKS).then(function(data){
			$scope.books = data;
			$scope.selectedBooks={};
			
			})
			
			
			$scope.deleteBranch= function(branch){
				lmsFactory.deleteObject(lmsConstants.DELETE_BRANCH,branch).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
						$scope.branches=data;
						$window.location="#/librarian/libraryBranchList";
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
			
			$scope.updateBranchBooks=function(noOfCopies){
				var noc=parseInt(noOfCopies);
				var check=0;
				
				var bookCopies={
						"bookId": $scope.selectedBooks.bookId,
						"branchId": $scope.branch.branchId,
						"noOfCopies": noc
				}
				
				for(var i in  $scope.branch.books)
					{
					
					
					
						if($scope.selectedBooks.title == $scope.branch.books[i].title)
							{
								check=1;
								//code to update the tbl_book_copies table
								console.log("update");
								lmsFactory.updateObject(lmsConstants.UPDATE_BOOK_COPY,bookCopies).then(function(data){
								lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
									$scope.branches=data;
									 $location.url("#/librarian/libraryBranchList");
//									$window.location="#/librarian/libraryBranchList";
								})
							})
								
							}
					}
				
				if(check===0)
					{
						//code to add book to tbl_book_copies
						console.log("add");
						lmsFactory.saveObject(lmsConstants.ADD_BOOK_COPY,bookCopies).then(function(){
							lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
								$scope.branches=data;
								$window.location="#/librarian/libraryBranchList";
							})
						
						})
						
					}
				
				
				
//				lmsFactory.updateObject(lmsConstants.UPDATE_BRANCH,$scope.branch).then(function(data){
//					lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
//						$scope.branches=data;
//					})
//				})
			}
			
			
			$scope.addBranch=function(branchName,branchAddress){
				var branch={
						"branchName": branchName,
						"branchAddress": branchAddress
				}
				
				
				lmsFactory.saveObject(lmsConstants.ADD_BRANCH,branch).then(function(){
					lmsFactory.getAll(lmsConstants.GET_ALL_BRANCHES).then(function(data){
						$scope.branches=data;
						$window.location="#/librarian/libraryBranchList";
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