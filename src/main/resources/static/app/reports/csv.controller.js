(function () {
    'use strict';
    angular
        .module('mainModule')
        .controller('csv.controller', ['$scope', 'dataService', '$log',
            function ($scope, dataService, $log) {

            getAnimalsCSV();

                function getAnimalsCSV() {
                    dataService.getAnimalsCSV()
                        .then(
                            function (response) {
                            console.log(response.status);
                            }
                            ,
                            function (error) {
                            console.log('error during fetching  CSV data ' + error.data);
                            });
                }


            }]);
}());