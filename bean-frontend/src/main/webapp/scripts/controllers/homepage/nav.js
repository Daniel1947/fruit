'use strict';

home.controller("NavCtrl", ['$scope', 'Category', function($scope, Category) {
    Category.get(function(ret) {
        $scope.categories = ret.categories;
    });
}]);
