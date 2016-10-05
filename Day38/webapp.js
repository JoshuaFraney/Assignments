angular.module("AppMod", ["ngRoute"])
	.controller("AppCtrl", ['$http', function($http) {
		var self = this;

		$http.get('http://localhost:8080/student')
			.then(function(resp){
				self.students = resp.data;
			},function(err) {

			});

	}])
	.config(['$routeProvider', function($routeProvider) {

		$routeProvider
		.when('/', {
			templateUrl: 'views/home.view.html'

		}).when('/student', {
			templateUrl: 'views/student.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'

		}).when('/about', {
			templateUrl: 'views/about.view.html'
		
		}).when('/contact', {
			templateUrl: 'views/contact.view.html'
		})

		.otherwise({redirectTo: '/'});

	}]); // end config