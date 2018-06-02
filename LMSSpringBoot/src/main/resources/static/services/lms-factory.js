lmsApp.factory("lmsFactory",function($http){
	return{
		
		getAll: function(url){
			console.log(url);
			var returnList={};
			return $http.get(url).success(function(data){
				returnList=data;
			}).then(function(){
				return returnList;
			})
		},
		
		deleteObject: function(url,object){
			return $http.post(url,object);
		},
		
		updateObject: function(url,object){
			return $http.put(url,object);
		},
		
		saveObject: function(url,object){
			return $http.post(url,object);
		},
		
	}
})