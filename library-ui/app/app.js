'use strict';

// Declare app level module which depends on views, and core components
angular.module('myApp', [
  'ngMaterial',
  'ngMessages',
  'vcRecaptcha',
  'oitozero.ngSweetAlert',
  'md.data.table',
  'ngRoute',
  'myApp.list-book',
  'myApp.add-book'
]).config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider.otherwise({redirectTo: '/list-book'});
}]);
