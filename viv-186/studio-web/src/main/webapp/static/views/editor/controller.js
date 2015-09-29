(function () {
    'use strict';

    var app = angular.module('VFusionStudio.editor', ['VFusionStudio.dashboard']);
    app.controller('EditorController', ['$scope', 'dataService', function ($scope, dataService) {
        var self = this;
        $scope.proName = dataService.getData().projectName;
        $scope.hasAccess = dataService.getData().hasAccess;
    }]);
}());