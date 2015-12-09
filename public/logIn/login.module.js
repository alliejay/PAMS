(function () {
  "use strict";
  angular
    .module('pamsLogin', [
      'ngRoute',
      'underscore'
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/', {
          templateUrl: 'login/views/login.html',
          controller: 'LoginController as loginCtrl',
        })
        .otherwise({ redirectTo: '/404'});
    });

  angular
    .module('underscore', [])
    .factory('_', function($window){
      return $window._;
    });

})();
