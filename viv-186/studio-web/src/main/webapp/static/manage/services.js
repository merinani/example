/* Services */
(function () {
    'use strict';

    angular.module('VFusionStudio.services', [])

    .value('ServerAddress', 'http://localhost:8080')

    .factory('AuthInterceptor', ['$q', '$location', function ($q, $location) {
        return {
            'responseError': function (response) {
                if (response.status === 401) { // Unauthorized
                    Session.destroy();
                    $location.path('/login');
                }
                return $q.reject(response);
            }
        };
    }]);
}());