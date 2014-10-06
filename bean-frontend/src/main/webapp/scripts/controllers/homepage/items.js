'use strict';

home.controller('ItemsCtrl', ['$scope', 'Item', function($scope, Item) {
/*    var photos = [];
    for (var i = 0; i < 20; i++) {
        photos[i] = {'title': (i + 1), src: "http://lorempixel.com/300/400/sports/" + (i + 1) };
    }
    $scope.photos = photos;*/
    Item.get(function(ret) {
        $scope.items = ret.entities;
    });

}]);
