(function () {
    'use strict';

    var app = angular.module('VFusionStudio.dashboard', ['ngTable']);
    app.service('dataService', function () {
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
    app.controller('DashboardController', ['$scope', '$http', '$filter', 'NgTableParams', '$location', 'dataService', function ($scope, $http, $filter, NgTableParams, $location, dataService) {
        var self = this;
        $scope.projects = [];

        self.getProjects = function () {

            //if($scope.isAuthenticated) {	
            $http.get('projects/').
                success(function (data) {
                    $scope.projects = data;
                    self.loadTable();
                }).error(function () {
                    $scope.projects = null;
                });
            //}

            $scope.listRowClick = function (currentUserHasAccess, projectName) {
                var params = { 'projectName': projectName, 'hasAccess': currentUserHasAccess };
                //$state.go(currentUserHasAccess ? 'editor' : '403', params);
                dataService.setData(params);
                //setTimeout(function () {
                    $location.path(currentUserHasAccess ? 'editor' : '403');
                //}, 50);
            }
        };
        self.getProjects();

        self.loadTable = function () {
            try {
                $scope.tableParams = new NgTableParams({
                    // show first page
                    page: 1,
                    // count per page
                    count: 10,
                    // initial filter
                    filter: {
                        'projectName': '',
                        'version': '',
                        'lastUpdated': '',
                        'lastUpdatedUser': ''
                    },
                    // initial sorting
                    sorting: {
                        'projectName': 'asc'
                    }
                }, {
                    filterDelay: 0,
                    // length of data
                    total: $scope.projects.length,
                    getData: function ($defer, params) {
                        // use build-in angular filter
                        var filteredData = params.filter() ? $filter('filter')($scope.projects, params.filter()) : $scope.projects;
                        var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : $scope.projects;
                        params.total(orderedData.length); // set total for recalc pagination
                        $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                    }
                });
            } catch (e) {
                console.log(e);
            }
        };
    }]);
}());