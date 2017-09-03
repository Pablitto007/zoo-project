(function (){
    'use strict';
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
        
    dataService.getAnimalsPage = function(pageNumber) {
        return $http({
    		method: 'GET',
    		url: urlAnimalTemplate +'page/' + pageNumber
    	});
    };
    
    dataService.getStaff = function() {
        return $http({
    		method: 'GET',
    		url: urlStaffTemplate 
    	});
    };
        
    dataService.getStaffPage = function(pageNumber) {
        return $http({
    		method: 'GET',
    		url: urlStaffTemplate +'page/' + pageNumber
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
        
    dataService.updateStaff = function(staff, id){
    	return $http({
    		method: 'PUT',
    		url: urlStaffTemplate +id,
    		data: staff
    	});
    };
    
    return dataService;
    
}]);
}());