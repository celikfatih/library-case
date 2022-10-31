'use strict';

angular.module('myApp.list-book', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/list-book', {
    templateUrl: 'list-book/list-book.html',
    controller: 'ListBookCtrl'
  });
}])
.service('BookService', function($http) {
  this.getAll = function(page, size) {
    return $http.get("http://localhost:8082/books/all?page="+page+"&size="+size);
  };
  this.save = function(data) {
    return $http.post("http://localhost:8082/books", data);
  };
  this.update = function(id, data) {
    return $http.put("http://localhost:8082/books/"+id, data);
  }
  this.delete = function(id) {
    return $http.delete("http://localhost:8082/books/"+id);
  };
})
.controller('ListBookCtrl', function($scope, $mdDialog, $filter, BookService, SweetAlert) {
  $scope.books = [];
  $scope.pageable = {
    order: 'name',
    limit: 5,
    page: 1,
    totalElements: 0,
  };

  $scope.getAll = function(page, limit) {
    $scope.loading = true;
    BookService.getAll(page-1, limit)
      .then(function(response) {
        $scope.books = response.data.content;
        $scope.pageable.totalElements = response.data.totalElements;
      })
      .catch(function(err) {
        SweetAlert.swal("Error!", "An error occurred while retrieving books!", "error");
      })
      .finally(function() {
        $scope.loading = false;
      });
  };

  $scope.update = function(body) {
    BookService.update(body.id, body)
      .then(function(response) {
        $scope.getAll($scope.pageable.page, $scope.pageable.limit);
        SweetAlert.swal("Updated!", "Book has been updated.", "success");
      })
      .catch(function(err) {
        SweetAlert.swal("Error", "An error occurred while updating book!", "error");
      })
  };

  $scope.delete = function(id) {
    SweetAlert.swal({
      title: "Are you sure?",
      text: "Your will not be able to recover this book!",
      type: "warning",
      showCancelButton: true,
      confirmButtonColor: "#DD6B55",
      confirmButtonText: "Yes, delete it!",
      closeOnConfirm: false,
      closeOnCancel: true,
    }, function(isConfirm) { 
      if (isConfirm) {
        BookService.delete(id)
          .then(function(response) {
            SweetAlert.swal("Deleted!", "Book has been deleted.", "success");
            $scope.getAll($scope.pageable.page, $scope.pageable.limit);
          })
          .catch(function(err) {
            SweetAlert.swal("Error!", "An error occurred while deleting book!", "error");
          });
      }
    });
  };

  $scope.showUpdateDialog = function(ev, id) {
    $scope.current = $filter('filter')($scope.books, {'id': id})[0];
    $mdDialog.show({
      controller: function($scope, book) {
        $scope.book = book;
        $scope.cancel = function () {
          $mdDialog.cancel();
        };
    
        $scope.onSubmit = function () {
          $mdDialog.hide($scope.book);
        };
      },
      templateUrl: 'update-book.html',
      locals: {
        book: {
          id: $scope.current.id,
          name: $scope.current.name,
          author: $scope.current.author,
        }
      },
      parent: angular.element(document.body),
      targetEvent: ev,
      scope: $scope,
      preserveScope: true,
      clickOutsideToClose: true,
      fullscreen: $scope.customFullscreen
    })  
    .then(function (updated) {
      $scope.update(updated);
    }, function(reject) {
      
    });
  }

  $scope.getAll($scope.pageable.page, $scope.pageable.limit);
});