(function () {
    'use strict';
    angular
        .module('mainModule')
        .controller('csv.controller', ['$scope', 'dataService', '$log', '$mdDialog',
            function ($scope, dataService, $log, $mdDialog) {

                getAnimalsCSV();

                function getAnimalsCSV() {
                    dataService.getAnimalsCSV()
                        .then(
                            function (response) {
                                showMessage(response);
                            },
                            function (error) {
                                $log.log('error during fetching  CSV data ' + error.data);
                            });
                }

                function saveAs(response) {

                    $log.log("downloading file ");

                    var fname = function (contentDisp) {
                        if (contentDisp) {
                            var result = contentDisp.split(';')[1].trim().split('=')[1];
                            return result.replace(/"/g, '');
                        }
                        return 'default.csv';
                    }(response.headers['Content-Disposition']);

                    download(response.data, fname, 'text/csv');
                }

                function showMessage(response) {
                    var confirm = $mdDialog
                        .confirm()
                        .title('Report is ready')
                        .ariaLabel('Lucky day')
                        .ok('Save file')
                        .cancel('Cancel');

                    $mdDialog.show(confirm).then(function () {
                        saveAs(response)
                    });
                };

                // Function to download data to a file
                function download(data, filename, type) {
                    var file = new Blob([data], {type: type});
                    if (window.navigator.msSaveOrOpenBlob) // IE10+
                        window.navigator.msSaveOrOpenBlob(file, filename);
                    else { // Others
                        var a = document.createElement("a"),
                            url = URL.createObjectURL(file);
                        a.href = url;
                        a.download = filename;
                        document.body.appendChild(a);
                        a.click();
                        setTimeout(function () {
                            document.body.removeChild(a);
                            window.URL.revokeObjectURL(url);
                        }, 0);
                    }
                }
            }]);
}());
