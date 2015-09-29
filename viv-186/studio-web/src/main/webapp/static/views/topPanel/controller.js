(function () {
    'use strict';

    var app = angular.module('VFusionStudio.topPanel', []);
    app.service('dataManage', function () {
        var _data = {};

        return {
            getData: function () {
                return _data
            },
            setData: function (data) {
                _data = data;
            }
        }
    });
    app.controller('TopPanelController', ['$scope', '$rootScope', '$http', 'dataManage', '$location', function ($scope, $rootScope, $http, dataManage, $location) {
        $scope.manageNavigation = function (currentUserHasAccess, projectName) {
            var params = {
                'userName': $rootScope.userData.userName,
                'isAdmin': $rootScope.userData.isAdmin,
                'isLogged': $rootScope.userData.isLogged
            };
            dataManage.setData(params);
            $location.path('manage');
        }
    }]);
}());