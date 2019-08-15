(function () {
    'use strict';
    angular.module('mainModule')
        .controller('animal-list.controller', ['$scope', 'dataService', '$mdDialog', '$log', '$mdSidenav', '$filter',
            function ($scope, dataService, $mdDialog, $log, $mdSidenav, $filter) {

                $scope.currentPage = 1;
                $scope.animals = [];
                $scope.staff = [];
                getStaff();

                $scope.selected = [];

                $scope.paging = {
                    total: 10,
                    current: 1,
                    onPageChanged: loadPages,
                };

                function loadPages() {
                    console.log('Current page is : ' + $scope.paging.current);

                    getAnimalsPage($scope.paging.current);

                    $scope.currentPage = $scope.paging.current;
                }

                function getAnimalsPage(pageNumber) {
                    dataService.getAnimalsPage(pageNumber)
                        .then(
                            function (response) {
                                $scope.animals = response.data.content;
                                $scope.paging.total = response.data.totalPages;

                            },
                            function (error) {
                                $log
                                    .error('error during fetching data ' + error.data);
                            });
                }

                function getAnimals() {
                    dataService.getAnimals()
                        .then(
                            function (response) {
                                $scope.animals = response.data;
                            },
                            function (error) {
                                $log
                                    .error('error during fetching data ' + error);
                            });
                }

                function getStaff() {
                    dataService.getStaff()
                        .then(
                            function (response) {
                                $scope.staff = response.data;
                            },
                            function (error) {
                                $log
                                    .error('error during fetching data ' + error.data);
                            });
                }

                $scope.deleteAnimal = function (id) {
                    dataService.deleteAnimal(id)
                        .then(
                            function (response) {
                                $log.log('deleted animal id: ' + id)
                                for (var i = 0; i < $scope.animals.length; i++) {
                                    var animal = $scope.animals[i];
                                    if (animal.id == id) {
                                        $scope.animals.splice(i, 1);
                                    }
                                }
                            },
                            function (error) {
                                $log
                                    .error('error during delete animal id' + id);
                            });
                };

                $scope.selectedItem = null;


                $scope.addAnimal = function (animal) {
                    animal.birthDate = $filter('date')($scope.picker.birthDate, "dd/MM/yyyy");
                    animal.arrivalDate = $filter('date')($scope.picker.arrivalDate, "dd/MM/yyyy");
                    var id = JSON.stringify($scope.selectedItem.id);
                    $log.log(id);
                    dataService.addAnimal(animal, id)
                        .then(function (response) {
                                $log.log('added new Animal ')
                                $scope.animals.push(animal);
                                $scope.toggleLeft();
                                animal = null;
                            },
                            function (error) {
                                $log.error('error during add animal ' + error.status);
                            });
                };

                $scope.showConfirm = function (id) {
                    var confirm = $mdDialog
                        .confirm()
                        .title('Would you like to delete this animal?')
                        .textContent('Delete can not be undone.')
                        .ariaLabel('Lucky day')
                        .ok('Please do it!')
                        .cancel('No, maybe later');

                    $mdDialog.show(confirm).then(function () {
                        $scope.deleteAnimal(id);
                    });
                };

                $scope.toggleLeft = buildToggler('left');
                $scope.toggleRight = buildToggler('right');

                function buildToggler(componentId) {
                    return function () {
                        $mdSidenav(componentId).toggle();
                    };
                }

                <!--min and max(current date) dates for date pickers-->

                $scope.myDate = new Date();

                $scope.minDate = new Date($scope.myDate
                    .getFullYear() - 5, $scope.myDate
                    .getMonth(), $scope.myDate
                    .getDate());

                $scope.maxDate = new Date($scope.myDate
                    .getFullYear(), $scope.myDate
                    .getMonth(), $scope.myDate
                    .getDate());

            }]);
}());
