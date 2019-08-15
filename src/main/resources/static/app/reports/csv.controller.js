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
                                console.log("ok");
                                console.log("payload " + response.data);

                                saveAs(response);
                            }
                            ,
                            function (error) {
                                console.log('error during fetching  CSV data ' + error.data);
                            });
                }

                function saveAs(response) {

                    console.log(typeof response.headers);

                    /* var fname = function (contentDisp) {
                         if (contentDisp) {
                             var result = contentDisp.split(';')[1].trim().split('=')[1];
                             return result.replace(/"/g, '');
                         }

                         return 'animals1.csv';
                     }(response.headers['Content-Disposition']);

                     var link = document.createElement('a');
                     link.download = fname;
                     link.href = 'data:attachment/csv,' + encodeURI(response.data);
                     link.target = '_blank'
                     document.appendChild(link);
                     link.click();*/

                    //var file = new Blob([response.data], { type: 'text/csv' });
                    //saveAs(file, 'filename.csv');
                }

            }]);
}());
