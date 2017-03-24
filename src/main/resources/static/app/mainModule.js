 //script.js  -- main module --

(function () {

var app = angular.module('mainModule', ['ngMaterial', 'ngRoute']);

app.config(['$routeProvider' , '$locationProvider', '$mdThemingProvider', 
            function ($routeProvider, $locationProvider, $mdThemingProvider) {
	
	$mdThemingProvider.theme('docs-dark', 'default')
    .primaryPalette('green')
    .warnPalette('red')
    .accentPalette('green')
    .dark();
	
	$mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
    $mdThemingProvider.theme('light-green').primaryPalette('green');
    $mdThemingProvider.theme('dark-green').backgroundPalette('teal').dark();         
	
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