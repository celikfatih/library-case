'use strict';

angular.module('myApp.add-book', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/add-book', {
    templateUrl: 'add-book/add-book.html',
    controller: 'AddBookCtrl'
  });
}])
.controller('AddBookCtrl', function($scope, $location, BookService, SweetAlert) {
  $scope.captcha = {};
  $scope.book = {};
  $scope.save = function() {
    if ($scope.bookForm.$valid) {
      $scope.loading = true;
      BookService.save($scope.book)
      .then(function(res) {
        SweetAlert.swal("Success!", "Book has been successfully created!", "success");
        $location.path('/list-book');
      })
      .catch(function(err) {
        SweetAlert.swal("Error", "Something went wrong. Please try again later!", "error");
      })
      .finally(function() {
        $scope.loading = false;
      });
    }
  };
});