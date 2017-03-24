(function () {
    angular
        .module('mainModule')
        .controller('staff.controller', ['$scope', 'dataService', '$mdDialog', '$log',
				function ($scope, dataService, $mdDialog, $log) {

                $scope.staff = [];
                getStaff();
                $scope.OneStaff = {};
           
                $scope.cancel = function (name, surname) {
                    $scope.OneStaff.name = name;
                    $scope.OneStaff.surname = surname;
                    $mdDialog.hide();
                }
                $scope.confirm = function(staff){
                    dataService.updateStaff(staff)
                        .then(function (response) {
                                $log.log('updated  staff id ' + staff.id);
                                $mdDialog.hide();
                            },
                            function (error) {
                                $log.error('error during updating staff ' + error.status);
								$mdDialog.hide();
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

                $scope.getOneStaff = function (id) {
                    dataService.getOneStaff(id)
                        .then(
                            function (response) {
                                $scope.OneStaff = response.data;
                            },
                            function (error) {
                                $log
                                    .error('error during fetching data ' + error.data);
                            });
                }


                $scope.showStaffDetails = function (stf, evt) {
					
					$scope.OneStaff = stf;
					
                    $mdDialog.show({

                        targetEvent: evt,
                        locals: {
                            parent: $scope,
                            stf: stf,
                            name: stf.name,
                            surname: stf.surname
                        },
                        controller: angular.noop,

                        controllerAs: 'ctrl',
                        bindToController: true,

                        template: '<md-dialog aria-label="List dialog" md-theme="light-green">' +
                            '<md-toolbar>' +
                            '<div class="md-toolbar-tools">' +
                            '<h2>{{ctrl.name}} {{ctrl.surname}}</h2>' +
                            '</div>' +
                            '</md-toolbar>' +
                            '<md-dialog-content>' +
                            '<div layout-gt-xs="row">' +
                            '<md-input-container class="md-block" flex-gt-xs>' +
                            '<label>Name</label>' +
                            '<input ng-model="ctrl.parent.OneStaff.name"  size="30" placeholder="{{ctrl.stf.name}}">'+
                            '</md-input-container>' +
                            '<md-input-container class="md-block" flex-gt-xs>' +
                            '<label>Surname</label>' +
                            '<input type="text" ng-model="ctrl.parent.OneStaff.surname" size="30" placeholder="{{ctrl.stf.surname}}">' +
                            '</md-input-container>' +
                            '</div>' +
                            '<md-divider ></md-divider>'+
                            '<md-list> Animals:' +
                            '<md-list-item ng-repeat="animal in ctrl.stf.animals">' +
                            '{{animal.name}}' +
                            '</md-list-item>' +
                            '</md-list>' +
                            '</md-dialog-content>' +
                            '<md-dialog-actions>' +
                            '<md-button ng-click="ctrl.parent.confirm(ctrl.parent.OneStaff)" class="md-primary">CONFIRM</md-button>' +
                            '<md-button ng-click="ctrl.parent.cancel(ctrl.name, ctrl.surname)" class="md-primary">CANCEL</md-button>' +
                            '</md-dialog-actions>' +
                            '</md-dialog>'
                    });
                };

				}]);
}());