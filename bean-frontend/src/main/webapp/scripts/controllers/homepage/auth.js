'use strict';

home.controller('AuthCtrl', ['$scope', '$cookieStore', '$location', 'User', function($scope, $cookieStore, $location, User) {
    var signedIn = false;

    $scope.signup = function() {
        var user = {
            email: $scope.user.email,
            username: $scope.user.name,
            password: $scope.user.password
        };
        $scope.dismiss();
    };

    $scope.login = function() {
        User.get({user: $scope.user.name, passwd: $scope.user.password}, function(user) {
            console.log(user);
            $scope.user = user;
            $cookieStore.put('user', user);
            signedIn = true;
            $scope.dismiss();
        });
    };

    $scope.signedIn = function() {
        if (signedIn) {
            return true;
        } else {
            var user = $cookieStore.get('user');
            if (user != null) {
                $scope.user = user;
                return true;
            }
        }
        return false;
    };

    $scope.logout = function() {
        signedIn = false;
        $cookieStore.remove('user');
        $location.path('/');
    };
}]);

home.directive('myModal', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            scope.dismiss = function() {
                element.modal('hide');
            };
        }
    }
});
