(function () {
    'use strict';

    var VFusion = angular.module('VFusionStudio', [
        'VFusionStudio.directives',
        'VFusionStudio.services',
        'ngRoute',
        'VFusionStudio.dashboard',
        'VFusionStudio.topPanel',
        'VFusionStudio.editor',
        'VFusionStudio.manage',
        'VFusionStudio.error',
        'VFusionStudio.redirect',
        'VFusionStudio.logout',
    ]);

    VFusion.run(['$rootScope', '$http', 'dataManage', '$location', function ($rootScope, $http, dataManage, $location) {
        $rootScope.userData = [];
        $rootScope.csrf = [];

        var getCsrf = function () {
            $http.get('csrf/').success(function (data) {
                $rootScope.csrf = data;
                getUserData();
            }).error(function () {
                $rootScope.csrf = null;
            });
        };
        
        var getUserData = function () {
            $http.get('userdata/').success(function (userData) {
                $rootScope.userData = userData;
                $rootScope.$on('$locationChangeStart', function (event, next, current) {
                    // redirect to login page if not logged in and trying to access a restricted page
                    var restrictedPage = $.inArray($location.path(), ['/redirect']) === -1;
                    var loggedIn = $rootScope.userData.isLogged;
                    if (restrictedPage && !loggedIn) {
                        $location.path('/redirect');
                    }
                });
                if (!userData.isLogged) {
                    $location.path('/redirect');
                }
            }).error(function () {
                $rootScope.userData = null;
            });
        };
        getCsrf();
    }]);

    VFusion.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
          .when('/dashboard', {
              templateUrl: 'static/views/dashboard/view.html',
              controller: 'DashboardController'
          })
          .when('/editor', {
              templateUrl: 'static/views/editor/view.html',
              controller: 'EditorController'
          })
          .when('/403', {
              templateUrl: 'static/views/error/view.html',
              controller: 'ErrorController'
          })
          .when('/redirect', {
              templateUrl: 'static/views/redirect/view.html',
              controller: 'RedirectFailController'
          })
          .when('/manage', {
              templateUrl: 'static/views/manage/view.html',
              controller: 'ManageController'
          })
          .otherwise({ redirectTo: '/dashboard' });
    }]);

    VFusion.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('AuthInterceptor');
    }]);
}());