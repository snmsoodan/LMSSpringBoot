lmsApp.controller("homeController",function($scope,$http,lmsFactory,lmsConstants,$location,$window,Pagination){
	
	$scope.readBookLoanByUser= function(cardNo){
		console.log("homeController");
		lmsFactory.getAll(lmsConstants.GET_BORROWER_BY_ID+cardNo).then(function(data){
			console.log(data);
			if(data.length == 0 || data == 'undefined')
			{
				$scope.error="Card Number Invalid... Try Again";
				console.log($scope.error);
			}

			else
			{
				$location.url("/borrower/bookLoanList/"+data[0].cardNo);
			}

		})
		
	}
	
	
})