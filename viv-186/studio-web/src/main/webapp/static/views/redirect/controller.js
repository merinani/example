(function () {
    'use strict';

    var app = angular.module('VFusionStudio.redirect', []);
    app.controller('RedirectFailController', ['$rootScope', '$scope', function ($rootScope, $scope) {
        var self = this;
        $scope.formSubmit = function () {
            document.getElementById("redirectForm").submit();
        };
    }]);
}());