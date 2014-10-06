'use strict';

home.factory('Item', ['$resource', function($resource) {
    return $resource('rest/item');
}]);