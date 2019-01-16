var app = angular.module("AddressBookManagement", []);

app.controller("welcome", function($scope, $http) {
	$scope.message = "Welcome to Address Boot Sample Application !";
});
