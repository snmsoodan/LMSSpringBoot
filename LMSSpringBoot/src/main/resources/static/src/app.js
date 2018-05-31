var lmsApp=angular.module("lmsApp",["ngRoute","simplePagination"]);

lmsApp.config(["$routeProvider",function($routeProvider){
	return $routeProvider.when("/",{
		redirectTo: "/home"
	})
	
	.when("/home",{
		templateUrl: "./views/home/home.html"
	})
	
	.when("/admin",{
		templateUrl: "./views/admin/admin.html"
	})
	
	.when("/authorList",{
		templateUrl: "./views/admin/author/authorList.html"
	})
	
	
	.when("/borrowerList",{
		templateUrl: "./views/admin/borrower/borrowerList.html"
	})
	
	
}])