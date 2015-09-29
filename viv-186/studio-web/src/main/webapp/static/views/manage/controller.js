(function () {
    'use strict';

    var app = angular.module('VFusionStudio.manage', ['VFusionStudio.topPanel']);
    app.controller('ManageController', ['$scope', 'dataManage', '$location', function ($scope, dataManage, $location) {
        var self = this;
        if (0 < Object.keys(dataManage.getData()).length) {
            if (dataManage.getData().isAdmin) {
                $scope.userName = dataManage.getData().userName;
            } else {
                $location.path('403');
            }
        } else {
            $location.path('dashboard');
        }
    }]);
}());