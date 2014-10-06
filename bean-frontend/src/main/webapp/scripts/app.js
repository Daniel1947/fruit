'use strict';

var home = angular.module('homepage', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute']);

home.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'ItemsCtrl',
            templateUrl: 'views/homepage/items.html'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);


