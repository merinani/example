(function () {
    'use strict';

    var app = angular.module('VFusionStudio.logout', []);
    app.controller('LogoutController', ['$rootScope','$scope', function ($rootScope, $scope) {
        var self = this;
        $scope.formSubmit = function () {
            document.getElementById("logoutForm").submit();
        };
    }]);
}());