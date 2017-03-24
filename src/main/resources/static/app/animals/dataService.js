(function (){
angular
    .module('mainModule')
    .factory('dataService', ['$http',  function ($http) {
	
	var dataService = {};
	var urlAnimalTemplate = 'http://localhost:8080/rest/animals/';
	var urlStaffTemplate = 'http://localhost:8080/rest/staff/';
	
	dataService.getAnimals = function() {
        return $http({
    		method: 'GET',
    		url: urlAnimalTemplate 
    	});
    };
    
    dataService.getStaff = function() {
        return $http({
    		method: 'GET',
    		url: urlStaffTemplate 
    	});
    };
    
    dataService.getOneStaff = function(id) {
        return $http({
    		method: 'GET',
    		url: urlStaffTemplate + id 
    	});
    };

    dataService.deleteAnimal = function(id){
    	return $http({
    		method: 'DELETE',
    		url: urlAnimalTemplate + id
    	});
    };
    
    dataService.addAnimal = function(animal, id){
    	return $http({
    		method: 'POST',
    		url: urlAnimalTemplate + id, 
    		data: animal
    	});
    };
        
    dataService.updateStaff = function(staff){
    	return $http({
    		method: 'PUT',
    		url: urlStaffTemplate , 
    		data: staff
    	});
    };
    
    return dataService;
    
}]);
}());