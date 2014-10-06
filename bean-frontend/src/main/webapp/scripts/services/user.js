'use strict';

home.factory('User', ['$resource', function($resource) {
    return $resource('rest/user/account=:user&pwd=:passwd', {user: '@name', passwd: '@passwd'});
}]);