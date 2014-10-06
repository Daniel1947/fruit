'use strict';

home.factory('Category', ['$resource', function($resource) {
    return $resource('rest/category/all');
}]);