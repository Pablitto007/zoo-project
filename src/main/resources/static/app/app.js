(function () {
    'use strict';

var app = angular.module('mainModule', ['ngMaterial', 'ngRoute', 'cl.paging']);

app.config(['$routeProvider' , '$locationProvider', '$mdThemingProvider', 
            function ($routeProvider, $locationProvider, $mdThemingProvider) {
	
	$mdThemingProvider.theme('docs-dark')
	.primaryPalette('green')
    .warnPalette('red')
    .accentPalette('green')
    .dark();
    
    $mdThemingProvider.setDefaultTheme('docs-dark');
	
    $mdThemingProvider.theme('light-green').primaryPalette('green');
       
	
	$locationProvider.html5Mode(true);
	$routeProvider
	.when('/animals', {
        templateUrl: '/app/animals/animal-list.template.html', 
        controller: 'animal-list.controller'
    })
    .when('/staff', {
        templateUrl: '/app/staff/staff.template.html', 
        controller: 'staff.controller'
    })
    .otherwise({ redirectTo: '/animals'});
	
}]);

})();