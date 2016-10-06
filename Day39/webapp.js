angular.module("AppMod", ["ngRoute"])
	.controller("AppCtrl", ['$http', '$routeParams', function($http, $routeParams) {
  		var self = this;
 		self.id = $routeParams.studentId;

	$http.get("http://localhost:8080/student")
			.then(function(resp) {
				self.students = resp.data;
				for(var s of self.students) {
					s.vis = true;
				}
				}, function(err) {

				});
	

	$http.get('http://localhost:8080/student/'+self.id)
			.then(function(resp){
				self.student = resp.data;
			},function(err) {

			})

	self.refresh = function() {
			window.location.reload();
		};

		
	self.hideStds = function() {
			for(var std of self.students) {
				std.vis = false;
			}};

	self.hidden = function(student) {
			student.vis = false;
		};
			
	}])

	.config(['$routeProvider', function($routeProvider) {

		$routeProvider
		.when('/', {
			templateUrl: 'views/home.view.html'

		}).when('/student', {
			templateUrl: 'views/student.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'

		}).when('/student/:studentId', {
 			templateUrl: 'views/detail.view.html',
 			controller: 'AppCtrl',
 			controllerAs: 'ctrl'

		}).when('/about', {
			templateUrl: 'views/about.view.html'
		
		}).when('/contact', {
			templateUrl: 'views/contact.view.html'
		})

		.otherwise({redirectTo: '/'});

	}]); 